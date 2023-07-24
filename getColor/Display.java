package getColor;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display extends JFrame {

    JButton startButton;
    JButton rgbButton;
    JButton hexButton;
    JPanel Panel;
    int pixelWH = 300;
    Color lastFoundColor = null;
    String lastFoundColorRGB = "";
    String lastFoundColorHEX = "";

    Display() {
        start();
    }

    public void start() {

        this.setTitle("Get Color");

        Panel = colorPanel(); // sólo muestran texto o imgs, llenar con el nombre del código de color
        Panel.setBackground(new Color(0x00FF00));

        startButton = mainButton();
        rgbButton = colorCodeButtons(30, "Copy RGB");
        hexButton = colorCodeButtons(165, "Copy HEX");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(pixelWH, pixelWH);
        this.setVisible(true);
        this.setLayout(null);

        this.add(startButton);
        this.add(rgbButton);
        this.add(hexButton);

    }

    private JPanel colorPanel() {

        JPanel auxPanel = new JPanel();

        auxPanel.setForeground(lastFoundColor);

        auxPanel.setBounds(0, (pixelWH / 2) - 20, pixelWH, pixelWH / 2);

        return auxPanel;

    }

    private JButton mainButton() {
        JButton auxBoton = new JButton();

        auxBoton.setBounds((int) ((pixelWH - (pixelWH * 0.8)) / 2), 10, (int) (pixelWH * 0.8), 30);

        auxBoton.addActionListener(e -> star()); // lo mismo que el "this" pero mejor supongo, no usa actionlistener
        auxBoton.setText("get Color");
        auxBoton.setFocusable(false); // le saca el cuadradito que tiene por estar en focus

        auxBoton.setHorizontalTextPosition(JButton.CENTER);
        auxBoton.setVerticalTextPosition(JButton.BOTTOM);
        auxBoton.setFont(new Font("Comic Sans", Font.BOLD, 25));
        auxBoton.setIconTextGap(-15);
        auxBoton.setBackground(Color.lightGray);
        auxBoton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return auxBoton;

    }

    public JButton colorCodeButtons(int spawnPoint, String relleno) {

        JButton copyButton = new JButton();
        copyButton.setText(relleno);
        copyButton.setBounds(spawnPoint, 180, 100, 50);
        copyButton.setFocusable(false);

        if (relleno.equals("Copy HEX")) {
            copyButton.addActionListener(e -> {
                lastFoundColorHEX = calculateColorCode(false, lastFoundColor);
                copyToClipboard(lastFoundColorHEX);
                JOptionPane.showMessageDialog(this, "Texto copiado al portapapeles: " + lastFoundColorHEX);
            });
        } else {
            copyButton.addActionListener(e -> {
                lastFoundColorRGB = calculateColorCode(true, lastFoundColor);
                copyToClipboard(lastFoundColorRGB);
                JOptionPane.showMessageDialog(this, "Texto copiado al portapapeles: " + lastFoundColorRGB);
            });
        }
        return copyButton;
    }

    public void star() {
countDown(5);

    }

    private static void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }

    public String calculateColorCode(boolean is_rgb, Color color_to_decode) {

        String color_code = "There was not color selected!";

        if (color_to_decode == null) {
            return color_code;
        } else {
            int red = color_to_decode.getRed();

            int green = color_to_decode.getGreen();

            int blue = color_to_decode.getBlue();

            if (is_rgb) {
                color_code = "" + red + "," + green + "," + blue;
            } else {
                color_code = rgbToHex(red, green, blue);

            }
            return color_code;

        }

    }

    public void countDown(int espera) {
    AtomicInteger countdown = new AtomicInteger(espera);

    Timer timer = new Timer(1000, e -> {
        int currentCountdown = countdown.getAndDecrement();
        startButton.setText("Hover over color in: " + currentCountdown + "s");

        if (currentCountdown == 0) {
            ((Timer) e.getSource()).stop();
            startButton.setText("get Color"); // Restaurar el texto original del botón

            DisplayManager display = new DisplayManager();

        
        Point cursorLocation = display.getCursorLocation();
        Color locationColor = display.getColorOf(cursorLocation);

        lastFoundColor = locationColor;

        Panel.setBackground(lastFoundColor);
        this.add(Panel);
        this.repaint();

        }

    });


    timer.start();

}

    public void actualizzr() {

        this.repaint();

    }

    public static String rgbToHex(int red, int green, int blue) {
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));
        String hexRed = Integer.toHexString(red);
        String hexGreen = Integer.toHexString(green);
        String hexBlue = Integer.toHexString(blue);
        hexRed = (hexRed.length() < 2) ? "0" + hexRed : hexRed;
        hexGreen = (hexGreen.length() < 2) ? "0" + hexGreen : hexGreen;
        hexBlue = (hexBlue.length() < 2) ? "0" + hexBlue : hexBlue;
        String hexColor = "#" + hexRed + hexGreen + hexBlue;
        return hexColor;
    }
}

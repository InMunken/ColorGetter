package getColor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
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
    JPanel panel;
    int pixelWH = 300;
    Color lastFoundColor = null;
    String lastFoundColorRGB = "";
    String lastFoundColorHEX = "";
    ImageIcon imagebotton = new ImageIcon("icon.png");
    String fuente = "Comic Sans";

    Display() {
        launchModal();
    }

    public void launchModal() {
        this.setIconImage(imagebotton.getImage());
        this.setTitle("Get Color");
        this.setBounds(100,100,300,225);
        this.setResizable(false);

        panel = colorPanel();
        panel.setBackground(new Color(0x000000));

        startButton = mainButton();
        rgbButton = colorCodeButtons(30, "Copy RGB");
        hexButton = colorCodeButtons(165, "Copy HEX");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setLayout(null);

        
        this.add(startButton);
        this.add(rgbButton);
        this.add(hexButton);
        this.add(panel);
    }

    private JPanel colorPanel() {
        JPanel auxPanel = new JPanel();
        auxPanel.setForeground(lastFoundColor);
        auxPanel.setBounds(0, 70, pixelWH, pixelWH / 2);
        return auxPanel;
    }

    private JButton mainButton() {
        JButton auxBoton = new JButton();

        auxBoton.setBounds(30, 0, 240, 60);  //ancho, alto; ancho, alto

        auxBoton.addActionListener(e -> start(4)); // lo mismo que el "this" pero mejor supongo, no usa actionlistener
        auxBoton.setText("get Color");
        auxBoton.setFocusable(false); // le saca el cuadradito que tiene por estar en focus

        auxBoton.setHorizontalTextPosition(JButton.CENTER);
        auxBoton.setVerticalTextPosition(JButton.BOTTOM);
        auxBoton.setFont(new Font(fuente, Font.BOLD, 25));
        auxBoton.setIconTextGap(-15);
        auxBoton.setBackground(Color.lightGray);
        auxBoton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return auxBoton;
    }

    public JButton colorCodeButtons(int spawnPoint, String relleno) {

        JButton copyButton = new JButton();
        copyButton.setText(relleno);
        copyButton.setBounds(spawnPoint, 110, 100, 50);
        copyButton.setFocusable(false);

        copyButton.setFont(new Font(fuente, Font.BOLD, 15));
        copyButton.setBackground(Color.lightGray);
        copyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

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

    public void start(int espera) {
        AtomicInteger countdown = new AtomicInteger(espera);

        Timer timer = new Timer(1000, e -> {
            int currentCountdown = countdown.getAndDecrement();
            startButton.setText("Hover over the color in: " + currentCountdown + "s");
            startButton.setFont(new Font(fuente, Font.BOLD, 15));
            

            if (currentCountdown == 0) {
                ((Timer) e.getSource()).stop();
                
                startButton.setText("get Color"); // Restaurar el texto original del botón
                startButton.setFont(new Font(fuente, Font.BOLD, 25));
                DisplayManager display = new DisplayManager();
                Point cursorLocation = display.getCursorLocation();
                Color locationColor = display.getColorOf(cursorLocation);
                lastFoundColor = locationColor;
                panel.setBackground(lastFoundColor);
                this.repaint();

            }

        });

        timer.start();

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

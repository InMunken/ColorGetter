package getColor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame {

    JButton startButton;
    JPanel Panel;
    int pixelWH = 300;
    Color lastFoundColor = null;

    Display() {
        start();
    }

    public void start() {
        this.setTitle("Get Color");


        Panel = colorPanel(); // sólo muestran texto o imgs, llenar con el nombre del código de color
        Panel.setBackground(new Color(0x00FF00));
        
        startButton =  mainButton();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(pixelWH,pixelWH);
        this.setVisible(true);
        this.setLayout(null);
        
        
        this.add(startButton);

        
    }

    private JPanel colorPanel() {

        JPanel auxPanel = new JPanel();
        
        auxPanel.setForeground(lastFoundColor);
        
        auxPanel.setBounds(0, (pixelWH/2)-20, pixelWH, pixelWH/2);


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

    public void star() {
        DisplayManager display = new DisplayManager();

        display.setUpTime(5);
        Point cursorLocation = display.getCursorLocation();
        Color locationColor = display.getColorOf(cursorLocation);

        lastFoundColor = locationColor;

        Panel.setBackground(lastFoundColor);
        this.add(Panel);
        this.repaint();

    }

}

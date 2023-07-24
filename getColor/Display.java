package getColor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display extends JFrame{

    JButton boton;
    JLabel label;
    final int pixelWH = 500;
    Color lastFoundColor = null;

    Display() {
    }

    public void start() {

        this.setTitle("Get Color");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(pixelWH, pixelWH);
        this.setVisible(true);

        label =  colorLabel();

        label.setForeground(new Color(0x00FF00));
                                                             
        boton = mainButton();
        
        JLabel auxLabel = new JLabel();
        
        auxLabel.setText("hello hello");
        auxLabel.setForeground(Color.green);
        

        ImageIcon image = new ImageIcon("150s_3.png"); // Creo un objeto ImageIcon, que busca en el direcorio el ico
        this.setIconImage(image.getImage()); //

//        this.getContentPane().setBackground(Color.green); // change back
        
       this.add(boton);
        this.add(label);
        this.add(auxLabel);
        // el append child de la naturaleza
    }



    private JLabel colorLabel() {

        JLabel auxLabel = new JLabel();
        
        auxLabel.setText("hello hello");
        auxLabel.setForeground(lastFoundColor);

        return auxLabel;
    }

    private JButton mainButton(){
        JButton auxBoton = new JButton();

        
        auxBoton.setBounds((int) ((pixelWH-(pixelWH * 0.8))/2) , 10 , (int) (pixelWH * 0.8) ,30 ); // tamaño y lacacion del boton 

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

    public void star(){
        DisplayManager display = new DisplayManager();

        display.setUpTime(5);
        Point cursorLocation = display.getCursorLocation();
        Color locationColor = display.getColorOf(cursorLocation);

        lastFoundColor =  locationColor;
    
    }

}

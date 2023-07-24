package getColor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Display extends JFrame implements ActionListener {

    JButton boton;
    JButton botondos;

    Display() {

    }

    public void start() {

        ImageIcon imagebotton = new ImageIcon("150s_3.png"); // Creo un objeto ImageIcon, que busca en el direcorio elico
                                                             
        this.setIconImage(imagebotton.getImage());
        imagebotton = resizeImageIcon(imagebotton, 150, 100);

        boton = new JButton();
        boton.setBounds(200, 100, 200, 100);
        boton.addActionListener(this); // ¿¿¿¿¿¿
        boton.setText("im a botton");
        boton.setFocusable(false); // le saca el cuadradito que tiene por estar en focus
        boton.setIcon(imagebotton);
        boton.setHorizontalTextPosition(JButton.CENTER);
        boton.setVerticalTextPosition(JButton.BOTTOM);
        boton.setFont(new Font("Comic Sans", Font.BOLD, 25));
        boton.setIconTextGap(-15);
        boton.setBackground(Color.lightGray);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        botondos = new JButton();
        botondos.setBounds(200, 250, 100, 50);
        botondos.addActionListener(e -> System.out.println("hola mundo")); // lo mismo que el "this" pero mejor supongo, no usa actionlistener

        this.setTitle("Shiny a la fuerza"); // título de la vetana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // accion del boton cerrar
        this.setLayout(null); // ¿¿¿
        this.setResizable(false); // evita que se pueda cambiar el tamaño de la vetana
        this.setSize(420, 420); // tamaño en pixeles de la ventana
        this.setVisible(true); // que sea visible
        this.add(boton);
        // el append child de la naturaleza
        this.add(botondos);

        ImageIcon image = new ImageIcon("150s_3.png"); // Creo un objeto ImageIcon, que busca en el direcorio el ico
        this.setIconImage(image.getImage()); //

        this.getContentPane().setBackground(Color.green); // change back
    }

    private ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) {

            System.out.println("El bóton está fuincionando!");
        }
    }

}

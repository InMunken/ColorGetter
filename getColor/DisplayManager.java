package getColor;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class DisplayManager {

    DisplayManager(){
        //hide the implicit one
    }

    public void setUpTime(int segundosAEsperar){
        try{
            Robot robot = new Robot();
            System.out.println("Al finalizar la cuenta ten el cursor sobre el color que quieres analizar");
            for (int j = 0; j < segundosAEsperar; j++) {
                robot.delay(1000);
                System.out.println("quedan:" + (segundosAEsperar - j) + "s. ");
            }
        }catch(AWTException e){
            e.printStackTrace();
        }
    }

    public Point getCursorLocation(){
        return MouseInfo.getPointerInfo().getLocation();
    }

    public Color getColorOf(Point location_Point){
        try{
            Robot robot = new Robot();
            return robot.getPixelColor(location_Point.x, location_Point.y);

        }catch (AWTException e) {
            e.printStackTrace();
            return null;
        }  

    }
    
}

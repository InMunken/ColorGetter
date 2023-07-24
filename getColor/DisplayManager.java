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

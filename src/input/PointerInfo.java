/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.Point;

/**
 *
 * @author nickz
 */
public class PointerInfo {

    public int deviceType, pointerID;
    public boolean inverted;
    public int pressure;
    public Point point;

    public String getDevIdInvString() {
        return "dev,id,inv = " + deviceType + "," + pointerID + "," + inverted;
    }

    public String getXYPressureString() {
        return "x,y,pressure = " + point.x + "," + point.y + "," + pressure;
    }
}

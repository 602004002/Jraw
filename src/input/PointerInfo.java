/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import common.User;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author nickz
 */
public class PointerInfo implements Serializable {
    
    private static final long serialVersionUID = 10L;

    private boolean inverted;
    private int pressure;
    private int maxPressure = 1023;
    private Point currentPoint;
    private Point prevPoint;
    private Point overlayPoint;

    private User userTag;

    public String getXYPressureString() {
        return "x,y,pressure = " + currentPoint.x + "," + currentPoint.y + "," + pressure;
    }
    
    public String getXYPressureStringRaw() {
        return currentPoint.x + "," + currentPoint.y + "," + pressure;
    }

    @Override
    public String toString() {
        return this.currentPoint.toString();
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(int maxPressure) {
        this.maxPressure = maxPressure;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }

    public Point getOverlayPoint() {
        return overlayPoint;
    }

    public void setOverlayPoint(Point overlayPoint) {
        this.overlayPoint = overlayPoint;
    }

    public User getUserTag() {
        return userTag;
    }

    public void setUserTag(User userTag) {
        this.userTag = userTag;
    }
}

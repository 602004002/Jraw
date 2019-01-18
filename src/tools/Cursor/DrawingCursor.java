/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.Cursor;

import input.*;
import java.awt.Cursor;
import javax.swing.JComponent;
import tools.DrawingTool;

/**
 *
 * @author nickz
 */
public class DrawingCursor extends Cursor {

    //private icon
    //seperate icons for non-drawing tools
    private DrawingTool currentTool;
    private JComponent currentLayer;
    private MousePositionListener mpl; //Listens for mouse input
    private TabletPenPositionListener tppl; //Listens for pen input
    private int xpos, ypos;
    private int radius;

    public DrawingCursor(DrawingTool currentTool,
            JComponent currentLayer,
            MousePositionListener mpl,
            TabletPenPositionListener tppl) {
        super(Cursor.CUSTOM_CURSOR);
        this.currentTool = currentTool;
        this.currentLayer = currentLayer;
        this.mpl = mpl;
        this.tppl = tppl;
        this.xpos = 0;
        this.ypos = 0;
        this.radius = 10;
    }
    
    public void draw () {
        if (!((JComponent)this.currentLayer).isVisible()) {
            return;
        }
        System.out.println("Drawing... @ Cursor on " + this.currentLayer.getName());
        //draw on pixels on canvas for each pixel in the radius that overlaps
        
    }
    
    public void setTool(DrawingTool dt) {
        this.currentTool = dt;
    }

    public void setCurrentLayer(JComponent cl) {
        this.currentLayer = cl;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        
    }
    
    
}

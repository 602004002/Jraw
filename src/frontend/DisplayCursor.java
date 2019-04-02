/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Cursor;
import layer.RasterLayer;
import tools.DrawingTool;

/**
 *
 * @author nickz
 */
public class DisplayCursor extends Cursor {

    //private icon
    //seperate icons for non-drawing tools
    private DrawingTool selectedTool;
    private RasterLayer selectedLayer;
    private boolean drawing;
    private int xpos, ypos;
    private int radius;

    public DisplayCursor() {
        super(Cursor.CUSTOM_CURSOR);
        this.xpos = 0;
        this.ypos = 0;
        this.radius = 10;
        this.drawing = false;
    }
    
    /**
     * Draws on layer by replacing pixels that the cursor overlaps with
     */
    public void draw () {
        if (!this.selectedLayer.isVisible() && !this.drawing) {
            
            return;
        } else if (!this.selectedLayer.isVisible() && this.drawing) {
            return;
        }
        System.out.println("Drawing... @ Cursor on " + this.selectedLayer.getName());
        //draw on pixels on canvas for each pixel in the radius that overlaps
        //calculate resultant color from drawing on existing color
        
        
    }
    
    public void setTool(DrawingTool dt) {
        this.selectedTool = dt;
    }

    public void setCurrentLayer(RasterLayer rl) {
        this.selectedLayer = rl;
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

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }
}

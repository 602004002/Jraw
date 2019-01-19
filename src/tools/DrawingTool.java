/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import common.ToolIconType;
import javax.swing.ImageIcon;

/**
 *
 * @author nickz
 */
public abstract class DrawingTool {
    //like a preset for the cursor to draw 
    protected String name;
    protected ImageIcon i;
    protected ToolIconType ti;
    protected ToolSettings ts; //aggregated
    protected DrawingTool (String name, ImageIcon i, ToolIconType ti) {
        this.ti = ti;
        this.i = i;
        this.name = name;
    }

    protected abstract void draw();//draw with info from listeners

    public ImageIcon getIcon() {
        return i;
    }
    
    
    
    @Override
    public String toString () {
        return this.name;
    }
}

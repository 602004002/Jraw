/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Color;

/**
 *
 * @author nickz
 */
public class Pixel {

    private int xpos, ypos;
    private Color c;

    /**
     * Default constructor.
     * @param xpos X position.
     * @param ypos Y position.
    */
    public Pixel(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.c = new Color(255,255,255,0); //white w/ alpha 0
    }

    public Pixel(Color c, int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.c = c;
    }
    
    //Accessors and mutators
    public void setXY (int newxpos, int newypos) {
        this.xpos = newxpos;
        this.ypos = newypos;
    }

    public int getXpos() {
        return this.xpos;
    }

    public int getYpos() {
        return this.ypos;

    }
}

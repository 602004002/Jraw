/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import java.awt.Color;

/**
 *
 * @author nickz
 */
public class LayerSettings {

    //delegate object for layer settings. aggregated.
    //color of the layer
    private Color layercolor; //normally transparent
    //opacity from 0 to 100
    private int opacity;
    //position of layer relative to center
    private int xpos, ypos;

    public LayerSettings() {
        this.layercolor = new Color(255, 255, 255, 0);
        this.opacity = 100;
        this.xpos = 0;
        this.ypos = 0;
    }

    public LayerSettings(Color layercolor) {
        this.layercolor = layercolor;
        this.opacity = 100;
        this.xpos = 0;
        this.ypos = 0;
    }

    //implement accessors and mutators
    public Color getLayercolor() {
        return layercolor;
    }

    public void setLayercolor(Color layercolor) {
        this.layercolor = layercolor;
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
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
}

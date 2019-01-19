/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.Session;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author nickz
 */
public class RasterLayerOld extends JLabel // implements DrawingLayer 
{

    private LayerSettings layerSettings;
    private BufferedImage data;
    private Session s;
    private String name;

    public RasterLayerOld(String name, Session s, LayerSettings layerSettings) {
        this.data = new BufferedImage(s.getCanvasWidth(), s.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);
        this.setIcon(new ImageIcon(this.data));
        this.name = name;
        this.s = s;
        this.layerSettings = layerSettings;
        //this.data.setData(Raster.createRaster(sm, db, location));
    }

    public void bufferlog() {
        throw new UnsupportedOperationException("Not implemented yet.");
        //s.bf.push();//push 
    }

    public LayerSettings getLayerSettings() {
        return this.layerSettings;
    }

    public void setLayerSettings(LayerSettings layerSettings) {
        this.layerSettings = layerSettings;
    }

    public BufferedImage getImg() {
        return this.data;
    }    
    
}

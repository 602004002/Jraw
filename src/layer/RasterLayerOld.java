/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;
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
    private SessionModel s;
    private String name;

    public RasterLayerOld(String name, SessionModel s, LayerSettings layerSettings) {
        this.data = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_ARGB);
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

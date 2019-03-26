/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author nickz
 */
public class RasterLayer extends JComponent {

    private LayerSettings layerSettings;
    private BufferedImage data;
    private SessionModel sessionModel;
    private Dimension size;

    public RasterLayer(String name,
            SessionModel sessionModel,
            LayerSettings layerSettings) {
        this.size = sessionModel.getSize();
        this.data = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_ARGB);
        this.sessionModel = sessionModel;
        this.layerSettings = layerSettings;
        this.setName(name);
        fullColor();
    }

    private void fullColor() {
        Graphics g = this.data.createGraphics();
        g.setColor(this.layerSettings.getLayercolor());
        g.fillRect(0, 0, this.size.width, this.size.height);
        g.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.data, 0, 0, this.size.width, this.size.height, this);
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

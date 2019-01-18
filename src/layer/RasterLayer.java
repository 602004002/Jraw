/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.Session;
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
    private Session s;
    private String name;

    public RasterLayer(String name, Session s, LayerSettings layerSettings) {
        this.data = new BufferedImage(s.getCanvasWidth(), s.getCanvasHeight(),
                BufferedImage.TYPE_INT_ARGB);
        this.name = name;
        this.s = s;
        this.layerSettings = layerSettings;
        fullColor();
    }

    /**
     * Very dangerous method to use!
     */
    private void fullColor() {
        for (int y = 0; y < this.s.getCanvasHeight(); y++) {
            for (int x = 0; x < this.s.getCanvasWidth(); x++) {
                this.data.setRGB(x, y,
                        this.layerSettings.getLayercolor().getRGB());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setPaintMode();
        int imgW = this.s.getCanvasWidth();
        int imgH = this.s.getCanvasHeight();
        g.drawImage(this.data, 0, 0, imgW, imgH, this);
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

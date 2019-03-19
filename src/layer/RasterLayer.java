/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;
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
    private SessionModel s;

    public RasterLayer(String name, SessionModel s, LayerSettings layerSettings) {
        this.data = new BufferedImage(s.getWidth(), s.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        this.s = s;
        this.setName(name);
        this.layerSettings = layerSettings;
        fullColor();
    }

    /**
     * Very dangerous method to use!
     */
    private void fullColor() {
//        for (int y = 0; y < this.s.getCanvasHeight(); y++) {
//            for (int x = 0; x < this.s.getCanvasWidth(); x++) {
//                this.data.setRGB(x, y,
//                        this.layerSettings.getLayercolor().getRGB());
//            }
//        }
        Graphics g = this.data.createGraphics();
        g.setColor(this.layerSettings.getLayercolor());
        g.fillRect(0, 0, this.s.getWidth(), this.s.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imgW = this.s.getWidth();
        int imgH = this.s.getHeight();
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

    public void setImg(BufferedImage data) {
        this.data = data;
    }

}

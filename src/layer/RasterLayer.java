/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;
import common.User;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.event.UndoableEditEvent;

/**
 *
 * @author nickz
 */
public class RasterLayer extends DrawingLayer implements Serializable {
    
    private static final long serialVersionUID = 10L;
    
    private transient BufferedImage data;
    private transient BufferedImage temporary;
    private transient Graphics2D tempG2d;
    
    private RasterLayer(Builder b) {
        super(b);
        this.data = new BufferedImage(this.size.width, this.size.height,
                BufferedImage.TYPE_INT_ARGB);
        if (b.fillColor != null) {
            fillColor(b.fillColor);
        }
    }
    
    public static class Builder extends DrawingLayer.Builder {
        
        private Color fillColor;
        
        public Builder fillColor(final Color fillColor) {
            this.fillColor = fillColor;
            return this;
        }
        
        @Override
        public RasterLayer build() {
            if (this.size == null) {
                throw new IllegalStateException("Size cannot be null");
            }
            return new RasterLayer(this);
        }
        
    }
    
    private void fillColor(Color c) {
        Graphics g = this.data.createGraphics();
        g.setColor(c);
        g.fillRect(0, 0, this.size.width, this.size.height);
        g.dispose();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.data, 0, 0, this.size.width, this.size.height, this);
        if (this.temporary != null) {
            g.drawImage(this.temporary, 0, 0, this.size.width, this.size.height, this);
        }
    }
    
    public BufferedImage getRasterImage() {
        return this.data;
    }
    
    public Graphics2D getTemporary() {
        if (temporary == null) {
            temporary = new BufferedImage(this.size.width, this.size.height, BufferedImage.TYPE_INT_ARGB);
            tempG2d = temporary.createGraphics();
        }
        return tempG2d;
    }
    
    public void commitTemporary(SessionModel sm) {
        //Graphics g = this.data.getGraphics();
        //g.drawImage(this.temporary, 0, 0, this.size.width, this.size.height, this);
        if (undoManager != null) {
            undoManager.undoableEditHappened(
                    new UndoableEditEvent(this,
                            new RasterEdit(sm.layerHierarchy.indexOf(this), temporary, User.getLocalUser())));
        }
        this.temporary = null;
        this.tempG2d.dispose();
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(this.getRasterImage(), "png", out);
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.data = ImageIO.read(in);
    }
}

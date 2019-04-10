/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import input.DrawMethod;
import input.PointerInfo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public abstract class DrawingTool implements DrawMethod {

    //like a preset for the cursor to draw 
    protected String name;
    protected int diameterSize, crosshairLength, dithering, hardness, density;
    protected boolean pressureAffectsSize, pressureAffectsDensity;
    protected Color color;
    protected ImageIcon toolbarIcon;
    protected BufferedImage canvasIcon;

    protected DrawingTool(AbstractBuilder b) {
        this.name = b.name;
        this.diameterSize = b.diameterSize;
        this.dithering = b.dithering;
        this.hardness = b.hardness;
        this.density = b.density;
        this.pressureAffectsSize = b.pressureAffectsSize;
        this.pressureAffectsDensity = b.pressureAffectsDensity;
        this.color = b.color;
        this.toolbarIcon = b.toolbarIcon;
        if (b.canvasIcon == null) {
            initCanvasIcon();
        } else {
            this.canvasIcon = b.canvasIcon;
        }
    }

    protected static abstract class AbstractBuilder {

        private String name;
        private int diameterSize, dithering, hardness, density;
        private boolean pressureAffectsSize, pressureAffectsDensity;
        private Color color;
        private ImageIcon toolbarIcon;
        private BufferedImage canvasIcon;

        public AbstractBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AbstractBuilder diameterSize(int diameter) {
            this.diameterSize = diameter;
            return this;
        }

        public AbstractBuilder dithering(int dithering) {
            this.dithering = dithering;
            return this;
        }

        public AbstractBuilder hardness(int hardness) {
            this.hardness = hardness;
            return this;
        }

        public AbstractBuilder density(int density) {
            this.density = density;
            return this;
        }

        public AbstractBuilder pressureAffectsSize(boolean pressureAffectsSize) {
            this.pressureAffectsSize = pressureAffectsSize;
            return this;
        }

        public AbstractBuilder pressureAffectsDensity(boolean pressureAffectsDensity) {
            this.pressureAffectsDensity = pressureAffectsDensity;
            return this;
        }

        public AbstractBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public AbstractBuilder toolBarIcon(ImageIcon toolbarIcon) {
            this.toolbarIcon = toolbarIcon;
            return this;
        }

        public AbstractBuilder canvasIcon(BufferedImage canvasIcon) {
            this.canvasIcon = canvasIcon;
            return this;
        }

        public abstract DrawingTool build();
    }

    /**
     * Raster Draw Method. Writes directly to the image
     *
     * @param pointerInfo Information about the pointer's location and pressure.
     * @param layer The layer to draw onto.
     */
    public abstract void drawRaster(PointerInfo pointerInfo, RasterLayer layer);//draw with info from listeners

    /**
     * Vector Draw Method. Creates many points and updates path
     *
     * @param pointerInfo Information about the pointer's location and pressure.
     * @param layer The layer to draw onto.
     */
    public abstract void drawVector(PointerInfo pointerInfo, VectorLayer layer);//draw with info from listeners

    public ImageIcon toolbarIcon() {
        return toolbarIcon;
    }

    public BufferedImage canvasIcon() {
        return this.canvasIcon;
    }

    public void resizeDiameter(int diameter) {
        this.diameterSize = diameter;
        this.initCanvasIcon();
    }

    public void initCanvasIcon() {
        int size = diameterSize + crosshairLength;
        this.canvasIcon = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
    }

    @Override
    public String toString() {
        return "Class: " + this.getClass().getName() + ", Name: " + this.name;
    }
}

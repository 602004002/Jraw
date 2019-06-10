/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import common.SessionModel;
import layer.DrawingLayer;
import layer.RasterLayer;
import layer.VectorLayer;
import input.PointerInfo;

/**
 *
 * @author nickz
 */
public abstract class DrawingTool {

    //like a preset for the cursor to draw 
    protected String name;
    protected int diameterSize, crosshairLength, dithering, hardness, density;
    protected boolean pressureAffectsSize, pressureAffectsDensity;
    protected ImageIcon toolbarIcon;
    protected BufferedImage canvasIcon;

    protected Graphics2D temporary;

    protected DrawingTool(AbstractBuilder b) {
        this.name = b.name;
        this.diameterSize = b.diameterSize;
        this.dithering = b.dithering;
        this.hardness = b.hardness;
        this.density = b.density;
        this.pressureAffectsSize = b.pressureAffectsSize;
        this.pressureAffectsDensity = b.pressureAffectsDensity;
        this.toolbarIcon = b.toolbarIcon;
        if (b.canvasIcon != null) {
            this.canvasIcon = b.canvasIcon;
        } else {
            generateCanvasIcon();
        }
    }

    public static abstract class AbstractBuilder {

        protected String name;
        protected int diameterSize, dithering, hardness, density;
        protected boolean pressureAffectsSize, pressureAffectsDensity;
        protected Color color;
        protected ImageIcon toolbarIcon;
        protected BufferedImage canvasIcon;

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

    public void draw(PointerInfo pointerInfo, SessionModel sm) {
        DrawingLayer layer = sm.getDrawLayer();
        if (layer.isVisible() && pointerInfo.getPressure() > 0) {
            if (layer instanceof RasterLayer) {
                if (this.temporary == null) {
                    this.temporary = ((RasterLayer) layer).getTemporary();
                }
                drawRaster(pointerInfo, this.temporary);
            } else if (layer instanceof VectorLayer) {
                drawVector(pointerInfo, (VectorLayer) layer);
            }
        } else {
            if (this.temporary != null && layer instanceof RasterLayer) {
                this.temporary = null;
                ((RasterLayer) layer).commitTemporary(sm);
                sm.setSaved(false);
            }

        }
    }

    /**
     * Raster Draw Method. Writes directly to the graphics context every timer
     * cycle.
     *
     * @param pointerInfo Information about the pointer's location and pressure.
     * @param g2d Graphics context to draw with
     */
    public abstract void drawRaster(PointerInfo pointerInfo, Graphics2D g2d);//draw with info from listeners

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
        this.generateCanvasIcon();
    }

    public int diameter() {
        return this.diameterSize;
    }

    public final void generateCanvasIcon() {
        int size = diameterSize + crosshairLength;
        this.canvasIcon = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
        //Graphics2D g2d = this.canvasIcon.createGraphics();

        //g2d.dispose();
    }

    @Override
    public String toString() {
        return "Class: " + this.getClass().getName() + ", Name: " + this.name;
    }
}

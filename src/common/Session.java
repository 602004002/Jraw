/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import layer.LayerSettings;
import layer.RasterLayer;
import layer.buffer.ChangeBuffer;
import frontend.toolbar.ColorPalette;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author nickz
 */
public class Session {
    //this class is like a live file

    //keep a base image of commited changes
    //private drawinglayer
    private String name;
    private DrawingType dt;
    private boolean saved; //based on buffer changes
    public ChangeBuffer bf;
    private int resolution, width, height; //in pixels
    public final ArrayList<JComponent> hierarchy;

    public ColorPalette cp;

    public Session(String name,
            DrawingType dt,
            int resolution,
            int xsize,
            int ysize) {
        System.out.println("Session()");
        this.name = name;
        this.dt = dt;
        this.saved = false;
        this.resolution = resolution;
        this.width = xsize;
        this.height = ysize;
        this.saved = false;
        this.hierarchy = new ArrayList<>();
        this.cp = new ColorPalette();
    }

    public void initWorkspace(Color backgroundColor) {
        System.out.println("initWorkspace(Color) buffer 30");
        this.bf = new ChangeBuffer(30);
        LayerSettings ls1 = new LayerSettings(backgroundColor);
        this.hierarchy.add(new RasterLayer("Paper", this, ls1));
        this.hierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
    }

    public void initWorkspace() {
        System.out.println("initWorkspace() buffer 30");
        this.bf = new ChangeBuffer(30);
        this.hierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrawingType getDrawingType() {
        return dt;
    }

    public void setDrawingType(DrawingType dt) {
        this.dt = dt;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getCanvasWidth() {
        return width;
    }

    public void setXsize(int xsize) {
        this.width = xsize;
    }

    public int getCanvasHeight() {
        return height;
    }

    public void setYsize(int ysize) {
        this.height = ysize;
    }

    public ColorPalette getColorPalette() {
        return cp;
    }

    public void setColorPalette(ColorPalette cp) {
        this.cp = cp;
    }

    public void undo() {

    }

    public void redo() {

    }

}

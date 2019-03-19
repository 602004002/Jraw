/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import frontend.layerlist.LayerList;
import frontend.layerlist.LayerListCell;
import layer.LayerSettings;
import layer.RasterLayer;
import layer.buffer.ChangeBuffer;
import frontend.toolbar.ColorPalette;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

/**
 *
 * @author nickz
 */
public class SessionModel {
    //this class is like a live file

    //keep a base image of commited changes
    //private drawinglayer
    private String name;
    private DrawingType dt;
    private boolean saved; //based on buffer changes
    public ChangeBuffer bf;
    private int resolution, width, height; //in pixels
    public final ArrayList<JComponent> hierarchy;
    private int[] selectedLayerIndexes;
    private LayerListCell[] cells;

    public ColorPalette cp;
    public LayerList layerList;

    public SessionModel(String name,
            DrawingType dt,
            int resolution,
            int xsize,
            int ysize,
            LayerList ll) {
        this.name = name;
        this.dt = dt;
        this.saved = false;
        this.resolution = resolution;
        this.width = xsize;
        this.height = ysize;
        this.saved = false;
        this.hierarchy = new ArrayList<>();
        this.cp = new ColorPalette();
        this.layerList = ll;
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

    public void updateCells() {
        this.cells = null;
        int size = this.hierarchy.size();
        this.cells = new LayerListCell[size];
        for (int i = size - 1; i >= 0; i--) {
            LayerListCell llc = new LayerListCell(this.hierarchy.get(i),
                    this.layerList);
            this.cells[i] = llc;
        }
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int ysize) {
        this.height = ysize;
    }

    public int[] getSelectedLayerIndexes() {
        return this.selectedLayerIndexes;
    }

    public void setSelectedLayerIndices(int[] indexes) {
        this.selectedLayerIndexes = indexes;
    }
    
    public int getLayerCount() {
        return this.hierarchy.size();
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
    
    @Override
    public String toString() {
        return "Session: " + this.name
                + "\n Width: " + this.width
                + "\n Height: " + this.height;
    }

}

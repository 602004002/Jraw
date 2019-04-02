/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import layer.LayerSettings;
import layer.RasterLayer;
import layer.buffer.ChangeBuffer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author nickz
 */
public class SessionModel {
    //this class is like a live file

    //keep a base image of commited changes
    //private drawinglayer
    private String name;
    private DrawingType drawingType;
    private boolean saved; //based on buffer changes
    public final ChangeBuffer changeBuffer;
    private Dimension size;
    private int resolution; //in pixels
    private int framerate;
    public final ArrayList<JComponent> hierarchy;
    private int[] selectedLayerIndexes;

    private SessionModel(Builder sb) {
        System.out.println("SessionModel(Builder)");
        this.name = sb.name;
        this.drawingType = sb.dt;
        this.saved = false;
        this.size = sb.size;
        this.resolution = sb.resolution;
        this.framerate = sb.framerate;
        this.changeBuffer = new ChangeBuffer(30);
        this.hierarchy = new ArrayList<>();
        if (sb.backgroundColor != null) {
            LayerSettings ls1 = new LayerSettings(sb.backgroundColor);
            this.hierarchy.add(new RasterLayer("Paper", this, ls1));
            this.hierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
        } else {
            this.hierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
        }
    }

    public static class Builder {

        private String name;
        private DrawingType dt;
        private Dimension size;
        private int resolution; //in pixels
        private int framerate;
        private Color backgroundColor;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder drawingType(final DrawingType dt) {
            this.dt = dt;
            return this;
        }

        public Builder size(final Dimension size) {
            this.size = size;
            return this;
        }

        public Builder resolution(final int resolution) {
            this.resolution = resolution;
            return this;
        }

        public Builder backgroundColor(final Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder framerate(final int framerate) {
            this.framerate = framerate;
            return this;
        }

        public SessionModel build() {
            return new SessionModel(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrawingType getDrawingType() {
        return drawingType;
    }

    public void setDrawingType(DrawingType dt) {
        this.drawingType = dt;
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

    public Dimension getSize() {
        return this.size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getFramerate() {
        return framerate;
    }

    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }

    public int[] getSelectedLayerIndexes() {
        return this.selectedLayerIndexes;
    }

    /**
     * NOT meant to be used elsewhere other than controllers
     *
     * @param indexes Indexes to be selected
     */
    public void setSelectedLayerIndices(int[] indexes) {
        this.selectedLayerIndexes = indexes;
    }

    public int getLayerCount() {
        return this.hierarchy.size();
    }

    public void undo() {

    }

    public void redo() {

    }

    @Override
    public String toString() {
        return "Session: " + this.name
                + "\n Width: " + this.size.width
                + "\n Height: " + this.size.height;
    }

}

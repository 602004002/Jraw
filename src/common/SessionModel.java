/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import layer.LayerSettings;
import layer.RasterLayer;
import changestack.UndoRedoStack;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import layer.DrawingLayer;

/**
 *
 * @author nickz
 */
public class SessionModel {
    //this class is like a live file

    public static class Builder {

        private String name;
        private DrawingType drawingType;
        private Dimension size;
        private int resolution; //in pixels
        private int framerate;
        private Color backgroundColor;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder drawingType(final DrawingType drawingType) {
            this.drawingType = drawingType;
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
            if (name == null || name.isEmpty()) {
                throw new IllegalStateException("Name cannot be null or empty");
            }
            if (drawingType == null) {
                throw new IllegalStateException("Need a drawing type");
            }
            if (size == null) {
                throw new IllegalStateException("Need a size");
            }
            if (resolution == 0) {
                throw new IllegalStateException("Need a resolution");
            }
            return new SessionModel(this);
        }
    }

    private SessionModel(Builder sb) {
        this.name = sb.name;
        this.drawingType = sb.drawingType;
        this.saved = false;
        this.size = sb.size;
        this.resolution = sb.resolution;
        this.framerate = sb.framerate;
        this.stack = UndoRedoStack.createEmptyStack();
        this.layerHierarchy = new ArrayList<>();
        initializeFirstValues(sb.backgroundColor);
    }

    //keep a base image of commited changes
    //private drawinglayer
    private String name;
    private DrawingType drawingType;
    private boolean saved; //based on buffer changes
    private int resolution; //in pixels
    private int framerate;

    public final Dimension size;
    public final ArrayList<DrawingLayer> layerHierarchy;
    public final UndoRedoStack stack;
    private int[] selectedLayerIndexes;

    private void initializeFirstValues(Color bgColor) {
        if (bgColor != null) {
            LayerSettings ls1 = new LayerSettings(bgColor);
            this.layerHierarchy.add(new RasterLayer("Paper", this, ls1));
            this.layerHierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
        } else {
            this.layerHierarchy.add(new RasterLayer("Layer 1", this, new LayerSettings()));
        }
        this.selectedLayerIndexes = new int[]{this.layerCount() - 1};
    }

    public String name() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public DrawingType getDrawingType() {
        return drawingType;
    }

    public void drawingType(DrawingType dt) {
        this.drawingType = dt;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int resolution() {
        return this.resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public Dimension size() {
        return this.size;
    }

    public void resize(int width, int height) {
        this.size.width = width;
        this.size.height = height;
    }

    public void resize(Dimension size) {
        this.size.width = size.width;
        this.size.height = size.height;
    }

    public int framerate() {
        return framerate;
    }

    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }

    /**
     * WARNING - May not be up to date. Recommended that you use
     * LayerList.getSelectedLayerIndexes() instead This is just meant as a
     * hand-off when the user switches tabs
     *
     * @return Returns an int array of all selected indexes.
     */
    public int[] getSelectedLayerIndexes() {
        return this.selectedLayerIndexes;
    }

    /**
     * WARNING - NOT meant to be used elsewhere other than controllers. This is
     * just meant as a hand-off when the user switches tabs
     *
     * @param indexes Indexes to be selected
     */
    public void setSelectedLayerIndices(int[] indexes) {
        this.selectedLayerIndexes = indexes;
    }

    public int layerCount() {
        return this.layerHierarchy.size();
    }

    @Override
    public String toString() {
        return "BEGIN_[SessionModel Name: " + this.name
                + "\n Drawing Type: " + this.drawingType
                + "\n isSaved: " + this.saved
                + "\n Size: " + this.size
                + "\n Resolution: " + this.resolution
                + "\n Framerate (if Applicable): " + this.framerate
                + "]_END";
    }

}

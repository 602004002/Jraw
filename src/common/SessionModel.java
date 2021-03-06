/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.UUID;

import layer.DrawingLayer;
import layer.RasterLayer;
import undoredo.UndoManager_Edit;

/**
 *
 * @author nickz
 */
public class SessionModel implements Serializable {

    private static final long serialVersionUID = 15L;

    //this class is like a live file
    public static class Builder {

        private User creator;

        private String name;
        private DrawingType drawingType;
        private Dimension size;
        private int resolution; //in pixels
        private int framerate;
        private Color backgroundColor;

        public Builder creator(final User creator) {
            this.creator = creator;
            return this;
        }

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

    /**
     * WARNING: layerHierarchy of sm is NOT cloned.
     *
     * @param sm Model to clone
     */
    protected SessionModel(SessionModel sm) {
        this.creator = sm.creator;
        this.uuid = UUID.randomUUID();
        this.name = sm.name;
        this.drawingType = sm.drawingType;
        this.saved = true;
        this.size = sm.size;
        this.resolution = sm.resolution;
        this.framerate = sm.framerate;
        this.undoMgr = sm.undoMgr;
        this.layerHierarchy = sm.layerHierarchy;
        this.selectedLayerIndexes = new int[]{this.layerCount() - 1};
    }

    protected SessionModel(Builder sb) {
        this.creator = sb.creator;
        this.uuid = UUID.randomUUID();
        this.name = sb.name;
        this.drawingType = sb.drawingType;
        this.saved = true;
        this.size = sb.size;
        this.resolution = sb.resolution;
        this.framerate = sb.framerate;
        this.undoMgr = new UndoManager_Edit(this);
        this.layerHierarchy = new ArrayList<>();
        initializeFirstValues(sb.backgroundColor);
        this.selectedLayerIndexes = new int[]{this.layerCount() - 1};
    }
    //keep a base image of commited changes
    //private drawinglayer
    public final User creator;
    private UUID uuid;
    private transient File lastPath;

    private transient String name;
    private DrawingType drawingType;
    private transient boolean saved; //based on buffer changes
    private int resolution; //in pixels
    private int framerate;
    private Dimension size;
    public final ArrayList<DrawingLayer> layerHierarchy;
    protected transient UndoManager_Edit undoMgr;
    private int[] selectedLayerIndexes;

    private void initializeFirstValues(Color bgColor) {
        if (bgColor != null) {
            RasterLayer colorLayer = (RasterLayer) new RasterLayer.Builder()
                    .fillColor(bgColor)
                    .name("Paper")
                    .undoManager(undoMgr)
                    .size(size)
                    .build();
            this.layerHierarchy.add(colorLayer);
            this.addRasterLayer();
        } else {
            this.addRasterLayer();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Accessors and Mutators">
    public String name() {
        return name;
    }

    public void rename(String name) {
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

    public int framerate() {
        return framerate;
    }

    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }

    public DrawingLayer getDrawLayer() {
        return this.layerHierarchy.get(this.selectedLayerIndexes[0]);
    }

    /**
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

    public final int layerCount() {
        return this.layerHierarchy.size();
    }

    public File getLastPath() {
        return this.lastPath;
    }

    public void setLastPath(File lastPath) {
        this.lastPath = lastPath;
    }

    public UUID uuid() {
        return this.uuid;
    }

    public final UndoManager_Edit getUndoManager() {
        return undoMgr;
    }

    //</editor-fold>
    public void addRasterLayer() {
        RasterLayer newLayer = (RasterLayer) new RasterLayer.Builder()
                .name("Layer " + layerCount())
                .undoManager(undoMgr)
                .size(size())
                .build();
        layerHierarchy.add(newLayer);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.undoMgr = new UndoManager_Edit(this);
        for (DrawingLayer dl : this.layerHierarchy) {
            dl.setUndoManager(this.undoMgr);
        }
    }

    @Override
    public String toString() {
        return "Class: " + getClass().getName() + "\nBEGIN_[Name: " + this.name
                + "\n" + this.layerHierarchy
                + "\n Drawing Type: " + this.drawingType
                + "\n isSaved: " + this.saved
                + "\n Size: " + this.size
                + "\n Resolution: " + this.resolution
                + "\n Framerate (if Applicable): " + this.framerate
                + "\n Selected Indexes: " + Arrays.toString(this.getSelectedLayerIndexes())
                + "]_END";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;
import java.awt.Dimension;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.undo.UndoManager;

/**
 *
 * @author nickz
 */
public abstract class DrawingLayer extends JComponent implements Serializable {
    
    private static final long serialVersionUID = 10L;

    protected Dimension size;
    protected int opacity;
    protected transient UndoManager undoManager;
    private boolean scrolling;

    protected DrawingLayer(Builder b) {
        this.size = b.size;
        this.undoManager = b.mgr;
        this.setName(b.name);
    }

    @Override
    public final void setName(String name) {
        super.setName(name);
    }

    public static abstract class Builder {

        protected SessionModel sm;
        protected String name;
        protected Dimension size;
        protected UndoManager mgr;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder size(final Dimension size) {
            this.size = size;
            return this;
        }
        
        public Builder undoManager(UndoManager mgr) {
            this.mgr = mgr;
            return this;
        }

        public abstract DrawingLayer build();
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public boolean isScrolling() {
        return scrolling;
    }

    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }
    
    public void setUndoManager(UndoManager um) {
        this.undoManager = um;
    }

    @Override
    public String toString() {
        return "Class: " + this.getClass().getName() + " Name: " + this.getName();
    }
}

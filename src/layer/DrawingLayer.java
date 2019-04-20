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

/**
 *
 * @author nickz
 */
public abstract class DrawingLayer extends JComponent implements Serializable {
    
    private static final long serialVersionUID = 10L;
    
    private transient SessionModel sm;

    protected Dimension size;
    protected int opacity;
    private boolean scrolling;

    protected DrawingLayer(AbstractBuilder b) {
        this.size = b.size;
        this.sm = b.sm;
        this.setName(b.name);
    }

    @Override
    public final void setName(String name) {
        super.setName(name);
    }

    public static abstract class AbstractBuilder {

        protected SessionModel sm;
        protected String name;
        protected Dimension size;

        public AbstractBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public AbstractBuilder size(final Dimension size) {
            this.size = size;
            return this;
        }
        
        public AbstractBuilder sessionModel(final SessionModel sm) {
            this.sm = sm;
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
    
    public SessionModel getSessionModel() {
        return this.sm;
    }

    @Override
    public String toString() {
        return "Class: " + this.getClass().getName() + " Name: " + this.getName();
    }
}

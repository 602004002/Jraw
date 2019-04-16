/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import java.awt.Dimension;
import java.io.Serializable;
import javax.swing.JComponent;

/**
 *
 * @author nickz
 */
public abstract class DrawingLayer extends JComponent implements Serializable {

    protected Dimension size;
    protected int opacity;

    protected DrawingLayer(AbstractBuilder b) {
        this.size = b.size;
        this.setName(b.name);
    }

    @Override
    public final void setName(String name) {
        super.setName(name);
    }

    public static abstract class AbstractBuilder {

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

        public abstract DrawingLayer build();
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    @Override
    public String toString() {
        return "Class: " + this.getClass().getName() + " Name: " + this.getName();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import input.PointerInfo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public class PencilTool extends DrawingTool {

    private PencilTool(Builder b) {
        super(b);
    }

    public static class Builder extends DrawingTool.AbstractBuilder {

        @Override
        public DrawingTool build() {
            return new PencilTool(this);
        }

    }

    /**
     * Not to be invoked by anywhere else other than input.PointerListener
     *
     * @param pointerInfo All current info returned by the pointer
     * @param layer Which layer to draw on
     */
    @Override
    public void drawRaster(PointerInfo pointerInfo, RasterLayer layer) {
        Graphics2D g2d = layer.getRasterImage().createGraphics();
        float stroke = this.diameterSize;
        float multiplier = ((float) pointerInfo.getPressure() / pointerInfo.getMaxPressure());
        if (this.pressureAffectsSize) {
            stroke *= multiplier;
        }
        if (this.pressureAffectsDensity) {
            int r = this.color.getRed();
            int g = this.color.getGreen();
            int b = this.color.getBlue();
            int a = (int) (multiplier * 255);
            Color c = new Color(r, g, b, a);
            g2d.setColor(c);
        } else {
            g2d.setColor(this.color);
        }
        g2d.setStroke(new BasicStroke(stroke));
        Point old = pointerInfo.getPrevPoint();
        Point cur = pointerInfo.getCurrentPoint();
        if (old != null && cur != null) {
            g2d.drawLine(old.x, old.y, cur.x, cur.y);
        }
        g2d.dispose();
        //layer.repaint();
    }

    @Override
    public void drawVector(PointerInfo pointerInfo, VectorLayer layer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

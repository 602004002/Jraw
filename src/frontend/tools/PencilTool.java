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
    
    private BasicStroke bs;

    /**
     * Not to be invoked by anywhere else other than input.PointerListener
     *
     * @param pointerInfo All current info returned by the pointer
     */
    @Override
    public void drawRaster(PointerInfo pointerInfo, Graphics2D g2d) {
        Color color = ColorToolbar.getGlobalColor();
        float stroke = this.diameterSize;
        float multiplier = ((float) pointerInfo.getPressure() / pointerInfo.getMaxPressure());
        if (this.pressureAffectsSize) {
            stroke *= multiplier;
        }
        if (this.pressureAffectsDensity) {
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            int a = (int) (multiplier * 255);
            Color c = new Color(r, g, b, a);
            g2d.setColor(c);
        } else {
            g2d.setColor(color);
        }
        if (bs == null || bs.getLineWidth() != stroke) {
            bs = new BasicStroke(stroke);
            
        }   
        g2d.setStroke(bs);
        Point old = pointerInfo.getPrevPoint();
        Point cur = pointerInfo.getCurrentPoint();
        if (old != null && cur != null) {
            g2d.drawLine(old.x, old.y, cur.x, cur.y);
        }
        //layer.repaint();
    }

    @Override
    public void drawVector(PointerInfo pointerInfo, VectorLayer layer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

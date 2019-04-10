/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import input.PointerInfo;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public class EffectTool extends DrawingTool {
    //hotload vector graphics

    private EffectTool(Builder b) {
        super(b);
    }

    @Override
    public void drawRaster(PointerInfo pointerInfo, RasterLayer layer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawVector(PointerInfo pointerInfo, VectorLayer layer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Builder extends DrawingTool.AbstractBuilder {

        @Override
        public EffectTool build() {
            return new EffectTool(this);
        }

    }
}

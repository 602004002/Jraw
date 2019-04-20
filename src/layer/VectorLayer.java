/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

/**
 *
 * @author nickz
 */
public class VectorLayer extends DrawingLayer {
    
    private static final long serialVersionUID = 10L;

    private VectorLayer(Builder b) {
        super(b);
    }
    
    public static class Builder extends DrawingLayer.AbstractBuilder {

        @Override
        public VectorLayer build() {
            return new VectorLayer(this);
        }

    }

}

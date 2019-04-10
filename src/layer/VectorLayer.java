/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.SessionModel;

/**
 *
 * @author nickz
 */
public class VectorLayer extends DrawingLayer {

    private String name;

    public VectorLayer(String name, SessionModel s, LayerSettings ls) {
        this.name = name;
    }

}

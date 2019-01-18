/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.Session;

/**
 *
 * @author nickz
 */
public class VectorLayer {
    
    private String name;

    public VectorLayer(String name, Session s, LayerSettings ls) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

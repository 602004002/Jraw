/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer.buffer;

import java.util.ArrayList;
import tools.vector.VectorPoint;

/**
 *
 * @author nickz
 */
public class VectorChange extends LayerChange {
    public VectorChange () {
        this.al = new ArrayList<VectorPoint>();
    }
}

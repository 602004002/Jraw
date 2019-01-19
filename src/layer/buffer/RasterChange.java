/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer.buffer;

import common.Pixel;
import java.util.ArrayList;

/**
 *
 * @author nickz
 */
public class RasterChange extends LayerChange {
    public RasterChange () {
        this.al = new ArrayList<Pixel>();
    }
}

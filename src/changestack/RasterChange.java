/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changestack;

import java.awt.image.Raster;

/**
 *
 * @author nickz
 */
public class RasterChange extends Change {
    private Raster r;
    
    public RasterChange (String name) {
        super(name);
    }
}

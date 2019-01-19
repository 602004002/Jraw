/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import common.ToolIconType;
import javax.swing.ImageIcon;

/**
 *
 * @author nickz
 */
public class VectorTool extends DrawingTool{

    public VectorTool(String name, ImageIcon i) {
        super(name, i, ToolIconType.Icon);
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

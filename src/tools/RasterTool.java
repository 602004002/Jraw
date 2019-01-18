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
public class RasterTool extends DrawingTool {

    public RasterTool(String name, ImageIcon toolBarIcon) {
        super(name, toolBarIcon, ToolIconType.Radius);
    }
    
    
    
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

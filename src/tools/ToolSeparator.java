/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import common.ToolIconType;

/**
 *
 * @author nickz
 */
public class ToolSeparator extends DrawingTool {
    //dummy for adding separators
    public ToolSeparator() {
        super(null, null, ToolIconType.ToolBar_Separator);
    }

    @Override
    protected void draw() {
        throw new UnsupportedOperationException("??? Shouldn't be able to use this."); 
    }
}

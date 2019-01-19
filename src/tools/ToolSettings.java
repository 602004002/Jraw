/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import common.ToolIconType;
import java.awt.Color;

/**
 *
 * @author nickz
 */
//encapsulation
public class ToolSettings {
    private Color color;
    private ToolIconType tt;
    private int dithering, hardness, thickness;
    //default
    public ToolSettings (ToolIconType tt) {
        this.color = Color.BLACK;
        this.dithering = 0;
        this.hardness = 100;
        this.thickness = 10;
        this.tt = tt;
    }
    //implement accessors and mutators
}

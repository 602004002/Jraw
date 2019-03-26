/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.toolbar;

import java.awt.Color;

/**
 *
 * @author nickz
 */
public class ColorPalette1 {
    private Color[][] colorArr;
    public ColorPalette1 () {
        this.colorArr = new Color[5][5]; //dummy value
        
    }
    
    public void setColor (Color c, int row, int col) {
        
    }
    
    public Color getColor (int row, int col) {
        return this.colorArr[row][col];
    }
    
    public Color[][] getArr () {
        return this.colorArr;
    }
}

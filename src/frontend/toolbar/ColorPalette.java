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
public class ColorPalette {
    private Color[][] colorArr;
    public ColorPalette () {
        this.colorArr = new Color[5][5]; //dummy value
        for (int row = 0; row < this.colorArr.length; row++) {
            for (int col = 0; col < this.colorArr[row].length; col++) {
                this.colorArr[row][col] = Color.WHITE;
            }
        }
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

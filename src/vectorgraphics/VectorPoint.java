/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectorgraphics;

import java.awt.Point;

/**
 *
 * @author nickz
 */
public class VectorPoint extends Point {
    public Point nextPoint;
    public Point prevPoint;
    
    private float weight;
    private int diameterSize;
}

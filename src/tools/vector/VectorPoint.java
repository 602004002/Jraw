/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.vector;

/**
 *
 * @author nickz
 */
public class VectorPoint { 

    private float weight, xPos, yPos;
    private VectorPoint nextPoint;
    private VectorPoint prevPoint;

    public VectorPoint(float weight, float xPos, float yPos) {
        
    }
    
    public float getWeight() {
        return this.weight;
    }

    public float getXPos() {
        return xPos;
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
    }

    public VectorPoint getNextPoint() {
        return this.nextPoint;
    }

    public void setNextPoint(VectorPoint nextPoint) {
        this.nextPoint = nextPoint;
    }

    public VectorPoint getPrevPoint() {
        return this.prevPoint;
    }

    public void setPrevPoint(VectorPoint prevPoint) {
        this.prevPoint = prevPoint;
    }
}

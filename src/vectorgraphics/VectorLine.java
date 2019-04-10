/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectorgraphics;

/**
 *
 * @author nickz
 */
public class VectorLine {
    public VectorPoint rootNode, tailNode;
    private int size;
    
    public void addPoint(VectorPoint add) {
        this.addPoint(add, this.size - 1);
    }
    
    public void addPoint(VectorPoint add, int index) {
        VectorPoint currentNode = rootNode;
        VectorPoint tempNode = rootNode;
        while (tempNode != null) {
            
        }
    }
}

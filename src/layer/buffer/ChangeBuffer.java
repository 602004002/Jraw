/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer.buffer;

/**
 *
 * @author nickz
 */
public class ChangeBuffer {
    
    private LayerChange[] bf; //some sort of array storing changes between every user interaction

    public ChangeBuffer(int size) {
        this.bf = new LayerChange[size];
    }

    /**
     * @param change    Push a LayerChange to index 0.
    */
    public void push(LayerChange change) {
        LayerChange commit = this.bf[this.bf.length - 1];
        
        
        System.arraycopy(bf, 0, bf, 1, this.bf.length - 1);
        bf[0] = change;
    }

    /**
     *
     * @param index Index of item in buffer.
     * @return  Returns a LayerChange from index.
     */
    public LayerChange get(int index) {
        return this.bf[index];
    }

    public int length() {
        return this.bf.length;
    }
}

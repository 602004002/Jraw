/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changestack;

import java.util.LinkedList;

/**
 *
 * @author nickz
 */
public class UndoRedoStack {

    private final LinkedList<Change> bf;
    //some sort of array storing changes between every user interaction

    public static UndoRedoStack createEmptyStack() {
        return new UndoRedoStack();
    }
    
    private UndoRedoStack() {
        this.bf = new LinkedList<>();
    }

    /**
     * @param change Push a LayerChange to end.
     */
    public void push(Change change) {
        this.bf.addLast(change);
    }

    /**
     * @return Returns the last action.
     */
    public Change pop() {
        return this.bf.removeLast();
    }
    
   public Change peek() {
       return this.bf.getLast();
   }

   
   /**
    * @return Returns how many items are in the stack.
    */
    public int size() {
        return this.bf.size();
    }
    
    /**
     * Clears everything.
     */
    public void clear() {
        this.bf.clear();
    }
}

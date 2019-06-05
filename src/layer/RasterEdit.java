/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.User;
import java.awt.image.BufferedImage;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author nickz
 */
public class RasterEdit implements UndoableEdit {
    
    private boolean undid;
    private RasterLayer layer;
    private BufferedImage change;
    private User userTag;
    
    public RasterEdit(RasterLayer layer, BufferedImage change, User userTag) {
        this.layer = layer;
        this.change = change;
        this.userTag = userTag;
        this.undid = false;
    }

    @Override
    public void undo() throws CannotUndoException {
        System.out.println("undo @ RasterEdit");
        //remove this change
    }

    @Override
    public boolean canUndo() {
        return !undid;
    }

    @Override
    public void redo() throws CannotRedoException {
        //add this change back
    }

    @Override
    public boolean canRedo() {
        return undid;
    }

    @Override
    public void die() {
        System.out.println("die @ RasterEdit");
        //commit to base?
    }

    @Override
    public boolean addEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean replaceEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public String getPresentationName() {
        return "Layer Edit";
    }

    @Override
    public String getUndoPresentationName() {
        return "";
    }

    @Override
    public String getRedoPresentationName() {
        return "";
    }
    
    @Override
    public String toString() {
        return getPresentationName();
    }
    
}

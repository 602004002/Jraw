/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer;

import common.User;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author nickz
 */
public class RasterEdit implements UndoableEdit, Serializable {

    private boolean undid;
    private boolean fromStream;
    private int layerIndex;
    private transient BufferedImage change;
    private User userTag;

    public RasterEdit(int layerIndex, BufferedImage change, User userTag) {
        this.layerIndex = layerIndex;
        this.change = change;
        this.userTag = userTag;
        this.undid = false;
        this.fromStream = false;
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

    public int getLayerIndex() {
        return layerIndex;
    }

    public BufferedImage getChange() {
        return change;
    }

    public User getUserTag() {
        return userTag;
    }
    
    public boolean isFromStream() {
        return fromStream;
    }

    @Override
    public String toString() {
        return getPresentationName();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        ImageIO.write(change, "png", oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.change = ImageIO.read(ois);
        this.fromStream = true;
    }
}

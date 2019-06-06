/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoredo;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author nickz
 */
public class UndoManager_Edit extends UndoManager {

    private ArrayList<UndoableEditListener> events;
    private transient Display d;

    public UndoManager_Edit() {
        this.events = new ArrayList<>();
    }

    public int eventSize() {
        return this.events.size();
    }

    public void setDisplay(Display d) {
        this.d = d;
        d.setUndoManager(this);
    }

    public void addEditEvent(UndoableEditListener l) {
        this.events.add(l);
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        super.undoableEditHappened(e);
        for (UndoableEditListener lis : events) {
            lis.undoableEditHappened(e);
        }
        if (d != null) {
            d.setUndoManager(this);
        }

    }

    public Vector<UndoableEdit> getEdits() {
        return this.edits;
    }
}

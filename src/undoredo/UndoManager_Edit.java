/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoredo;

import java.util.List;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import networkio.ClientToServerSocketWrapper;

/**
 *
 * @author nickz
 */
public class UndoManager_Edit extends UndoManager {
    private ClientToServerSocketWrapper ctssw;
    
    public UndoManager_Edit() {
        
    }
    
    public UndoManager_Edit(ClientToServerSocketWrapper ctssw) {
        this.ctssw = ctssw;
    }
    
    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        super.undoableEditHappened(e);
        if (ctssw != null)
        ctssw.queueSend(e.getEdit());
    }
    
    public List<UndoableEdit> getEdits() {
        return this.edits;
    }
}

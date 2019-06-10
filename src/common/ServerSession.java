/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoableEdit;
import layer.RasterEdit;
import networkio.ClientToServerSocketWrapper;

/**
 *
 * @author nickz
 */
public class ServerSession extends SessionModel {

    private ClientToServerSocketWrapper ctssw;

    public ServerSession(SessionModel sm, ClientToServerSocketWrapper ctssw) {
        super(sm);
        this.ctssw = ctssw;
        this.undoMgr.addEditEvent(e -> {
            if (e.getEdit() instanceof RasterEdit && !((RasterEdit) e.getEdit()).isFromStream()) {
                ctssw.queueSend(e.getEdit());
            }
        });
        this.ctssw.addHandler((Object o) -> {
            if (o instanceof RasterEdit) {
                this.undoMgr.undoableEditHappened(new UndoableEditEvent(this, (UndoableEdit) o));
            }
        });
    }

    public ClientToServerSocketWrapper getServer() {
        return this.ctssw;
    }

}

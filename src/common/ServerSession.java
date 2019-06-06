/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import layer.DrawingLayer;
import networkio.ClientToServerSocketWrapper;
import undoredo.UndoManager_Edit;

/**
 *
 * @author nickz
 */
public class ServerSession extends SessionModel {

    private ClientToServerSocketWrapper ctssw;

    public ServerSession(SessionModel sm, ClientToServerSocketWrapper ctssw) {
        super(new Builder().creator(sm.creator)
                .drawingType(sm.getDrawingType())
                .size(sm.size())
                .resolution(sm.resolution())
                .layerHierarchy(sm.layerHierarchy));
        this.ctssw = ctssw;
        this.undoMgr = new UndoManager_Edit();
        for (DrawingLayer dl : this.layerHierarchy) {
            dl.setUndoManager(undoMgr);
        }
        this.undoMgr.addEditEvent(e -> {
            ctssw.queueSend(e.getEdit());
        });
    }

    public ClientToServerSocketWrapper getServer() {
        return this.ctssw;
    }

}

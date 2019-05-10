/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import networkio.ClientToServerSocketWrapper;

/**
 *
 * @author nickz
 */
public class ServerSession extends SessionModel {

    private ClientToServerSocketWrapper server;

    public ServerSession(SessionModel sm) {
        super(new Builder().creator(sm.creator)
                .name(sm.name())
                .drawingType(sm.getDrawingType())
                .size(sm.size())
                .resolution(sm.resolution())
                .layerHierarchy(sm.layerHierarchy));
    }

    public void setServer(ClientToServerSocketWrapper ctssw) {
        this.server = ctssw;
    }

    public ClientToServerSocketWrapper getServer() {
        return this.server;
    }

}

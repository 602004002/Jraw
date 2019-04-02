/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import frontend.RasterCursor;

/**
 *
 * @author nickz
 */
public class Communication {
    //P2P via hole punching
    //true false for successful unsuccessful
    private Client otherUser;
    private RasterCursor c; //for cursor activity
    public boolean connect() {
        return false;
    }
    public boolean disconnect() {
        return false;
    }
    public boolean connect(boolean force) {
        return false;
    }
    public boolean disconnect(boolean force) {
        return false;
    }
    public boolean sendData(byte[] bytes) {//using with sockets. will probably change later
        return false;//there may be more of these methods instead of just for generic "data"
    }
    public boolean compareData () {
        return false;
    }
    public boolean sync () {
        return false;
    }
}

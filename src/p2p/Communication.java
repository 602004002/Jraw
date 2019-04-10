/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

/**
 *
 * @author nickz
 */
public class Communication {

    //P2P via hole punching
    public void connect(User otherUser) {

    }

    public void disconnect() {

    }

    public void connect(User otherUser, boolean force) {
        if (!force) {
            this.connect(otherUser);
        }
    }

    public void disconnect(boolean force) {
        if (!force) {
            this.disconnect();
        }
    }

    public void sendData(byte[] bytes) {//using with sockets. will probably change later
        //there may be more of these methods instead of just for generic "data"
    }

    public void compareData() {
        
    }

    public void sync() {
        
    }
}

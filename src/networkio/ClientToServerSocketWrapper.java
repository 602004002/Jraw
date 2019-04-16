/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkio;

import common.User;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author nickz
 */
public class ClientToServerSocketWrapper extends NetworkSocketWrapper {
    
    public ClientToServerSocketWrapper(Socket s) throws IOException {
        super(s);
    }

    @Override
    protected void handleIncomingObject(Object obj) {
        System.out.println(obj);
        if (obj.equals(Request.UserInfo)) {
            this.queueSend(User.localUser);
        }
    }
    
}

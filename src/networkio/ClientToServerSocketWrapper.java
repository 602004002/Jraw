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
        init2();
    }

    private void init2() {
        this.addObjectHandler((Object o) -> {
            if (o.equals(Request.UserInfo)) {
                this.queueSend(User.getLocalUser());
            }
        });
        this.addObjectHandler((Object o) -> {
            if (o instanceof String) {
                System.out.println(o);
            }
        });
    }
}

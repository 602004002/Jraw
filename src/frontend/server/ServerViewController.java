/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.server;

import common.ServerSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;

import common.SessionModel;
import common.User;
import frontend.AbstractController;
import networkio.ClientQuery;
import networkio.ClientToServerSocketWrapper;
import networkio.ObjectHandler;
import networkio.P2PUtilities;
import networkio.Request;
import networkio.ServerReply;

/**
 *
 * @author nickz
 */
public class ServerViewController extends AbstractController {

    private ServerView serverView;
    PacketListenerThread plt;
    MulticastSocket mcs;
    private InetSocketAddress address;
    private ClientQuery query;

    private ArrayList<ServerReply> replies;

    public ServerViewController(ServerView serverView) {
        query = new ClientQuery(User.getLocalUser().uuid());
        this.serverView = serverView;
        this.replies = new ArrayList<>();
        this.plt = new PacketListenerThread();
        try {
            this.mcs = new MulticastSocket(P2PUtilities.CLIENT_LISTENER_PORT);
            this.mcs.setBroadcast(true);
            this.address = new InetSocketAddress(
                    P2PUtilities.getDefaultDomain(), P2PUtilities.SERVER_LISTENER_PORT);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    void refresh() {
        this.replies.clear();
        try {
            DatagramPacket out = P2PUtilities.convertToPacket(query, address);
            mcs.send(out);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        updateServerView();
    }

    void updateServerView() {
        serverView.serverList.setListData(replies.toArray());
    }

    class RefreshAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refresh();
        }
    }

    class ConnectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ServerReply reply = (ServerReply) serverView.serverList.getSelectedValue();
            if (reply == null) {
                return;
            }
            try {
                Socket s = new Socket(reply.getAddress(), reply.getServerPort());
                ClientToServerSocketWrapper ctssw = new ClientToServerSocketWrapper(s);
                ctssw.startIOThreads();
                ObjectHandler oh = new ObjectHandler() {
                    @Override
                    public void handleObject(Object o) {
                        if (o instanceof SessionModel) {
                            //create new server session object
                            if (!model.contains((SessionModel) o)) {
                                ServerSession ss = new ServerSession((SessionModel) o, ctssw);
                                ss.rename(reply.getServerName());
                                model.add(ss);
                                ctssw.addDisconnectHandler(() -> model.remove(ss));
                            }
                        }
                    }
                };
                ctssw.addHandler(oh);
                ctssw.queueSend(Request.SessionModel);
                serverView.dispose();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    class PacketListenerThread extends Thread {

        PacketListenerThread() {
            super("PacketListenerThread");
        }

        @Override
        public synchronized void run() {
            while (true) {
                try {
                    DatagramPacket incomingPacket = P2PUtilities.getBufferPacket();
                    mcs.receive(incomingPacket); //this thread waits here
                    ServerReply reply = (ServerReply) P2PUtilities.fromPacket(incomingPacket);
                    reply.setAddress(incomingPacket.getAddress());
                    replies.add(reply);
                    updateServerView();
                } catch (IOException ex) {
                    return;
                }
            }
        }
    }

}

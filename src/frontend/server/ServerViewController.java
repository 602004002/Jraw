/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.server;

import common.User;
import frontend.AbstractController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import networkio.ClientQuery;
import networkio.ClientToServerSocketWrapper;
import networkio.P2PUtilities;
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
        query = new ClientQuery(User.localUser.uuid());
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
            System.out.println("Sent discovery packet");
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
                serverView.dispose();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    class PacketListenerThread extends Thread {

        @Override
        public synchronized void run() {
            System.out.println("Starting new packet listener thread");
            while (true) {
                try {
                    DatagramPacket incomingPacket = P2PUtilities.getBufferPacket();
                    mcs.receive(incomingPacket); //this thread waits here
                    ServerReply reply = (ServerReply) P2PUtilities.fromPacket(incomingPacket);
                    reply.setAddress(incomingPacket.getAddress());
                    replies.add(reply);
                    System.out.println(reply);
                    updateServerView();
                } catch (IOException ex) {
                    System.err.println(ex);
                    return;
                }
            }
        }
    }

}

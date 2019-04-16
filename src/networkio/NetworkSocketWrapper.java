/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author nickz
 */
public abstract class NetworkSocketWrapper {

    private IThread iThread;
    private OThread oThread;

    protected final Socket socket;
    protected final ObjectOutputStream oos;
    protected final ObjectInputStream ois;
    
    private boolean disconnected;

    protected NetworkSocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    public void startIOThreads() {
        this.iThread = new IThread();
        this.oThread = new OThread();
        this.iThread.start();
        this.oThread.start();
    }

    public void stopIOThreads() {
        this.iThread.interrupt();
        this.oThread.interrupt();
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean inputThreadActive() {
        return this.iThread.isAlive();
    }

    public void queueSend(Collection c) {
        this.oThread.queue.addAll(c);
    }

    public void queueSend(Object o) {
        this.oThread.queue.add(o);
    }

    public void disconnect() throws IOException {
        stopIOThreads();
        this.oos.close();
        this.ois.close();
        this.socket.close();
        this.disconnected = true;
    }
    
    public boolean isDisconnected() {
        return this.disconnected;
    }

    /**
     * This method is automatically called when this socket receives an object.
     * It must be implemented to handle objects that arrive.
     *
     * @param obj The incoming object.
     */
    protected abstract void handleIncomingObject(Object obj);

    private class IThread extends Thread {

        @Override
        public synchronized void run() {
            while (true) {
                try {
                    Object o = ois.readObject();
                    handleIncomingObject(o);
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println(ex);
                    try {
                        disconnect();
                    } catch (IOException ex1) {

                    }
                    return;
                }
            }
        }
    }

    private class OThread extends Thread {

        private LinkedBlockingQueue queue;

        private OThread() {
            this.queue = new LinkedBlockingQueue();
        }

        @Override
        public synchronized void run() {
            while (true) {
                try {
                    oos.writeObject(queue.take());
                    oos.flush();
                    while (!queue.isEmpty()) {
                        oos.writeObject(queue.take());
                        oos.flush();
                    }
                } catch (IOException | InterruptedException ex) {
                    System.out.println(ex);
                    try {
                        disconnect();
                    } catch (IOException ex1) {

                    }
                    return;
                }
            }
        }
    }

}

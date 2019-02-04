package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MovementThread extends Thread {

    public static final short MOVEMENT_THRESHOLD = 50;
    private short movingCounter;
    //private boolean isMoving;
    private MouseThread mt;

    public MovementThread(MouseThread mt) {
        super("MovementThread");
        this.mt = mt;
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
    }

    @Override
    public void run() {
        if (movingCounter > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(MousePositionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            movingCounter--;
            this.run();
        } else if (movingCounter <= 0) {
            //this.isMoving = false;
            this.mt.setMoving(false);
            this.mt.queueKillThread();
            System.out.println("Movement thread stop!");
        }
    }

    public void resetCounter() {
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
        this.mt.setMoving(true);
        //this.isMoving = true;
    }
}

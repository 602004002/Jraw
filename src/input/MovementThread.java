package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MovementThread extends Thread {

    public static final short MOVEMENT_THRESHOLD = 2;
    private short movingCounter;
    private boolean isMoving;

    public MovementThread() {
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
    }

    @Override
    public void run() {
        if (movingCounter > 0) {
            this.isMoving = true;
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(MousePositionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            movingCounter--;
            this.run();
        } else if (movingCounter <= 0) {
            this.isMoving = false;
            System.out.println("Moving == " + movingCounter);
        }
    }

    public void resetCounter() {
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
    }

    public boolean isMoving() {
        return this.isMoving;
    }
}

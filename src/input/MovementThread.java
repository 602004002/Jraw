package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MovementThread extends Thread {

    public static final short MOVEMENT_THRESHOLD = 200;
    private short movingCounter;

    public MovementThread() {
        super("MovementThread");
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
    }

    @Override
    public void run() {
        while (movingCounter > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(MousePositionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            movingCounter--;
        }
        System.out.println("Movement thread stop!");

    }

    public void resetCounter() {
        this.movingCounter = MovementThread.MOVEMENT_THRESHOLD;
    }
}

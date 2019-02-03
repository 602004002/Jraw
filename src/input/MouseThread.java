package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseThread extends Thread {

    /**
     * Should match mouse DPI
     */
    public static final short SLEEP_VAL = 5;
    public static final short IDLE_THRESHOLD = 1500;
    private short idleCounter;
    private boolean killThread;
    private boolean drawing;
    private boolean moving;

    @Override
    public void run() {
        //System.out.println("MouseThread Loop run " + idleCounter);
        assert (!killThread) : "Thread is queued to be killed";
        if (!moving) {
            idleCounter += MouseThread.SLEEP_VAL;
            if (idleCounter >= MouseThread.IDLE_THRESHOLD) {
                this.queueKillThread();
            }
        } else {
            idleCounter = 0;
        }
        if (drawing) {
            assert(idleCounter == 0) : "Drawing. Should not be idle state.";
            //this.sender.getDrawingCursor().draw();
            //draw(new Point()); //get point relative to canvas
            System.out.println("Drawing...");
        }
        if (killThread) {
            System.out.println("Thread interrupted");
            killThread = false;
            this.interrupt();
            return;
        }
        try {
            Thread.sleep(MouseThread.SLEEP_VAL);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        //assert (!killThread) : "Thread should be dead";
        assert (idleCounter < MouseThread.IDLE_THRESHOLD) : "Thread should have been terminated when reaching threshold";
        this.run();
    }
    
    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public void queueKillThread() {
        this.killThread = true;
    }
}

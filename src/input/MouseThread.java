package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseThread extends Thread {

    /**
     * Should match mouse DPI
     */
    private boolean killThread;
    private boolean holding;
    private boolean moving;
    public MouseThread() {
        super("MouseThread");
    }

    @Override
    public void run() {
        //System.out.println("MouseThread Loop run " + idleCounter);
        //assert (!killThread) : "Thread is queued to be killed";
        System.out.println("");
        if (holding && moving) {
            //this.sender.getDrawingCursor().draw();
            //draw(new Point()); //get point relative to canvas
            System.out.println("Drawing...");
        }
        if (killThread) {
            System.out.println("Mouse Thread interrupted");
            this.interrupt();
            return;
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.run();
    }

    public void setHolding(boolean holding) {
        this.holding = holding;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void queueKillThread() {
        this.killThread = true;
    }
}

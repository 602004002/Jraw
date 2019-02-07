package input;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseThread extends Thread {

    /**
     * Should match mouse DPI
     */
    //private boolean killThread;
    private boolean holding;
    private MovementThread mmt;

    public void setMmt(MovementThread mmt) {
        this.mmt = mmt;
    }
    
    public MouseThread(MovementThread mmt) {
        super("MouseThread");
        this.mmt = mmt;
    }

    @Override
    public void run() {
        //System.out.println("MouseThread Loop run " + idleCounter);
        //assert (!killThread) : "Thread is queued to be killed";
        //System.out.println("");
        //if (killThread) {
        if(!this.mmt.isAlive()) {
            System.out.println("Mouse Thread stop!");
            return;
        }
        if (holding) {
            //this.sender.getDrawingCursor().draw();
            //draw(new Point()); //get point relative to canvas
            //System.out.println("Drawing...");
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
}

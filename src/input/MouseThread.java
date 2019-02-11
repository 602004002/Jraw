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

    public MouseThread(MovementThread mmt) {
        super("MouseThread");
        this.mmt = mmt;
    }

    @Override
    public void run() {
        while (this.mmt.isAlive()) {
            assert (this.mmt != null) : "Why is the movement thread null?";
            if (holding) {
                //this.sender.getDrawingCursor().draw();
                //draw(new Point()); //get point relative to canvas
                System.out.println("Drawing...");
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(MouseThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Mouse Thread stop!");
    }

    public void setHolding(boolean holding) {
        this.holding = holding;
    }

    public void setMmt(MovementThread mmt) {
        this.mmt = mmt;
    }
}

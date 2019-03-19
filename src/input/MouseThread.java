package input;

import frontend.LayerViewport;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import layer.RasterLayer;

public class MouseThread extends Thread {

    /**
     * Should match mouse DPI
     */
    public static final short MOVEMENT_THRESHOLD = 200;
    private boolean holding;
    private int movingCounter;
    private LayerViewport sender;
    private BasicStroke bs;
    private Point old;

    public MouseThread(LayerViewport sender) {
        super("MouseThread");
        movingCounter = MouseThread.MOVEMENT_THRESHOLD;
        this.sender = sender;
        this.bs = new BasicStroke(5f);
    }

    @Override
    public void run() {
        while (movingCounter > 0) {
            final Point p = this.sender.getVp().getMousePosition(true);
            if (holding && p != null && old != null) {
                //dirty code
                RasterLayer get = (RasterLayer) this.sender.getSession().hierarchy.get(0);
                //get from layerlist
                Graphics2D g2d = (Graphics2D) get.getImg().createGraphics();
                g2d.setColor(Color.BLACK);
                g2d.setStroke(this.bs);
                g2d.drawLine(old.x, old.y, p.x, p.y);
                //g2d.fillRect(p.x, p.y, 10, 2);
                g2d.dispose();

                get.repaint();
            }
            movingCounter--;
            old = p;
        }
        //System.out.println("Mouse Thread stop!");
    }

    public void resetCounter() {
        this.movingCounter = MouseThread.MOVEMENT_THRESHOLD;
    }

    public void setHolding(boolean holding) {
        this.holding = holding;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import layer.RasterLayer;
import frontend.Viewport;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author nickz
 */
public class MousePositionListener implements MouseMotionListener, MouseListener {

    private Viewport sender;
    private MouseThread mt;
    private MovementThread mmt;

    public MousePositionListener(Viewport sender) {
        System.out.println("MousePositionListener(Viewport \"" + sender.getName() + "\")");
        this.sender = sender;
    }

    private void checkTerminate() {
        if (this.mt.isAlive()) {
            this.mt.queueKillThread();
        }
    }

    private void checkRun() {
        if (this.mt == null || !this.mt.isAlive() || this.mt.isInterrupted()) {
            this.mt = new MouseThread();
            this.mt.start();
        }
    }

    private void checkMovementRun() {
        if (this.mmt == null || !this.mmt.isAlive()) {
            if (this.mmt != null) {
                System.out.println("mmt thread alive: " + this.mmt.isAlive());
            }
            System.out.println("new movement thread started");
            this.mmt = new MovementThread();
            this.mmt.start();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.checkRun();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        checkMovementRun();
        this.checkRun();
    }

    /*
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        //zoom with alt held down
        //pan left and right with ctrl held down
        //pan up and down with nothing held down
        //rotate with shift held down
    }
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.mt.setDrawing(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mt.setDrawing(true);
        this.checkRun();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mt.setDrawing(true);
        this.checkRun();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mt.setDrawing(true);
        this.checkRun();

    }

    private void draw(Point p) {
        //replace with cursor.draw()
        //this is quick and dirty code
        RasterLayer get = (RasterLayer) this.sender.session.hierarchy.get(0);
        get.getImg().setRGB(p.x, p.y, java.awt.Color.BLACK.getRGB());
        System.out.println("drawing...");
        this.sender.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.checkTerminate();
        this.mt.setDrawing(false);
    }
}
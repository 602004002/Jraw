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

    private void checkRun() {
        if (this.mt == null || !this.mt.isAlive() || this.mt.isInterrupted()) {
            System.out.println("New mouse thread started");
            this.mt = new MouseThread(this.mmt);
            this.mt.start();
        }
    }

    private void checkMovementRun() {
        if (this.mmt == null || !this.mmt.isAlive()) {
            System.out.println("New Movement Thread started");
            this.mmt = new MovementThread();
            if (this.mt != null) {
                this.mt.setMmt(this.mmt);
            }
            this.mmt.start();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        checkMovementRun();
        this.checkRun();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        checkMovementRun();
        this.checkRun();
        this.mmt.resetCounter();
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
        this.mt.setHolding(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mt.setHolding(true);
        checkMovementRun();
        this.mmt.resetCounter();
        this.checkRun();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mt.setHolding(true);
        checkMovementRun();
        this.mmt.resetCounter();
        this.checkRun();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mt.setHolding(true);
        checkMovementRun();
        this.mmt.resetCounter();
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
        this.mt.setHolding(false);
    }
}
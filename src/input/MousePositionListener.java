/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import frontend.LayerViewport;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author nickz
 */
public class MousePositionListener implements MouseMotionListener, MouseListener {

    private LayerViewport sender;
    private MouseThread mt;

    public MousePositionListener(LayerViewport sender) {
        System.out.println("MousePositionListener(Viewport \"" + sender.getName() + "\")");
        this.sender = sender;
    }

    private void checkRun() {
        if (this.mt == null || !this.mt.isAlive() || this.mt.isInterrupted()) {
            //System.out.println("New mouse thread started");
            this.mt = new MouseThread(this.sender);
            this.mt.start();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.checkRun();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.checkRun();
        this.mt.resetCounter();
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
        //this.mt.setHolding(true);
        this.mt.resetCounter();
        this.checkRun();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mt.setHolding(true);
        this.mt.resetCounter();
        this.checkRun();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mt.setHolding(true);
        this.mt.resetCounter();
        this.checkRun();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mt.setHolding(false);
    }
}
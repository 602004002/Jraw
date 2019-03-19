/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import common.SessionModel;
import frontend.LayerViewport;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import layer.RasterLayer;

/**
 *
 * @author nickz
 */
public class MousePositionListener1 implements MouseMotionListener, MouseListener {

    private SessionModel session;
    private LayerViewport vp;
    private Point old;

    public MousePositionListener1(LayerViewport vp) {
        System.out.println("MousePositionListener(Viewport \"" + vp.getName() + "\")");
        this.session = vp.getSession();
        this.vp = vp;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
        this.old = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        draw();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void draw() {
        int[] indexes = this.session.getSelectedLayerIndexes();
        RasterLayer get = null;
        if (indexes.length != 0) {
            get = (RasterLayer) this.session.hierarchy.get(indexes[0]);
        }
        final Point p = this.vp.getMousePosition(true);
        if (p != null) {
            p.x -= session.getWidth() / 2;
            p.y -= session.getHeight() / 2;
        }
        if (get != null && p != null && old != null && get.isVisible()) {
            //dirty code
            Graphics2D g2d = (Graphics2D) get.getImg().createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(old.x, old.y, p.x, p.y);
            //g2d.fillRect(p.x, p.y, 10, 2);
            g2d.dispose();
            get.repaint();
        }
        old = p;
    }
}

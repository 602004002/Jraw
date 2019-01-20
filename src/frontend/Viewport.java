/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.Session;
import input.MousePositionListener;
import input.TabletPenPositionListener;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;

/**
 *
 * @author nickz
 */
public class Viewport extends JLayeredPane {

    private int xpan, ypan;
    private MousePositionListener mpl;
    private TabletPenPositionListener tppl;
    public final Session session;
    private RasterCursor cursor;

    public Viewport(Session session) {
        System.out.println("Viewport(Session)");
        this.session = session;
        this.setName(session.getName());
        this.mpl = new MousePositionListener(this);
        this.tppl = new TabletPenPositionListener(this);
        init();
    }

    private void init() {
        this.setCursor(new RasterCursor());
        this.addMouseListener(mpl);
        this.addMouseMotionListener(mpl);
        //this.addMouseWheelListener(mpl);
        this.setBackground(Color.RED);
        session.hierarchy.forEach((JComponent dl) -> {
                this.add(dl);
                dl.setSize(this.session.getCanvasWidth(),
                        this.session.getCanvasHeight());
            
        });
    }

    //x pixels in this direction, y pixels in that direction
    public void pan(int x, int y) {

    }

    //zooms this amount in. should have a maximum and minimum
    public void zoom(int percent) {
        //min 5%
        //max 500%
        //or make this user configurable
    }

    //rotates this layer deg degrees. 
    public void rotate(int deg) {

    }

    public int getXpan() {
        return xpan;
    }

    public void setXpan(int xpan) {
        this.xpan = xpan;
    }

    public int getYpan() {
        return ypan;
    }

    public void setYpan(int ypan) {
        this.ypan = ypan;
    }

    public RasterCursor getDrawingCursor() {
        return this.cursor;
    }
}
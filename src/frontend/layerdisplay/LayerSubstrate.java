/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerdisplay;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import common.SessionModel;
import layer.DrawingLayer;

/**
 *
 * @author nickz
 */
public class LayerSubstrate extends JLayeredPane {

    private LayerOverlay overlay;
    private final SessionModel session;
    private Dimension size;
    private Timer timer;

    public LayerSubstrate(SessionModel session) {
        this.session = session;
        this.size = session.size();
        this.timer = new Timer(10, e -> this.repaint());
        init();
    }

    public void enableOverlay() {
        this.overlay = new LayerOverlay();
        timer.start();
        this.updateLayers();
    }

    public void disableOverlay() {
        this.overlay = null;
        timer.stop();
        this.updateLayers();
    }

    @Override
    public String getName() {
        return this.session.name();
    }

    private void init() {
        this.setVPSize();
        updateLayers();
    }

    private void setVPSize() {
        Dimension d = new Dimension(size);
        d.height *= 1.25;
        d.width *= 1.25;
        this.setPreferredSize(d);
    }

    public void updateLayers() {
        this.removeAll();
        if (this.overlay != null) {
            this.add(this.overlay);
            Dimension d = new Dimension(size);
            d.height *= 1.25;
            d.width *= 1.25;
            this.overlay.setSize(d);
            //this.overlay.setLocation(0, 0);
        }
        Point loc = new Point(size.width / 8, size.height / 8);
        for (int i = this.session.layerCount() - 1; i >= 0; i--) {
            DrawingLayer dl = session.layerHierarchy.get(i);
            this.add(dl);
            dl.setSize(size);
            dl.setLocation(loc);
        }
    }

    public LayerOverlay getOverlay() {
        return this.overlay;
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
}

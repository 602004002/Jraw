/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;

/**
 *
 * @author nickz
 */
public class LayerSubstrate extends JLayeredPane {

    private final SessionModel session;
    private Dimension size;

    public LayerSubstrate(SessionModel session) {
        this.session = session;
        this.size = new Dimension(session.getSize());
        //this.mpl = new MousePositionListener(session, this.layeredPane);

        init();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public String getName() {
        return this.session.getName();
    }

    private void init() {
        this.setVPSize();
        //this.layeredPane.addMouseListener(mpl);
        //this.layeredPane.addMouseMotionListener(mpl);
        //this.addMouseWheelListener(mpl);
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
        for (int i = this.session.getLayerCount() - 1; i >= 0; i--) {
            JComponent dl = session.hierarchy.get(i);
            this.add(dl);
            dl.setSize(size);
            dl.setLocation(size.width / 8, size.height / 8);
        }
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

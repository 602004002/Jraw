/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import input.MousePositionListener1;
import input.TabletPenPositionListener;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author nickz
 */
public class LayerViewport extends JScrollPane {

    private MousePositionListener1 mpl;
    private TabletPenPositionListener tppl;
    private final SessionModel session;
    private JLayeredPane jlp;
    private Dimension size;

    public LayerViewport(SessionModel session) {
        this.session = session;
        this.size = new Dimension(this.session.getSize());
        this.setName(session.getName());
        this.mpl = new MousePositionListener1(session);
        this.tppl = new TabletPenPositionListener(this);
        this.jlp = new JLayeredPane();
        init();
    }

    private void init() {
        this.setName(this.session.getName());
        this.setVPSize();
        this.setViewportView(this.jlp);
        this.setAutoscrolls(true);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.jlp.setCursor(new RasterCursor());
        this.jlp.addMouseListener(mpl);
        this.jlp.addMouseMotionListener(mpl);
        //this.addMouseWheelListener(mpl);
        updateLayers();

    }

    private void setVPSize() {
        Dimension d = new Dimension(size);
        d.height *= 1.25;
        System.out.println(d.height);
        d.width *= 1.25;
        this.jlp.setPreferredSize(d);
    }

    public void updateLayers() {
        this.jlp.removeAll();
        for (int i = this.session.getLayerCount() - 1; i >= 0; i--) {
            JComponent dl = session.hierarchy.get(i);
            this.jlp.add(dl);
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

    public int getXpan() {
        return 0;
    }

    public void setXpan(int xpan) {

    }

    public int getYpan() {
        return 0;
    }

    public void setYpan(int ypan) {

    }

    public JLayeredPane getVp() {
        return jlp;
    }

}

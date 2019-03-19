/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import input.MousePositionListener1;
import input.TabletPenPositionListener;
import java.awt.Color;
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

    public LayerViewport(SessionModel session) {
        System.out.println("LayerViewport(Session)");
        this.session = session;
        this.mpl = new MousePositionListener1(this);
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
        this.jlp.setBackground(Color.ORANGE);
        for (int i = this.session.getLayerCount() - 1; i >= 0; i--) {
            JComponent dl = session.hierarchy.get(i);
            this.jlp.add(dl);
            int w = this.session.getWidth();
            int h = this.session.getHeight();
            dl.setSize(w, h);
            dl.setLocation(w / 2, h / 2);
        }
        
    }

    private void setVPSize() {
        Dimension d = new Dimension();
        d.height = this.session.getHeight() * 2;
        d.width = this.session.getWidth() * 2;
        this.jlp.setPreferredSize(d);
    }
    
    public SessionModel getSession() {
        return session;
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

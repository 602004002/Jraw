/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import com.sun.prism.paint.Color;
import frontend.RasterCursor;
import layer.RasterLayer;
import frontend.Viewport;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author nickz
 */
public class MousePositionListener extends MouseAdapter {

    private int xpos, ypos;
    private Viewport vp;
    private RasterCursor dc;

    public boolean isDrawing() {
        return false;
    }

    public MousePositionListener() {

    }

    public MousePositionListener(Viewport sender) {
        System.out.println("MousePositionListener(Viewport \"" + sender.getName() + "\")");
        this.vp = sender;
        this.dc = sender.getDrawingCursor();
    }
    
    @Override
    public void mouseWheelMoved (MouseWheelEvent mwe) {
        //zoom with alt held down
        //pan left and right with ctrl held down
        //pan up and down with nothing held down
        //rotate with shift held down
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        draw(e);
        //this.dc.draw();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        draw(e);
        //this.dc.draw();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e);
        //this.dc.draw();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    private void draw(MouseEvent e){
        //replace with cursor.draw()
        //this is quick and dirty code
        Point p = e.getPoint();
        RasterLayer get = (RasterLayer)this.vp.session.hierarchy.get(0);
        get.getImg().setRGB(p.x, p.y, Color.RED.getIntArgbPre());
        System.out.println("drawing...");
        this.vp.repaint();
    }
}

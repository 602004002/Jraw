/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerdisplay;

import common.User;
import frontend.tools.DrawingTool;
import input.PointerInfo;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author nickz
 */
public class DisplayCursor {

    protected static final int FONTHEIGHT = 20;
    protected boolean online;
    protected final PointerInfo pi;
    protected User user;

    private Ellipse2D e2d;
    private Line2D l2d;

    public DisplayCursor(PointerInfo pi, boolean online) {
        this.user = pi.getUserTag();
        this.pi = pi;
        this.online = online;
        e2d = new Ellipse2D.Float();
        l2d = new Line2D.Float();
    }

    public void draw(Graphics2D g2d) {
        Point overlay = pi.getOverlayPoint();
        DrawingTool currentTool = this.user.drawingTool();
        if (overlay != null && currentTool != null) {
            int diameter = currentTool.diameter();
            int radius = diameter / 2;
            e2d.setFrame(overlay.x - radius, overlay.y - radius, diameter, diameter);
            g2d.draw(e2d);
            if (online) {
                //draw username string
                l2d.setLine(overlay.x, overlay.y, overlay.x + FONTHEIGHT, overlay.y + FONTHEIGHT);
                g2d.draw(l2d);
                g2d.drawString(user.username(), overlay.x + FONTHEIGHT, overlay.y + 1 * FONTHEIGHT);
            }
        }
    }
}

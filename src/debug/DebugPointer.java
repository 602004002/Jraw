/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import frontend.display.DisplayCursor;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import input.PointerInfo;
import java.awt.Color;

/**
 *
 * @author nickz
 */
public class DebugPointer extends DisplayCursor {

    private Line2D line2D = new Line2D.Float();
    private Ellipse2D.Float ellipse2D = new Ellipse2D.Float();

    public DebugPointer(PointerInfo pi, boolean online) {
        super(pi, online);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (pi.getOverlayPoint() != null) {
            g2d.setColor(Color.red);
            ellipse2D.setFrame(pi.getOverlayPoint().x - FONTHEIGHT, pi.getOverlayPoint().y - FONTHEIGHT, 2 * FONTHEIGHT, 2 * FONTHEIGHT);
            g2d.draw(ellipse2D);

            line2D.setLine(pi.getOverlayPoint().x, pi.getOverlayPoint().y, pi.getOverlayPoint().x + FONTHEIGHT, pi.getOverlayPoint().y + 2 * FONTHEIGHT);
            g2d.draw(line2D);

            g2d.drawString(user.username(), pi.getOverlayPoint().x + FONTHEIGHT, pi.getOverlayPoint().y + 3 * FONTHEIGHT);
            g2d.drawString(pi.getXYPressureString(), pi.getOverlayPoint().x + FONTHEIGHT, pi.getOverlayPoint().y + 3 * FONTHEIGHT);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import input.PointerInfo;

/**
 *
 * @author nickz
 */
public class DebugPanel extends JPanel {

    private Line2D line2D = new Line2D.Float();
    private Ellipse2D.Float ellipse2D = new Ellipse2D.Float();
    private static final int FONTHEIGHT = 20;
    public PointerInfo pi = new PointerInfo();

    @Override //DEBUG
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (pi != null) {
            this.pi.currentPoint = this.getMousePosition();
            if (pi.currentPoint != null) {
                ellipse2D.setFrame(pi.currentPoint.x - FONTHEIGHT, pi.currentPoint.y - FONTHEIGHT, 2 * FONTHEIGHT, 2 * FONTHEIGHT);
                g2.draw(ellipse2D);

                line2D.setLine(pi.currentPoint.x, pi.currentPoint.y, pi.currentPoint.x + FONTHEIGHT, pi.currentPoint.y + 2 * FONTHEIGHT);
                g2.draw(line2D);

                String s1 = "dev,id,inv = " + pi.deviceType + "," + pi.pointerID + "," + pi.inverted;
                String s2 = "x,y,pressure = " + pi.currentPoint.x + "," + pi.currentPoint.y + "," + pi.pressure;
                g2.drawString(s1, pi.currentPoint.x + FONTHEIGHT, pi.currentPoint.y + 2 * FONTHEIGHT);
                g2.drawString(s2, pi.currentPoint.x + FONTHEIGHT, pi.currentPoint.y + 3 * FONTHEIGHT);
            }
        }
    }
}

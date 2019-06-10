/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerdisplay;

import input.PointerInfo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author nickz
 */
public class LayerOverlay extends JComponent {

    private List<DisplayCursor> cursors = new ArrayList<>();

    public void setCursors(DisplayCursor... cursors) {
        this.cursors = Arrays.asList(cursors);
    }

    public void addPointerInfo(PointerInfo pi) {
        for (DisplayCursor d : cursors) {
            if (d.user.equals(pi.getUserTag())) {
                updateCursor(d, pi);
                return;
            }
        }
        cursors.add(new DisplayCursor(pi, true));
    }

    public void updateCursor(DisplayCursor d, PointerInfo pi) {
        d.pi.setOverlayPoint(pi.getOverlayPoint());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cursors != null) {
            for (DisplayCursor cursor : cursors) {
                cursor.draw((Graphics2D) g);
            }
        }
    }
}

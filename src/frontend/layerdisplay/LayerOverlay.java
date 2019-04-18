/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerdisplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author nickz
 */
public class LayerOverlay extends JComponent {

    private List<DisplayCursor> cursors;

    public LayerOverlay() {
        this.cursors = new ArrayList<>();

    }

    public void setCursors(List<DisplayCursor> cursors) {
        this.cursors = cursors;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.display;

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
    private Timer timer;
    
    public LayerOverlay() {
        this.cursors = new ArrayList<>();
        this.timer = new Timer(10, (ActionEvent ae) -> {
            this.repaint();
            timer.restart();
        });
        //timer.start();
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

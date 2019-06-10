/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author nickz
 */
public class ToolbarButton extends JButton {

    private ToolbarPassSelected tps;
    private DrawingTool dt;

    public ToolbarButton(DrawingTool dt, ToolbarPassSelected tps) {
        this.tps = tps;
        this.dt = dt;
        this.init(dt);
    }

    private void init(DrawingTool dt) {
        Dimension minSize = new Dimension(36, 36);
        this.setIcon(dt.toolbarIcon());
        this.setMinimumSize(minSize);
        this.setPreferredSize(minSize);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setToolTipText(dt.toString());
        this.addActionListener((ActionEvent ae) -> {
            this.tps.pass(this);
        });
    }

    public DrawingTool getTool() {
        return this.dt;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.toolbar;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import tools.DrawingTool;

/**
 *
 * @author nickz
 */
public class ToolButton extends JButton{
    
    private ToolbarPassSelected tps;
    private DrawingTool dt;
    
    public ToolButton (DrawingTool dt, ToolbarPassSelected tps) {
        this.tps = tps;
        this.dt = dt;
        this.init(dt);
    }
    private void init (DrawingTool dt) {
        this.setIcon(dt.getIcon());
        this.setBorder(new EtchedBorder());
        this.setToolTipText(dt.toString());
        this.addActionListener((ActionEvent ae) -> {
            //System.out.println("Pass!");
            this.tps.pass(this);
        });
    }
    public DrawingTool getTool() {
        return this.dt;
    }
    
    
}

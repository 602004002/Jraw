/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.toolbar;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import tools.DrawingTool;
import tools.RasterTool;
import tools.ToolSeparator;

/**
 *
 * @author nickz
 */
public class Toolbar extends JToolBar {

    private ToolButton selected;

    public Toolbar() {
        //test
        ImageIcon i = new ImageIcon(getClass().getResource("/frontend/toolbar/testIcon.png"));
        ArrayList<DrawingTool> testarrlist = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            if (j == 3 || j == 6) {
                testarrlist.add(new ToolSeparator());
            }
            testarrlist.add(new RasterTool("Test RasterTool " + j, i));
        }
        this.setTools(testarrlist);
        System.out.println("Added test tools and separators. Hello from Toolbar constructor!");
    }

    public void setTools(ArrayList<DrawingTool> dt) {
        dt.forEach((DrawingTool drawingTool) -> {
            if (!(drawingTool instanceof ToolSeparator)) {
            ToolButton t = new ToolButton(
                    drawingTool,
                    (ToolButton tb) -> {
                        this.setSelected(tb);
                    });
            this.add(t);
            } else {
                this.addSeparator();
            }
        });
    }

    private void setSelected(ToolButton selected) {
        //System.out.println("Caught it!");
        if (this.selected != null) {
            this.selected.setBorder(new EtchedBorder());
        }
        this.selected = selected;
        selected.setBorder(new BevelBorder(BevelBorder.LOWERED));
        System.out.println("Selected tool is " + selected.getTool());
    }
    
    public void clearTools() {
        this.removeAll();
        this.selected = null;
    }

    /**
     *
     * @return Returns the currently selected tool. If nothing has been
     * selected, this method will return null. It probably shouldn't since a
     * tool should be selected at all times.
     */
    public DrawingTool getSelectedTool() {
        return this.selected.getTool();
    }
}

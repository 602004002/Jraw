/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import common.User;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author nickz
 */
public class Toolbar extends JToolBar {

    private ToolbarButton selected;
    private final ArrayList<DrawingTool> toolList;

    public Toolbar() {
        //test
        this.toolList = new ArrayList<>();
        debug();
    }

    private static final ImageIcon TESTICON = new ImageIcon(Toolbar.class.getResource("/frontend/tools/testIcon.png"));

    private void debug() {
        ImageIcon penIcon = new ImageIcon(Toolbar.class.getResource("/frontend/layerlist/pen.png"));
        DrawingTool pencil = DrawingToolFactory.getBuilder("PenTool")
                .name("Test Pencil")
                //.toolBarIcon(TESTICON)
                .toolBarIcon(penIcon)
                .diameterSize(10)
                .build();
        DrawingTool pressurePencil = DrawingToolFactory.getBuilder("PenTool")
                .name("Pressure Pencil")
                .diameterSize(5)
                .toolBarIcon(penIcon)
                .pressureAffectsDensity(true)
                .pressureAffectsSize(true)
                .build();
        addToToolbar(pencil);
        addToToolbar(pressurePencil);
        System.out.println("debug() @ Toolbar");
    }

    /**
     *
     * @param drawingtool Tool to add. Input null to add a separator
     */
    public void addToToolbar(DrawingTool drawingtool) {
        if (drawingtool == null) {
            this.addSeparator();
        } else {
            this.toolList.add(drawingtool);
            if (drawingtool.toolbarIcon == null) {
                drawingtool.toolbarIcon = TESTICON;
            }
            this.add(new ToolbarButton(drawingtool, sel -> setSelected(sel)));
        }
    }

    private void setSelected(ToolbarButton selected) {
        if (this.selected != null) {
            this.selected.setBorder(new EtchedBorder());
        }
        this.selected = selected;
        selected.setBorder(new BevelBorder(BevelBorder.LOWERED));
        User.getLocalUser().setDrawingTool(selected.getTool());
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

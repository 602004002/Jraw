/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

import input.PointerListener;
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
    private ArrayList<DrawingTool> toolList;
    private PointerListener pointerListener;

    public Toolbar() {
        //test
        this.toolList = new ArrayList<>();
        debug();
    }

    public Toolbar(PointerListener pointerListener) {
        //test
        this.pointerListener = pointerListener;
        this.toolList = new ArrayList<>();
        debug();
    }

    private static final ImageIcon TESTICON = new ImageIcon(Toolbar.class.getResource("/frontend/tools/testIcon.png"));

    private void debug() {

        DrawingTool pencil = DrawingToolFactory.getBuilder("PenTool")
                .name("Test Pencil")
                .color(Color.yellow)
                //.toolBarIcon(TESTICON)
                .diameterSize(10)
                .build();
        DrawingTool pressurePencil = DrawingToolFactory.getBuilder("PenTool")
                .name("Pressure Pencil")
                .color(Color.BLACK)
                .diameterSize(5)
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
        //System.out.println("Caught it!");
        if (this.selected != null) {
            this.selected.setBorder(new EtchedBorder());
        }
        this.selected = selected;
        selected.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.pointerListener.setDrawMethod(this.selected.getTool());
        System.out.println(selected.getTool());
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

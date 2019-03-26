/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author nickz
 */
public class DocumentPane extends JTabbedPane {

    private static final ImageIcon CLOSE_ICON;
    private final MainViewController mvc;

    static {
        CLOSE_ICON = new ImageIcon(
                DocumentPane.class.getResource("/frontend/close.png"));
    }

    public DocumentPane() {
        this.mvc = new MainViewController();
        init();
    }

    public DocumentPane(MainViewController mvc) {
        this.mvc = mvc;
        init();
    }

    private void init() {
        this.addChangeListener(this.mvc.new UpdateSelectedTab());
        this.addContainerListener(this.mvc.new TabContainerListener());
    }

    @Override
    public Component add(Component component) {
        super.add(component);
        int index = this.indexOfComponent(component);
        if (index < 0) {
            return component;
        }
        JPanel namePlusCloseBtn = new JPanel();
        JLabel name = new JLabel(component.getName());
        JButton closeBtn = new JButton();
        closeBtn.addActionListener(
                this.mvc.new TabCloseAction((LayerViewport) component));
        if (CLOSE_ICON != null) {
            closeBtn.setIcon(DocumentPane.CLOSE_ICON);
            closeBtn.setBorder(null);
            Dimension tt = new Dimension(16, 16);
            closeBtn.setPreferredSize(tt);
            closeBtn.setMaximumSize(tt);
        }
        namePlusCloseBtn.add(name);
        namePlusCloseBtn.add(closeBtn);
        this.setTabComponentAt(index, namePlusCloseBtn);
        return component;
    }

}

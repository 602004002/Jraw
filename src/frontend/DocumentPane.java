/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final ImageIcon CLOSE_ICON = initCloseIcon();

    private static ImageIcon initCloseIcon() {
        return new ImageIcon("/resources/icon/close.png");
    }

    public DocumentPane() {

    }

    @Override
    public Component add(Component component) {
        super.add(component);
        JPanel jp = new JPanel();
        jp.add(new JLabel(component.getName()));
        JButton close = new JButton();
        if (CLOSE_ICON != null) {
            close.setIcon(this.CLOSE_ICON);
        } else {
            try {
                System.out.println(new File("resources/icon/close.png").getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(DocumentPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jp.add(close);
        System.out.println("Tab Count: " + this.getTabCount());
        this.setTabComponentAt(this.getTabCount() - 1, jp);
        return component;
    }
}

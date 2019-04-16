package frontend;

import frontend.display.LayerSubstrate;
import frontend.MainViewController.TabCloseAction;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author nickz
 */
public class DocumentPane extends JTabbedPane {

    private final MainViewController mvc;

    public DocumentPane() {
        this.mvc = new MainViewController();
    }

    public DocumentPane(MainViewController mvc) {
        this.mvc = mvc;
    }

    public final void finishInit() {
        this.addChangeListener(this.mvc.new UpdateSelectedTab());
        this.addContainerListener(this.mvc.new TabContainerListener());
    }

    @Override
    public Component add(Component component) {
        if (!(component instanceof LayerSubstrate)) {
            super.add(component);
            return component;
        }
        JScrollPane jsp = new JScrollPane(component);
        super.add(jsp);
        TabLabel tl = new TabLabel(component.getName(), this.mvc.new TabCloseAction((LayerSubstrate) component));
        int index = this.indexOfComponent(jsp);
        this.setTabComponentAt(index, tl);
        return component;
    }

    @Override
    public Component getSelectedComponent() {
        Component c = super.getSelectedComponent();
        if (c instanceof JScrollPane) {
            return ((JScrollPane)c).getViewport().getView();
        }
        return c;
    }

    public int indexOfComponent(LayerSubstrate component) {
        for (int i = 0; i < this.getTabCount(); i++) {
            Component check = this.getComponentAt(i);
            if (!(check instanceof JScrollPane)) {
                continue;
            }
            if (((JScrollPane) check).getViewport().getView() == component) {
                return i;
            }
        }
        return -1;
    }

    private static class TabLabel extends JPanel {

        private static final ImageIcon CLOSE_ICON;
        private static final Dimension TT;

        static {
            CLOSE_ICON = new ImageIcon(
                    TabLabel.class.getResource("/frontend/close.png"));
            TT = new Dimension(16, 16);
        }

        private JLabel nameLabel;
        private JButton closeBtn;

        TabLabel(String name, ActionListener al) {
            nameLabel = new JLabel(name);
            closeBtn = new JButton();
            closeBtn.setIcon(TabLabel.CLOSE_ICON);
            closeBtn.setBorder(null);
            closeBtn.setPreferredSize(TT);
            closeBtn.setMaximumSize(TT);
            closeBtn.addActionListener(al);
            this.add(nameLabel);
            this.add(closeBtn);
        }
    }
}

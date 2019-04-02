package frontend;

import common.SessionModel;
import java.awt.Component;
import javax.swing.JScrollPane;

/**
 *
 * @author nickz
 */
public abstract class AbstractController {

    protected MainView mainview;
    protected Model model;

    public void setMainView(MainView mainview) {
        this.mainview = mainview;
    }

    protected void setModel(Model model) {
        this.model = model;
    }

    protected SessionModel getCurrentSessionModel() {
        int index = -1;
        Component c = mainview.documentTabbedPane.getSelectedComponent();
        if (c != null) {
            index = model.indexOf((LayerSubstrate) c);
        }
        SessionModel s = null;
        if (index >= 0) {
            s = this.model.getSessionModel(index);
        }
        return s;
    }

    protected LayerSubstrate getCurrentViewport() {
        return (LayerSubstrate) this.mainview.documentTabbedPane.getSelectedComponent();
    }
}

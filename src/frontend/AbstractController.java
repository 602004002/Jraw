package frontend;

import frontend.display.LayerSubstrate;
import common.SessionModel;
import java.awt.Component;

/**
 *
 * @author nickz
 */
public abstract class AbstractController {

    protected MainView mainview;
    protected Model model;

    protected void setMainView(MainView mainview) {
        this.mainview = mainview;
    }

    protected void setModel(Model model) {
        this.model = model;
    }

    protected SessionModel getCurrentSessionModel() {
        int index = model.indexOf(this.getCurrentViewport());
        if (index >= 0) {
            return this.model.getSessionModel(index);
        }
        return null;
    }

    protected LayerSubstrate getCurrentViewport() {
        Component c = this.mainview.documentTabbedPane.getSelectedComponent();
        if (c instanceof LayerSubstrate) {
            return (LayerSubstrate) c;
        }
        return null;
    }
}

package frontend;

import frontend.layerdisplay.LayerSubstrate;
import common.SessionModel;
import java.awt.Component;

/**
 *
 * @author nickz
 */
public abstract class AbstractController {

    protected MainView mainview;
    protected AllSessionsModel model;

    protected void setMainView(MainView mainview) {
        this.mainview = mainview;
    }

    protected void setModel(AllSessionsModel model) {
        this.model = model;
    }

    protected SessionModel getActiveSession() {
        int index = model.indexOf(this.getActiveViewport());
        if (index >= 0) {
            return this.model.getSessionModel(index);
        }
        return null;
    }

    protected LayerSubstrate getActiveViewport() {
        Component c = this.mainview.sessionTabbedPane.getSelectedComponent();
        if (c instanceof LayerSubstrate) {
            return (LayerSubstrate) c;
        }
        return null;
    }
}

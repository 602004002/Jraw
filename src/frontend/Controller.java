/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;

/**
 *
 * @author nickz
 */
public class Controller {

    protected MainView mainview;
    protected Model model;

    public void setMainView(MainView mainview) {
        this.mainview = mainview;
    }

    protected void setModel(Model model) {
        this.model = model;
    }

    protected SessionModel getCurrentSessionModel() {
        int index = mainview.documentTabbedPane.getSelectedIndex();
        SessionModel s = null;
        if (index >= 0) {
            s = this.model.sessionList.get(index);
        }
        return s;
    }

    protected LayerViewport getCurrentViewport() {
        return (LayerViewport) this.mainview.documentTabbedPane.getSelectedComponent();
    }
}

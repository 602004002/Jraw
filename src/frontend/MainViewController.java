/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author nickz
 */
public class MainViewController extends Controller {
    
    private void layerListUpdate() {
        SessionModel s = this.getCurrentSessionModel();
        mainview.layerList.setSession(s);
        if (s != null) {
            mainview.layerList.setSession(s);
            mainview.layerList.setSelectedIndices(s.getSelectedLayerIndexes());
        }
    }

    class UpdateSelectedTab implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            layerListUpdate();
        }
    }

    class TabContainerListener implements ContainerListener {

        @Override
        public void componentAdded(ContainerEvent e) {
            layerListUpdate();
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            layerListUpdate();
        }

    }

    class TabCloseAction implements ActionListener {

        private final LayerViewport tabToClose;

        public TabCloseAction(LayerViewport tabToClose) {
            this.tabToClose = tabToClose;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = mainview.documentTabbedPane.indexOfComponent(tabToClose);
            mainview.documentTabbedPane.remove(index);
            model.sessionList.remove(index);
            mainview.updateDocumentPane();
            layerListUpdate();
        }
    }
}

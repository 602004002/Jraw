/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import frontend.subforms.newfile.FileController;
import frontend.subforms.newfile.NewFileForm;
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
public class ModelViewController {

    private MainView mainview;
    private Model model;

    public void setMainView(MainView mainview) {
        this.mainview = mainview;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private void layerListUpdate() {
        int index = mainview.documentTabbedPane.getSelectedIndex();
        SessionModel s = null;
        if (index >= 0) {
            s = model.sessionList.get(index);
        }
        mainview.layerList.setSession(s);
        if (s != null) {
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
            model.sessionList.remove(tabToClose.getSession());
            mainview.updateDocumentPane();
            layerListUpdate();
        }
    }

    class QuitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //check for save

        }
    }

    class NewFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread nf = new Thread(() -> {
                new NewFileForm(new FileController(model, mainview))
                        .setVisible(true);
            }, "New file thread");
            nf.start();
        }
    }

    class OpenFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}

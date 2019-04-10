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
public class MainViewController extends AbstractController {

    void updateTabs() {
        DocumentPane dp = this.mainview.documentTabbedPane;
        for (int i = 0; i < model.size(); i++) {
            LayerSubstrate ls = this.model.getSubstrate(i);
            if (dp.indexOfComponent(ls) < 0) {
                dp.add(ls);
            }
        }
    }

    void update() {
        SessionModel s = this.getCurrentSessionModel();
        this.mainview.layerList.setSession(s);
        this.mainview.pointerListener.setSession(s);
    }

    class UpdateSelectedTab implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            update();
        }
    }

    class TabContainerListener implements ContainerListener {

        @Override
        public void componentAdded(ContainerEvent e) {
            update();
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            update();
        }

    }

    class TabCloseAction implements ActionListener {

        private final LayerSubstrate tabToClose;

        TabCloseAction(LayerSubstrate tabToClose) {
            this.tabToClose = tabToClose;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int mainviewIndex = mainview.documentTabbedPane.indexOfComponent(tabToClose);
            if (true) {//need to implement save state soon
                mainview.documentTabbedPane.remove(mainviewIndex);
                remove();
            } else {
                //ask if they want to save
                //remove(index) if no
                //pop up with CommonIO savedialog if file doesn't exist
            }
        }

        private void remove() {
            int index = model.indexOf(tabToClose);
            model.remove(index);
            update();
        }
    }
}

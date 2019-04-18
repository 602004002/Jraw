/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import frontend.layerdisplay.LayerSubstrate;
import common.SessionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author nickz
 */
public class MainViewController extends AbstractController {

    public void updateTabs() {
        DocumentPane dp = this.mainview.documentTabbedPane;
        for (int i = 0; i < model.size(); i++) {
            LayerSubstrate ls = this.model.getSubstrate(i);
            if (dp.indexOfComponent(ls) < 0) {
                dp.add(ls);
            }
        }
    }

    public void mvUpdate() {
        System.out.println("MVC Update Call");
        SessionModel s = this.getCurrentSessionModel();
        LayerSubstrate ls = this.getCurrentViewport();
        this.mainview.layerList.setSession(s);
        this.mainview.pointerListener.setSession(s, ls);
        menuUpdate();
    }

    public void menuUpdate() {
        boolean sessionExists = this.getCurrentSessionModel() != null;
        this.mainview.closeMenuItem.setEnabled(sessionExists);
        this.mainview.saveMenuItem.setEnabled(sessionExists);
        this.mainview.saveAsMenuItem.setEnabled(sessionExists);
        this.mainview.exportMenuItem.setEnabled(sessionExists);
        this.mainview.undoMenuItem.setEnabled(sessionExists);
        this.mainview.redoMenuItem.setEnabled(sessionExists);
        this.mainview.cutMenuItem.setEnabled(sessionExists);
        this.mainview.copyMenuItem.setEnabled(sessionExists);
        this.mainview.pasteMenuItem.setEnabled(sessionExists);
        this.mainview.clearMenuItem.setEnabled(sessionExists);
        this.mainview.fillMenuItem.setEnabled(sessionExists);
        this.mainview.advancedFillMenuItem.setEnabled(sessionExists);
        this.mainview.changeResolutionMenuItem.setEnabled(sessionExists);
        this.mainview.changeCanvasSizeMenuItem.setEnabled(sessionExists);
        this.mainview.cropMenuItem.setEnabled(sessionExists);
        this.mainview.layerPropertiesMenuItem.setEnabled(sessionExists);
        this.mainview.bufferMenuItem.setEnabled(sessionExists);
        this.mainview.newRasterLayerMenuItem.setEnabled(sessionExists);
        this.mainview.newLayerFolderMenuItem.setEnabled(sessionExists);
        this.mainview.duplicateLayerMenuItem.setEnabled(sessionExists);
        this.mainview.deleteLayerMenuItem.setEnabled(sessionExists);
        this.mainview.selectAllMenuItem.setEnabled(sessionExists);
        this.mainview.deselectMenuItem.setEnabled(sessionExists);
        this.mainview.invertSelectionMenuItem.setEnabled(sessionExists);

    }

    class UpdateSelectedTab implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            mvUpdate();
        }
    }

    class TabCloseAction implements ActionListener {

        private final LayerSubstrate tabToClose;

        TabCloseAction(LayerSubstrate tabToClose) {
            this.tabToClose = tabToClose;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = model.getSessionModel(tabToClose);
            if (sm.isSaved()) {
                int mainviewIndex = model.indexOf(sm);
                mainview.documentTabbedPane.remove(mainviewIndex);
                model.remove(tabToClose);
                mvUpdate();
            } else {
                //ask if they want to save
                //remove(index) if no
                //pop up with CommonIO savedialog if file doesn't exist
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.ServerSession;
import frontend.layerdisplay.LayerSubstrate;
import common.SessionModel;
import fileio.CommonIO;
import frontend.dialog.YesNoDialog;
import frontend.newfile.NewFileForm;
import frontend.newfile.NewFileFormController;
import frontend.server.ServerView;
import frontend.server.ServerViewController;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import undoredo.UndoManager_Edit;

/**
 *
 * @author nickz
 */
public class MainViewController extends AbstractController {

    private static final FileNameExtensionFilter JRAW, PNG, JPG;

    static {
        JRAW = new FileNameExtensionFilter("Jraw File Format (.jraw)", "jraw");
        PNG = new FileNameExtensionFilter("Portable Network Graphics (.png)", "png");
        JPG = new FileNameExtensionFilter("JPG Image (.jpg)", "jpg", "jpeg");
    }

    public void updateTabs() {
        SubstratePane dp = this.mainview.sessionTabbedPane;
        dp.removeAll();
        for (int i = 0; i < model.size(); i++) {
            LayerSubstrate ls = this.model.getSubstrate(i);
            dp.add(ls);
        }
    }

    public void fullUpdate() {
        SessionModel s = this.getActiveSession();
        LayerSubstrate ls = this.getActiveViewport();
        this.mainview.layerList.setSession(s);
        this.mainview.pointerListener.setCurrentSession(s, ls);
        menuUpdate();
    }

    public void menuUpdate() {
        SessionModel currentModel = this.getActiveSession();
        boolean sessionExists = currentModel != null;
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

        boolean serverConnected = false;
        for (int i = 0; i < model.size(); i++) {
            if (model.getSessionModel(i) instanceof ServerSession) {
                serverConnected = true;
                break;
            }
        }
        this.mainview.connectMenuItem.setEnabled(!serverConnected);
        this.mainview.disconnectMenuItem.setEnabled(currentModel instanceof ServerSession);
    }

    boolean com_TryCloseTab(LayerSubstrate tabToClose) {
        SessionModel sm = model.getSessionModel(tabToClose);
        if (sm instanceof ServerSession) {
            ((ServerSession) sm).getServer().disconnect();
            return true;
        }
        if (sm.isSaved()) {
            model.remove(tabToClose);
            return true;
        } else {
            //ask if they want to save
            int result = YesNoDialog.showDialog(mainview, "Save before closing?");
            if (result == YesNoDialog.YES_OPTION) {
                return com_SaveFile(sm);
            } else if (result == YesNoDialog.NO_OPTION) {
                model.remove(tabToClose);
                return true;
            }
        }
        return false;
    }

    void com_TryQuit() {
        //check for save
        for (int i = model.size() - 1; i >= 0; i--) {
            if (!com_TryCloseTab(model.getSubstrate(i))) {
                return;
            }
        }
        System.exit(0);
    }

    void com_NewFile() {
        NewFileForm nff = new NewFileForm(mainview);
        NewFileFormController nffo = new NewFileFormController(nff);
        nffo.setModel(model);
        nff.setController(nffo);
        nff.setVisible(true);
    }

    void com_OpenFile() {
        File f = CommonIO.showOpenDialog(mainview, JRAW);
        if (f != null) {
            try {
                SessionModel sm = CommonIO.readProprieteryFormat(f);
                if (!model.contains(sm)) {
                    model.add(sm);
                    sm.setLastPath(f);
                    sm.setSaved(true);
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    boolean com_SaveFile(SessionModel sm) {
        if (sm.isSaved()) {
            return true;
        }
        if (sm.getLastPath() != null && sm.getLastPath().exists()) {
            try {
                CommonIO.saveProprieteryFormat(sm, sm.getLastPath(), mainview);
                sm.setSaved(true);
                updateTabs();
                return true;
            } catch (IOException ex) {
            }
        }
        return com_SaveAsFile(sm);
    }

    boolean com_SaveAsFile(SessionModel sm) {
        if (sm == null) {
            return false;
        }
        File f = CommonIO.showSaveDialog(sm.getLastPath(), mainview, JRAW);
        if (f != null) {
            try {
                CommonIO.saveProprieteryFormat(sm, f, mainview);
                sm.setLastPath(f);
                sm.setSaved(true);
                updateTabs();
                return true;
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return false;
    }

    boolean com_Export() {
        SessionModel sm = this.getActiveSession();
        if (sm == null) {
            return false;
        }
        File f = CommonIO.showSaveDialog(mainview, PNG, JPG);
        if (f != null) {
            try {
                CommonIO.export(sm, f, mainview);
                sm.setLastPath(f);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return false;
    }

    void com_NewRasterLayer() {
        SessionModel sm = this.getActiveSession();
        LayerSubstrate lv = getActiveViewport();
        sm.addRasterLayer();
        lv.updateLayers();
        mainview.layerList.refresh();
    }

    void com_Undo() {
        SessionModel current = this.getActiveSession();
        try {
            current.getUndoManager().undo();
        } catch (CannotUndoException ex) {

        }
    }

    void com_Redo() {
        SessionModel current = this.getActiveSession();
        try {
            current.getUndoManager().redo();
        } catch (CannotRedoException ex) {

        }
    }

    void com_OpenBuffer() {
        UndoManager_Edit ume = getActiveSession().getUndoManager();
        undoredo.Display d = undoredo.Display.getInstance(mainview);
        d.setUndoManager(ume);
        d.setVisible(true);
    }

    void com_Connect() {
        ServerView sv = new ServerView(mainview);
        ServerViewController svc = new ServerViewController(sv);
        sv.setController(svc);
        svc.setModel(model);
        sv.setVisible(true);
    }

    void com_Disconnect() {
        ServerSession sm = (ServerSession) this.getActiveSession();
        sm.getServer().disconnect();
    }
}

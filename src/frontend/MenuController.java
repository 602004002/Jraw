/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.undo.UndoManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import common.ServerSession;
import common.SessionModel;
import frontend.layerdisplay.LayerSubstrate;
import frontend.dialog.YesNoDialog;
import frontend.newfile.NewFileForm;
import frontend.newfile.NewFileFormController;
import frontend.server.ServerView;
import frontend.server.ServerViewController;
import fileio.CommonIO;
import fileio.FileExistsException;
import layer.RasterLayer;

/**
 *
 * @author nickz
 */
public class MenuController extends AbstractController {

    private static final FileNameExtensionFilter JRAW, PNG, JPG;

    static {
        JRAW = new FileNameExtensionFilter("Jraw File Format (.jraw)", "jraw");
        PNG = new FileNameExtensionFilter("Portable Network Graphics (.png)", "png");
        JPG = new FileNameExtensionFilter("JPG Image (.jpg)", "jpg", "jpeg");
    }

    class QuitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //check for save
            for (int i = 0; i < model.size(); i++) {
                if (!model.getSessionModel(i).isSaved()) {
                    return;
                }
            }
            System.exit(0);
        }
    }

    class NewFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NewFileForm nff = new NewFileForm(mainview);
            NewFileFormController nffo = new NewFileFormController(nff);
            nffo.setModel(model);
            nff.setController(nffo);
            nff.setVisible(true);
        }
    }

    class OpenFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
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
    }

    class SaveFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            if (sm.isSaved()) {
                return;
            }
            if (sm.getLastPath() != null && sm.getLastPath().exists()) {
                try {
                    CommonIO.saveProprieteryFormat(sm, sm.getLastPath(), true);
                    sm.setSaved(true);
                } catch (IOException | FileExistsException ex) {
                }
            } else {
                new SaveAsFileAction().actionPerformed(e);
            }
        }

    }

    class SaveAsFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            if (sm == null) {
                return;
            }
            File f = CommonIO.showSaveDialog(sm.getLastPath(), mainview, JRAW);
            if (f != null) {
                try {
                    CommonIO.saveProprieteryFormat(sm, f, false);
                    sm.setLastPath(f);
                    sm.setSaved(true);
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (FileExistsException ex) {
                    //overwrite?
                    YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                        try {
                            CommonIO.saveProprieteryFormat(sm, f, true);
                            sm.setLastPath(f);
                            sm.setSaved(true);
                        } catch (IOException ex1) {
                            System.err.println(ex1);
                        }
                    });
                    ynd.setMessage("Overwrite file?");
                    ynd.setVisible(true);
                }
            }
        }
    }

    class ExportFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            if (sm == null) {
                return;
            }
            File f = CommonIO.showSaveDialog(mainview, PNG, JPG);
            if (f != null) {
                try {
                    CommonIO.export(sm, f, false);
                    sm.setLastPath(f);
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (FileExistsException ex) {
                    //overwrite?
                    YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                        try {
                            CommonIO.export(sm, f, true);
                            sm.setLastPath(f);
                        } catch (IOException ex1) {
                            System.err.println(ex1);
                        }
                    });
                    ynd.setMessage("Overwrite export?");
                    ynd.setVisible(true);
                }
            }
        }
    }

    class NewRasterLayerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            LayerSubstrate lv = getCurrentViewport();
            RasterLayer newLayer = (RasterLayer) new RasterLayer.Builder()
                    .name("Layer " + sm.layerCount())
                    .size(sm.size())
                    .build();
            sm.layerHierarchy.add(newLayer);
            lv.updateLayers();
            mainview.layerList.refresh();
        }
    }

    class UndoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel current = MenuController.this.getCurrentSessionModel();
            try {
                current.undoMgr.undo();
            } catch (CannotUndoException ex) {

            }
        }

    }

    class RedoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel current = MenuController.this.getCurrentSessionModel();
            try {
                current.undoMgr.redo();
            } catch (CannotRedoException ex) {

            }
        }

    }

    class BufferAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UndoManager um = getCurrentSessionModel().undoMgr;
            
        }

    }

    class ConnectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ServerView sv = new ServerView(mainview);
            ServerViewController svc = new ServerViewController(sv);
            sv.setController(svc);
            svc.setModel(model);
            sv.setVisible(true);
        }
    }

    class DisconnectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ServerSession sm = (ServerSession) getCurrentSessionModel();
                sm.getServer().disconnect();
            } catch (IOException ex) {
            }
        }

    }
}

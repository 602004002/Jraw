/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

import frontend.layerdisplay.LayerSubstrate;
import common.SessionModel;
import frontend.dialog.YesNoDialog;
import frontend.newfile.NewFileForm;
import fileio.CommonIO;
import fileio.FileExistsException;
import frontend.newfile.NewFileFormController;
import frontend.server.ServerView;
import frontend.server.ServerViewController;
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
            boolean allSaved = true;
            for (int i = 0; i < model.size(); i++) {
                allSaved &= model.getSessionModel(i).isSaved();
            }
            if (allSaved) {
                System.exit(0);
            }
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
            SessionModel sm;
            if (f != null) {
                try {
                    sm = CommonIO.readProprieteryFormat(f);
                    model.add(sm);
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
            if (sm.getLastPath().exists()) {
                
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
                    System.out.println("Saved");
                    sm.setSaved(true);
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (FileExistsException ex) {
                    //overwrite?
                    YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                        try {
                            CommonIO.saveProprieteryFormat(sm, f, true);
                            System.out.println("Overwriting...");
                            sm.setSaved(true);
                        } catch (IOException | FileExistsException ex1) {
                            System.err.println(ex1);
                        }
                    });
                    ynd.setMessage("Overwrite?");
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
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (FileExistsException ex) {
                    //overwrite?
                    YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                        try {
                            CommonIO.export(sm, f, true);
                            System.out.println("overwriting...");
                        } catch (IOException | FileExistsException ex1) {
                            System.err.println(ex1);
                        }
                    });
                    ynd.setMessage("Overwrite?");
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
}

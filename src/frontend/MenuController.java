/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.SessionModel;
import frontend.subforms.newfile.NewSessionController;
import frontend.subforms.newfile.NewFileForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import layer.LayerSettings;
import layer.RasterLayer;

/**
 *
 * @author nickz
 */
public class MenuController extends Controller {

    private static final FileNameExtensionFilter FF;

    static {
        FF = new FileNameExtensionFilter("Jraw File Format", "jraw");
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
                new NewFileForm(new NewSessionController(model, mainview))
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

    class SaveFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action");
            assert (mainview.documentTabbedPane.getTabCount()
                    == model.sessionList.size()) : "Why do the sizes differ";
            SessionModel sm = getCurrentSessionModel();
            if (sm != null) {
                File f;
                Thread jfcThread = new Thread(() -> {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setVisible(true);
                }, "JFileChooser Thread");
                Thread saveThread = new Thread(() -> {
                    try {
                        jfcThread.join();
                        System.out.println("Saving...");
                        //CommonIO.saveProprieteryFormat(sm, path);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }, "Saving Thread");
                jfcThread.start();
                saveThread.start();
            }
        }

    }

    class SaveAsFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    class NewRasterLayerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            LayerViewport lv = getCurrentViewport();
            System.out.println("action");
            sm.hierarchy.add(new RasterLayer("Layer " + sm.getLayerCount(),
                    sm, new LayerSettings()));
            lv.updateLayers();
            mainview.layerList.setSession(sm);
        }

    }

}

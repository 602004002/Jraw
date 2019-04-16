/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

import frontend.display.LayerSubstrate;
import common.DrawingType;
import common.SessionModel;
import frontend.dialog.YesNoDialog;
import frontend.newfile.NewFileForm;
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

    private MainViewController mvc;

    void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
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
            NewFileForm nff = new NewFileForm();
            NewFileFormOK nffo = new NewFileFormOK(nff);
            nff.addOkButtonActionListener(nffo);
            nff.setVisible(true);
        }
    }

    class NewFileFormOK implements ActionListener {

        private NewFileForm nff;

        public NewFileFormOK(NewFileForm nff) {
            this.nff = nff;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = nff.drawingTypeTabs.getSelectedIndex();
            Dimension size = new Dimension((int) nff.widthSpinner.getValue(),
                    (int) nff.heightSpinner.getValue());
            SessionModel.Builder sb = new SessionModel.Builder()
                    .name(nff.filenameField.getText())
                    .resolution((int) nff.resolutionSpinner.getValue())
                    .size(size);
            if (nff.backgroundColorCheckbox.isSelected()) {
                sb.backgroundColor(nff.colorButton.getBackground());
            }
            switch (selectedIndex) {
                case 0:
                    sb.drawingType(DrawingType.Illustration);
                    break;
                case 1:
                    sb.drawingType(DrawingType.Animation)
                            .framerate((int) nff.framerateSpinner.getValue());
                    break;
            }
            SessionModel s = sb.build();
            model.add(s);
            mvc.updateTabs();
            nff.dispose();
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
            SessionModel sm = getCurrentSessionModel();
            if (sm != null) {
                File f = CommonIO.showSaveDialog(mainview, JRAW);
                if (f != null) {
                    try {
                        CommonIO.saveProprieteryFormat(sm, f, false);
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileExistsException ex) {
                        //overwrite?
                        YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                            try {
                                CommonIO.saveProprieteryFormat(sm, f, true);
                                System.out.println("overwriting...");
                            } catch (IOException ex1) {
                                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex1);
                            } catch (FileExistsException ex1) {
                                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        });
                        ynd.setMessage("Overwrite?");
                        ynd.setVisible(true);
                    }
                }
            }

        }

    }

    class ExportFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel sm = getCurrentSessionModel();
            if (sm != null) {
                File f = CommonIO.showSaveDialog(mainview, PNG, JPG);
                if (f != null) {
                    try {
                        CommonIO.export(sm, f, false);
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileExistsException ex) {
                        //overwrite?
                        YesNoDialog ynd = new YesNoDialog(mainview, () -> {
                            try {
                                CommonIO.export(sm, f, true);
                                System.out.println("overwriting...");
                            } catch (IOException ex1) {
                                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex1);
                            } catch (FileExistsException ex1) {
                                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        });
                        ynd.setMessage("Overwrite?");
                        ynd.setVisible(true);
                    }
                }
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.subforms.newfile;

import common.DrawingType;
import common.SessionModel;
import frontend.MainView;
import frontend.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nickz
 */
public class FileController {
    private Model model;
    private MainView mainview;
    
    public FileController(Model model, MainView mainview) {
        this.model = model;
        this.mainview = mainview;
    }
    
     class NewFileFormOK implements ActionListener {

        private NewFileForm nff;

        public NewFileFormOK(NewFileForm nff) {
            this.nff = nff;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SessionModel s = new SessionModel(nff.filenameField.getText(),
                    (DrawingType) nff.typeBox.getSelectedItem(),
                    (int) nff.illustrationPanel.resolutionSpinner.getValue(),
                    (int) nff.illustrationPanel.widthSpinner.getValue(),
                    (int) nff.illustrationPanel.heightSpinner.getValue(),
                    mainview.layerList);
            if (nff.illustrationPanel.enableBackgroundColor.isSelected()) {
                s.initWorkspace(nff.illustrationPanel.backgroundColor.getBackground());
            } else {
                s.initWorkspace();
            }
            mainview.layerList.setSession(s);
            mainview.layerList.setSelectedIndices(new int[]{s.getLayerCount() - 1});
            model.sessionList.add(s);
            mainview.updateDocumentPane();
            nff.dispose();
        }
    }
}

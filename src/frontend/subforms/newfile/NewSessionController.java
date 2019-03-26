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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 *
 * @author nickz
 */
public class NewSessionController {

    private Model model;
    private MainView mainview;

    public NewSessionController(Model model, MainView mainview) {
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
            mainview.layerList.setSession(s);
            mainview.layerList.setSelectedIndices(new int[]{s.getLayerCount() - 1});
            model.sessionList.add(s);
            mainview.updateDocumentPane();
            nff.dispose();
        }
    }
}

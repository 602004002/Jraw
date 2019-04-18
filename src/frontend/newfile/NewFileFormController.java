/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.newfile;

import frontend.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import common.DrawingType;
import common.SessionModel;

/**
 *
 * @author nickz
 */
public class NewFileFormController extends AbstractController implements ActionListener {

    private NewFileForm nff;

    public NewFileFormController(NewFileForm nff) {
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
        nff.dispose();
    }
}

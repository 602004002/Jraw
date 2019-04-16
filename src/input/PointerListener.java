/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import common.SessionModel;
import common.User;
import frontend.display.DisplayCursor;
import frontend.display.LayerSubstrate;
import java.awt.Point;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import jwinpointer.JWinPointerReader.PointerEventListener;
import layer.DrawingLayer;

/**
 *
 * @author nickz
 */
public class PointerListener implements PointerEventListener {

    private SessionModel session;
    private LayerSubstrate substrate;
    private PointerInfo pointerInfo;

    public PointerListener(PointerInfo pi) {
        this.pointerInfo = pi;
    }

    public void setSession(SessionModel session, LayerSubstrate substrate) {
        if (this.substrate != null) {
            this.substrate.disableOverlay(); //for old substrate
        }
        this.session = session;
        this.substrate = substrate;
        if (substrate != null) {
            substrate.enableOverlay();
            //temp
            substrate.getOverlay().setCursors(Arrays.asList(new DisplayCursor(this.pointerInfo, true)));
        }
    }

    @Override
    public void pointerXYEvent(int deviceType, int pointerID, int eventType,
            boolean inverted, int x, int y, int pressure) {
        pointerInfo.setInverted(inverted);
        pointerInfo.setPressure(pressure);
        DrawingLayer drawLayer = session.layerHierarchy.get(session.getSelectedLayerIndexes()[0]);
        if (session != null && substrate != null) {
            Point p = substrate.getMousePosition(true);
            pointerInfo.setCurrentPoint(SwingUtilities.convertPoint(substrate,
                    p, drawLayer));
            pointerInfo.setOverlayPoint(SwingUtilities.convertPoint(substrate,
                    p, this.substrate.getOverlay()));
        }
        User.localUser.drawingTool().draw(pointerInfo, (DrawingLayer) drawLayer);
        pointerInfo.setPrevPoint(pointerInfo.getCurrentPoint());
    }

    @Override
    public void pointerButtonEvent(int deviceType, int pointerID,
            int eventType, boolean inverted, int buttonIndex) {
    }

    @Override
    public void pointerEvent(int deviceType, int pointerID, int eventType,
            boolean inverted) {
    }
}

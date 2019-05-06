/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import common.SessionModel;
import common.User;
import frontend.layerdisplay.DisplayCursor;
import frontend.layerdisplay.LayerOverlay;
import frontend.layerdisplay.LayerSubstrate;
import java.awt.Point;
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

    public void setCurrentSession(SessionModel session, LayerSubstrate substrate) {
        if (this.substrate != null) {
            this.substrate.disableOverlay(); //for old substrate
        }
        this.session = session;
        this.substrate = substrate;
        if (substrate != null) {
            substrate.enableOverlay();
            //temp
            substrate.getOverlay().setCursors(new DisplayCursor(this.pointerInfo, true));
        }
    }

    @Override
    public void pointerXYEvent(int deviceType, int pointerID, int eventType,
            boolean inverted, int x, int y, int pressure) {
        pointerInfo.setInverted(inverted);
        pointerInfo.setPressure(pressure);
        DrawingLayer drawLayer = session.getDrawLayer();
        if (session != null && substrate != null) {
            LayerOverlay lo = this.substrate.getOverlay();
            Point p = lo.getMousePosition(true);
            pointerInfo.setCurrentPoint(
                    SwingUtilities.convertPoint(substrate, p, drawLayer));
            pointerInfo.setOverlayPoint(p);
        }
        User.getLocalUser().drawingTool().draw(pointerInfo, session);
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

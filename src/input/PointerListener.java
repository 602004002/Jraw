/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import common.SessionModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;
import jwinpointer.JWinPointerReader.PointerEventListener;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public class PointerListener implements PointerEventListener {

    private SessionModel session;
    private PointerInfo pointerInfo;
    private DrawMethod drawMethod;

    public PointerListener(PointerInfo pointerInfo) {
        this.pointerInfo = pointerInfo;
    }

    public void setDrawMethod(DrawMethod drawMethod) {
        this.drawMethod = drawMethod;
    }

    public void setSession(SessionModel session) {
        this.session = session;
    }

    @Override
    public void pointerXYEvent(int deviceType, int pointerID, int eventType,
            boolean inverted, int x, int y, int pressure) {
        pointerInfo.deviceType = deviceType;
        pointerInfo.pointerID = pointerID;
        pointerInfo.inverted = inverted;
        pointerInfo.pressure = pressure;
        JComponent layer = session.layerHierarchy.get(session.layerCount() - 1);
        if (session != null) {
            pointerInfo.currentPoint = layer.getMousePosition();
        }
        if (drawMethod != null) {
            if (layer instanceof RasterLayer) {
                drawMethod.drawRaster(pointerInfo, (RasterLayer) layer);
            } else if (layer instanceof VectorLayer) {
                drawMethod.drawVector(pointerInfo, (VectorLayer) layer);
            }
        }
        pointerInfo.prevPoint = pointerInfo.currentPoint;
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

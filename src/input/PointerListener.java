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
import jwinpointer.JWinPointerReader.PointerEventListener;
import layer.RasterLayer;

/**
 *
 * @author nickz
 */
public class PointerListener implements PointerEventListener {

    private Point old;
    private SessionModel session;
    private PointerInfo pointerInfo;
    private DrawMethod drawMethod;

    public PointerListener() {
        this.pointerInfo = new PointerInfo();
    }

    public PointerListener(DrawMethod drawMethod) {
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
        if (session != null) {
            pointerInfo.point = session.hierarchy.get(session.getLayerCount() - 1).getMousePosition();
        }
        if (pressure > 0) {
            draw(pointerInfo);
        }
        old = pointerInfo.point;
    }

    @Override
    public void pointerButtonEvent(int deviceType, int pointerID,
            int eventType, boolean inverted, int buttonIndex) {
        System.out.println("pbutton");
    }

    @Override
    public void pointerEvent(int deviceType, int pointerID, int eventType,
            boolean inverted) {

    }

    private void draw(final PointerInfo pi) {
        int[] indexes = session.getSelectedLayerIndexes();
        RasterLayer get;
        if (indexes.length != 0) {
            get = (RasterLayer) session.hierarchy.get(indexes[0]);
        } else {
            return;
        }
        if (get != null && pi.point != null && old != null && get.isVisible()) {
            //dirty code
            Graphics2D g2d = (Graphics2D) get.getImg().createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(pi.pressure / 200));
            g2d.drawLine(old.x, old.y, pi.point.x, pi.point.y);
            //g2d.fillRect(p.x, p.y, 10, 2);
            g2d.dispose();
            get.repaint();
        }
    }
}

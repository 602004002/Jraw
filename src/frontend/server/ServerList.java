/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.server;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import networkio.ServerReply;

/**
 *
 * @author nickz
 */
public class ServerList extends JList {

    public ServerList() {
        this.setCellRenderer(new CellRenderer());
    }

    private class CellRenderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            ServerReply sr = (ServerReply) value;
            ServerListCell slc = new ServerListCell(sr);
            if (isSelected) {
                slc.setBackground(Color.lightGray);
            } else {
                slc.setBackground(Color.white);
            }
            return slc;
        }

    }

}

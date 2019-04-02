/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerlist;

import common.SessionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

/**
 *
 * @author nickz
 */
public class LayerList extends javax.swing.JPanel {

    private static final ImageIcon PEN_ICON;
    private static final ImageIcon CHECKMARK;

    static {
        PEN_ICON = new ImageIcon(
                LayerList.class.getResource("/frontend/layerlist/pen.png"));
        CHECKMARK = new ImageIcon(
                LayerList.class.getResource("/frontend/layerlist/checkmark.png"));
    }

    private SessionModel session;
    private int[] selectedIndexes;
    private LayerListCell[] cells;

    public LayerList() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        layersPane = new javax.swing.JPanel();

        jLabel3.setText("Selected");

        setMinimumSize(new java.awt.Dimension(184, 0));
        setPreferredSize(new java.awt.Dimension(216, 300));

        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        layersPane.setMinimumSize(new java.awt.Dimension(172, 0));

        javax.swing.GroupLayout layersPaneLayout = new javax.swing.GroupLayout(layersPane);
        layersPane.setLayout(layersPaneLayout);
        layersPaneLayout.setHorizontalGroup(
            layersPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        layersPaneLayout.setVerticalGroup(
            layersPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(layersPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        this.setName("layerList");
    }

    public void setSession(SessionModel session) {
        this.session = session;
        this.updateCells();
        if (session != null) {
            setSelectedIndices(session.getSelectedLayerIndexes());
        }
        this.revalidate();
        this.repaint();
    }

    public void updateCells() {
        this.layersPane.removeAll();
        this.cells = null;
        if (this.session == null) {
            return;
        }
        int size = this.session.hierarchy.size();
        this.cells = new LayerListCell[size];
        GroupLayout layersPaneLayout = new GroupLayout(this.layersPane);
        this.layersPane.setLayout(layersPaneLayout);
        ParallelGroup horizontal = layersPaneLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING, true);
        SequentialGroup vSeq = layersPaneLayout.createSequentialGroup();
        for (int i = size - 1; i >= 0; i--) {
            JComponent layer = this.session.hierarchy.get(i);
            LayerListCell llc = new LayerListCell(layer, this);
            llc.visBtn.setSelected(layer.isVisible());
            horizontal = horizontal.addComponent(llc, GroupLayout.DEFAULT_SIZE,
                    layersPane.getWidth(), layersPane.getWidth());
            vSeq = vSeq.addComponent(llc, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
            this.cells[i] = llc;
        }
        layersPaneLayout.linkSize(SwingConstants.HORIZONTAL,
                this.layersPane.getComponents());
        layersPaneLayout.setHorizontalGroup(horizontal);
        layersPaneLayout.setVerticalGroup(vSeq);
    }

    int indexOf(LayerListCell llc) {
        int index = -1;
        //find where index in this.cells
        for (int i = 0; i < this.cells.length; i++) {
            if (llc == this.cells[i]) {
                index = i;
            }
        }
        return index;
    }

    void appendSelfToSelectedIndexes(LayerListCell llc) {
        int index = indexOf(llc);
        assert (index >= 0) : "Why is was it not found?";
        if (this.selectedIndexes != null) {
            for (int id : this.selectedIndexes) {//scan array if already exists
                if (id == index) {
                    return;
                }
            }
        } else {
            this.selectedIndexes = new int[0];
        }
        int[] resize = this.selectedIndexes;
        this.selectedIndexes = new int[resize.length + 1];
        System.arraycopy(resize, 0, this.selectedIndexes, 0, resize.length);
        this.selectedIndexes[resize.length] = index;
        this.session.setSelectedLayerIndices(this.selectedIndexes);
    }

    void removeSelfFromSelectedIndexes(LayerListCell llc) {
        int indexInCells = indexOf(llc);
        if (indexInCells < 0 || this.selectedIndexes == null) {
            return;
        }
        int indexInSelectedIndexes = -1;
        for (int i = 0; i < this.selectedIndexes.length; i++) {
            if (indexInCells == this.selectedIndexes[i]) {
                indexInSelectedIndexes = i;
            }
        }
        if (indexInSelectedIndexes < 0) {
            return;
        }
        int[] old = this.selectedIndexes;
        this.selectedIndexes = new int[old.length - 1];
        System.arraycopy(old, 0, this.selectedIndexes, 0, indexInSelectedIndexes);
        System.arraycopy(old, indexInSelectedIndexes + 1,
                this.selectedIndexes, indexInSelectedIndexes,
                this.selectedIndexes.length - indexInSelectedIndexes);
        this.session.setSelectedLayerIndices(this.selectedIndexes);
    }

    public void setSelectedIndices(int[] indexes) {
        if (indexes.length == 0) {
            return;
        }
        this.clearSelection();
        for (int i : indexes) {
            cells[i].setSelected(true);
        }
        session.setSelectedLayerIndices(this.selectedIndexes);
    }

    public void clearSelection() {
        this.selectedIndexes = null;
        for (LayerListCell llc : cells) {
            llc.setSelected(false);
        }
    }

    public int[] getSelectedIndexes() {
        return this.selectedIndexes;
    }

    public int getSelectedIndex() {
        return this.selectedIndexes[0];
    }

    LayerListCell getCell(int i) {
        return this.cells[i];
    }

    void updateIcons() {
        if (this.selectedIndexes != null && this.selectedIndexes.length > 0) {
            for (LayerListCell llc : this.cells) {
                llc.selBtn.setIcon(null);
            }
            for (int i : this.selectedIndexes) {
                this.cells[i].selBtn.setIcon(CHECKMARK);
            }
            this.cells[this.selectedIndexes[0]].selBtn.setIcon(LayerList.PEN_ICON);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel layersPane;
    // End of variables declaration//GEN-END:variables
}

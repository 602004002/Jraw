/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.layerlist;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import layer.DrawingLayer;

/**
 *
 * @author nickz
 */
public class LayerListCell extends javax.swing.JPanel {

    private static final ImageIcon EYEOPEN, EYECLOSE;

    static {
        Class cl = LayerListCell.class;
        EYEOPEN = new ImageIcon(cl.getResource("/frontend/layerlist/eyeOpen.png"));
        EYECLOSE = new ImageIcon(cl.getResource("/frontend/layerlist/eyeClosed.png"));
    }

    private DrawingLayer layer;
    private Color defaultColor;
    private boolean selected;
    private final LayerList layerList;

    public LayerListCell(DrawingLayer layer, LayerList layerList) {
        this.layer = layer;
        this.layerList = layerList;
        this.defaultColor = this.getBackground();
        this.selected = false;
        this.setName(layer.getName());
        initComponents();
        this.visBtn.setSelected(true);
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

        jLayeredPane1 = new javax.swing.JLayeredPane();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMinimumSize(new java.awt.Dimension(172, 42));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        visBtn.setSelected(true);
        visBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        visBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        visBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visBtnActionPerformed(evt);
            }
        });

        selBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        selBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        selBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selBtnActionPerformed(evt);
            }
        });

        layerNameField.setText(this.layer.getName());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layerNameField)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(layerNameField)
                    .addComponent(visBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        this.layerNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                layer.setName(layerNameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                layer.setName(layerNameField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changed?");
            }
        });
        this.visBtn.setIcon(EYECLOSE);
        this.visBtn.setSelectedIcon(EYEOPEN);
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        if (!selected) {
            this.setBackground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        if (!selected) {
            this.setBackground(defaultColor);
        }
    }//GEN-LAST:event_formMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if (selected && this.layerList.getSelectedIndexes().length == 1) {
            return;
        }
        if (!evt.isControlDown()) {
            this.layerList.clearSelection();
        }
        this.setSelected(!selected);
    }//GEN-LAST:event_formMouseClicked

    private void selBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selBtnActionPerformed
        // TODO add your handling code here:
        if (selected && this.layerList.getSelectedIndexes().length == 1) {
            this.selBtn.setSelected(true);
            return;
        }
        this.setSelected(this.selBtn.isSelected());
    }//GEN-LAST:event_selBtnActionPerformed

    private void visBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visBtnActionPerformed
        // TODO add your handling code here:
        this.layer.setVisible(this.visBtn.isSelected());
    }//GEN-LAST:event_visBtnActionPerformed

    boolean isSelected() {
        return this.selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
        this.selBtn.setSelected(selected);
        if (selected) {
            this.setBackground(Color.GRAY);
            this.layerList.appendSelfToSelectedIndexes(this);
        } else {
            this.setBackground(defaultColor);
            this.layerList.removeSelfFromSelectedIndexes(this);
        }
        this.layerList.updateIcons();
        this.revalidate();
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    public final javax.swing.JTextField layerNameField = new javax.swing.JTextField();
    public final javax.swing.JToggleButton selBtn = new javax.swing.JToggleButton();
    public final javax.swing.JToggleButton visBtn = new javax.swing.JToggleButton();
    // End of variables declaration//GEN-END:variables
}

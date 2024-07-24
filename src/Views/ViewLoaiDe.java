/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Views;

import DAO.LoaiDeService;
import Model.LoaiDe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ViewLoaiDe extends javax.swing.JDialog {

    DefaultTableModel dtm = new DefaultTableModel();
    List<LoaiDe> list = new ArrayList<>();
    LoaiDeService loaiDeService = new LoaiDeService();

    public ViewLoaiDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadDataTable();

    }

    public void loadDataTable() {
        list = loaiDeService.findAll();
        dtm = (DefaultTableModel) tbDanhSachLoaiDe.getModel();
        dtm.setRowCount(0);
        for (LoaiDe loaiDe : list) {
            dtm.addRow(new Object[]{
                loaiDe.getId(),
                loaiDe.getTenLoaiDe(),
                loaiDe.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public LoaiDe showIndex(int index) {
        LoaiDe loaiDe = list.get(index);
        lbID.setText(String.valueOf(loaiDe.getId()));
        txtLoaiDe.setText(loaiDe.getTenLoaiDe());
        int trangThai = loaiDe.getTrangThai();
        if (trangThai == 1) {
            rdCH.setSelected(true);
        } else {
            rdHH.setSelected(true);
        }
        return loaiDe;
    }

    public LoaiDe getDataForm() {
        String ten = txtLoaiDe.getText();
        int trangThai = -1;
        if (rdCH.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new LoaiDe(null, ten, trangThai);
    }

    public LoaiDe getDataUpdate() {
        int id = Integer.valueOf(lbID.getText());
        String ten = txtLoaiDe.getText();
        int trangThai = -1;
        if (rdCH.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new LoaiDe(id, ten, trangThai);
    }

    public void resetForm() {
        txtLoaiDe.setText("");
        lbID.setText("");
        buttonGroup1.clearSelection();
    }
    public boolean checktTrong(){
        if (txtLoaiDe.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên loại đế");
            txtLoaiDe.requestFocus();
            return false;
        }
        if (!rdCH.isSelected() && !rdHH.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtLoaiDe = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSachLoaiDe = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        rdHH = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rdCH = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Thêm Loại Đế"));

        jLabel1.setText("ID: ");

        lbID.setText("...");

        jLabel3.setText("Loại đế");

        tbDanhSachLoaiDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Loại đế", "Trạng thái"
            }
        ));
        tbDanhSachLoaiDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachLoaiDeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhSachLoaiDe);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdHH);
        rdHH.setText("Hết hàng");

        jLabel4.setText("Trạng thái: ");

        buttonGroup1.add(rdCH);
        rdCH.setText("Còn hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(rdCH, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdHH, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdCH)
                    .addComponent(rdHH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(54, 54, 54)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbDanhSachLoaiDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachLoaiDeMouseClicked
        int index = tbDanhSachLoaiDe.getSelectedRow();
        showIndex(index);
    }//GEN-LAST:event_tbDanhSachLoaiDeMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if(checktTrong()){
            JOptionPane.showMessageDialog(this, loaiDeService.addLoaiDe(getDataForm()));
            loadDataTable();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int row = tbDanhSachLoaiDe.getSelectedRow();
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        } else if (c == JOptionPane.OK_OPTION) {
            if(checktTrong()){
            JOptionPane.showMessageDialog(this, loaiDeService.updateLoaiDe(getDataUpdate()));
            loadDataTable();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        resetForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewLoaiDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLoaiDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLoaiDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLoaiDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewLoaiDe dialog = new ViewLoaiDe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbID;
    private javax.swing.JRadioButton rdCH;
    private javax.swing.JRadioButton rdHH;
    private javax.swing.JTable tbDanhSachLoaiDe;
    private javax.swing.JTextField txtLoaiDe;
    // End of variables declaration//GEN-END:variables
}

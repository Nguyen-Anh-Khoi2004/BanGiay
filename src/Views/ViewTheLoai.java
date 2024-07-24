/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Views;

import DAO.TheLoaiService;
import Model.TheLoai;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ViewTheLoai extends javax.swing.JDialog {

    DefaultTableModel dtm = new DefaultTableModel();
    List<TheLoai> list = new ArrayList<>();
    TheLoaiService theLoaiService = new TheLoaiService();

    public ViewTheLoai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadDataTable();
    }

    public void loadDataTable() {
        dtm = (DefaultTableModel) tbDanhSachTheLoai.getModel();
        list = theLoaiService.findAll();
        dtm.setRowCount(0);
        for (TheLoai theLoai : list) {
            dtm.addRow(new Object[]{
                theLoai.getId(),
                theLoai.getTenTheLoai(),
                theLoai.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public TheLoai getIndex(int index) {
        TheLoai theLoai = list.get(index);
        lbID.setText(String.valueOf(theLoai.getId()));
        txtTheLoai.setText(theLoai.getTenTheLoai());
        int trangThai = theLoai.getTrangThai();
        if (trangThai == 1) {
            rdCH.setSelected(true);
        } else {
            rdHH.setSelected(true);
        }
        return theLoai;
    }

    public TheLoai getDataForm() {
        String ten = txtTheLoai.getText();
        int trangThai = -1;
        if (rdCH.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new Model.TheLoai(null, ten, trangThai);
    }

    public TheLoai getDataUpdate() {
        int id = Integer.valueOf(lbID.getText());
        String ten = txtTheLoai.getText();
        int trangThai = -1;
        if (rdCH.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new TheLoai(id, ten, trangThai);
    }

    public void resetForm() {
        txtTheLoai.setText("");
        lbID.setText("");
        buttonGroup1.clearSelection();
    }

    public boolean checktTrong() {
        if (txtTheLoai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên thể loại");
            txtTheLoai.requestFocus();
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
        txtTheLoai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSachTheLoai = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        rdHH = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rdCH = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Thêm Thể Loại"));

        jLabel1.setText("ID: ");

        lbID.setText("...");

        jLabel3.setText("Thể loại:");

        tbDanhSachTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Thể loại", "Trạng thái"
            }
        ));
        tbDanhSachTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhSachTheLoai);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rdCH, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdHH, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(txtTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdCH)
                    .addComponent(rdHH))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(54, 54, 54)
                        .addComponent(btnSua)
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

    private void tbDanhSachTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachTheLoaiMouseClicked
        int index = tbDanhSachTheLoai.getSelectedRow();
        getIndex(index);
    }//GEN-LAST:event_tbDanhSachTheLoaiMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if (checktTrong()) {
                TheLoai newTheLoai = getDataForm();
                JOptionPane.showMessageDialog(this, theLoaiService.addTheLoai(newTheLoai));
                loadDataTable();
                // Đóng cửa sổ sau khi thêm thể loại mới
                dispose();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tbDanhSachTheLoai.getSelectedRow();
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        } else if (c == JOptionPane.OK_OPTION) {
            if(checktTrong()){
            JOptionPane.showMessageDialog(this, theLoaiService.updateTheLoai(getDataUpdate()));
            loadDataTable();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(ViewTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewTheLoai dialog = new ViewTheLoai(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbID;
    private javax.swing.JRadioButton rdCH;
    private javax.swing.JRadioButton rdHH;
    private javax.swing.JTable tbDanhSachTheLoai;
    private javax.swing.JTextField txtTheLoai;
    // End of variables declaration//GEN-END:variables
}

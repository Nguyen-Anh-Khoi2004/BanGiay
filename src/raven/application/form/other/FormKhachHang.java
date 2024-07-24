package raven.application.form.other;

import DAO.KhachHangDAO;
import Model.KhachHang;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Raven
 */
public class FormKhachHang extends javax.swing.JPanel {

    private KhachHangDAO khd = new KhachHangDAO();
    private DefaultTableModel dtm = new DefaultTableModel();
    private int index = -1;

    public FormKhachHang(String text) {
        initComponents();
        KHPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        this.fillTable(khd.getAll());
    }

    private void fillTable(ArrayList<KhachHang> all) {
        dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.setRowCount(0);
        for (KhachHang kh : all) {
            dtm.addRow(new Object[]{
                kh.getId(),
                kh.getHoTen(),
                kh.getGioiTinh() == 1 ? "Nam" : "Nữ",
                kh.getSoDienThoai(),
                kh.getDiaChi()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        KHPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtSoDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();

        tblKhachHang.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Giới Tính", "Số Điện Thoại", "Địa Chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Khách Hàng"));

        jLabel1.setText("Họ Tên");

        jLabel2.setText("Giới Tính");

        jLabel3.setText("Số ĐT");

        jLabel4.setText("Địa Chỉ");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(rdNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChi)))
                .addGap(37, 37, 37)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnSearch)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KHPanelLayout = new javax.swing.GroupLayout(KHPanel);
        KHPanel.setLayout(KHPanelLayout);
        KHPanelLayout.setHorizontalGroup(
            KHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KHPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnThem)
                .addGap(32, 32, 32)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnReset))
            .addGroup(KHPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(KHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        KHPanelLayout.setVerticalGroup(
            KHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KHPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(KHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(KHPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(KHPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        index = tblKhachHang.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtSoDT.setText("");
        rdNam.setSelected(true);
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String hoTen = txtHoTen.getText().trim();
        String soDTText = txtSoDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();

        if (hoTen.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        int soDT = 0;
        if (!soDTText.isEmpty()) {
            try {
                soDT = Integer.parseInt(soDTText);
                // Kiểm tra số điện thoại không được âm và phải có ít nhất 9 và tối đa 10 chữ số
                if (soDT < 0 || soDTText.length() < 9 || soDTText.length() > 10) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
                return;
            }
        }

        KhachHang kh = new KhachHang(0, hoTen, rdNam.isSelected() ? 1 : 0, diaChi, soDT);
        int result = khd.themKH(kh);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công.");
            fillTable(khd.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại.");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        String keyword = jTextField1.getText().toLowerCase();
        search(keyword);

    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để sửa.");
            return;
        }

        int id = (int) tblKhachHang.getValueAt(selectedRow, 0);
        String hoTen = txtHoTen.getText().trim();
        String soDTText = txtSoDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();

        if (hoTen.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        int soDT = 0;
        if (!soDTText.isEmpty()) {
            try {
                soDT = Integer.parseInt(soDTText);
                // Kiểm tra số điện thoại không được âm và phải có ít nhất 9 và tối đa 10 chữ số
                if (soDT < 0 || soDTText.length() < 9 || soDTText.length() > 10) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
                return;
            }
        }

        KhachHang kh = new KhachHang(id, hoTen, rdNam.isSelected() ? 1 : 0, diaChi, soDT);
        int result = khd.updateKH(kh);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công.");
            fillTable(khd.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thất bại.");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void showData(int index) {
        txtHoTen.setText(tblKhachHang.getValueAt(index, 1).toString());
        txtSoDT.setText(tblKhachHang.getValueAt(index, 3).toString());
        txtDiaChi.setText(tblKhachHang.getValueAt(index, 4).toString());
        String gioiTinh = tblKhachHang.getValueAt(index, 2).toString();
        if (gioiTinh.equals("Nam")) {
            rdNam.setSelected(true);
            rdNu.setSelected(false);
        } else if (gioiTinh.equals("Nữ")) {
            rdNam.setSelected(false);
            rdNu.setSelected(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel KHPanel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDT;
    // End of variables declaration//GEN-END:variables

    private void search(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblKhachHang.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String name = (String) entry.getValue(1);
                return name.toLowerCase().contains(keyword);
            }
        };

        sorter.setRowFilter(filter);
    }

}

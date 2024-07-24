package raven.application.form.other;

import DAO.NhanVienDAO;
import Model.KhachHang;
import Model.NhanVien;
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
public class FormNhanVien extends javax.swing.JPanel {

    private NhanVienDAO nvd = new NhanVienDAO();
    private DefaultTableModel dtm = new DefaultTableModel();
    private int index = -1;

    public FormNhanVien(String text) {
        initComponents();
        NVPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        this.fillTable(nvd.getAll());
    }

    private void fillTable(ArrayList<NhanVien> all) {
        dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.setRowCount(0);
        for (NhanVien nv : all) {
            dtm.addRow(new Object[]{
                nv.getId(),
                nv.getHoTen(),
                nv.getGioiTinh() == 1 ? "Nam" : "Nữ",
                nv.getSoDt(),
                nv.getEmail(),
                nv.getDiaChi(),
                nv.getTrangThai()
                == 1 ? "Đang Làm" : "Đã Nghỉ Việc"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        NVPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtSoDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdDangLam = new javax.swing.JRadioButton();
        rdDaNghiViec = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDangLam = new javax.swing.JButton();
        btnDaNghi = new javax.swing.JButton();
        btnTatCa = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Giới Tính", "Số Điện Thoại", "Email", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Nhân Viên"));

        jLabel1.setText("Họ Tên");

        jLabel2.setText("Giới Tính");

        jLabel3.setText("Số Điện Thoại");

        jLabel4.setText("Email");

        jLabel5.setText("Địa Chỉ");

        jLabel6.setText("Trạng Thái");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        buttonGroup2.add(rdDangLam);
        rdDangLam.setSelected(true);
        rdDangLam.setText("Đang Làm");

        buttonGroup2.add(rdDaNghiViec);
        rdDaNghiViec.setText("Đã Nghỉ Việc");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        btnDangLam.setText("Đang Làm");
        btnDangLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangLamMouseClicked(evt);
            }
        });

        btnDaNghi.setText("Đã Nghỉ Việc");
        btnDaNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDaNghiMouseClicked(evt);
            }
        });

        btnTatCa.setText("Tất cả");
        btnTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTatCaMouseClicked(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdDaNghiViec))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(61, 61, 61)
                                .addComponent(btnSua)
                                .addGap(77, 77, 77)
                                .addComponent(btnReset)
                                .addGap(66, 66, 66)
                                .addComponent(btnDangLam))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch)))
                        .addGap(61, 61, 61)
                        .addComponent(btnDaNghi)
                        .addGap(55, 55, 55)
                        .addComponent(btnTatCa)))
                .addContainerGap(214, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtHoTen, txtSoDT});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(rdNam)
                    .addComponent(rdNu)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdDangLam)
                    .addComponent(rdDaNghiViec))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnReset)
                    .addComponent(btnDangLam)
                    .addComponent(btnDaNghi)
                    .addComponent(btnTatCa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout NVPanelLayout = new javax.swing.GroupLayout(NVPanel);
        NVPanel.setLayout(NVPanelLayout);
        NVPanelLayout.setHorizontalGroup(
            NVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NVPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        NVPanelLayout.setVerticalGroup(
            NVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NVPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NVPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NVPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        index = tblNhanVien.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtSoDT.setText("");
        txtEmail.setText("");
        rdDangLam.setSelected(true);
        rdNam.setSelected(true);
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        String keyword = jTextField1.getText().toLowerCase();
        search(keyword);
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnDangLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangLamMouseClicked
        // TODO add your handling code here:
        fillTable(nvd.getDangLam());
    }//GEN-LAST:event_btnDangLamMouseClicked

    private void btnDaNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDaNghiMouseClicked
        // TODO add your handling code here:
        fillTable(nvd.getDaNghiViec());
    }//GEN-LAST:event_btnDaNghiMouseClicked

    private void btnTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTatCaMouseClicked
        // TODO add your handling code here:
        fillTable(nvd.getAll());
    }//GEN-LAST:event_btnTatCaMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String hoTen = txtHoTen.getText().trim();
        String soDTText = txtSoDT.getText().trim();
        String email = txtEmail.getText().trim();
        String diaChi = txtDiaChi.getText().trim();

        // Kiểm tra xem các trường thông tin có được nhập đầy đủ không
        if (hoTen.isEmpty() || soDTText.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Kiểm tra số điện thoại
        int soDT = 0;
        try {
            soDT = Integer.parseInt(soDTText);
            // Kiểm tra số điện thoại không được âm và phải có từ 9 đến 10 chữ số
            if (soDT < 0 || soDTText.length() < 9 || soDTText.length() > 10) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
            return;
        }

        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
            return;
        }

        // Tạo đối tượng NhanVien từ thông tin lấy được
        NhanVien nv = new NhanVien(0, hoTen, rdNam.isSelected() ? 1 : 0, soDT, email, diaChi, 1);

        // Thêm nhân viên vào cơ sở dữ liệu
        int result = nvd.themNV(nv);

        // Kiểm tra kết quả và thông báo cho người dùng
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.");
            fillTable(nvd.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại.");
        }
    }

// Phương thức kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }//GEN-LAST:event_btnThemActionPerformed
    private void showData(int index) {
        txtHoTen.setText(tblNhanVien.getValueAt(index, 1).toString());
        txtSoDT.setText(tblNhanVien.getValueAt(index, 3).toString());
        txtDiaChi.setText(tblNhanVien.getValueAt(index, 5).toString());
        txtEmail.setText(tblNhanVien.getValueAt(index, 4).toString());
        String gioiTinh = tblNhanVien.getValueAt(index, 2).toString();
        if (gioiTinh.equals("Nam")) {
            rdNam.setSelected(true);
            rdNu.setSelected(false);
        } else if (gioiTinh.equals("Nữ")) {
            rdNam.setSelected(false);
            rdNu.setSelected(true);
        }
        String trangThai = tblNhanVien.getValueAt(index, 6).toString();
        if (trangThai.equals("Đang Làm")) {
            rdDangLam.setSelected(true);
            rdDaNghiViec.setSelected(false);
        } else if (trangThai.equals("Đã Nghỉ Việc")) {
            rdDangLam.setSelected(false);
            rdDaNghiViec.setSelected(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NVPanel;
    private javax.swing.JButton btnDaNghi;
    private javax.swing.JButton btnDangLam;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTatCa;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rdDaNghiViec;
    private javax.swing.JRadioButton rdDangLam;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDT;
    // End of variables declaration//GEN-END:variables

    private void search(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblNhanVien.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String name = (String) entry.getValue(1);
                return name.toLowerCase().contains(keyword);
            }
        };

        sorter.setRowFilter(filter);
    }

}

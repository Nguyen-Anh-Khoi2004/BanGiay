package raven.application.form.other;

import DAO.HoaDonChiTietService;
import DAO.HoaDonService;
import Model.HoaDon;
import Model.HoaDonCT;
import com.formdev.flatlaf.FlatClientProperties;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raven
 */
public class FormHoaDon extends javax.swing.JPanel {

    DefaultTableModel modelHoaDon = new DefaultTableModel();
    DefaultTableModel modelHoaDonChiTiet = new DefaultTableModel();
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    List<HoaDon> listHoaDon = new ArrayList<>();
    List<HoaDonCT> listHDCT = new ArrayList<>();
    int idHoaDon;

    public FormHoaDon(String text) {
        initComponents();
        pnHoaDon.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        modelHoaDon = (DefaultTableModel) tbDanhSachHoaDon.getModel();
        modelHoaDonChiTiet = (DefaultTableModel) tbDanhSachHoaDonChiTiet.getModel();
        loadTableHoaDon(listHoaDon = hoaDonService.findAll());
    }

    public void loadTableHoaDon(List<HoaDon> listHoaDon) {
        modelHoaDon.setRowCount(0);
        for (HoaDon hoaDon : listHoaDon) {
            modelHoaDon.addRow(new Object[]{
                hoaDon.getIdHoaDon(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getPhuongThucThanhToan() == 1 ? "Chuyển khoản" : "Tiền mặt",
                hoaDon.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

//    private void timKiemTheoNgay() {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            Date ngayBatDau = sdf.parse(txtNgay.getText());
//            List<HoaDon> danhSachHoaDon = hoaDonService.timKiemTheoNgay((java.sql.Date) ngayBatDau);
//            loadTableHoaDon(danhSachHoaDon);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    private void timKiemTheoNgay() {
        try {
            java.util.Date utilDate = dcTimKiemNgay.getDate();
            if (utilDate == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày!");
                return;
            }
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            List<HoaDon> danhSachHoaDon = hoaDonService.timKiemTheoNgay(sqlDate);
            loadTableHoaDon(danhSachHoaDon);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
        }
    }

    public HoaDon showIndex(int index) {
        HoaDon hd = listHoaDon.get(index);
        txtTenNV.setText(hd.getTenNV());
        txtTenKH.setText(hd.getTenKH());
        txtTongTien.setText(hd.getTongTien() + "");
        Date ngay = hd.getNgayTao();
        dcNgay.setDate(ngay);
        return hd;
    }

    public void loadTableHoaDonCT(List<HoaDonCT> listHDCT) {
        modelHoaDonChiTiet.setRowCount(0);
        for (HoaDonCT hoaDonCT : listHDCT) {
            modelHoaDonChiTiet.addRow(new Object[]{
                hoaDonCT.getIdHoaDon(),
                hoaDonCT.getIdSPCT(),
                hoaDonCT.getTenSanPham(),
                hoaDonCT.getSoLuong(),
                hoaDonCT.getDonGia(),});
        }
    }
    public void resetForm() {
        dcNgay.setDate(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        dcNgay = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDanhSachHoaDon = new javax.swing.JTable();
        dcTimKiemNgay = new com.toedter.calendar.JDateChooser();
        btnTimKiem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSachHoaDonChiTiet = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hoá đơn"));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán" }));
        cbbTrangThai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbTrangThaiMouseClicked(evt);
            }
        });
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel1.setText("ID");

        jLabel2.setText("Ngày tạo");

        jLabel3.setText("Tổng tiền");

        jLabel4.setText("Tên nhân viên");

        jLabel5.setText("Tên khách hàng");

        lbID.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV)
                    .addComponent(txtTenKH)
                    .addComponent(dcNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(txtTongTien)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbID))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tbDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Ngày tạo", "Tổng tiền", "Phương thức thanh toán", "Trạng thái"
            }
        ));
        tbDanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDanhSachHoaDon);
        if (tbDanhSachHoaDon.getColumnModel().getColumnCount() > 0) {
            tbDanhSachHoaDon.getColumnModel().getColumn(1).setResizable(false);
        }

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dcTimKiemNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem)
                        .addGap(22, 22, 22)
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem)
                            .addComponent(jButton1))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(dcTimKiemNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn chi tiết"));

        tbDanhSachHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID hoá đơn", "ID SPCT", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tbDanhSachHoaDonChiTiet);
        if (tbDanhSachHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tbDanhSachHoaDonChiTiet.getColumnModel().getColumn(1).setResizable(false);
            tbDanhSachHoaDonChiTiet.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbDanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachHoaDonMouseClicked
        int row = tbDanhSachHoaDon.getSelectedRow();
        idHoaDon = listHoaDon.get(row).getIdHoaDon();
        showIndex(row);
        loadTableHoaDonCT(listHDCT = hoaDonChiTietService.loadListHDCT(idHoaDon));
    }//GEN-LAST:event_tbDanhSachHoaDonMouseClicked

    private void cbbTrangThaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbTrangThaiMouseClicked

    }//GEN-LAST:event_cbbTrangThaiMouseClicked

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        int tt = cbbTrangThai.getSelectedIndex();
        if (tt == 0) {
            listHoaDon = hoaDonService.findAll().stream()
                    .filter(hd -> hd.getTrangThai() == 1)
                    .collect(Collectors.toList());
            loadTableHoaDon(listHoaDon);
        } else if (tt == 1) {
            listHoaDon = hoaDonService.findAll().stream()
                    .filter(hd -> hd.getTrangThai() == 0)
                    .collect(Collectors.toList());
            loadTableHoaDon(listHoaDon);
        }
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        timKiemTheoNgay();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadTableHoaDonCT(listHDCT);
        resetForm();
        loadTableHoaDon(listHoaDon);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private com.toedter.calendar.JDateChooser dcNgay;
    private com.toedter.calendar.JDateChooser dcTimKiemNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbID;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JTable tbDanhSachHoaDon;
    private javax.swing.JTable tbDanhSachHoaDonChiTiet;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}

package raven.application.form.other;

import DAO.KhuyenMaiDAO;
import Model.KhuyenMai;
import Model.SanPhamChiTiet;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raven
 */
public class FormKhuyenMai extends javax.swing.JPanel {

    DefaultTableModel dtm = new DefaultTableModel();
    KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    List<KhuyenMai> listKM = new ArrayList<>();
    List<SanPhamChiTiet> listSP = new ArrayList<>();

    public FormKhuyenMai(String text) {
        initComponents();
        pnKhuyenMai.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        loadTable(listKM = kmDAO.findAll());
        loadTableSP(listSP = kmDAO.findAllSP());
    }

    public void loadTable(List<KhuyenMai> list) {
        dtm = (DefaultTableModel) tbKhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : list) {
            dtm.addRow(new Object[]{
                km.getId(),
                km.getTenKM(),
                km.getLoai(),
                km.getGiaTri(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getTrangThai() == 1 ? "Đang áp dụng" : "hết hạn"
            });
        }
    }

    public KhuyenMai getDataForm() {
        String ten = txtTenKM.getText();
        String loai = txtLoai.getText();
        float gia = Float.parseFloat(txtGia.getText());
        java.util.Date ngayBDUtil = txtNgayBD.getDate();
        java.sql.Date ngayBD = new java.sql.Date(ngayBDUtil.getTime());
        java.util.Date ngayKTUtil = txtNgayKT.getDate();
        java.sql.Date ngayKT = new java.sql.Date(ngayKTUtil.getTime());
        int trangThai = -1;
        if (rdAD.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new Model.KhuyenMai(null, ten, loai, gia, ngayBD, ngayKT, trangThai);
    }

    public KhuyenMai getDataUpdate() {
        int id = Integer.parseInt(txtIdKM.getText());
        String ten = txtTenKM.getText();
        String loai = txtLoai.getText();
        float gia = Float.parseFloat(txtGia.getText());
        java.util.Date ngayBDUtil = txtNgayBD.getDate();
        java.sql.Date ngayBD = new java.sql.Date(ngayBDUtil.getTime());
        java.util.Date ngayKTUtil = txtNgayKT.getDate();
        java.sql.Date ngayKT = new java.sql.Date(ngayKTUtil.getTime());
        int trangThai = -1;
        if (rdAD.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 0;
        }
        return new Model.KhuyenMai(id, ten, loai, gia, ngayBD, ngayKT, trangThai);
    }

    private void detail1(int index) {
        KhuyenMai km = listKM.get(index);
        txtIdKM.setText(km.getId() + "");
        txtTenKM.setText(km.getTenKM());
        txtGia.setText(km.getGiaTri() + "");
        txtLoai.setText(km.getLoai());
        txtNgayBD.setDate(km.getNgayBatDau());
        txtNgayKT.setDate(km.getNgayKetThuc());
        int TT = km.getTrangThai();
        if (TT == 1) {
            rdAD.setSelected(true);
            rdHH.setSelected(false);
        } else if (TT == 0) {
            rdHH.setSelected(true);
            rdAD.setSelected(false);
        } else {
            rdAD.setSelected(false);
            rdHH.setSelected(false);
        }
    }

    public void resetForm() {
        txtTenKM.setText("");
        txtLoai.setText("");
        txtGia.setText("");
        txtTenKM.setText("");
        txtNgayKT.setDate(null);
        txtNgayBD.setDate(null);
        txtIdKM.setText("");
        txtSearch.setText("");

    }

    public void search() {
        String ten = txtSearch.getText();
        dtm = (DefaultTableModel) tbKhuyenMai.getModel();
        try {
            List<KhuyenMai> listKM = kmDAO.getByTen(ten);
            dtm.setRowCount(0);
            for (KhuyenMai km : listKM) {
                dtm.addRow(new Object[]{km.getId(),
                    km.getTenKM(),
                    km.getLoai(),
                    km.getGiaTri(),
                    km.getNgayBatDau(),
                    km.getNgayKetThuc(),
                    km.getTrangThai() == 1 ? "Đang áp dụng" : "Hết hạn"
                });
            }
        } catch (Exception ex) {
            System.out.println("lỗi");
            ex.printStackTrace();
        }
    }

    public boolean checkTrong() {
        if (txtTenKM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên khuyến mãi");
            txtTenKM.requestFocus();
            return false;
        }
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống giá trị");
            txtGia.requestFocus();
            return false;
        }
        try {
            double gia = Double.parseDouble(txtGia.getText().trim());
            if (gia > 50) {
                JOptionPane.showMessageDialog(this, "Giá phải dưới 50 nghìn");
                txtGia.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số hợp lệ");
            txtGia.requestFocus();
            return false;
        }
        if (txtLoai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống loại");
            txtLoai.requestFocus();
            return false;
        }
        if (txtNgayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày bắt đầu");
            txtNgayBD.requestFocus();
            return false;
        }
        if (txtNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày kết thúc");
            txtNgayKT.requestFocus();
            return false;
        }

        Date ngayBatDau = txtNgayBD.getDate();
        Date ngayKetThuc = txtNgayKT.getDate();

        Date ngayHienTai = new Date();

        if (ngayBatDau.after(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được là ngày tương lai");
            txtNgayBD.requestFocus();
            return false;
        }

        if (ngayKetThuc.before(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sớm hơn ngày bắt đầu");
            txtNgayKT.requestFocus();
            return false;
        }
        if (!rdAD.isSelected() && !rdHH.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái");
            return false;
        }

        return true;
    }

    //=============sanpham=============//
    public void loadTableSP(List<SanPhamChiTiet> list) {
        dtm = (DefaultTableModel) tbSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTiet sp : list) {
            dtm.addRow(new Object[]{
                sp.getId(),
                sp.getTenSanPham(),
                sp.getKichThuoc(),
                sp.getSoLuong(),
                sp.getGia()
            });
        }
    }

    private void detailSP(int index) {
        SanPhamChiTiet sp = listSP.get(index);
        txtIDSP.setText(sp.getId() + "");
        txtTenSP.setText(sp.getTenSanPham());
        txtGiaSP.setText(sp.getGia() + "");
        txtSLSP.setText(sp.getSoLuong() + "");
        txtSize.setText(sp.getKichThuoc() + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnKhuyenMai = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        txtLoai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdAD = new javax.swing.JRadioButton();
        rdHH = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhuyenMai = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbbLocTT = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtIDSP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtSLSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGiaSP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btThem = new javax.swing.JButton();
        btApDung = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID khuyến mãi:");

        txtIdKM.setEditable(false);

        jLabel2.setText("Tên khuyến mãi:");

        jLabel3.setText("Loại:");

        jLabel4.setText("Gía trị:");

        jLabel5.setText("Ngày bắt đầu:");

        jLabel6.setText("Ngày kết thúc:");

        jLabel7.setText("Trạng thái:");

        buttonGroup1.add(rdAD);
        rdAD.setText("Đang áp dụng");

        buttonGroup1.add(rdHH);
        rdHH.setText("Hết hạn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdKM, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGia)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLoai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdAD)
                    .addComponent(rdHH))
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdHH)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIdKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(rdAD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id khuyến mãi", "Tên khuyến mãi", "Loại khuyến mãi", "Gía trị ", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhuyenMai);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Lọc trạng thái:");

        cbbLocTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang áp dụng", "Hết hạn" }));
        cbbLocTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cbbLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(cbbLocTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel8.setText("Tìm kiếm(Tên):");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel10.setText("Thông tin khuyến mãi");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("ID sản phẩm:");

        txtIDSP.setEditable(false);

        jLabel12.setText("Tên sản phẩm:");

        txtTenSP.setEditable(false);

        txtSLSP.setEditable(false);

        jLabel13.setText("Số lượng:");

        txtGiaSP.setEditable(false);

        jLabel14.setText("Giá sản phẩm:");

        jLabel17.setText("Size");

        txtSize.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtGiaSP)
                        .addGap(187, 187, 187))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtIDSP, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txtSize))
                .addGap(94, 94, 94)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtSLSP, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(96, 96, 96))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIDSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtSLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel18.setText("Thông tin sản phẩm");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btSua.setText("Sửa");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btApDung.setText("Áp dụng");
        btApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btApDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btApDung)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSua)
                    .addComponent(btThem)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(btApDung)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID sản phẩm", "Tên sản phẩm", "Size", "Số lượng sản phẩm", "Giá sản phẩm"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSanPham);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel15.setText("Bảng khuyến mãi");

        jLabel16.setText("Bảng sản phẩm");

        javax.swing.GroupLayout pnKhuyenMaiLayout = new javax.swing.GroupLayout(pnKhuyenMai);
        pnKhuyenMai.setLayout(pnKhuyenMaiLayout);
        pnKhuyenMaiLayout.setHorizontalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel18)
                    .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                        .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                        .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnKhuyenMaiLayout.setVerticalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel18))
                    .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(5, 5, 5)
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if (checkTrong()) {
                JOptionPane.showMessageDialog(this, kmDAO.addKM(getDataForm()));
                loadTable(kmDAO.findAll());
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btThemActionPerformed

    private void cbbLocTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTTActionPerformed
        try {
            int trangThai;
            String selectedItem = cbbLocTT.getSelectedItem().toString();
            if (selectedItem.equals("Đang áp dụng")) {
                trangThai = 1;
            } else if (selectedItem.equals("Hết hạn")) {
                trangThai = 0;
            } else {
                System.out.println("lỗi");
                return;
            }

            ArrayList<KhuyenMai> filteredList = (ArrayList<KhuyenMai>) kmDAO.getByTrangThai(trangThai);
            loadTable(filteredList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbLocTTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadTable(kmDAO.findAll());
        resetForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        this.search();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        int row = tbKhuyenMai.getSelectedRow();
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        } else if (c == JOptionPane.OK_OPTION) {
            if (checkTrong()) {
                JOptionPane.showMessageDialog(this, kmDAO.update(getDataUpdate()));
                loadTable(kmDAO.findAll());
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btSuaActionPerformed

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        int row = tbKhuyenMai.getSelectedRow();
        detail1(row);
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        detailSP(row);
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btApDungActionPerformed
        int selectedRowSP = tbSanPham.getSelectedRow();
        if (selectedRowSP < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
            return;
        }

        int selectedRowKM = tbKhuyenMai.getSelectedRow();
        if (selectedRowKM < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi!");
            return;
        }

        SanPhamChiTiet sp = listSP.get(selectedRowSP);
        KhuyenMai km = listKM.get(selectedRowKM);
        if (km.getTrangThai() == 0) {
            JOptionPane.showMessageDialog(null, "Khuyến mãi này không khả dụng!");
            return;
        }

        float giaTriKM = km.getGiaTri();

        float giaMoi = sp.getGia() - giaTriKM;
        if (giaMoi < 0) {
            giaMoi = 0; // Đảm bảo giá không âm
        }
        sp.setGia(giaMoi);

// Cập nhật giá mới vào cơ sở dữ liệu
        if (kmDAO.updateGiaSPDatabase(sp)) {
            JOptionPane.showMessageDialog(null, "Áp dụng khuyến mãi thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi áp dụng khuyến mãi!");
        }

// Tải lại bảng sản phẩm
        loadTableSP(listSP);
    }//GEN-LAST:event_btApDungActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btApDung;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLocTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnKhuyenMai;
    private javax.swing.JRadioButton rdAD;
    private javax.swing.JRadioButton rdHH;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtIDSP;
    private javax.swing.JTextField txtIdKM;
    private javax.swing.JTextField txtLoai;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtSLSP;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}

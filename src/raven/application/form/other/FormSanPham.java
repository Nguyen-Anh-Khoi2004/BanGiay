package raven.application.form.other;

import DAO.ChatLieuService;
import DAO.KichThuocService;
import DAO.LoaiDeService;
import DAO.MauService;
import DAO.NhaCungCapService;
import DAO.SanPhamChiTietService;
import DAO.SanPhamService;
import DAO.TheLoaiService;
import DAO.ThuongHieuService;
import Model.ChatLieu;
import Model.KichThuoc;
import Model.LoaiDe;
import Model.Mau;
import Model.NhaCungCap;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.TheLoai;
import Model.ThuongHieu;
import Views.ViewMauSac;
import Views.ViewTheLoai;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;

/**
 *
 * @author Raven
 */
public class FormSanPham extends javax.swing.JPanel {

    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel modelSP = new DefaultTableModel();
    List<SanPham> listSanPham = new ArrayList<>();
    List<SanPhamChiTiet> listSPCT = new ArrayList<>();
    List<TheLoai> listTheLoai = new ArrayList<>();
    List<ChatLieu> listChatLieu = new ArrayList<>();
    List<ThuongHieu> listThuongHieu = new ArrayList<>();
    List<NhaCungCap> listNCC = new ArrayList<>();
    List<Mau> listMau = new ArrayList<>();
    List<LoaiDe> listLoaiDe = new ArrayList<>();
    List<KichThuoc> listKichThuoc = new ArrayList<>();
    SanPhamService sanPhamService = new SanPhamService();
    SanPhamChiTietService sanPhamChiTietService = new SanPhamChiTietService();
    ChatLieuService chatLieuService = new ChatLieuService();
    KichThuocService kichThuocService = new KichThuocService();
    LoaiDeService loaiDeService = new LoaiDeService();
    MauService mauService = new MauService();
    NhaCungCapService nhaCungCapService = new NhaCungCapService();
    TheLoaiService theLoaiService = new TheLoaiService();
    ThuongHieuService thuongHieuService = new ThuongHieuService();
    public static int idSanPham;
    public static int idSPCT;
    private String duongDan = "C:\\Users\\Admin\\Downloads\\img_da1";

    public FormSanPham() {
        initComponents();
        SanPhamPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        dtm = (DefaultTableModel) tbDanhSachSPCT.getModel();
        listSanPham = sanPhamService.findAll();
        listTheLoai = theLoaiService.findAll();
        listChatLieu = chatLieuService.findAll();
        listThuongHieu = thuongHieuService.findAll();
        listNCC = nhaCungCapService.findAll();
        listMau = mauService.findAll();
        listLoaiDe = loaiDeService.findAll();
        listKichThuoc = kichThuocService.findAll();

        loadDataTableSanPham(listSanPham);
        loadTheLoaiCBB();
        loadCbbChatLieu();
        loadCbbKichThuoc();
        loadCbbLoaiDe();
        loadCbbMau();
        loadCbbNCC();
        loadCbbTH();
    }

    public void loadDataTableSanPham(List<SanPham> listSanPham) {
        modelSP = (DefaultTableModel) tbDanhSachSanPham.getModel();
        modelSP.setRowCount(0);
        for (SanPham sanPham : listSanPham) {
            modelSP.addRow(new Object[]{
                sanPham.getId(),
                sanPham.getTenSanPham(),
                sanPham.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng",
                sanPham.getTenTheLoai(),
                sanPham.getHinhAnh()
            });
        }
    }

    public SanPham showIndexSanPham(int index) {
        SanPham sanPham = listSanPham.get(index);
        idSanPham = sanPham.getId();
        lbID.setText(String.valueOf(sanPham.getId()));
        txtTenSP.setText(sanPham.getTenSanPham());
        cbbIDTheLoai.setSelectedItem(sanPham.getTenTheLoai());

        String imagePath = sanPham.getHinhAnh();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        lbAnh.setIcon(imageIcon);

        int trangThai = sanPham.getTrangThai();
        if (trangThai == 1) {
            rdCH.setSelected(true);
        } else {
            rdHH.setSelected(true);
        }
        return sanPham;
    }

    public SanPham getDataForm() {
        SanPham sp = new SanPham();
        String ten = txtTenSP.getText();
        String anh = duongDan;
        int trangThai = -1;
        if (rdCH.isSelected()) {
            trangThai = 1;
        } else if (rdHH.isSelected()) {
            trangThai = 2;
        }
        int theLoai = cbbIDTheLoai.getSelectedIndex();
        if (theLoai >= 0) {
            int idTheLoai = listTheLoai.get(theLoai).getId();
            sp.setIdTheLoai(idTheLoai);
        } else {
            JOptionPane.showMessageDialog(this, "Thể loại không được để trống");
            return null;
        }
        sp.setId(idSanPham);
        sp.setTenSanPham(ten);
        sp.setTrangThai(trangThai);
        sp.setHinhAnh(anh);

        return sp;
    }

    public SanPhamChiTiet getDataSPCT() {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        int chatLieu = cbbChatLieu.getSelectedIndex();
        int idChatLieu = listChatLieu.get(chatLieu).getId();
        int thuongHieu = cbbThuongHieu.getSelectedIndex();
        int idThuongHieu = listThuongHieu.get(thuongHieu).getId();
        int nhaCungCap = cbbNCC.getSelectedIndex();
        int idNCC = listNCC.get(nhaCungCap).getId();
        int mau = cbbMauSac.getSelectedIndex();
        int idMau = listMau.get(mau).getId();
        int loaiDe = cbbLoaiDe.getSelectedIndex();
        int idLoaiDe = listLoaiDe.get(loaiDe).getId();
        int kichThuoc = cbbKichThuoc.getSelectedIndex();
        int idKichThuoc = listKichThuoc.get(kichThuoc).getId();
        txtSoLuong.setEditor(new JSpinner.NumberEditor(txtSoLuong, "0"));
        Integer soLuong = (Integer) txtSoLuong.getValue();
        Float gia = Float.valueOf(txtGia.getText());
        java.util.Date ngayNhap = dcNgay.getDate();
        java.sql.Date ngay = new java.sql.Date(ngayNhap.getTime());
        spct.setIdChatLieu(idChatLieu);
        spct.setIdKichThuoc(idKichThuoc);
        spct.setIdLoaiDe(idLoaiDe);
        spct.setIdMau(idMau);
        spct.setIdNhaCungCap(nhaCungCap);
        spct.setIdSanPham(idSanPham);
        spct.setId(idSPCT);
        spct.setIdNhaCungCap(idNCC);
        spct.setIdThuongHieu(idThuongHieu);
        spct.setGia(gia);
        spct.setSoLuong(soLuong);
        spct.setNgayTao(ngay);
        return spct;
    }

    public void loadDataTableSPCT(List<SanPhamChiTiet> listSPCT) {
        dtm.setRowCount(0);
        int index = 1;
        for (SanPhamChiTiet spct : listSPCT) {
            dtm.addRow(new Object[]{
                spct.getId(),
                spct.getTenChatLieu(),
                spct.getTenThuongHieu(),
                spct.getTenNhaCungCap(),
                spct.getTenMau(),
                spct.getTenLoaiDe(),
                spct.getKichThuoc(),
                spct.getGia(),
                spct.getSoLuong(),
                spct.getNgayTao()
            });
        }
    }

    public SanPhamChiTiet showIndexChiTietSanPham(int index) {
        SanPhamChiTiet spct = listSPCT.get(index);
        idSPCT = spct.getId();
        cbbChatLieu.setSelectedItem(spct.getTenChatLieu());
        cbbKichThuoc.setSelectedItem(String.valueOf(spct.getKichThuoc()));
        cbbLoaiDe.setSelectedItem(spct.getTenLoaiDe());
        cbbMauSac.setSelectedItem(spct.getTenMau());
        cbbNCC.setSelectedItem(spct.getTenNhaCungCap());
        cbbThuongHieu.setSelectedItem(spct.getTenThuongHieu());
        int soLuong = Integer.valueOf(String.valueOf(spct.getSoLuong()));
        txtSoLuong.setValue(soLuong);
        dcNgay.setDate(spct.getNgayTao());
        txtGia.setText(String.valueOf(spct.getGia()));
        return spct;
    }

    public void loadCbbChatLieu() {
        List<ChatLieu> list = new ArrayList<>();
        list = chatLieuService.findAll();
        for (ChatLieu chatLieu : list) {
            cbbChatLieu.addItem(chatLieu.getTenChatLieu());
        }
    }

    public void loadCbbKichThuoc() {
        List<KichThuoc> list = new ArrayList<>();
        list = kichThuocService.findAll();
        for (KichThuoc kichThuoc : list) {
            cbbKichThuoc.addItem(String.valueOf(kichThuoc.getTenKichThuoc()));
        }
    }

    public void loadCbbLoaiDe() {
        List<LoaiDe> list = new ArrayList<>();
        list = loaiDeService.findAll();
        for (LoaiDe loaiDe : list) {
            cbbLoaiDe.addItem(loaiDe.getTenLoaiDe());
        }
    }

    public void loadCbbMau() {
        List<Mau> list = new ArrayList<>();
        list = mauService.findAll();
        for (Mau mau : list) {
            cbbMauSac.addItem(mau.getTenMauSac());
        }
    }

    public void loadCbbNCC() {
        List<NhaCungCap> list = new ArrayList<>();
        list = nhaCungCapService.findAll();
        for (NhaCungCap nhaCungCap : list) {
            cbbNCC.addItem(nhaCungCap.getTenNhaCungCap());
        }
    }

    public void loadTheLoaiCBB() {
        cbbIDTheLoai.removeAllItems();
        List<TheLoai> list = theLoaiService.findAll();
        for (TheLoai theLoai : list) {
            cbbIDTheLoai.addItem(theLoai.getTenTheLoai());
        }
    }

    public void loadCbbTH() {
        List<ThuongHieu> list = new ArrayList<>();
        list = thuongHieuService.findAll();
        for (ThuongHieu thuongHieu : list) {
            cbbThuongHieu.addItem(thuongHieu.getTenThuongHieu());
        }
    }

    public void resetForm() {
        lbID.setText("");
        txtTenSP.setText("");
        buttonGroup1.clearSelection();
        cbbIDTheLoai.setSelectedIndex(0);
    }

    public void resetFormSPCT() {
        cbbChatLieu.setSelectedIndex(0);
        cbbIDTheLoai.setSelectedIndex(0);
        cbbKichThuoc.setSelectedIndex(0);
        cbbLoaiDe.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbNCC.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);
        txtSoLuong.setValue(0);
        dcNgay.setDate(null);
        txtGia.setText("");
    }

    public boolean checkTrongSP() {
        String tenSP = txtTenSP.getText().trim();
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên sản phẩm");
            txtTenSP.requestFocus();
            return false;
        }
        for (SanPham sp : listSanPham) {
            if (sp.getTenSanPham().equalsIgnoreCase(tenSP)) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
                txtTenSP.requestFocus();
                return false;
            }
        }
        String tenTheLoai = (String) cbbIDTheLoai.getSelectedItem();
        int trangThai = theLoaiService.layTrangThai(tenTheLoai);
        if (trangThai == 0) {
            JOptionPane.showMessageDialog(this, "Thể loại đã hết hàng");
            return false;
        }
        if (!rdCH.isSelected() && !rdHH.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái");
            return false;
        }
        return true;
    }

    public boolean checkTrongSPCT() {
        Date ngayHienTai = new Date();
        Date ngayDuocChon = dcNgay.getDate();
//        String giaStr = txtGia.getText().trim();
//        float gia = Float.parseDouble(giaStr);
        if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống giá");
            txtGia.requestFocus();
            return false;
        }
        try {
            double gia = Double.parseDouble(txtGia.getText().trim());
            if (gia >= 100_000_000) {
                JOptionPane.showMessageDialog(this, "Giá phải dưới 100 triệu");
                txtGia.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số hợp lệ");
            txtGia.requestFocus();
            return false;
        }
//        if (gia < 0) {
//            JOptionPane.showMessageDialog(this, "Giá không được âm");
//            txtGia.requestFocus();
//            return false;
//        }
//
//        // Check if the value does not exceed 1 billion
//        if (gia > 1000000000) {
//            JOptionPane.showMessageDialog(this, "Giá không được quá 1 tỉ");
//            txtGia.requestFocus();
//            return false;
//        }
        if (ngayDuocChon == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày");
            dcNgay.requestFocus();
            return false;
        }
        if (ngayDuocChon.after(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày không được lớn hơn ngày hiện tại");
            dcNgay.requestFocus();
            return false;
        }
//    if (txtTenSP.getText().trim().isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Không để trống tên sản phẩm");
//        txtTenSP.requestFocus();
//        return false;
//    }
        if (((Number) txtSoLuong.getValue()).intValue() <= 0) {
            JOptionPane.showMessageDialog(this, "số lượng lớn hơn 0");
            txtSoLuong.requestFocus();
            return false;
        }
        //
        String tenChatLieu = (String) cbbChatLieu.getSelectedItem();
        int trangThai = chatLieuService.layTrangThai(tenChatLieu);
        if (trangThai == 0) {
            JOptionPane.showMessageDialog(this, "Chất liệu đã hết hàng");
            return false;
        }
        //
        String tenMau = (String) cbbMauSac.getSelectedItem();
        int trangThai1 = mauService.layTrangThai(tenMau);
        if (trangThai1 == 0) {
            JOptionPane.showMessageDialog(this, "Màu sắc đã hết hàng");
            return false;
        }
        //
        String tenLoaiDe = (String) cbbLoaiDe.getSelectedItem();
        int trangThai2 = loaiDeService.layTrangThai(tenLoaiDe);
        if (trangThai2 == 0) {
            JOptionPane.showMessageDialog(this, "Loại đế đã hết hàng");
            return false;
        }
        String tenKichThuoc = (String) cbbKichThuoc.getSelectedItem();
        int trangThai3 = kichThuocService.layTrangThai(tenKichThuoc);
        if (trangThai3 == 0) {
            JOptionPane.showMessageDialog(this, "Kích thước đã hết hàng");
            return false;
        }
        String tenThuongHieu = (String) cbbThuongHieu.getSelectedItem();
        int trangThai4 = thuongHieuService.layTrangThai(tenKichThuoc);
        if (trangThai4 == 0) {
            JOptionPane.showMessageDialog(this, "Thương hiệu đã hết hàng");
            return false;
        }
        String tenNhaCungCap = (String) cbbNCC.getSelectedItem();
        int trangThai6 = nhaCungCapService.layTrangThai(tenNhaCungCap);
        if (trangThai6 == 0) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp đã hết hàng");
            return false;
        }

        return true;
    }

    public ImageIcon resizeImage(String imagePath) {
        ImageIcon myImage = new ImageIcon(imagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    private void showImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        lbAnh.setIcon(imageIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        SanPhamPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rdCH = new javax.swing.JRadioButton();
        rdHH = new javax.swing.JRadioButton();
        cbbIDTheLoai = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        lbID = new javax.swing.JLabel();
        btnAddTL = new javax.swing.JButton();
        btnAddSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSachSanPham = new javax.swing.JTable();
        SPCTPanel = new javax.swing.JPanel();
        PanelChucNang = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbNCC = new javax.swing.JComboBox<>();
        btnAddCL = new javax.swing.JButton();
        btnAddTH = new javax.swing.JButton();
        btnAddKT = new javax.swing.JButton();
        btnAddLD = new javax.swing.JButton();
        btnAddNCC = new javax.swing.JButton();
        btnAddMS = new javax.swing.JButton();
        lbTenSP = new javax.swing.JLabel();
        cbbLoaiDe = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        dcNgay = new com.toedter.calendar.JDateChooser();
        txtGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btChatLieu = new javax.swing.JButton();
        btThuongHieu = new javax.swing.JButton();
        btKichThuoc = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnLamMoiSPCT = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        btnAddSPCT = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDanhSachSPCT = new javax.swing.JTable();

        SanPhamPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Sản Phẩm"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel13.setText("ID sản phẩm");

        jLabel14.setText("Tên sản phẩm");

        jLabel15.setText("Trạng thái");

        jLabel16.setText("ID thể loại");

        buttonGroup1.add(rdCH);
        rdCH.setText("Còn hàng");

        buttonGroup1.add(rdHH);
        rdHH.setText("Hết hàng");

        cbbIDTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbIDTheLoaiActionPerformed(evt);
            }
        });

        lbID.setText("...");

        btnAddTL.setText("+");
        btnAddTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTLActionPerformed(evt);
            }
        });

        btnAddSP.setText("Thêm");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbAnh.setToolTipText("");
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdCH)
                    .addComponent(rdHH)
                    .addComponent(cbbIDTheLoai, 0, 169, Short.MAX_VALUE)
                    .addComponent(txtTenSP)
                    .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAddTL))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaSP)
                            .addComponent(btnAddSP)
                            .addComponent(btnLamMoiSP))))
                .addGap(68, 68, 68)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lbID))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddSP))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(rdCH))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdHH)
                            .addComponent(btnSuaSP))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cbbIDTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddTL))
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiSP))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tbDanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên sản phẩm", "Trạng thái", "Thể loại"
            }
        ));
        tbDanhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhSachSanPham);

        javax.swing.GroupLayout SanPhamPanelLayout = new javax.swing.GroupLayout(SanPhamPanel);
        SanPhamPanel.setLayout(SanPhamPanelLayout);
        SanPhamPanelLayout.setHorizontalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamPanelLayout.createSequentialGroup()
                .addGroup(SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamPanelLayout.createSequentialGroup()
                        .addGap(1061, 1061, 1061)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SanPhamPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SanPhamPanelLayout.setVerticalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Sản phẩm", SanPhamPanel);

        PanelChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Chi Tiết Sản Phẩm"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel17.setText("Tên sản phẩm: ");

        jLabel18.setText("Chất liệu: ");

        jLabel19.setText("Thương hiệu:");

        jLabel20.setText("Kích thước:");

        jLabel21.setText("Loại đế:");

        jLabel22.setText("Màu sắc:");

        jLabel23.setText("Nhà cung cấp");

        btnAddCL.setText("+");
        btnAddCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCLActionPerformed(evt);
            }
        });

        btnAddTH.setText("+");
        btnAddTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTHActionPerformed(evt);
            }
        });

        btnAddKT.setText("+");
        btnAddKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKTActionPerformed(evt);
            }
        });

        btnAddLD.setText("+");
        btnAddLD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLDActionPerformed(evt);
            }
        });

        btnAddNCC.setText("+");
        btnAddNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNCCActionPerformed(evt);
            }
        });

        btnAddMS.setText("+");
        btnAddMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMSActionPerformed(evt);
            }
        });

        lbTenSP.setText("...");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jButton6.setText("jButton5");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(jButton6))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        jLabel1.setText("Số lượng");

        txtSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel2.setText("Ngày tạo");

        jLabel3.setText("Giá");

        jLabel4.setText("Đơn giá");

        btChatLieu.setText("+");
        btChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChatLieuActionPerformed(evt);
            }
        });

        btThuongHieu.setText("+");
        btThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThuongHieuActionPerformed(evt);
            }
        });

        btKichThuoc.setText("+");
        btKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelChucNangLayout = new javax.swing.GroupLayout(PanelChucNang);
        PanelChucNang.setLayout(PanelChucNangLayout);
        PanelChucNangLayout.setHorizontalGroup(
            PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChucNangLayout.createSequentialGroup()
                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                        .addGap(991, 991, 991)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel19))
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelChucNangLayout.createSequentialGroup()
                                                            .addGap(22, 22, 22)
                                                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btChatLieu)
                                                    .addComponent(btThuongHieu)
                                                    .addComponent(btKichThuoc))))))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAddTH)
                                            .addComponent(btnAddKT)
                                            .addComponent(btnAddCL)))
                                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                                        .addGap(152, 152, 152)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(304, 304, 304))
                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                .addGap(403, 403, 403)
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelChucNangLayout.createSequentialGroup()
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel22))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(btnAddMS))
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addComponent(cbbLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAddLD))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelChucNangLayout.createSequentialGroup()
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelChucNangLayout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addGap(42, 42, 42))
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel4))
                                                .addGap(68, 68, 68)))
                                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                                .addComponent(cbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(btnAddNCC))
                                            .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(dcNgay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelChucNangLayout.setVerticalGroup(
            PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChucNangLayout.createSequentialGroup()
                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(489, 489, 489)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelChucNangLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(btnAddTH)
                                    .addComponent(jLabel23)
                                    .addComponent(cbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddNCC)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btThuongHieu)))
                            .addGroup(PanelChucNangLayout.createSequentialGroup()
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(lbTenSP)
                                    .addComponent(jLabel21)
                                    .addComponent(btnAddLD)
                                    .addComponent(cbbLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAddCL)
                                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22)
                                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAddMS)
                                        .addComponent(btChatLieu)))))
                        .addGap(43, 43, 43)
                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(btnAddKT)
                                .addComponent(jLabel2)
                                .addComponent(btKichThuoc))
                            .addComponent(dcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(PanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnLamMoiSPCT.setText("Làm mới");
        btnLamMoiSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPCTActionPerformed(evt);
            }
        });

        btnSuaSPCT.setText("Sửa");
        btnSuaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPCTActionPerformed(evt);
            }
        });

        btnAddSPCT.setText("Thêm");
        btnAddSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddSPCT)
                    .addComponent(btnSuaSPCT)
                    .addComponent(btnLamMoiSPCT))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnAddSPCT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnSuaSPCT)
                .addGap(53, 53, 53)
                .addComponent(btnLamMoiSPCT)
                .addGap(112, 112, 112))
        );

        tbDanhSachSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SPCT", "Chất liệu", "Thương hiệu", "Nhà cung cấp", "Màu sắc", "Loại đế", "Kích thước", "Giá", "Số lượng", "Ngày tạo"
            }
        ));
        tbDanhSachSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachSPCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDanhSachSPCT);

        javax.swing.GroupLayout SPCTPanelLayout = new javax.swing.GroupLayout(SPCTPanel);
        SPCTPanel.setLayout(SPCTPanelLayout);
        SPCTPanelLayout.setHorizontalGroup(
            SPCTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPCTPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(SPCTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SPCTPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(727, Short.MAX_VALUE))
        );
        SPCTPanelLayout.setVerticalGroup(
            SPCTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPCTPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(PanelChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SPCTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Sản phẩm chi tiết", SPCTPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMSActionPerformed
        new Views.ViewMauSac(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddMSActionPerformed

    private void btnAddNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNCCActionPerformed
        new Views.ViewNhaCungCap(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddNCCActionPerformed

    private void btnAddLDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLDActionPerformed
        new Views.ViewLoaiDe(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddLDActionPerformed

    private void btnAddKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKTActionPerformed
        new Views.ViewKichThuoc(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddKTActionPerformed

    private void btnAddTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTHActionPerformed
        new Views.ViewThuongHieu(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddTHActionPerformed

    private void btnAddCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCLActionPerformed
        new Views.ViewChatLieu(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddCLActionPerformed

    private void btnAddTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTLActionPerformed
       new Views.ViewTheLoai(null, true).setVisible(true);
    }//GEN-LAST:event_btnAddTLActionPerformed

    private void tbDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachSanPhamMouseClicked
        int row = tbDanhSachSanPham.getSelectedRow();
        showIndexSanPham(row);
        if (evt.getButton() == MouseEvent.BUTTON1) {
            showIndexSanPham(row);
            System.out.println("id sản phẩm là : " + idSanPham);
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            if (row != -1) {
                int index = jTabbedPane2.indexOfComponent(SPCTPanel);
                jTabbedPane2.setSelectedIndex(index);
                String tenSp = listSanPham.get(row).getTenSanPham();
                lbTenSP.setText(tenSp);
                loadDataTableSPCT(listSPCT = sanPhamChiTietService.findAll(idSanPham));
            } else {
                JOptionPane.showMessageDialog(this, "Vui long chọn chuột phải vào dòng sản phẩm trước khi muốn xem chi tiết ");
                return;
            }
        }
    }//GEN-LAST:event_tbDanhSachSanPhamMouseClicked

    private void cbbIDTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbIDTheLoaiActionPerformed
//
    }//GEN-LAST:event_cbbIDTheLoaiActionPerformed

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if (checkTrongSP()) {
                JOptionPane.showMessageDialog(this, sanPhamService.addSanPham(getDataForm()));
                loadDataTableSanPham(listSanPham = sanPhamService.findAll());
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        int row = tbDanhSachSanPham.getSelectedRow();
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        } else if (c == JOptionPane.OK_OPTION) {
            if (checkTrongSP()) {
                JOptionPane.showMessageDialog(this, sanPhamService.updateSanPham(getDataForm()));
                loadDataTableSanPham(listSanPham = sanPhamService.findAll());
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        resetForm();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void tbDanhSachSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachSPCTMouseClicked
        int index = tbDanhSachSPCT.getSelectedRow();
        showIndexChiTietSanPham(index);
    }//GEN-LAST:event_tbDanhSachSPCTMouseClicked

    private void btnAddSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPCTActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if (checkTrongSPCT()) {
                JOptionPane.showMessageDialog(this, sanPhamChiTietService.addSPCT(getDataSPCT()));
                loadDataTableSPCT(listSPCT = sanPhamChiTietService.findAll(idSanPham));
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnAddSPCTActionPerformed

    private void btnSuaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPCTActionPerformed
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (c == JOptionPane.OK_OPTION) {
            if (checkTrongSPCT()) {
                JOptionPane.showMessageDialog(this, sanPhamChiTietService.updateSPCT(getDataSPCT()));
                loadDataTableSPCT(listSPCT = sanPhamChiTietService.findAll(idSanPham));
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnSuaSPCTActionPerformed

    private void btnLamMoiSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPCTActionPerformed
        resetFormSPCT();
    }//GEN-LAST:event_btnLamMoiSPCTActionPerformed

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        try {
            // Tạo một cửa sổ chọn tệp
            JFileChooser fileChooser = new JFileChooser("D:\\DuAn1\\image\\face");

            // Đặt tiêu đề cho cửa sổ chọn tệp
            fileChooser.setDialogTitle("Mở file");

            // Hiển thị cửa sổ chọn tệp và chờ người dùng chọn tệp
            int result = fileChooser.showOpenDialog(null);

            // Kiểm tra nếu người dùng đã chọn tệp và không hủy bỏ
            if (result == JFileChooser.APPROVE_OPTION) {
                // Lấy tệp đã chọn
                File selectedFile = fileChooser.getSelectedFile();

                // Lấy đường dẫn tuyệt đối của tệp và lưu vào biến duongDan
                duongDan = selectedFile.getAbsolutePath();

                // Đặt biểu tượng (icon) của JLabel là hình ảnh từ đường dẫn đã chọn
                // (Hàm setSizeImg(duongDan) được giả định là hàm để thiết lập kích thước hình ảnh)
                lbAnh.setIcon(resizeImage(duongDan)); // Hàm setSizeImg chưa được cung cấp trong đoạn mã này
            } else {
                // Người dùng chưa chọn tệp hoặc đã hủy bỏ
                System.out.println("Chưa chọn ảnh");
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình chọn tệp
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
        System.out.println(duongDan);
    }//GEN-LAST:event_lbAnhMouseClicked

    private void btChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChatLieuActionPerformed
        new Views.ViewChatLieu(null, true).setVisible(true);
    }//GEN-LAST:event_btChatLieuActionPerformed

    private void btThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThuongHieuActionPerformed
        new Views.ViewThuongHieu(null, true).setVisible(true);
    }//GEN-LAST:event_btThuongHieuActionPerformed

    private void btKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKichThuocActionPerformed
        new Views.ViewKichThuoc(null, true).setVisible(true);
    }//GEN-LAST:event_btKichThuocActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelChucNang;
    private javax.swing.JPanel SPCTPanel;
    private javax.swing.JPanel SanPhamPanel;
    private javax.swing.JButton btChatLieu;
    private javax.swing.JButton btKichThuoc;
    private javax.swing.JButton btThuongHieu;
    private javax.swing.JButton btnAddCL;
    private javax.swing.JButton btnAddKT;
    private javax.swing.JButton btnAddLD;
    private javax.swing.JButton btnAddMS;
    private javax.swing.JButton btnAddNCC;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnAddSPCT;
    private javax.swing.JButton btnAddTH;
    private javax.swing.JButton btnAddTL;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiSPCT;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbIDTheLoai;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbLoaiDe;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNCC;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private com.toedter.calendar.JDateChooser dcNgay;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbTenSP;
    private javax.swing.JRadioButton rdCH;
    private javax.swing.JRadioButton rdHH;
    private javax.swing.JTable tbDanhSachSPCT;
    private javax.swing.JTable tbDanhSachSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JSpinner txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}

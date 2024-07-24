package raven.application.form.other;

import DAO.HoaDonChiTietService;
import DAO.HoaDonService;
import DAO.KhachHangDAO;
import DAO.KhuyenMaiDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamChiTietService;
import Model.HoaDon;
import Model.HoaDonCT;
import Model.KhachHang;
import Model.KhuyenMai;
import Model.NhanVien;
import Model.SanPhamChiTiet;
import com.formdev.flatlaf.FlatClientProperties;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static raven.application.form.other.FormBanHang.idHoaDon;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormBanHang extends javax.swing.JPanel {

    List<HoaDon> listHoaDon = new ArrayList<>();
    HoaDonService hoaDonService = new HoaDonService();
    List<SanPhamChiTiet> listHDCT = new ArrayList<>();
    List<SanPhamChiTiet> listSP = new ArrayList<>();
    List<KhuyenMai> listKM = new ArrayList<>();

    SanPhamChiTietService sanPhamChiTietService = new SanPhamChiTietService();
    KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    KhachHangDAO khSV = new KhachHangDAO();
    NhanVienDAO nvSV = new NhanVienDAO();
    DefaultTableModel modelHoaDon = new DefaultTableModel();
    DefaultTableModel modelSPCT = new DefaultTableModel();
    DefaultTableModel modelHDCT = new DefaultTableModel();
    DefaultTableModel modelGioHang = new DefaultTableModel();

    private Map<Integer, List<SanPhamChiTiet>> dbDem = new HashMap<>();

    public static int idHoaDon;
    int idSPCT;
// Khởi tạo biến tổng tiền ban đầu là 0
    float tongTien = 0;
    float tienThanhToan = 0;

    public FormBanHang() {
        initComponents();
        BanHangPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        listSP = sanPhamChiTietService.loadFormSale();
        loadTableSPCT(listSP);
        modelHoaDon = (DefaultTableModel) tbHoaDon.getModel();
        loadTableHoaDon(listHoaDon = hoaDonService.findAll());
        listHDCT = sanPhamChiTietService.loadFormSale();
//        loadCbbKhachHang();
        loadCbbNhanVien();
//        loadKhuyenMaiToComboBox();

    }
//
//    public void loadKhuyenMaiToComboBox() {
//        List<KhuyenMai> dsKM = kmDAO.findAll();
//        List<String> tenKMList = new ArrayList<>();
//        for (KhuyenMai km : dsKM) {
//            if (km.getTrangThai() != 0) {
//                tenKMList.add(km.getTenKM());
//            }
//        }
//        cbbKM.removeAllItems();
//        for (String tenKM : tenKMList) {
//            cbbKM.addItem(tenKM);
//        }
//    }

//    public void loadCbbKhachHang() {
//        List<KhachHang> list = new ArrayList<>();
//        list = khSV.getAll();
//        for (KhachHang chatLieu : list) {
//            cbbTTKH.addItem(chatLieu.getHoTen());
//            chatLieu.getId();
//        }
//    }
    public void loadIdKH(int index) {
        ArrayList<KhachHang> list = khSV.getAll();
        if (list != null && list.size() > index) {
            KhachHang kh = list.get(index);
//            lbIDKH.setText(String.valueOf(kh.getId()));
        }
    }

    public void loadCbbNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        list = nvSV.getAll();
        for (NhanVien chatLieu : list) {
            cbbNhanVien.addItem(chatLieu.getHoTen());
        }
    }

    public void loadTableHoaDon(List<HoaDon> listHoaDon) {
        listHoaDon = hoaDonService.findAll();
        modelHoaDon.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (HoaDon hoaDon : listHoaDon) {
            modelHoaDon.addRow(new Object[]{
                hoaDon.getIdHoaDon(),
                dateFormat.format(hoaDon.getNgayTao()), // Chuyển đổi java.sql.Date thành chuỗi ngày
                hoaDon.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    public void locTTHoaDon(List<HoaDon> listHoaDon) {
        int trangThai = 0;
        listHoaDon = hoaDonService.locTrangThai(trangThai);
        modelHoaDon.setRowCount(0);
        for (HoaDon hoaDon : listHoaDon) {
            modelHoaDon.addRow(new Object[]{
                hoaDon.getIdHoaDon(),
                hoaDon.getNgayTao(),
                hoaDon.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    public void loadTableHoaDonCT(List<HoaDonCT> listHDCT) {
        modelHDCT = (DefaultTableModel) tbGioHang.getModel();
        modelHDCT.setRowCount(0);
        for (HoaDonCT hoaDonCT : listHDCT) {
            modelHDCT.addRow(new Object[]{
                hoaDonCT.getId(),
                hoaDonCT.getTenSanPham(),
                hoaDonCT.getSoLuong(),
                hoaDonCT.getDonGia(),
                hoaDonCT.getTongTien()
            });
        }
    }

    public void loadTableSPCT(List<SanPhamChiTiet> listSPCT) {
        modelSPCT = (DefaultTableModel) tbSPCT.getModel();
        modelSPCT.setRowCount(0);
        for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
            modelSPCT.addRow(new Object[]{
                sanPhamChiTiet.getId(),
                sanPhamChiTiet.getTenSanPham(),
                sanPhamChiTiet.getTenChatLieu(),
                sanPhamChiTiet.getTenThuongHieu(),
                sanPhamChiTiet.getTenNhaCungCap(),
                sanPhamChiTiet.getTenMau(),
                sanPhamChiTiet.getTenLoaiDe(),
                sanPhamChiTiet.getKichThuoc(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getGia(),
            });
        }
    }

    private void showGioHang(List<SanPhamChiTiet> listSPCT) {
        modelGioHang = (DefaultTableModel) tbGioHang.getModel();
        modelGioHang.setRowCount(0);

        double tongTien = 0; // Biến lưu trữ tổng tiền

        for (SanPhamChiTiet g : listSPCT) {
            double thanhTien = g.getSoLuong() * g.getGia();
            tongTien += thanhTien; // Cộng dồn thanhTien vào tongTien

            modelGioHang.addRow(new Object[]{
                g.getTenSanPham(),
                g.getSoLuong(),
                g.getGia(),
                thanhTien,});
        }

        // Hiển thị tổng tiền lên giao diện, giả sử bạn có một JTextField tên là txtTongTien
        lbTongTien.setText(String.valueOf(tongTien));
        tinhTienThua();

    }

    public boolean thanhToan() {
        String inputTienKhachDua = txtTienKhachDua.getText();
        Float tienKhachDua = 0f;
        Float tienThua = 0f;
        int pttt = cbbPTTT.getSelectedIndex();
        if (pttt == 0) {
            if (inputTienKhachDua.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số !");
                return false;
            } else if (inputTienKhachDua.isEmpty()) {
                tienKhachDua = 0f;
                txtTienKhachDua.setText(String.valueOf(tienKhachDua));
                return false;
            } else if (!inputTienKhachDua.isEmpty()) {
                tienKhachDua = Float.parseFloat(inputTienKhachDua);
                if (tienKhachDua < tienThanhToan) {
                    JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Không Đủ !");
                    return false;
                } else if (tienKhachDua > tienThanhToan) {
                    tienThua = 0f;
                    tienThua = tienKhachDua - tienThanhToan;
                    lbTienThua.setText(String.valueOf(tienThua));
                    return true;
                } else if (tienKhachDua == tienThanhToan) {
                    tienThua = 0f;
                    lbTienThua.setText(String.valueOf(tienThua));
                    return true;
                }
                return true;
            }
        } else if (pttt == 1) {
            tienThua = 0f;
            tienKhachDua = 0f;
            lbTienThua.setText(String.valueOf(tienThua));
            lbTienThua.setText(String.valueOf(tienThua));
            return true;
        }

        return true;
    }

    private void tinhTienThua() {
        try {
            // Lấy và kiểm tra giá trị tổng tiền
            String tongTienText = lbTongTien.getText().replaceAll("[^\\d.]", "");
            BigDecimal tongTien = new BigDecimal(tongTienText);

            // Lấy và kiểm tra giá trị tiền khách đưa
            String tienKhachDuaText = txtTienKhachDua.getText().replaceAll("[^\\d.]", "");
            if (!tienKhachDuaText.isEmpty()) {
                BigDecimal tienKhachDua = new BigDecimal(tienKhachDuaText);

                // Tính toán tiền thừa
                BigDecimal tienThua = tienKhachDua.subtract(tongTien);
                lbTienThua.setText(tienThua.toString());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            lbTienThua.setText("Lỗi định dạng số");
        }
    }

    private int findRowInSPById(int id) {
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getId() == id) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    private HoaDon data() {
        int trangThai = 1;
        int pttt = cbbPTTT.getSelectedIndex();

        BigDecimal tienKhachDua;
        BigDecimal tienThua;
        BigDecimal tongTien;

        try {
            tienKhachDua = new BigDecimal(txtTienKhachDua.getText());
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không hợp lệ, ví dụ: thông báo lỗi
            tienKhachDua = BigDecimal.ZERO; // Gán giá trị mặc định hoặc 0
        }

        try {
            tienThua = new BigDecimal(lbTienThua.getText());
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không hợp lệ, ví dụ: thông báo lỗi
            tienThua = BigDecimal.ZERO; // Gán giá trị mặc định hoặc 0
        }

        try {
            tongTien = new BigDecimal(lbTongTien.getText());
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không hợp lệ, ví dụ: thông báo lỗi
            tongTien = BigDecimal.ZERO; // Gán giá trị mặc định hoặc 0
        }

        return new HoaDon(idHoaDon, 1, TOOL_TIP_TEXT_KEY, trangThai, new java.sql.Date(System.currentTimeMillis()), tongTien, tienKhachDua, tienThua, pttt, 1, TOOL_TIP_TEXT_KEY, 1, TOOL_TIP_TEXT_KEY);
    }

    public void updateChiTietSanPhamById(int idsp) {
        for (int i = 0; i < listSP.size(); i++) {
            SanPhamChiTiet el = listSP.get(i);
            if (el.getId() == idsp) {
                SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet();
                chiTietSanPham.setId(el.getId());
                chiTietSanPham.setSoLuong(el.getSoLuong());
                sanPhamChiTietService.updateCTSPInGioHang(chiTietSanPham);
            }
        }
    }

//    public void tinhTongTienKM() {
//        String selectedKM = (String) cbbKM.getSelectedItem();
//        if (selectedKM != null) { // Kiểm tra xem đã chọn khuyến mãi nào chưa
//            for (KhuyenMai km : kmDAO.findAll()) {
//                if (km.getTenKM().equals(selectedKM)) {
//                    float giaTriKM = km.getGiaTri(); // Lấy giá trị khuyến mãi từ đối tượng KhuyenMai
//                    BigDecimal giaTriKMBigDecimal = BigDecimal.valueOf(giaTriKM); // Chuyển đổi sang BigDecimal
//                    BigDecimal tongTienSauKM = tinhTongTienSauKhuyenMai(new BigDecimal(tongTien), giaTriKM);
//                    lbTongTien.setText(tongTienSauKM.toString()); // Hiển thị tổng tiền sau khi áp dụng khuyến mãi
//                    return;
//                }
//            }
//        }
//        // Nếu không chọn hoặc không tìm thấy khuyến mãi, hiển thị tổng tiền không thay đổi
//        lbTongTien.setText(String.valueOf(tongTien));
//    }
//
//    private BigDecimal tinhTongTienSauKhuyenMai(BigDecimal tongTien, float giaTriKhuyenMai) {
//        BigDecimal giaTriKhuyenMaiBigDecimal = BigDecimal.valueOf(giaTriKhuyenMai);
//        BigDecimal tongTienSauKhuyenMai = tongTien.subtract(giaTriKhuyenMaiBigDecimal);
//
//        // Đảm bảo tổng tiền không nhỏ hơn 0
//        if (tongTienSauKhuyenMai.compareTo(BigDecimal.ZERO) < 0) {
//            return BigDecimal.ZERO;
//        }
//
//        return tongTienSauKhuyenMai;
//    }
//    public HoaDon dataDonHang(){
//        String trangThai = "Đã thanh toán";
//        int id_khachHang = 0;
//        String tenKH = cbbTTKH.getSelectedItem() + "";
//        HoaDon hd = new HoaDon();
//        hd.setNgayTao(new java.sql.Date(new java.util.Date().getTime()));
//        hd.setTrangThai(Integer.valueOf(trangThai));
//        
//    }
//    public void loadCbbKH() {
//        List<KhachHang> list = new ArrayList<>();
//        list = khachHang.findAll();
//        for (KhachHang khachHang : list) {
//            cbbTTKH.addItem(khachHang.get());
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        BanHangPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        rdChuaTT = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSPCT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtKhach = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbIDHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        cbbPTTT = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbNhanVien = new javax.swing.JComboBox<>();

        jToolBar1.setRollover(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn"));

        btnTaoHoaDon.setText("Tạo hoá đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        rdChuaTT.setText("Chưa thanh toán");
        rdChuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChuaTTActionPerformed(evt);
            }
        });

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Ngày tạo", "Trạng thái"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdChuaTT)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(rdChuaTT))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(45, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn chi tiết"));

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tbGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGioHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm chi tiết"));

        tbSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SPCT", "Tên sản phẩm", "Chất liệu", "Thương hiệu", "Nhà cung cấp", "Màu sắc", "Loại đế", "Kích thước", "Số lượng", "Giá"
            }
        ));
        tbSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSPCT);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm tên sản phẩm"));

        jLabel3.setText("Tìm kiếm");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnTimKiem)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lí bán hàng"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel2.setText("Tên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(73, 73, 73)
                .addComponent(txtKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));

        jLabel4.setText("ID hoá đơn");

        lbIDHD.setText("...");

        jLabel6.setText("Tiền khách đưa");

        jLabel7.setText("Tiền thừa");

        jLabel8.setText("Phương thức thanh toán");

        jLabel9.setText("Tổng tiền");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        cbbPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cbbPTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPTTTActionPerformed(evt);
            }
        });

        jLabel10.setText("Nhân viên");

        lbTienThua.setText("...");

        lbTongTien.setText("...");

        jLabel5.setText("Ngày tạo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(90, 90, 90)
                                .addComponent(lbIDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(btnThanhToan))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel10))
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtTienKhachDua)
                                                    .addComponent(cbbPTTT, 0, 123, Short.MAX_VALUE))))))))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lbTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbIDHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbTongTien))
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbTienThua))
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnThanhToan)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BanHangPanelLayout = new javax.swing.GroupLayout(BanHangPanel);
        BanHangPanel.setLayout(BanHangPanelLayout);
        BanHangPanelLayout.setHorizontalGroup(
            BanHangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHangPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanHangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BanHangPanelLayout.setVerticalGroup(
            BanHangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHangPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanHangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanHangPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BanHangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BanHangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm hoá đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            HoaDon hoaDon = new HoaDon(
                    1, // idNhanVien
                    1, // trangThai
                    "a", // ghiChu
                    0, // idKhachHang
                    new java.sql.Date(System.currentTimeMillis()), // ngayTao
                    BigDecimal.ONE, // tongTien
                    BigDecimal.TEN, // tienKhachDua
                    BigDecimal.TEN, // tienThua
                    HEIGHT, // chiều cao
                    WIDTH, // chiều rộng
                    TOOL_TIP_TEXT_KEY, // toolTipTextKey
                    WIDTH, // chiều rộng
                    TOOL_TIP_TEXT_KEY // toolTipTextKey
            );
            hoaDonService.insert(hoaDon);
            loadTableHoaDon(listHoaDon = hoaDonService.findAll());
        }
//        int c = JOptionPane.showConfirmDialog(this, "bạn có muốn thêm hóa đơn không");
//        if (c == JOptionPane.OK_OPTION) {
//            JOptionPane.showMessageDialog(this, hoaDonService.insert(hd));
//            showTableHoaDon(listHD = serviceHD.listAll().stream().filter(hd -> hd.getTrangThai().trim().equals("Chờ thanh toán")).toList());
//        } else {
//            return;
//        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void rdChuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChuaTTActionPerformed
        int trangThai = 0;
        if (rdChuaTT.isSelected()) {
            locTTHoaDon(listHoaDon = hoaDonService.locTrangThai(trangThai));
        } else {
            loadTableHoaDon(listHoaDon = hoaDonService.findAll());
        }
    }//GEN-LAST:event_rdChuaTTActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int index = tbHoaDon.getSelectedRow();
        idHoaDon = listHoaDon.get(index).getIdHoaDon();
        HoaDon hoaDon = listHoaDon.get(index);
        listHDCT = new ArrayList<>();
        if (dbDem.containsKey(hoaDon.getIdHoaDon())) {
            listHDCT.addAll(dbDem.get(hoaDon.getIdHoaDon()));
        } else {
            listHDCT.addAll(sanPhamChiTietService.getSPCtInHoaDon(hoaDon.getIdHoaDon()));
            dbDem.put(hoaDon.getIdHoaDon(), listHDCT);
        }
        Date ngay = hoaDon.getNgayTao();

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(ngay);

        // Set the date to the text field
        txtNgayTao.setText(formattedDate);
        lbIDHD.setText(String.valueOf(hoaDon.getIdHoaDon()));
        showGioHang(listHDCT);
    }//GEN-LAST:event_tbHoaDonMouseClicked
    private void tbSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPCTMouseClicked
        if (tbHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn trước khi thêm sản phẩm");
            return;
        }
        HoaDon hd = listHoaDon.get(tbHoaDon.getSelectedRow());
        int row = this.tbSPCT.getSelectedRow();
        SanPhamChiTiet sanPhamSelected = listSP.get(row);
        System.out.println(sanPhamSelected.getId());
        int soLuongCheckValidate = listSP.get(row).getSoLuong();
        SanPhamChiTiet hoaDonSanPhamUpdate = new SanPhamChiTiet();

        hoaDonSanPhamUpdate.setId(sanPhamSelected.getId());
        hoaDonSanPhamUpdate.setTenSanPham(sanPhamSelected.getTenSanPham());
        hoaDonSanPhamUpdate.setGia(sanPhamSelected.getGia());

        boolean check = true;
        int viTriCheck = -1;
        String soLuongChon = JOptionPane.showInputDialog(this, "Chọn số lượng sản phẩm " + sanPhamSelected.getTenSanPham());
        Integer soLuongConvert = 0;
        Integer soLuongTon = sanPhamSelected.getSoLuong();
        try {
            if (soLuongChon.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng sản phẩm " + sanPhamSelected.getTenSanPham());
                return;
            }
            try {
                soLuongConvert = Integer.valueOf(soLuongChon);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập kiểu số nguyên");
                return;
            }
            if (soLuongConvert <= soLuongCheckValidate && soLuongConvert > 0) {
                for (int i = 0; i < listHDCT.size(); i++) {
                    SanPhamChiTiet el = listHDCT.get(i);
                    if (el.getId() == sanPhamSelected.getId()) {
                        check = false;
                        viTriCheck = i;
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ hoặc bạn nhập sai số lượng");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "LỖi");
            return;
        }
        if (!check) {
            hoaDonSanPhamUpdate.setSoLuong(listHDCT.get(viTriCheck).getSoLuong() + soLuongConvert);
            listHDCT.set(viTriCheck, hoaDonSanPhamUpdate);
        } else {
            hoaDonSanPhamUpdate.setSoLuong(soLuongConvert);
            listHDCT.add(hoaDonSanPhamUpdate);
        }
        showGioHang(listHDCT);
        dbDem.put(hd.getIdHoaDon(), listHDCT);
        // load chi tiết sản phẩm
        soLuongTon = soLuongTon - soLuongConvert;
        sanPhamSelected.setSoLuong(soLuongTon);
        listSP.set(row, sanPhamSelected);
        loadTableSPCT(listSP);
    }//GEN-LAST:event_tbSPCTMouseClicked


    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        int row = tbGioHang.getSelectedRow();
        if (row >= 0 && row < listHDCT.size()) {
            int IDSanPhamTrongGioHang = listHDCT.get(row).getId();
            int soLuongSanPhamTrongGioHang = listHDCT.get(row).getSoLuong();
            int soLuongCheck = listHDCT.get(row).getSoLuong();

            System.out.println("ID sản phẩm trong giỏ hàng = " + IDSanPhamTrongGioHang);
            System.out.println("Số lượng sản phẩm trong giỏ hàng = " + soLuongSanPhamTrongGioHang);

            String input = JOptionPane.showInputDialog(this, "Vui lòng nhập số lượng bạn muốn xóa");
            if (input != null && !input.isEmpty() && input.matches("\\d+")) {
                int soLuongChon = Integer.parseInt(input);
                if (soLuongChon <= soLuongCheck) {
                    int soLuongUpdate = soLuongSanPhamTrongGioHang - soLuongChon;
                    System.out.println("Số lượng bạn nhập vào là = " + soLuongChon);
                    System.out.println("Tổng số lượng còn lại trong giỏ hàng = " + soLuongUpdate);

                    // Cập nhật giỏ hàng
                    SanPhamChiTiet sp = listHDCT.get(row);
                    sp.setSoLuong(soLuongUpdate);
                    listHDCT.set(row, sp); // Cập nhật lại đối tượng trong danh sách
                    showGioHang(listHDCT); // Đảm bảo showGioHang nhận đúng danh sách giỏ hàng

                    // Cập nhật số lượng trong kho
                    int rowInSP = findRowInSPById(IDSanPhamTrongGioHang);
                    if (rowInSP != -1) {
                        SanPhamChiTiet spKho = listSP.get(rowInSP);
                        int soLuongSanPhamTrongKho = spKho.getSoLuong();
                        int TongSoLuongUpdate = soLuongSanPhamTrongKho + soLuongChon;
                        spKho.setSoLuong(TongSoLuongUpdate);
                        listSP.set(rowInSP, spKho);
                        loadTableSPCT(listSP); // Đảm bảo loadTableSPCT nhận đúng danh sách sản phẩm
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng không lớn hơn số lượng trong giỏ hàng.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên dương.");
            }
        } else {
            System.out.println("Chọn sản phẩm trong giỏ hàng trước khi thực hiện hành động này.");
        }
    }//GEN-LAST:event_tbGioHangMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int idSP = 0;
        if (tbHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn trước khi thêm sản phẩm");
            return;
        }

        // Lấy và kiểm tra giá trị tổng tiền
        String tongTienText = lbTongTien.getText().replaceAll("[^\\d.]", "");
        BigDecimal tongTien = new BigDecimal(tongTienText);
        if (tongTien.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ");
            return;
        }

        // Lấy và kiểm tra giá trị tiền khách đưa
        String tienKhachDuaText = txtTienKhachDua.getText().replaceAll("[^\\d.]", "");
        if (tienKhachDuaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không được rỗng");
            return;
        }
        BigDecimal tienKhachDua = new BigDecimal(tienKhachDuaText);

        // Tính toán tiền thừa
        BigDecimal tienThua = tienKhachDua.subtract(tongTien);

        if (thanhToan()) {
            HoaDon hd = listHoaDon.get(tbHoaDon.getSelectedRow());
            System.out.println("HD trong thanh toán : " + hd.getIdHoaDon());
            for (SanPhamChiTiet el : dbDem.get(hd.getIdHoaDon())) {
                HoaDonCT hdct = new HoaDonCT();
                idSP = el.getId();
                hdct.setIdHoaDon(hd.getIdHoaDon());
                hdct.setIdSPCT(idSP);
                hdct.setSoLuong(el.getSoLuong());
                hdct.setDonGia(el.getGia());
                hoaDonChiTietService.them(hdct);
                updateChiTietSanPhamById(el.getId());
            }
            hoaDonService.update(data());
            System.out.println(data());
            loadTableHoaDon(listHoaDon = hoaDonService.findAll());

            showGioHang(listHDCT);
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");

            // Đường dẫn tới file phông chữ Unicode
            String fontPath = "C:\\Users\\Admin\\Downloads\\font-times-new-roman\\font-times-new-roman\\font-times-new-roman.ttf"; // Thay bằng đường dẫn thực tế tới phông chữ của bạn
            try {
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Hỏi người dùng có muốn in hóa đơn hay không
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String path = "C:/Users/Admin/Pictures/Screenshots/";
                    Document doc = new Document();
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream(path + "HoaDon.pdf"));
                        doc.open();
                        doc.add(new Paragraph("----------------- CỬA HÀNG GIÀY N4V XIN KÍNH CHÀO --------------------", font));
                        doc.add(new Paragraph(" "));
                        doc.add(new Paragraph("Mã Hóa Đơn:     " + lbIDHD.getText(), font));
                        doc.add(new Paragraph("Ngày Tạo:       " + txtNgayTao.getText(), font));
                        doc.add(new Paragraph("Tên Nhân Viên:   " + cbbNhanVien.getSelectedItem().toString(), font));
                        doc.add(new Paragraph("Tên Khách Hàng:  " + txtKhach.getText(), font));
                        PdfPTable table = new PdfPTable(tbGioHang.getColumnCount());
                        table.setWidthPercentage(100);

                        for (int i = 0; i < tbGioHang.getColumnCount(); i++) {
                            table.addCell(new Paragraph(tbGioHang.getColumnName(i), font));
                        }

                        for (int i = 0; i < tbGioHang.getRowCount(); i++) {
                            for (int j = 0; j < tbGioHang.getColumnCount(); j++) {
                                table.addCell(new Paragraph(tbGioHang.getValueAt(i, j).toString(), font));
                            }
                        }
                        doc.add(new Paragraph("Tổng Tiền:      " + tongTien, font));
                        doc.add(new Paragraph("Tiền Khách Đưa: " + tienKhachDua, font));
                        doc.add(new Paragraph("Tiền Thừa:      " + tienThua, font));
                        doc.add(new Paragraph("Phương thức thanh toán:  " + cbbPTTT.getSelectedItem().toString(), font));
                        doc.add(new Paragraph(" "));

                        doc.add(table);
                        doc.add(new Paragraph("---------------CỬA HÀNG GIÀY N4V XIN CẢM ƠN --------------------", font));
                        doc.add(new Paragraph("Địa Chỉ:           NAM TỪ LIÊM, HÀ NỘI", font));
                        doc.add(new Paragraph("Số Điện Thoại Liên Hệ:  0987654321", font));
                        doc.add(new Paragraph("--------------------------HẸN GẶP LẠI QUÝ KHÁCH----------------------------", font));
                        doc.close();
                        JOptionPane.showMessageDialog(this, "In thành công");
                    } catch (DocumentException | IOException e) {
                        // Hiển thị chi tiết lỗi để gỡ lỗi
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "In Thất Bại: " + e.getMessage());
                    }
                }
            } catch (DocumentException | IOException e) {
                // Hiển thị chi tiết lỗi để gỡ lỗi
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi tạo phông chữ: " + e.getMessage());
            }

            lbIDHD.setText("");
            txtNgayTao.setText("");
//            lbIDKH.setText("");
            lbTongTien.setText("");
            txtTienKhachDua.setText("");
            lbTienThua.setText("");
// Xóa tất cả các hàng trong bảng tbGioHang
            DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
            model.setRowCount(0);

            listHDCT.clear();

        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        tinhTienThua();
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String tenSP = txtTimKiem.getText(); // Giả sử txtTenSanPham là trường nhập liệu cho tên sản phẩm
        // Gọi phương thức timKiemTenSP để tìm kiếm sản phẩm
        List<SanPhamChiTiet> ketQuaTimKiem = sanPhamChiTietService.timKiemTenSP(tenSP);
        loadTableSPCT(ketQuaTimKiem);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbbPTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPTTTActionPerformed
        //
    }//GEN-LAST:event_cbbPTTTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BanHangPanel;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JComboBox<String> cbbPTTT;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbIDHD;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JRadioButton rdChuaTT;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSPCT;
    private javax.swing.JTextField txtKhach;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

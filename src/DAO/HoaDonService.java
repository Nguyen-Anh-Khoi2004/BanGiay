/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.HoaDon;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService {

    private Connection conn;

    public HoaDonService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDon> findAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "	SELECT \n"
                    + "    hd.id_hoaDon,\n"
                    + "	hd.trangThai,\n"
                    + "    hd.ngayTao,\n"
                    + "    hd.tongTien,\n"
                    + "	hd.phuongThucThanhToan,\n"
                    + "    nv.hoTen,\n"
                    + "    kh.hoTen,\n"
                    + "    km.tenKhuyenMai\n"
                    + "FROM \n"
                    + "    hoaDon hd\n"
                    + "LEFT JOIN \n"
                    + "    nhanVien nv ON hd.id_nhanVien = nv.id_nhanVien\n"
                    + "LEFT JOIN \n"
                    + "    khachHang kh ON hd.id_khachHang = kh.id_khachHang\n"
                    + "LEFT JOIN \n"
                    + "    khuyenMai km ON hd.id_khuyenMai = km.id_khuyenMai;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdHoaDon(rs.getInt("id_hoaDon"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                hoaDon.setNgayTao(rs.getDate("ngayTao"));
                hoaDon.setTongTien(rs.getBigDecimal("tongTien"));
                hoaDon.setPhuongThucThanhToan(rs.getInt("phuongThucThanhToan"));
                hoaDon.setTenNV(rs.getString("hoTen"));
                hoaDon.setTenKH(rs.getString("hoTen"));
                hoaDon.setTenKM(rs.getString("tenKhuyenMai"));
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> findLoadId() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    hd.id_hoaDon,\n"
                    + "    hd.trangThai,\n"
                    + "    hd.ngayTao,\n"
                    + "    hd.tongTien,\n"
                    + "    hd.phuongThucThanhToan,\n"
                    + "    nv.hoTen AS tenNV,\n"
                    + "    kh.hoTen AS tenKH,\n"
                    + "    km.tenKhuyenMai\n"
                    + "FROM \n"
                    + "    hoaDon hd\n"
                    + "LEFT JOIN \n"
                    + "    nhanVien nv ON hd.id_nhanVien = nv.id_nhanVien\n"
                    + "LEFT JOIN \n"
                    + "    khachHang kh ON hd.id_khachHang = kh.id_khachHang\n"
                    + "LEFT JOIN \n"
                    + "    khuyenMai km ON hd.id_khuyenMai = km.id_khuyenMai\n"
                    + "ORDER BY \n"
                    + "    hd.id_hoaDon DESC;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdHoaDon(rs.getInt("id_hoaDon"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                hoaDon.setNgayTao(rs.getDate("ngayTao"));
                hoaDon.setTongTien(rs.getBigDecimal("tongTien"));
                hoaDon.setPhuongThucThanhToan(rs.getInt("phuongThucThanhToan"));
                hoaDon.setTenNV(rs.getString("tenNV"));
                hoaDon.setTenKH(rs.getString("tenKH"));
                hoaDon.setTenKM(rs.getString("tenKhuyenMai"));
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public boolean insert(HoaDon hd) {
//        Integer row = null;
//        try {
//            String sql = "insert into HoaDon (id_nhanVien ,trangThai,ngayTao,tongTien,tienKhachDua,tienThua,phuongThucThanhToan,id_khachHang,id_khuyenMai) values(?,?,?,?,?,?,?,?,?)";
//            PreparedStatement ps = this.conn.prepareStatement(sql);
//            ps.setInt(1, hd.getIdNhanVien());
//            ps.setInt(2, hd.getTrangThai());
//            ps.setDate(3, new java.sql.Date(hd.getNgayTao().getTime()));
//            ps.setBigDecimal(4, hd.getTongTien());
//            ps.setBigDecimal(5, hd.getTienKhachDua());
//            ps.setBigDecimal(6, hd.getTienThua());
//            ps.setInt(7, hd.getPhuongThucThanhToan());
//            ps.setInt(8, hd.getIdKhachHang());
//            ps.setInt(9, hd.getIdKhuyenMai());
//            ps.execute();
//            row = ps.getUpdateCount();
//            System.out.println(row);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return row > 0;
//    }
//
    public boolean update(HoaDon hd) {
        Integer row = null;
        try {
            String sql = "UPDATE HoaDon SET id_nhanVien = ?, trangThai = ?, ngayTao = ?, tongTien = ?, tienKhachDua = ?,tienThua = ?,phuongThucThanhToan = ?,id_khachHang = ?,id_khuyenMai = ? WHERE id_hoaDon = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hd.getIdNhanVien());
            ps.setInt(2, hd.getTrangThai());
            ps.setDate(3, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setBigDecimal(4, hd.getTongTien());
            ps.setBigDecimal(5, hd.getTienKhachDua());
            ps.setBigDecimal(6, hd.getTienThua());
            ps.setInt(7, hd.getPhuongThucThanhToan());
            ps.setInt(8, hd.getIdKhachHang());
            ps.setInt(9, hd.getIdKhuyenMai());
            ps.setInt(10, hd.getIdHoaDon());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row > 0;
    }
//    

    public boolean insert(HoaDon hd) {
        Integer row = null;
        try {
            int trangThai = 0;
            String sql = "insert into HoaDon (id_nhanVien ,trangThai,ngayTao,tongTien,tienKhachDua,tienThua,phuongThucThanhToan,id_khachHang,id_khuyenMai) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setInt(2, trangThai);
            ps.setDate(3, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setNull(4, java.sql.Types.INTEGER); // Giá trị null cho tổng tiền (numeric)
            ps.setNull(5, java.sql.Types.INTEGER); // Giá trị null cho tiền khách đưa (numeric)
            ps.setNull(6, java.sql.Types.INTEGER); // Giá trị null cho tiền thừa (numeric)
            ps.setNull(7, java.sql.Types.INTEGER);// Giá trị null cho phương thức thanh toán (integer)
            ps.setNull(8, java.sql.Types.INTEGER); // Giá trị null cho id khách hàng (integer)
            ps.setNull(9, java.sql.Types.INTEGER); // Giá trị null cho id khuyến mãi (integer)

            ps.execute();
            row = ps.getUpdateCount();
            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    public ArrayList<HoaDon> locTrangThai(int trangThai) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "select id_hoaDon, ngayTao, trangThai from hoaDon where trangThai = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, trangThai);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdHoaDon(rs.getInt("id_hoaDon"));
                hoaDon.setNgayTao(rs.getDate("ngayTao"));
                hoaDon.setTrangThai(rs.getInt("trangThai"));
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public String updateTrangThaiHoaDon(HoaDon hd) {
//        try {
//            String sql = "UPDATE HoaDon SET trangThai = ?, tongTien = ?, tienKhachDua = ?, tienThua = ?, phuongThucThanhToan = ?, Id_khachHang = ?, id_khuyenMai = ?"
//                    + " WHERE id_hoaDon = ?";
//            PreparedStatement pre = this.conn.prepareStatement(sql);
//
//            int trangThai=1;
//            pre.setInt(1, trangThai);
//            pre.setBigDecimal(2, hd.getTongTien());
//            pre.setBigDecimal(3, hd.getTienKhachDua());
//            pre.setBigDecimal(4, hd.getTienThua());
//            pre.setInt(5, hd.getPhuongThucThanhToan());
//            pre.setInt(6, hd.getIdKhachHang());
//            pre.setInt(7, hd.getIdKhuyenMai());
//            pre.setInt(8, raven.application.form.other.FormBanHang.idHoaDon);
//
//            // Thực thi câu lệnh update
//            pre.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error: " + e.getMessage();
//        }
//        return "Done";
//    }
   public List<HoaDon> timKiemTheoNgay(Date ngayBatDau) {
        ArrayList<HoaDon> list = new ArrayList<>();
            try {
                String sql = "SELECT * FROM hoaDon WHERE ngayTao = ?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setDate(1, ngayBatDau);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setIdHoaDon(rs.getInt("id_hoaDon"));
                    hoaDon.setIdNhanVien(rs.getInt("id_nhanVien"));
                    hoaDon.setTrangThai(rs.getInt("trangThai"));
                    hoaDon.setNgayTao(rs.getDate("ngayTao"));
                    hoaDon.setTongTien(rs.getBigDecimal("tongTien"));
                    hoaDon.setTienKhachDua(rs.getBigDecimal("tienKhachDua"));
                    hoaDon.setTienThua(rs.getBigDecimal("tienThua"));
                    hoaDon.setPhuongThucThanhToan(rs.getInt("phuongThucThanhToan"));
                    hoaDon.setIdKhachHang(rs.getInt("id_khachHang"));
                    hoaDon.setIdKhuyenMai(rs.getInt("id_khuyenMai"));
                    list.add(hoaDon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    public class HoaDonDAO {
        // Khai báo biến kết nối và các phương thức khác

        public ArrayList<HoaDon> timKiemTheoNgay(Date ngayBatDau) {
            ArrayList<HoaDon> list = new ArrayList<>();
            try {
                String sql = "SELECT * FROM hoaDon WHERE ngayTao = ?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setDate(1, ngayBatDau);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setIdHoaDon(rs.getInt("id_hoaDon"));
                    hoaDon.setIdNhanVien(rs.getInt("id_nhanVien"));
                    hoaDon.setTrangThai(rs.getInt("trangThai"));
                    hoaDon.setNgayTao(rs.getDate("ngayTao"));
                    hoaDon.setTongTien(rs.getBigDecimal("tongTien"));
                    hoaDon.setTienKhachDua(rs.getBigDecimal("tienKhachDua"));
                    hoaDon.setTienThua(rs.getBigDecimal("tienThua"));
                    hoaDon.setPhuongThucThanhToan(rs.getInt("phuongThucThanhToan"));
                    hoaDon.setIdKhachHang(rs.getInt("id_khachHang"));
                    hoaDon.setIdKhuyenMai(rs.getInt("id_khuyenMai"));
                    list.add(hoaDon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    }

}

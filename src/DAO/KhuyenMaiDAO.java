/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.HoaDon;
import Model.KhuyenMai;
import Model.SanPhamChiTiet;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhuyenMaiDAO {

    private Connection conn;

    public KhuyenMaiDAO() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhuyenMai> findAll() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        try {
            String sql = "select * from khuyenMai";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int idKhuyenMai = rs.getInt("id_khuyenMai");
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                String loai = rs.getString("loai");
                float giaTri = rs.getFloat("giaTri");
                Date ngayBatDau = rs.getDate("ngayBatDau");
                Date ngayKetThuc = rs.getDate("ngayKetThuc");
                int trangThai = rs.getInt("trangThai");
                KhuyenMai km = new KhuyenMai(idKhuyenMai, tenKhuyenMai, loai, giaTri, ngayBatDau, ngayKetThuc, trangThai);
                list.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addKM(KhuyenMai km) {
        String sql = "INSERT INTO khuyenMai (tenKhuyenMai, loai, giaTri,ngayBatDau,ngayKetThuc,trangThai) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, km.getTenKM());
            pstm.setString(2, km.getLoai());
            pstm.setFloat(3, km.getGiaTri());
            pstm.setDate(4, km.getNgayBatDau());
            pstm.setDate(5, km.getNgayKetThuc());
            pstm.setInt(6, km.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public List<KhuyenMai> getByTrangThai(int trangThai) {
        List<KhuyenMai> listKM = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT * FROM khuyenMai WHERE trangThai = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, trangThai);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int idKhuyenMai = rs.getInt("id_khuyenMai");
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                String loai = rs.getString("loai");
                float giaTri = rs.getFloat("giaTri");
                Date ngayBatDau = rs.getDate("ngayBatDau");
                Date ngayKetThuc = rs.getDate("ngayKetThuc");
                KhuyenMai km = new KhuyenMai(idKhuyenMai, tenKhuyenMai, loai, giaTri, ngayBatDau, ngayKetThuc, trangThai);
                listKM.add(km);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKM;
    }

    public List<KhuyenMai> getByTen(String tenKM) {
        List<KhuyenMai> listKM = new ArrayList<>();
        try {
            String sql = "select * from khuyenMai where tenKhuyenMai like ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenKM + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int idKhuyenMai = rs.getInt("id_khuyenMai");
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                String loai = rs.getString("loai");
                float giaTri = rs.getFloat("giaTri");
                Date ngayBatDau = rs.getDate("ngayBatDau");
                Date ngayKetThuc = rs.getDate("ngayKetThuc");
                int trangThai = rs.getInt("trangThai");
                KhuyenMai km = new KhuyenMai(idKhuyenMai, tenKhuyenMai, loai, giaTri, ngayBatDau, ngayKetThuc, trangThai);
                listKM.add(km);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listKM;
    }

    public String update(KhuyenMai km) {
        String sql = "UPDATE khuyenMai SET tenKhuyenMai = ?, loai = ?, giaTri = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ? WHERE id_khuyenMai = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, km.getTenKM());
            pstm.setString(2, km.getLoai());
            pstm.setFloat(3, km.getGiaTri());
            pstm.setDate(4, km.getNgayBatDau());
            pstm.setDate(5, km.getNgayKetThuc());
            pstm.setInt(6, km.getTrangThai());
            pstm.setInt(7, km.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }

    public List<SanPhamChiTiet> findAllSP() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    spct.id_sanPhamCt,\n"
                    + "    sp.id_sanPham,\n"
                    + "    sp.tenSanPham,\n"
                    + "    spct.gia,\n"
                    + "    kt.id_kichThuoc,\n"
                    + "    kt.tenKichThuoc,\n"
                    + "    spct.soLuong\n"
                    + "FROM \n"
                    + "    [dbo].[sanPhamCt] spct\n"
                    + "JOIN \n"
                    + "    [dbo].[sanPham] sp ON spct.id_sanPham = sp.id_sanPham\n"
                    + "JOIN \n"
                    + "    [dbo].[kichThuoc] kt ON spct.id_kichThuoc = kt.id_kichThuoc"; // Đã sửa bí danh ở đây
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setId(rs.getInt("id_sanPhamCt"));
                sanPhamChiTiet.setIdSanPham(rs.getInt("id_sanPham"));
                sanPhamChiTiet.setTenSanPham(rs.getString("tenSanPham"));
                sanPhamChiTiet.setGia(rs.getFloat("gia"));
                sanPhamChiTiet.setSoLuong(rs.getInt("soLuong"));
                sanPhamChiTiet.setIdKichThuoc(rs.getInt("id_kichThuoc"));
                sanPhamChiTiet.setKichThuoc(rs.getInt("tenKichThuoc")); // Đã sửa phương thức này
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm cập nhật giá sản phẩm trong cơ sở dữ liệu
    public boolean updateGiaSPDatabase(SanPhamChiTiet sp) {
        String sql = "UPDATE sanPhamCt SET gia = ? WHERE id_sanPhamCt = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, sp.getGia());
            pstmt.setInt(2, sp.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu xảy ra lỗi
        }
    }

    public double findGiamGiaByTenKM(String tenKhuyenMai) {
        double giamGia = 0.0;
        try {
            String sql = "SELECT giaTri FROM khuyenMai WHERE tenKhuyenMai = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenKhuyenMai);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                giamGia = rs.getFloat("giaTri");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giamGia;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietService {

    private Connection conn;

    public SanPhamChiTietService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPhamChiTiet> findAll(int idSanPham) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    spct.id_sanPhamCt,\n"
                    + "    sp.id_sanPham,\n"
                    + "    sp.tenSanPham,\n"
                    + "    cl.tenChatLieu,\n"
                    + "    th.tenThuongHieu,\n"
                    + "    nc.tenNhaCungCap,\n"
                    + "    m.tenMau,\n"
                    + "    ld.tenLoaiDe,\n"
                    + "    kt.tenKichThuoc,\n"
                    + "    spct.gia,\n"
                    + "    spct.soLuong,\n"
                    + "    spct.ngayTao,\n"
                    + "    spct.ghiChu\n"
                    + "FROM \n"
                    + "    [dbo].[sanPhamCt] spct\n"
                    + "JOIN \n"
                    + "    [dbo].[sanPham] sp ON spct.id_sanPham = sp.id_sanPham\n"
                    + "JOIN \n"
                    + "    [dbo].[chatLieu] cl ON spct.id_chatLieu = cl.id_chatLieu\n"
                    + "JOIN \n"
                    + "  [dbo].[thuongHieu] th ON spct.id_thuongHieu = th.id_thuongHieu\n"
                    + "JOIN \n"
                    + "  [dbo].[nhaCungCap] nc ON spct.id_nhaCungCap = nc.id_nhaCungCap\n"
                    + "JOIN \n"
                    + "		  [dbo].[mau] m ON spct.id_mau = m.id_mau\n"
                    + "JOIN \n"
                    + "      [dbo].[loaiDe] ld ON spct.id_loaiDe = ld.id_loaiDe\n"
                    + "JOIN \n"
                    + "   [dbo].[kichThuoc] kt ON spct.id_kichThuoc = kt.id_kichThuoc\n"
                    + "   WHERE sp.id_sanPham = ? ";//AND xoaMem = 0
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idSanPham);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setId(rs.getInt("id_sanPhamCt"));
                sanPhamChiTiet.setIdSanPham(rs.getInt("id_sanPham"));
                sanPhamChiTiet.setTenChatLieu(rs.getString("tenChatLieu"));
                sanPhamChiTiet.setTenThuongHieu(rs.getString("tenThuongHieu"));
                sanPhamChiTiet.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
                sanPhamChiTiet.setTenMau(rs.getString("tenMau"));
                sanPhamChiTiet.setTenLoaiDe(rs.getString("tenLoaiDe"));
                sanPhamChiTiet.setKichThuoc(rs.getInt("tenKichThuoc"));
                sanPhamChiTiet.setGia(rs.getFloat("gia"));
                sanPhamChiTiet.setSoLuong(rs.getInt("soLuong"));
                sanPhamChiTiet.setNgayTao(rs.getDate("ngayTao"));
                sanPhamChiTiet.setGhiChu(rs.getString("ghiChu"));
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addSPCT(SanPhamChiTiet spct) {
        String sql = "INSERT INTO sanPhamCt "
                + "(id_sanPham, id_chatLieu, id_thuongHieu,"
                + " id_nhaCungCap, id_mau, id_loaiDe,"
                + " id_kichThuoc, gia, soLuong, ngayTao, ghiChu) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?,?);";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, spct.getIdSanPham());
            pstm.setInt(2, spct.getIdChatLieu());
            pstm.setInt(3, spct.getIdThuongHieu());
            pstm.setInt(4, spct.getIdNhaCungCap());
            pstm.setInt(5, spct.getIdMau());
            pstm.setInt(6, spct.getIdLoaiDe());
            pstm.setInt(7, spct.getIdKichThuoc());
            pstm.setFloat(8, spct.getGia());
            pstm.setInt(9, spct.getSoLuong());
            pstm.setDate(10, spct.getNgayTao());
            pstm.setString(11, spct.getGhiChu());
            pstm.executeUpdate();
            return "Thêm thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm thất bại: " + e.getMessage();
        }
    }

    public String updateSPCT(SanPhamChiTiet spct) {
        String sql = "UPDATE sanPhamCt set id_sanPham = ? , id_chatLieu = ? , id_thuongHieu = ?, id_nhaCungCap = ?,"
                + "id_mau = ?, id_loaiDe = ? , id_kichThuoc = ? , gia = ?, soLuong = ?,"
                + "ngayTao = ?, ghiChu = ? Where id_sanPhamCt = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, spct.getIdSanPham());
            pstm.setInt(2, spct.getIdChatLieu());
            pstm.setInt(3, spct.getIdThuongHieu());
            pstm.setInt(4, spct.getIdNhaCungCap());
            pstm.setInt(5, spct.getIdMau());
            pstm.setInt(6, spct.getIdLoaiDe());
            pstm.setInt(7, spct.getIdKichThuoc());
            pstm.setFloat(8, spct.getGia());
            pstm.setInt(9, spct.getSoLuong());
            pstm.setDate(10, spct.getNgayTao());
            pstm.setString(11, spct.getGhiChu());
            pstm.setInt(12, spct.getId());
            pstm.executeUpdate();
            return "Sửa thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sửa thất bại: " + e.getMessage();
        }
    }

    public List<SanPhamChiTiet> loadFormSale() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    spct.id_sanPhamCt,\n"
                    + "    sp.id_sanPham,\n"
                    + "    sp.tenSanPham,\n"
                    + "    cl.tenChatLieu,\n"
                    + "    th.tenThuongHieu,\n"
                    + "    nc.tenNhaCungCap,\n"
                    + "    m.tenMau,\n"
                    + "    ld.tenLoaiDe,\n"
                    + "    kt.tenKichThuoc,\n"
                    + "    spct.gia,\n"
                    + "    spct.soLuong,\n"
                    + "    spct.ngayTao,\n"
                    + "    spct.ghiChu\n"
                    + "FROM \n"
                    + "    [dbo].[sanPhamCt] spct\n"
                    + "JOIN \n"
                    + "    [dbo].[sanPham] sp ON spct.id_sanPham = sp.id_sanPham\n"
                    + "JOIN \n"
                    + "    [dbo].[chatLieu] cl ON spct.id_chatLieu = cl.id_chatLieu\n"
                    + "JOIN \n"
                    + "  [dbo].[thuongHieu] th ON spct.id_thuongHieu = th.id_thuongHieu\n"
                    + "JOIN \n"
                    + "  [dbo].[nhaCungCap] nc ON spct.id_nhaCungCap = nc.id_nhaCungCap\n"
                    + "JOIN \n"
                    + "		  [dbo].[mau] m ON spct.id_mau = m.id_mau\n"
                    + "JOIN \n"
                    + "      [dbo].[loaiDe] ld ON spct.id_loaiDe = ld.id_loaiDe\n"
                    + "JOIN \n"
                    + "   [dbo].[kichThuoc] kt ON spct.id_kichThuoc = kt.id_kichThuoc\n";//AND xoaMem = 0
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setId(rs.getInt("id_sanPhamCt"));
                sanPhamChiTiet.setTenSanPham(rs.getString("tenSanPham"));
                sanPhamChiTiet.setIdSanPham(rs.getInt("id_sanPham"));
                sanPhamChiTiet.setTenChatLieu(rs.getString("tenChatLieu"));
                sanPhamChiTiet.setTenThuongHieu(rs.getString("tenThuongHieu"));
                sanPhamChiTiet.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
                sanPhamChiTiet.setTenMau(rs.getString("tenMau"));
                sanPhamChiTiet.setTenLoaiDe(rs.getString("tenLoaiDe"));
                sanPhamChiTiet.setKichThuoc(rs.getInt("tenKichThuoc"));
                sanPhamChiTiet.setGia(rs.getFloat("gia"));
                sanPhamChiTiet.setSoLuong(rs.getInt("soLuong"));
                sanPhamChiTiet.setNgayTao(rs.getDate("ngayTao"));
                sanPhamChiTiet.setGhiChu(rs.getString("ghiChu"));
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPhamChiTiet> getSPCtInHoaDon(int index) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    SanPhamCt.id_sanPhamCt,\n"
                    + "    SanPham.tenSanPham,\n"
                    + "    hoaDonCT.soLuong,\n"
                    + "    hoaDonCT.donGia\n"
                    + "FROM \n"
                    + "    hoaDonCT\n"
                    + "JOIN \n"
                    + "    SanPhamCt ON hoaDonCT.id_sanPhamCt = SanPhamCt.id_sanPhamCt\n"
                    + "JOIN \n"
                    + "    SanPham ON SanPhamCt.id_sanPham = SanPham.id_sanPham\n"
                    + "WHERE \n"
                    + "    hoaDonCT.id_hoaDon = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, index);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setId(rs.getInt("id_sanPhamCt"));
                sanPhamChiTiet.setTenSanPham(rs.getString("tenSanPham"));
                sanPhamChiTiet.setSoLuong(rs.getInt("soLuong"));
                sanPhamChiTiet.setGia(rs.getFloat("donGia"));
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String updateCTSPInGioHang(SanPhamChiTiet sp) {
        String sql = "Update sanPhamCt set soLuong = ? WHERE id_sanPhamCt = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sp.getSoLuong());
            pstm.setInt(2, sp.getId());
            conn.commit();
            pstm.executeUpdate();
            return "Sửa thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sửa số lượng thất bại: " + e.getMessage();
        }
    }

    public List<SanPhamChiTiet> timKiemTenSP(String tenSP) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT spct.id_sanPhamCt, "
                    + "sp.id_sanPham, "
                    + "sp.tenSanPham, "
                    + "cl.tenChatLieu, "
                    + "th.tenThuongHieu, "
                    + "nc.tenNhaCungCap, "
                    + "m.tenMau, "
                    + "ld.tenLoaiDe, "
                    + "kt.tenKichThuoc, "
                    + "spct.gia, "
                    + "spct.soLuong, "
                    + "spct.ngayTao, "
                    + "spct.ghiChu "
                    + "FROM [dbo].[sanPhamCt] spct "
                    + "JOIN [dbo].[sanPham] sp ON spct.id_sanPham = sp.id_sanPham "
                    + "JOIN [dbo].[chatLieu] cl ON spct.id_chatLieu = cl.id_chatLieu "
                    + "JOIN [dbo].[thuongHieu] th ON spct.id_thuongHieu = th.id_thuongHieu "
                    + "JOIN [dbo].[nhaCungCap] nc ON spct.id_nhaCungCap = nc.id_nhaCungCap "
                    + "JOIN [dbo].[mau] m ON spct.id_mau = m.id_mau "
                    + "JOIN [dbo].[loaiDe] ld ON spct.id_loaiDe = ld.id_loaiDe "
                    + "JOIN [dbo].[kichThuoc] kt ON spct.id_kichThuoc = kt.id_kichThuoc "
                    + "WHERE sp.tenSanPham LIKE ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + tenSP + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setId(rs.getInt("id_sanPhamCt"));
                sanPhamChiTiet.setIdSanPham(rs.getInt("id_sanPham"));
                sanPhamChiTiet.setTenSanPham(rs.getString("tenSanPham"));
                sanPhamChiTiet.setTenChatLieu(rs.getString("tenChatLieu"));
                sanPhamChiTiet.setTenThuongHieu(rs.getString("tenThuongHieu"));
                sanPhamChiTiet.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
                sanPhamChiTiet.setTenMau(rs.getString("tenMau"));
                sanPhamChiTiet.setTenLoaiDe(rs.getString("tenLoaiDe"));
                sanPhamChiTiet.setKichThuoc(rs.getInt("tenKichThuoc"));
                sanPhamChiTiet.setGia(rs.getFloat("gia"));
                sanPhamChiTiet.setSoLuong(rs.getInt("soLuong"));
                sanPhamChiTiet.setNgayTao(rs.getDate("ngayTao"));
                sanPhamChiTiet.setGhiChu(rs.getString("ghiChu"));
                list.add(sanPhamChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

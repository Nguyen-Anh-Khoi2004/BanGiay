/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamService {

    private Connection conn;

    public SanPhamService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> findAll() {
        List<SanPham> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    sp.id_sanPham,\n"
                    + "    sp.tenSanPham,\n"
                    + "    sp.trangThai,\n"
                    + "    sp.hinhAnh,\n"
                    + "    tl.tenTheLoai\n"
                    + "FROM \n"
                    + "    sanPham sp\n"
                    + "JOIN \n"
                    + "    theloai tl ON sp.id_theLoai = tl.id_theLoai;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("id_sanPham"));
                sanPham.setTenSanPham(rs.getString("tenSanPham"));
                sanPham.setTrangThai(rs.getInt("trangThai"));
                sanPham.setTenTheLoai(rs.getString("tenTheLoai"));
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addSanPham(SanPham sanPham) {
        String sql = "INSERT INTO sanPham (tenSanPham, trangThai, hinhAnh, id_theLoai)"
                + " VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, sanPham.getTenSanPham());
            pstm.setInt(2, sanPham.getTrangThai());
            pstm.setString(3, sanPham.getHinhAnh());
            pstm.setInt(4, sanPham.getIdTheLoai());
            pstm.executeUpdate();
            return "Thêm thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm thất bại: " + e.getMessage();
        }
    }

    public String updateSanPham(SanPham sanPham) {
        String sql = "UPDATE sanPham SET tenSanPham = ?, trangThai = ?, hinhAnh = ?, id_theLoai = ? WHERE id_sanPham = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, sanPham.getTenSanPham());
            pstm.setInt(2, sanPham.getTrangThai());
            pstm.setString(3, sanPham.getHinhAnh());
            pstm.setInt(4, sanPham.getIdTheLoai());
            pstm.setInt(5, sanPham.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KichThuocService {

    private Connection conn;

    public KichThuocService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KichThuoc> findAll() {
        List<KichThuoc> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM kichThuoc";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_kichThuoc");
                int ten = rs.getInt("tenKichThuoc");
                int trangThai = rs.getInt("trangThai");
                KichThuoc kichThuoc = new KichThuoc(id, ten, trangThai);
                list.add(kichThuoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addKichThuoc(KichThuoc kichThuoc) {
        String sql = "INSERT INTO kichThuoc (tenKichThuoc, trangThai) VALUES (?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, kichThuoc.getTenKichThuoc());
            pstm.setInt(2, kichThuoc.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateLoaiDe(KichThuoc kichThuoc) {
        String sql = "UPDATE kichThuoc set tenKichThuoc = ?, trangThai = ? WHERE id_kichThuoc = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, kichThuoc.getTenKichThuoc());
            pstm.setInt(2, kichThuoc.getTrangThai());
            pstm.setInt(3, kichThuoc.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();

        }
    }
         public int layTrangThai(String tenKichThuoc) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM kichThuoc WHERE tenKichThuoc = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenKichThuoc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                trangThai = rs.getInt("trangThai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }
}

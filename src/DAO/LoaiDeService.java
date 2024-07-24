/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.LoaiDe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoaiDeService {

    private Connection conn;

    public LoaiDeService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LoaiDe> findAll() {
        List<LoaiDe> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM loaiDe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_loaiDe");
                String ten = rs.getString("tenLoaiDe");
                int trangThai = rs.getInt("trangThai");
                LoaiDe loaiDe = new LoaiDe(id, ten, trangThai);
                list.add(loaiDe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addLoaiDe(LoaiDe loaiDe) {
        String sql = "INSERT INTO loaiDe (tenLoaiDe, trangThai) VALUES (?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, loaiDe.getTenLoaiDe());
            pstm.setInt(2, loaiDe.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateLoaiDe(LoaiDe loaiDe) {
        String sql = "UPDATE loaiDe set tenLoaiDe = ?, trangThai = ? WHERE id_loaiDe = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, loaiDe.getTenLoaiDe());
            pstm.setInt(2, loaiDe.getTrangThai());
            pstm.setInt(3, loaiDe.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
     public int layTrangThai(String tenLoaiDe) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM loaiDe WHERE tenLoaiDe = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenLoaiDe);
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

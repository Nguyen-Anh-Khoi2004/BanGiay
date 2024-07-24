/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Mau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MauService {

    private Connection conn;

    public MauService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Mau> findAll() {
        List<Mau> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mau";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_mau");
                String ten = rs.getString("tenMau");
                int trangThai = rs.getInt("trangThai");
                Mau mau = new Mau(id, ten, trangThai);
                list.add(mau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addMau(Mau mau) {
        String sql = "INSERT INTO mau (tenMau, trangThai)\n"
                + "VALUES(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mau.getTenMauSac());
            pstm.setInt(2, mau.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateMau(Mau mau) {
        String sql = "UPDATE mau SET tenMau = ?, trangThai = ? WHERE id_mau = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mau.getTenMauSac());
            pstm.setInt(2, mau.getTrangThai());
            pstm.setInt(3, mau.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
     public int layTrangThai(String tenMau) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM mau WHERE tenMau = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenMau);
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

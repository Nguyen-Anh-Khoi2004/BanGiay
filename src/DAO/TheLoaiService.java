/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Mau;
import Model.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TheLoaiService {

    private Connection conn;

    public TheLoaiService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TheLoai> findAll() {
        List<TheLoai> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM theloai";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_theLoai");
                String ten = rs.getString("tenTheLoai");
                int trangThai = rs.getInt("trangThai");
                TheLoai theLoai = new TheLoai(id, ten, trangThai);
                list.add(theLoai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addTheLoai(TheLoai theLoai) {
        String sql = "INSERT INTO theLoai (tenTheLoai, trangThai)\n"
                + "VALUES(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, theLoai.getTenTheLoai());
            pstm.setInt(2, theLoai.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateTheLoai(TheLoai theLoai) {
        String sql = "UPDATE theLoai SET tenTheLoai = ?, trangThai = ? WHERE id_theLoai = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, theLoai.getTenTheLoai());
            pstm.setInt(2, theLoai.getTrangThai());
            pstm.setInt(3, theLoai.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
     public int layTrangThai(String tenTheLoai) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM theloai WHERE tenTheLoai = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenTheLoai);
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

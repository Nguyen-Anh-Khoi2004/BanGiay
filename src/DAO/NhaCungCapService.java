/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhaCungCapService {

    private Connection conn;

    public NhaCungCapService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhaCungCap> findAll() {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM nhaCungCap";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_nhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                int trangThai = rs.getInt("trangThai");
                NhaCungCap nhaCungCap = new NhaCungCap(id, ten, trangThai);
                list.add(nhaCungCap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addNhaCungCap(NhaCungCap nhaCungCap) {
        String sql = "INSERT INTO nhaCungCap (tenNhaCungCap, trangThai)\n"
                + "VALUES(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nhaCungCap.getTenNhaCungCap());
            pstm.setInt(2, nhaCungCap.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateNhaCungCap(NhaCungCap nhaCungCap) {
        String sql = "UPDATE nhaCungCap SET tenNhaCungCap = ?, trangThai = ? WHERE id_nhaCungCap = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nhaCungCap.getTenNhaCungCap());
            pstm.setInt(2, nhaCungCap.getTrangThai());
            pstm.setInt(3, nhaCungCap.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
    public int layTrangThai(String tenNhaCungCap) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM nhaCungCap WHERE tenNhaCungCap = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenNhaCungCap);
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

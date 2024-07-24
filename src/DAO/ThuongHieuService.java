/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ThuongHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuService {

    private Connection conn;

    public ThuongHieuService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ThuongHieu> findAll() {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM thuongHieu";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_thuongHieu");
                String ten = rs.getString("tenThuongHieu");
                int trangThai = rs.getInt("trangThai");
                ThuongHieu thuongHieu = new ThuongHieu(id, ten, trangThai);
                list.add(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addThuongHieu(ThuongHieu thuongHieu) {
        String sql = "INSERT INTO thuongHieu (tenThuongHieu, trangThai)\n"
                + "VALUES(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, thuongHieu.getTenThuongHieu());
            pstm.setInt(2, thuongHieu.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateThuongHieu(ThuongHieu thuongHieu) {
        String sql = "UPDATE thuongHieu SET tenThuongHieu = ?, trangThai = ? WHERE id_thuongHieu = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, thuongHieu.getTenThuongHieu());
            pstm.setInt(2, thuongHieu.getTrangThai());
            pstm.setInt(3, thuongHieu.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }
    public int layTrangThai(String tenThuongHieu) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM thuongHieu WHERE tenThuongHieu = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenThuongHieu);
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

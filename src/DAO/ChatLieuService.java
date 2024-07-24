/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChatLieuService {

    private Connection conn;

    public ChatLieuService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChatLieu> findAll() {
        List<ChatLieu> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chatLieu";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_chatLieu");
                String ten = rs.getString("tenChatLieu");
                int trangThai = rs.getInt("trangThai");
                ChatLieu chatLieu = new ChatLieu(id, ten, trangThai);
                list.add(chatLieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addChatLieu(ChatLieu chatLieu) {
        String sql = "INSERT INTO chatLieu (tenChatLieu, trangThai)\n"
                + "VALUES(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, chatLieu.getTenChatLieu());
            pstm.setInt(2, chatLieu.getTrangThai());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }

    public String updateChatLieu(ChatLieu chatLieu) {
        String sql = "UPDATE chatLieu SET tenChatLieu = ?, trangThai = ? WHERE id_chatLieu = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, chatLieu.getTenChatLieu());
            pstm.setInt(2, chatLieu.getTrangThai());
            pstm.setInt(3, chatLieu.getId());
            pstm.executeUpdate();
            return "Update thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update thất bại: " + e.getMessage();
        }
    }

    public int layTrangThai(String tenChatLieu) {
        int trangThai = -1;
        String sql = "SELECT trangThai FROM chatLieu WHERE tenChatLieu = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tenChatLieu);
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

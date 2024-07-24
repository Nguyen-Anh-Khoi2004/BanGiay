/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DoanhThuDAO {

    private Connection conn;

    public DoanhThuDAO() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Float DoanhThuToday() {
        float doanhThu = 0;
        String sql = "SELECT SUM(tongTien) AS tongTien FROM hoaDon WHERE CONVERT(date, ngayTao) = CONVERT(date, GETDATE()) AND trangThai = 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                doanhThu = rs.getFloat("tongTien");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doanhThu;
    }

    public Float DoanhThuMonth(int month, int year) {
        float doanhThu = 0;
        String sql = "SELECT SUM(tongTien) AS tongTien FROM hoaDon WHERE MONTH(ngayTao) = ? AND YEAR(ngayTao) = ? AND trangThai = 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    doanhThu = rs.getFloat("tongTien");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doanhThu;
    }

    public Float DoanhThuYear(int year) {
        float doanhThu = 0;
        String sql = "SELECT SUM(tongTien) AS tongTien FROM hoaDon WHERE YEAR(ngayTao) = ? AND trangThai = 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    doanhThu = rs.getFloat("tongTien");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doanhThu;
    }

    public Float TongDoanhThu() {
        float doanhThu = 0;
        String sql = "SELECT SUM(tongTien) AS tongTien FROM hoaDon WHERE trangThai = 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                doanhThu = rs.getFloat("tongTien");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doanhThu;
    }
}

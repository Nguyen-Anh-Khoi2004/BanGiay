/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.KhachHang;
import DAO.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author EmDong
 */
public class KhachHangDAO {

    private static Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public KhachHangDAO() {
        try {
            con = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        sql = "select * from khachhang";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hoTen, diaChi;
                int id = rs.getInt(1);
                hoTen = rs.getString(2);
                int gioiTinh = rs.getInt(3);
                diaChi = rs.getString(4);
                int soDienThoai = rs.getInt(5);
                listKH.add(new KhachHang(id, hoTen, gioiTinh, diaChi, soDienThoai));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
     public int themKH(KhachHang kh ) {
        sql = "insert into khachhang(hoten,gioiTinh,diachi,sodienthoai) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getGioiTinh());
            ps.setObject(3, kh.getDiaChi());
            ps.setObject(4, kh.getSoDienThoai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
     public int updateKH(KhachHang kh) {
    String sql = "UPDATE khachhang SET hoten = ?, gioiTinh = ?, diachi = ?, sodienthoai = ? WHERE id_khachhang = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, kh.getHoTen());
        ps.setObject(2, kh.getGioiTinh());
        ps.setObject(3, kh.getDiaChi());
        ps.setObject(4, kh.getSoDienThoai());
        ps.setObject(5, kh.getId());
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}

}

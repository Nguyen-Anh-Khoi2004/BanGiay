/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.KhachHang;
import Model.NhanVien;
import DAO.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author EmDong
 */
public class NhanVienDAO {

    private static Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public NhanVienDAO() {
        try {
            con = DBContext.getConnection();
        } catch (Exception e) {
        }
    }

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        sql = "select * from nhanvien";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hoTen, email, diaChi;
                int id = rs.getInt(1);
                hoTen = rs.getString(2);
                int gioiTinh = rs.getInt(3);
                int soDT = rs.getInt(4);
                email = rs.getString(5);
                diaChi = rs.getString(6);
                int trangThai = rs.getInt(8);
                listNV.add(new NhanVien(id, hoTen, gioiTinh, soDT, email, diaChi, trangThai));
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<NhanVien> getDangLam() {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        sql = "SELECT * FROM NhanVien WHERE TrangThai = 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hoTen, email, diaChi;
                int id = rs.getInt(1);
                hoTen = rs.getString(2);
                int gioiTinh = rs.getInt(3);
                int soDT = rs.getInt(4);
                email = rs.getString(5);
                diaChi = rs.getString(6);
                int trangThai = rs.getInt(8);
                listNV.add(new NhanVien(id, hoTen, gioiTinh, soDT, email, diaChi, trangThai));
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<NhanVien> getDaNghiViec() {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        sql = "SELECT * FROM NhanVien WHERE TrangThai = 0";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hoTen, email, diaChi;
                int id = rs.getInt(1);
                hoTen = rs.getString(2);
                int gioiTinh = rs.getInt(3);
                int soDT = rs.getInt(4);
                email = rs.getString(5);
                diaChi = rs.getString(6);
                int trangThai = rs.getInt(8);
                listNV.add(new NhanVien(id, hoTen, gioiTinh, soDT, email, diaChi, trangThai));
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public int themNV(NhanVien nv) {
        sql = "insert into nhanvien(hoten,gioiTinh,sodt,email,diachi,trangthai) values (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getHoTen());
            ps.setObject(2, nv.getGioiTinh());
            ps.setObject(3, nv.getSoDt());
            ps.setObject(4, nv.getEmail());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

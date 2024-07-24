/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.HoaDon;
import Model.HoaDonCT;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService {

    private Connection conn;

    public HoaDonChiTietService() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonCT> findAll() {
        ArrayList<HoaDonCT> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    hdc.id_hoaDonCt,\n"
                    + "    hdc.id_hoaDon,\n"
                    + "    hdc.soLuong,\n"
                    + "    hdc.ngayTao,\n"
                    + "    hdc.donGia,\n"
                    + "    hdc.tongTien,\n"
                    + "    hdc.id_sanPhamCt,\n"
                    + "    spc.id_sanPham,\n"
                    + "    sp.tenSanPham\n"
                    + "FROM \n"
                    + "    dbo.hoaDonCT AS hdc\n"
                    + "LEFT JOIN \n"
                    + "    dbo.sanPhamCt AS spc ON hdc.id_sanPhamCt = spc.id_sanPhamCt\n"
                    + "LEFT JOIN\n"
                    + "    dbo.sanPham AS sp ON spc.id_sanPham = sp.id_sanPham;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_hoaDonCt");
                int idHoaDon = rs.getInt("id_hoaDon");
                int soLuong = rs.getInt("soLuong");
                Date ngayTao = rs.getDate("ngayTao");
                float donGia = rs.getFloat("donGia");
                BigDecimal tongTien = rs.getBigDecimal("tongTien");
                int idSPCT = rs.getInt("id_sanPhamCt");
                int idSanPham = rs.getInt("id_sanPham");
                String tenSanPham = rs.getString("tenSanPham");
                HoaDonCT hoaDonCT = new HoaDonCT(id, idHoaDon, soLuong, ngayTao, donGia, tongTien, idSPCT, idSanPham, tenSanPham);
                list.add(hoaDonCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonCT> loadListHDCT(int id) {
        ArrayList<HoaDonCT> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    hdc.id_hoaDonCt,\n"
                    + "    hdc.id_hoaDon,\n"
                    + "    hdc.soLuong,\n"
                    + "    hdc.ngayTao,\n"
                    + "    hdc.donGia,\n"
                    + "    hdc.tongTien,\n"
                    + "    hdc.id_sanPhamCt,\n"
                    + "    spc.id_sanPham,\n"
                    + "    sp.tenSanPham\n"
                    + "FROM \n"
                    + "    dbo.hoaDonCT AS hdc\n"
                    + "LEFT JOIN \n"
                    + "    dbo.sanPhamCt AS spc ON hdc.id_sanPhamCt = spc.id_sanPhamCt\n"
                    + "LEFT JOIN\n"
                    + "    dbo.sanPham AS sp ON spc.id_sanPham = sp.id_sanPham "
                    + "WHERE id_hoaDon =?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                HoaDonCT hoaDonCT = new HoaDonCT();
                hoaDonCT.setId(rs.getInt("id_hoaDonCt"));
                hoaDonCT.setIdHoaDon(rs.getInt("id_hoaDon"));
                hoaDonCT.setSoLuong(rs.getInt("soLuong"));
                hoaDonCT.setNgayTao(rs.getDate("ngayTao"));
                hoaDonCT.setDonGia(rs.getFloat("donGia"));
                hoaDonCT.setTongTien(rs.getBigDecimal("tongTien"));
                hoaDonCT.setIdSPCT(rs.getInt("id_sanPhamCt"));
                hoaDonCT.setIdSanPham(rs.getInt("id_sanPham"));
                hoaDonCT.setTenSanPham(rs.getString("tenSanPham"));
                list.add(hoaDonCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void suaCTHD(int soLuong, int idSPCT) {
        try {
            String q = "UPDATE hoaDonCT set soLuong= ? where id_sanPhamCt = ?";
            PreparedStatement ps = conn.prepareCall(q);
            ps.setInt(1, soLuong);
            ps.setInt(2, idSPCT);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int idCTSanPham(int idCTSanPham) {
        int id = 0;
        try {
            String query = "SELECT * FROM sanPhamCt WHERE id_sanPhamCt=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCTSanPham);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                id = rs.getInt("id_sanPhamCt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String them(HoaDonCT hdct) {
        try {
            String sql = "INSERT INTO hoaDonCT (id_hoaDon, soLuong, ngayTao, donGia, tongTien, id_sanPhamCt)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, hdct.getIdHoaDon());
            ps.setInt(2, hdct.getSoLuong());
            ps.setDate(3, (java.sql.Date) hdct.getNgayTao());
            ps.setFloat(4, hdct.getDonGia());
            ps.setBigDecimal(5, hdct.getTongTien());
            ps.setInt(6, hdct.getIdSPCT());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Thất bại: " + e.getMessage();
        }
        return "Thành công";
    }

    
}

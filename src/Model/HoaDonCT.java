/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonCT {
    private Integer id;
    private int idHoaDon;
    private int soLuong;
    private Date ngayTao;
    private float donGia;
    private BigDecimal tongTien;
    private int idSPCT;
    private int idSanPham;
    private String tenSanPham;

    public HoaDonCT() {
    }

    public HoaDonCT(Integer id, int idHoaDon, int soLuong, Date ngayTao, float donGia, BigDecimal tongTien, int idSPCT, int idSanPham, String tenSanPham) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.idSPCT = idSPCT;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    
}

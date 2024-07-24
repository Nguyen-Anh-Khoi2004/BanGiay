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
public class HoaDon {

    private Integer idHoaDon;
    private int idNhanVien;
    private String tenNV;
    private int trangThai;
    private Date ngayTao;
    private BigDecimal tongTien;
    private BigDecimal tienKhachDua;
    private BigDecimal tienThua;
    private int phuongThucThanhToan;
    private int idKhachHang;
    private String tenKH;
    private int idKhuyenMai;
    private String tenKM;

    public HoaDon() {
    }

    public HoaDon(Integer idHoaDon, int idNhanVien, String tenNV, int trangThai, Date ngayTao, BigDecimal tongTien, BigDecimal tienKhachDua, BigDecimal tienThua, int phuongThucThanhToan, int idKhachHang, String tenKH, int idKhuyenMai, String tenKM) {
        this.idHoaDon = idHoaDon;
        this.idNhanVien = idNhanVien;
        this.tenNV = tenNV;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.idKhachHang = idKhachHang;
        this.tenKH = tenKH;
        this.idKhuyenMai = idKhuyenMai;
        this.tenKM = tenKM;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(BigDecimal tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public int getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(int phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(int idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }
    
}

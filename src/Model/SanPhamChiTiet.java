/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTiet {

    private Integer id;
    private Integer idSanPham;
    private String tenSanPham;
    private Integer idChatLieu;
    private String tenChatLieu;
    private Integer idThuongHieu;
    private String tenThuongHieu;
    private Integer idNhaCungCap;
    private String tenNhaCungCap;
    private Integer idMau;
    private String tenMau;
    private Integer idLoaiDe;
    private String tenLoaiDe;
    private Integer idKichThuoc;
    private int kichThuoc;
    private float gia;
    private int soLuong;
    private Date ngayTao;
    private String ghiChu;
    private float giaSauGiam;

    public SanPhamChiTiet() {
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "id=" + id + ", idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham + ", idChatLieu=" + idChatLieu + ", tenChatLieu=" + tenChatLieu + ", idThuongHieu=" + idThuongHieu + ", tenThuongHieu=" + tenThuongHieu + ", idNhaCungCap=" + idNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", idMau=" + idMau + ", tenMau=" + tenMau + ", idLoaiDe=" + idLoaiDe + ", tenLoaiDe=" + tenLoaiDe + ", idKichThuoc=" + idKichThuoc + ", kichThuoc=" + kichThuoc + ", gia=" + gia + ", soLuong=" + soLuong + ", ngayTao=" + ngayTao + ", ghiChu=" + ghiChu + '}';
    }

    public SanPhamChiTiet(Integer id, Integer idSanPham, String tenSanPham, Integer idChatLieu, String tenChatLieu, Integer idThuongHieu, String tenThuongHieu, Integer idNhaCungCap, String tenNhaCungCap, Integer idMau, String tenMau, Integer idLoaiDe, String tenLoaiDe, Integer idKichThuoc, int kichThuoc, float gia, int soLuong, Date ngayTao, String ghiChu) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idChatLieu = idChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.idNhaCungCap = idNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.idMau = idMau;
        this.tenMau = tenMau;
        this.idLoaiDe = idLoaiDe;
        this.tenLoaiDe = tenLoaiDe;
        this.idKichThuoc = idKichThuoc;
        this.kichThuoc = kichThuoc;
        this.gia = gia;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(Integer idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public Integer getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(Integer idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public Integer getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(Integer idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public Integer getIdMau() {
        return idMau;
    }

    public void setIdMau(Integer idMau) {
        this.idMau = idMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public Integer getIdLoaiDe() {
        return idLoaiDe;
    }

    public void setIdLoaiDe(Integer idLoaiDe) {
        this.idLoaiDe = idLoaiDe;
    }

    public String getTenLoaiDe() {
        return tenLoaiDe;
    }

    public void setTenLoaiDe(String tenLoaiDe) {
        this.tenLoaiDe = tenLoaiDe;
    }

    public Integer getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(Integer idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

     public float getGiaSauGiam() {
        return giaSauGiam;
    }

    public void setGiaSauGiam(float giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }
    

}

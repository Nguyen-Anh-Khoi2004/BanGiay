/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private Integer id;
    private String tenSanPham;
    private Integer trangThai;
    private String hinhAnh;
    private Integer idTheLoai;
    private String tenTheLoai;

    public SanPham() {
    }

    public SanPham(Integer id, String tenSanPham, Integer trangThai, String hinhAnh, Integer idTheLoai, String tenTheLoai) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.idTheLoai = idTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class KhuyenMai {
    private Integer id;
    private String tenKM;
    private String loai;
    private float giaTri;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(Integer id, String tenKM, String loai, float giaTri, Date ngayBatDau, Date ngayKetThuc, int trangThai) {
        this.id = id;
        this.tenKM = tenKM;
        this.loai = loai;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

 

}

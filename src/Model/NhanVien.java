/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EmDong
 */
public class NhanVien {

    public static DefaultTableModel getModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int id;
    private String hoTen;
    private int gioiTinh;
    private int soDt;
    private String email;
    private String diaChi;
    private int trangThai;

    public NhanVien() {
    }

    public NhanVien(int id, String hoTen, int gioiTinh, int soDt, String email, String diaChi, int trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDt = soDt;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSoDt() {
        return soDt;
    }

    public void setSoDt(int soDt) {
        this.soDt = soDt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class KichThuoc {
    private Integer id;
    private Integer tenKichThuoc;
    private Integer trangThai;
    public KichThuoc() {
    }

    public KichThuoc(Integer id, Integer tenKichThuoc, Integer trangThai) {
        this.id = id;
        this.tenKichThuoc = tenKichThuoc;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(Integer tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    
}

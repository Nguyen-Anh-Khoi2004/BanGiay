/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class LoaiDe {
    private Integer id;
    private String tenLoaiDe; 
    private Integer trangThai;
    public LoaiDe() {
    }

    public LoaiDe(Integer id, String tenLoaiDe, Integer trangThai) {
        this.id = id;
        this.tenLoaiDe = tenLoaiDe;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLoaiDe() {
        return tenLoaiDe;
    }

    public void setTenLoaiDe(String tenLoaiDe) {
        this.tenLoaiDe = tenLoaiDe;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    
   
}

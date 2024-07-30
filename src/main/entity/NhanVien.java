/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class NhanVien {

    private String IdNguoiDung;
    private String MaNhanVien;
    private String TenNhanVien;
    private boolean GioiTinh;
    private String SoDienThoai;
    private String CanCuocCongDan;
    private String DiaChi;
    private boolean TrangThai;
    private String NgayTao;
    private String NgayCapNhat;

    public NhanVien(String IdNguoiDung, String TenNhanVien, boolean GioiTinh, 
            String SoDienThoai, String DiaChi, String CanCuocCongDan, String NgayTao, String NgayCapNhat) {
        this.IdNguoiDung = IdNguoiDung;
        this.TenNhanVien = TenNhanVien;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.CanCuocCongDan = CanCuocCongDan;
        this.DiaChi = DiaChi;
        this.NgayTao = NgayTao;
        this.NgayCapNhat = NgayCapNhat;
    }

}

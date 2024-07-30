/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 *
 * @author ADMIN
 */
@AllArgsConstructor // contrutor full tham so 
@NoArgsConstructor // contrutor k tham so 
@Getter
@Setter 
@ToString 
@Builder // contructor tuy y tham so 
public class PhieuGiamGia {

    

    public PhieuGiamGia(int id, String ten_giam_gia, String mo_ta, Date ngay_tao) {
    }
    private int ID;
    private String Ma_Voucher;
    private Date Ngay_Bat_Dau;
    private Date Ngay_Het_Han;
    private String Loai_Giam_Gia;
    private Double Gia_Tri_Giam_Gia;
    private Double Gia_Tri_Don_Hang_Toi_Thieu;
    private int So_Lan_Su_Dung_Toi_Da;
    private int So_Lan_Su_Dung;
    private String Trang_Thai;
    private String Mo_ta;


//    public PhieuGiamGia() {
//    }
//
//    public PhieuGiamGia(int ID, String Ma_Voucher, Date Ngay_Bat_Dau, Date Ngay_Het_Han, String Loai_Giam_Gia, Double Gia_Tri_Giam_Gia, Double Gia_Tri_Don_Hang_Toi_Thieu, int So_Lan_Su_Dung_Toi_Da, int So_Lan_Su_Dung, String Trang_Thai, String Mo_ta, Date Ngay_tao, Date Ngay_Cap_Nhat) {
//        this.ID = ID;
//        this.Ma_Voucher = Ma_Voucher;
//        this.Ngay_Bat_Dau = Ngay_Bat_Dau;
//        this.Ngay_Het_Han = Ngay_Het_Han;
//        this.Loai_Giam_Gia = Loai_Giam_Gia;
//        this.Gia_Tri_Giam_Gia = Gia_Tri_Giam_Gia;
//        this.Gia_Tri_Don_Hang_Toi_Thieu = Gia_Tri_Don_Hang_Toi_Thieu;
//        this.So_Lan_Su_Dung_Toi_Da = So_Lan_Su_Dung_Toi_Da;
//        this.So_Lan_Su_Dung = So_Lan_Su_Dung;
//        this.Trang_Thai = Trang_Thai;
//        this.Mo_ta = Mo_ta;
//        this.Ngay_tao = Ngay_tao;
//        this.Ngay_Cap_Nhat = Ngay_Cap_Nhat;
//    }

    public PhieuGiamGia(int id, String ngay_bat_dau, String ngay_het_han, String loai_giam_gia, double gia_tri_giam_gia, double gia_tri_don_hang_toi_thieu, int so_lan_su_dung_toi_da, int so_lan_su_dung, String mo_ta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

   

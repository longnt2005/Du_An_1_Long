/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

/**
 *
 * @author admin
 */
public class ThongKe {
    private double tongTien;
    private String so_luong_hoa_don;
    private String so_luong_hoa_don_bi_huy;
    private String so_luong_khach_hang;

    public ThongKe() {
    }

    public ThongKe(double tongTien, String so_luong_hoa_don, String so_luong_hoa_don_bi_huy, String so_luong_khach_hang) {
        this.tongTien = tongTien;
        this.so_luong_hoa_don = so_luong_hoa_don;
        this.so_luong_hoa_don_bi_huy = so_luong_hoa_don_bi_huy;
        this.so_luong_khach_hang = so_luong_khach_hang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getSo_luong_hoa_don() {
        return so_luong_hoa_don;
    }

    public void setSo_luong_hoa_don(String so_luong_hoa_don) {
        this.so_luong_hoa_don = so_luong_hoa_don;
    }

    public String getSo_luong_hoa_don_bi_huy() {
        return so_luong_hoa_don_bi_huy;
    }

    public void setSo_luong_hoa_don_bi_huy(String so_luong_hoa_don_bi_huy) {
        this.so_luong_hoa_don_bi_huy = so_luong_hoa_don_bi_huy;
    }

    public String getSo_luong_khach_hang() {
        return so_luong_khach_hang;
    }

    public void setSo_luong_khach_hang(String so_luong_khach_hang) {
        this.so_luong_khach_hang = so_luong_khach_hang;
    }
    
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hangnt
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietRespone {
    private Integer ID;
    private String maSPCT;
    private String tenSP;
    private String thuongHieu;
    private String xuatXu;
    private String mauSac;
    private String kichThuoc;
    private String chatLieu;
    private String coAo;
    private String doDay;
    private String phongCach;
    private Double giaBan;
    private Integer soLuong;
    private boolean trangThai;
    
    
}

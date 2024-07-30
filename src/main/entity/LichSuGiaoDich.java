/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author admin
 */
// Them cac annotation (@) can thiet 
@AllArgsConstructor  // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter
@Setter 
@ToString
@Builder // contructor tuy y tham so 

public class LichSuGiaoDich {
    private Integer id;
    
    private String maNhanVien;
    
    private String maKhachHang;
    
    private String maHD;
    
    private String hoTen;
   
    private String soDienThoai;
    
    private String email;
    
    private String diaChi;
    
    private Double tongTien;
    
    private String ngayTao;
    
    private boolean  trangThai;
    
}

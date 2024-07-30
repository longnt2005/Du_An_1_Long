/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDonResponse {

    private Integer id;
   
    private String maHoaDon;
    
    private String ngayTao;
    
    private String ngayCapNhap;
    
    private Double tongTien;
    
    private String maNhanVien;
    
    private String hoTen;
    
    private String diaChi;
    
    private String SDT;
    
    private Integer trangThai;
    
    private Integer hinhThucTT;
    

 

   
}

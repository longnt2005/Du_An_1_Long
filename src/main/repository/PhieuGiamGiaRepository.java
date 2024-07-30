/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import com.Product.form.GiamGiaForm;
import com.sun.tools.javap.TryBlockWriter;
import main.config.DBConnect;
import main.entity.PhieuGiamGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuGiamGiaRepository {
  
    public ArrayList<PhieuGiamGia> getAll() throws SQLException{
        String sql = "select id, ma_voucher, ngay_bat_dau,ngay_het_han,loai_giam_gia,gia_tri_giam_gia,gia_tri_don_hang_toi_thieu,so_lan_su_dung_toi_da,so_lan_su_dung,trang_thai,mo_ta from Voucher"; 
        try(Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<PhieuGiamGia> list = new ArrayList<>();
            while (rs.next()) {                
                PhieuGiamGia PGG = new PhieuGiamGia(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getDate(4), 
                        rs.getString(5), 
                        rs.getDouble(6),rs.getDouble(7),rs.getInt(8),
                        rs.getInt(9),rs.getString(10),
                        rs.getString(11));
                          list.add(PGG);    
            }
            return list;
        }catch(Exception e){
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
   public boolean add(PhieuGiamGia phieu){
       int check = 0;
       String sql = "insert into Voucher (ngay_bat_dau,ngay_het_han,loai_giam_gia,gia_tri_giam_gia,gia_tri_don_hang_toi_thieu,so_lan_su_dung_toi_da,so_lan_su_dung,mo_ta)\n" +
"values (?,?,?,?,?,?,?,?);";
       try (Connection con = DBConnect.getConnection();
               PreparedStatement ps = con.prepareStatement(sql)){
           ps.setObject(1,phieu.getNgay_Bat_Dau());
           ps.setObject(2,phieu.getNgay_Het_Han());
           ps.setObject(3,phieu.getLoai_Giam_Gia());
           ps.setObject(4,phieu.getGia_Tri_Giam_Gia());
           ps.setObject(5,phieu.getGia_Tri_Don_Hang_Toi_Thieu());
           ps.setObject(6,phieu.getSo_Lan_Su_Dung_Toi_Da());
           ps.setObject(7,phieu.getSo_Lan_Su_Dung());
           ps.setObject(8,phieu.getMo_ta());

           check = ps.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
        return check > 0;
       
   }
   public boolean update(PhieuGiamGia phieu, Integer id){
       int check = 0;
       String sql = "Update Voucher\n" +
"Set ngay_bat_dau = ?, ngay_het_han = ?, loai_giam_gia = ?, gia_tri_giam_gia = ?, gia_tri_don_hang_toi_thieu = ?, so_lan_su_dung_toi_da = ?, so_lan_su_dung = ?, mo_ta = ?\n" +
"WHERE id = ?;";
        try (Connection con = DBConnect.getConnection();
               PreparedStatement ps = con.prepareStatement(sql)){
           ps.setObject(1,phieu.getNgay_Bat_Dau());
           ps.setObject(2,phieu.getNgay_Het_Han());
           ps.setObject(3,phieu.getLoai_Giam_Gia());
           ps.setObject(4,phieu.getGia_Tri_Giam_Gia());
           ps.setObject(5,phieu.getGia_Tri_Don_Hang_Toi_Thieu());
           ps.setObject(6,phieu.getSo_Lan_Su_Dung_Toi_Da());
           ps.setObject(7,phieu.getSo_Lan_Su_Dung());
           ps.setObject(8,phieu.getMo_ta());
           ps.setObject(9, id);
           check = ps.executeUpdate();
           
       } catch (Exception e) {
           e.printStackTrace();
       }
        return check > 0;
       
   }
   private void loadVouchers() {
        ArrayList<PhieuGiamGia> vouchers = new ArrayList<>();
        String sql = "SELECT id, ngay_bat_dau, ngay_het_han, loai_giam_gia, gia_tri_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, so_lan_su_dung, mo_ta "
                   + "FROM Voucher ORDER BY id DESC"; // Sắp xếp theo id giảm dần để mục mới nhất lên đầu
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ngay_bat_dau = rs.getString("ngay_bat_dau");
                String ngay_het_han = rs.getString("ngay_het_han");
                String loai_giam_gia = rs.getString("loai_giam_gia");
                double gia_tri_giam_gia = rs.getDouble("gia_tri_giam_gia");
                double gia_tri_don_hang_toi_thieu = rs.getDouble("gia_tri_don_hang_toi_thieu");
                int so_lan_su_dung_toi_da = rs.getInt("so_lan_su_dung_toi_da");
                int so_lan_su_dung = rs.getInt("so_lan_su_dung");
                String mo_ta = rs.getString("mo_ta");

                PhieuGiamGia voucher = new PhieuGiamGia(id, ngay_bat_dau, ngay_het_han, loai_giam_gia, gia_tri_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, so_lan_su_dung, mo_ta);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        tableModel.setVouchers(vouchers);
    }
}
  
      


   
    
    



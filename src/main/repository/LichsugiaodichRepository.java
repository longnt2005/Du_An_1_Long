/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;
import main.entity.LichSuGiaoDich;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;
/**
 *
 * @author admin
 */
public class LichsugiaodichRepository {
    public ArrayList<LichSuGiaoDich> getAll() {
        // B1: Tao cau SQL 
        String sql = "SELECT    dbo.HoaDon.id, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ma_khach_hang, dbo.HoaDon.ma_hoa_don, dbo.KhachHang.ho_ten, dbo.KhachHang.so_dien_thoai, dbo.KhachHang.email, dbo.KhachHang.dia_chi, \n"
                + "                      dbo.HoaDon.tong_tien, dbo.HoaDon.ngay_tao, dbo.HoaDon.trang_thai\n"
                + "FROM         dbo.HoaDon INNER JOIN\n"
                + "                      dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id INNER JOIN\n"
                + "                      dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id";

                     
         ArrayList<LichSuGiaoDich> lists = new ArrayList<>();             
        // B2: Mo cong ket noi 
        // try...with..resource => Tu dong cong ket noi sql
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
           
            while (rs.next()) {                
                lists.add(new LichSuGiaoDich(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                         rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), rs.getBoolean(11)));
            }
           
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return lists;
    }
    
    public ArrayList<LichSuGiaoDich> getByIDKhachHang(Integer id_khachhang) {
    // B1: Tao cau SQL 
    String sql = "SELECT dbo.HoaDon.id, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ma_khach_hang, dbo.HoaDon.ma_hoa_don, " +
                 "dbo.KhachHang.ho_ten, dbo.KhachHang.so_dien_thoai, dbo.KhachHang.email, dbo.KhachHang.dia_chi, " +
                 "dbo.HoaDon.tong_tien, dbo.HoaDon.ngay_tao, dbo.HoaDon.trang_thai " +
                 "FROM dbo.HoaDon " +
                 "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id " +
                 "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id " +
                 "WHERE dbo.KhachHang.id = ? AND dbo.KhachHang.trang_thai = 1";

    ArrayList<LichSuGiaoDich> lists = new ArrayList<>();
    
    // B2: Mo cong ket noi 
    // try...with..resource => Tu dong dong ket noi SQL
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        // Thiết lập giá trị cho tham số id_khachhang
        ps.setObject(1, id_khachhang);
        
        // table => ResultSet
        ResultSet rs = ps.executeQuery();
        // Doi vs cac cau SQL 
        // su dung excuteQuery => tra ve 1 bang(resultset)
       
        while (rs.next()) {                
            lists.add(new LichSuGiaoDich(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                     rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), rs.getBoolean(11)));
        }
       
    } catch (Exception e) {
        // loi => nhay vao catch
        e.printStackTrace(System.out);
    }
    return lists;
}
    public ArrayList<LichSuGiaoDich> searchLICHSUGD(String maHoaDon) {
    String sql = "SELECT dbo.HoaDon.id, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ma_khach_hang, dbo.HoaDon.ma_hoa_don, dbo.KhachHang.ho_ten, dbo.KhachHang.so_dien_thoai, dbo.KhachHang.email, dbo.KhachHang.dia_chi,\n"
                + "dbo.HoaDon.tong_tien, dbo.HoaDon.ngay_tao, dbo.HoaDon.trang_thai\n"
                + "FROM dbo.HoaDon INNER JOIN\n"
                + "dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id INNER JOIN\n"
                + "dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id\n"
                + "WHERE dbo.NhanVien.ma_nhan_vien LIKE ?\n"
                + "OR dbo.KhachHang.ma_khach_hang LIKE ?\n"
                + "OR dbo.HoaDon.ma_hoa_don LIKE ?\n"
                + "OR dbo.KhachHang.so_dien_thoai LIKE ?\n"
                + "OR dbo.KhachHang.email LIKE ?";

    ArrayList<LichSuGiaoDich> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

        String searchString = "%" + maHoaDon + "%"; // Tạo chuỗi tìm kiếm với %

        // Đặt giá trị cho từng tham số trong câu truy vấn SQL
        for (int i = 1; i <= 5; i++) {
            ps.setString(i, searchString);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String ma_nhan_vien = rs.getString("ma_nhan_vien");
            String ma_khach_hang = rs.getString("ma_khach_hang");
            String ma_hoa_don = rs.getString("ma_hoa_don");
            String ho_ten = rs.getString("ho_ten");
            String so_dien_thoai = rs.getString("so_dien_thoai");
            String email = rs.getString("email");
            String dia_chi = rs.getString("dia_chi");
            Double tong_tien = rs.getDouble("tong_tien");
            String ngay_tao = rs.getString("ngay_tao");
            boolean trang_thai = rs.getBoolean("trang_thai");

            // Tạo đối tượng LichSuGiaoDich từ dữ liệu trong ResultSet
            LichSuGiaoDich lichsu = new LichSuGiaoDich(id, ma_nhan_vien, ma_khach_hang, ma_hoa_don, ho_ten, so_dien_thoai, email, dia_chi, tong_tien, ngay_tao, trang_thai);
            lists.add(lichsu);
        }
    } catch (Exception e) {
        System.out.println("Error executing SQL query: " + e.getMessage());
        e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
    }
    return lists;
}



}

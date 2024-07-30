/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import com.Product.form.NhanVienForm;
import main.entity.NhanVien;

/**
 * c
 *
 * @author hangnt
 */
public class repo {

    public ArrayList<NhanVien> getALL() {
        ArrayList<NhanVien> lists = new ArrayList<>();
        String sql = "SELECT \n"
                + "      [ma_nhan_vien]\n"
                + "      ,[ten_nhan_vien]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[so_dien_thoai]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[ngay_cap_nhat]\n"
                + "  FROM [dbo].[NhanVien]"
                + "    ";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien response = NhanVien.builder()
                        .MaNhanVien(rs.getString(1))
                        .TenNhanVien(rs.getString(2))
                        .GioiTinh(rs.getBoolean(3))
                        .SoDienThoai(rs.getString(4))
                        .DiaChi(rs.getString(5))
                        .TrangThai(rs.getBoolean(6))
                        .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    // hien thi cac tai khoan dang hoat dong
    public ArrayList<NhanVien> getALLAccountActive() {
        ArrayList<NhanVien> lists = new ArrayList<>();
        String sql = "SELECT \n"
                + "      [ma_nhan_vien]\n"
                + "      ,[ten_nhan_vien]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[so_dien_thoai]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[ngay_cap_nhat]\n"
                + "  FROM [dbo].[NhanVien] WHERE [trang_thai] = 'True'"
                + "    ";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien response = NhanVien.builder()
                        .MaNhanVien(rs.getString(1))
                        .TenNhanVien(rs.getString(2))
                        .GioiTinh(rs.getBoolean(3))
                        .SoDienThoai(rs.getString(4))
                        .DiaChi(rs.getString(5))
                        .TrangThai(rs.getBoolean(6))
                        .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    //them
    public boolean add(NhanVien nv) {
        int check = 0;
        String sql = "insert into NhanVien(id_nguoi_dung, ten_nhan_vien, gioi_tinh, so_dien_thoai, dia_chi,"
                + "can_cuoc_cong_dan, ngay_tao, ngay_cap_nhat) values\n"
                + "(?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getIdNguoiDung());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSoDienThoai());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getCanCuocCongDan());
            ps.setObject(7, nv.getNgayTao());
            ps.setObject(8, nv.getNgayCapNhat());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // xoa tam thoi doi trang_thai = false
    public boolean remove(String id) {
        int check = 0;
        String sql = "UPDATE [dbo].[NhanVien] SET [trang_thai] = 'False' WHERE [ma_nhan_vien] = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    // sua thong tin nhan vien
    public boolean edit(NhanVien nv) {
        int check = 0;
        String sql = "UPDATE [dbo].[NhanVien] "
                + "SET [ten_nhan_vien] = ? , [gioi_tinh] = ?, [so_dien_thoai] = ?, [dia_chi] = ? "
                + "WHERE [ma_nhan_vien] = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getTenNhanVien());
            ps.setObject(2, nv.isGioiTinh());
            ps.setObject(3, nv.getSoDienThoai());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getMaNhanVien());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    //Tim kiem nang cao
//    String sql = "SELECT * "
//                + "FROM NhanVien"
//                + "WHERE ten_nhan_vien LIKE ? "
//                + "OR gioi_tinh LIKE ? "
//                + "OR trang_thai LIKE ?";
//
//        ArrayList<NhanVien> lists = new ArrayList<>();
//        try (Connection con = dbconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//            String searchString = "%" + ten_nhan_vien + "%"; // Tạo chuỗi tìm kiếm với %
//
//            // Đặt giá trị cho từng tham số trong câu truy vấn SQL
//            for (int i = 1; i <= 8; i++) {
//                ps.setString(i, searchString);
//            }
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String ma_khach_hang = rs.getString("ma_khach_hang");
//                String ho_ten = rs.getString("ho_ten");
//                String ngay_sinh = rs.getString("ngay_sinh");
//                boolean gioi_tinh = rs.getBoolean("gioi_tinh");
//                String dia_chi = rs.getString("dia_chi");
//                String email = rs.getString("email");
//                String so_dien_thoai = rs.getString("so_dien_thoai");
//                String ngay_tao = rs.getString("ngay_tao");
//
//                // Tạo đối tượng KhachHang từ dữ liệu trong ResultSet
//                KhachHang khachHang = new KhachHang(id, ma_khach_hang, ho_ten, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, ngay_tao);
//                lists.add(khachHang);
//            }
//        } catch (Exception e) {
//            System.out.println("Error executing SQL query: " + e.getMessage());
//            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
//        }
//        return lists;
    
    public ArrayList<NhanVien> timkiem(String ten_nv, String gioi_tinh, String trang_thai) {

        ArrayList<NhanVien> lists = new ArrayList<>();
        String sql = "SELECT \n"
                + "      [ma_nhan_vien]\n"
                + "      ,[ten_nhan_vien]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[so_dien_thoai]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "  FROM [dbo].[NhanVien] "
                + "WHERE [ten_nhan_vien] LIKE ? AND [gioi_tinh] = ? AND [trang_thai] = ?"
                + " ";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String searchString = "%" + ten_nv + "%"; // Tạo chuỗi tìm kiếm với %

            // Đặt giá trị cho từng tham số trong câu truy vấn SQL
            ps.setString(1, searchString);
            ps.setString(2, gioi_tinh);
            ps.setString(3, trang_thai);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                NhanVien response = NhanVien.builder()
                        .MaNhanVien(rs.getString(1))
                        .TenNhanVien(rs.getString(2))
                        .GioiTinh(rs.getBoolean(3))
                        .SoDienThoai(rs.getString(4))
                        .DiaChi(rs.getString(5))
                        .TrangThai(rs.getBoolean(6))
                        .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            // In ra stack trace để xem chi tiết lỗi
        }
        return null;
    }
}
//    

// tim kiem nhan vien
//}


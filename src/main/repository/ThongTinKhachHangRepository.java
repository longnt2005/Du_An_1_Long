/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.entity.KhachHang;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;
import java.util.Date;

/**
 *
 * @author admin
 */
public class ThongTinKhachHangRepository {

    public static ArrayList<KhachHang> search;

    public ArrayList<KhachHang> getAll() {
        // B1: Tao cau SQL 
        String sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao from KhachHang\n"
                + "where trang_thai = 1";

        ArrayList<KhachHang> lists = new ArrayList<>();
        // B2: Mo cong ket noi 
        // try...with..resource => Tu dong cong ket noi sql
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)

            while (rs.next()) {
                lists.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public boolean add(KhachHang kh) {
        int check = 0;
        String sql = "insert into KhachHang(ho_ten,so_dien_thoai,gioi_tinh,ngay_sinh,dia_chi,email)\n"
                + "values(?,?,?,?,?,?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getSoDienThoai());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getNgaySinh());
            ps.setObject(5, kh.getDiaChi());
            ps.setObject(6, kh.getEmail());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KhachHang newKhachHang, Integer id) {
        int check = 0;
        String sql = "update KhachHang\n"
                + "set ho_ten=?,\n"
                + "	so_dien_thoai=?,\n"
                + "	gioi_tinh=?,\n"
                + "	ngay_sinh=?,\n"
                + "	dia_chi=?,\n"
                + "	email=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newKhachHang.getHoTen());
            ps.setObject(2, newKhachHang.getSoDienThoai());
            ps.setObject(3, newKhachHang.isGioiTinh());
            ps.setObject(4, newKhachHang.getNgaySinh());
            ps.setObject(5, newKhachHang.getDiaChi());
            ps.setObject(6, newKhachHang.getEmail());
            ps.setObject(7, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
//

    public boolean delete(Integer id) {
        int check = 0;
        String sql = "update KhachHang\n"
                + "set trang_thai = 0\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<KhachHang> searchKhachHang(String maKhachHang) {
        String sql = "SELECT id, ma_khach_hang, ho_ten, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, ngay_tao "
                + "FROM KhachHang "
                + "WHERE ma_khach_hang LIKE ? "
                + "OR ho_ten LIKE ? "
                + "OR ngay_sinh LIKE ? "
                + "OR gioi_tinh LIKE ? "
                + "OR dia_chi LIKE ? "
                + "OR email LIKE ? "
                + "OR so_dien_thoai LIKE ? "
                + "OR ngay_tao LIKE ?";

        ArrayList<KhachHang> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maKhachHang + "%"; // Tạo chuỗi tìm kiếm với %

            // Đặt giá trị cho từng tham số trong câu truy vấn SQL
            for (int i = 1; i <= 8; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_khach_hang = rs.getString("ma_khach_hang");
                String ho_ten = rs.getString("ho_ten");
                String ngay_sinh = rs.getString("ngay_sinh");
                boolean gioi_tinh = rs.getBoolean("gioi_tinh");
                String dia_chi = rs.getString("dia_chi");
                String email = rs.getString("email");
                String so_dien_thoai = rs.getString("so_dien_thoai");
                String ngay_tao = rs.getString("ngay_tao");

                // Tạo đối tượng KhachHang từ dữ liệu trong ResultSet
                KhachHang khachHang = new KhachHang(id, ma_khach_hang, ho_ten, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, ngay_tao);
                lists.add(khachHang);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public ArrayList<KhachHang> searchGioiTinhNam() {
        String sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao from KhachHang\n"
                + "where gioi_tinh=1\n"
                + "and trang_thai =1";
        ArrayList<KhachHang> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new KhachHang(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<KhachHang> searchGioiTinhNu() {
        String sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao from KhachHang\n"
                + "where gioi_tinh=0\n"
                + "and trang_thai =1";
        ArrayList<KhachHang> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new KhachHang(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

//    public ArrayList<KhachHang> search(String keyword, String maKhachHang, String tenKhachHang,
//             String sdt, boolean gioiTinh, Date ngaySinh, String diaChi, String email, Date ngayTao) {
//        String sql = "SELECT kh.Id, kh.ma_khach_hang, kh.ho_ten,kh.so_dien_thoai,kh.ngay_sinh,kh.dia_chi, kh.Email,kh.ngay_tao\n"
//                + "        FROM KhachHang kh\n"
//                + "        WHERE kh.ma_khach_hang = ?\n"
//                + "        AND kh.ho_ten = ?\n"
//                + "        AND kh.so_dien_thoai = ?\n"
//                + "        AND kh.ngay_sinh = ?\n"
//                + "        AND kh.dia_chi = ?\n"
//                + "        AND kh.email = ?\n"
//                + "        AND kh.ngay_tao = ?\n"
//                + "        AND kh.Email = ?";
//        if (keyword.length() > 0) { // isempty
//            sql += "AND (\n"
//                    + "                kh.ma_khach_hang LIKE ?\n"
//                    + "                OR kh.ho_ten LIKE ?               \n"
//                    + "				OR kh.so_dien_thoai LIKE  ?\n"
//                    + "				OR kh.ngay_sinh LIKE ?\n"
//                    + "				OR kh.dia_chi LIKE ?\n"
//                    + "				OR kh.email LIKE ?\n"
//                    + "				OR kh.ngay_tao LIKE ?\n"
//                    + "				OR kh.Email LIKE ?\n"
//                    + "            )";
//        }
//        ArrayList<KhachHang> lists = new ArrayList<>();
//        try (Connection con = DBConnect.getConnection();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//            int index = 1; // Vi tri cua dau hoi cham dau tien 
//            ps.setObject(1, maKhachHang);
//            ps.setObject(2, tenKhachHang);
//            ps.setObject(3, sdt);
//            ps.setObject(4, gioiTinh);
//            ps.setObject(5, ngaySinh);
//            ps.setObject(6, diaChi);
//            ps.setObject(7, email);
//            ps.setObject(8, ngayTao);
//            if (keyword.length() > 0) {
//                String value = "%" + keyword + "%";
//                // search 1 o input nhieu truong
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//            }
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                lists.add(new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5), rs.getString(6),
//                         rs.getString(7), rs.getString(8), rs.getDate(9)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out); // nem loi khi xay ra 
//        }
//        return lists;
//    }
    
    
    
}

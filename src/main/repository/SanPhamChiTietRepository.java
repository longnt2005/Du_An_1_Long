/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.config.DBConnect;
import main.entity.SanPhamChiTiet;
import com.Product.form.NhanVienForm;
import main.response.SanPhamChiTietRespone;
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
public class SanPhamChiTietRepository {

    public ArrayList<SanPhamChiTietRespone> getAll() {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public boolean add(SanPhamChiTiet spct) {
        int check = 0;

        String sql = "INSERT INTO SanPhamChiTiet "
                + "(id_san_pham, id_thuong_hieu, id_xuat_xu, id_mau_sac, id_kich_thuoc, "
                + "id_chat_lieu, id_co_ao, id_do_day, id_tinh_linh_hoat, gia_ban, so_luong_ton, trang_thai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Kiểm tra giá trị không null
            if (spct.getSanPhamID() == null || spct.getThuongHieuID() == null
                    || spct.getXuatXuID() == null || spct.getMauSacID() == null
                    || spct.getKichThuocID() == null || spct.getChatLieuID() == null
                    || spct.getCoAoID() == null || spct.getDoDayID() == null
                    || spct.getTinhLinhHoatID() == null || spct.getGiaBan() == null
                    || spct.getSoLuongTon() == null) {
                throw new IllegalArgumentException("Các giá trị bắt buộc không thể null.");
            }

            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getThuongHieuID());
            ps.setObject(3, spct.getXuatXuID());
            ps.setObject(4, spct.getMauSacID());
            ps.setObject(5, spct.getKichThuocID());
            ps.setObject(6, spct.getChatLieuID());
            ps.setObject(7, spct.getCoAoID());
            ps.setObject(8, spct.getDoDayID());
            ps.setObject(9, spct.getTinhLinhHoatID());
            ps.setObject(10, spct.getGiaBan());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, spct.isTrangThai());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi dữ liệu: " + e.getMessage());
        }

        return check > 0;
    }

    public ArrayList<SanPhamChiTietRespone> searchh(String maSP) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao, dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, dbo.SanPhamChiTiet.trang_thai\n"
                + "FROM dbo.SanPhamChiTiet\n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id\n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id\n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id\n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id\n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id\n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id\n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id\n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id\n"
                + "WHERE dbo.SanPhamChiTiet.ma_san_pham_chi_tiet LIKE ?\n"
                + "  OR dbo.SanPham.ten_san_pham LIKE ?\n"
                + "  OR dbo.ThuongHieu.ten_thuong_hieu LIKE ?\n"
                + "  OR dbo.XuatXu.ten_nuoc LIKE ?\n"
                + "  OR dbo.MauSac.ten_mau LIKE ?\n"
                + "  OR dbo.KichThuoc.size LIKE ?\n"
                + "  OR dbo.ChatLieu.ten_chat_lieu LIKE ?\n"
                + "  OR dbo.CoAo.ten_co_ao LIKE ?\n"
                + "  OR dbo.DoDay.ten_do_day LIKE ?\n"
                + "  OR dbo.TinhLinhHoat.ten_tinh_linh_hoat LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.gia_ban LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.so_luong_ton LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.trang_thai LIKE ?";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maSP + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm
            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            for (int i = 1; i <= 13; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham_chi_tiet = rs.getString("ma_san_pham_chi_tiet");
                String ten_san_pham = rs.getString("ten_san_pham");
                String thuong_hieu = rs.getString("ten_thuong_hieu");
                String ten_nuoc = rs.getString("ten_nuoc");
                String ten_mau = rs.getString("ten_mau");
                String size = rs.getString("size");
                String ten_chat_lieu = rs.getString("ten_chat_lieu");
                String ten_co_ao = rs.getString("ten_co_ao");
                String ten_do_day = rs.getString("ten_do_day");
                String ten_tinh_linh_hoat = rs.getString("ten_tinh_linh_hoat");
                Double giaBan = rs.getDouble("gia_ban");
                Integer so_luong_ton = rs.getInt("so_luong_ton");
                Boolean trang_thai = rs.getBoolean("trang_thai");

                SanPhamChiTietRespone sanPham = new SanPhamChiTietRespone(id, ma_san_pham_chi_tiet, ten_san_pham, thuong_hieu, ten_nuoc, ten_mau, size, ten_chat_lieu, ten_co_ao, ten_do_day, ten_tinh_linh_hoat, giaBan, so_luong_ton, true);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> getAllGiamDan() {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "ORDER BY dbo.SanPhamChiTiet.ngay_tao DESC";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoDieuKien(SanPhamChiTiet spct) {

        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.SanPhamChiTiet.id_san_pham = ? \n"
                + "AND dbo.SanPhamChiTiet.id_xuat_xu = ? \n"
                + "AND dbo.SanPhamChiTiet.id_thuong_hieu = ? \n"
                + "AND dbo.SanPhamChiTiet.id_tinh_linh_hoat = ? \n";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getXuatXuID());
            ps.setObject(3, spct.getThuongHieuID());
            ps.setObject(4, spct.getTinhLinhHoatID());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getString("ten_nuoc"),
                        rs.getString("ten_mau"),
                        rs.getString("size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getString("ten_co_ao"),
                        rs.getString("ten_do_day"),
                        rs.getString("ten_tinh_linh_hoat"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoDieuKienGiaTangDan(String tenSanPham, String tinhLinhHoat, String xuatXu, String thuongHieu) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.SanPham.ten_san_pham LIKE ? \n"
                + "AND dbo.TinhLinhHoat.ten_tinh_linh_hoat LIKE ? \n"
                + "AND dbo.XuatXu.ten_nuoc LIKE ? \n"
                + "AND dbo.ThuongHieu.ten_thuong_hieu LIKE ? \n"
                + "ORDER BY dbo.SanPhamChiTiet.gia_ban ASC";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + (tenSanPham != null ? tenSanPham : "") + "%");
            ps.setString(2, "%" + (tinhLinhHoat != null ? tinhLinhHoat : "") + "%");
            ps.setString(3, "%" + (xuatXu != null ? xuatXu : "") + "%");
            ps.setString(4, "%" + (thuongHieu != null ? thuongHieu : "") + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

//        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
//        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, "%" + tenTinhLinhHoat + "%"); // Thay đổi tham số đầu vào cho LIKE
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
//                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
//                        rs.getInt(13), rs.getBoolean(14)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out); // nem loi khi xay ra 
//        }
//        return lists;
//    }
//
//    public ArrayList<SanPhamChiTietRespone> giaTangDan() {
//    String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
//            + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
//            + "       dbo.SanPham.ten_san_pham, \n"
//            + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
//            + "       dbo.XuatXu.ten_nuoc, \n"
//            + "       dbo.MauSac.ten_mau, \n"
//            + "       dbo.KichThuoc.size, \n"
//            + "       dbo.ChatLieu.ten_chat_lieu, \n"
//            + "       dbo.CoAo.ten_co_ao, \n"
//            + "       dbo.DoDay.ten_do_day, \n"
//            + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
//            + "       dbo.SanPhamChiTiet.gia_ban, \n"
//            + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
//            + "       dbo.SanPhamChiTiet.trang_thai \n"
//            + "FROM dbo.SanPhamChiTiet \n"
//            + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
//            + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
//            + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
//            + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
//            + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
//            + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
//            + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
//            + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
//            + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
//            + "ORDER BY dbo.SanPhamChiTiet.gia_ban ASC"; // Sắp xếp theo giá bán từ thấp đến cao
//
//    ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
//    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
//                    rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
//                    rs.getInt(13), rs.getBoolean(14)));
//        }
//    } catch (Exception e) {
//        e.printStackTrace(System.out); // nem loi khi xay ra 
//    }
//    return lists;
//}
//    
//    public ArrayList<SanPhamChiTietRespone> giaGiamDan() {
//    String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
//            + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
//            + "       dbo.SanPham.ten_san_pham, \n"
//            + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
//            + "       dbo.XuatXu.ten_nuoc, \n"
//            + "       dbo.MauSac.ten_mau, \n"
//            + "       dbo.KichThuoc.size, \n"
//            + "       dbo.ChatLieu.ten_chat_lieu, \n"
//            + "       dbo.CoAo.ten_co_ao, \n"
//            + "       dbo.DoDay.ten_do_day, \n"
//            + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
//            + "       dbo.SanPhamChiTiet.gia_ban, \n"
//            + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
//            + "       dbo.SanPhamChiTiet.trang_thai \n"
//            + "FROM dbo.SanPhamChiTiet \n"
//            + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
//            + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
//            + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
//            + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
//            + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
//            + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
//            + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
//            + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
//            + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
//            + "ORDER BY dbo.SanPhamChiTiet.gia_ban DESC"; // Sắp xếp theo giá bán giảm dần
//
//    ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
//    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
//                    rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
//                    rs.getInt(13), rs.getBoolean(14)));
//        }
//    } catch (Exception e) {
//        e.printStackTrace(System.out); // nem loi khi xay ra 
//    }
//    return lists;
//}
    public ArrayList<SanPhamChiTietRespone> getSanPhamChiTietByIdSanPham(int idSanPham) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.SanPham.id = ?"; // Lọc theo id_san_pham

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getString("ten_nuoc"),
                        rs.getString("ten_mau"),
                        rs.getString("size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getString("ten_co_ao"),
                        rs.getString("ten_do_day"),
                        rs.getString("ten_tinh_linh_hoat"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return lists;
    }

   public SanPhamChiTietRespone getSanPhamChiTietByMaSPCT(String maSPCT) {
    String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
            + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
            + "       dbo.SanPham.ten_san_pham, \n"
            + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
            + "       dbo.XuatXu.ten_nuoc, \n"
            + "       dbo.MauSac.ten_mau, \n"
            + "       dbo.KichThuoc.size, \n"
            + "       dbo.ChatLieu.ten_chat_lieu, \n"
            + "       dbo.CoAo.ten_co_ao, \n"
            + "       dbo.DoDay.ten_do_day, \n"
            + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
            + "       dbo.SanPhamChiTiet.gia_ban, \n"
            + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
            + "       dbo.SanPhamChiTiet.trang_thai \n"
            + "FROM dbo.SanPhamChiTiet \n"
            + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
            + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
            + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
            + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
            + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
            + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
            + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
            + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
            + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
            + "WHERE dbo.SanPhamChiTiet.ma_san_pham_chi_tiet = ?";

    SanPhamChiTietRespone spct = null;
    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maSPCT);  // Sử dụng setString thay vì setObject vì maSPCT là String
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            spct = new SanPhamChiTietRespone(
                    rs.getInt("id"),
                    rs.getString("ma_san_pham_chi_tiet"),
                    rs.getString("ten_san_pham"),
                    rs.getString("ten_thuong_hieu"),
                    rs.getString("ten_nuoc"),
                    rs.getString("ten_mau"),
                    rs.getString("size"),
                    rs.getString("ten_chat_lieu"),
                    rs.getString("ten_co_ao"),
                    rs.getString("ten_do_day"),
                    rs.getString("ten_tinh_linh_hoat"),
                    rs.getDouble("gia_ban"),
                    rs.getInt("so_luong_ton"),
                    rs.getBoolean("trang_thai")
            );
        }
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
        e.printStackTrace();
    }
    return spct;
}


//    
//    public ArrayList<HoaDonResponse> search(String keyword, Integer trangThai, Integer httt, Double giaMin, Double giaMax, String ngayBatDau, String ngayKetThuc) {
//    String sql = "SELECT " +
//            "dbo.HoaDon.id, " +
//            "dbo.HoaDon.ma_hoa_don, " +
//            "dbo.HoaDon.ngay_tao, " +
//            "dbo.HoaDon.ngay_cap_nhat, " +
//            "dbo.HoaDon.tong_tien, " +
//            "dbo.NhanVien.ma_nhan_vien, " +
//            "dbo.KhachHang.ho_ten, " +
//            "dbo.KhachHang.dia_chi, " +
//            "dbo.KhachHang.so_dien_thoai, " +
//            "dbo.HoaDon.trang_thai, " +
//            "dbo.HoaDon.hinh_thuc_thanh_toan " +
//            "FROM dbo.HoaDon " +
//            "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id " +
//            "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id " +
//            "WHERE 1=1"; // Start with a dummy condition that will always be true
//
//    // Append conditions for mandatory parameters
//    sql += " AND dbo.HoaDon.tong_tien BETWEEN ? AND ?";
//    
//    // Append conditions for optional parameters
//    if (trangThai != null) {
//        sql += " AND dbo.HoaDon.trang_thai = ?";
//    }
//    if (httt != null) {
//        sql += " AND dbo.HoaDon.hinh_thuc_thanh_toan = ?";
//    }
//    if (ngayBatDau != null) {
//        sql += " AND dbo.HoaDon.ngay_tao >= ?";
//    }
//    if (ngayKetThuc != null) {
//        sql += " AND dbo.HoaDon.ngay_tao <= ?";
//    }
//    if (keyword != null && !keyword.isEmpty()) {
//        sql += " AND (dbo.HoaDon.ma_hoa_don LIKE ? OR dbo.NhanVien.ma_nhan_vien LIKE ? OR dbo.KhachHang.ho_ten LIKE ? OR dbo.KhachHang.dia_chi LIKE ? OR dbo.KhachHang.so_dien_thoai LIKE ?)";
//    }
//
//    ArrayList<HoaDonResponse> lists = new ArrayList<>();
//    try (Connection con = DBConnect.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//        int index = 1; // Start index for setting parameters
//
//        // Set mandatory parameters
//        ps.setDouble(index++, giaMin != null ? giaMin : 0.0); // Set a default value or handle differently if needed
//        ps.setDouble(index++, giaMax != null ? giaMax : Double.MAX_VALUE); // Set a default value or handle differently if needed
//
//        // Set optional parameters based on conditions
//        if (trangThai != null) {
//            ps.setInt(index++, trangThai);
//        }
//        if (httt != null) {
//            ps.setInt(index++, httt);
//        }
//        if (ngayBatDau != null) {
//            ps.setDate(index++, java.sql.Date.valueOf(ngayBatDau));
//        }
//        if (ngayKetThuc != null) {
//            ps.setDate(index++, java.sql.Date.valueOf(ngayKetThuc));
//        }
//        if (keyword != null && !keyword.isEmpty()) {
//            String value = "%" + keyword + "%";
//            ps.setString(index++, value); // LIKE for ma_hoa_don
//            ps.setString(index++, value); // LIKE for ma_nhan_vien
//            ps.setString(index++, value); // LIKE for ho_ten
//            ps.setString(index++, value); // LIKE for dia_chi
//ps.setString(index++, value); // LIKE for so_dien_thoai
//        }
//
//        // Execute query and process results
//        try (ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                HoaDonResponse response = new HoaDonResponse();
//                response.setId(rs.getInt("id"));
//                response.setMaHoaDon(rs.getString("ma_hoa_don"));
//                response.setNgayTao(rs.getString("ngay_tao"));
//                response.setNgayCapNhap(rs.getString("ngay_cap_nhat"));
//                response.setTongTien(rs.getDouble("tong_tien"));
//                response.setMaNhanVien(rs.getString("ma_nhan_vien"));
//                response.setHoTen(rs.getString("ho_ten"));
//                response.setDiaChi(rs.getString("dia_chi"));
//                response.setSDT(rs.getString("so_dien_thoai"));
//                response.setTrangThai(rs.getInt("trang_thai"));
//                response.setHinhThucTT(rs.getInt("hinh_thuc_thanh_toan"));
//                lists.add(response);
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace(); // Handle SQL exception
//    }
//    return lists;
//}
//try {
//            String keyword = txtSearch.getText().trim();
//            Integer trangThai = cbox_hoaDon.getSelectedIndex();
//            Integer httt = cb_httt.getSelectedIndex();
//
//            // Get giaMin
//            Double giaMin = null;
//            String giaMinText = txt_timTheoGia.getText().trim();
//            if (!giaMinText.isEmpty()) {
//                giaMin = Double.parseDouble(giaMinText);
//            }
//
//            // Get giaMax
//            Double giaMax = null;
//            String giaMaxText = txt_timTheoGiaMax.getText().trim();
//            if (!giaMaxText.isEmpty()) {
//                giaMax = Double.parseDouble(giaMaxText);
//            }
//
//            // Get startDate
//            LocalDate startDate = null;
//            String ngayBatDau = txt_tuNgay.getText().trim();
//            if (!ngayBatDau.isEmpty()) {
//                try {
//                    startDate = LocalDate.parse(ngayBatDau, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                } catch (DateTimeParseException e) {
//                    throw new DateTimeParseException("Invalid date format", ngayBatDau, 0, e);
//                }
//            }
//
//            // Get endDate
//            LocalDate endDate = null;
//            String ngayKetThuc = txt_denNgay.getText().trim();
//            if (!ngayKetThuc.isEmpty()) {
//                try {
//                    endDate = LocalDate.parse(ngayKetThuc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                } catch (DateTimeParseException e) {
//                    throw new DateTimeParseException("Invalid date format", ngayKetThuc, 0, e);
//                }
//            }
//
//            // Call the search method with inputs
//            showDataTable(hdRepo.search(keyword, trangThai, httt, giaMin, giaMax, startDate != null ? startDate.toString() : null, endDate != null ? endDate.toString() : null));
//            
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho phạm vi giá.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        } catch (DateTimeParseException e) {
//            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập ngày ở định dạng yyyy-MM-dd.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
}

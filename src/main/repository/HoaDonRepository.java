/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import main.config.DBConnect;
import main.entity.HoaDon;
import main.response.HoaDonResponse;
import util.Helper;
import java.util.Date;
//

/**
 *
 * @author ADMIN
 */

public class HoaDonRepository {

    public ArrayList<HoaDonResponse> getAll() {
        String sql = "SELECT  dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \n"
                + "                 dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM      dbo.HoaDon INNER JOIN\n"
                + "                 dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n"
                + "                 dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();

            ArrayList<HoaDonResponse> lists = new ArrayList<>();
            while (rs.next()) {
                HoaDonResponse response
                        = HoaDonResponse.builder()
                                .id(rs.getInt(1))
                                .maHoaDon(rs.getString(2))
                                .ngayTao(rs.getString(3))
                                .ngayCapNhap(rs.getString(4))
                                .tongTien(rs.getDouble(5))
                                .maNhanVien(rs.getString(6))
                                .hoTen(rs.getString(7))
                                .diaChi(rs.getString(8))
                                .SDT(rs.getString(9))
                                .trangThai(rs.getInt(10))
                                .hinhThucTT(rs.getInt(11))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<HoaDonResponse> byIDHoaDOn() {
        String sql = "public ArrayList<HoaDonResponse> getAll() {\n"
                + "        String sql = \"SELECT  dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \\n\"\n"
                + "                + \"                 dbo.HoaDon.hinh_thuc_thanh_toan\\n\"\n"
                + "                + \"FROM      dbo.HoaDon INNER JOIN\\n\"\n"
                + "                + \"                 dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\\n\"\n"
                + "                + \"                 dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\";\n"
                + "\n"
                + "        try (Connection con = DBConnect.getConnection();\n"
                + "                PreparedStatement ps = con.prepareStatement(sql)) {\n"
                + "//            ps.setObject(1, hoaDonID);\n"
                + "            ResultSet rs = ps.executeQuery();\n"
                + "\n"
                + "            ArrayList<HoaDonResponse> lists = new ArrayList<>();\n"
                + "            while (rs.next()) {\n"
                + "                HoaDonResponse response\n"
                + "                        = HoaDonResponse.builder()\n"
                + "                                .id(rs.getInt(1))\n"
                + "                                .maHoaDon(rs.getString(2))\n"
                + "                                .ngayTao(rs.getString(3))\n"
                + "                                .ngayCapNhap(rs.getString(4))\n"
                + "                                .tongTien(rs.getDouble(5))\n"
                + "                                .maNhanVien(rs.getString(6))\n"
                + "                                .hoTen(rs.getString(7))\n"
                + "                                .diaChi(rs.getString(8))\n"
                + "                                .SDT(rs.getString(9))\n"
                + "                                .trangThai(rs.getInt(10))\n"
                + "                                .hinhThucTT(rs.getInt(11))\n"
                + "                                .build();\n"
                + "                lists.add(response);\n"
                + "            }\n"
                + "            return lists;\n"
                + "        } catch (Exception e) {\n"
                + "            // loi => nhay vao catch\n"
                + "            e.printStackTrace(System.out);\n"
                + "        }\n"
                + "        return null;\n"
                + "    }";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();

            ArrayList<HoaDonResponse> lists = new ArrayList<>();
            while (rs.next()) {
                HoaDonResponse response
                        = HoaDonResponse.builder()
                                .id(rs.getInt(1))
                                .maHoaDon(rs.getString(2))
                                .ngayTao(rs.getString(3))
                                .ngayCapNhap(rs.getString(4))
                                .tongTien(rs.getDouble(5))
                                .maNhanVien(rs.getString(6))
                                .hoTen(rs.getString(7))
                                .diaChi(rs.getString(8))
                                .SDT(rs.getString(9))
                                .trangThai(rs.getInt(10))
                                .hinhThucTT(rs.getInt(11))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<HoaDonResponse> trangThaiHoaDon(Integer trangThai) {
    String sql = "SELECT \n" +
                 "    dbo.HoaDon.id, \n" +
                 "    dbo.HoaDon.ma_hoa_don, \n" +
                 "    dbo.HoaDon.ngay_tao, \n" +
                 "    dbo.HoaDon.ngay_cap_nhat, \n" +
                 "    dbo.HoaDon.tong_tien, \n" +
                 "    dbo.NhanVien.ma_nhan_vien, \n" +
                 "    dbo.KhachHang.ho_ten, \n" +
                 "    dbo.KhachHang.dia_chi, \n" +
                 "    dbo.KhachHang.so_dien_thoai, \n" +
                 "    dbo.HoaDon.trang_thai, \n" +
                 "    dbo.HoaDon.hinh_thuc_thanh_toan\n" +
                 "FROM \n" +
                 "    dbo.HoaDon\n" +
                 "INNER JOIN \n" +
                 "    dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id\n" +
                 "INNER JOIN \n" +
                 "    dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\n" +
                 "WHERE 1=1"; // Start with a dummy condition that will always be true

    // Append condition for trangThai if it is not "All"
    if (trangThai != null) {
        sql += " AND dbo.HoaDon.trang_thai = ?";
    }

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        if (trangThai != null) {
            ps.setInt(1, trangThai);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonResponse response = HoaDonResponse.builder()
                        .id(rs.getInt(1))
                        .maHoaDon(rs.getString(2))
                        .ngayTao(rs.getString(3))
                        .ngayCapNhap(rs.getString(4))
                        .tongTien(rs.getDouble(5))
                        .maNhanVien(rs.getString(6))
                        .hoTen(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .hinhThucTT(rs.getInt(11))
                        .build();
                lists.add(response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    }
    return lists;
}

   public ArrayList<HoaDonResponse> hinhThucThanhToan(Integer httt) {
    // Base SQL query with condition to filter by payment method
    String sql = "SELECT "
            + "dbo.HoaDon.id, "
            + "dbo.HoaDon.ma_hoa_don, "
            + "dbo.HoaDon.ngay_tao, "
            + "dbo.HoaDon.ngay_cap_nhat, "
            + "dbo.HoaDon.tong_tien, "
            + "dbo.NhanVien.ma_nhan_vien, "
            + "dbo.KhachHang.ho_ten, "
            + "dbo.KhachHang.dia_chi, "
            + "dbo.KhachHang.so_dien_thoai, "
            + "dbo.HoaDon.trang_thai, "
            + "dbo.HoaDon.hinh_thuc_thanh_toan "
            + "FROM dbo.HoaDon "
            + "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id "
            + "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id"; // Added condition

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        // Set the parameter for the SQL query
        ps.setInt(1, httt);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonResponse response = HoaDonResponse.builder()
                        .id(rs.getInt("id"))
                        .maHoaDon(rs.getString("ma_hoa_don"))
                        .ngayTao(rs.getString("ngay_tao"))
                        .ngayCapNhap(rs.getString("ngay_cap_nhat"))
                        .tongTien(rs.getDouble("tong_tien"))
                        .maNhanVien(rs.getString("ma_nhan_vien"))
                        .hoTen(rs.getString("ho_ten"))
                        .diaChi(rs.getString("dia_chi"))
                        .SDT(rs.getString("so_dien_thoai"))
                        .trangThai(rs.getInt("trang_thai"))
                        .hinhThucTT(rs.getInt("hinh_thuc_thanh_toan"))
                        .build();
                lists.add(response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    }
    return lists;
}
   
    public ArrayList<HoaDonResponse> search(Integer trangThai, Integer httt, Double giaMin, Double giaMax) {
    String sql = "SELECT "
            + "dbo.HoaDon.id, "
            + "dbo.HoaDon.ma_hoa_don, "
            + "dbo.HoaDon.ngay_tao, "
            + "dbo.HoaDon.ngay_cap_nhat, "
            + "dbo.HoaDon.tong_tien, "
            + "dbo.NhanVien.ma_nhan_vien, "
            + "dbo.KhachHang.ho_ten, "
            + "dbo.KhachHang.dia_chi, "
            + "dbo.KhachHang.so_dien_thoai, "
            + "dbo.HoaDon.trang_thai, "
            + "dbo.HoaDon.hinh_thuc_thanh_toan "
            + "FROM dbo.HoaDon "
            + "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id "
            + "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id "
            + "WHERE 1=1"; // Start with a dummy condition that will always be true

    // Append conditions for mandatory parameters
    sql += " AND dbo.HoaDon.tong_tien BETWEEN ? AND ?";

    // Append conditions for optional parameters
    if (trangThai != null) {
        sql += " AND CAST(dbo.HoaDon.trang_thai AS VARCHAR) LIKE ?";
    }
    if (httt != null) {
        sql += " AND CAST(dbo.HoaDon.hinh_thuc_thanh_toan AS VARCHAR) LIKE ?";
    }

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
        int index = 1; // Start index for setting parameters

        // Set mandatory parameters
        ps.setDouble(index++, giaMin != null ? giaMin : 0.0); // Set a default value or handle differently if needed
        ps.setDouble(index++, giaMax != null ? giaMax : Double.MAX_VALUE); // Set a default value or handle differently if needed

        // Set optional parameters based on conditions
        if (trangThai != null) {
            ps.setString(index++, "%" + trangThai.toString() + "%");
        }
        if (httt != null) {
            ps.setString(index++, "%" + httt.toString() + "%");
        }

        // Execute query and process results
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonResponse response = new HoaDonResponse();
                response.setId(rs.getInt("id"));
                response.setMaHoaDon(rs.getString("ma_hoa_don"));
                response.setNgayTao(rs.getString("ngay_tao"));
                response.setNgayCapNhap(rs.getString("ngay_cap_nhat"));
                response.setTongTien(rs.getDouble("tong_tien"));
                response.setMaNhanVien(rs.getString("ma_nhan_vien"));
                response.setHoTen(rs.getString("ho_ten"));
                response.setDiaChi(rs.getString("dia_chi"));
                response.setSDT(rs.getString("so_dien_thoai"));
                response.setTrangThai(rs.getInt("trang_thai"));
                response.setHinhThucTT(rs.getInt("hinh_thuc_thanh_toan"));
                lists.add(response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle SQL exception
    }
    return lists;
}



    public ArrayList<HoaDonResponse> searchh(String maHoaDon) {
        String sql = "SELECT dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \n"
                + "dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM dbo.HoaDon INNER JOIN\n"
                + "dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n"
                + "dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id \n"
                + "WHERE dbo.HoaDon.ma_hoa_don LIKE ? \n"
                + "OR dbo.HoaDon.ngay_tao LIKE ? \n"
                + "OR dbo.HoaDon.ngay_cap_nhat LIKE ? \n"
                + "OR dbo.HoaDon.tong_tien LIKE ? \n"
                + "OR dbo.NhanVien.ma_nhan_vien LIKE ? \n"
                + "OR dbo.KhachHang.ho_ten LIKE ? \n"
                + "OR dbo.KhachHang.dia_chi LIKE ? \n"
                + "OR dbo.KhachHang.so_dien_thoai LIKE ? \n"
                + "OR dbo.HoaDon.trang_thai LIKE ? \n"
                + "OR dbo.HoaDon.hinh_thuc_thanh_toan LIKE ?";

        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            String searchString = "%" + maHoaDon + "%";
            for (int i = 1; i <= 10; i++) {
                ps.setString(i, searchString);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonResponse(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // Xử lý lỗi khi xảy ra
        }
        return lists;
    }

    public HoaDonResponse timKiemHoaDonResponsebyQR(String maHoaDon) {
    String sql = "SELECT dbo.HoaDon.id, " +
                 "dbo.HoaDon.ma_hoa_don, " +
                 "dbo.HoaDon.ngay_tao, " +
                 "dbo.HoaDon.ngay_cap_nhat, " +
                 "dbo.HoaDon.tong_tien, " +
                 "dbo.NhanVien.ma_nhan_vien, " +
                 "dbo.KhachHang.ho_ten, " +
                 "dbo.KhachHang.dia_chi, " +
                 "dbo.KhachHang.so_dien_thoai, " +
                 "dbo.HoaDon.trang_thai, " +
                 "dbo.HoaDon.hinh_thuc_thanh_toan " +
                 "FROM dbo.HoaDon " +
                 "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id " +
                 "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id " +
                 "WHERE dbo.HoaDon.ma_hoa_don = ?";
    
    HoaDonResponse hoaDonResponse = null;
    
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Set search parameter
        ps.setString(1, maHoaDon);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                hoaDonResponse = new HoaDonResponse(
                        rs.getInt("id"),
                        rs.getString("ma_hoa_don"),
                        rs.getString("ngay_tao"),
                        rs.getString("ngay_cap_nhat"),
                        rs.getDouble("tong_tien"),
                        rs.getString("ma_nhan_vien"),
                        rs.getString("ho_ten"),
                        rs.getString("dia_chi"),
                        rs.getString("so_dien_thoai"),
                        rs.getInt("trang_thai"),
                        rs.getInt("hinh_thuc_thanh_toan")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out); // Handle SQL exceptions
    }
    
    return hoaDonResponse;
}

    public ArrayList<HoaDonResponse> timKiemTheoGia(Double giaMin, Double giaMax) {
    String sql = "SELECT "
            + "dbo.HoaDon.id, "
            + "dbo.HoaDon.ma_hoa_don, "
            + "dbo.HoaDon.ngay_tao, "
            + "dbo.HoaDon.ngay_cap_nhat, "
            + "dbo.HoaDon.tong_tien, "
            + "dbo.NhanVien.ma_nhan_vien, "
            + "dbo.KhachHang.ho_ten, "
            + "dbo.KhachHang.dia_chi, "
            + "dbo.KhachHang.so_dien_thoai, "
            + "dbo.HoaDon.trang_thai, "
            + "dbo.HoaDon.hinh_thuc_thanh_toan "
            + "FROM dbo.HoaDon "
            + "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id "
            + "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id "
            + "WHERE dbo.HoaDon.tong_tien BETWEEN ? AND ?"; // Filter by price range

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
        int index = 1; // Start index for setting parameters

        // Set mandatory parameters
        ps.setDouble(index++, giaMin != null ? giaMin : 0.0); // Set a default value or handle differently if needed
        ps.setDouble(index++, giaMax != null ? giaMax : Double.MAX_VALUE); // Set a default value or handle differently if needed

        // Execute query and process results
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonResponse response = new HoaDonResponse();
                response.setId(rs.getInt("id"));
                response.setMaHoaDon(rs.getString("ma_hoa_don"));
                response.setNgayTao(rs.getString("ngay_tao"));
                response.setNgayCapNhap(rs.getString("ngay_cap_nhat"));
                response.setTongTien(rs.getDouble("tong_tien"));
                response.setMaNhanVien(rs.getString("ma_nhan_vien"));
                response.setHoTen(rs.getString("ho_ten"));
                response.setDiaChi(rs.getString("dia_chi"));
                response.setSDT(rs.getString("so_dien_thoai"));
                response.setTrangThai(rs.getInt("trang_thai"));
                response.setHinhThucTT(rs.getInt("hinh_thuc_thanh_toan"));
                lists.add(response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle SQL exception
    }
    return lists;
}


//    public HoaDonResponse getAll(String invoiceId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

}

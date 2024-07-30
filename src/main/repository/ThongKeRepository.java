/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.entity.ThongKe;

/**
 *
 * @author admin
 */
public class ThongKeRepository {
    public ArrayList<ThongKe> getAll() {
        // B1: Tao cau SQL 
        String sql = "SELECT \n"
                + "    (SELECT SUM(tong_tien) FROM HoaDon) AS tong_tien,\n"
                + "    (SELECT COUNT(*) FROM HoaDon) AS so_luong_hoa_don,\n"
                + "    (SELECT COUNT(*) FROM HoaDon WHERE trang_thai = 0) AS so_luong_hoa_don_bi_huy,\n"
                + "    (SELECT COUNT(*) FROM KhachHang) AS so_luong_khach_hang;";

        ArrayList<ThongKe> lists = new ArrayList<>();
        // B2: Mo cong ket noi 
        // try...with..resource => Tu dong cong ket noi sql
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)

            while (rs.next()) {
                lists.add(new ThongKe(rs.getDouble(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }

        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return lists;
    }
}

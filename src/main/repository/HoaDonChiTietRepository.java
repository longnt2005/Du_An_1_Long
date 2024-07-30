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
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietRepository {

    public ArrayList<HoaDonChiTietReponse> getAll() {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    th.ten_thuong_hieu, \n" +
"    xx.ten_nuoc, \n" +
"    ms.ten_mau, \n" +
"    kt.size, \n" +
"    cl.ten_chat_lieu, \n" +
"    ca.ten_co_ao, \n" +
"    dd.ten_do_day, \n" +
"    tlh.ten_tinh_linh_hoat, \n" +
"    spct.gia_ban, \n" +
"    hdct.so_luong, \n" +
"    spct.trang_thai \n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"    INNER JOIN dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id \n" +
"    INNER JOIN dbo.XuatXu xx ON spct.id_xuat_xu = xx.id \n" +
"    INNER JOIN dbo.MauSac ms ON spct.id_mau_sac = ms.id \n" +
"    INNER JOIN dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id \n" +
"    INNER JOIN dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id \n" +
"    INNER JOIN dbo.CoAo ca ON spct.id_co_ao = ca.id \n" +
"    INNER JOIN dbo.DoDay dd ON spct.id_do_day = dd.id \n" +
"    INNER JOIN dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id\n" +
"    INNER JOIN dbo.HoaDonChiTiet hdct ON spct.id = hdct.id_san_pham_chi_tiet";

        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonChiTietReponse(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getInt(12), rs.getBoolean(13)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<HoaDonChiTietReponse> getByIdHoaDon(Integer idHD) {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    th.ten_thuong_hieu,  \n" +
"    xx.ten_nuoc, \n" +
"    ms.ten_mau, \n" +
"    kt.size, \n" +
"    cl.ten_chat_lieu, \n" +
"    ca.ten_co_ao,  \n" +
"    dd.ten_do_day, \n" +
"    tlh.ten_tinh_linh_hoat, \n" +
"    spct.gia_ban, \n" +
"    hdct.so_luong, \n" +
"    spct.trang_thai  \n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct \n" +
"    INNER JOIN dbo.HoaDonChiTiet hdct ON spct.id = hdct.id_san_pham_chi_tiet\n" +
"    INNER JOIN dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n" +
"    INNER JOIN dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n" +
"    INNER JOIN dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n" +
"    INNER JOIN dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n" +
"    INNER JOIN dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n" +
"    INNER JOIN dbo.CoAo ca ON spct.id_co_ao = ca.id  \n" +
"    INNER JOIN dbo.DoDay dd ON spct.id_do_day = dd.id  \n" +
"    INNER JOIN dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id\n" +
"WHERE \n" +
"    hdct.id_hoa_don = ?";

        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);// l?
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonChiTietReponse(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getInt(12), rs.getBoolean(13)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}

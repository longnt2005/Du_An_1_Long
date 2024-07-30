/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.config.DBConnect;
import main.entity.ThuocTinhSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThuocTinhSanPhamRepository {

    public ArrayList<ThuocTinhSanPham> getAll() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id, ma_chat_lieu AS ma, ten_chat_lieu FROM ChatLieu WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_co_ao AS ma, ten_co_ao FROM CoAo WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_do_day AS ma, ten_do_day FROM DoDay WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_kich_thuoc AS ma, size FROM KichThuoc WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_mau_sac AS ma, ten_mau FROM MauSac WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_thuong_hieu AS ma, ten_thuong_hieu FROM ThuongHieu WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_tinh_linh_hoat AS ma, ten_tinh_linh_hoat FROM TinhLinhHoat WHERE trang_thai = 1 UNION ALL \n"
                + "SELECT id, ma_xuat_xu AS ma, ten_nuoc FROM XuatXu WHERE trang_thai = 1";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getChatLieu() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_chat_lieu, ten_chat_lieu FROM ChatLieu where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getCoAO() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_co_ao, ten_co_ao FROM CoAo where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getDoDay() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_do_day, ten_do_day FROM DoDay where trang_thai = 1 ";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getKichThuoc() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_kich_thuoc, size FROM KichThuoc where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getMauSac() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_mau_sac, ten_mau FROM MauSac where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getThuongHieu() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_thuong_hieu, ten_thuong_hieu FROM ThuongHieu where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getTinhLinhHoat() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_tinh_linh_hoat, ten_tinh_linh_hoat FROM TinhLinhHoat where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<ThuocTinhSanPham> getXuatXu() {
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql = "SELECT id,ma_xuat_xu, ten_nuoc FROM XuatXu where trang_thai = 1";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThuocTinhSanPham(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean insertCoAo(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into CoAo(ten_co_ao)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertChatLieu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into ChatLieu(ten_chat_lieu)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertThuongHieu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into ThuongHieu(ten_thuong_hieu)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertKichThuoc(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into KichThuoc(size)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertMauSac(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into MauSac(ten_mau)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertDoDay(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into DoDay(ten_do_day)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertPhongCach(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into TinhLinhHoat(ten_tinh_linh_hoat)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean insertXuatXu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into XuatXu(ten_nuoc)\n"
                + "values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateThuongHieu(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update ThuongHieu\n"
                + "set ten_thuong_hieu=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateChatLieu(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update ChatLieu\n"
                + "set ten_chat_lieu=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateKichThuoc(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update KichThuoc\n"
                + "set size=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateCoAo(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update CoAo\n"
                + "set ten_co_ao=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateMauSac(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update MauSac\n"
                + "set ten_mau=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateDoDay(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update DoDay\n"
                + "set ten_do_day=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updatePhongCach(ThuocTinhSanPham ttsp, String ma) {
        int check = 0;
        String sql = "update TinhLinhHoat\n"
                + "set ten_tinh_linh_hoat=?\n"
                + "where ma_tinh_linh_hoat=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, ttsp.getMaThuocTinhSanPham());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateXuatXu(ThuocTinhSanPham ttsp, Integer id) {
        int check = 0;
        String sql = "update XuatXu\n"
                + "set ten_nuoc=?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeThuongHieu(Integer id) {
        int check = 0;
        String sql = "update ThuongHieu set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeChatLieu(Integer id) {
        int check = 0;
        String sql = "update ChatLieu set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeKichThuoc(Integer id) {
        int check = 0;
        String sql = "update KichThuoc set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeCoAo(Integer id) {
        int check = 0;
        String sql = "update CoAo set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeMauSac(Integer id) {
        int check = 0;
        String sql = "update MauSac set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeDoDay(Integer id) {
        int check = 0;
        String sql = "update DoDay set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removePhongCach(Integer id) {
        int check = 0;
        String sql = "update PhongCach set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean removeXuatXu(Integer id) {
        int check = 0;
        String sql = "update XuatXu set trang_thai=0 where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}

package com.Product.form;

import com.Product.Helper.BarCodeJFrame;
import com.Product.Helper.ZXingHelper;
import main.repository.ChatLieuRepository;
import main.repository.CoAoRepository;
import main.repository.DoDayRepository;
import main.repository.KichThuocRepository;
import main.repository.MauSacRepository;
import Jframe.ThemTenSanPhamJFrame;
import main.repository.SanPhamChiTietRepository;
import main.repository.SanPhamRepository;
import main.repository.ThuocTinhSanPhamRepository;
import main.repository.ThuongHieuRepository;
import main.repository.TinhLinhHoatRepository;
import main.repository.XuatXuRepository;
import Jframe.ThemChatLieuJFrame;
import Jframe.ThemCoAoJFrame;
import Jframe.ThemDoDayJFrame;
import Jframe.ThemKichThuocJFrame;
import Jframe.ThemMauSacJFrame;
import Jframe.ThemThuongHieuJFrame;
import Jframe.ThemTinhLinhHoatJFrame;
import Jframe.ThemXuatXuJFrame;
import Jframe.ThongTinSPJFrame;
import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import com.Product.component.Menu;
import main.entity.ChatLieu;
import main.entity.CoAo;
import main.entity.DoDay;
import main.entity.KichThuoc;
import main.entity.MauSac;
import main.entity.SanPham;
import main.entity.SanPhamChiTiet;
import main.entity.ThuocTinhSanPham;
import main.entity.ThuongHieu;
import main.entity.TinhLinhHoat;
import main.entity.XuatXu;
import main.response.SanPhamChiTietRespone;
import com.Product.swing.MenuAnimation;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SanPhamForm extends javax.swing.JPanel {

    private DefaultComboBoxModel dcbmXuatXu;
    private XuatXuRepository xuatXuRepo = new XuatXuRepository();

    private DefaultComboBoxModel dcbmKichThuoc;
    private KichThuocRepository kichThuocRepo = new KichThuocRepository();

    private DefaultComboBoxModel dcbmCoAo;
    private CoAoRepository coAoRepo = new CoAoRepository();

    private DefaultComboBoxModel dcbmDoDay;
    private DoDayRepository doDayRepository = new DoDayRepository();

    private DefaultComboBoxModel dcbmMauSac;
    private MauSacRepository mauSacRepo = new MauSacRepository();

    private DefaultComboBoxModel dcbmTinhLinhHoat;
    private TinhLinhHoatRepository tinhLinhHoatRepo = new TinhLinhHoatRepository();

    private DefaultComboBoxModel dcbmThuongHieu;
    private ThuongHieuRepository thuongHieuRepo = new ThuongHieuRepository();

    private DefaultComboBoxModel dcbmChatLieu;
    private ChatLieuRepository chatLieuRepo = new ChatLieuRepository();

    private DefaultTableModel dtmSanPhamChiTiet = new DefaultTableModel();
    private SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();

    private DefaultTableModel dtmSanPham = new DefaultTableModel();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    private DefaultTableModel dtmThuocTinhSanPham;
    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository;

    public static String maSanPhamChiTiet ;

    public SanPhamForm() {
        initComponents();
        setOpaque(false);
        showComboboxChatLieu(chatLieuRepo.getAll());
        showComboboxCoAo(coAoRepo.getAll());
        showComboboxMauSac(mauSacRepo.getAll());
        showComboboxDoDay(doDayRepository.getAll());
        showComboboxThuongHieu(thuongHieuRepo.getAll());
        showComboboxXuatXu(xuatXuRepo.getAll());
        showComboboxKichThuoc(kichThuocRepo.getAll());
        showComboboxTenSanPham(sanPhamRepository.getAll());
        showComboboxTinhLinhHoat(tinhLinhHoatRepo.getAll());
        showComboboxLocChatLieu(chatLieuRepo.getAll());
        showComboboxLocXuatXu(xuatXuRepo.getAll());
        showComboboxLocPhongCach(tinhLinhHoatRepo.getAll());
        showComboboxLocThuongHieu(thuongHieuRepo.getAll());

//        dcbm = new DefaultComboBoxModel();
//
//        dcbm = (DefaultComboBoxModel) cbb_TenSPCT.getModel();
//        showComboboxModel();
        jpn_Them_SPCT.setVisible(false);

        sanPhamChiTietRepository = new SanPhamChiTietRepository();
        dtmSanPhamChiTiet = (DefaultTableModel) tbl_San_Pham_Chi_Tiet.getModel();
        showTableSanPhamChiTiet(sanPhamChiTietRepository.getAll());

        sanPhamRepository = new SanPhamRepository();
        dtmSanPham = (DefaultTableModel) tbl_SanPham.getModel();
        showTableSanPham(sanPhamRepository.getAll());

        thuocTinhSanPhamRepository = new ThuocTinhSanPhamRepository();
        dtmThuocTinhSanPham = (DefaultTableModel) tbl_Thuoc_Tinh_San_Pham.getModel();
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());

        TableActionEvent eventSanPham = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?");
                if (respone == JOptionPane.YES_OPTION) {
                    SanPham sanpham = getFormData();
                    if (sanpham == null) {

                        return;
                    }
                    try {
                        sanPhamRepository.add(sanpham);
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                        showTableSanPham(sanPhamRepository.getAllGiamDan());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void onDelete(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?");
                if (tbl_SanPham.isEditing()) {
                    tbl_SanPham.getCellEditor().stopCellEditing();
                    {

                    }
                }
            }

        };

        TableActionEvent eventThuocTinhSanPham = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không vậy????");
                if (respone == JOptionPane.YES_OPTION) {

                }
            }

            @Override
            public void onDelete(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không vậy?");
                if (tbl_SanPham.isEditing()) {
                    tbl_SanPham.getCellEditor().stopCellEditing();
                    {

                    }
                }
            }

        };

        tbl_SanPham.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        tbl_SanPham.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(eventSanPham));

        tbl_Thuoc_Tinh_San_Pham.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        tbl_Thuoc_Tinh_San_Pham.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(eventThuocTinhSanPham));

    }

    private void showTableSanPhamChiTiet(ArrayList<SanPhamChiTietRespone> lists) {
        dtmSanPhamChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPhamChiTiet.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSPCT(), s.getTenSP(),
            s.getThuongHieu(), s.getXuatXu(),
            s.getMauSac(),
            s.getKichThuoc(), s.getChatLieu(), s.getCoAo(), s.getDoDay(), s.getPhongCach(),
            s.getGiaBan(), s.getSoLuong(),
            s.isTrangThai() ? "Hết Hàng" : "Còn hàng"
        }));
    }

    private void showTableSanPham(ArrayList<SanPham> lists) {
        dtmSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSanPham(), s.getTenSanPham(),
            s.getMoTa(), s.getSoLuong(), s.isTrangThai() ? "Còn hàng" : "Hết hàng",
            s.getNgayTao()}));
    }

    private void showTableThuocTinhSanPham(ArrayList<ThuocTinhSanPham> lists) {
        dtmThuocTinhSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmThuocTinhSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaThuocTinhSanPham(), s.getTenThuocTinhSanPham(),}));
    }

    private void showComboboxChatLieu(ArrayList<ChatLieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_ChatLieuSPCT.getModel();
        for (ChatLieu cl : list) {
            comboBoxModel.addElement(cl);
            System.out.println(cl.getId());
        }
    }

    private void showComboboxThuongHieu(ArrayList<ThuongHieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_ThuongHieuSPCT.getModel();
        for (ThuongHieu cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxTinhLinhHoat(ArrayList<TinhLinhHoat> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_TinhLinhHoat.getModel();
        for (TinhLinhHoat cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxCoAo(ArrayList<CoAo> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_CoAoSPCT.getModel();
        for (CoAo cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxKichThuoc(ArrayList<KichThuoc> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_KichThuoc.getModel();
        for (KichThuoc cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxTenSanPham(ArrayList<SanPham> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_TenSPCT.getModel();
        for (SanPham cl : list) {
            comboBoxModel.addElement(cl.getTenSanPham());
        }
    }

    private void showComboboxMauSac(ArrayList<MauSac> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_MauSac.getModel();
        for (MauSac cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxDoDay(ArrayList<DoDay> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_DoDay.getModel();
        for (DoDay cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxXuatXu(ArrayList<XuatXu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_XuatXu.getModel();
        for (XuatXu cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxLocXuatXu(ArrayList<XuatXu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocXuatXu.getModel();
        for (XuatXu cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxLocThuongHieu(ArrayList<ThuongHieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocThuongHieu.getModel();
        for (ThuongHieu cl : list) {
            comboBoxModel.addElement(cl);

        }

    }

    private void showComboboxLocPhongCach(ArrayList<TinhLinhHoat> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocTinhLinhHoat.getModel();
        for (TinhLinhHoat cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxLocChatLieu(ArrayList<ChatLieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocChatLieu.getModel();
        for (ChatLieu cl : list) {
            comboBoxModel.addElement(cl);

        }
    }

    private SanPham getFormData() {
        // builder 
        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Sản Phẩm", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SanPham kh = SanPham.builder()
                .maSanPham(txtMaSanPham.getText())
                .tenSanPham(txtTenSanPham.getText())
                .moTa(txtMoTaSanPham.getText())
                .build();
        // Tuong ung voi contructor khong tham so
//        KhachHang kh1 = new KhachHang();
        return kh;
    }

    private ThuocTinhSanPham getFormDataThuocTinhSP() {
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ThuocTinhSanPham ttsp = ThuocTinhSanPham.builder()
                .maThuocTinhSanPham(txtMaThuocTinh.getText())
                .tenThuocTinhSanPham(txtTenThuocTinh.getText())
                .build();

        return ttsp;
    }

    private SanPhamChiTietRespone getFormDataSanPhamCT() {
        // Lấy dữ liệu từ giao diện người dùng

        String tenSP = cbb_TenSPCT.getSelectedItem().toString().trim();
        String thuongHieu = cbb_ThuongHieuSPCT.getSelectedItem().toString().trim();
        String chatLieu = cbb_ChatLieuSPCT.getSelectedItem().toString().trim();
        String kichThuoc = cbb_KichThuoc.getSelectedItem().toString().trim();
        String coAo = cbb_CoAoSPCT.getSelectedItem().toString().trim();
        String mauSac = cbb_MauSac.getSelectedItem().toString().trim();
        String doDay = cbb_DoDay.getSelectedItem().toString().trim();
        String xuatXu = cbb_XuatXu.getSelectedItem().toString().trim();
        String tinhLinhHoat = cbb_TinhLinhHoat.getSelectedItem().toString().trim();
        String giaBanText = txt_GiaBanSPCT.getText().trim();
        String soLuongText = txt_SoLuongSPCT.getText().trim();

        // Kiểm tra các trường dữ liệu không để trống
        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (thuongHieu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thương hiệu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (chatLieu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chất liệu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (kichThuoc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kích thước không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (coAo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Có áo không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (mauSac.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Màu sắc không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (doDay.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Độ dày không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (xuatXu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Xuất xứ không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (tinhLinhHoat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phong Cách không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaBanText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá bán không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            // Chuyển đổi dữ liệu và xây dựng đối tượng respone
            Double giaBan = Double.parseDouble(giaBanText);
            Integer soLuong = Integer.parseInt(soLuongText);

            return SanPhamChiTietRespone.builder()
                    .tenSP(tenSP)
                    .thuongHieu(thuongHieu)
                    .xuatXu(xuatXu)
                    .mauSac(mauSac)
                    .kichThuoc(kichThuoc)
                    .chatLieu(chatLieu)
                    .coAo(coAo)
                    .doDay(doDay)
                    .phongCach(tinhLinhHoat)
                    .giaBan(giaBan)
                    .soLuong(soLuong)
                    .build();
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Phương thức convertResponeToEntity sử dụng convertToId
    private SanPhamChiTiet convertResponeToEntity(SanPhamChiTietRespone respone) {
        // Lấy danh sách sản phẩm theo tên
        ArrayList<SanPham> spList = sanPhamRepository.getSanPhamByTen(respone.getTenSP());

        // Lấy phần tử đầu tiên trong danh sách hoặc trả về null nếu danh sách rỗng
        SanPham sp = spList.stream().findFirst().orElse(null);

        // Lấy các thuộc tính khác
        ChatLieu cl = chatLieuRepo.getChatLieuByTen(respone.getChatLieu());
        ThuongHieu th = thuongHieuRepo.getThuongHieuByTen(respone.getThuongHieu());
        XuatXu xx = xuatXuRepo.getXuatXuByTen(respone.getXuatXu());
        MauSac ms = mauSacRepo.getMauSacByTen(respone.getMauSac());
        KichThuoc kt = kichThuocRepo.getKichThuocByTen(respone.getKichThuoc());
        CoAo ca = coAoRepo.getCoAoByTen(respone.getCoAo());
        DoDay dd = doDayRepository.getDoDayByTen(respone.getDoDay());
        TinhLinhHoat tlh = tinhLinhHoatRepo.getTinhLinhHoatByTen(respone.getPhongCach());

        // Xử lý trường hợp không tìm thấy sản phẩm
        if (sp == null) {
            throw new RuntimeException("Sản phẩm không tìm thấy: " + respone.getTenSP());
        }

        return SanPhamChiTiet.builder()
                .maSanPhamChiTiet(respone.getMaSPCT())
                .sanPhamID(sp.getId())
                .thuongHieuID(th != null ? th.getId() : null)
                .XuatXuID(xx != null ? xx.getId() : null)
                .mauSacID(ms != null ? ms.getId() : null)
                .kichThuocID(kt != null ? kt.getId() : null)
                .chatLieuID(cl != null ? cl.getId() : null)
                .coAoID(ca != null ? ca.getId() : null)
                .doDayID(dd != null ? dd.getId() : null)
                .tinhLinhHoatID(tlh != null ? tlh.getId() : null)
                .giaBan(respone.getGiaBan())
                .soLuongTon(respone.getSoLuong())
                .trangThai(respone.isTrangThai())
                .build();
    }

    private void detailSanPham(int index) {
        SanPham sp = sanPhamRepository.getAllGiamDan().get(index);
        txtMaSanPham.setText(sp.getMaSanPham());
        txtTenSanPham.setText(sp.getTenSanPham());
        txtMoTaSanPham.setText(sp.getMoTa());

    }

    private void detailSanPhamChiTiet(int index) {
        SanPham sp = sanPhamRepository.getAll().get(index);
        SanPhamChiTietRespone spctrp = sanPhamChiTietRepository.getAll().get(index);
        cbb_TenSPCT.setSelectedItem(sp.getTenSanPham());
        cbb_ThuongHieuSPCT.setSelectedItem(spctrp.getThuongHieu());
        cbb_ChatLieuSPCT.setSelectedItem(spctrp.getChatLieu());
        cbb_KichThuoc.setSelectedItem(spctrp.getKichThuoc());
        cbb_CoAoSPCT.setSelectedItem(spctrp.getCoAo());
        cbb_MauSac.setSelectedItem(spctrp.getMauSac());
        cbb_XuatXu.setSelectedItem(spctrp.getXuatXu());
        cbb_DoDay.setSelectedItem(spctrp.getDoDay());
        Double giaBan = spctrp.getGiaBan();
        txt_GiaBanSPCT.setText(giaBan.toString());
        Integer soLuong = spctrp.getSoLuong();
        txt_SoLuongSPCT.setText(soLuong.toString());

    }

    private void detalThuocTinhSanPham(int index) {
        // Lấy danh sách thuộc tính sản phẩm từ repository
//        List<ThuocTinhSanPham> danhSachThuocTinh = thuocTinhSanPhamRepository.getAll();

        // Kiểm tra nếu index hợp lệ
        if (index < 0 || index > tbl_Thuoc_Tinh_San_Pham.getRowCount() - 1) {
            JOptionPane.showMessageDialog(null, "Chỉ số không hợp lệ.");
            return;
        }

        // Lấy thuộc tính sản phẩm từ danh sách
//        ThuocTinhSanPham ttsp = danhSachThuocTinh.get(index);
        // Cập nhật thông tin lên các trường văn bản
//        txtMaThuocTinh.setText(ttsp.getMaThuocTinhSanPham());
//        txtTenThuocTinh.setText(ttsp.getTenThuocTinhSanPham());
        // Xác định thuộc tính và chọn radio button tương ứng
//        String ma = ttsp.getMaThuocTinhSanPham();
//        rdoChatLieu.setSelected(ma.startsWith("CL"));
//        rdoCoAo.setSelected(ma.startsWith("CA"));
//        rdoDoDay.setSelected(ma.startsWith("DD"));
//        rdoKichThuoc.setSelected(ma.startsWith("KT"));
//        rdoMauSac.setSelected(ma.startsWith("MS"));
//        rdoThuongHieu.setSelected(ma.startsWith("TH"));
//        rdoXuatXu.setSelected(ma.startsWith("XX"));
//        rdoPhongCach.setSelected(ma.startsWith("TLH"));
        String maThuocTinh = (String) tbl_Thuoc_Tinh_San_Pham.getValueAt(index, 1);
        String tenThuocTinh = (String) tbl_Thuoc_Tinh_San_Pham.getValueAt(index, 2);

        txtMaThuocTinh.setText(maThuocTinh);
        txtTenThuocTinh.setText(tenThuocTinh);
    }

    public JComboBox<String> getCbb_CoAo() {
        return cbb_CoAoSPCT;
    }

    public JComboBox<String> getCbb_tenSanPham() {
        return cbb_TenSPCT;
    }

    private void locSanPhamChiTiet() {

//    Integer idChatLieu = (Integer) cbb_LocChatLieu.getSelectedItem();
//        System.out.println(""+idChatLieu);
//    Integer idThuongHieu = (Integer) cbb_LocThuongHieu.getSelectedItem();
//    Integer idXuatXu = (Integer) cbb_LocXuatXu.getSelectedItem();
//    Integer idTinhLinhHoat = (Integer) cbb_LocTinhLinhHoat.getSelectedItem();
//
//    SanPhamChiTiet spct = new SanPhamChiTiet();
//    spct.setChatLieuID(idChatLieu);
//    spct.setThuongHieuID(idThuongHieu);
//    spct.setXuatXuID(idXuatXu);
//    spct.setTinhLinhHoatID(idTinhLinhHoat);
//
//    ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(spct);
//        System.out.println("Hello: "+list);
//    showTableSanPhamChiTiet(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgSanPham = new javax.swing.ButtonGroup();
        btgThuocTinhSanPham = new javax.swing.ButtonGroup();
        tabbedPaneCustomm1 = new com.Product.GUI.tabbed.TabbedPaneCustomm();
        jpn_SanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSanPham = new com.Product.GUI.textfield.TextField();
        txtTenSanPham = new com.Product.GUI.textfield.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_ThemSanPham = new com.Product.swing.ButtonBadges();
        btn_SuaSanPham = new com.Product.swing.ButtonBadges();
        btn_XoaSanPham = new com.Product.swing.ButtonBadges();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaSanPham = new javax.swing.JTextArea();
        btn_LamMoiSP = new com.Product.swing.ButtonBadges();
        jLabel12 = new javax.swing.JLabel();
        rdo_DangBan = new javax.swing.JRadioButton();
        rdo_TatCa = new javax.swing.JRadioButton();
        rdo_NgungBan = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SanPham = new com.Product.GUI.Table();
        jPanel10 = new javax.swing.JPanel();
        txt_SearchSP = new com.Product.GUI.textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        btn_TimSanPham = new com.Product.swing.ButtonBadges();
        btn_XuatExcelSanPham = new com.Product.swing.ButtonBadges();
        jpn_SPCT = new javax.swing.JPanel();
        jpn_Them_SPCT = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        buttonBadges3 = new com.Product.swing.ButtonBadges();
        cbb_ThuongHieuSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_ChatLieuSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_TenSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_KichThuoc = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_MauSac = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbb_XuatXu = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_DoDay = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbb_CoAoSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        txt_SoLuongSPCT = new com.Product.GUI.textfield.TextField();
        txt_GiaBanSPCT = new com.Product.GUI.textfield.TextField();
        btn_ThemSPCT = new com.Product.swing.ButtonBadges();
        btn_ThemSPCT1 = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhTenSP = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhCoAo = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhThuongHieu = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhMauSac = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhChatLieu = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhXuatXu = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhKichThuoc = new com.Product.swing.ButtonBadges();
        btn_ThemNhanhDoDay = new com.Product.swing.ButtonBadges();
        cbb_TinhLinhHoat = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        btn_ThemNhanhTinhLinhHoat = new com.Product.swing.ButtonBadges();
        jLabel16 = new javax.swing.JLabel();
        jpn_Thong_Tin_SPCT = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_San_Pham_Chi_Tiet = new com.Product.GUI.Table();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        cbb_LocThuongHieu = new com.Product.GUI.Combobox();
        cbb_LocXuatXu = new com.Product.GUI.Combobox();
        cbb_LocChatLieu = new com.Product.GUI.Combobox();
        cbb_LocTinhLinhHoat = new com.Product.GUI.Combobox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_TimKiemSPCT = new com.Product.GUI.textfield.TextField();
        btn_ThemMoiSPCT3 = new com.Product.swing.ButtonBadges();
        btn_Quet_QR3 = new com.Product.swing.ButtonBadges();
        btn_LamMoiSPCT3 = new com.Product.swing.ButtonBadges();
        cbb_LocTheoGia = new com.Product.GUI.Combobox();
        btn_XuatExcelSPCT = new com.Product.swing.ButtonBadges();
        btn_TimSanPhamChiTiet = new com.Product.swing.ButtonBadges();
        lblQRCode = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtTenThuocTinh = new com.Product.GUI.textfield.TextField();
        txtMaThuocTinh = new com.Product.GUI.textfield.TextField();
        jPanel8 = new javax.swing.JPanel();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoDoDay = new javax.swing.JRadioButton();
        rdoPhongCach = new javax.swing.JRadioButton();
        rdoCoAo = new javax.swing.JRadioButton();
        rdoXuatXu = new javax.swing.JRadioButton();
        rdoTatCaThuocTinhSP = new javax.swing.JRadioButton();
        btn_ThemThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        btn_SuaThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        btn_XoaThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        btn_LamMoiThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Thuoc_Tinh_San_Pham = new com.Product.GUI.Table();

        tabbedPaneCustomm1.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustomm1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabbedPaneCustomm1.setSelectedColor(new java.awt.Color(255, 255, 0));
        tabbedPaneCustomm1.setUnselectedColor(new java.awt.Color(255, 255, 204));

        jpn_SanPham.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Thông Tin Sản Phẩm");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtMaSanPham.setEnabled(false);
        txtMaSanPham.setLabelText("Mã sản phẩm");

        txtTenSanPham.setLabelText("Nhập tên sản phẩm");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mô tả sản phẩm");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Mã sản phẩm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên sản phẩm");

        btn_ThemSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_ThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemSanPham.setText("Thêm");
        btn_ThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSanPhamActionPerformed(evt);
            }
        });

        btn_SuaSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_SuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        btn_SuaSanPham.setText("Sửa");
        btn_SuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_SuaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SuaSanPhamMouseClicked(evt);
            }
        });
        btn_SuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaSanPhamActionPerformed(evt);
            }
        });

        btn_XoaSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_XoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        btn_XoaSanPham.setText("Xóa");
        btn_XoaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSanPhamActionPerformed(evt);
            }
        });

        txtMoTaSanPham.setColumns(20);
        txtMoTaSanPham.setRows(5);
        jScrollPane2.setViewportView(txtMoTaSanPham);

        btn_LamMoiSP.setBackground(new java.awt.Color(255, 255, 0));
        btn_LamMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiSP.setText("Làm Mới");
        btn_LamMoiSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_LamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_SuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(116, 116, 116)
                                .addComponent(btn_XoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btn_ThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btn_LamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(148, 148, 148))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Trạng thái");

        rdo_DangBan.setBackground(new java.awt.Color(255, 255, 255));
        btgSanPham.add(rdo_DangBan);
        rdo_DangBan.setText("Còn hàng");
        rdo_DangBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_DangBanMouseClicked(evt);
            }
        });
        rdo_DangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DangBanActionPerformed(evt);
            }
        });

        btgSanPham.add(rdo_TatCa);
        rdo_TatCa.setSelected(true);
        rdo_TatCa.setText("Tất Cả");
        rdo_TatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_TatCaMouseClicked(evt);
            }
        });

        btgSanPham.add(rdo_NgungBan);
        rdo_NgungBan.setText("Hết hàng");
        rdo_NgungBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_NgungBanMouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204), 3));

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Mô tả", "Số Lượng", "Trạng Thái", "Ngày Tạo", "Hành Động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_SearchSP.setLabelText("Tìm kiếm tại đây");
        txt_SearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchSPActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm kiếm sản phẩm");

        btn_TimSanPham.setBackground(new java.awt.Color(255, 204, 204));
        btn_TimSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        btn_TimSanPham.setText("Tìm ");
        btn_TimSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_SearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_TimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        btn_XuatExcelSanPham.setBackground(new java.awt.Color(255, 204, 102));
        btn_XuatExcelSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btn_XuatExcelSanPham.setText("Xuất Excel");
        btn_XuatExcelSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XuatExcelSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcelSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpn_SanPhamLayout = new javax.swing.GroupLayout(jpn_SanPham);
        jpn_SanPham.setLayout(jpn_SanPhamLayout);
        jpn_SanPhamLayout.setHorizontalGroup(
            jpn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_SanPhamLayout.createSequentialGroup()
                .addGroup(jpn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_SanPhamLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jpn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpn_SanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_TatCa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_DangBan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_NgungBan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_XuatExcelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 928, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpn_SanPhamLayout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jpn_SanPhamLayout.setVerticalGroup(
            jpn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jpn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdo_DangBan)
                    .addComponent(rdo_TatCa)
                    .addComponent(rdo_NgungBan)
                    .addComponent(btn_XuatExcelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneCustomm1.addTab("Sản phẩm", jpn_SanPham);

        jpn_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        jpn_Them_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Thêm Sản Phẩm Chi Tiết");

        buttonBadges3.setBackground(new java.awt.Color(102, 255, 255));
        buttonBadges3.setText("Back");
        buttonBadges3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonBadges3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges3ActionPerformed(evt);
            }
        });

        cbb_TenSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TenSPCTActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Thương hiệu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Chất Liệu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Kích Thước");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cổ Áo");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Màu sắc");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Độ dày");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Xuất Xứ");

        txt_SoLuongSPCT.setLabelText("Số Lượng");

        txt_GiaBanSPCT.setLabelText("Giá Bán");

        btn_ThemSPCT.setBackground(new java.awt.Color(255, 255, 51));
        btn_ThemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemSPCT.setText("Thêm");
        btn_ThemSPCT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_ThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPCTActionPerformed(evt);
            }
        });

        btn_ThemSPCT1.setBackground(new java.awt.Color(255, 255, 51));
        btn_ThemSPCT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        btn_ThemSPCT1.setText("Sửa");
        btn_ThemSPCT1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btn_ThemNhanhTenSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhTenSPActionPerformed(evt);
            }
        });

        btn_ThemNhanhCoAo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhCoAoActionPerformed(evt);
            }
        });

        btn_ThemNhanhThuongHieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhThuongHieuActionPerformed(evt);
            }
        });

        btn_ThemNhanhMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhMauSacActionPerformed(evt);
            }
        });

        btn_ThemNhanhChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhChatLieuActionPerformed(evt);
            }
        });

        btn_ThemNhanhXuatXu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhXuatXuActionPerformed(evt);
            }
        });

        btn_ThemNhanhKichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhKichThuocActionPerformed(evt);
            }
        });

        btn_ThemNhanhDoDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhDoDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhDoDayActionPerformed(evt);
            }
        });

        btn_ThemNhanhTinhLinhHoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/add.png"))); // NOI18N
        btn_ThemNhanhTinhLinhHoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhTinhLinhHoatActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Phong Cách");

        javax.swing.GroupLayout jpn_Them_SPCTLayout = new javax.swing.GroupLayout(jpn_Them_SPCT);
        jpn_Them_SPCT.setLayout(jpn_Them_SPCTLayout);
        jpn_Them_SPCTLayout.setHorizontalGroup(
            jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btn_ThemNhanhTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbb_TinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(cbb_CoAoSPCT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_ThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_ThemNhanhTinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(53, 53, 53)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Them_SPCTLayout.createSequentialGroup()
                                .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(btn_ThemSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_GiaBanSPCT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbb_ThuongHieuSPCT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbb_MauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addComponent(btn_ThemNhanhThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32))
                                    .addComponent(btn_ThemNhanhMauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbb_ChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_ThemNhanhChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_ThemNhanhDoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ThemNhanhKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addComponent(cbb_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ThemNhanhXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonBadges3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpn_Them_SPCTLayout.setVerticalGroup(
            jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(55, 55, 55)
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbb_ThuongHieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ThemNhanhThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbb_ChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ThemNhanhChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_Them_SPCTLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ThemNhanhKichThuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addComponent(jLabel2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_Them_SPCTLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btn_ThemNhanhTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6)))
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addComponent(btn_ThemNhanhMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ThemSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemNhanhXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                            .addComponent(btn_ThemNhanhCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ThemNhanhTinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                            .addComponent(cbb_CoAoSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_GiaBanSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbb_TinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                            .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_ThemNhanhDoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbb_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(62, 62, 62)
                            .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBadges3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1728, Short.MAX_VALUE))
        );

        jpn_Thong_Tin_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        tbl_San_Pham_Chi_Tiet.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tbl_San_Pham_Chi_Tiet.setForeground(new java.awt.Color(255, 255, 255));
        tbl_San_Pham_Chi_Tiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên Sản Phẩm", "Thương Hiệu", "Xuất Xứ", "Màu Sắc", "Kích Thước", "Chất Liệu", "Cổ Áo", "Độ Dày", "Phong Cách", "Giá Bán", "Số Lượng", "Trạng Thái", "Hành Động"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_San_Pham_Chi_Tiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_San_Pham_Chi_Tiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_San_Pham_Chi_TietMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_San_Pham_Chi_TietMouseEntered(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_San_Pham_Chi_Tiet);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("Thông Tin Sản Phẩm Chi Tiết");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Tìm kiếm:");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cbb_LocThuongHieu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));
        cbb_LocThuongHieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocThuongHieuItemStateChanged(evt);
            }
        });
        cbb_LocThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_LocThuongHieuActionPerformed(evt);
            }
        });

        cbb_LocXuatXu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));
        cbb_LocXuatXu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocXuatXuItemStateChanged(evt);
            }
        });
        cbb_LocXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_LocXuatXuActionPerformed(evt);
            }
        });

        cbb_LocChatLieu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", " " }));
        cbb_LocChatLieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocChatLieuItemStateChanged(evt);
            }
        });
        cbb_LocChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_LocChatLieuActionPerformed(evt);
            }
        });

        cbb_LocTinhLinhHoat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", " " }));
        cbb_LocTinhLinhHoat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocTinhLinhHoatItemStateChanged(evt);
            }
        });
        cbb_LocTinhLinhHoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_LocTinhLinhHoatActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Chất Liệu");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Thương hiệu");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Phong cách");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Xuất xứ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(cbb_LocChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_LocThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_LocTinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_LocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29)
                            .addComponent(cbb_LocThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_LocChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(cbb_LocTinhLinhHoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbb_LocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        txt_TimKiemSPCT.setLabelText("Nhập tìm kiếm tại đây");
        txt_TimKiemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemSPCTActionPerformed(evt);
            }
        });

        btn_ThemMoiSPCT3.setBackground(new java.awt.Color(255, 255, 0));
        btn_ThemMoiSPCT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemMoiSPCT3.setText("Thêm Mới");
        btn_ThemMoiSPCT3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_ThemMoiSPCT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemMoiSPCT3MouseClicked(evt);
            }
        });
        btn_ThemMoiSPCT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMoiSPCT3ActionPerformed(evt);
            }
        });

        btn_Quet_QR3.setBackground(new java.awt.Color(255, 255, 0));
        btn_Quet_QR3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        btn_Quet_QR3.setText("Quét QR");
        btn_Quet_QR3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btn_LamMoiSPCT3.setBackground(new java.awt.Color(255, 255, 0));
        btn_LamMoiSPCT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiSPCT3.setText("Làm Mới");
        btn_LamMoiSPCT3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_LamMoiSPCT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiSPCT3ActionPerformed(evt);
            }
        });

        cbb_LocTheoGia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Giá từ cao đến thấp", "Giá từ thấp đến cao" }));
        cbb_LocTheoGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocTheoGiaItemStateChanged(evt);
            }
        });
        cbb_LocTheoGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_LocTheoGiaActionPerformed(evt);
            }
        });

        btn_XuatExcelSPCT.setBackground(new java.awt.Color(204, 204, 0));
        btn_XuatExcelSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btn_XuatExcelSPCT.setText("Xuất Excel");
        btn_XuatExcelSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_XuatExcelSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcelSPCTActionPerformed(evt);
            }
        });

        btn_TimSanPhamChiTiet.setBackground(new java.awt.Color(255, 204, 204));
        btn_TimSanPhamChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        btn_TimSanPhamChiTiet.setText("Tìm ");
        btn_TimSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimSanPhamChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpn_Thong_Tin_SPCTLayout = new javax.swing.GroupLayout(jpn_Thong_Tin_SPCT);
        jpn_Thong_Tin_SPCT.setLayout(jpn_Thong_Tin_SPCTLayout);
        jpn_Thong_Tin_SPCTLayout.setHorizontalGroup(
            jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_TimSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(txt_TimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(155, 155, 155)
                        .addComponent(lblQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(btn_ThemMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(btn_Quet_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(btn_LamMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btn_XuatExcelSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbb_LocTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpn_Thong_Tin_SPCTLayout.setVerticalGroup(
            jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txt_TimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_LamMoiSPCT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_ThemMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Quet_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XuatExcelSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_LocTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1627, 1627, 1627))
        );

        javax.swing.GroupLayout jpn_SPCTLayout = new javax.swing.GroupLayout(jpn_SPCT);
        jpn_SPCT.setLayout(jpn_SPCTLayout);
        jpn_SPCTLayout.setHorizontalGroup(
            jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_SPCTLayout.createSequentialGroup()
                .addComponent(jpn_Thong_Tin_SPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_SPCTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpn_Them_SPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        jpn_SPCTLayout.setVerticalGroup(
            jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpn_Thong_Tin_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_SPCTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpn_Them_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tabbedPaneCustomm1.addTab("Sản phẩm chi tiết", jpn_SPCT);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Thuộc tính sản phẩm");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        txtTenThuocTinh.setLabelText("Tên thuộc tính");
        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        txtMaThuocTinh.setEnabled(false);
        txtMaThuocTinh.setLabelText("Mã thuộc tính");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh mục thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        btgThuocTinhSanPham.add(rdoThuongHieu);
        rdoThuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoThuongHieu.setText("Thương hiệu");
        rdoThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoThuongHieuMouseClicked(evt);
            }
        });
        rdoThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThuongHieuActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoChatLieu);
        rdoChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChatLieuMouseClicked(evt);
            }
        });
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoKichThuoc);
        rdoKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoKichThuoc.setText("Kích thước ");
        rdoKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKichThuocMouseClicked(evt);
            }
        });
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoMauSac);
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauSacMouseClicked(evt);
            }
        });
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoDoDay);
        rdoDoDay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDoDay.setText("Đồ dày");
        rdoDoDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDoDayMouseClicked(evt);
            }
        });
        rdoDoDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDoDayActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoPhongCach);
        rdoPhongCach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoPhongCach.setText("Phong cách");
        rdoPhongCach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoPhongCachMouseClicked(evt);
            }
        });
        rdoPhongCach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPhongCachActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoCoAo);
        rdoCoAo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoCoAo.setText("Cổ áo");
        rdoCoAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoCoAoMouseClicked(evt);
            }
        });
        rdoCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCoAoActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoXuatXu);
        rdoXuatXu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoXuatXu.setText("Xuất xứ");
        rdoXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoXuatXuMouseClicked(evt);
            }
        });
        rdoXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoXuatXuActionPerformed(evt);
            }
        });

        rdoTatCaThuocTinhSP.setBackground(new java.awt.Color(255, 255, 255));
        btgThuocTinhSanPham.add(rdoTatCaThuocTinhSP);
        rdoTatCaThuocTinhSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoTatCaThuocTinhSP.setSelected(true);
        rdoTatCaThuocTinhSP.setText("Tất Cả");
        rdoTatCaThuocTinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTatCaThuocTinhSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoCoAo)
                    .addComponent(rdoTatCaThuocTinhSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoMauSac))
                .addGap(47, 47, 47)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoDoDay))
                .addGap(52, 52, 52)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdoKichThuoc)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdoPhongCach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(rdoXuatXu)
                        .addGap(22, 22, 22))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoTatCaThuocTinhSP))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoDoDay)
                    .addComponent(rdoPhongCach)
                    .addComponent(rdoXuatXu)
                    .addComponent(rdoCoAo))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_ThemThuocTinhSanPham.setBackground(new java.awt.Color(255, 255, 51));
        btn_ThemThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemThuocTinhSanPham.setText("Thêm");
        btn_ThemThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_SuaThuocTinhSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_SuaThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        btn_SuaThuocTinhSanPham.setText("Sửa");
        btn_SuaThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_SuaThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_XoaThuocTinhSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_XoaThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xoa.png"))); // NOI18N
        btn_XoaThuocTinhSanPham.setText("Xóa");
        btn_XoaThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XoaThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_LamMoiThuocTinhSanPham.setBackground(new java.awt.Color(255, 255, 0));
        btn_LamMoiThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiThuocTinhSanPham.setText("Làm Mới");
        btn_LamMoiThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_LamMoiThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiThuocTinhSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btn_SuaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btn_XoaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(762, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XoaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));

        tbl_Thuoc_Tinh_San_Pham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính", "Hành Động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Thuoc_Tinh_San_Pham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_Thuoc_Tinh_San_Pham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_Thuoc_Tinh_San_PhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_Thuoc_Tinh_San_PhamMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_Thuoc_Tinh_San_Pham);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1659, Short.MAX_VALUE))
        );

        tabbedPaneCustomm1.addTab("Thuộc tính sản phẩm", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustomm1, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustomm1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void txt_SearchSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchSPActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        int i = tbl_SanPham.getSelectedRow();

        int id_sanPham = (int) tbl_SanPham.getValueAt(i, 0);
        showTableSanPhamChiTiet(sanPhamChiTietRepository.getSanPhamChiTietByIdSanPham(id_sanPham));
//        detailSanPham(tbl_SanPham.getSelectedRow());
        jpn_SanPham.setVisible(false);
        jpn_SPCT.setVisible(true);

    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void rdoThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoThuongHieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoThuongHieuMouseClicked

    private void rdoChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChatLieuMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());
    }//GEN-LAST:event_rdoChatLieuMouseClicked

    private void rdoKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKichThuocMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());
    }//GEN-LAST:event_rdoKichThuocMouseClicked

    private void rdoCoAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoCoAoMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());
    }//GEN-LAST:event_rdoCoAoMouseClicked

    private void rdoMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauSacMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());
    }//GEN-LAST:event_rdoMauSacMouseClicked

    private void rdoDoDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDoDayMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDoDay());
    }//GEN-LAST:event_rdoDoDayMouseClicked

    private void rdoPhongCachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoPhongCachMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());
    }//GEN-LAST:event_rdoPhongCachMouseClicked

    private void rdoXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoXuatXuMouseClicked
        // TODO add your handling code here:
//        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
    }//GEN-LAST:event_rdoXuatXuMouseClicked

    private void tbl_Thuoc_Tinh_San_PhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked
        // TODO add your handling code here:
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        this.detalThuocTinhSanPham(index);
    }//GEN-LAST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked

    private void btn_LamMoiThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
        this.showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());
    }//GEN-LAST:event_btn_LamMoiThuocTinhSanPhamActionPerformed

    private void rdoTatCaThuocTinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTatCaThuocTinhSPMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());
    }//GEN-LAST:event_rdoTatCaThuocTinhSPMouseClicked

    private void rdo_DangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DangBanActionPerformed

    private void rdo_DangBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_DangBanMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getSanPhamDangBan());
    }//GEN-LAST:event_rdo_DangBanMouseClicked

    private void rdo_NgungBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_NgungBanMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getSanPhamNgungBan());
    }//GEN-LAST:event_rdo_NgungBanMouseClicked

    private void rdo_TatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_TatCaMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getAll());
    }//GEN-LAST:event_rdo_TatCaMouseClicked

    private void btn_ThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSanPhamActionPerformed
        // Thêm sản phẩm mới vào kho

        SanPham sanpham = getFormData();
        if (sanpham == null) {
            return;
        }
        try {
            sanPhamRepository.add(sanpham);
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
            showTableSanPham(sanPhamRepository.getAllGiamDan());

            List<SanPham> sp = sanPhamRepository.getAllGiamDan();
            DefaultComboBoxModel<String> modelSanPham = new DefaultComboBoxModel<>();
            for (SanPham g : sp) {
                modelSanPham.addElement(g.getTenSanPham()); // Thêm tên thuộc tính vào ComboBox
            }
            cbb_TenSPCT.setModel(modelSanPham); // Cập nhật model của ComboBox

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_ThemSanPhamActionPerformed

    private void btn_ThemThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemThuocTinhSanPhamActionPerformed
        ThuocTinhSanPham ttsp = getFormDataThuocTinhSP();
        if (ttsp == null) {
            return;
        }
        try {
            if (rdoCoAo.isSelected()) {
                thuocTinhSanPhamRepository.insertCoAo(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());

                // Cập nhật ComboBox CoAo
                List<CoAo> coao = coAoRepo.getAll();
                DefaultComboBoxModel<String> modelCoAo = new DefaultComboBoxModel<>();
                for (CoAo g : coao) {
                    modelCoAo.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_CoAoSPCT.setModel(modelCoAo); // Cập nhật model của ComboBox

            } else if (rdoChatLieu.isSelected()) {
                thuocTinhSanPhamRepository.insertChatLieu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());

                // Cập nhật ComboBox ChatLieu
                List<ChatLieu> chatLieu = chatLieuRepo.getAll();
                DefaultComboBoxModel<String> modelChatLieu = new DefaultComboBoxModel<>();
                for (ChatLieu g : chatLieu) {
                    modelChatLieu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_ChatLieuSPCT.setModel(modelChatLieu); // Cập nhật model của ComboBox

            } else if (rdoDoDay.isSelected()) {
                thuocTinhSanPhamRepository.insertDoDay(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDoDay());

                // Cập nhật ComboBox DoDay
                List<DoDay> doDay = doDayRepository.getAll();
                DefaultComboBoxModel<String> modelDoDay = new DefaultComboBoxModel<>();
                for (DoDay g : doDay) {
                    modelDoDay.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_DoDay.setModel(modelDoDay); // Cập nhật model của ComboBox

            } else if (rdoKichThuoc.isSelected()) {
                thuocTinhSanPhamRepository.insertKichThuoc(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());

                // Cập nhật ComboBox KichThuoc
                List<KichThuoc> kichThuoc = kichThuocRepo.getAll();
                DefaultComboBoxModel<String> modelKichThuoc = new DefaultComboBoxModel<>();
                for (KichThuoc g : kichThuoc) {
                    modelKichThuoc.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_KichThuoc.setModel(modelKichThuoc); // Cập nhật model của ComboBox

            } else if (rdoMauSac.isSelected()) {
                thuocTinhSanPhamRepository.insertMauSac(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());

                // Cập nhật ComboBox MauSac
                List<MauSac> mauSac = mauSacRepo.getAll();
                DefaultComboBoxModel<String> modelMauSac = new DefaultComboBoxModel<>();
                for (MauSac g : mauSac) {
                    modelMauSac.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_MauSac.setModel(modelMauSac); // Cập nhật model của ComboBox

            } else if (rdoPhongCach.isSelected()) {
                thuocTinhSanPhamRepository.insertPhongCach(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());

                // Cập nhật ComboBox TinhLinhHoat
                List<TinhLinhHoat> tinhLinhHoat = tinhLinhHoatRepo.getAll();
                DefaultComboBoxModel<String> modelTinhLinhHoat = new DefaultComboBoxModel<>();
                for (TinhLinhHoat g : tinhLinhHoat) {
                    modelTinhLinhHoat.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_TinhLinhHoat.setModel(modelTinhLinhHoat); // Cập nhật model của ComboBox

            } else if (rdoThuongHieu.isSelected()) {
                thuocTinhSanPhamRepository.insertThuongHieu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getThuongHieu());

                // Cập nhật ComboBox ThuongHieu
                List<ThuongHieu> thuongHieu = thuongHieuRepo.getAll();
                DefaultComboBoxModel<String> modelThuongHieu = new DefaultComboBoxModel<>();
                for (ThuongHieu g : thuongHieu) {
                    modelThuongHieu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_ThuongHieuSPCT.setModel(modelThuongHieu); // Cập nhật model của ComboBox

            } else if (rdoXuatXu.isSelected()) {
                thuocTinhSanPhamRepository.insertXuatXu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());

                // Cập nhật ComboBox XuatXu
                List<XuatXu> xuatXu = xuatXuRepo.getAll();
                DefaultComboBoxModel<String> modelXuatXu = new DefaultComboBoxModel<>();
                for (XuatXu g : xuatXu) {
                    modelXuatXu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_XuatXu.setModel(modelXuatXu); // Cập nhật model của ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemThuocTinhSanPhamActionPerformed

    private void buttonBadges3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges3ActionPerformed
        // TODO add your handling code here:
        jpn_Them_SPCT.setVisible(false);
        jpn_Thong_Tin_SPCT.setVisible(true);
    }//GEN-LAST:event_buttonBadges3ActionPerformed

    private void txt_TimKiemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemSPCTActionPerformed

    private void btn_ThemMoiSPCT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMoiSPCT3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemMoiSPCT3MouseClicked

    private void btn_ThemMoiSPCT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMoiSPCT3ActionPerformed
        // TODO add your handling code here:
        jpn_Thong_Tin_SPCT.setVisible(false);
        jpn_Them_SPCT.setVisible(true);

    }//GEN-LAST:event_btn_ThemMoiSPCT3ActionPerformed

    private void btn_SuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaSanPhamActionPerformed
        // TODO add your handling code here:
        int index = tbl_SanPham.getSelectedRow();
        SanPham newSanPham = getFormData();

        if (newSanPham == null) {
            return;
        }
        try {
            SanPham sp = sanPhamRepository.getAllGiamDan().get(index);
            sanPhamRepository.update(getFormData(), sp.getId());
            showTableSanPham(sanPhamRepository.getAllGiamDan());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_SuaSanPhamActionPerformed

    private void btn_SuaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaSanPhamMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_SuaSanPhamMouseClicked

    private void btn_XoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSanPhamActionPerformed
        // TODO add your handling code here:
        int index = tbl_SanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SanPham newSanPham = getFormData();
        if (newSanPham == null) {
            return;
        }

        try {
            ArrayList<SanPham> sanPhamList = sanPhamRepository.getAllGiamDan();
            if (sanPhamList == null || sanPhamList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm nào để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SanPham sp = sanPhamList.get(index);
            if (sp == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với chỉ số đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm '" + sp.getTenSanPham() + "'?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                sanPhamRepository.remove(sp.getId());
                showTableSanPham(sanPhamList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_XoaSanPhamActionPerformed

    private void btn_LamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiSPActionPerformed
        // TODO add your handling code here:
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtMoTaSanPham.setText("");
        showTableSanPham(sanPhamRepository.getAllGiamDan());
    }//GEN-LAST:event_btn_LamMoiSPActionPerformed

    private void rdoThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThuongHieuActionPerformed
        // Xóa tất cả các dòng cũ trong bảng
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) tbl_Thuoc_Tinh_San_Pham.getModel();

        // Xóa tất cả các dòng cũ trong bảng
        model.setRowCount(0);

        // Lấy dữ liệu mới từ repository
        List<ThuocTinhSanPham> thuongHieuList = thuocTinhSanPhamRepository.getThuongHieu();

        // Thêm dữ liệu mới vào bảng
        for (ThuocTinhSanPham ttsp : thuongHieuList) {
            model.addRow(new Object[]{ttsp.getId(), ttsp.getMaThuocTinhSanPham(), ttsp.getTenThuocTinhSanPham()}); // Thay đổi theo cấu trúc dữ liệu của bạn
        }

        // Cập nhật giao diện bảng
        tbl_Thuoc_Tinh_San_Pham.revalidate();
        tbl_Thuoc_Tinh_San_Pham.repaint();
    }//GEN-LAST:event_rdoThuongHieuActionPerformed

    private void btn_XuatExcelSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcelSanPhamActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Sản Phẩm");

            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH SẢN PHẨM");

            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "MÃ SP", "Tên SP", "Mô Tả", "Trạng Thái", "Ngày Tạo"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            SanPhamRepository sanPhamRepository = new SanPhamRepository();
            ArrayList<SanPham> listItem = sanPhamRepository.getAll();

            for (int i = 0; i < listItem.size(); i++) {
                SanPham sp = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(sp.getMaSanPham());
                row.createCell(2).setCellValue(sp.getTenSanPham());
                row.createCell(3).setCellValue(sp.getMoTa());
                row.createCell(4).setCellValue(sp.isTrangThai() ? "Còn hàng" : "Hết hàng");
                row.createCell(5).setCellValue(sp.getNgayTao());
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null); // Thay đổi 'this' thành 'null' ở đây

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                    JOptionPane.showMessageDialog(null, "Xuất file Excel thành công."); // Thay đổi 'this' thành 'null' ở đây
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi khi ghi file.", "Lỗi", JOptionPane.ERROR_MESSAGE); // Thay đổi 'this' thành 'null' ở đây
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra.", "Lỗi", JOptionPane.ERROR_MESSAGE); // Thay đổi 'this' thành 'null' ở đây
        }

    }//GEN-LAST:event_btn_XuatExcelSanPhamActionPerformed

    private void btn_ThemNhanhMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhMauSacActionPerformed
        // TODO add your handling code here:
        ThemMauSacJFrame v = new ThemMauSacJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<MauSac> mauSac = mauSacRepo.getAll();
                DefaultComboBoxModel<String> modelMauSac = new DefaultComboBoxModel<>();
                for (MauSac g : mauSac) {
                    modelMauSac.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_MauSac.setModel(modelMauSac); // Cập nhật model của ComboBox

            }
        });
    }//GEN-LAST:event_btn_ThemNhanhMauSacActionPerformed

    private void btn_ThemNhanhTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhTenSPActionPerformed
        // TODO add your handling code here:
        ThemTenSanPhamJFrame v = new ThemTenSanPhamJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<SanPham> sanPham = sanPhamRepository.getAll();
                DefaultComboBoxModel<String> modelSanPham = new DefaultComboBoxModel<>();
                for (SanPham g : sanPham) {
                    modelSanPham.addElement(g.getTenSanPham()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_ThuongHieuSPCT.setModel(modelSanPham); // Cập nhật model của ComboBox

//                showComboboxTenSanPham(sanPhamRepository.getAll());
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhTenSPActionPerformed

    private void btn_ThemNhanhThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhThuongHieuActionPerformed
        // TODO add your handling code here:
        ThemThuongHieuJFrame v = new ThemThuongHieuJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<ThuongHieu> thuongHieu = thuongHieuRepo.getAll();
                DefaultComboBoxModel<String> modelThuongHieu = new DefaultComboBoxModel<>();
                for (ThuongHieu g : thuongHieu) {
                    modelThuongHieu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_ThuongHieuSPCT.setModel(modelThuongHieu); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhThuongHieuActionPerformed

    private void btn_ThemNhanhChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhChatLieuActionPerformed
        // TODO add your handling code here:
        ThemChatLieuJFrame v = new ThemChatLieuJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<ChatLieu> chatLieu = chatLieuRepo.getAll();
                DefaultComboBoxModel<String> modelChatLieu = new DefaultComboBoxModel<>();
                for (ChatLieu g : chatLieu) {
                    modelChatLieu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_ChatLieuSPCT.setModel(modelChatLieu); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhChatLieuActionPerformed

    private void btn_ThemNhanhKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhKichThuocActionPerformed
        // TODO add your handling code here:
        ThemKichThuocJFrame v = new ThemKichThuocJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<KichThuoc> kichThuoc = kichThuocRepo.getAll();
                DefaultComboBoxModel<String> modelKichThuoc = new DefaultComboBoxModel<>();
                for (KichThuoc g : kichThuoc) {
                    modelKichThuoc.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_KichThuoc.setModel(modelKichThuoc); // Cập nhật model của ComboBox

            }
        });
    }//GEN-LAST:event_btn_ThemNhanhKichThuocActionPerformed

    private void btn_ThemNhanhCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhCoAoActionPerformed
        // TODO add your handling code here:
        ThemCoAoJFrame v = new ThemCoAoJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<CoAo> coao = coAoRepo.getAll();
                DefaultComboBoxModel<String> modelCoAo = new DefaultComboBoxModel<>();
                for (CoAo g : coao) {
                    modelCoAo.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_CoAoSPCT.setModel(modelCoAo); // Cập nhật model của ComboBox
            }
        });

    }//GEN-LAST:event_btn_ThemNhanhCoAoActionPerformed

    private void btn_ThemNhanhDoDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhDoDayActionPerformed
        // TODO add your handling code here:
        ThemDoDayJFrame v = new ThemDoDayJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<DoDay> doDay = doDayRepository.getAll();
                DefaultComboBoxModel<String> modelDoDay = new DefaultComboBoxModel<>();
                for (DoDay g : doDay) {
                    modelDoDay.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_XuatXu.setModel(modelDoDay); // Cập nhật model của ComboBox
            }
        });

    }//GEN-LAST:event_btn_ThemNhanhDoDayActionPerformed

    private void btn_ThemNhanhXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhXuatXuActionPerformed
        // TODO add your handling code here:
        ThemXuatXuJFrame v = new ThemXuatXuJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<XuatXu> xuatXu = xuatXuRepo.getAll();
                DefaultComboBoxModel<String> modelXuatXu = new DefaultComboBoxModel<>();
                for (XuatXu g : xuatXu) {
                    modelXuatXu.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_XuatXu.setModel(modelXuatXu); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhXuatXuActionPerformed

    private void btn_ThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSPCTActionPerformed
        // TODO add your handling code here:

        SanPhamChiTietRespone spctrp = getFormDataSanPhamCT();
        if (spctrp == null) {
            return;
        }
        try {
            SanPhamChiTiet spct = convertResponeToEntity(spctrp);
            sanPhamChiTietRepository.add(spct);
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
            showTableSanPhamChiTiet(sanPhamChiTietRepository.getAllGiamDan());
            showTableSanPham(sanPhamRepository.getAllGiamDan());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemSPCTActionPerformed

    private void cbb_TenSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TenSPCTActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbb_TenSPCTActionPerformed

    private void btn_SuaThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaThuocTinhSanPhamActionPerformed
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa");
            return;
        }

        try {
            String message = "Sửa Thành Công";
            ArrayList<ThuocTinhSanPham> updatedList = null;

            if (rdoChatLieu.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getChatLieu().get(index);
                thuocTinhSanPhamRepository.updateChatLieu(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getChatLieu();
                // Cập nhật ComboBox ChatLieu
                List<ChatLieu> chatLieu = chatLieuRepo.getAll();
                DefaultComboBoxModel<String> modelChatLieu = new DefaultComboBoxModel<>();
                for (ChatLieu g : chatLieu) {
                    modelChatLieu.addElement(g.getTen());
                }
                cbb_ChatLieuSPCT.setModel(modelChatLieu);

            } else if (rdoThuongHieu.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getThuongHieu().get(index);
                thuocTinhSanPhamRepository.updateThuongHieu(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getThuongHieu();
                // Cập nhật ComboBox ThuongHieu
                List<ThuongHieu> thuongHieu = thuongHieuRepo.getAll();
                DefaultComboBoxModel<String> modelThuongHieu = new DefaultComboBoxModel<>();
                for (ThuongHieu g : thuongHieu) {
                    modelThuongHieu.addElement(g.getTen());
                }
                cbb_ThuongHieuSPCT.setModel(modelThuongHieu);

            } else if (rdoKichThuoc.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getKichThuoc().get(index);
                thuocTinhSanPhamRepository.updateKichThuoc(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getKichThuoc();
                // Cập nhật ComboBox KichThuoc
                List<KichThuoc> kichThuoc = kichThuocRepo.getAll();
                DefaultComboBoxModel<String> modelKichThuoc = new DefaultComboBoxModel<>();
                for (KichThuoc g : kichThuoc) {
                    modelKichThuoc.addElement(g.getTen());
                }
                cbb_KichThuoc.setModel(modelKichThuoc);

            } else if (rdoCoAo.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getCoAO().get(index);
                thuocTinhSanPhamRepository.updateCoAo(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getCoAO();
                // Cập nhật ComboBox CoAo
                List<CoAo> coao = coAoRepo.getAll();
                DefaultComboBoxModel<String> modelCoAo = new DefaultComboBoxModel<>();
                for (CoAo g : coao) {
                    modelCoAo.addElement(g.getTen());
                }
                cbb_CoAoSPCT.setModel(modelCoAo);

            } else if (rdoMauSac.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getMauSac().get(index);
                thuocTinhSanPhamRepository.updateMauSac(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getMauSac();
                // Cập nhật ComboBox MauSac
                List<MauSac> mauSac = mauSacRepo.getAll();
                DefaultComboBoxModel<String> modelMauSac = new DefaultComboBoxModel<>();
                for (MauSac g : mauSac) {
                    modelMauSac.addElement(g.getTen());
                }
                cbb_MauSac.setModel(modelMauSac);

            } else if (rdoDoDay.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getDoDay().get(index);
                thuocTinhSanPhamRepository.updateDoDay(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getDoDay();
                // Cập nhật ComboBox DoDay
                List<DoDay> doDay = doDayRepository.getAll();
                DefaultComboBoxModel<String> modelDoDay = new DefaultComboBoxModel<>();
                for (DoDay g : doDay) {
                    modelDoDay.addElement(g.getTen());
                }
                cbb_XuatXu.setModel(modelDoDay);

            } else if (rdoPhongCach.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getTinhLinhHoat().get(index);
                thuocTinhSanPhamRepository.updatePhongCach(getFormDataThuocTinhSP(), ttsp.getMaThuocTinhSanPham());
                updatedList = thuocTinhSanPhamRepository.getTinhLinhHoat();
                // Cập nhật ComboBox TinhLinhHoat
                List<TinhLinhHoat> tinhLinhHoat = tinhLinhHoatRepo.getAll();
                DefaultComboBoxModel<String> modelTinhLinhHoat = new DefaultComboBoxModel<>();
                for (TinhLinhHoat g : tinhLinhHoat) {
                    modelTinhLinhHoat.addElement(g.getTen());
                }
                cbb_TinhLinhHoat.setModel(modelTinhLinhHoat);

            } else if (rdoXuatXu.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getXuatXu().get(index);
                thuocTinhSanPhamRepository.updateXuatXu(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getXuatXu();
                // Cập nhật ComboBox XuatXu
                List<XuatXu> xuatXu = xuatXuRepo.getAll();
                DefaultComboBoxModel<String> modelXuatXu = new DefaultComboBoxModel<>();
                for (XuatXu g : xuatXu) {
                    modelXuatXu.addElement(g.getTen());
                }
                cbb_DoDay.setModel(modelXuatXu);

            } else {
                message = "Vui lòng chọn thuộc tính để sửa!";
                JOptionPane.showMessageDialog(null, message);
                return;
            }

            if (updatedList != null) {
                showTableThuocTinhSanPham(updatedList);
            }

            JOptionPane.showMessageDialog(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_SuaThuocTinhSanPhamActionPerformed

    private void tbl_San_Pham_Chi_TietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_San_Pham_Chi_TietMouseClicked
        // TODO add your handling code here:
       int selectedRowIndex = tbl_San_Pham_Chi_Tiet.getSelectedRow();
    if (selectedRowIndex != -1) {
        // Giả sử cột thứ hai trong bảng là mã sản phẩm chi tiết
         maSanPhamChiTiet = (String) tbl_San_Pham_Chi_Tiet.getValueAt(selectedRowIndex, 1);
        System.out.println("ma: "+ maSanPhamChiTiet);

        if (maSanPhamChiTiet != null && !maSanPhamChiTiet.isEmpty()) {
//            SanPhamChiTietRespone sp = sanPhamChiTietRepository.getSanPhamChiTietByMaSPCT(maSanPhamChiTiet);
//            if (sp != null) {
                // Tạo và hiển thị ThongTinSPJFrame với thông tin sản phẩm
                ThongTinSPJFrame detailsFrame = new ThongTinSPJFrame();
                detailsFrame.setVisible(true);
//            } else {
//                // Hiển thị thông báo lỗi và không mở cửa sổ chi tiết
//                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
        } else {
            // Hiển thị thông báo lỗi nếu mã sản phẩm không hợp lệ
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Hiển thị thông báo lỗi nếu không có hàng nào được chọn
        JOptionPane.showMessageDialog(this, "Không có hàng nào được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_tbl_San_Pham_Chi_TietMouseClicked

    private void btn_XoaThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaThuocTinhSanPhamActionPerformed
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
            return;
        }

        try {
            ThuocTinhSanPham ttsp = null;
            String message = "Xóa Thành Công";
            List<ThuocTinhSanPham> danhSachThuocTinh = null;

            if (rdoChatLieu.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getChatLieu();
            } else if (rdoThuongHieu.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getThuongHieu();
            } else if (rdoKichThuoc.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getKichThuoc();
            } else if (rdoCoAo.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getCoAO();
            } else if (rdoMauSac.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getMauSac();
            } else if (rdoDoDay.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getDoDay();
            } else if (rdoPhongCach.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getTinhLinhHoat();
            } else if (rdoXuatXu.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getXuatXu();
            } else {
                message = "Vui lòng chọn thuộc tính để xóa!";
                JOptionPane.showMessageDialog(null, message);
                return;
            }

            if (danhSachThuocTinh != null && index >= 0 && index < danhSachThuocTinh.size()) {
                ttsp = danhSachThuocTinh.get(index);

                if (rdoChatLieu.isSelected()) {
                    thuocTinhSanPhamRepository.removeChatLieu(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());
                    // Cập nhật ComboBox ChatLieu
                    List<ChatLieu> chatLieu = chatLieuRepo.getAll();
                    DefaultComboBoxModel<String> modelChatLieu = new DefaultComboBoxModel<>();
                    for (ChatLieu g : chatLieu) {
                        modelChatLieu.addElement(g.getTen());
                    }
                    cbb_ChatLieuSPCT.setModel(modelChatLieu);

                } else if (rdoThuongHieu.isSelected()) {
                    thuocTinhSanPhamRepository.removeThuongHieu(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getThuongHieu());
                    // Cập nhật ComboBox ThuongHieu
                    List<ThuongHieu> thuongHieu = thuongHieuRepo.getAll();
                    DefaultComboBoxModel<String> modelThuongHieu = new DefaultComboBoxModel<>();
                    for (ThuongHieu g : thuongHieu) {
                        modelThuongHieu.addElement(g.getTen());
                    }
                    cbb_ThuongHieuSPCT.setModel(modelThuongHieu);

                } else if (rdoKichThuoc.isSelected()) {
                    thuocTinhSanPhamRepository.removeKichThuoc(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());
                    // Cập nhật ComboBox KichThuoc
                    List<KichThuoc> kichThuoc = kichThuocRepo.getAll();
                    DefaultComboBoxModel<String> modelKichThuoc = new DefaultComboBoxModel<>();
                    for (KichThuoc g : kichThuoc) {
                        modelKichThuoc.addElement(g.getTen());
                    }
                    cbb_KichThuoc.setModel(modelKichThuoc);

                } else if (rdoCoAo.isSelected()) {
                    thuocTinhSanPhamRepository.removeCoAo(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());
                    // Cập nhật ComboBox CoAo
                    List<CoAo> coao = coAoRepo.getAll();
                    DefaultComboBoxModel<String> modelCoAo = new DefaultComboBoxModel<>();
                    for (CoAo g : coao) {
                        modelCoAo.addElement(g.getTen());
                    }
                    cbb_CoAoSPCT.setModel(modelCoAo);

                } else if (rdoMauSac.isSelected()) {
                    thuocTinhSanPhamRepository.removeMauSac(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());
                    // Cập nhật ComboBox MauSac
                    List<MauSac> mauSac = mauSacRepo.getAll();
                    DefaultComboBoxModel<String> modelMauSac = new DefaultComboBoxModel<>();
                    for (MauSac g : mauSac) {
                        modelMauSac.addElement(g.getTen());
                    }
                    cbb_MauSac.setModel(modelMauSac);

                } else if (rdoDoDay.isSelected()) {
                    thuocTinhSanPhamRepository.removeDoDay(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDoDay());
                    // Cập nhật ComboBox DoDay
                    List<DoDay> doDay = doDayRepository.getAll();
                    DefaultComboBoxModel<String> modelDoDay = new DefaultComboBoxModel<>();
                    for (DoDay g : doDay) {
                        modelDoDay.addElement(g.getTen());
                    }
                    cbb_XuatXu.setModel(modelDoDay);

                } else if (rdoPhongCach.isSelected()) {
                    thuocTinhSanPhamRepository.removePhongCach(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());
                    // Cập nhật ComboBox TinhLinhHoat
                    List<TinhLinhHoat> tinhLinhHoat = tinhLinhHoatRepo.getAll();
                    DefaultComboBoxModel<String> modelTinhLinhHoat = new DefaultComboBoxModel<>();
                    for (TinhLinhHoat g : tinhLinhHoat) {
                        modelTinhLinhHoat.addElement(g.getTen());
                    }
                    cbb_TinhLinhHoat.setModel(modelTinhLinhHoat);

                } else if (rdoXuatXu.isSelected()) {
                    thuocTinhSanPhamRepository.removeXuatXu(ttsp.getId());
                    showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
                    // Cập nhật ComboBox XuatXu
                    List<XuatXu> xuatXu = xuatXuRepo.getAll();
                    DefaultComboBoxModel<String> modelXuatXu = new DefaultComboBoxModel<>();
                    for (XuatXu g : xuatXu) {
                        modelXuatXu.addElement(g.getTen());
                    }
                    cbb_DoDay.setModel(modelXuatXu);
                }
                JOptionPane.showMessageDialog(null, message);
            } else {
                JOptionPane.showMessageDialog(null, "Chỉ số không hợp lệ hoặc danh sách trống.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xóa thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_XoaThuocTinhSanPhamActionPerformed

    private void btn_TimSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimSanPhamActionPerformed
        // TODO add your handling code here:
        String maSP = txt_SearchSP.getText().trim();
        ArrayList<SanPham> searchResult = sanPhamRepository.searchh(maSP);
        showTableSanPham(searchResult);
    }//GEN-LAST:event_btn_TimSanPhamActionPerformed

    private void btn_TimSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimSanPhamChiTietActionPerformed
        // TODO add your handling code here:
        String maSPCT = txt_TimKiemSPCT.getText().trim();
        ArrayList<SanPhamChiTietRespone> searchResult = sanPhamChiTietRepository.searchh(maSPCT);
        showTableSanPhamChiTiet(searchResult);
    }//GEN-LAST:event_btn_TimSanPhamChiTietActionPerformed

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());
    }//GEN-LAST:event_rdoKichThuocActionPerformed

    private void rdoCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCoAoActionPerformed
        // TODO add your handling code here:

        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());
    }//GEN-LAST:event_rdoCoAoActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoDoDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDoDayActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDoDay());
    }//GEN-LAST:event_rdoDoDayActionPerformed

    private void rdoPhongCachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPhongCachActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());
    }//GEN-LAST:event_rdoPhongCachActionPerformed

    private void rdoXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoXuatXuActionPerformed
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
    }//GEN-LAST:event_rdoXuatXuActionPerformed

    private void btn_ThemNhanhTinhLinhHoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhTinhLinhHoatActionPerformed
        // TODO add your handling code here:
        ThemTinhLinhHoatJFrame v = new ThemTinhLinhHoatJFrame();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                List<TinhLinhHoat> tinhLinhHoat = tinhLinhHoatRepo.getAll();
                DefaultComboBoxModel<String> modelTinhLinhHoat = new DefaultComboBoxModel<>();
                for (TinhLinhHoat g : tinhLinhHoat) {
                    modelTinhLinhHoat.addElement(g.getTen()); // Thêm tên thuộc tính vào ComboBox
                }
                cbb_TinhLinhHoat.setModel(modelTinhLinhHoat); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhTinhLinhHoatActionPerformed

    private void tbl_Thuoc_Tinh_San_PhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Thuoc_Tinh_San_PhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_Thuoc_Tinh_San_PhamMouseEntered

    private void btn_XuatExcelSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcelSPCTActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Sản Phẩm Chi Tiết");

            // Tạo phong cách cho tiêu đề
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.ALT_BARS.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.DASHED.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Thêm tiêu đề
            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH SẢN PHẨM CHI TIẾT");
            cell.setCellStyle(headerStyle); // Áp dụng phong cách tiêu đề

            // Thêm hàng tiêu đề
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "MÃ SPCT", "Tên SP", "THƯƠNG HIỆU", "XUẤT XỨ", "MÀU SẮC", "KÍCH THƯỚC", "CHẤT LIỆU", "CỔ ÁO", "ĐỘ DÀY", "PHONG CÁCH", "GIÁ BÁN", "SỐ LƯỢNG", "TRẠNG THÁI"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle); // Áp dụng phong cách tiêu đề
            }

            // Lấy dữ liệu từ repository
            SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();
            ArrayList<SanPhamChiTietRespone> listItem = sanPhamChiTietRepository.getAll();

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < listItem.size(); i++) {
                SanPhamChiTietRespone spct = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(spct.getMaSPCT());
                row.createCell(2).setCellValue(spct.getTenSP());
                row.createCell(3).setCellValue(spct.getThuongHieu());
                row.createCell(4).setCellValue(spct.getXuatXu());
                row.createCell(5).setCellValue(spct.getMauSac());
                row.createCell(6).setCellValue(spct.getKichThuoc());
                row.createCell(7).setCellValue(spct.getChatLieu());
                row.createCell(8).setCellValue(spct.getCoAo());
                row.createCell(9).setCellValue(spct.getDoDay());
                row.createCell(10).setCellValue(spct.getPhongCach());
                row.createCell(11).setCellValue(spct.getGiaBan());
                row.createCell(12).setCellValue(spct.getSoLuong());
                row.createCell(13).setCellValue(spct.isTrangThai() ? "Còn hàng" : "Hết hàng");
            }

            // Lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi khi ghi file.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_XuatExcelSPCTActionPerformed

    private void cbb_LocChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_LocChatLieuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbb_LocChatLieuActionPerformed

    private void cbb_LocThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_LocThuongHieuActionPerformed
        // TODO add your handling code here:
//
//            SanPhamChiTiet spct = cbb_LocThuongHieu.getSelectedItem();
//            ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(spct);
//            showTableSanPhamChiTiet(list);
    }//GEN-LAST:event_cbb_LocThuongHieuActionPerformed

    private void cbb_LocTinhLinhHoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_LocTinhLinhHoatActionPerformed
        // TODO add your handling code here:

//           String i = cbb_LocTinhLinhHoat.getSelectedItem().toString();
//           ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoTenTinhLinhHoat(i);
//           showTableSanPhamChiTiet(list);
    }//GEN-LAST:event_cbb_LocTinhLinhHoatActionPerformed

    private void cbb_LocXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_LocXuatXuActionPerformed
        // TODO add your handling code here:

//            String i = cbb_LocXuatXu.getSelectedItem().toString();
//            ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoTenXuatXu(i);
//            showTableSanPhamChiTiet(list);
    }//GEN-LAST:event_cbb_LocXuatXuActionPerformed

    private void cbb_LocTheoGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_LocTheoGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_LocTheoGiaActionPerformed

    private void cbb_LocChatLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocChatLieuItemStateChanged
//         TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {

            ChatLieu cl = (ChatLieu) cbb_LocChatLieu.getSelectedItem();
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setChatLieuID(cl.getId());
            ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(spct);
            showTableSanPhamChiTiet(list);
        }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            locSanPhamChiTiet();
        }
    }//GEN-LAST:event_cbb_LocChatLieuItemStateChanged

    private void cbb_LocThuongHieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocThuongHieuItemStateChanged
//        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
//         Integer id = (Integer) cbb_LocThuongHieu.getSelectedItem();
//         ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(null, null, id, null);
//         showTableSanPhamChiTiet(list);
//     } 
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            locSanPhamChiTiet();

        }

    }//GEN-LAST:event_cbb_LocThuongHieuItemStateChanged

    private void cbb_LocTinhLinhHoatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocTinhLinhHoatItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
//         Integer id = (Integer) cbb_LocTinhLinhHoat.getSelectedItem();
//         ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(null, null, null, id);
//         showTableSanPhamChiTiet(list);
//     }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            locSanPhamChiTiet();
        }


    }//GEN-LAST:event_cbb_LocTinhLinhHoatItemStateChanged

    private void cbb_LocXuatXuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocXuatXuItemStateChanged
        // TODO add your handling code here:
//     if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
//         Integer id = (Integer) cbb_LocXuatXu.getSelectedItem();
//         ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKien(null, id, null, null);
//         showTableSanPhamChiTiet(list);
//     }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            locSanPhamChiTiet();
        }

    }//GEN-LAST:event_cbb_LocXuatXuItemStateChanged

    private void cbb_LocTheoGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocTheoGiaItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbb_LocTheoGiaItemStateChanged

    private void btn_LamMoiSPCT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiSPCT3ActionPerformed
        // TODO add your handling code here:
        showTableSanPhamChiTiet(sanPhamChiTietRepository.getAllGiamDan());
    }//GEN-LAST:event_btn_LamMoiSPCT3ActionPerformed

    private void tbl_San_Pham_Chi_TietMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_San_Pham_Chi_TietMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_San_Pham_Chi_TietMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgSanPham;
    private javax.swing.ButtonGroup btgThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_LamMoiSP;
    private com.Product.swing.ButtonBadges btn_LamMoiSPCT3;
    private com.Product.swing.ButtonBadges btn_LamMoiThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_Quet_QR3;
    private com.Product.swing.ButtonBadges btn_SuaSanPham;
    private com.Product.swing.ButtonBadges btn_SuaThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_ThemMoiSPCT3;
    private com.Product.swing.ButtonBadges btn_ThemNhanhChatLieu;
    private com.Product.swing.ButtonBadges btn_ThemNhanhCoAo;
    private com.Product.swing.ButtonBadges btn_ThemNhanhDoDay;
    private com.Product.swing.ButtonBadges btn_ThemNhanhKichThuoc;
    private com.Product.swing.ButtonBadges btn_ThemNhanhMauSac;
    private com.Product.swing.ButtonBadges btn_ThemNhanhTenSP;
    private com.Product.swing.ButtonBadges btn_ThemNhanhThuongHieu;
    private com.Product.swing.ButtonBadges btn_ThemNhanhTinhLinhHoat;
    private com.Product.swing.ButtonBadges btn_ThemNhanhXuatXu;
    private com.Product.swing.ButtonBadges btn_ThemSPCT;
    private com.Product.swing.ButtonBadges btn_ThemSPCT1;
    private com.Product.swing.ButtonBadges btn_ThemSanPham;
    private com.Product.swing.ButtonBadges btn_ThemThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_TimSanPham;
    private com.Product.swing.ButtonBadges btn_TimSanPhamChiTiet;
    private com.Product.swing.ButtonBadges btn_XoaSanPham;
    private com.Product.swing.ButtonBadges btn_XoaThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_XuatExcelSPCT;
    private com.Product.swing.ButtonBadges btn_XuatExcelSanPham;
    private com.Product.swing.ButtonBadges buttonBadges3;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_ChatLieuSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_CoAoSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_DoDay;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_KichThuoc;
    private com.Product.GUI.Combobox cbb_LocChatLieu;
    private com.Product.GUI.Combobox cbb_LocTheoGia;
    private com.Product.GUI.Combobox cbb_LocThuongHieu;
    private com.Product.GUI.Combobox cbb_LocTinhLinhHoat;
    private com.Product.GUI.Combobox cbb_LocXuatXu;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_MauSac;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_TenSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_ThuongHieuSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_TinhLinhHoat;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_XuatXu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jpn_SPCT;
    private javax.swing.JPanel jpn_SanPham;
    private javax.swing.JPanel jpn_Them_SPCT;
    private javax.swing.JPanel jpn_Thong_Tin_SPCT;
    private javax.swing.JLabel lblQRCode;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoCoAo;
    private javax.swing.JRadioButton rdoDoDay;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoPhongCach;
    private javax.swing.JRadioButton rdoTatCaThuocTinhSP;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JRadioButton rdoXuatXu;
    private javax.swing.JRadioButton rdo_DangBan;
    private javax.swing.JRadioButton rdo_NgungBan;
    private javax.swing.JRadioButton rdo_TatCa;
    private com.Product.GUI.tabbed.TabbedPaneCustomm tabbedPaneCustomm1;
    private com.Product.GUI.Table tbl_SanPham;
    private com.Product.GUI.Table tbl_San_Pham_Chi_Tiet;
    private com.Product.GUI.Table tbl_Thuoc_Tinh_San_Pham;
    private com.Product.GUI.textfield.TextField txtMaSanPham;
    private com.Product.GUI.textfield.TextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTaSanPham;
    private com.Product.GUI.textfield.TextField txtTenSanPham;
    private com.Product.GUI.textfield.TextField txtTenThuocTinh;
    private com.Product.GUI.textfield.TextField txt_GiaBanSPCT;
    private com.Product.GUI.textfield.TextField txt_SearchSP;
    private com.Product.GUI.textfield.TextField txt_SoLuongSPCT;
    private com.Product.GUI.textfield.TextField txt_TimKiemSPCT;
    // End of variables declaration//GEN-END:variables
}

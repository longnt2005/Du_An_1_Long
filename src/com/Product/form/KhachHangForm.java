package com.Product.form;

import main.entity.KhachHang;
import main.entity.LichSuGiaoDich;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import main.repository.LichsugiaodichRepository;
import main.repository.ThongTinKhachHangRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import javax.swing.*;
import org.apache.poi.ss.util.CellRangeAddress;


public class KhachHangForm extends javax.swing.JPanel {

    private DefaultTableModel dtm, dtm1;
    private ThongTinKhachHangRepository repottkh;
    private LichsugiaodichRepository repolichsugiaodich;
    private DefaultComboBoxModel dcbmGioiTinh = new DefaultComboBoxModel();

    public KhachHangForm() {
        initComponents();
        dtm = (DefaultTableModel) tbkh.getModel();
        dtm1 = (DefaultTableModel) tblsgd.getModel();
        repottkh = new ThongTinKhachHangRepository();
        repolichsugiaodich = new LichsugiaodichRepository();
        setOpaque(false);
        showDataTTKH(repottkh.getAll());
        showDataLSGD(repolichsugiaodich.getAll());
//        showcbGioiTinh();
    }

    private void showDataTTKH(ArrayList<KhachHang> list_kh) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list_kh.forEach(kh -> dtm.addRow(new Object[]{
            index.getAndIncrement(), kh.getMaKhachHang(), kh.getHoTen(),
            kh.getSoDienThoai(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getNgaySinh(),
            kh.getDiaChi(), kh.getEmail(), kh.getNgayTao()
        }
        ));
    }

    private void showDataLSGD(ArrayList<LichSuGiaoDich> list_ls) {
        dtm1.setRowCount(0);
        AtomicInteger index1 = new AtomicInteger(1);
        list_ls.forEach(ls -> dtm1.addRow(new Object[]{
            index1.getAndIncrement(), ls.getMaNhanVien(), ls.getMaKhachHang(), ls.getMaHD(), ls.getHoTen(),
            ls.getSoDienThoai(), ls.getEmail(), ls.getDiaChi(), ls.getTongTien(), ls.getNgayTao(),
            ls.isTrangThai()
        }
        ));
    }

    private KhachHang getFormDataKhachHang() {
////        String ngaySinhStr = txtNgaySinh.getText();
//
////        Date ngaySinh = null;
////        try {
////            if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
////                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
////                ngaySinh = formatter.parse(ngaySinhStr);
////            }
////        } catch (ParseException e) {
////            e.printStackTrace(System.out);
////        }

        // Convert gender selection to Boolean
        Boolean gioiTinh = null;
        String genderStr = (String) cbGioiTinh.getSelectedItem();
        if (genderStr != null) {
            gioiTinh = genderStr.equalsIgnoreCase("Nam");
        }

        // Build KhachHang object using Lombok's builder
        KhachHang kh = KhachHang.builder()
                .hoTen(txtHoTen.getText())
                .diaChi(txtDiaChi.getText())
                .soDienThoai(txtSDT.getText())
                .email(txtEmail.getText())
                .ngaySinh(txtNgaySinh.getText())
                .gioiTinh(gioiTinh)
                .build();

        return kh;
    }

    private void detailKhachHang(int index) {
        KhachHang kh = repottkh.getAll().get(index);
        txtMaKH.setText(kh.getMaKhachHang());
        txtHoTen.setText(kh.getHoTen());
        txtDiaChi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSoDienThoai());
        txtEmail.setText(kh.getEmail());
        txtNgaySinh.setText(kh.getNgaySinh());
        cbGioiTinh.setSelectedItem(kh.isGioiTinh() ? "Nam" : "Nữ");
    }
//    private void showcbGioiTinh(){
//        cbGioiTinh1.removeAllItems();
//        repottkh.getAll().forEach(s -> dcbmGioiTinh.addElement(s.isGioiTinh()?"Nam":"Nữ"));
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgNamNu = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        tabbedPaneCustomm28 = new com.Product.GUI.tabbed.TabbedPaneCustomm();
        ThongTinKhachHang = new javax.swing.JPanel();
        lsuGiaoDich = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTimKiemLSGD = new com.Product.GUI.textfield.TextField();
        btnTimKiemLSGD = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btnXuatExcelLSGD = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsgd = new com.Product.GUI.Table();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ttKhachHang = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cbGioiTinh2 = new com.Product.GUI.Combobox();
        btnSearch1 = new javax.swing.JButton();
        txtTimKiemNC1 = new com.Product.GUI.textfield.TextField();
        btnXuatExcelKH = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        tbkh = new com.Product.GUI.Table();
        jPanel3 = new javax.swing.JPanel();
        txtHoTen = new com.Product.GUI.textfield.TextField();
        txtMaKH = new com.Product.GUI.textfield.TextField();
        txtSDT = new com.Product.GUI.textfield.TextField();
        txtDiaChi = new com.Product.GUI.textfield.TextField();
        txtEmail = new com.Product.GUI.textfield.TextField();
        txtNgaySinh = new com.Product.GUI.textfield.TextField();
        cbGioiTinh = new com.Product.GUI.Combobox();
        jLabel3 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 922));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Khách Hàng");

        tabbedPaneCustomm28.setBackground(new java.awt.Color(255, 204, 0));
        tabbedPaneCustomm28.setForeground(new java.awt.Color(0, 0, 0));
        tabbedPaneCustomm28.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        tabbedPaneCustomm28.setSelectedColor(new java.awt.Color(255, 204, 0));
        tabbedPaneCustomm28.setUnselectedColor(new java.awt.Color(255, 204, 0));

        ThongTinKhachHang.setPreferredSize(new java.awt.Dimension(750, 800));

        lsuGiaoDich.setBackground(new java.awt.Color(255, 255, 255));
        lsuGiaoDich.setPreferredSize(new java.awt.Dimension(750, 800));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm nâng cao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Từ ngày :");

        txtTimKiemLSGD.setBackground(new java.awt.Color(255, 255, 255));
        txtTimKiemLSGD.setLabelText("Mã HD- Email-SDT");
        txtTimKiemLSGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemLSGDActionPerformed(evt);
            }
        });

        btnTimKiemLSGD.setBackground(new java.awt.Color(255, 204, 0));
        btnTimKiemLSGD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemLSGD.setForeground(new java.awt.Color(0, 0, 0));
        btnTimKiemLSGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        btnTimKiemLSGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemLSGDActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Đến ngày :");

        jButton3.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Tìm ngày");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setForeground(new java.awt.Color(0, 0, 0));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setForeground(new java.awt.Color(0, 0, 0));

        btnXuatExcelLSGD.setBackground(new java.awt.Color(255, 204, 0));
        btnXuatExcelLSGD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatExcelLSGD.setForeground(new java.awt.Color(0, 0, 0));
        btnXuatExcelLSGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/xuatfile.png"))); // NOI18N
        btnXuatExcelLSGD.setText("Xuất");
        btnXuatExcelLSGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelLSGDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(txtTimKiemLSGD, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemLSGD, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(152, 152, 152)
                        .addComponent(btnXuatExcelLSGD)
                        .addGap(189, 189, 189))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimKiemLSGD)
                    .addComponent(txtTimKiemLSGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(26, 26, 26)))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelLSGD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblsgd.setBackground(new java.awt.Color(204, 204, 204));
        tblsgd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Mã KH", "Mã HĐ", "Tên KH", "SDT", "Email KH", "Địa Chỉ", "Tổng Tiền", "Ngày Tạo", "TT"
            }
        ));
        jScrollPane3.setViewportView(tblsgd);

        jLabel4.setBackground(new java.awt.Color(255, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Lịch Sử Giao Dịch");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lsuGiaoDichLayout = new javax.swing.GroupLayout(lsuGiaoDich);
        lsuGiaoDich.setLayout(lsuGiaoDichLayout);
        lsuGiaoDichLayout.setHorizontalGroup(
            lsuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lsuGiaoDichLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(166, 166, 166)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lsuGiaoDichLayout.setVerticalGroup(
            lsuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lsuGiaoDichLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(lsuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ttKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        ttKhachHang.setPreferredSize(new java.awt.Dimension(750, 800));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm nâng cao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Tìm theo giới tính :");

        cbGioiTinh2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        cbGioiTinh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGioiTinh2ActionPerformed(evt);
            }
        });

        btnSearch1.setBackground(new java.awt.Color(255, 204, 0));
        btnSearch1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(0, 0, 0));
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        btnSearch1.setText("Tìm");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        txtTimKiemNC1.setBackground(new java.awt.Color(255, 255, 255));
        txtTimKiemNC1.setLabelText("Tìm Kiếm :");

        btnXuatExcelKH.setBackground(new java.awt.Color(255, 0, 0));
        btnXuatExcelKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatExcelKH.setForeground(new java.awt.Color(0, 0, 0));
        btnXuatExcelKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btnXuatExcelKH.setText("Xuất Excel");
        btnXuatExcelKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelKHActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTimKiemNC1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnSearch1)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGioiTinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel18))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemNC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch1)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbGioiTinh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatExcelKH)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tbkh.setBackground(new java.awt.Color(204, 204, 204));
        tbkh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "SDT", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Email", "Ngày Tạo"
            }
        ));
        tbkh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkhMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tbkh);

        javax.swing.GroupLayout ttKhachHangLayout = new javax.swing.GroupLayout(ttKhachHang);
        ttKhachHang.setLayout(ttKhachHangLayout);
        ttKhachHangLayout.setHorizontalGroup(
            ttKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ttKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        ttKhachHangLayout.setVerticalGroup(
            ttKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ttKhachHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ttKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
            .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lsuGiaoDich, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ttKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
            .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lsuGiaoDich, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)))
        );

        tabbedPaneCustomm28.addTab("Thông Tin Khách Hàng", ThongTinKhachHang);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setPreferredSize(new java.awt.Dimension(325, 666));

        txtHoTen.setBackground(new java.awt.Color(255, 255, 255));
        txtHoTen.setLabelText("Họ và Tên");

        txtMaKH.setBackground(new java.awt.Color(255, 255, 255));
        txtMaKH.setEnabled(false);
        txtMaKH.setLabelText("Mã KH");

        txtSDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSDT.setLabelText("SDT");

        txtDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setLabelText("Địa Chỉ");

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setLabelText("Email");

        txtNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setLabelText("Ngày Sinh");

        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        cbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGioiTinhActionPerformed(evt);
            }
        });

        jLabel3.setText("Giới tính :");

        btnThem.setBackground(new java.awt.Color(255, 204, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 0));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 0));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 204, 0));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 0, 0));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.setToolTipText("");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Thiết lập thông tin khách hàng :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustomm28, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneCustomm28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        repottkh.add(getFormDataKhachHang());
        showDataTTKH(repottkh.getAll());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        KhachHang kh = repottkh.getAll().get(tbkh.getSelectedRow());
        repottkh.update(getFormDataKhachHang(), kh.getId());
        showDataTTKH(repottkh.getAll());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGioiTinhActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        int index = tbkh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the selected KhachHang data
        KhachHang newkh = getFormDataKhachHang();
        if (newkh == null) {
            return;
        }

        try {
            // Retrieve the list of KhachHang
            ArrayList<KhachHang> KHList = repottkh.getAll();
            if (KHList == null || KHList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có khách hàng nào để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ensure the selected index is within bounds
            if (index >= KHList.size()) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng trùng với ý của bạn đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Retrieve the KhachHang at the selected index
            KhachHang kh = KHList.get(index);
            if (kh == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng trùng với ý của bạn đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này '" + kh.getMaKhachHang() + "'?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Delete the KhachHang and refresh the table data
                repottkh.delete(kh.getId());
                KHList.remove(index); // Remove the item from the list
                showDataTTKH(KHList); // Update the table display
                JOptionPane.showMessageDialog(null, "Khách hàng đã được xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa Khách Hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHoTen.setText("");
        txtMaKH.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        lsuGiaoDich.setVisible(false);
        ttKhachHang.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lsuGiaoDich.setVisible(true);
        ttKhachHang.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbGioiTinh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGioiTinh2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGioiTinh2ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
        showDataTTKH(repottkh.searchKhachHang(txtTimKiemNC1.getText()));
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnXuatExcelKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelKHActionPerformed
        // TODO add your handling code here:
         try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Khách Hàng");

            // Tạo kiểu cho tiêu đề chính
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            XSSFFont titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // Tạo kiểu cho tiêu đề cột
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo kiểu cho các ô dữ liệu
            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Tiêu đề chính
            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH Khách Hàng");
            cell.setCellStyle(titleStyle);

            // Merge các ô cho tiêu đề chính
            spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 8));

            // Tiêu đề cột
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "MÃ KH", "Tên KH", "SDT", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Email", "Ngày Tạo"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                spreadsheet.setColumnWidth(i, 4000);
            }

            // Lấy dữ liệu khách hàng
            ThongTinKhachHangRepository ttkhrepo = new ThongTinKhachHangRepository();
            ArrayList<KhachHang> listItem = ttkhrepo.getAll();

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < listItem.size(); i++) {
                KhachHang kh = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(kh.getMaKhachHang());
                row.createCell(2).setCellValue(kh.getHoTen());
                row.createCell(3).setCellValue(kh.getSoDienThoai());
                row.createCell(4).setCellValue(kh.isGioiTinh() ? "Nam" : "Nữ");
                row.createCell(5).setCellValue(kh.getNgaySinh());
                row.createCell(6).setCellValue(kh.getDiaChi());
                row.createCell(7).setCellValue(kh.getEmail());
                row.createCell(8).setCellValue(kh.getNgayTao());

                for (int j = 0; j < headers.length; j++) {
                    row.getCell(j).setCellStyle(dataStyle);
                }
            }

            // Chọn vị trí và tên file để lưu
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
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi khi ghi file.");
                }

                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatExcelKHActionPerformed

    private void tbkhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkhMouseClicked
        // TODO add your handling code here:
        detailKhachHang(tbkh.getSelectedRow());
        int i = tbkh.getSelectedRow();
        
        Integer id_kh = (int) tbkh.getValueAt(i, 0);
        showDataLSGD(repolichsugiaodich.getByIDKhachHang(id_kh));
         ttKhachHang.setVisible(false);
          lsuGiaoDich.setVisible(true);
       
    }//GEN-LAST:event_tbkhMouseClicked

    private void btnTimKiemLSGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemLSGDActionPerformed
        showDataLSGD(repolichsugiaodich.searchLICHSUGD(txtTimKiemLSGD.getText()));
    }//GEN-LAST:event_btnTimKiemLSGDActionPerformed

    private void txtTimKiemLSGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemLSGDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemLSGDActionPerformed

    private void btnXuatExcelLSGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelLSGDActionPerformed
         try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("LỊCH SỬ GIAO DỊCH");

            // Tạo kiểu cho tiêu đề chính
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            XSSFFont titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // Tạo kiểu cho tiêu đề cột
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo kiểu cho các ô dữ liệu
            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Tiêu đề chính
            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH LỊCH SỬ GIAO DỊCH");
            cell.setCellStyle(titleStyle);

            // Merge các ô cho tiêu đề chính
            spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 8));

            // Tiêu đề cột
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "MÃ NV", "Mã KH", "Mã HD", "Tên KH", "SDT","Email", "Địa Chỉ", "Tổng Tiền","Ngày Tạo", "Trạng Thái"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                spreadsheet.setColumnWidth(i, 4000);
            }

            // Lấy dữ liệu khách hàng
            LichsugiaodichRepository lsgdrepo = new LichsugiaodichRepository();
            ArrayList<LichSuGiaoDich> listItem = lsgdrepo.getAll();

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < listItem.size(); i++) {
                LichSuGiaoDich ls = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(ls.getMaNhanVien());
                row.createCell(2).setCellValue(ls.getMaKhachHang());
                row.createCell(3).setCellValue(ls.getMaHD());
                row.createCell(4).setCellValue(ls.getHoTen());
                row.createCell(5).setCellValue(ls.getSoDienThoai());
                row.createCell(6).setCellValue(ls.getEmail());
                row.createCell(7).setCellValue(ls.getDiaChi());
                row.createCell(8).setCellValue(ls.getTongTien());
                row.createCell(9).setCellValue(ls.getNgayTao());
                row.createCell(10).setCellValue(ls.isTrangThai()?"Đã thanh toán":"Chưa thanh toán");

                for (int j = 0; j < headers.length; j++) {
                    row.getCell(j).setCellStyle(dataStyle);
                }
            }

            // Chọn vị trí và tên file để lưu
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
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi khi ghi file.");
                }

                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatExcelLSGDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThongTinKhachHang;
    private javax.swing.ButtonGroup btgNamNu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiemLSGD;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatExcelKH;
    private javax.swing.JButton btnXuatExcelLSGD;
    private com.Product.GUI.Combobox cbGioiTinh;
    private com.Product.GUI.Combobox cbGioiTinh2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JPanel lsuGiaoDich;
    private com.Product.GUI.tabbed.TabbedPaneCustomm tabbedPaneCustomm28;
    private com.Product.GUI.Table tbkh;
    private com.Product.GUI.Table tblsgd;
    private javax.swing.JPanel ttKhachHang;
    private com.Product.GUI.textfield.TextField txtDiaChi;
    private com.Product.GUI.textfield.TextField txtEmail;
    private com.Product.GUI.textfield.TextField txtHoTen;
    private com.Product.GUI.textfield.TextField txtMaKH;
    private com.Product.GUI.textfield.TextField txtNgaySinh;
    private com.Product.GUI.textfield.TextField txtSDT;
    private com.Product.GUI.textfield.TextField txtTimKiemLSGD;
    private com.Product.GUI.textfield.TextField txtTimKiemNC1;
    // End of variables declaration//GEN-END:variables
}

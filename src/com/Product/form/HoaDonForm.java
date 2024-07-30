package com.Product.form;

import com.itextpdf.text.BaseColor;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import main.entity.HoaDon;

import main.repository.HoaDonChiTietRepository;
import main.repository.HoaDonRepository;
import main.repository.LichSuHoaDonRepository;
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse;
import main.response.LichSuHoaDonResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class HoaDonForm extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();

    private HoaDonRepository hdRepo;

    private HoaDonChiTietRepository hdctRepo;

    private DefaultTableModel dtmHoaDonChiTiet;

    private DefaultTableModel dtmLichSuHoaDon;

    private LichSuHoaDonRepository lshdRepo;

    private Menu menu;
    

    public HoaDonForm() {
        initComponents();
        setOpaque(false);
        hdRepo = new HoaDonRepository();

        hdctRepo = new HoaDonChiTietRepository();

        dtm = (DefaultTableModel) tb_hd.getModel();

        showDataTable(hdRepo.getAll());
//        String reString = "HD002";
//        showDataTableV2(timKiemHoaDon(reString));

        dtmHoaDonChiTiet = (DefaultTableModel) tb_hdct.getModel();

        lshdRepo = new LichSuHoaDonRepository();

        dtmLichSuHoaDon = (DefaultTableModel) tb_lshd.getModel();
    }
// tao ra mot bien toan cuc de luu tru du lieu qr
    String resultQR;
//     chuyen vao ham tim tim kiem(ham quet qr)

    HoaDonForm(String ketqua) {
        resultQR = ketqua;
        System.out.println("ma hoa don tai jframe hoa don " + resultQR);
    }

    private void showDataTable(ArrayList<HoaDonResponse> lists) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1); // Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        // for..each + lamda 
        lists.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(), s.getMaHoaDon(), s.getNgayTao(), s.getNgayCapNhap(), s.getTongTien(), s.getMaNhanVien(),
            s.getHoTen(), s.getDiaChi(), s.getSDT(), s.getTrangThai() == 0 ? "Chưa thanh toán":"Đã Thanh Toán"  , s.getHinhThucTT() == 0 ?"Tiền Mặt" :"Chuyển Khoản"
        }));
    }

    private void showTableHoaDonChiTiet(ArrayList<HoaDonChiTietReponse> lists) {
        DefaultTableModel model = (DefaultTableModel) tb_hdct.getModel();
        model.setRowCount(0);// quet lai phat
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSPCT(),
            s.getThuongHieu(), s.getXuatXu(),
            s.getMauSac(),
            s.getKichThuoc(), s.getChatLieu(), s.getCoAo(), s.getDoDay(), s.getPhongCach(),
            s.getGiaBan(), s.getSoLuong(),
            s.isTrangThai() ? "Còn hàng" : "Hết hàng"
        }));
    }

    private void showTableLichSuHoaDon(ArrayList<LichSuHoaDonResponse> lists) {
        DefaultTableModel model = (DefaultTableModel) tb_lshd.getModel();
        model.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model.addRow(new Object[]{
            index.getAndIncrement(), s.getMaNV(), s.getNgayCapNhap(), s.getTrangThai() == 0 ? "Chưa thanh toán":"Đã Thanh Toán" 
        }));
    }

    private static PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void addTableHeader(PdfPTable table, Font font) {
        Stream.of("STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell(new Phrase(columnTitle, font));
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_hdct = new com.Product.GUI.Table();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_lshd = new com.Product.GUI.Table();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt_timTheoGiaMax = new com.Product.GUI.textfield.TextField();
        txt_timTheoGia = new com.Product.GUI.textfield.TextField();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbox_hoaDon = new com.Product.GUI.Combobox();
        cb_httt = new com.Product.GUI.Combobox();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch1 = new com.Product.GUI.textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hd = new com.Product.GUI.Table();
        jButton7 = new javax.swing.JButton();
        QR = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_xuat = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Hóa đơn");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204), 3));

        tb_hdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma SPCT", "Thương Hiệu", "Xuất xứ", "Màu Sắc", "Kích Thước", "Chất Liệu", "Cổ áo", "Độ dày", "Phong cách", "Giá bán", "Số Lượng", "Trạng Thái"
            }
        ));
        tb_hdct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcl(evt);
            }
        });
        jScrollPane2.setViewportView(tb_hdct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 3));

        tb_lshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Nguoi Tac Dong", "Ngay Cap Nhap", "Trang Thai"
            }
        ));
        jScrollPane3.setViewportView(tb_lshd);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Hóa đơn chi tiết");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Lịch sử hóa đơn");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Ðon", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        txt_timTheoGiaMax.setLabelText("Nhập giá max");

        txt_timTheoGia.setLabelText("Nhập giá min");

        jButton6.setBackground(new java.awt.Color(255, 255, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/loc.png"))); // NOI18N
        jButton6.setText("Lọc");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Hình thức thanh toán");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Trạng thái hóa đơn");

        cbox_hoaDon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chưa Thanh Toán", "Đã Thanh Toán", "Tat Ca" }));
        cbox_hoaDon.setLineColor(new java.awt.Color(51, 51, 255));
        cbox_hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_hoaDonActionPerformed(evt);
            }
        });

        cb_httt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Mặt", "Chuyển Khoản", "Tat Ca" }));
        cb_httt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_htttActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm theo giá");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm ");

        txtSearch1.setLabelText("Ma Hoa Don - Ma Nhan Vien - SDT - Ðia Chi");
        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        tb_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Ngày Thanh Toán", "Tổng Tiền", "Mã NV", "Tên KH", "Địa Chỉ ", "Số Điện Thoại", "Trạng Thái", "Hình Thức Thanh Toán"
            }
        ));
        tb_hd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hd);

        jButton7.setBackground(new java.awt.Color(255, 255, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        QR.setBackground(new java.awt.Color(255, 255, 153));
        QR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        QR.setText("Quét QR");
        QR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QRActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        jButton5.setText("Làm Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/hoadonmoi.png"))); // NOI18N
        jButton3.setText("In Hóa Đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_xuat.setBackground(new java.awt.Color(255, 255, 153));
        btn_xuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btn_xuat.setText("Xuất Excel");
        btn_xuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QR)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txt_timTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton6)
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(52, 52, 52)
                .addComponent(jButton3)
                .addGap(28, 28, 28)
                .addComponent(btn_xuat)
                .addGap(38, 38, 38))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(QR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(txt_timTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton3)
                            .addComponent(btn_xuat))
                        .addContainerGap())))
        );

        jLabel2.getAccessibleContext().setAccessibleName("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(486, 486, 486))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.getAccessibleContext().setAccessibleName("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        txtSearch1.setText("");
        txt_timTheoGia.setText("");
        txt_timTheoGiaMax.setText("");
        showDataTable(hdRepo.getAll());
    }//GEN-LAST:event_jButton5ActionPerformed


    private void cbox_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_hoaDonActionPerformed
        // TODO add your handling code here:
        try {
            // Get selected trangThai from combobox
            Integer trangThai = cbox_hoaDon.getSelectedIndex();

            // If "All" is selected, set trangThai to null
            if (trangThai == 2) { // Assuming "All" is at index 0
                trangThai = null;
            }

            // Call the search method with trangThai
            showDataTable(hdRepo.trangThaiHoaDon(trangThai));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cbox_hoaDonActionPerformed

    private void mcl(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcl
        // TODO add your handling code here:
    }//GEN-LAST:event_mcl

    private void btn_xuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatActionPerformed
        try {
            // Khởi tạo workbook và sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Hóa Đơn");

            // Cài đặt kiểu chữ tiêu đề
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            XSSFFont titleFont = workbook.createFont();
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setBold(true);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Tạo dòng tiêu đề
            XSSFRow row = spreadsheet.createRow(0);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH HÓA ĐƠN");
            cell.setCellStyle(titleStyle);

            // Cài đặt kiểu chữ cho tiêu đề cột
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Tạo dòng tiêu đề cột
            row = spreadsheet.createRow(1);
            row.setHeight((short) 400);

            String[] headers = {"STT", "MÃ HĐ", "Ngày Tạo", "Ngày Thanh Toán", "Tổng Tiền", "Mã Nhân Viên", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại", "Trạng Thái", "Hình Thức Thanh Toán"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Cài đặt kiểu chữ cho dữ liệu
            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Lấy danh sách hóa đơn từ HoaDonRepository (giả sử là một đối tượng chứa phương thức getAll())
            HoaDonRepository hoaDonRepository = new HoaDonRepository();
            ArrayList<HoaDonResponse> listItem = hoaDonRepository.getAll();

            // Đổ dữ liệu vào bảng Excel
            for (int i = 0; i < listItem.size(); i++) {
                HoaDonResponse hoaDon = listItem.get(i);

                row = spreadsheet.createRow(2 + i);
                row.setHeight((short) 400);

                row.createCell(0).setCellValue(i + 1); // STT
                row.createCell(1).setCellValue(hoaDon.getMaHoaDon()); // Mã HĐ
                row.createCell(2).setCellValue(hoaDon.getNgayTao().toString()); // Ngày Tạo
                row.createCell(3).setCellValue(hoaDon.getNgayCapNhap().toString()); // Ngày Thanh Toán
                row.createCell(4).setCellValue(hoaDon.getTongTien()); // Tổng Tiền
                row.createCell(5).setCellValue(hoaDon.getMaNhanVien()); // Mã Nhân Viên
                row.createCell(6).setCellValue(hoaDon.getHoTen()); // Tên Khách Hàng
                row.createCell(7).setCellValue(hoaDon.getDiaChi()); // Địa Chỉ
                row.createCell(8).setCellValue(hoaDon.getSDT()); // Số Điện Thoại
                row.createCell(9).setCellValue(hoaDon.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa Thanh Toán"); // Trạng Thái
                row.createCell(10).setCellValue(hoaDon.getHinhThucTT() == 0 ? "Tiền Mặt" : "Chuyển Khoản"); // Hình Thức Thanh Toán

                // Cài đặt kiểu chữ cho dữ liệu
                for (int j = 0; j < headers.length; j++) {
                    row.getCell(j).setCellStyle(dataStyle);
                }
            }

            // Tự động căn chỉnh kích thước cột
            for (int i = 0; i < headers.length; i++) {
                spreadsheet.autoSizeColumn(i);
            }

            // Hiển thị hộp thoại để chọn vị trí và tên file để lưu
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Đảm bảo rằng file có đuôi .xlsx
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                // Ghi workbook vào file Excel
                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi khi ghi file.");
                }
            }

            // Đóng workbook để giải phóng tài nguyên
            workbook.close();

            System.out.println("Xuất file Excel thành công.");
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_xuatActionPerformed

    private void QRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QRActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HoaDonResponse hdrp = getHoaDonResponse(menu.maHD);// quet lai di
                ArrayList<HoaDonChiTietReponse> listHdct = hdctRepo.getByIdHoaDon(hdrp.getId());

                System.out.println("hdct: " + listHdct);
                showTableHoaDonChiTiet(listHdct);// quét l?i xem nó nhan ko

                HoaDonResponse hdr = getHoaDonResponse(menu.id);

                // Retrieve and display LichSuHoaDon
                ArrayList<LichSuHoaDonResponse> listLshd = lshdRepo.getByIdLSHoaDon(hdr.getId());
                System.out.println("lshd: " + listLshd);
                showTableLichSuHoaDon(listLshd);
            }
        });
    }//GEN-LAST:event_QRActionPerformed

    private ArrayList<HoaDonResponse> timKiemHoaDon(String resultQR) {
        HoaDonRepository hdRepo = new HoaDonRepository();
        ArrayList<HoaDonResponse> list = new ArrayList<>();

        System.out.println("Chức năng tìm kiếm hóa đơn");

        if (resultQR == null || resultQR.trim().isEmpty()) {
            System.out.println("Không có mã QR được cung cấp");
        } else {
            HoaDonResponse hoaDon = hdRepo.timKiemHoaDonResponsebyQR(resultQR.trim());
            if (hoaDon != null) {
                list.add(hoaDon);
                System.out.println("Hóa đơn được tìm thấy: " + hoaDon.getMaHoaDon());
            } else {
                System.out.println("Không tìm thấy hóa đơn với mã QR: " + resultQR);
            }

            // Hiển thị dữ liệu trên giao diện
            System.out.println(list);
            showDataTableV2(list);
        }

        return list;
    }
    
    private HoaDonResponse getHoaDonResponse(String resultQR) {
        HoaDonRepository hdRepo = new HoaDonRepository();

        System.out.println("Chức năng tìm kiếm hóa đơn");

        // Kiểm tra mã QR không null hoặc rỗng
        if (resultQR == null || resultQR.trim().isEmpty()) {
            System.out.println("Không có mã QR được cung cấp");
            return null; // Trả về null nếu không có mã QR
        } else {
            HoaDonResponse hoaDon = hdRepo.timKiemHoaDonResponsebyQR(resultQR.trim());
            if (hoaDon != null) {
                System.out.println("Hóa đơn được tìm thấy: " + hoaDon.getMaHoaDon());
                return hoaDon;
            } else {
                System.out.println("Không tìm thấy hóa đơn với mã QR: " + resultQR);
                return null; // Trả về null nếu không tìm thấy hóa đơn
            }
        }
    }

    private void showDataTableV2(ArrayList<HoaDonResponse> lists) {
        dtm.setRowCount(0); // Clear the existing rows in the table model
        AtomicInteger index = new AtomicInteger(1); // Initialize index starting from 1

        lists.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaHoaDon(),
            s.getNgayTao(),
            s.getNgayCapNhap(),
            s.getTongTien(),
            s.getMaNhanVien(),
            s.getHoTen(),
            s.getDiaChi(),
            s.getSDT(),
            s.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa thanh toán",
            s.getHinhThucTT() == 0 ? "Tiền Mặt" : "Chuyển Khoản"
        }));

        dtm.fireTableDataChanged(); // Notify the table that the model has been updated
    }

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            // Get trangThai
            Integer trangThai = cbox_hoaDon.getSelectedIndex();
            if (trangThai == 3) { // Assuming "Tất cả" is the first item
                trangThai = null;
            }

            // Get httt
            Integer httt = cb_httt.getSelectedIndex();
            if (httt == 3) { // Assuming "Tất cả" is the first item for httt
                httt = null;
            }

            // Get giaMin
            Double giaMin = null;
            String giaMinText = txt_timTheoGia.getText().trim();
            if (!giaMinText.isEmpty()) {
                giaMin = Double.parseDouble(giaMinText);
            }

            // Get giaMax
            Double giaMax = null;
            String giaMaxText = txt_timTheoGiaMax.getText().trim();
            if (!giaMaxText.isEmpty()) {
                giaMax = Double.parseDouble(giaMaxText);
            }

            // Call the search method with the updated inputs
            showDataTable(hdRepo.search(trangThai, httt, giaMin, giaMax));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho phạm vi giá.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tb_hdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hdMouseClicked
        // TODO add your handling code here:
        int i = tb_hd.getSelectedRow();
        int id_hoaDon = (int) tb_hd.getValueAt(i, 0);
        showTableHoaDonChiTiet(hdctRepo.getByIdHoaDon(id_hoaDon));
        showTableLichSuHoaDon(lshdRepo.getByIdLSHoaDon(id_hoaDon));
    }//GEN-LAST:event_tb_hdMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        showDataTable(hdRepo.searchh(txtSearch1.getText()));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:    
        int rowIndex = tb_hd.getSelectedRow();
        int rowIndexx = tb_hdct.getSelectedRow();

// Check if rows are selected in both tables
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dòng trong bảng hóa đơn để xuất");
            return;
        }
        if (rowIndexx == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dòng trong bảng chi tiết hóa đơn để xuất");
            return;
        }

// Ensure the rowIndex is within bounds
        if (rowIndex < 0 || rowIndex >= tb_hd.getRowCount() || rowIndexx < 0 || rowIndexx >= tb_hdct.getRowCount()) {
            JOptionPane.showMessageDialog(null, "Dòng chọn không hợp lệ.");
            return;
        }

// Get data from the selected row in tb_hd
        String maHoaDon = (String) tb_hd.getValueAt(rowIndex, 1); // Assuming column 1 is MaHoaDon
        String hoTen = (String) tb_hd.getValueAt(rowIndex, 6); // Assuming column 6 is HoTen
        String ngayTao = (String) tb_hd.getValueAt(rowIndex, 2); // Assuming column 2 is NgayTao
        Double tongTien = (Double) tb_hd.getValueAt(rowIndex, 4); // Assuming column 4 is TongTien

// Get data from the selected row in tb_hdct
        String tenSanPham = (String) tb_hdct.getValueAt(rowIndexx, 2); // Assuming column 2 is TenSanPham in tb_hdct
        Integer soLuong = (Integer) tb_hdct.getValueAt(rowIndexx, 11); // Assuming column 11 is SoLuong in tb_hdct
        Double donGia = (Double) tb_hdct.getValueAt(rowIndexx, 10); // Assuming column 10 is DonGia in tb_hdct

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String outputPath = fileToSave.getAbsolutePath();

            // Ensure the file has a .pdf extension
            if (!outputPath.endsWith(".pdf")) {
                outputPath += ".pdf";
            }

            // Generate PDF file
            try {
                // Create a new Document
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                document.open(); // Open the document for writing

                // Load Vietnamese font
                BaseFont baseFont = BaseFont.createFont("C:\\Users\\ADMIN\\Desktop\\Du_An_1_SD19310-master\\Du_An_1_SD19310-master\\lib\\TimesNewRoman\\SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Font boldFont = new Font(baseFont, 12, Font.BOLD);
                Font regularFont = new Font(baseFont, 12, Font.NORMAL);

                // Add the main title
                Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG\nBILL OF SALE", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Add invoice details
                PdfPTable infoTable = new PdfPTable(2);
                infoTable.setWidthPercentage(100);
                infoTable.setSpacingBefore(10f);
                infoTable.setSpacingAfter(10f);
                infoTable.addCell(getCell("Mã hóa đơn:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(maHoaDon, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Khách hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(hoTen, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Ngày mua:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(ngayTao, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Nhân viên bán hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell("Admin", PdfPCell.ALIGN_RIGHT, regularFont)); // Placeholder for staff name
                document.add(infoTable);

                // Add products table
                PdfPTable productTable = new PdfPTable(5);
                productTable.setWidthPercentage(100);
                productTable.setSpacingBefore(20f);

                // Add table headers
                productTable.addCell(new PdfPCell(new Phrase("STT", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Số lượng", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Đơn giá", boldFont)));
                productTable.addCell(new PdfPCell(new Phrase("Thành tiền", boldFont)));

                // Add product details
                productTable.addCell(new PdfPCell(new Phrase("1", regularFont)));
                productTable.addCell(new PdfPCell(new Phrase(tenSanPham, regularFont)));
                productTable.addCell(new PdfPCell(new Phrase(String.valueOf(soLuong), regularFont)));
                productTable.addCell(new PdfPCell(new Phrase(String.format("%.0f đ", donGia), regularFont)));
                productTable.addCell(new PdfPCell(new Phrase(String.format("%.0f đ", soLuong * donGia), regularFont)));

                document.add(productTable);

                // Add total amount
                PdfPTable totalTable = new PdfPTable(2);
                totalTable.setWidthPercentage(100);
                totalTable.setSpacingBefore(10f);
                totalTable.setSpacingAfter(10f);
                totalTable.addCell(getCell("Tổng tiền phải thanh toán:", PdfPCell.ALIGN_LEFT, boldFont));
                totalTable.addCell(getCell(String.format("%.0f đ", tongTien), PdfPCell.ALIGN_RIGHT, regularFont));
                totalTable.addCell(getCell("Trạng thái đơn hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                totalTable.addCell(getCell("Hoàn thành", PdfPCell.ALIGN_RIGHT, regularFont));
                document.add(totalTable);

                // Generate QR code for the invoice
                String qrContent = maHoaDon;
                BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
                com.itextpdf.text.Image qrImage = qrCode.getImage();
                qrImage.setAlignment(Element.ALIGN_RIGHT);
                qrImage.setSpacingBefore(10f);
                document.add(qrImage);

                // Add footer
                Paragraph footer = new Paragraph("Copyright © 2023 ShunShine\nAll rights reserved", boldFont);
                footer.setAlignment(Element.ALIGN_CENTER);
                footer.setSpacingBefore(20f);
                document.add(footer);

                // Close the document
                document.close();
                System.out.println("PDF created at: " + outputPath);
            } catch (DocumentException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cb_htttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_htttActionPerformed
//        try {
//            // Get selected trangThai from combobox
//            Integer httt = cb_httt.getSelectedIndex();
//
//            // Call the search method with trangThai
//            showDataTable(hdRepo.hinhThucThanhToan(httt));
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
//        }       
    }//GEN-LAST:event_cb_htttActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QR;
    private javax.swing.JButton btn_xuat;
    private com.Product.GUI.Combobox cb_httt;
    private com.Product.GUI.Combobox cbox_hoaDon;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.Product.GUI.Table tb_hd;
    private com.Product.GUI.Table tb_hdct;
    private com.Product.GUI.Table tb_lshd;
    private com.Product.GUI.textfield.TextField txtSearch1;
    private com.Product.GUI.textfield.TextField txt_timTheoGia;
    private com.Product.GUI.textfield.TextField txt_timTheoGiaMax;
    // End of variables declaration//GEN-END:variables
}

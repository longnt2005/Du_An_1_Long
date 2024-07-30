package com.Product.form;

import main.repository.repo;
import main.entity.NhanVien;


import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NhanVienForm extends javax.swing.JPanel {
    private repo rp  ;
    private DefaultTableModel mol ; 
    private int i = -1;
    
 

     private void showDataTable(ArrayList<NhanVien> lists) {
        mol.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1); // Khoi 
//        tao 1 gia tri bat dau bang 1 de tu dong tang
        // for..each + lamda
        lists.forEach(s -> mol.addRow(new Object[]{
            index.getAndIncrement(), s.getMaNhanVien(), s.getTenNhanVien(), s.isGioiTinh()?"Nam":"Nữ", s.getSoDienThoai(),
            s.getDiaChi(), s.isTrangThai()?"Đang làm":"Nghỉ làm"
        }));
    }
    
    public NhanVienForm() {
        initComponents();
        setOpaque(false);   
        rp = new repo();
        
        int check = rp.getALL().size();
        
        mol = (DefaultTableModel) table.getModel();
        showDataTable(rp.getALL());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbb_trangthai = new com.Product.GUI.Combobox();
        jLabel11 = new javax.swing.JLabel();
        txt_tennhanvien = new com.Product.GUI.textfield.TextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_manhanvien = new com.Product.GUI.textfield.TextField();
        rdbt_nam = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdbt_nu = new com.Product.GUI.radio_button.RadioButtonCustom();
        txt_sodienthoai = new com.Product.GUI.textfield.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_diachi = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btn_sua = new javax.swing.JButton();
        btn_chuyentrangthai = new javax.swing.JButton();
        btn_lammoi = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbbtimkiem_trangthai = new com.Product.GUI.Combobox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rdbt_nam1 = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdbt_nu1 = new com.Product.GUI.radio_button.RadioButtonCustom();
        btn_timkiem = new javax.swing.JButton();
        txttimkiem_tennhanvien = new com.Product.GUI.textfield.TextField();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.Product.GUI.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Nhân viên");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Mã nhân viên:");

        cbb_trangthai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang làm", "Nghỉ làm" }));
        cbb_trangthai.setEnabled(false);
        cbb_trangthai.setLineColor(new java.awt.Color(51, 51, 255));
        cbb_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_trangthaiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên nhân viên:");

        txt_tennhanvien.setLabelText("Nhập tên nhân viên...");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Giới tính:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Số điện thoại:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Địa chỉ:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Trạng thái:");

        txt_manhanvien.setEnabled(false);
        txt_manhanvien.setLabelText("Nhập mã nhân viên...");
        txt_manhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_manhanvienActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbt_nam);
        rdbt_nam.setText("Nam");

        buttonGroup1.add(rdbt_nu);
        rdbt_nu.setText("Nữ");

        txt_sodienthoai.setLabelText("Nhập số điện thoại...");

        jTextArea_diachi.setColumns(20);
        jTextArea_diachi.setLineWrap(true);
        jTextArea_diachi.setRows(5);
        jTextArea_diachi.setWrapStyleWord(true);
        jTextArea_diachi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea_diachi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(jTextArea_diachi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdbt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdbt_nu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tennhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_manhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_manhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_tennhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbb_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbt_nu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        rdbt_nam.getAccessibleContext().setAccessibleName("");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Danh sách nhân viên");

        btn_sua.setBackground(new java.awt.Color(255, 204, 102));
        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/edit.png"))); // NOI18N
        btn_sua.setText("SỬA");
        btn_sua.setToolTipText("");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_chuyentrangthai.setBackground(new java.awt.Color(255, 204, 102));
        btn_chuyentrangthai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_chuyentrangthai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/delete.png"))); // NOI18N
        btn_chuyentrangthai.setText("CHUYỂN TRẠNG THÁI");
        btn_chuyentrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chuyentrangthaiActionPerformed(evt);
            }
        });

        btn_lammoi.setBackground(new java.awt.Color(255, 204, 102));
        btn_lammoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_lammoi.setText("LÀM MỚI");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(255, 204, 102));
        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_them.setText("THÊM");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm nâng cao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        cbbtimkiem_trangthai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang làm", "Nghỉ làm" }));
        cbbtimkiem_trangthai.setLineColor(new java.awt.Color(51, 51, 255));
        cbbtimkiem_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtimkiem_trangthaiActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Trạng thái:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Giới tính:");

        buttonGroup1.add(rdbt_nam1);
        rdbt_nam1.setText("Nam");

        buttonGroup1.add(rdbt_nu1);
        rdbt_nu1.setText("Nữ");

        btn_timkiem.setBackground(new java.awt.Color(255, 204, 102));
        btn_timkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_timkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        btn_timkiem.setText("TÌM KIẾM");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });
        btn_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btn_timkiemKeyReleased(evt);
            }
        });

        txttimkiem_tennhanvien.setLabelText("Nhập tên nhân viên muốn tìm...");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tên nhân viên:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdbt_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdbt_nu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_timkiem))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttimkiem_tennhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbtimkiem_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txttimkiem_tennhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbtimkiem_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbt_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbt_nu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_timkiem)
                .addGap(17, 17, 17))
        );

        jButton4.setBackground(new java.awt.Color(255, 204, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        jButton4.setText("Xuất Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        jButton1.setText("Quét CCCD");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Địa chỉ", "Trạng thái"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_chuyentrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_chuyentrangthai, btn_lammoi, btn_sua, btn_them, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_sua)
                    .addComponent(btn_chuyentrangthai)
                    .addComponent(btn_lammoi)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_chuyentrangthai, btn_lammoi, btn_sua, btn_them, jButton4});

    }// </editor-fold>//GEN-END:initComponents

    private void cbb_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_trangthaiActionPerformed

    private void btn_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_timkiemKeyReleased
        // TODO add your handling code here:
//        String searchST = txt_timkiem.getText();
//        search(searchST);
    }//GEN-LAST:event_btn_timkiemKeyReleased

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        txt_manhanvien.setText("");
        txt_tennhanvien.setText("");
        txt_sodienthoai.setText("");
        jTextArea_diachi.setText("");
        rdbt_nam.setSelected(false);
        rdbt_nu.setSelected(false);

        


    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void txt_manhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_manhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_manhanvienActionPerformed

    private void cbbtimkiem_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtimkiem_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtimkiem_trangthaiActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        rp.add(getFormData());
        showDataTable(rp.getALL());
    }//GEN-LAST:event_btn_themActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
               i = table.getSelectedRow();
                this.showData(i);
    }//GEN-LAST:event_tableMouseClicked

    private void btn_chuyentrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chuyentrangthaiActionPerformed
        // TODO add your handling code here:
        i = table.getSelectedRow();
        rp.remove(rp.getALL().get(i).getMaNhanVien());
        showDataTable(rp.getALL());
    }//GEN-LAST:event_btn_chuyentrangthaiActionPerformed

    // Edit 
    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        rp.edit(getFormDataEdit());
        showDataTable(rp.getALL());
    }//GEN-LAST:event_btn_suaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Nhân Viên");

            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH NHÂN VIÊN");

            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "Mã nhân viên", "Tên nhân viên","Giới Tính", "Số điện thoại", "Địa Chỉ", "Trạng thái"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            repo repoNV = new repo();
            ArrayList<NhanVien> listItem = repoNV.getALL();

            for (int i = 0; i < listItem.size(); i++) {
                NhanVien nv = listItem.get(i);
                row = spreadsheet.createRow(6 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(nv.getMaNhanVien());
                row.createCell(2).setCellValue(nv.getTenNhanVien());
                row.createCell(4).setCellValue(nv.getSoDienThoai());
                row.createCell(3).setCellValue(nv.isGioiTinh() ? "Nam" : "Nữ");
                row.createCell(5).setCellValue(nv.getDiaChi());
                row.createCell(6).setCellValue(nv.isTrangThai() ? "Đang làm" : "Nghỉ làm"); // Example translation for TrangThai
            }

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
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công."); // Use null for the parent component
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        String trang_thai;
        if(cbbtimkiem_trangthai.getSelectedIndex() == 0)
            trang_thai = "1";
        else
            trang_thai = "0";
        
        String gioi_tinh;
        if(rdbt_nam1.isSelected())
            gioi_tinh = "1";
        else 
            gioi_tinh = "0";
        
        showDataTable(rp.timkiem(txttimkiem_tennhanvien.getText(), gioi_tinh, trang_thai));
    }//GEN-LAST:event_btn_timkiemActionPerformed
   
    private NhanVien getFormData(){
       boolean check = true ? rdbt_nam.isSelected() : rdbt_nu.isSelected();
       
       NhanVien nv = new NhanVien("1", txt_tennhanvien.getText(), check, txt_sodienthoai.getText(), 
       jTextArea_diachi.getText(), "121243214", "2024-07-18 23:35:33.090", "2024-07-18 23:35:33.090");
       
       return nv;
   }
    
    private NhanVien getFormDataEdit(){

       i = table.getSelectedRow();
       NhanVien nv = rp.getALL().get(i);
       nv.setTenNhanVien(txt_tennhanvien.getText());
       nv.setMaNhanVien(txt_manhanvien.getText());
       nv.setSoDienThoai(txt_sodienthoai.getText());
       nv.setDiaChi(jTextArea_diachi.getText());
       if(rdbt_nam.isSelected()){
           nv.setGioiTinh(true);
       }else{
           nv.setGioiTinh(false);
       }
           
       return nv;
   }
    
   void showData(int i){
        txt_manhanvien.setText(table.getValueAt(i, 1).toString());
        txt_tennhanvien.setText(table.getValueAt(i, 2).toString());
        txt_sodienthoai.setText(table.getValueAt(i, 4).toString());
        rdbt_nam.setSelected(table.getValueAt(i, 3).equals("Nam"));
        rdbt_nu.setSelected(table.getValueAt(i, 3).equals("Nữ"));
        cbb_trangthai.setSelectedItem(table.getValueAt(i, 6).toString());
        jTextArea_diachi.setText(table.getValueAt(i, 5).toString());
}

    
    



    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chuyentrangthai;
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.Product.GUI.Combobox cbb_trangthai;
    private com.Product.GUI.Combobox cbbtimkiem_trangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea_diachi;
    private com.Product.GUI.radio_button.RadioButtonCustom rdbt_nam;
    private com.Product.GUI.radio_button.RadioButtonCustom rdbt_nam1;
    private com.Product.GUI.radio_button.RadioButtonCustom rdbt_nu;
    private com.Product.GUI.radio_button.RadioButtonCustom rdbt_nu1;
    private com.Product.GUI.Table table;
    private com.Product.GUI.textfield.TextField txt_manhanvien;
    private com.Product.GUI.textfield.TextField txt_sodienthoai;
    private com.Product.GUI.textfield.TextField txt_tennhanvien;
    private com.Product.GUI.textfield.TextField txttimkiem_tennhanvien;
    // End of variables declaration//GEN-END:variables
}

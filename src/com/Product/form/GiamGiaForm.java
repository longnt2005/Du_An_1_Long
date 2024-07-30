package com.Product.form;

import com.Product.GUI.Table;
import main.entity.PhieuGiamGia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.repository.PhieuGiamGiaRepository;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
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
public class GiamGiaForm extends javax.swing.JPanel {
   private DefaultTableModel dtm;
   private int i = -1;
   private PhieuGiamGiaRepository repo;
    private ArrayList<PhieuGiamGia> list;

   public GiamGiaForm() throws SQLException{
       initComponents();
       //viet code duoi innit
       dtm = (DefaultTableModel) tbl_BangTT.getModel();
       repo = new PhieuGiamGiaRepository();
       //show datatable
       ShowDataTable(repo.getAll());
    }
   private void ShowDataTable(ArrayList<PhieuGiamGia>list ){
       dtm.setRowCount(0);
       AtomicInteger index = new AtomicInteger(1); // khoi tao 1 gia tri bat dau bang 1 de tu dong tang
       //for.. each + lamda
       list.forEach(s-> dtm.addRow(new Object[]{
       index.getAndIncrement(),s.getMa_Voucher(),s.getNgay_Bat_Dau(),s.getNgay_Het_Han(),s.getLoai_Giam_Gia(),s.getGia_Tri_Giam_Gia(),s.getGia_Tri_Don_Hang_Toi_Thieu(),s.getSo_Lan_Su_Dung_Toi_Da(),s.getSo_Lan_Su_Dung(),s.getTrang_Thai(),s.getMo_ta()
       }));
   }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new com.Product.GUI.combo_suggestion.ComboSuggestionUI();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSoLanDungToiDa = new com.Product.GUI.textfield.TextField();
        txtTenKhuyenMai = new com.Product.GUI.textfield.TextField();
        txtHDToiThieu = new com.Product.GUI.textfield.TextField();
        txtMaGiamGia = new com.Product.GUI.textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSoTienGiam = new com.Product.GUI.textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_BangTT = new com.Product.GUI.Table();
        BtnThem = new com.Product.swing.Button();
        BtnSua = new com.Product.swing.Button();
        BtnLamMoi = new com.Product.swing.Button();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        BtnExcel = new com.Product.swing.Button();
        BtnTimKiem = new com.Product.swing.Button();
        txtTimKiem = new com.Product.GUI.textfield.TextField();
        rdDHD = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdNHD = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdSDR = new com.Product.GUI.radio_button.RadioButtonCustom();
        BtnSua1 = new com.Product.swing.Button();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giảm Giá");

        txtSoLanDungToiDa.setLabelText("Số lần dùng tối đa: ");

        txtTenKhuyenMai.setLabelText("Tên Giảm Giá:");

        txtHDToiThieu.setLabelText("Hóa Đơn Tối Thiểu:");

        txtMaGiamGia.setEnabled(false);
        txtMaGiamGia.setLabelText("Mã Giảm Giá :");
        txtMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGiamGiaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ngày bắt đầu :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Ngày kết thúc :");

        txtSoTienGiam.setLabelText("Số Tiền Giảm Tối Đa :");
        txtSoTienGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTienGiamActionPerformed(evt);
            }
        });

        tbl_BangTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Voucher", "Ngày Bắt Đầu", "Ngày Hết Hạn", "Tên Giảm Giá", "Giá Trị Giảm", "Giá Trị Đơn Hàng Tối Thiểu", "Số Lần Dùng Tối Đ", "Số Lần Sử Dụng", "Trạng Thái ", "Mô Tả", "Hành Động"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_BangTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BangTTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_BangTT);

        BtnThem.setBackground(new java.awt.Color(255, 255, 0));
        BtnThem.setText("Thêm");
        BtnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThemActionPerformed(evt);
            }
        });

        BtnSua.setBackground(new java.awt.Color(255, 255, 0));
        BtnSua.setText("Sửa");
        BtnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuaActionPerformed(evt);
            }
        });

        BtnLamMoi.setBackground(new java.awt.Color(255, 255, 0));
        BtnLamMoi.setText("Làm Mới");
        BtnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLamMoiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Trạng Thái:");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Mô Tả:");

        BtnExcel.setBackground(new java.awt.Color(255, 255, 0));
        BtnExcel.setText("Xuất Excel");
        BtnExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcelActionPerformed(evt);
            }
        });

        BtnTimKiem.setBackground(new java.awt.Color(255, 255, 0));
        BtnTimKiem.setText("Tìm Kiếm");
        BtnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setLabelText("Tìm kiếm tại đây:");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        rdDHD.setBackground(new java.awt.Color(255, 204, 0));
        rdDHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdDHD);
        rdDHD.setText("Đang Hoạt Động");

        rdNHD.setBackground(new java.awt.Color(255, 204, 0));
        rdNHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdNHD);
        rdNHD.setText("Ngừng Hoạt Động");

        rdSDR.setBackground(new java.awt.Color(255, 204, 0));
        rdSDR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdSDR);
        rdSDR.setText("Sắp Diễn Ra");

        BtnSua1.setBackground(new java.awt.Color(255, 255, 0));
        BtnSua1.setText("Chuyển đổi");
        BtnSua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtSoLanDungToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtHDToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdNHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdSDR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 532, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLanDungToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHDToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6))))
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSDR, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoTienGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoTienGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoTienGiamActionPerformed

    private void tbl_BangTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BangTTMouseClicked
        // TODO add your handling code here:
      int i = tbl_BangTT.getSelectedRow();
txtMaGiamGia.setText(tbl_BangTT.getValueAt(i, 1).toString());

Date ngayBatDauStr = (Date) tbl_BangTT.getValueAt(i, 2);
txtNgayBatDau.setDate(ngayBatDauStr);

Date ngayKetThucStr = (Date) tbl_BangTT.getValueAt(i, 3);
txtNgayKetThuc.setDate(ngayKetThucStr);

txtTenKhuyenMai.setText(tbl_BangTT.getValueAt(i, 4).toString());
txtSoTienGiam.setText(tbl_BangTT.getValueAt(i, 5).toString());
txtHDToiThieu.setText(tbl_BangTT.getValueAt(i, 6).toString());
txtSoLanDungToiDa.setText(tbl_BangTT.getValueAt(i, 7).toString());
txtMoTa.setText(tbl_BangTT.getValueAt(i, 8).toString());

    }//GEN-LAST:event_tbl_BangTTMouseClicked

    private void BtnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThemActionPerformed
       try {
           // TODO add your handling code here:
           repo.add(getFormData());
           ShowDataTable(repo.getAll());
       } catch (SQLException ex) {
           Logger.getLogger(GiamGiaForm.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_BtnThemActionPerformed

    private void BtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuaActionPerformed
      int index = tbl_BangTT.getSelectedRow();
    PhieuGiamGia newPhieu = getFormData();

    if (newPhieu == null) {
        return;
    }
    try {
        PhieuGiamGia sp = repo.getAll().get(index);
        repo.update(newPhieu, sp.getID());
        ShowDataTable(repo.getAll());
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BtnSuaActionPerformed

    private void BtnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLamMoiActionPerformed
        // TODO add your handling code here:
        PhieuGiamGia ph = new PhieuGiamGia();
        txtMaGiamGia.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null); 
//        rdDHD.setSelected(!ph.getTrang_Thai().equals("Đang hoạt dộng"));
//        rdNHD.setSelected(!ph.getTrang_Thai().equals("Ngưng hoạt động"));
//        rdSDR.setSelected(!ph.getTrang_Thai().equals("Sắp diễn ra"));
        txtTenKhuyenMai.setText("");
        txtSoTienGiam.setText("");
        txtHDToiThieu.setText("");
        txtSoLanDungToiDa.setText("");  
        txtMoTa.setText("");
    }//GEN-LAST:event_BtnLamMoiActionPerformed

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Phiếu Giảm Giá");

            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Danh Sách Phiếu Giảm Giá");

            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "Mã Voucher", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tên Giảm Giá", "Giá Trị Giảm", "Giá Trị Đơn Hàng Tối Thiểu", "Số Lần Dùng Tối Đa", "Số Lần Dùng","Trạng Thái","Mô Tả"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            PhieuGiamGiaRepository repoGG = new PhieuGiamGiaRepository();
            ArrayList<PhieuGiamGia> listItem = repoGG.getAll();

            for (int i = 0; i < listItem.size(); i++) {
                PhieuGiamGia phieu = listItem.get(i);
                row = spreadsheet.createRow(8 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(phieu.getMa_Voucher());
                row.createCell(2).setCellValue(phieu.getNgay_Bat_Dau());
                row.createCell(3).setCellValue(phieu.getNgay_Het_Han());
                row.createCell(4).setCellValue(phieu.getLoai_Giam_Gia());
                row.createCell(5).setCellValue(phieu.getGia_Tri_Giam_Gia());
                row.createCell(6).setCellValue(phieu.getGia_Tri_Don_Hang_Toi_Thieu());
                row.createCell(7).setCellValue(phieu.getSo_Lan_Su_Dung_Toi_Da());
                row.createCell(8).setCellValue(phieu.getSo_Lan_Su_Dung());
                row.createCell(9).setCellValue(phieu.getTrang_Thai());
                row.createCell(10).setCellValue(phieu.getMo_ta());
            };

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

                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BtnExcelActionPerformed

    private void BtnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTimKiemActionPerformed
        // TODO add your handling code here:
        
        
       
    }//GEN-LAST:event_BtnTimKiemActionPerformed

    private void txtMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGiamGiaActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void BtnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSua1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSua1ActionPerformed

    private void initComponets(){
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_BangTT = (Table) new javax.swing.JTable();
        
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
private PhieuGiamGia getFormData() {
    try {
        Double SoTienGiamGia = Double.parseDouble(txtSoTienGiam.getText().trim());
        Double GiaTriDonHangToiThieu = Double.parseDouble(txtHDToiThieu.getText().trim());
        Integer SoLanDungToiDa = Integer.parseInt(txtSoLanDungToiDa.getText().trim());
//        Integer SoLanDung = Integer.parseInt(txtSoLanDung.getText().trim());

        if (txtTenKhuyenMai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên Khuyến Mãi", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null; // Trả về null nếu có lỗi nhập liệu
        }

        String trangThai;
        if (rdDHD.isSelected()) {
            trangThai = "Đang hoạt động";
        } else if (rdNHD.isSelected()) { // Giả định rằng bạn có một radio button khác cho "Ngưng hoạt động"
            trangThai = "Ngưng hoạt động";
        } else {
            trangThai = "Sắp diễn ra";
        }

        return PhieuGiamGia.builder()
                .Ma_Voucher(txtMaGiamGia.getText())
                .Ngay_Bat_Dau(txtNgayBatDau.getDate())
                .Ngay_Het_Han(txtNgayKetThuc.getDate())
                .Loai_Giam_Gia(txtTenKhuyenMai.getText())
                .Gia_Tri_Giam_Gia(SoTienGiamGia)
                .Gia_Tri_Don_Hang_Toi_Thieu(GiaTriDonHangToiThieu)
                .So_Lan_Su_Dung_Toi_Da(SoLanDungToiDa)
//                .So_Lan_Su_Dung(SoLanDung)
                .Trang_Thai(trangThai)
                .Mo_ta(txtMoTa.getText())
                .build();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Kiểm tra lại giá trị nhập vào, có thể có lỗi định dạng số", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
        return null;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null;
    }
}

   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.Button BtnExcel;
    private com.Product.swing.Button BtnLamMoi;
    private com.Product.swing.Button BtnSua;
    private com.Product.swing.Button BtnSua1;
    private com.Product.swing.Button BtnThem;
    private com.Product.swing.Button BtnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.Product.GUI.combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.Product.GUI.radio_button.RadioButtonCustom rdDHD;
    private com.Product.GUI.radio_button.RadioButtonCustom rdNHD;
    private com.Product.GUI.radio_button.RadioButtonCustom rdSDR;
    private com.Product.GUI.Table tbl_BangTT;
    private com.Product.GUI.textfield.TextField txtHDToiThieu;
    private com.Product.GUI.textfield.TextField txtMaGiamGia;
    private javax.swing.JTextArea txtMoTa;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private com.Product.GUI.textfield.TextField txtSoLanDungToiDa;
    private com.Product.GUI.textfield.TextField txtSoTienGiam;
    private com.Product.GUI.textfield.TextField txtTenKhuyenMai;
    private com.Product.GUI.textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private PhieuGiamGia repo(ArrayList<PhieuGiamGia> list) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

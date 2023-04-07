/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import com.mycompany.phanmemquanlibanquanao.service.KhuyenMaiService;
import com.mycompany.phanmemquanlibanquanao.service.impl.KhuyenMaiServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Giang
 */
public class KhuyenMaiJpanel extends javax.swing.JPanel {

    private KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl();
    private DefaultTableModel dtm;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form KhuyenMaiJpanel
     */
    public KhuyenMaiJpanel() {
        initComponents();        
        loadTable(khuyenMaiService.getAll());
    }

    public void loadTable(List<KhuyenMai> list) {
        dtm = (DefaultTableModel) tblKhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : list) {
            Object[] row = new Object[]{
                km.getId(),
                km.getMa(),
                km.getTen(),
                km.getMucGiamGia(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getTrangThai() == 1 ? "Đang hoạt động" : "Hết hạn"
            };
            dtm.addRow(row);
        }
    }

    public void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtMucGiam.setText("");
        txtBatDau.setDateFormatString("");
        txtKetThuc.setDateFormatString("");
        txtTrangThai.setText("");
    }
    public void checkKhuyenMai(){
        Date date=new Date();
        Thread thread=new Thread(){
            @Override
            public void run(){
                while (true) {                    
                    khuyenMaiService.checkChuaBatDau();
                    khuyenMaiService.checkDangHoatDong();
                    khuyenMaiService.checkKetThuc();
                    loadTable(khuyenMaiService.getAll());
                }
            }
        };
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtKetThuc = new com.toedter.calendar.JDateChooser();
        txtBatDau = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 255, 255));

        jLabel2.setText("Mã khuyến mại");

        jLabel3.setText("Tên khuyến mại");

        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setText("Ngày kết thúc");

        jLabel6.setText("Mức giảm giá");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtKetThuc.setDateFormatString("dd/MM/yyyy");

        txtBatDau.setDateFormatString("dd/MM/yyyy");

        jLabel7.setText("Trạng thái");

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel8.setText("Tìm kiếm");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KM", "Tên KM", "Mức giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);
        if (tblKhuyenMai.getColumnModel().getColumnCount() > 0) {
            tblKhuyenMai.getColumnModel().getColumn(0).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(1).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(2).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(3).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(4).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(5).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton1.setText("Reload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Quản lí khuyến mại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel6))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMucGiam)
                                    .addComponent(txtKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSua)
                                    .addComponent(btnThem)
                                    .addComponent(btnXoa)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel9)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(btnThem)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(btnSua))
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa)
                    .addComponent(jLabel7)
                    .addComponent(txtTrangThai))
                .addGap(57, 57, 57)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        StringBuilder sb=new StringBuilder();
        Long now=System.currentTimeMillis();
        Date start=txtBatDau.getDate();
        Date end=txtKetThuc.getDate();
        Long startValue=start.getTime();
        Long endValue=end.getTime();
        
        
        if(txtTen.getText().isEmpty()){
            sb.append("Tên không được để trống\n");
        }
        
        if(txtMucGiam.getText().isEmpty()){
            sb.append("Mức giảm không được để trống\n");
        }
        else{
            try{
                int mucGiam=Integer.parseInt(txtMucGiam.getText());
                if(mucGiam<1||mucGiam>99){
                    sb.append("Mức giảm phải lớn hơn 1 và nhỏ hơn 100\n");
                }
            }
            catch(Exception e){
                sb.append("Mức giảm phải là số\n");
            }
        }
        try{
            Date ngayBD=this.txtBatDau.getDate();
            Date ngayKT=this.txtKetThuc.getDate();
            if(ngayBD==null){
                sb.append("Ngày bắt đầu không được để trống\n");
            }
            if(ngayKT==null){
                sb.append("Ngày kết thúc không được để trống\n");
            }
            if(ngayBD.getTime()>=ngayKT.getTime()){
                sb.append("Ngày bắt đầu phải trước ngày kết thúc\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(sb.length()>0){
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        KhuyenMai km = new KhuyenMai();
        km.setMa(txtMa.getText());
        km.setTen(txtTen.getText());
        km.setNgayBatDau(txtBatDau.getDate());
        km.setNgayKetThuc(txtKetThuc.getDate());
        km.setMucGiamGia(Integer.valueOf(txtMucGiam.getText()));
        if(now>=startValue&&now<=endValue){
            km.setTrangThai(1);
        }
        else{
            km.setTrangThai(0);
        }
        
        if (khuyenMaiService.add(km)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(khuyenMaiService.getAll());
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        int row = tblKhuyenMai.getSelectedRow();
        txtMa.setText(tblKhuyenMai.getValueAt(row, 1).toString());
        txtTen.setText(tblKhuyenMai.getValueAt(row, 2).toString());
        txtMucGiam.setText(tblKhuyenMai.getValueAt(row, 3).toString());
        txtBatDau.setDate((Date) tblKhuyenMai.getValueAt(row, 4));
        txtKetThuc.setDate((Date) tblKhuyenMai.getValueAt(row, 5));
        if(tblKhuyenMai.getValueAt(row, 6).equals("Hết hạn")){
            txtTrangThai.setText("Hết hạn");
        }
        else{
            txtTrangThai.setText("Đang hoạt động");
        }
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        StringBuilder sb=new StringBuilder();
        if(txtTen.getText().isEmpty()){
            sb.append("Tên không được để trống\n");
        }
        if(txtMucGiam.getText().isEmpty()){
            sb.append("Mức giảm không được để trống\n");
        }
        else{
            try{
                int mucGiam=Integer.parseInt(txtMucGiam.getText());
                if(mucGiam<1||mucGiam>99){
                    sb.append("Mức giảm phải lớn hơn 1 và nhỏ hơn 100\n");
                }
            }
            catch(Exception e){
                sb.append("Mức giảm phải là số\n");
            }
        }
        try{
            Date ngayBD=this.txtBatDau.getDate();
            Date ngayKT=this.txtKetThuc.getDate();
            if(ngayBD==null){
                sb.append("Ngày bắt đầu không được để trống\n");
            }
            if(ngayKT==null){
                sb.append("Ngày kết thúc không được để trống\n");
            }
            if(ngayBD.getTime()>=ngayKT.getTime()){
                sb.append("Ngày bắt đầu phải trước ngày kết thúc\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(sb.length()>0){
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        int row = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = khuyenMaiService.getAll().get(row);
        km.setMa(txtMa.getText());
        km.setTen(txtTen.getText());
        km.setNgayBatDau(txtBatDau.getDate());
        km.setNgayKetThuc(txtKetThuc.getDate());
        km.setMucGiamGia(Integer.valueOf(txtMucGiam.getText()));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trên table để sửa");
        } else {
            if (khuyenMaiService.update(km)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadTable(khuyenMaiService.getAll());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblKhuyenMai.getSelectedRow();
        KhuyenMai km = khuyenMaiService.getAll().get(row);
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một dòng trên table để xoá");
        } else {
            if (khuyenMaiService.delete(km)) {
                JOptionPane.showMessageDialog(this, "Xoá thành công");
                loadTable(khuyenMaiService.getAll());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xoá thất bại");
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        List<KhuyenMai>list=new ArrayList<>();
        for(KhuyenMai km:khuyenMaiService.getAll()){
            if(km.getMa().contains(txtSearch.getText())||
                    km.getTen().contains(txtSearch.getText())||
                    String.valueOf(km.getMucGiamGia()).contains(txtSearch.getText())
                    ){
                list.add(km);
            }
        }
        loadTable(list);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadTable(khuyenMaiService.getAll());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhuyenMai;
    private com.toedter.calendar.JDateChooser txtBatDau;
    private com.toedter.calendar.JDateChooser txtKetThuc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucGiam;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables
}

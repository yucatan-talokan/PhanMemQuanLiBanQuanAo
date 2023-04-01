/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChucVu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import com.mycompany.phanmemquanlibanquanao.repository.NhanVienRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChucVuService;
import com.mycompany.phanmemquanlibanquanao.service.NhanVienService;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChucVuServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.NhanVienServiceImpl;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Thanh Giang
 */
public class NhanVienJpanel extends javax.swing.JPanel {

    /**
     * Creates new form test
     */
    
    private NhanVienServiceImpl nhanVienServiceImpl = new NhanVienServiceImpl();
      private NhanVienService nhanVienService = new NhanVienServiceImpl();
      private ChucVuService chucVuService = new ChucVuServiceImpl();
      
      private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private DefaultTableModel defaultTableModel2;
    private DefaultTableModel defaultTableModel;
    private List<NhanVien> list = nhanVienService.getAll();
    private List<ChucVu> listChucVus = nhanVienService.getChucVu();

     private StringBuilder sb;
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    public NhanVienJpanel() {
        initComponents();
        loadData(nhanVienService.getAll());
        loadCbo();
loadDataCV(chucVuService.getAll());
    }
      private void loadCbo() {
        DefaultComboBoxModel defaultComboBoxModel;
        defaultComboBoxModel = (DefaultComboBoxModel) cboChucVu.getModel();
        defaultComboBoxModel.removeAllElements();
        for (ChucVu chucVu : chucVuService.getAll()) {
            defaultComboBoxModel.addElement(chucVu.getTen());
        }
    }
      public void loadDataCV(List<ChucVu> list) {
        chucVuService = new ChucVuServiceImpl()
        ;
        defaultTableModel = (DefaultTableModel) tblChucVu1.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (ChucVu chucVu : list) {
            defaultTableModel.addRow(new Object[]{i, chucVu.getMa(), chucVu.getTen()});
            i++;
        }
    }

         public Boolean checkMChucVu() {
        Boolean check = true;
        for (ChucVu chucVu : listChucVus) {
            if (chucVu.getTen().equalsIgnoreCase(txtTenchucvu.getText())) {
                check = false;
            }
        }
        return check;
    }
            private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        String ngaySinh = format.format(d);
        return ngaySinh;
    }
        
    public void loadData(List<NhanVien> list) {
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        defaultTableModel2 = (DefaultTableModel) tbNhanVien.getModel();
        defaultTableModel2.setRowCount(0);
        int i = 1;
        for (NhanVien nhanVien : list) {
            defaultTableModel2.addRow(new Object[]{i, nhanVien.getMaNV(),nhanVien.getCccd(),nhanVien.getTenNhanVien(),nhanVien.htGioiTinh(), nhanVien.getEmail(),
              doiNgay(nhanVien.getNgaySinh()),nhanVien.getSDT(),nhanVien.getDiaChi(),nhanVien.getChucVu().getTen(),nhanVien.htDeleted()});
            i++;
        }
    }
  private StringBuilder validator() {
        sb = new StringBuilder();
        if (txtCccd.getText().isEmpty()) {
            sb.append("Chưa nhập cccd").append("\n");
        } else {
            String checkCmt = "\\d{9}";
            String checkCC = "\\d{12}";
            if (!txtCccd.getText().matches(checkCC) && !txtCccd.getText().matches(checkCmt)) {
                sb.append("Mã cccd sai hoặc không đúng định dạng").append("\n");
            } else {
                for (NhanVien nhanVien : nhanVienService.getAll()) {
                    if (nhanVien.getCccd().equals(txtCccd.getText())) {
                        sb.append("Mã cccd đã được sử dụng").append("\n");
                    }
                }
            }
        }
        if (txtTenNhanVien.getText().isEmpty()) {
            sb.append("Chưa nhập họ tên").append("\n");
        }
        if (txtEmail1.getText().isEmpty()) {
            sb.append("Chưa nhập Email").append("\n");
        } else {
            for (NhanVien nhanVien : nhanVienService.getAll()) {
                if (nhanVien.getEmail().equals(txtEmail1.getText())) {
                    sb.append("Email đã được sử dụng").append("\n");
                } else {
                    String checkEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                    if (!txtEmail1.getText().matches(checkEmail)) {
                        sb.append("Email sai định dạng").append("\n");
                    }
                }
            }
        }

        try {
            Date ngaySinh = this.txtNgaySinh.getDate();
            if (ngaySinh == null) {
                sb.append("Chưa nhập ngày sinh ").append("\n");
            }
        } catch (Exception e) {
        }
        try {
            LocalDate localDate = LocalDate.now();
            int year = localDate.getYear();
            int namSinh = this.txtNgaySinh.getDate().getYear() + 1900;
            System.out.println(namSinh);
            if (year - namSinh < 15) {
                sb.append("Nhân viên không trong độ tuổi lao động").append("\n");
            }
        } catch (Exception e) {
        }
        if (txtSDT.getText().isEmpty()) {
            sb.append("Chưa nhập số điện thoại").append("\n");
        } else {
            for (NhanVien nhanVien : nhanVienService.getAll()) {
                if (nhanVien.getSDT().equals(txtSDT.getText())) {
                    sb.append("SĐT đã được sử dụng").append("\n");
                }
            }
        }
        String checkSDT = "0\\d{9,10}";
        String checkSDT2 = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (!txtSDT.getText().matches(checkSDT) || !txtSDT.getText().matches(checkSDT2)) {
            sb.append("SĐT sai định dạng").append("\n");
        }
        if (txtDiaChi.getText().isEmpty()) {
            sb.append("Chưa nhập địa chỉ").append("\n");
        }
        return sb;
    }
  private StringBuilder validatorSua() {
        sb = new StringBuilder();
        if (txtTenNhanVien.getText().isEmpty()) {
            sb.append("Chưa nhập họ tên").append("\n");
        }
        if (txtEmail1.getText().isEmpty()) {
            sb.append("Chưa nhập Email").append("\n");
        } else {
            String checkEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            if (!txtEmail1.getText().matches(checkEmail)) {
                sb.append("Email sai định dạng").append("\n");
            }

        }
        try {
            Date ngaySinh = this.txtNgaySinh.getDate();
            if (ngaySinh == null) {
                sb.append("Chưa nhập ngày sinh ").append("\n");
            }
        } catch (Exception e) {
        }
        if (txtSDT.getText().isEmpty()) {
            sb.append("Chưa nhập số điện thoại").append("\n");
        }
        String checkSDT = "0\\d{9,10}";
        String checkSDT2 = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (!txtSDT.getText().matches(checkSDT) || !txtSDT.getText().matches(checkSDT2)) {
            sb.append("SĐT sai định dạng").append("\n");
        }
        if (txtDiaChi.getText().isEmpty()) {
            sb.append("Chưa nhập địa chỉ").append("\n");
        }
        return sb;
    }
 private void sendMail() {
//        final String username = "tranviethung271003@gmail.com";
//        final String password = "ibxcgycsagvxcabs";
        final String username = "luongpvph27222@fpt.edu.vn";
        final String password = "Vanluong03";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("luongpvph27222@fpt.edu.vn"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(txtEmail1.getText())
            );
            message.setSubject("Ban da duoc nhan lam tai cua hang");
            message.setText("Mat khau cua ban la: 1\n Luu y: Ban can doi mat khau som de dam bao an toan cho tai khoan !");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Gửi mail thành công");

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        rbnNam = new javax.swing.JRadioButton();
        rbnNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jlbTrangThai = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoaNv = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCccd = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        chekNghi = new javax.swing.JCheckBox();
        btnXuatFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChucVu1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenchucvu = new javax.swing.JTextField();
        txtMachucvu = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Tên nhân viên :");

        jLabel5.setText("Giới tính :");

        jLabel6.setText("Ngày Sinh :");

        buttonGroup1.add(rbnNam);
        rbnNam.setText("Nam");

        buttonGroup1.add(rbnNu);
        rbnNu.setText("Nữ");

        jLabel7.setText("SĐT :");

        jLabel8.setText("Địa chỉ :");

        jlbTrangThai.setText("Trạng thái :");

        jLabel12.setText("Email :");

        jLabel13.setText("Mã nhân viên :");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnNew.setBackground(new java.awt.Color(255, 0, 0));
        btnNew.setText("Làm mới ");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 0, 0));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(255, 0, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoaNv.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaNv.setText("Xoa");
        btnXoaNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnXoaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel9.setText("CCCD :");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        jLabel14.setText("Chức vụ");

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");

        chekNghi.setText("Nghi");

        btnXuatFile.setBackground(new java.awt.Color(0, 204, 102));
        btnXuatFile.setForeground(new java.awt.Color(51, 51, 51));
        btnXuatFile.setText("Xuất Excel");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboChucVu, 0, 300, Short.MAX_VALUE)
                    .addComponent(txtTenNhanVien)
                    .addComponent(txtCccd)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(rbnNu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlbTrangThai)
                        .addGap(18, 18, 18)
                        .addComponent(chekNghi))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))))
                .addGap(77, 77, 77)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbTrangThai)
                                    .addComponent(chekNghi)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel9)
                                                .addComponent(txtCccd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6))))))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rbnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbnNu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)))
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "CCCD", "Ten NV", "Giới tính", "Email", "Ngày sinh", "SDT", "Diachi", "Chức vụ", "Trang Thai"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jLabel11.setText("Tìm Kiếm :");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel2.setText("Danh sách nhân viên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(551, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhân viên", jPanel1);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tblChucVu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã chức vụ", "Tên chức vụ"
            }
        ));
        tblChucVu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVu1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChucVu1);

        jLabel4.setText("Mã chức vụ");

        jLabel10.setText("Tên chức vụ");

        btnClear.setText("Clear form");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThem1.setText("Thêm ");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnClear)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(41, 41, 41)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMachucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(116, 116, 116)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnThem1)
                                        .addComponent(btnSua1)
                                        .addComponent(btnXoa)))))))
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMachucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem1))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua1))
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(30, 30, 30)
                .addComponent(btnClear)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chức vụ", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
          txtMaNV.setText(null);
        txtCccd.setText(null);
        txtNgaySinh.setCalendar(null);
        txtTenNhanVien.setText(null);
        cboChucVu.setSelectedItem(0);
        txtDiaChi.setText(null);
        txtEmail1.setText(null);
        txtSDT.setText(null);
        rbnNam.setSelected(false);
        rbnNu.setSelected(false);
        chekNghi.setSelected(false);
        chekNghi.setEnabled(false);
        
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
      int index = tbNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng");
        }
        if (validatorSua().length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        } else {
            NhanVien nhanVien = nhanVienService.getAll().get(index);
            nhanVien.setMaNV(txtMaNV.getText());
//            nhanVien.setCccd(txtCccd.getText());
            nhanVien.setTenNhanVien(txtTenNhanVien.getText());
            ChucVu chucVu = chucVuService.getAll().get(cboChucVu.getSelectedIndex());
            nhanVien.setChucVu(chucVu);
            Boolean gioiTinh = false;
            if (rbnNam.isSelected()) {
                gioiTinh = true;
            }
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setEmail(txtEmail1.getText());
            try {
                String ngayS = doiNgay(txtNgaySinh.getDate());
                Date ngaySinh = format.parse(ngayS);
                nhanVien.setNgaySinh(ngaySinh);
            } catch (Exception e) {
                e.printStackTrace();
            }
            nhanVien.setSDT(txtSDT.getText());
            nhanVien.setDiaChi(txtDiaChi.getText());
//      nhanVien.setMatKhau(txtMatKhau.getText());
            nhanVien.setTrangThai(chekNghi.isSelected());
            if (nhanVienService.update(nhanVien)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadData((ArrayList<NhanVien>) nhanVienService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validator().length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        List<NhanVien> listNV = nhanVienService.getAll();
        NhanVien nhanVien = new NhanVien();
        String i = "123";
        nhanVien.autoPass(i);
        nhanVien.setMaNV(txtMaNV.getText());
        nhanVien.setCccd(txtCccd.getText());
        nhanVien.setTenNhanVien(txtTenNhanVien.getText());
        ChucVu chucVu = chucVuService.getAll().get(cboChucVu.getSelectedIndex());
        nhanVien.setChucVu(chucVu);
        Boolean gioiTinh = false;
        if (rbnNam.isSelected()) {
            gioiTinh = true;
        }
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setEmail(txtEmail1.getText());
        try {
            String ngayS = doiNgay(txtNgaySinh.getDate());
            Date ngaySinh = format.parse(ngayS);
            nhanVien.setNgaySinh(ngaySinh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nhanVien.setSDT(txtSDT.getText());
        nhanVien.setDiaChi(txtDiaChi.getText());
//        nhanVien.setMatKhau(txtMatKhau.getText());
        nhanVien.setTrangThai(chekNghi.isSelected());
        if (nhanVienService.add(nhanVien)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadData((ArrayList<NhanVien>) nhanVienService.getAll());
            sendMail();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNvActionPerformed
        // TODO add your handling code here:
        int index = tbNhanVien.getSelectedRow();
        NhanVien nhanVien = nhanVienService.getAll().get(index);
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        } else if (nhanVienService.delete(nhanVien)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadData(nhanVienService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaNvActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tbNhanVien.getSelectedRow();
        txtMaNV.setText(tbNhanVien.getValueAt(row, 1).toString());
        txtCccd.setText(tbNhanVien.getValueAt(row,2).toString());
        txtTenNhanVien.setText(tbNhanVien.getValueAt(row,3).toString());
        if (tbNhanVien.getValueAt(row, 4).toString().equalsIgnoreCase("Nam")) {
            rbnNam.setSelected(true);
        } else {
            rbnNu.setSelected(true);
        }
        txtEmail1.setText(tbNhanVien.getValueAt(row,5).toString());
         try {
            Date ngayS = new SimpleDateFormat("dd-MM-yyyy").parse(tbNhanVien.getValueAt(row, 6).toString());
            txtNgaySinh.setDate(ngayS);
        } catch (Exception e) {
        }
        txtSDT.setText(tbNhanVien.getValueAt(row,7).toString());
        txtDiaChi.setText(tbNhanVien.getValueAt(row,8).toString());
        cboChucVu.setSelectedItem(tbNhanVien.getValueAt(row, 9).toString());
        if(tbNhanVien.getValueAt(row,10).toString().equalsIgnoreCase("Nghỉ làm")){
            chekNghi.setSelected(true);
        }else{
            chekNghi.setSelected(false);
        }
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        ArrayList<NhanVien> lst = new ArrayList<>();
        for (NhanVien nv : nhanVienService.getAll()) {
            if (nv.getTenNhanVien().contains(txtTim.getText()) || nv.getMaNV().contains(txtTim.getText()) || nv.getCccd().contains(txtTim.getText())) {
                lst.add(nv);
            }
        }
        loadData(lst);
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        String CurentDirectoryFilePath = "";
        JFileChooser execlExportChooser = new JFileChooser(CurentDirectoryFilePath);
        FileNameExtensionFilter excelFNEF = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        execlExportChooser.setFileFilter(excelFNEF);
        execlExportChooser.setDialogTitle("Save excel... ");

        int excelChooser = execlExportChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook exceSSFWorkbookExprort = new XSSFWorkbook();
            XSSFSheet excelSheet = exceSSFWorkbookExprort.createSheet("Danh Sach");
            for (int i = 0; i < defaultTableModel2.getRowCount(); i++) {
                XSSFRow excelRow = excelSheet.createRow(i);
                for (int j = 0; j < defaultTableModel2.getColumnCount(); j++) {
                    XSSFCell excelCell = excelRow.createCell(j);
                    excelCell.setCellValue(defaultTableModel2.getValueAt(i, j).toString());
                }
            }
            FileOutputStream excelFIS;
            BufferedOutputStream excelBOU;
            try {
                excelFIS = new FileOutputStream(execlExportChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFIS);
                exceSSFWorkbookExprort.write(excelBOU);
                excelBOU.close();
                exceSSFWorkbookExprort.close();
                JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int index = tblChucVu1.getSelectedRow();
        ChucVu chucVu = chucVuService.getAll().get(index);
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        } else if (chucVuService.delete(chucVu)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadDataCV(chucVuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblChucVu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVu1MouseClicked
        // TODO add your handling code here:
        int row = tblChucVu1.getSelectedRow();
        txtMachucvu.setText(tblChucVu1.getValueAt(row, 1).toString());
        txtTenchucvu.setText(tblChucVu1.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tblChucVu1MouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtMachucvu.setText("");
        txtTenchucvu.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        ChucVu chucVu = new ChucVu();
        chucVu.setTen(txtTenchucvu.getText());
        chucVu.setMa(txtMachucvu.getText());
        if (chucVuService.add(chucVu)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadDataCV(chucVuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        //        // TODO add your handling code here:
        //        int index = tblChucVu.getSelectedRow();
        //        ChucVu chucVu = chucVuService.getAll().get(index);
        //        chucVu.setTen(txtTenchucvu.getText());
        //        chucVu.setMa(txtMachucvu.getText());
        //        if (chucVuService) {
            //            JOptionPane.showMessageDialog(this, "Sửa thành công");
            //            loadData(chucVuService.getAll());
            //        } else {
            //            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            //        }
    }//GEN-LAST:event_btnSua1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaNv;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JCheckBox chekNghi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlbTrangThai;
    private javax.swing.JRadioButton rbnNam;
    private javax.swing.JRadioButton rbnNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTable tblChucVu1;
    private javax.swing.JTextField txtCccd;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMachucvu;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenchucvu;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}

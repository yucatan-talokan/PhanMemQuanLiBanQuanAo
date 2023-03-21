/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;


import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;

import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import com.mycompany.phanmemquanlibanquanao.repository.ChiTietSPRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChatLieuService;
import com.mycompany.phanmemquanlibanquanao.service.DongSPService;
import com.mycompany.phanmemquanlibanquanao.service.MauSacService;
import com.mycompany.phanmemquanlibanquanao.service.NSXService;
import com.mycompany.phanmemquanlibanquanao.service.SanPhamService;
import com.mycompany.phanmemquanlibanquanao.service.SizeService;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChatLieuServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChiTietSPServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.DongSPServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.MauSacServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.NSXServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.SanPhamServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.SizeServiceImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Giang
 */
public class SanPhamJpanel extends javax.swing.JPanel {

    /**
     * Creates new form SanPhamJpanel
     */
    private DefaultTableModel model;
    ChiTietSPServiceImpl chiTietSpServiceImpl = new ChiTietSPServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private SizeService sizeService = new SizeServiceImpl();
    private NSXService nSXService = new NSXServiceImpl();
    private DongSPService dongSPService = new DongSPServiceImpl();
    private ChatLieuService chatLieuService = new ChatLieuServiceImpl();
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    
    private ChiTietSPRepository chiTietSpRepository = new ChiTietSPRepository();
    
    private List<MauSac> listMauSac = chiTietSpServiceImpl.getMauSac();
    private List<Size> listSize = chiTietSpServiceImpl.getSize();
    private List<NSX> listNSX = chiTietSpServiceImpl.getNsx();
    private List<DongSP> listDongSP = chiTietSpServiceImpl.getDongSP();
    private List<ChatLieu> listChatLieu = chiTietSpServiceImpl.getChatLieu();
    private List<SanPham> listSanPham = chiTietSpServiceImpl.getSanPham();
    
    public SanPhamJpanel() {
        initComponents();
        loadCboMauSac(chiTietSpServiceImpl.getMauSac());
        loadCboSize(chiTietSpServiceImpl.getSize());
        loadCboNSX(chiTietSpServiceImpl.getNsx());
        loadCboDongSP(chiTietSpServiceImpl.getDongSP());
        loadCboChatLieu(chiTietSpServiceImpl.getChatLieu());
        loadCboSanPham(chiTietSpServiceImpl.getSanPham());
    }

    public void loadCboMauSac(List<MauSac> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (MauSac mauSac : list) {
            row.addElement(mauSac.getTen());
        }
        cboMauSac.setModel(row);

    }
    
    public void loadCboSize(List<Size> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (Size size : list) {
            row.addElement(size.getTen());
        }
        cboSize.setModel(row);

    }
    
     public void loadCboNSX(List<NSX> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (NSX nsx : list) {
            row.addElement(nsx.getTen());
        }
        cboNsx.setModel(row);

    }

      public void loadCboDongSP(List<DongSP> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (DongSP dongSP : list) {
            row.addElement(dongSP.getTen());
        }
        cboDongSp.setModel(row);

    }
      
       public void loadCboChatLieu(List<ChatLieu> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (ChatLieu chatLieu : list) {
            row.addElement(chatLieu.getTen());
        }
        cboChatLieu.setModel(row);

    }
      public void loadCboSanPham(List<SanPham> list) {
        DefaultComboBoxModel row = new DefaultComboBoxModel();
        for (SanPham sanPham : list) {
            row.addElement(sanPham.getTen());
        }
        cboSanPham.setModel(row);

    }
    public void loadTblMau(List<MauSac> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
        for (MauSac mauSac : list) {
            Object[] row = new Object[]{
                i, mauSac.getMa(), mauSac.getTen()
            };
            i++;
            model.addRow(row);

        }
    }
    
    public void loadTblSize(List<Size> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
        for (Size size : list) {
            Object[] row = new Object[]{
                i, size.getMa(), size.getTen()
            };
            i++;
            model.addRow(row);

        }
    }
    
     public void loadTblNSX(List<NSX> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
        for (NSX nsx : list) {
            Object[] row = new Object[]{
                i, nsx.getMa(), nsx.getTen()
            };
            i++;
            model.addRow(row);

        }
    }
     
      public void loadTblDongSP(List<DongSP> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
        for (DongSP dongSP : list) {
            Object[] row = new Object[]{
                i, dongSP.getMa(), dongSP.getTen()
            };
            i++;
            model.addRow(row);

        }
    }

       public void loadTblChatLieu(List<ChatLieu> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
            for (ChatLieu chatLieu : list) {
            Object[] row = new Object[]{
                i, chatLieu.getMa(),chatLieu.getTen()
            };
            i++;
            model.addRow(row);

        }
    }
        public void loadTblSanPham(List<SanPham> list) {
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        int i = 1;
            for (SanPham sanPham : list) {
            Object[] row = new Object[]{
                i, sanPham.getMa(),sanPham.getTen()
            };
            i++;
            model.addRow(row);

        }
    }
    public Boolean checkMauSac() {
        Boolean check = true;
        for (MauSac mauSac : listMauSac) {
            if (mauSac.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }
    
    public Boolean checkSize() {
        Boolean check = true;
        for (Size size : listSize) {
            if (size.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }
    
     public Boolean checkNSX() {
        Boolean check = true;
        for (NSX nsx : listNSX) {
            if (nsx.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }
 
      public Boolean checkDongSP() {
        Boolean check = true;
        for (DongSP dongSP: listDongSP) {
            if (dongSP.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }
       public Boolean checkChatLieu() {
        Boolean check = true;
        for (ChatLieu chatLieu: listChatLieu) {
            if (chatLieu.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }
        public Boolean checkSanPham() {
        Boolean check = true;
        for (SanPham sanPham: listSanPham) {
            if (sanPham.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSUa = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSp = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboNsx = new javax.swing.JComboBox<>();
        cboSanPham = new javax.swing.JComboBox<>();
        cboDongSp = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        rdoMau = new javax.swing.JRadioButton();
        rdoSize = new javax.swing.JRadioButton();
        rdoDongSP = new javax.swing.JRadioButton();
        rdoNSX = new javax.swing.JRadioButton();
        rboChatLieu = new javax.swing.JRadioButton();
        rdoSanPham = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setText("Them");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSUa.setText("Sua");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });

        jButton3.setText("Xoa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(btnSUa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSUa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblChiTietSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Chất liệu", "Loại", "Size", "NSX", "Màu sắc", "Sản phẩm", "Số lượng", "Giá ", "Mô tả"
            }
        ));
        tblChiTietSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSp);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUAN LI SAN PHAM");

        jLabel2.setText("Ma San Pham");

        txtMaSp.setEditable(false);
        txtMaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSpActionPerformed(evt);
            }
        });

        jLabel4.setText("San Pham");

        jLabel5.setText("Loai");

        jLabel6.setText("So Luong Ton");

        jLabel7.setText("Mau Sac");

        jLabel8.setText("Gia");

        jLabel9.setText("Chat Lieu");

        jLabel10.setText("Mo Ta");

        jLabel11.setText("Size");

        jLabel12.setText("Noi San Xuat");

        cboNsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNsx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNsxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboNsxMouseEntered(evt);
            }
        });
        cboNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNsxActionPerformed(evt);
            }
        });

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboSanPhamMouseEntered(evt);
            }
        });
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        cboDongSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDongSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboDongSpMouseEntered(evt);
            }
        });
        cboDongSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDongSpActionPerformed(evt);
            }
        });

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboMauSacMouseEntered(evt);
            }
        });
        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboChatLieuMouseEntered(evt);
            }
        });

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboSizeMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jButton5.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(28, 28, 28)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin", jPanel1);

        jLabel3.setText("Tên thuộc tính");

        jLabel13.setText("Mã");

        jLabel14.setText("Ngày tạo");

        buttonGroup1.add(rdoMau);
        rdoMau.setText("Màu sắc");
        rdoMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoSize);
        rdoSize.setText("Size");
        rdoSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoSizeMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoDongSP);
        rdoDongSP.setText("Loại");
        rdoDongSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDongSPMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoNSX);
        rdoNSX.setText("NSX");
        rdoNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNSXMouseClicked(evt);
            }
        });

        buttonGroup1.add(rboChatLieu);
        rboChatLieu.setText("Chất liệu");
        rboChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rboChatLieuMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoSanPham);
        rdoSanPham.setText("Sản phẩm");
        rdoSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoSanPhamMouseClicked(evt);
            }
        });

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        jScrollPane2.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton4)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(113, 113, 113)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rdoMau)
                                    .addComponent(rboChatLieu)
                                    .addComponent(rdoDongSP)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jButton1)))
                        .addGap(109, 109, 109)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoSize)
                            .addComponent(rdoNSX)
                            .addComponent(rdoSanPham)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoMau)
                    .addComponent(rdoSize))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDongSP)
                    .addComponent(rdoNSX))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rboChatLieu)
                        .addComponent(rdoSanPham)))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính", jPanel2);

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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed

    }//GEN-LAST:event_btnSUaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblChiTietSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSpMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblChiTietSpMouseClicked

    private void txtMaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSpActionPerformed

    private void cboNsxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNsxMouseClicked
        //         TODO add your handling code here:
    }//GEN-LAST:event_cboNsxMouseClicked

    private void cboNsxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNsxMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNsxMouseEntered

    private void cboNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNsxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNsxActionPerformed

    private void cboSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSanPhamMouseEntered

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void cboDongSpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDongSpMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDongSpMouseEntered

    private void cboDongSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDongSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDongSpActionPerformed

    private void cboMauSacMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMauSacMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacMouseEntered

    private void cboChatLieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboChatLieuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChatLieuMouseEntered

    private void cboSizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSizeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSizeMouseEntered

    private void rdoMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauMouseClicked
        loadTblMau(chiTietSpServiceImpl.getMauSac());
return;        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMauMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        chiTietSpRepository = new ChiTietSPRepository();
        chiTietSpServiceImpl = new ChiTietSPServiceImpl();
        if (rdoMau.isSelected() == true) {

            try {
                MauSac mauSac = new MauSac();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền màu sắc");
                    return;
                }
                if (!checkMauSac()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                mauSac.setTen(txtTenThuocTinh.getText());
                mauSac.setMa(txtMa.getText());

                if (mauSacService.add(mauSac)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblMau(chiTietSpServiceImpl.getMauSac());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblMau(chiTietSpServiceImpl.getMauSac());
        
        
        
         if (rdoSize.isSelected() == true) {

            try {
                Size size = new Size();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền size");
                    return;
                }
                if (!checkSize()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                size.setTen(txtTenThuocTinh.getText());
                size.setMa(txtMa.getText());

                if (sizeService.add(size)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblSize(chiTietSpServiceImpl.getSize());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblSize(chiTietSpServiceImpl.getSize());
        
        
          if (rdoNSX.isSelected() == true) {

            try {
                NSX nsx = new NSX();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền NSX");
                    return;
                }
                if (!checkNSX()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                nsx.setTen(txtTenThuocTinh.getText());
                nsx.setMa(txtMa.getText());

                if (nSXService.add(nsx)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblNSX(chiTietSpServiceImpl.getNsx());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblNSX(chiTietSpServiceImpl.getNsx());
        if (rdoDongSP.isSelected() == true) {

            try {
                DongSP dongSP = new DongSP();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền Dòng sản phẩm");
                    return;
                }
                if (!checkDongSP()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                dongSP.setTen(txtTenThuocTinh.getText());
                dongSP.setMa(txtMa.getText());

                if (dongSPService.add(dongSP)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblDongSP(chiTietSpServiceImpl.getDongSP());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblChatLieu(chiTietSpServiceImpl.getChatLieu());
         if (rboChatLieu.isSelected() == true) {

            try {
                ChatLieu chatLieu = new ChatLieu();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền chất liệu");
                    return;
                }
                if (!checkChatLieu()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                chatLieu.setTen(txtTenThuocTinh.getText());
                chatLieu.setMa(txtMa.getText());

                if (chatLieuService.add(chatLieu)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblChatLieu(chiTietSpServiceImpl.getChatLieu());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblChatLieu(chiTietSpServiceImpl.getChatLieu());
         if (rdoSanPham.isSelected() == true) {

            try {
                SanPham sanPham = new SanPham();
                if (txtTenThuocTinh.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Điền Sản phẩm");
                    return;
                }
                if (!checkSanPham()) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    return;
                }
                sanPham.setTen(txtTenThuocTinh.getText());
                sanPham.setMa(txtMa.getText());

                if (sanPhamService.add(sanPham)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTblSanPham(chiTietSpServiceImpl.getSanPham());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadTblSanPham(chiTietSpServiceImpl.getSanPham());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:   
        chiTietSpRepository = new ChiTietSPRepository();
        chiTietSpServiceImpl = new ChiTietSPServiceImpl();
        if (rdoMau.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho mau de xoa");
                    return;
                }
                MauSac chatLieu = mauSacService.getAll().get(index);
                if (mauSacService.delete(chatLieu)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblMau(mauSacService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
         if (rdoSize.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho size de xoa");
                    return;
                }
                Size size = sizeService.getAll().get(index);
                if (sizeService.delete(size)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblSize(sizeService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
          if (rdoNSX.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho nsx de xoa");
                    return;
                }
                NSX nsx = nSXService.getAll().get(index);
                if (nSXService.delete(nsx)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblNSX(nSXService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
          if (rdoDongSP.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho dòng sản phẩm de xoa");
                    return;
                }
                DongSP dongSP = dongSPService.getAll().get(index);
                if (dongSPService.delete(dongSP)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblDongSP(dongSPService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
           if (rboChatLieu.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho chất liệu de xoa");
                    return;
                }
                ChatLieu chatLieu = chatLieuService.getAll().get(index);
                if (chatLieuService.delete(chatLieu)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblChatLieu(chatLieuService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
           if (rdoSanPham.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chua cho san pham de xoa");
                    return;
                }
                SanPham sanPham = sanPhamService.getAll().get(index);
                if (sanPhamService.delete(sanPham)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblSanPham(sanPhamService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lien quan san pham chi tiet");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rdoSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoSizeMouseClicked
        // TODO add your handling code here:
         loadTblSize(chiTietSpServiceImpl.getSize());
         return;
    }//GEN-LAST:event_rdoSizeMouseClicked

    private void rdoNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNSXMouseClicked
        // TODO add your handling code here:
        loadTblNSX(chiTietSpServiceImpl.getNsx());
         return;
    }//GEN-LAST:event_rdoNSXMouseClicked

    private void rdoDongSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDongSPMouseClicked
        // TODO add your handling code here:
        loadTblDongSP(chiTietSpServiceImpl.getDongSP());
         return;
    }//GEN-LAST:event_rdoDongSPMouseClicked

    private void rboChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rboChatLieuMouseClicked
        // TODO add your handling code here:
         loadTblChatLieu(chiTietSpServiceImpl.getChatLieu());
         return;
    }//GEN-LAST:event_rboChatLieuMouseClicked

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacActionPerformed

    private void rdoSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoSanPhamMouseClicked
        // TODO add your handling code here:
        loadTblSanPham(chiTietSpServiceImpl.getSanPham());
         return;
    }//GEN-LAST:event_rdoSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSUa;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboDongSp;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNsx;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JRadioButton rboChatLieu;
    private javax.swing.JRadioButton rdoDongSP;
    private javax.swing.JRadioButton rdoMau;
    private javax.swing.JRadioButton rdoNSX;
    private javax.swing.JRadioButton rdoSanPham;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JTable tblChiTietSp;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}

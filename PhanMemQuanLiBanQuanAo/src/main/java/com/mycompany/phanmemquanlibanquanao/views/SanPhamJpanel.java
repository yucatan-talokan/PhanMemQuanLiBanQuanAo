/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.WriterException;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.QR;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private List<ChiTietSP> list = chiTietSpServiceImpl.getAll();
    private List<MauSac> listMauSac = chiTietSpServiceImpl.getMauSac();
    private List<Size> listSize = chiTietSpServiceImpl.getSize();
    private List<NSX> listNSX = chiTietSpServiceImpl.getNsx();
    private List<DongSP> listDongSP = chiTietSpServiceImpl.getDongSP();
    private List<ChatLieu> listChatLieu = chiTietSpServiceImpl.getChatLieu();
    private List<SanPham> listSanPham = chiTietSpServiceImpl.getSanPham();
    public Webcam webcam = null;

    public SanPhamJpanel() {
        initComponents();
        loadCboMauSac(chiTietSpServiceImpl.getMauSac());
        loadCboSize(chiTietSpServiceImpl.getSize());
        loadCboNSX(chiTietSpServiceImpl.getNsx());
        loadCboDongSP(chiTietSpServiceImpl.getDongSP());
        loadCboChatLieu(chiTietSpServiceImpl.getChatLieu());
        loadCboSanPham(chiTietSpServiceImpl.getSanPham());
        loadTableCtSanPham(chiTietSpServiceImpl.getAll());
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
                i, chatLieu.getMa(), chatLieu.getTen()
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
                i, sanPham.getMa(), sanPham.getTen()
            };
            i++;
            model.addRow(row);

        }
    }

    public void loadTableCtSanPham(ArrayList<ChiTietSP> list) {
        model = (DefaultTableModel) tblChiTietSp.getModel();
        model.setRowCount(0);

        int i = 1;
        for (ChiTietSP chiTietSanPham : list) {
            Object[] row = new Object[]{chiTietSanPham.getMactsp(), chiTietSanPham.getChatLieu().getTen(), chiTietSanPham.getDongSp().getTen(),
                chiTietSanPham.getSize().getTen(), chiTietSanPham.getNsx().getTen(), chiTietSanPham.getMauSac().getTen(), chiTietSanPham.getSanPham().getTen(), chiTietSanPham.getSoLuongTon(),
                chiTietSanPham.getGia(), chiTietSanPham.getMoTa()};
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
        for (DongSP dongSP : listDongSP) {
            if (dongSP.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }

    public Boolean checkChatLieu() {
        Boolean check = true;
        for (ChatLieu chatLieu : listChatLieu) {
            if (chatLieu.getTen().equalsIgnoreCase(txtTenThuocTinh.getText())) {
                check = false;
            }
        }
        return check;
    }

    public Boolean checkSanPham() {
        Boolean check = true;
        for (SanPham sanPham : listSanPham) {
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
        btnQR = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSp = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        txtMaSp = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        txtTu = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDen = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
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
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSUa.setText("Sửa");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });

        btnQR.setText("QR");
        btnQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRActionPerformed(evt);
            }
        });

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(581, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(317, 317, 317)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSUa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addContainerGap()))
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

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lí sản phẩm");

        jLabel2.setText("Mã sản phẩm");

        jLabel4.setText("Sản phẩm");

        jLabel5.setText("Loại");

        jLabel6.setText("Số luọng tồn");

        jLabel7.setText("Màu sắc");

        jLabel8.setText("Giá");

        jLabel9.setText("Chất liệu");

        jLabel10.setText("Mô tả");

        jLabel11.setText("Size");

        jLabel12.setText("Nơi sản xuất");

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
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaSp))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cboDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabel1)))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMouseClicked(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        txtTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuActionPerformed(evt);
            }
        });
        txtTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKeyReleased(evt);
            }
        });

        jLabel15.setText("Giá từ");

        txtDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenKeyReleased(evt);
            }
        });

        jLabel16.setText("Đến");

        jButton3.setText("Tìm kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(28, 28, 28)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
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
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin", jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 255, 255));

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
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThuocTinh);

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addGap(49, 49, 49)
                                                .addComponent(jButton4))
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
                .addContainerGap(320, Short.MAX_VALUE))
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
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
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
        try {
            List<ChiTietSP> list = chiTietSpServiceImpl.getAll();
            ChiTietSP chiTietSp = new ChiTietSP();

            if (txtMaSp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã !");
                return;
            }
            if (Integer.parseInt(txtSoLuongTon.getText()) <= 0 || txtSoLuongTon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số không được để trống !");
                return;
            }
            if (Integer.parseInt(txtGia.getText()) <= 0 || txtGia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá phải là số và không được để trống !");
                return;
            }

            chiTietSp.setSoLuongTon(Integer.parseInt(txtSoLuongTon.getText()));
            chiTietSp.setGia(Integer.parseInt(txtGia.getText()));
            chiTietSp.setMoTa(txtMoTa.getText());
            chiTietSp.setMactsp(txtMaSp.getText());

            int index2 = cboChatLieu.getSelectedIndex();
            ChatLieu chatLieu = chiTietSpServiceImpl.getChatLieu().get(index2);
            chiTietSp.setChatLieu(chatLieu);

            int index3 = cboDongSp.getSelectedIndex();
            DongSP sp = chiTietSpServiceImpl.getDongSP().get(index3);
            chiTietSp.setDongSp(sp);

            int index4 = cboSize.getSelectedIndex();
            Size size = chiTietSpServiceImpl.getSize().get(index4);
            chiTietSp.setSize(size);

            int index5 = cboNsx.getSelectedIndex();
            NSX nsx = chiTietSpServiceImpl.getNsx().get(index5);
            chiTietSp.setNsx(nsx);

            int index6 = cboMauSac.getSelectedIndex();
            MauSac ms = chiTietSpServiceImpl.getMauSac().get(index6);
            chiTietSp.setMauSac(ms);

            int index7 = cboSanPham.getSelectedIndex();
            SanPham sanPham = chiTietSpServiceImpl.getSanPham().get(index7);
            chiTietSp.setSanPham(sanPham);

//            if (chiTietSpRepository.getCheckTrung(sanPham.getId(), sp.getId(), ms.getId(), chatLieu.getId(), size.getId(), nsx.getId()).size() > 0) {
//                JOptionPane.showMessageDialog(this, "Trung thuoc tinh");
//                return;
//            }
            if (chiTietSpServiceImpl.add(chiTietSp) == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadTableCtSanPham(chiTietSpRepository.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thất bại");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed
        chiTietSpRepository = new ChiTietSPRepository();
        chiTietSpServiceImpl = new ChiTietSPServiceImpl();
        try {
            String id = txtMaSp.getText();
            ChiTietSP chiTietSp = chiTietSpServiceImpl.getOne(id);

            if (txtMaSp.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã !");
                return;
            }
            if (Integer.parseInt(txtGia.getText()) <= 0 || txtGia.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, " Giá phải là số và không được để trống !");
                return;
            }
            if (Integer.parseInt(txtSoLuongTon.getText()) <= 0 || txtSoLuongTon.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số không được để trống !");
                return;
            }
            chiTietSp.setMactsp(txtMaSp.getText());

            int index2 = cboChatLieu.getSelectedIndex();
            ChatLieu chatLieu = chiTietSpServiceImpl.getChatLieu().get(index2);
            chiTietSp.setChatLieu(chatLieu);

            int index3 = cboDongSp.getSelectedIndex();
            DongSP sp = chiTietSpServiceImpl.getDongSP().get(index3);
            chiTietSp.setDongSp(sp);

            int index4 = cboSize.getSelectedIndex();
            Size size = chiTietSpServiceImpl.getSize().get(index4);
            chiTietSp.setSize(size);

            int index5 = cboNsx.getSelectedIndex();
            NSX nsx = chiTietSpServiceImpl.getNsx().get(index5);
            chiTietSp.setNsx(nsx);

            int index6 = cboMauSac.getSelectedIndex();
            MauSac ms = chiTietSpServiceImpl.getMauSac().get(index6);
            chiTietSp.setMauSac(ms);

            int index7 = cboSanPham.getSelectedIndex();
            SanPham sanPham = chiTietSpServiceImpl.getSanPham().get(index7);
            chiTietSp.setSanPham(sanPham);

            chiTietSp.setSoLuongTon(Integer.parseInt(txtSoLuongTon.getText()));
            chiTietSp.setGia(Integer.parseInt(txtGia.getText()));
            chiTietSp.setMoTa(txtMoTa.getText());

            Boolean check = false;
            chiTietSpRepository = new ChiTietSPRepository();
            chiTietSpServiceImpl = new ChiTietSPServiceImpl();
            for (ChiTietSP chiTietSp1 : chiTietSpRepository.getAll()) {
                if (chiTietSp1.getId() == chiTietSp.getId()) {
                    check = true;
                }
            }
//            if (check == false) {
//                if (chiTietSpRepository.getCheckTrung(sanPham.getId(), sp.getId(), ms.getId(), chatLieu.getId(), size.getId(), nsx.getId()).size() > 0) {
//                    JOptionPane.showMessageDialog(this, "Trung thuoc tinh");
//                    return;
//                }
//2
//            }

            if (chiTietSpServiceImpl.update(chiTietSp) == true) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTableCtSanPham(chiTietSpRepository.getAll());
    }//GEN-LAST:event_btnSUaActionPerformed

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        // TODO add your handling code here:
        int index = tblChiTietSp.getSelectedRow();

        QR qr = new QR();
        String id = tblChiTietSp.getValueAt(index, 0).toString();
        try {
            qr.output(id);
            JOptionPane.showMessageDialog(this, "Xuất mã QR thành công");
        } catch (WriterException ex) {
            Logger.getLogger(SanPhamJpanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnQRActionPerformed

    private void tblChiTietSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSpMouseClicked
        // TODO add your handling code here:
        int index = tblChiTietSp.getSelectedRow();

        txtMaSp.setText(tblChiTietSp.getValueAt(index, 0).toString());
        cboChatLieu.setSelectedItem(tblChiTietSp.getValueAt(index, 1));
        cboDongSp.setSelectedItem(tblChiTietSp.getValueAt(index, 2));
        cboSize.setSelectedItem(tblChiTietSp.getValueAt(index, 3));
        cboNsx.setSelectedItem(tblChiTietSp.getValueAt(index, 4));
        cboMauSac.setSelectedItem(tblChiTietSp.getValueAt(index, 5));
        cboSanPham.setSelectedItem(tblChiTietSp.getValueAt(index, 6));
        txtSoLuongTon.setText(tblChiTietSp.getValueAt(index, 7).toString());
        txtGia.setText(tblChiTietSp.getValueAt(index, 8).toString());
        txtMoTa.setText(tblChiTietSp.getValueAt(index, 9).toString());
    }//GEN-LAST:event_tblChiTietSpMouseClicked

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
                    JOptionPane.showMessageDialog(this, "Chưa chọn Màu sắc !");
                    return;
                }
                MauSac mauSac = mauSacService.getAll().get(index);
                if (mauSacService.delete(mauSac)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblMau(mauSacService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
                e.printStackTrace();
            }
        }
        if (rdoSize.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn size !");
                    return;
                }
                Size size = sizeService.getAll().get(index);
                if (sizeService.delete(size)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblSize(sizeService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
                e.printStackTrace();
            }
        }
        if (rdoNSX.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn nsx !");
                    return;
                }
                NSX nsx = nSXService.getAll().get(index);
                if (nSXService.delete(nsx)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblNSX(nSXService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
                e.printStackTrace();
            }
        }
        if (rdoDongSP.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn dòng sản phẩm !");
                    return;
                }
                DongSP dongSP = dongSPService.getAll().get(index);
                if (dongSPService.delete(dongSP)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblDongSP(dongSPService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
                e.printStackTrace();
            }
        }
        if (rboChatLieu.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn chất liệu !");
                    return;
                }
                ChatLieu chatLieu = chatLieuService.getAll().get(index);
                if (chatLieuService.delete(chatLieu)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblChatLieu(chatLieuService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
                e.printStackTrace();
            }
        }
        if (rdoSanPham.isSelected()) {
            try {
                int index = tblThuocTinh.getSelectedRow();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm  !");
                    return;
                }
                SanPham sanPham = sanPhamService.getAll().get(index);
                if (sanPhamService.delete(sanPham)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadTblSanPham(sanPhamService.getAll());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Liên quan chi tiết sản phẩm");
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

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        ArrayList<ChiTietSP> lst = new ArrayList<>();
        for (ChiTietSP ctsp : chiTietSpRepository.getAll()) {
            if (ctsp.getMactsp().contains(txtTim.getText()) || ctsp.getSanPham().getTen().contains(txtTim.getText())
                    || ctsp.getDongSp().getTen().contains(txtTim.getText()) || ctsp.getNsx().getTen().contains(txtTim.getText())
                    || ctsp.getChatLieu().getTen().contains(txtTim.getText()) || ctsp.getMauSac().getTen().contains(txtTim.getText())
                    || ctsp.getSize().getTen().contains(txtTim.getText()) || ctsp.getMoTa().contains(txtTim.getText())
                    ) {
                lst.add(ctsp);
            }
        }
        loadTableCtSanPham(lst);
    }//GEN-LAST:event_txtTimKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        int index = tblThuocTinh.getSelectedRow();
        txtTenThuocTinh.setText(tblThuocTinh.getValueAt(index, 2).toString());
        txtMa.setText(tblThuocTinh.getValueAt(index, 1).toString());

    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        int index = tblThuocTinh.getSelectedRow();
//        try {
//            MauSac mauSac = mauSacService.getAll().get(index);
//            mauSac.setTen(txtTenThuocTinh.getText());
//            mauSac.setMa(txtMa.getText());
//
//            if (mauSacService.update(mauSac) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblMau(mauSacService.getAll());
//
//                return;
//            }
//
//            Size size = sizeService.getAll().get(index);
//            size.setTen(txtTenThuocTinh.getText());
//            size.setMa(txtMa.getText());
//
//            if (sizeService.update(size) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblSize(sizeService.getAll());
//
//                return;
//            }
//
//            NSX nsx = nSXService.getAll().get(index);
//            nsx.setTen(txtTenThuocTinh.getText());
//            nsx.setMa(txtMa.getText());
//
//            if (nSXService.update(nsx) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblNSX(nSXService.getAll());
//
//                return;
//            }
//            DongSP dongSP = dongSPService.getAll().get(index);
//            dongSP.setTen(txtTenThuocTinh.getText());
//            dongSP.setMa(txtMa.getText());
//
//            if (dongSPService.update(dongSP) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblDongSP(dongSPService.getAll());
//
//                return;
//            }
//
//            SanPham sanPham = sanPhamService.getAll().get(index);
//            sanPham.setTen(txtTenThuocTinh.getText());
//            sanPham.setMa(txtMa.getText());
//
//            if (sanPhamService.update(sanPham) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblSanPham(sanPhamService.getAll());
//
//                return;
//            }
//            ChatLieu chatLieu = chatLieuService.getAll().get(index);
//            chatLieu.setTen(txtTenThuocTinh.getText());
//            chatLieu.setMa(txtMa.getText());
//
//            if (chatLieuService.update(chatLieu) == true) {
//                JOptionPane.showMessageDialog(this, "Sửa Thành công");
//                loadTblChatLieu(chatLieuService.getAll());
//
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int index = tblThuocTinh.getSelectedRow();
        if (rdoMau.isSelected() == true) {
            MauSac mauSac = mauSacService.getAll().get(index);
            mauSac.setMa(txtMa.getText());
            mauSac.setTen(txtTenThuocTinh.getText());
            if (mauSacService.update(mauSac)) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblMau(chiTietSpServiceImpl.getMauSac());
                
            }
        } else if (rdoNSX.isSelected() == true) {
            NSX nsx = nSXService.getAll().get(index);
            nsx.setMa(txtMa.getText());
            nsx.setTen(txtTenThuocTinh.getText());
            if (nSXService.update(nsx) == true) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblNSX(chiTietSpServiceImpl.getNsx());
            }
        }else if (rdoSize.isSelected() == true) {
            Size size = sizeService.getAll().get(index);
            size.setMa(txtMa.getText());
            size.setTen(txtTenThuocTinh.getText());
            if (sizeService.update(size) == true) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblSize(chiTietSpServiceImpl.getSize());
            }
        }else if (rdoDongSP.isSelected() == true) {
           DongSP dongSP = dongSPService.getAll().get(index);
            dongSP.setMa(txtMa.getText());
            dongSP.setTen(txtTenThuocTinh.getText());
            if (dongSPService.update(dongSP) == true) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblDongSP(chiTietSpServiceImpl.getDongSP());
            }
        }else if (rdoSanPham.isSelected() == true) {
            SanPham sanPham = sanPhamService.getAll().get(index);
            sanPham.setMa(txtMa.getText());
            sanPham.setTen(txtTenThuocTinh.getText());
            if (sanPhamService.update(sanPham) == true) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblSanPham(chiTietSpServiceImpl.getSanPham());
            }
        }else if (rboChatLieu.isSelected() == true) {
            ChatLieu chatLieu = chatLieuService.getAll().get(index);
            chatLieu.setMa(txtMa.getText());
            chatLieu.setTen(txtTenThuocTinh.getText());
            if (chatLieuService.update(chatLieu) == true) {
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadTblChatLieu(chiTietSpServiceImpl.getChatLieu());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuActionPerformed

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (Integer.parseInt(txtTu.getText()) <= 0 || Integer.parseInt(txtDen.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phải lớn hơn hoặc bằng 0");
                return;
            }
            if (Integer.parseInt(txtTu.getText()) > Integer.parseInt(txtDen.getText())) {
                JOptionPane.showMessageDialog(this, "Khoảng giá không hợp lệ!");
                return;
            }
            ArrayList<ChiTietSP> lst = new ArrayList<>();
            for (ChiTietSP ctsp : chiTietSpServiceImpl.getAllSanPhamLonHon0()) {
                if (ctsp.getGia() <= Integer.parseInt(txtDen.getText()) && ctsp.getGia() >= Integer.parseInt(txtTu.getText())) {
                    lst.add(ctsp);
                }
            }
            loadTableCtSanPham(lst);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ");
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQR;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JRadioButton rboChatLieu;
    private javax.swing.JRadioButton rdoDongSP;
    private javax.swing.JRadioButton rdoMau;
    private javax.swing.JRadioButton rdoNSX;
    private javax.swing.JRadioButton rdoSanPham;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JTable tblChiTietSp;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTu;
    // End of variables declaration//GEN-END:variables
}

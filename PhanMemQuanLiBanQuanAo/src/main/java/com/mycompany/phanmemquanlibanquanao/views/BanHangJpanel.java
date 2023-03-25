/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDonChiTiet;
import com.mycompany.phanmemquanlibanquanao.service.ChiTietSPService;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonChiTietService;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonService;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChiTietSPServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.HoaDonChiTietServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.HoaDonServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Giang
 */
public class BanHangJpanel extends javax.swing.JPanel {

    /**
     * Creates new form BanHangJpanel
     */
        private DefaultTableModel tableModel;
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    public BanHangJpanel() {
        initComponents();
                loadDataHoaDon(hoaDonService.getAll());
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(-1));
        lblMaHD.setText("");
    }
        private String getMa(List<HoaDon> list) {
        return "HD" + list.size();

    }

    private String getMaHd() {
        int row = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(row, 1).toString();
        return maHD;
    }

    private String fomartNgay(Date d) {
        return dateFormat.format(d);
    }

    private void loadDataHoaDon(List<HoaDon> list) {
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        int i = 1;
        String khachHang = "Khách lẻ";
        for (HoaDon hoaDon : list) {
            tableModel.addRow(new Object[]{
                i, hoaDon.getMaHoaDon(), khachHang,
                fomartNgay(hoaDon.getNgayTao()), hoaDon.htTrangThai()
            });
            i++;
        }
    }

    private void loadDataCtsp(List<ChiTietSP> list) {
        tableModel = (DefaultTableModel) tblCtsp.getModel();
        tableModel.setRowCount(0);
        int i = 1;
        for (ChiTietSP chiTietSp : list) {
            tableModel.addRow(new Object[]{
                i, chiTietSp.getMactsp(), chiTietSp.getSanPham().getTen(),
                chiTietSp.getDongSp().getTen(), chiTietSp.getMauSac().getTen(),
                chiTietSp.getChatLieu().getTen(), chiTietSp.getSize().getTen(),
                chiTietSp.getSoLuongTon(), chiTietSp.getGia()
            });
            i++;
        }
    }

    private void taoHd() {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(getMa(hoaDonService.getAll()));
        hd.setNgayTao(new Date());
//        hd.setNhanVien(UserLogin.getNhanVien());
        hd.setTrangThai(0);
        hd.setSdt("Không có");
        hd.setDiaChi("Không có");
        hd.setLyDo("Không có");
//        hd.setKhuyenMai(new KhuyenMai(1, "r", 0, new Date(), new Date()));
        if (hoaDonService.save(hd)) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!");
        }
        loadDataHoaDon(hoaDonService.getAll());
        rdoChoThanhToan.setSelected(true);
        tblHoaDon.setRowSelectionInterval(0, 0);
        lblMaHD.setText(getMaHd());
    }

    private int inputSoLuong() {
        try {
            int sl = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng"));
            return sl;
        } catch (Exception e) {

            return -1;
        }

    }

    private int getIdHd() {

        String maHD = lblMaHD.getText();
        int idHd = hoaDonService.getOneByMaHD(maHD).getId();
        return idHd;
    }

    private void loadDataGioHang(List<HoaDonChiTiet> list) {

        tableModel = (DefaultTableModel) tblHdct.getModel();
        tableModel.setRowCount(0);
        int i = 1;
        for (HoaDonChiTiet hdct : list) {
            tableModel.addRow(new Object[]{
                i, hdct.getChiTietSp().getMactsp(), hdct.getChiTietSp().getSanPham().getTen(),
                hdct.getChiTietSp().getMauSac().getTen(),
                hdct.getChiTietSp().getChatLieu().getTen(),
                hdct.getChiTietSp().getSize().getTen(), hdct.getSoLuong(), hdct.getDONGIA(), hdct.getSoLuong() * hdct.getDONGIA()});
            i++;
        }
    }

    private void themVaoGioHangQR(String maCtsp) {
     HoaDon hdd = hoaDonService.getOne(getIdHd());
        if (hdd.getTrangThai() == 1) {
            return;
        }
        boolean check = true;
        for (HoaDonChiTiet hdct : hoaDonChiTietService.getHdctByIdHD(getIdHd())) {
            if (hdct.getChiTietSp().getMactsp().equals(maCtsp)) {
                check = false;
            }
        }
        if (check) {
            ChiTietSP ctsp = chiTietSPService.getOneByMaCtsp(maCtsp);
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDonService.getOne(getIdHd()));

            hdct.setChiTietSp(ctsp);
            int sl = inputSoLuong();
            if (sl <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
                return;
            }
            if (sl > ctsp.getSoLuongTon()) {
                JOptionPane.showMessageDialog(this, "Hàng trong kho còn: " + ctsp.getSoLuongTon());
                return;
            }
            hdct.setSoLuong(sl);
            hdct.setDONGIA(ctsp.getGia());
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() - sl);
            hoaDonChiTietService.save(hdct);
            chiTietSPService.update(ctsp);
            loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
            loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
        } 
        else
        {
            ChiTietSP ctsp = chiTietSPService.getOneByMaCtsp(maCtsp);
            HoaDonChiTiet hdct = hoaDonChiTietService.getHdctByIdCtspAndIdHd(ctsp.getId(), getIdHd());
            int soL = inputSoLuong();
            if (soL < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
                return;
            }
            if (soL > ctsp.getSoLuongTon()) {
                JOptionPane.showMessageDialog(this, "Hàng trong kho còn: " + ctsp.getSoLuongTon());
                return;
            }

            hdct.setSoLuong(hdct.getSoLuong() + soL);
            hoaDonChiTietService.update(hdct);
            loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() - soL);
            chiTietSPService.update(ctsp);
            loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());

        }
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
    }
        private int getThanhTien() {
        int tien = 0;
        for (int i = 0; i < tblHdct.getRowCount(); i++) {
            tien += Integer.parseInt(tblHdct.getValueAt(i, 8).toString());
        }
        return tien;
    }
            private void thanhToan() {
        HoaDon hd = hoaDonService.getOneByMaHD(lblMaHD.getText());
        hd.setNgayThanhToan(new Date());
        hd.setHinhThucThanhToan(cboHinhThucThanhToan.getSelectedIndex());
        hd.setTongTien(getThanhTien());
//        hd.setKhachHang(khachHangService.getOne(1));
//        hd.setNhanVien(UserLogin.getNhanVien());
//        hd.setKhachHang(ChonKhachHang.getKhachHang());
        if (cboHinhThucThanhToan.getSelectedItem().toString().equalsIgnoreCase("Tiền mặt")) {
            hd.setTienKhachTra(getThanhTien());
//            try {
//                if (Integer.parseInt(txtKhachTra.getText()) < tienSauGiamGia()) {
//                    JOptionPane.showMessageDialog(this, "Tiền khách trả không đủ");
//                    return;
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Tiền khách trả không hợp lệ");
//                return;
//            }
        } else if (cboHinhThucThanhToan.getSelectedItem().toString().equalsIgnoreCase("Chuyển khoản")) {
            hd.setTienKhachChuyenKhoan(getThanhTien());
        } else {
            hd.setTienKhachTra(Integer.parseInt(txtKhachTra.getText()));
            hd.setTienKhachChuyenKhoan(Integer.parseInt(txtChuyenKhoan.getText()));
        }
        hd.setTrangThai(1);
        if (hoaDonService.update(hd)) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
//            loadDataHoaDon(hoaDonService.getHdWhere(1, UserLogin.getNhanVien().getId()));
            loadDataHoaDon(hoaDonService.getAll());
            rdoDaThanhToan.setSelected(true);
            btnThanhToan.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHdct = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCtsp = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblTenKH = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblMaGiamGia = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblMaKH = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Giỏ hàng");

        tblHdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Màu sắc", "Chất liệu", "Size", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblHdct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHdctMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHdct);

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setText("Làm mới giỏ hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel1.setText("Danh sách hóa đơn");

        rdoTatCa.setBackground(new java.awt.Color(255, 255, 255));
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTatCaMouseClicked(evt);
            }
        });

        rdoChoThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        rdoChoThanhToan.setText("Chờ thanh toán");
        rdoChoThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChoThanhToanMouseClicked(evt);
            }
        });

        rdoDaThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        rdoDaThanhToan.setText("Đã thanh toán");
        rdoDaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDaThanhToanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(rdoTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoChoThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDaThanhToan)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoChoThanhToan)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Danh sách sản phẩm");

        tblCtsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Loại", "Màu sắc", "Chất liệu", "Size", "Số lượng", "Đơn giá"
            }
        ));
        tblCtsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCtspMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCtsp);

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel4.setText("Tìm kiếm");

        jLabel15.setText("Giá từ");

        txtTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKeyReleased(evt);
            }
        });

        jLabel16.setText("Đến");

        txtDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenKeyReleased(evt);
            }
        });

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(39, 39, 39)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenKH.setText("Tên khách hàng: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mã hóa đơn: ");

        lblMaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaHD.setText("1234");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thành tiền:");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThanhTien.setText("0 đ");

        btnThanhToan.setBackground(new java.awt.Color(153, 255, 153));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã giảm giá");

        jLabel8.setText("Giảm giá KM:");

        txtGiamGia.setEditable(false);
        txtGiamGia.setText("0");

        jLabel9.setText("Tiền thừa");

        txtTienThua.setEditable(false);

        jLabel11.setText("Hình thức");

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cboHinhThucThanhToan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHinhThucThanhToanItemStateChanged(evt);
            }
        });

        jLabel12.setText("Khách trả");

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        jLabel13.setText("Chuyển khoản");

        txtChuyenKhoan.setEditable(false);

        jButton2.setText("Chọn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblMaGiamGia.setText("Mã");

        jLabel10.setText("Khách");

        jButton5.setText("Chọn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lblMaKH.setText("Lẻ");

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 51, 51));
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiamGia)
                            .addComponent(cboHinhThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblMaGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtKhachTra)
                            .addComponent(txtChuyenKhoan)
                            .addComponent(txtTienThua)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2)
                    .addComponent(lblMaGiamGia))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton5)
                    .addComponent(lblMaKH))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblThanhTien))
                .addGap(118, 118, 118)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHdctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHdctMouseClicked
        int row = tblHdct.getSelectedRow();
        String maCtsp = tblHdct.getValueAt(row, 1).toString();
        ChiTietSP ctsp = chiTietSPService.getOneByMaCtsp(maCtsp);
        HoaDonChiTiet hdct = hoaDonChiTietService.getHdctByIdCtspAndIdHd(chiTietSPService.getOneByMaCtsp(maCtsp).getId(), getIdHd());
        int sl = inputSoLuong();
        if (sl < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            return;
        }

        if (sl > hdct.getSoLuong()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng của bạn chỉ có " + hdct.getSoLuong() + " sản phẩm");
            return;
        }
        ctsp.setSoLuongTon(ctsp.getSoLuongTon() + sl);
        hdct.setSoLuong(hdct.getSoLuong() - sl);
        if (hdct.getSoLuong() == 0) {
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuong());
            hoaDonChiTietService.delete(hdct);
        }
        chiTietSPService.update(ctsp);
        hoaDonChiTietService.update(hdct);
//        lblThanhTien.setText(getThanhTien() + " đ");
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());          // TODO add your handling code here:
    }//GEN-LAST:event_tblHdctMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
                lblMaHD.setText(getMaHd() + "");
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
        lblThanhTien.setText(getThanhTien() + " đ");
        HoaDon hd = hoaDonService.getOneByMaHD(getMaHd());
//        ChonKhachHang.setKhachHang(hd.getKhachHang());
        if (hd.getTrangThai() == 1) {
            btnThanhToan.setEnabled(false);
//            tblCtsp.setEnabled(false);
//            tblHdct.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
//            tblCtsp.setEnabled(true);
//            tblHdct.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void rdoTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTatCaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTatCaMouseClicked

    private void rdoChoThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChoThanhToanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChoThanhToanMouseClicked

    private void rdoDaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDaThanhToanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDaThanhToanMouseClicked

    private void tblCtspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCtspMouseClicked
        if (lblMaHD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn!");
            return;
        }
        String maCtsp = tblCtsp.getValueAt(tblCtsp.getSelectedRow(), 1).toString();
        txtTim.setText(maCtsp);

        themVaoGioHangQR(maCtsp);
        lblThanhTien.setText(getThanhTien() + " đ");          // TODO add your handling code here:
    }//GEN-LAST:event_tblCtspMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (lblMaHD.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được chọn!");
            return;
        }
        if (tblHdct.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có gì trong giỏ hàng!");
            return;
        }
        thanhToan();        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cboHinhThucThanhToanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucThanhToanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboHinhThucThanhToanItemStateChanged

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
                if (cboHinhThucThanhToan.getSelectedIndex() == 0) {
            txtTienThua.setText((Integer.parseInt(txtKhachTra.getText()) - getThanhTien()) + "");
        } else if (cboHinhThucThanhToan.getSelectedIndex() == 2) {
            txtChuyenKhoan.setText((getThanhTien() - Integer.parseInt(txtKhachTra.getText())) + "");
        } else {
            txtKhachTra.setEditable(false); //Chuyển khoản
            txtKhachTra.setText("");
            txtChuyenKhoan.setText(getThanhTien() + "");
            txtTienThua.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
taoHd();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMaGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblCtsp;
    private javax.swing.JTable tblHdct;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTu;
    // End of variables declaration//GEN-END:variables
}

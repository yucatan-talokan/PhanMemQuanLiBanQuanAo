/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChonKH;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDonChiTiet;
import com.mycompany.phanmemquanlibanquanao.domainmodels.UserLogin;
import com.mycompany.phanmemquanlibanquanao.service.ChiTietSPService;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonChiTietService;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonService;
import com.mycompany.phanmemquanlibanquanao.service.KhachHangService;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChiTietSPServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.HoaDonChiTietServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.HoaDonServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.KhachHangServiceImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Giang
 */
public class BanHangJpanel extends javax.swing.JPanel implements Runnable, ThreadFactory, Serializable {

    /**
     * Creates new form test
     */
    private DefaultTableModel tableModel;
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    SanPhamJpanel chiTietSpJpanel;
    private WebcamPanel panel = null;
    public Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private KhachHangService khachHangService;

    public BanHangJpanel() {
        initComponents();
        khachHangService = new KhachHangServiceImpl();
        loadDataHoaDon(hoaDonService.getHoaDonByIdNV(UserLogin.getNhanVien().getId()));
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(-1));
        lblMaHD.setText("");
        autoKhuyenMai();

    }

    public void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jplCam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 207, 200));
        executor.execute((Runnable) this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.open()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                ex.printStackTrace();
            }
            if (result != null) {
                txtTim.setText(result.getText());
                if (!lblMaHD.getText().equals("")) {
                    themVaoGioHangQR(result.getText());
                }
                lblThanhTien.setText(getThanhTien() + " đ");
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
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
        for (HoaDon hoaDon : list) {
            tableModel.addRow(new Object[]{
                i, hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getTen(),
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
        hd.setKhachHang(khachHangService.getOne(1));
        hd.setMaHoaDon(getMa(hoaDonService.getAll()));
        hd.setNgayTao(new Date());
        hd.setNhanVien(UserLogin.getNhanVien());
        hd.setTrangThai(0);
        hd.setSdt("Không có");
        hd.setDiaChi("Không có");
        hd.setLyDo("Không có");
        hd.setKhachHang(ChonKH.getKhachHang());
//        hd.setKhuyenMai(new KhuyenMai(1, "r", 0, new Date(), new Date()));
        if (hoaDonService.save(hd)) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!");
        }
        loadDataHoaDon(hoaDonService.getHdWhere(0, UserLogin.getNhanVien().getId()));
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
        } else {
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
        hd.setKhachHang(khachHangService.getOne(1));
        hd.setNhanVien(UserLogin.getNhanVien());
        hd.setKhachHang(ChonKH.getKhachHang());
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
            loadDataHoaDon(hoaDonService.getHdWhere(1, UserLogin.getNhanVien().getId()));
            rdoDaThanhToan.setSelected(true);
            btnThanhToan.setEnabled(false);
        }
    }

    private void autoKhuyenMai() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    loadKhachHangKhuyenMai();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BanHangJpanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private void loadKhachHangKhuyenMai() {

        if (ChonKH.getKhachHang().getId() == 1) {
            lblTenKH.setText("");
            lblMaKH.setText("Khách lẻ");
        } else {
            if (ChonKH.getKhachHang() == null) {
                lblTenKH.setText("");
                lblMaKH.setText("Khách lẻ");
            } else {
                lblTenKH.setText("Tên khách hàng: " + ChonKH.getKhachHang().getTen());
                lblMaKH.setText(ChonKH.getKhachHang().getMa());

            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblTenKH = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblMaKH = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHdct = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        jplCam = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(102, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenKH.setText("Tên khách hàng: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mã hóa đơn: ");

        lblMaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaHD.setText("123");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thành tiền:");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThanhTien.setText("0 đ");

        btnThanhToan.setBackground(new java.awt.Color(255, 0, 0));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

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

        jLabel10.setText("Khách");

        jButton5.setText("Chọn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lblMaKH.setText("Lẻ");

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 0, 0));
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboHinhThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKhachTra)
                            .addComponent(txtChuyenKhoan)
                            .addComponent(txtTienThua)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE))
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton5)
                    .addComponent(lblMaKH))
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblThanhTien))
                .addGap(109, 109, 109)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHdctMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblHdct);

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("Làm mới giỏ hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTatCaMouseClicked(evt);
            }
        });
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        rdoChoThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoChoThanhToan);
        rdoChoThanhToan.setText("Chờ thanh toán");
        rdoChoThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChoThanhToanMouseClicked(evt);
            }
        });

        rdoDaThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setText("Đã thanh toán");
        rdoDaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDaThanhToanMouseClicked(evt);
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
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(rdoTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoChoThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDaThanhToan)
                        .addContainerGap(159, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoChoThanhToan)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jplCam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jplCam, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jplCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCtspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCtspMouseClicked
        if (lblMaHD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng tạo hoặc chọn hóa đơn!");
            return;
        }
        String maCtsp = tblCtsp.getValueAt(tblCtsp.getSelectedRow(), 1).toString();
        txtTim.setText(maCtsp);

        themVaoGioHangQR(maCtsp);
        lblThanhTien.setText(getThanhTien() + " đ");        // TODO add your handling code here:
    }//GEN-LAST:event_tblCtspMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        txtTu.setText("");
        txtDen.setText("");
        ArrayList<ChiTietSP> lst = new ArrayList<>();
        for (ChiTietSP ctsp : chiTietSPService.getAll()) {
            if (ctsp.getMactsp().contains(txtTim.getText()) || ctsp.getSanPham().getTen().contains(txtTim.getText())
                    || ctsp.getDongSp().getTen().contains(txtTim.getText()) || ctsp.getNsx().getTen().contains(txtTim.getText())
                    || ctsp.getChatLieu().getTen().contains(txtTim.getText()) || ctsp.getMauSac().getTen().contains(txtTim.getText())
                    || ctsp.getSize().getTen().contains(txtTim.getText())) {
                lst.add(ctsp);
            }
        }
        loadDataCtsp(lst);        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            for (ChiTietSP ctsp : chiTietSPService.getAllSanPhamLonHon0()) {
                if (ctsp.getGia() <= Integer.parseInt(txtDen.getText()) && ctsp.getGia() >= Integer.parseInt(txtTu.getText())) {
                    lst.add(ctsp);
                }
            }
            loadDataCtsp(lst);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ");
            return;
        }        // TODO add your handling code here:
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

        thanhToan();
        ChonKH.setKhachHang(khachHangService.getOne(1));
        //        int chon = JOptionPane.showConfirmDialog(this, "In hóa đơn", "Xác nhận", JOptionPane.YES_NO_OPTION);
        //        if (chon == JOptionPane.YES_OPTION) {
        //            exportBill();
        //        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cboHinhThucThanhToanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucThanhToanItemStateChanged
        if (cboHinhThucThanhToan.getSelectedIndex() == 0) {
            txtKhachTra.setEditable(true);//tiền trao cháo múc
            txtKhachTra.setText("");
            txtChuyenKhoan.setText("");
            txtTienThua.setText("");
        } else if (cboHinhThucThanhToan.getSelectedIndex() == 2) {
            txtKhachTra.setEditable(true); //kết hợp
            txtKhachTra.setText("");
            txtChuyenKhoan.setText("");
            txtTienThua.setText("");
        } else {
            txtKhachTra.setEditable(false); //Chuyển khoản
            txtKhachTra.setText("");
            txtChuyenKhoan.setText(getThanhTien() + "");
            txtTienThua.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cboHinhThucThanhToanItemStateChanged

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        // TODO add your handling code here:
        if (cboHinhThucThanhToan.getSelectedIndex() == 0) {
            txtTienThua.setText((Integer.parseInt(txtKhachTra.getText()) - getThanhTien()) + "");
        } else if (cboHinhThucThanhToan.getSelectedIndex() == 2) {
            txtChuyenKhoan.setText((getThanhTien() - Integer.parseInt(txtKhachTra.getText())) + "");
        } else {
            txtKhachTra.setEditable(false); //Chuyển khoản
            txtKhachTra.setText("");
            txtChuyenKhoan.setText(getThanhTien() + "");
            txtTienThua.setText("");
        }
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new KhachHangJfame().setVisible(true);      // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        //loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(-1));
        lblMaHD.setText("");
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(-1));
        loadDataHoaDon(hoaDonService.getHdWhere(0, UserLogin.getNhanVien().getId()));
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
        rdoChoThanhToan.setSelected(true);
        lblThanhTien.setText("0 đ");
        taoHd();
        btnThanhToan.setEnabled(true);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

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
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() - (sl - hdct.getSoLuong()));
//            return;
        } else {
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() + (hdct.getSoLuong() - sl));
        }

        hdct.setSoLuong(sl);
        if (hdct.getSoLuong() == 0) {
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hdct.getSoLuong());
            hoaDonChiTietService.delete(hdct);
        }
        chiTietSPService.update(ctsp);
        hoaDonChiTietService.update(hdct);

        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
        loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
        lblThanhTien.setText(getThanhTien() + " đ");
// TODO add your handling code here:
    }//GEN-LAST:event_tblHdctMouseClicked

    private void tblHdctMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHdctMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHdctMouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int xacNhan = JOptionPane.showConfirmDialog(this, "Xóa giỏ hàng?");
            if (xacNhan == JOptionPane.YES_OPTION) {

                for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietService.getHdctByIdHD(getIdHd())) {
                    ChiTietSP ctsp = chiTietSPService.getOneByMaCtsp(hoaDonChiTiet.getChiTietSp().getMactsp());
                    ctsp.setSoLuongTon(ctsp.getSoLuongTon() + hoaDonChiTiet.getSoLuong());
                    hoaDonChiTietService.delete(hoaDonChiTiet);
                    chiTietSPService.update(ctsp);
                }
                loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
                loadDataCtsp(chiTietSPService.getAllSanPhamLonHon0());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblThanhTien.setText(getThanhTien() + " đ");
        chiTietSPService = new ChiTietSPServiceImpl();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        lblMaHD.setText(getMaHd() + "");
        loadDataGioHang(hoaDonChiTietService.getHdctByIdHD(getIdHd()));
        lblThanhTien.setText(getThanhTien() + " đ");
        HoaDon hd = hoaDonService.getOneByMaHD(getMaHd());
        ChonKH.setKhachHang(hd.getKhachHang());
        if (hd.getTrangThai() == 1) {
            btnThanhToan.setEnabled(false);
            //            tblCtsp.setEnabled(false);
            //            tblHdct.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
            //            tblCtsp.setEnabled(true);
            //            tblHdct.setEnabled(true);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void rdoTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTatCaMouseClicked
        // TODO add your handling code here:
        loadDataHoaDon(hoaDonService.getHoaDonByIdNV(UserLogin.getNhanVien().getId()));
    }//GEN-LAST:event_rdoTatCaMouseClicked

    private void rdoChoThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChoThanhToanMouseClicked
        loadDataHoaDon(hoaDonService.getHdWhere(0, UserLogin.getNhanVien().getId()));        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChoThanhToanMouseClicked

    private void rdoDaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDaThanhToanMouseClicked
        // TODO add your handling code here:
        loadDataHoaDon(hoaDonService.getHdWhere(1, UserLogin.getNhanVien().getId()));
    }//GEN-LAST:event_rdoDaThanhToanMouseClicked

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTatCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jplCam;
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
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTu;
    // End of variables declaration//GEN-END:variables
}

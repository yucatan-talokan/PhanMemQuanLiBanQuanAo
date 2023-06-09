/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.views;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import com.mycompany.phanmemquanlibanquanao.repository.ChiTietSPRepository;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonService;
import com.mycompany.phanmemquanlibanquanao.service.impl.ChiTietSPServiceImpl;
import com.mycompany.phanmemquanlibanquanao.service.impl.HoaDonServiceImpl;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Thanh Giang
 */
public class ThongKeJpanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeJpanel
     */
    private ChiTietSPRepository chiTietSpRepository = new ChiTietSPRepository();
    private ChiTietSPServiceImpl chiTietSpServiceImpl = new ChiTietSPServiceImpl();
    private DefaultTableModel model;
    private HoaDonService hoaDonService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeJpanel() {
        initComponents();
        loadTable(chiTietSpRepository.getAll());
        hoaDonService = new HoaDonServiceImpl();
        txtTongDonHang1.setText(getTongDonHang() + "");
        txtThanhCong1.setText(getTongDonHangThanhCong() + "");
        bieuDoThang();
    }

    public void loadTable(ArrayList<ChiTietSP> list) {
        model = (DefaultTableModel) tblThongKeSp.getModel();
        model.setRowCount(0);
        int i = 1;
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Sản Phẩm", "Sản phẩm", "Dòng sản phẩm", "Đơn giá", "Màu sắc", "Chất liệu", "Size", "Nsx", "soluongton"
        });
        for (ChiTietSP chiTietSanPham : list) {

            Object[] row = new Object[]{
                i++,
                chiTietSanPham.getMactsp(),
                chiTietSanPham.getSanPham().getTen(), chiTietSanPham.getDongSp().getTen(),
                chiTietSanPham.getGia(),
                chiTietSanPham.getMauSac().getTen(), chiTietSanPham.getChatLieu().getTen(),
                chiTietSanPham.getSize().getTen(), chiTietSanPham.getNsx().getTen(), chiTietSanPham.getSoLuongTon()
            };
            model.addRow(row);
        }
    }

    private int getTongDonHang() {
        int i = 0;
        for (HoaDon hoaDon : hoaDonService.getAll()) {
            if (hoaDon.getTrangThai() == 1 || hoaDon.getTrangThai() == 2
                    || hoaDon.getTrangThai() == 3 || hoaDon.getTrangThai() == 4
                    || hoaDon.getTrangThai() == 5 || hoaDon.getTrangThai() == 0) {
                i++;
            }
        }
        return i;
    }

    private int getTongDonHangThanhCong() {
        int i = 0;
        for (HoaDon hoaDon : hoaDonService.getAll()) {
            if (hoaDon.getTrangThai() == 1 || hoaDon.getTrangThai() == 4) {
                i++;
            }
        }
        return i;
    }

    private int getTongDonHangBiHuy() {
        int i = 0;
        for (HoaDon hoaDon : hoaDonService.getAll()) {
            if (hoaDon.getTrangThai() == 2) {
                i++;
            }
        }
        return i;
    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }
    private void bieuDoThang(){
     List<Long> a1 = chiTietSpRepository.ThongKeThang1(1, 2023);
        double val1 = 0;
        for (Long double1 : a1) {
            if (double1 == null) {
                val1 = 0;
            } else {
                val1 = double1;
            }
        }
        //2
        List<Long> a2 = chiTietSpRepository.ThongKeThang2(2, 2023);
        double val2 = 0;
        for (Long double1 : a2) {
            if (double1 == null) {
                val2 = 0;
            } else {
                val2 = double1;
            }

        }
        //3
        List<Long> a3 = chiTietSpRepository.ThongKeThang3(3, 2023);
        double val3 = 0;
        for (Long double1 : a3) {
            if (double1 == null) {
                val3 = 0;
            } else {
                val3 = double1;
            }
        }
        //4
        List<Long> a4 = chiTietSpRepository.ThongKeThang4(4, 2023);
        double val4 = 0;
        for (Long double1 : a4) {
            if (double1 == null) {
                val4 = 0;
            } else {
                val4 = double1;
            }
        }
        //5
        List<Long> a5 = chiTietSpRepository.ThongKeThang5(5, 2023);
        double val5 = 0;
        for (Long double1 : a5) {
            if (double1 == null) {
                val5 = 0;
            } else {
                val5 = double1;
            }
        }
        //6
        List<Long> a6 = chiTietSpRepository.ThongKeThang6(6, 2023);
        double val6 = 0;
        for (Long double1 : a6) {
            if (double1 == null) {
                val6 = 0;
            } else {
                val6 = double1;
            }
        }
        //7
        List<Long> a7 = chiTietSpRepository.ThongKeThang7(7, 2023);
        double val7 = 0;
        for (Long double1 : a7) {
            if (double1 == null) {
                val7 = 0;
            } else {
                val7 = double1;
            }
        }
        //8
        List<Long> a8 = chiTietSpRepository.ThongKeThang8(8, 2023);
        double val8 = 0;
        for (Long double1 : a8) {
            if (double1 == null) {
                val8 = 0;
            } else {
                val8 = double1;
            }
        }
        //9
        List<Long> a9 = chiTietSpRepository.ThongKeThang9(9, 2023);
        double val9 = 0;
        for (Long double1 : a9) {
            if (double1 == null) {
                val9 = 0;
            } else {
                val9 = double1;
            }
        }
        //10
        List<Long> a10 = chiTietSpRepository.ThongKeThang10(10, 2023);
        double val10 = 0;
        for (Long double1 : a10) {
            if (double1 == null) {
                val10 = 0;
            } else {
                val10 = double1;
            }
        }//11
        List<Long> a11 = chiTietSpRepository.ThongKeThang11(11, 2023);
        double val11 = 0;
        for (Long double1 : a11) {
            if (double1 == null) {
                val11 = 0;
            } else {
                val11 = double1;
            }
        }
        //12
        List<Long> a12 = chiTietSpRepository.ThongKeThang12(12, 2023);
        double val12 = 0;
        for (Long double1 : a12) {
            if (double1 == null) {
                val12 = 0;
            } else {
                val12 = double1;
            }
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //int val1 = 20000;
//        int val2 = 100000;
//        int val3 = 10;
//        int val4 = 15;
//        // int val5 = 25;
//        int val6 = 20;
//        int val7 = 25;
//        int val8 = 5;
//        int val9 = 30;
//        int val10 = 35000;
//        int val11= 25;
//        int val12 = 30;
        dataset.setValue(val1, "", " 1");
        dataset.setValue(val2, "", " 2");
        dataset.setValue(val3, "", " 3");
        dataset.setValue(val4, "", " 4");
        dataset.setValue(val5, "", " 5");
        dataset.setValue(val6, "", " 6");
        dataset.setValue(val7, "", " 7");
        dataset.setValue(val8, "", " 8");
        dataset.setValue(val9, "", " 9");
        dataset.setValue(val10, "", " 10");
        dataset.setValue(val11, "", " 11");
        dataset.setValue(val12, "", " 12");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ TỔNG TIỀN THÁNG " + "", "", "TỔNG TIỀN(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        pan2.setLayout(new java.awt.BorderLayout());
        pan2.removeAll();
        pan2.add(oPanel);
        pan2.validate();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTongDonHang1 = new javax.swing.JTextField();
        txtThanhCong1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDtNgay = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDtThang = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNam = new javax.swing.JTextField();
        txtNgayBatdau = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        pan2 = new javax.swing.JPanel();
        rdoThang = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeSp = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btlTKDoanhThu = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel8.setText("Tổng đơn hàng");

        jLabel2.setText("Thành công");

        txtThanhCong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhCong1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThanhCong1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongDonHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongDonHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtThanhCong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jLabel3.setText("Tổng tiền theo ngày");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtDtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtDtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel4.setText("Tổng tiền theo tháng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtDtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtDtThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 255, 153));

        jLabel5.setText("Tổng tiền theo năm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(102, 255, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonGroup1.add(rdoThang);
        rdoThang.setSelected(true);
        rdoThang.setText("Tháng");
        rdoThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoThangMouseClicked(evt);
            }
        });
        rdoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThangActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Năm");
        rdoNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNamMouseClicked(evt);
            }
        });
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThang)
                    .addComponent(rdoNam))
                .addGap(94, 94, 94)
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(rdoThang)
                .addGap(31, 31, 31)
                .addComponent(rdoNam)
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh thu", jPanel10);

        jPanel11.setBackground(new java.awt.Color(153, 255, 153));

        tblThongKeSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblThongKeSp);

        jButton2.setText("Xuất file");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm sản phẩm");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(282, 282, 282))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel11);

        btlTKDoanhThu.setText("Tìm kiếm");
        btlTKDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlTKDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btlTKDoanhThu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlTKDoanhThu))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoThangMouseClicked
bieuDoThang();        // TODO add your handling code here:

       
    }//GEN-LAST:event_rdoThangMouseClicked

    private void rdoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoThangActionPerformed

    private void rdoNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNamMouseClicked
        // TODO add your handling code here:

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        //        List<Long> a = chiTietSpRepository.ThongKeNam1(2021);
//        //        double
//        //        for (Long double1 : a) {
//            //            val1=double1;
//            //        }
//        //        int val1 =0;
        List<Long> b = chiTietSpRepository.ThongKeNam1(2023);
        double val2 = 0;
        for (Long double2 : b) {
            val2 = double2;
        }
//        //        List<Long> c = chiTietSpRepository.ThongKeNam1(2023);
//        //        double val3 =0;
//        //        for (Long double3 : c) {
//            //            val3=double3;
//            //        }
//        //        List<Long> d = chiTietSpRepository.ThongKeNam1(2024);
//        //        double val4 =0;
//        //        for (Long double4 : d) {
//            //            val4=double4;
//            //        }
        int val1 = 0;
        //int val2 = 5;
        int val3 = 0;
        int val4 = 0;
        dataset.setValue(val1, "", "");
        dataset.setValue(val2, "", "NĂM 2023");
        dataset.setValue(val3, "", "NĂM 2024");
        dataset.setValue(val4, "", "");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ TỔNG TIỀN NĂM " + "", "", "TỔNG TIỀN(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        pan2.setLayout(new java.awt.BorderLayout());
        pan2.removeAll();
        pan2.add(oPanel);
        pan2.validate();

        //int val2 = 10;
        //int val3 = 15;
        //int val4 = 20;
    }//GEN-LAST:event_rdoNamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String CurentDirectoryFilePath = "D:\\test";
        JFileChooser execlExportChooser = new JFileChooser(CurentDirectoryFilePath);
        FileNameExtensionFilter excelFNEF = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        execlExportChooser.setFileFilter(excelFNEF);
        execlExportChooser.setDialogTitle("Save excel... ");

        int excelChooser = execlExportChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook exceSSFWorkbookExprort = new XSSFWorkbook();
            XSSFSheet excelSheet = exceSSFWorkbookExprort.createSheet("Danh Sach San Pham");
            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow excelRow = excelSheet.createRow(i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell excelCell = excelRow.createCell(j);
                    excelCell.setCellValue(model.getValueAt(i, j).toString());
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
            } catch (Exception e) {
            }
        }
        JOptionPane.showMessageDialog(this, "Xuất file thành công");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        ArrayList<ChiTietSP> list = new ArrayList<>();
        for (ChiTietSP ctsp : chiTietSpRepository.getAll()) {
            if (ctsp.getSanPham().getTen().contains(txtTim.getText())) {
                list.add(ctsp);
            }
        }
        loadTable(list);
    }//GEN-LAST:event_txtTimKeyReleased

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        chiTietSpRepository = new ChiTietSPRepository();
        loadTable(chiTietSpRepository.getAll());
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtThanhCong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhCong1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhCong1ActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btlTKDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlTKDoanhThuActionPerformed
        // TODO add your handling code here:
        int day = (txtNgayBatdau.getDate().getDate());
        int thang = (txtNgayBatdau.getDate().getMonth() + 1);
        int nam = (txtNgayBatdau.getDate().getYear() + 1900);
        txtDtNgay.setText(String.valueOf(chiTietSpRepository.ThongKeTong(day, thang, nam)));
        txtDtThang.setText(String.valueOf(chiTietSpRepository.ThongKeTong(day, day, day)));
        txtDtThang.setText(String.valueOf(chiTietSpRepository.ThongKeThan(thang, nam)));
        txtNam.setText(String.valueOf(chiTietSpRepository.ThongKeNam(nam)));
        System.out.println(day + "/" + thang + "/" + nam);
        if (txtDtNgay.getText().equals("null")) {
            txtDtNgay.setText("0");
        }
        if (txtDtThang.getText().equals("null")) {
            txtDtThang.setText("0");
        }
        if (txtNam.getText().equals("null")) {
            txtNam.setText("0");
        }
    }//GEN-LAST:event_btlTKDoanhThuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlTKDoanhThu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pan2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoThang;
    private javax.swing.JTable tblThongKeSp;
    private javax.swing.JTextField txtDtNgay;
    private javax.swing.JTextField txtDtThang;
    private javax.swing.JTextField txtNam;
    private com.toedter.calendar.JDateChooser txtNgayBatdau;
    private javax.swing.JTextField txtThanhCong1;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongDonHang1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.config;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.KhachHang;
import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Thanh Giang
 */
public class HibernateConfig {
        private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QUANLYBANQUANAON8");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");
        properties.put(Environment.SHOW_SQL, "true");
        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPham.class);
//        conf.addAnnotatedClass(ChiTietSp.class);    
        conf.addAnnotatedClass(DongSP.class);
        conf.addAnnotatedClass(KhuyenMai.class);
       conf.addAnnotatedClass(ChatLieu.class);
//        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(Size.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(MauSac.class);
//        conf.addAnnotatedClass(HoaDon.class);
//        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhachHang.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}

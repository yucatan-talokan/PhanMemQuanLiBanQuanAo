/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.KhachHang;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thanh Giang
 */
public class KhachHangRepository {
    private Session session = HibernateConfig.getFACTORY().openSession();
    private String fromTable = "From KhachHang ";

    public List<KhachHang> getAll() {
        Query query = session.createQuery(fromTable + " where Makhachhang !='KH0'");
        return query.getResultList();
    }

    public Boolean add(KhachHang KhachHang) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.save(KhachHang);
            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Thanh Giang
 */
public class NhanVienRepository {
     private Session session = HibernateConfig.getFACTORY().openSession();
    
    private String fromTable = "FROM NhanVien";
    
    public List<NhanVien> getAll(){
        Query query = session.createQuery(fromTable, NhanVien.class);
        return  query.getResultList();
    }
     public Boolean add(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
      public Boolean Update(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
       public Boolean delete(NhanVien nhanVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
         public NhanVien getOne(Integer id){
        String sql  = fromTable = "where manv =:id";
        javax.persistence.Query query = session.createQuery(sql, NhanVien.class);
        query.setParameter("id", id);
        return (NhanVien) query.getSingleResult();
    }
}

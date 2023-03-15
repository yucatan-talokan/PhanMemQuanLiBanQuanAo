/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Thanh Giang
 */
public class MauSacRepository {
     private Session session = HibernateConfig.getFACTORY().openSession();
    
    private String fromTable = "FROM MauSac";
    
    public List<MauSac> getAll(){
        Query query = session.createQuery(fromTable, MauSac.class);
        return  query.getResultList();
    }
 
     public Boolean add(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
    
     public Boolean Update(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
     
     public Boolean delete(MauSac mauSac) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
}

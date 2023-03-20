/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Thanh Giang
 */
public class NSXRepository {
     private Session session = HibernateConfig.getFACTORY().openSession();
    
    private String fromTable = "FROM NSX";
    
    public List<NSX> getAll(){
        Query query = session.createQuery(fromTable, NSX.class);
        return  query.getResultList();
    }
 
     public Boolean add(NSX nsx) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
    
  
     
     public Boolean delete(NSX nsx) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;

    }
}

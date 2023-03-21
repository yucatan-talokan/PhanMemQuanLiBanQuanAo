/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thanh Giang
 */
public class ChiTietSPRepository {
    private Session session = HibernateConfig.getFACTORY().openSession();
    public ArrayList<ChiTietSP> getAll() {
        Query query = session.createQuery(fromTable+" order by id desc");
        return (ArrayList<ChiTietSP>) query.getResultList();
    }
    
    public ArrayList<ChiTietSP> getAllSanPhamLonHon0() {
        Query query = session.createQuery(fromTable+" where soLuongTon >0");
        return (ArrayList<ChiTietSP>) query.getResultList();
    }

    private String fromTable = "From ChiTietSP";
    private String fromMauSac = "From MauSac";
        public List<MauSac> getMauSac() {
        Query query = session.createQuery(fromMauSac);
        return query.getResultList();
    }
        
    private String fromSize = "From Size";
        public List<Size> getSize() {
        Query query = session.createQuery(fromSize);
        return query.getResultList();
    }
        
        private String fromNSX = "From NSX";
        public List<NSX> getNSX() {
        Query query = session.createQuery(fromNSX);
        return query.getResultList();
    }  
        private String fromDongSP = "From DongSP";
        public List<DongSP> getDongSP() {
        Query query = session.createQuery(fromDongSP);
        return query.getResultList();
    }
            private String fromChatLieu = "From ChatLieu";
        public List<ChatLieu> getChatLieu() {
        Query query = session.createQuery(fromChatLieu);
        return query.getResultList();
    }
             private String fromSanPham = "From SanPham";
        public List<SanPham> getSanPham() {
        Query query = session.createQuery(fromSanPham);
        return query.getResultList();
    }
        public Boolean add(ChiTietSP chiTietSp) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {

            transaction = session.beginTransaction();
            session.save(chiTietSp);
            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        public Boolean update(ChiTietSP chiTietSp) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSp);
            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean delete(ChiTietSP chiTietSp) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {

            transaction = session.beginTransaction();
            session.delete(chiTietSp);
            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 public ChiTietSP getOne(String id) {
        String sql = fromTable + " where MACTSP=:id";
        Query query = session.createQuery(sql, ChiTietSP.class);
        query.setParameter("id", id);
        return (ChiTietSP) query.getSingleResult();
    }

}

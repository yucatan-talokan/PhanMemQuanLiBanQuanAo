/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Thanh Giang
 */
public class HoaDonRepository {
        private Session session = HibernateConfig.getFACTORY().openSession();

    private String fromTable = "FROM HoaDon";

    public List<HoaDon> getAll() {
        Query query = session.createQuery(fromTable + " order by id desc", HoaDon.class);
        return query.getResultList();
    }

    public List<HoaDon> getHdWhere(int tt, int id) {
        Query query = session.createQuery(fromTable + "  where trangThai =:tt and idNhanVien = :id order by id desc", HoaDon.class);
        query.setParameter("tt", tt);
        query.setParameter("id", id);
        return query.getResultList();
    }
    public List<HoaDon>searchByComboBoxNoJoin(String kind,String txt){
        Query query=session.createQuery( "FROM HoaDon where "+kind+" =:txt order by id desc",HoaDon.class);
        query.setParameter("txt", txt);
        System.out.println("Câu HQL SEARCH: FROM HoaDon where "+kind+" =:txt order by id desc");
        return query.getResultList();
    }
    
    public List<HoaDon> getLichSu(int tt) {
        Query query = session.createQuery(fromTable + "  where trangThai =:tt order by id desc", HoaDon.class);
        query.setParameter("tt", tt);
        return query.getResultList();
    }

    public Boolean save(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hoaDon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public HoaDon getOne(int id) {
        String sql = fromTable + " where id=:id";
        Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("id", id);
        return (HoaDon) query.getSingleResult();
    }

    public HoaDon getOneByMaHD(String maHoaDon) {
        String sql = fromTable + " where maHoaDon=:maHoaDon";
        Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("maHoaDon", maHoaDon);
        return (HoaDon) query.getSingleResult();
    }

    public List<HoaDon> getHoaDonByIdNV(int maNv) {
        String sql = fromTable + " where idNhanVien=:idnv order by id desc";
        Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("idnv", maNv);
        return query.getResultList();
    }

    public List<HoaDon> getHdWhereMaKH(int idkh) {
        Query query = session.createQuery(fromTable + "  where idKhachHang =:idkh", HoaDon.class);
        query.setParameter("idkh", idkh);
        return query.getResultList();
    }
}

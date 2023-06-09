/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.jpa.provider.HibernateUtils;

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

    // Query cho chức năng search------------------------------------------------------------
    public List<HoaDon> searchByComboBoxNoJoin(String kind, String txt) {
        Query query = session.createQuery("FROM HoaDon where " + kind + " LIKE :txt order by id desc", HoaDon.class);
        query.setParameter("txt", "%" + txt + "%");
        return query.getResultList();
    }

    public List<HoaDon> searchDateByComboBoxNoJoin(String kind, Date txt) {
        Query query = session.createQuery("FROM HoaDon where " + kind + " = :txt order by id desc", HoaDon.class);
        query.setParameter("txt", txt);
        return query.getResultList();
    }

    public List<HoaDon> searchNhanVienByComboBoxJoin(String txt) {
        Query query = session.createQuery("SELECT hd FROM HoaDon hd INNER JOIN hd.nhanVien nv where nv.tenNhanVien LIKE :txt order by hd.id desc");
        query.setParameter("txt", "%" + txt + "%");
        return query.getResultList();
    }

    public List<HoaDon> searchKhachHangByComboBoxJoin(String txt) {
        Query query = session.createQuery("SELECT hd FROM HoaDon hd INNER JOIN hd.khachHang kh where kh.ten LIKE :txt order by hd.id desc");
        query.setParameter("txt", "%" + txt + "%");
        return query.getResultList();
    }
    //------------------------------------------------------------------------------------

    public List<HoaDon> getLichSuByTrangThai(int tt) {
        Query query = session.createQuery(fromTable + "  where trangThai =:tt order by id desc", HoaDon.class);
        query.setParameter("tt", tt);
        return query.getResultList();
    }

    public Boolean save(HoaDon hoaDon) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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

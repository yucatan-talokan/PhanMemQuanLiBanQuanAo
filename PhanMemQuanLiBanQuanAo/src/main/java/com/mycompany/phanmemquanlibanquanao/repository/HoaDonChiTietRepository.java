/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thanh Giang
 */
public class HoaDonChiTietRepository {
          private Session session = HibernateConfig.getFACTORY().openSession();

    private String fromTable = " FROM HoaDonChiTiet ";

    public List<HoaDonChiTiet> getAll() {
        Query query = session.createQuery(fromTable, HoaDonChiTiet.class);
        return (ArrayList<HoaDonChiTiet>) query.getResultList();
    }

    public Boolean save(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hoaDonChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hoaDonChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public HoaDonChiTiet getOne(int id) {
        String sql = fromTable + "where id=:id";
        Query query = session.createQuery(sql, HoaDonChiTiet.class);
        query.setParameter("id", id);
        return (HoaDonChiTiet) query.getSingleResult();
    }

    public List<HoaDonChiTiet> getHdctByIdHd(int id) {
        String sql = fromTable + " where idHoaDon =:idhd";
        Query query = session.createQuery(sql, HoaDonChiTiet.class);
        query.setParameter("idhd", id);
        return query.getResultList();
    }

    public HoaDonChiTiet getHdctByIdCtsp(int idCtsp) {
        String sql = fromTable + " where idCtSp =:idctsp";
        Query query = session.createQuery(sql, HoaDonChiTiet.class);
        query.setParameter("idctsp", idCtsp);
        return (HoaDonChiTiet) query.getSingleResult();
    }

    public HoaDonChiTiet getHdctByIdCtspAndIdHd(int idCtsp, int idHd) {
        String sql = fromTable + " where idCtSp =:idctsp and idHoaDon =:idhd";
        Query query = session.createQuery(sql, HoaDonChiTiet.class);
        query.setParameter("idctsp", idCtsp);
        query.setParameter("idhd", idHd);

        return (HoaDonChiTiet) query.getSingleResult();
    }
}

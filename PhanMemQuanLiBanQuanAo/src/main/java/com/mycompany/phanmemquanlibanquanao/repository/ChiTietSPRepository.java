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
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thanh Giang
 */
public class ChiTietSPRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public ArrayList<ChiTietSP> getAll() {
        Query query = session.createQuery(fromTable + " order by id desc");
        return (ArrayList<ChiTietSP>) query.getResultList();
    }

    public ArrayList<ChiTietSP> getAllSanPhamLonHon0() {
        Query query = session.createQuery(fromTable + " where soLuongTon >0");
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

    public ChiTietSP getOneByMaCtsp(String ma) {
        String sql = fromTable + " where MACTSP=:ma";
        Query query = session.createQuery(sql, ChiTietSP.class);
        query.setParameter("ma", ma);
        return (ChiTietSP) query.getSingleResult();
    }

    public Long ThongKeTong(int ngay, int thang, int nam) {
        Long uuid;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where DAY(ngayThanhToan) =:day \n"
                    + "and MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getSingleResult();
        }
        return uuid;
    }

    //Thống kê Than== thống kê tháng
    public Long ThongKeThan(int thang, int nam) {
        Long uuid;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getSingleResult();
        }
        return uuid;
    }

    public List<Long> ThongKeThang1(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang2(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang3(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang4(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang5(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang6(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang7(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang8(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang9(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang10(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang11(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    public List<Long> ThongKeThang12(int thang, int nam) {

        List<Long> uuid = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "MONTH(ngayThanhToan) =:month and YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getResultList();
        }
        return uuid;
    }

    //Thống kê m tháng
    public Long ThongKeNam(int nam) {
        Long uuid;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            //query.setParameter("day", ngay);
            // query.setParameter("month", thang);
            query.setParameter("year", nam);
            uuid = query.getSingleResult();
        }
        return uuid;
    }

    public List<Long> ThongKeNam1(int nam) {
        List<Long> list = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement);
            //query.setParameter("day", ngay);
            // query.setParameter("month", thang);
            query.setParameter("year", nam);
            list = query.getResultList();
        }
        return list;
    }

    public List<Long> ThongKeNam2(int nam) {
        List<Long> list = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement);
            //query.setParameter("day", ngay);
            // query.setParameter("month", thang);
            query.setParameter("year", nam);
            list = query.getResultList();
        }
        return list;
    }

    public List<Long> ThongKeNam3(int nam) {
        List<Long> list = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement);
            //query.setParameter("day", ngay);
            // query.setParameter("month", thang);
            query.setParameter("year", nam);
            list = query.getResultList();
        }
        return list;
    }

    public List<Long> ThongKeNam4(int nam) {
        List<Long> list = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            String statement = "select sum(tongTien) from HoaDon where  \n"
                    + "YEAR(ngayThanhToan) =:year";
            TypedQuery<Long> query = session.createQuery(statement);
            //query.setParameter("day", ngay);
            // query.setParameter("month", thang);
            query.setParameter("year", nam);
            list = query.getResultList();
        }
        return list;
    }

}

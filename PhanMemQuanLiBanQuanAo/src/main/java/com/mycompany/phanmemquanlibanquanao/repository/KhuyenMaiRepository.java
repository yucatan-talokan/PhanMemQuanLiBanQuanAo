/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Thanh Giang
 */
public class KhuyenMaiRepository {
    private Session session=HibernateConfig.getFACTORY().openSession();
    
    public List<KhuyenMai> getAll(){
        return session.createQuery("FROM KhuyenMai", KhuyenMai.class).getResultList();
    }
    public Boolean add(KhuyenMai km){
        Transaction trans=null;
        try(Session session=HibernateConfig.getFACTORY().openSession()){
            trans=session.beginTransaction();
            session.save(km);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean update(KhuyenMai km){
        Transaction trans=null;
        try(Session session=HibernateConfig.getFACTORY().openSession()){
            trans=session.beginTransaction();
            session.saveOrUpdate(km);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean delete(KhuyenMai km){
        Transaction trans=null;
        try(Session session=HibernateConfig.getFACTORY().openSession()){
            trans=session.beginTransaction();
            session.delete(km);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void checkDangHoatDong(){
        Transaction trans=null;
        try(Session ss=HibernateConfig.getFACTORY().openSession()){
            trans=ss.beginTransaction();
            Query query=ss.createQuery("update KhuyenMai set trangThai=1 where GETDATE() between ngayBatDau and ngayKetThuc");
            query.executeUpdate();
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void checkKetThuc(){
        Transaction trans=null;
        try(Session ss=HibernateConfig.getFACTORY().openSession()){
            trans=ss.beginTransaction();
            Query query=ss.createQuery("update KhuyenMai set trangThai=0 where GETDATE() > ngayKetThuc");
            query.executeUpdate();
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void checkChuaBatDau(){
        Transaction trans=null;
        try(Session ss=HibernateConfig.getFACTORY().openSession()){
            trans=ss.beginTransaction();
            Query query=ss.createQuery("update KhuyenMai set trangThai=2 where GETDATE() < ngayBatDau");
            query.executeUpdate();
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

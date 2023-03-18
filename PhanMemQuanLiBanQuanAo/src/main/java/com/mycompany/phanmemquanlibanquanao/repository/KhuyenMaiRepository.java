/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import java.util.List;
import org.hibernate.Session;
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
}

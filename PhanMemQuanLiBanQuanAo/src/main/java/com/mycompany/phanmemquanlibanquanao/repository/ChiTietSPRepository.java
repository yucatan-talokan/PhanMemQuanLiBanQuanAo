/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.repository;

import com.mycompany.phanmemquanlibanquanao.config.HibernateConfig;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Thanh Giang
 */
public class ChiTietSPRepository {
    private Session session = HibernateConfig.getFACTORY().openSession();
    private String fromMauSac = "From MauSac";
        public List<MauSac> getMauSac() {
        Query query = session.createQuery(fromMauSac);
        return query.getResultList();
    }
}

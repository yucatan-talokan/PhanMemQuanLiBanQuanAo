/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import com.mycompany.phanmemquanlibanquanao.repository.KhuyenMaiRepository;
import com.mycompany.phanmemquanlibanquanao.service.KhuyenMaiService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class KhuyenMaiServiceImpl implements KhuyenMaiService{
private KhuyenMaiRepository khuyenMaiRepository=new KhuyenMaiRepository();
    @Override
    public List<KhuyenMai> getAll() {
        return khuyenMaiRepository.getAll();
    }

    @Override
    public Boolean add(KhuyenMai km) {
        return khuyenMaiRepository.add(km);
    }
    
}

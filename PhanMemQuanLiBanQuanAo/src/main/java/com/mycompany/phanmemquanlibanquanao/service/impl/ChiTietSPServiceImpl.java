/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import com.mycompany.phanmemquanlibanquanao.repository.ChiTietSPRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChiTietSPService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class ChiTietSPServiceImpl implements ChiTietSPService{
    private ChiTietSPRepository chiTietSpRepository = new ChiTietSPRepository();

    @Override
    public List<MauSac> getMauSac() {
return chiTietSpRepository.getMauSac();    }

    @Override
    public List<Size> getSize() {
       return chiTietSpRepository.getSize();
    }

    @Override
    public List<NSX> getNsx() {
     return chiTietSpRepository.getNSX();
    }
     @Override
    public List<DongSP> getDongSP()  {
     return chiTietSpRepository.getDongSP();
    }
   
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.repository.DongSpRepository;
import com.mycompany.phanmemquanlibanquanao.service.DongSPService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class DongSPServiceImpl  implements  DongSPService{
    private DongSpRepository dongSpRepository =  new DongSpRepository();
    @Override
    public List<DongSP> getAll() {
        return dongSpRepository.getAll();
    }

    @Override
    public Boolean add(DongSP dongSP) {
        return dongSpRepository.add(dongSP);
    }
      @Override
    public Boolean delete(DongSP dongSP) {
        return dongSpRepository.delete(dongSP);
    }

    @Override
    public Boolean update(DongSP dongSP) {
     return dongSpRepository.Update(dongSP);
    }
}

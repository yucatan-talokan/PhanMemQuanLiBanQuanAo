/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.repository.NSXRepository;
import com.mycompany.phanmemquanlibanquanao.service.NSXService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class NSXServiceImpl implements  NSXService{
    private NSXRepository nSXRepository =  new NSXRepository();
    @Override
    public List<NSX> getAll() {
        return nSXRepository.getAll();
    }

    @Override
    public Boolean add(NSX nsx) {
        return nSXRepository.add(nsx);
    }
      @Override
    public Boolean delete(NSX nsx) {
        return nSXRepository.delete(nsx);
    }

    @Override
    public Boolean update(NSX nsx) {
      return nSXRepository.Update(nsx);
    }
}

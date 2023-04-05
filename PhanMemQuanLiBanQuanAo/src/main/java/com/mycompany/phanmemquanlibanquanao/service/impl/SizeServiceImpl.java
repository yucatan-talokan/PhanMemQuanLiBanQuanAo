/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import com.mycompany.phanmemquanlibanquanao.repository.SizeRepository;
import com.mycompany.phanmemquanlibanquanao.service.SizeService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class SizeServiceImpl implements  SizeService{
    private SizeRepository sizeRepository =  new SizeRepository();
    @Override
    public List<Size> getAll() {
        return sizeRepository.getAll();
    }

    @Override
    public Boolean add(Size size) {
        return sizeRepository.add(size);
    }
      @Override
    public Boolean delete(Size size) {
        return sizeRepository.delete(size);
    }

    @Override
    public Boolean update(Size size) {
      return sizeRepository.Update(size);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChucVu;
import com.mycompany.phanmemquanlibanquanao.repository.ChucVuRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChucVuService;

import java.util.List;

/**
 *
 * @author acer
 */
public class ChucVuServiceImpl implements ChucVuService{
     private ChucVuRepository chucVuRepository =  new ChucVuRepository();
    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.getAll();
    }

    @Override
    public Boolean add(ChucVu chucVu) {
        return chucVuRepository.add(chucVu);
    }
      @Override
    public Boolean delete(ChucVu chucVu) {
        return chucVuRepository.delete(chucVu);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.KhachHang;
import com.mycompany.phanmemquanlibanquanao.repository.KhachHangRepository;
import com.mycompany.phanmemquanlibanquanao.service.KhachHangService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class KhachHangServiceImpl implements KhachHangService{
private KhachHangRepository KhachHangRepository = new KhachHangRepository();
    @Override
    public List<KhachHang> getAll() {
        return  KhachHangRepository.getAll();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean add(KhachHang KhachHang) {
        return  KhachHangRepository.add(KhachHang);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

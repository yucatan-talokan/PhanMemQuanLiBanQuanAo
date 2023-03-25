/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import com.mycompany.phanmemquanlibanquanao.repository.ChiTietSPRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChiTietSPService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class ChiTietSPServiceImpl implements ChiTietSPService{
    private ChiTietSPRepository chiTietSpRepository = new ChiTietSPRepository();

     @Override
    public ArrayList<ChiTietSP> getAll() {
        return chiTietSpRepository.getAll();
    }
     @Override
    public ArrayList<ChiTietSP> getAllSanPhamLonHon0() {
        return chiTietSpRepository.getAllSanPhamLonHon0();
    }
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
         @Override
    public List<ChatLieu> getChatLieu()  {
     return chiTietSpRepository.getChatLieu();
    }
        @Override
    public List<SanPham> getSanPham()  {
     return chiTietSpRepository.getSanPham();
    }
    
    @Override
    public Boolean add(ChiTietSP chiTietSp) {
        return chiTietSpRepository.add(chiTietSp);
    }
    @Override
    public Boolean update(ChiTietSP chiTietSp) {
        return chiTietSpRepository.update(chiTietSp);
    }

    @Override
    public Boolean delete(ChiTietSP chiTietSp) {
        return chiTietSpRepository.delete(chiTietSp);
    }
      @Override
    public ChiTietSP getOne(String id) {
        return chiTietSpRepository.getOne(id);
    }

    @Override
    public ChiTietSP getOneByMaCtsp(String ma) {
        return chiTietSpRepository.getOneByMaCtsp(ma);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

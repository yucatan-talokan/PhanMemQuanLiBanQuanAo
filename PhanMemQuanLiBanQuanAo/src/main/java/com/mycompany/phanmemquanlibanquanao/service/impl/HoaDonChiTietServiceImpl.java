/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDonChiTiet;
import com.mycompany.phanmemquanlibanquanao.repository.HoaDonChiTietRepository;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonChiTietService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService{
        private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    @Override
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietRepository.getAll();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet getHdctByIdCtspAndIdHd(int idCtsp, int idHd) {
        return hoaDonChiTietRepository.getHdctByIdCtspAndIdHd(idCtsp, idHd);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean save(HoaDonChiTiet hdct) {
        return hoaDonChiTietRepository.save(hdct);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean update(HoaDonChiTiet hdct) {
        return hoaDonChiTietRepository.update(hdct);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delete(HoaDonChiTiet hdct) {
        return hoaDonChiTietRepository.delete(hdct);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet getOne(int id) {
        return hoaDonChiTietRepository.getOne(id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> getHdctByIdHD(int id) {
        return hoaDonChiTietRepository.getHdctByIdHd(id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet getHdctByIdCtsp(int idctsp) {
        return hoaDonChiTietRepository.getHdctByIdCtsp(idctsp);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

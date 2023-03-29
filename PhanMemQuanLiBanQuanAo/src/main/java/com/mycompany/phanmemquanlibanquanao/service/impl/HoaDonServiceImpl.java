/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import com.mycompany.phanmemquanlibanquanao.repository.HoaDonRepository;
import com.mycompany.phanmemquanlibanquanao.service.HoaDonService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class HoaDonServiceImpl implements HoaDonService{
    private HoaDonRepository hoaDonReposity = new HoaDonRepository();
    @Override
    public List<HoaDon> getAll() {
        return hoaDonReposity.getAll();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon getOne(int id) {
        return hoaDonReposity.getOne(id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public HoaDon getOneByMaHD(String maHoaDon) {
        return hoaDonReposity.getOneByMaHD(maHoaDon);
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> getHoaDonByIdNV(int idnv) {
        return hoaDonReposity.getHoaDonByIdNV(idnv);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean save(HoaDon hd) {
        return hoaDonReposity.save(hd);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean update(HoaDon hd) {
        return hoaDonReposity.update(hd);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean delete(HoaDon hd) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> getHdWhere(int tt, int id) {
        return hoaDonReposity.getHdWhere(tt, id);
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import com.mycompany.phanmemquanlibanquanao.repository.NhanVienRepository;
import com.mycompany.phanmemquanlibanquanao.service.NhanVienService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class NhanVienServiceImpl implements NhanVienService{
     private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    
     @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public Boolean add(NhanVien nhanVien) {
        return nhanVienRepository.add(nhanVien);
    }
    
     @Override
    public Boolean delete(NhanVien nhanVien) {
        return nhanVienRepository.delete(nhanVien);
    }

    @Override
    public Boolean update(NhanVien nhanVien) {
        return nhanVienRepository.Update(nhanVien);
    }
     @Override
    public NhanVien getOne(Integer id) {
        return nhanVienRepository.getOne(id);
    }
}

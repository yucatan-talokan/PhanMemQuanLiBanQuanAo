/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import com.mycompany.phanmemquanlibanquanao.repository.SanPhamRepository;
import com.mycompany.phanmemquanlibanquanao.repository.SizeRepository;
import com.mycompany.phanmemquanlibanquanao.service.SanPhamService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class SanPhamServiceImpl implements SanPhamService{
    private SanPhamRepository sanPhamRepository =  new SanPhamRepository();
    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public Boolean add(SanPham sanPham) {
        return sanPhamRepository.add(sanPham);
    }
      @Override
    public Boolean delete(SanPham sanPham) {
        return sanPhamRepository.delete(sanPham);
    }

    @Override
    public Boolean update(SanPham sanPham) {
      return sanPhamRepository.Update(sanPham);
    }

}

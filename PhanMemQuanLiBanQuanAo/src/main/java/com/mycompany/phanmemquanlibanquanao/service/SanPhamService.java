/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface SanPhamService {
     List<SanPham> getAll();

    public Boolean add(SanPham sanPham);
    public Boolean delete(SanPham sanPham);
}

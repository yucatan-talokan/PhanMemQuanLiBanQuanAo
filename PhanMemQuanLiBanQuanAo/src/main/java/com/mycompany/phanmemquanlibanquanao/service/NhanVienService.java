/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChucVu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface NhanVienService {
      List<NhanVien> getAll();

    public Boolean add(NhanVien nhanVien);
    
    public Boolean delete(NhanVien nhanVien);

    public Boolean update(NhanVien nhanVien);
     public List<ChucVu> getChucVu();
    
     NhanVien getOne(Integer id);
}

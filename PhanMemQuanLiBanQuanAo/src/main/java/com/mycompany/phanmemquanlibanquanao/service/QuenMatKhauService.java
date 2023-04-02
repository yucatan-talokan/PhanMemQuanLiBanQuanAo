/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface QuenMatKhauService {
        public List<NhanVien> getAll();
    
    public String changePassword(String email,String MatKhau);
}

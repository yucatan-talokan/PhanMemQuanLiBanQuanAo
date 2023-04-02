/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.NhanVien;
import com.mycompany.phanmemquanlibanquanao.repository.QuenMatKhauRepository;
import com.mycompany.phanmemquanlibanquanao.service.QuenMatKhauService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class QuenMatKhauServiceImpl implements QuenMatKhauService{
    QuenMatKhauRepository qmkr = new QuenMatKhauRepository();
    @Override
    public List<NhanVien> getAll() {
        return qmkr.getAll();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String changePassword(String email, String MatKhau) {
        return qmkr.changePassword(email, MatKhau);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

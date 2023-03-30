/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.domainmodels;


import com.mycompany.phanmemquanlibanquanao.domainmodels.KhachHang;
import com.mycompany.phanmemquanlibanquanao.service.KhachHangService;
import com.mycompany.phanmemquanlibanquanao.service.impl.KhachHangServiceImpl;

/**
 *
 * @author Thanh Giang
 */
public class ChonKH {
            static KhachHangService khachHangService = new KhachHangServiceImpl();

    public static KhachHang kh = khachHangService.getOne(1);

    public ChonKH() {

    }

    public static KhachHang getKhachHang() {
        return kh;
    }

    public static void setKhachHang(KhachHang kh) {
        ChonKH.kh = kh;
    }
}

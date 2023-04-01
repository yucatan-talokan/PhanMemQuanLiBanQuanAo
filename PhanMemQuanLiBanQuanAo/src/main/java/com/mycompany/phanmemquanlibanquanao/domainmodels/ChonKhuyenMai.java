/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.domainmodels;

import com.mycompany.phanmemquanlibanquanao.service.KhuyenMaiService;
import com.mycompany.phanmemquanlibanquanao.service.impl.KhuyenMaiServiceImpl;

/**
 *
 * @author Thanh Giang
 */
public class ChonKhuyenMai {

    static KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl();

    public static KhuyenMai km = khuyenMaiService.getAll().get(0);

    public ChonKhuyenMai() {

    }

    public static KhuyenMai getKhuyenMai() {
        return km;
    }

    public static void setKhuyenMai(KhuyenMai kh) {
        ChonKhuyenMai.km = kh;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.domainmodels.ChiTietSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.DongSP;
import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.SanPham;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface ChiTietSPService {
        public List<MauSac> getMauSac();
        public List<Size> getSize();
        public List<NSX> getNsx();
        public List<DongSP> getDongSP();
        public List<ChatLieu> getChatLieu();
        public List<SanPham> getSanPham();
        public ArrayList<ChiTietSP> getAll();
        ArrayList<ChiTietSP> getAllSanPhamLonHon0();
        public Boolean add(ChiTietSP chiTietSp);
        public Boolean update(ChiTietSP chiTietSp);

    public Boolean delete(ChiTietSP chiTietSp);
    public ChiTietSP getOne(String id);
}

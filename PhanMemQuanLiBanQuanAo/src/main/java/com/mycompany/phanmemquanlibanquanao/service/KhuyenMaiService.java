/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface KhuyenMaiService {
    List<KhuyenMai> getAll();
    public Boolean add(KhuyenMai km);
    public Boolean update(KhuyenMai km);
    public Boolean delete(KhuyenMai km);
    public void checkDangHoatDong();
    public void checkKetThuc();
    public void checkChuaBatDau();
}

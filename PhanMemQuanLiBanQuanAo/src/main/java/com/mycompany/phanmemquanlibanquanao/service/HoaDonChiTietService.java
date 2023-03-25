/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface HoaDonChiTietService {
                List<HoaDonChiTiet> getAll();

    HoaDonChiTiet getHdctByIdCtspAndIdHd(int idCtsp, int idHd);

    Boolean save(HoaDonChiTiet hdct);

    Boolean update(HoaDonChiTiet hdct);

    Boolean delete(HoaDonChiTiet hdct);

    HoaDonChiTiet getOne(int id);

    List<HoaDonChiTiet> getHdctByIdHD(int id);

    HoaDonChiTiet getHdctByIdCtsp(int idctsp);
}

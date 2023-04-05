/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.HoaDon;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface HoaDonService {
        List<HoaDon> getAll();

    HoaDon getOne(int id);
    
    List<HoaDon> getHdWhere(int tt,int id);
    
    List<HoaDon> getLichSuByTrangThai(int tt);
    
    List<HoaDon>searchKindByComboBox(String kind,String txt);
    
    List<HoaDon>searchDateKindByComboBox(String kind,Date txt);
    
    List<HoaDon>searchNhanVienByComboBoxJoin(String txt);
    
    List<HoaDon>searchKhachHangByComboBoxJoin(String txt);

    HoaDon getOneByMaHD(String maHoaDon);

    List<HoaDon> getHoaDonByIdNV(int idnv);

    Boolean save(HoaDon hd);

    Boolean update(HoaDon hd);

    Boolean delete(HoaDon hd);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChucVu;
import com.mycompany.phanmemquanlibanquanao.views.ChucVuJpanel;
import java.util.List;

/**
 *
 * @author acer
 */
public interface ChucVuService {
    List<ChucVu> getAll();

    public Boolean add(ChucVu chucVu);
    
    public Boolean delete(ChucVu chucVu);
}

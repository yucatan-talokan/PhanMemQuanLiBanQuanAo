/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface SizeService {
    
    List<Size> getAll();

    public Boolean add(Size size);
    public Boolean delete(Size size);
     public Boolean update(Size size);
}

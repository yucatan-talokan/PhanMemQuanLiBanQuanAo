/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import com.mycompany.phanmemquanlibanquanao.domainmodels.Size;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface ChiTietSPService {
        public List<MauSac> getMauSac();
        public List<Size> getSize();
        public List<NSX> getNsx();
}

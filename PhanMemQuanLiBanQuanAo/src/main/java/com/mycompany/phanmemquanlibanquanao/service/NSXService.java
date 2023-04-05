/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.NSX;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface NSXService {
    List<NSX> getAll();

    public Boolean add(NSX nsx);
    public Boolean delete(NSX nsx);
     public Boolean update(NSX nsx);
}

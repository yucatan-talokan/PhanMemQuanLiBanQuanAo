/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface MauSacService {

    List<MauSac> getAll();

    public Boolean add(MauSac mauSac);

    public Boolean delete(MauSac mauSac);

    public Boolean update(MauSac mauSac);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.MauSac;
import com.mycompany.phanmemquanlibanquanao.repository.MauSacRepository;
import com.mycompany.phanmemquanlibanquanao.service.MauSacService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAll();
    }

    @Override
    public Boolean add(MauSac mauSac) {
        return mauSacRepository.add(mauSac);
    }

    @Override
    public Boolean delete(MauSac mauSac) {
        return mauSacRepository.delete(mauSac);
    }

    @Override
    public Boolean update(MauSac mauSac) {
        return mauSacRepository.Update(mauSac);
    }

}

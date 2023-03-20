/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public interface ChatLieuService {
    List<ChatLieu> getAll();

    public Boolean add(ChatLieu chatLieu);
    public Boolean delete(ChatLieu chatLieu);
}

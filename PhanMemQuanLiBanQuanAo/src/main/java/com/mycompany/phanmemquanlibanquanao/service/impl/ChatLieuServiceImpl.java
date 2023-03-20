/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.service.impl;

import com.mycompany.phanmemquanlibanquanao.domainmodels.ChatLieu;
import com.mycompany.phanmemquanlibanquanao.repository.ChatLieuRepository;
import com.mycompany.phanmemquanlibanquanao.repository.SizeRepository;
import com.mycompany.phanmemquanlibanquanao.service.ChatLieuService;
import java.util.List;

/**
 *
 * @author Thanh Giang
 */
public class ChatLieuServiceImpl implements ChatLieuService{
     private ChatLieuRepository chatLieuRepository =  new ChatLieuRepository();
    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.getAll();
    }

    @Override
    public Boolean add(ChatLieu chatLieu) {
        return chatLieuRepository.add(chatLieu);
    }
      @Override
    public Boolean delete(ChatLieu chatLieu) {
        return chatLieuRepository.delete(chatLieu);
    }

}

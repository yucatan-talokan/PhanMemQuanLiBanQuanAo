/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.domainmodels;

/**
 *
 * @author Thanh Giang
 */
public class UserLogin {
        public static NhanVien nv;
    public  UserLogin(){
        
    }
    public static NhanVien getNhanVien(){
        return nv;
    }
    public static void setNhanVien(NhanVien nv){
        UserLogin.nv=nv;
    }
}

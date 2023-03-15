/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.domainmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Thanh Giang
 */
@Table(name = "KHACHHANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class KhachHang {
          @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")

    private Integer id;

    @Column(name = "ten")
    private String ten;
    
    @Column(name = "sdt")
    private String sdt;
    
     
    @Column(name = "Makhachhang")
    private String ma;
    
     
    @Column(name = "capbac")
    private Integer capBac;
    
    @Column(name = "diaChi")
    private String diaChi;

    public String capbac(){
        if(capBac==0){
            return "Bạc";
        }else if(capBac==1){
            return "Vàng";
        }else return "Kim cương";  
    }
}

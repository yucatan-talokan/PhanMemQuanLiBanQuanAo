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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CHITIETSP")
public class ChiTietSP {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "idSp", referencedColumnName = "id") //categpry
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idDongSp", referencedColumnName = "id")
    private DongSP dongSp;

    @ManyToOne
    @JoinColumn(name = "idMauSac", referencedColumnName = "id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idChatLieu", referencedColumnName = "id")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "idSize", referencedColumnName = "id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "idNsx", referencedColumnName = "id")
    private NSX nsx;

    @Column(name = "soLuongTon")
    private int soLuongTon;

    @Column(name = "gia")
    private int gia;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "anh")
    private String anh;
    @Column(name = "mactsp")
    private String mactsp;
}

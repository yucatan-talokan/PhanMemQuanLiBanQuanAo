/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemquanlibanquanao.domainmodels;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Giang
 */
public class QR {
        private  static  void generateQRcode(String text,int width ,int height,String filePath) throws WriterException{
        QRCodeWriter qc =  new QRCodeWriter();
        BitMatrix  bm = qc.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path pobj = FileSystems.getDefault().getPath(filePath);
        try {
            MatrixToImageWriter.writeToPath(bm, "PNG", pobj);
        } catch (IOException ex) {
            Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  void output(String id) throws WriterException{
        generateQRcode(id, 600, 600,"E:\\pdf\\"+id+".png");
    }
}

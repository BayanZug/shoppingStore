package com.retailer.shop.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 * 
 * class for generating the barcode and decoding it
 *
 */
@Service
public class BarcodeGenerator {
	public Blob generateEAN13BarcodeImage(String barcodeText) {
		Blob barcodeImage =null;
		try {
		Barcode barcode = BarcodeFactory.createCode128(barcodeText);
//	    barcode.setFont(BARCODE_TEXT_FONT);

	   BufferedImage bi = BarcodeImageHandler.getImage(barcode);
	   if(bi!=null) {
	   ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   ImageIO.write(bi, "jpg", baos);
	   byte[] bytes = baos.toByteArray();
	   barcodeImage = new SerialBlob(bytes );
	   }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return barcodeImage;
	}

}

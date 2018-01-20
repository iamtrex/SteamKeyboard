package com.rweqx.constants;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.rweqx.IO.IOData;
import com.rweqx.start.Log;

public class IconParser {
	
	public static ImageIcon getIcon(String file, int x, int y){
		try{
			InputStream IS = IOData.getFileFromLocation(file);
			
			
			
			BufferedImage img = ImageIO.read(IS);
			
			Image rimg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
			
			ImageIcon i = new ImageIcon(rimg);
			
			return i;
			
		}catch(Exception e){
			e.printStackTrace();
			Log.log("Cannot find icon at - " + file, "5", true);
		}
		
		//If failed return a black box
		ImageIcon i = new ImageIcon();
		return i;
		
	}
}

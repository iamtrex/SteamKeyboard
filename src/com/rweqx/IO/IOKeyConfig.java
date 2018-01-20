package com.rweqx.IO;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.rweqx.constants.Settings;
import com.rweqx.start.Log;


public class IOKeyConfig {
	SAXBuilder saxBuilder = new SAXBuilder();
	InputStream IS;
	
	static int tKeys = -1;
	
	public Object[][] read(){
		System.out.println("REading");
		
		IS = IOData.getFileFromLocation(Settings.KEY_DATA_FILE_LOCATION);
		Object[][] data = null;
		Document doc;
		try {
			doc = saxBuilder.build(IS);
			
			
			
			Element classElement = doc.getRootElement();
			
			List<Element> keys = classElement.getChildren();
			
			data = new Object[keys.size()][4];
			tKeys = keys.size();
			
			
			for(int a=0; a<keys.size(); a++){
				Element key = keys.get(a);
				
				String n = key.getAttributeValue("name");
				String x = key.getChildText("x");
				String y = key.getChildText("y");
				String w = key.getChildText("w");
				
				data[a][0] = n;
				data[a][1] = x;
				data[a][2] = y;
				data[a][3] = w;
				
			}
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.log("Reading of KeyConfig Failed " + e.getMessage(), "3.1", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.log("Reading of KeyConfig Failed " + e.getMessage(), "3.2", true);
		}
		
		
		return data;
	}
	
	public static int gettKeys(){
		
		if(tKeys == -1){
			Log.log("Grabbing tKeys too early, returning -1");
		}
		return tKeys;
	}
}

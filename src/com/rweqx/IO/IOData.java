package com.rweqx.IO;

import java.io.InputStream;

import com.rweqx.start.Log;

public class IOData {
	static Object data[][];
	
	public IOData(){
		IOKC = new IOKeyConfig();
	//	readKeyConfig();
		
		data = IOKC.read();
	}
	
	static IOKeyConfig IOKC;
	
	public static Object[][] readKeyConfig(){
		return data;
	}

	
	
	/*
	 * Returns InputStream from file dir name.
	 * Must include / before file name...
	 */
	
	public static InputStream getFileFromLocation(String s){
		try {
			InputStream IS = IOData.class.getResourceAsStream(s);
		
			return IS;
			
		} catch (Exception e) {
			Log.log("Cannot locate file - " + s, "6.1", true);
			e.printStackTrace();
		}
		
		return null;
	}
}

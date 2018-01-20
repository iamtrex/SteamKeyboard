package com.rweqx.constants;

import com.rweqx.IO.IOData;
import com.rweqx.IO.IOKeyConfig;

public class Settings {
	

	public static Object[][] KeyData = IOData.readKeyConfig();
	
	public static double REFRESH = 0.125;
	public static int tKeys = IOKeyConfig.gettKeys();
	
	
	//Locations
	public static final String KEY_DATA_FILE_LOCATION = "/KeyConfig.xml";
	
	
	
}

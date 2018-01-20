package com.rweqx.constants;

import com.rweqx.IO.IOKeyConfig;
import com.rweqx.start.Log;


/**
 * Translates between JNativeHook language and my programs
 * @author RWEQX
 *
 */
public class Translator {

	
	public final static String LMouse = "Left Mouse";
	public final static String RMouse = "Right Mouse";
	public final static String CMouse = "Center Mouse";
	
	public Translator(Object[][] data){
		Translator.data = data;
	}
	
	public static String getMouseButtonFromID(int buttonID) {
		if(buttonID == 1){
			return LMouse;
		}else if(buttonID == 2){
			return RMouse;
		}else if(buttonID == 3){
			return CMouse;
		}
		Log.log("Mouse Input Button " + buttonID + " not found...", "2", true);
		return null;
	}
	
	public static int getMouseIDFromButton(String s){
		if(s.equalsIgnoreCase(LMouse)){
			return 1;
		}else if(s.equalsIgnoreCase(RMouse)){
			return 2;
		}else if(s.equalsIgnoreCase(CMouse)){
			return 3;
		}
		Log.log("Mouse Input Button " + s + " not found...", "2.1", true);
		return 0;
	
	}
	
	static Object data[][];
	
	public static int getKeyIDFromName(String keyName) {
		for(int a=0; a<IOKeyConfig.gettKeys(); a++){
			if(String.valueOf(data[a][0]).equalsIgnoreCase(keyName)){
				return a;
				
			}
		}
		Log.log("ID not found for key " + keyName, "4", true);
		return -1;
	}

	public static String getKeyFromID(String s) {
		//Any translations needed... List may be incomplete. 
		if(s.equalsIgnoreCase("Windows")){
			s = "WIN";
		}else if(s.equalsIgnoreCase("Shift")){
			s = "Shift";
		}else if(s.equalsIgnoreCase("Caps Lock")){
			s = "CAPS";
		}else if(s.equalsIgnoreCase("Minus")){
			s = "-";
		}else if(s.equalsIgnoreCase("Equals")){
			s = "=";
		}else if(s.equalsIgnoreCase("Backspace")){
			s = "Back";
		}else if(s.equalsIgnoreCase("Back quote")){
			s = "`";
		}else if(s.equalsIgnoreCase("Back slash")){
			s = "\\";
		}else if(s.equalsIgnoreCase("Semicolon")){
			s = ";";
		}else if(s.equalsIgnoreCase("Quote")){
			s = "'";
		}else if(s.equalsIgnoreCase("Period")){
			s = ".";
		}else if(s.equalsIgnoreCase("Dead Acute")){
			s = "/";
		}else if(s.equalsIgnoreCase("Comma")){
			s = ",";
		}else if(s.equalsIgnoreCase("Open Bracket")){
			s = "[";
		}else if(s.equalsIgnoreCase("Close Bracket")){
			s = "]";
		}else if(s.equalsIgnoreCase("Alt")){
			s = "Alt";
		}else if(s.equalsIgnoreCase("Context Menu")){
			s = "MNU";
		}
		return s;
	}

	public static int getMouseButtonsLength() {
		return 3;
	}
	
	
}

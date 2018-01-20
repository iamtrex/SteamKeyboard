package com.rweqx.calc;

import com.rweqx.IO.IOSettings;
import com.rweqx.UI.Window;

public class Logic {

	Window w;
	APM APM;
	KeyTrack KT;
	MoveTrack MT;
	
	IOSettings IOS;
	
	public Logic(IOSettings IOS){
		this.IOS = IOS;
		
	}
	public void giveW(Window w){
		this.w = w;
	}
	
	public void run(){
		APM = new APM(w, IOS);
		KT = new KeyTrack(w, IOS);
		MT = new MoveTrack(w, IOS);
	}
	
	
	public void newMousePosition(int x, int y) {
		MT.newMousePosition(x, y);
	}
	
	//Registers the press or release of keys
	public void keyPress(String keyName, boolean b) {
		KT.keyPress(keyName, b);
		APM.actionK(keyName, b);
	}
	
	public void mousePress(String mouseName, boolean b){
		KT.mousePress(mouseName, b);
		APM.actionM(mouseName, b);
	}
	
	
}

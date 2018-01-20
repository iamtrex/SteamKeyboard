package com.rweqx.calc;

import java.util.Timer;
import java.util.TimerTask;

import com.rweqx.IO.IOData;
import com.rweqx.IO.IOKeyConfig;
import com.rweqx.IO.IOSettings;
import com.rweqx.UI.Window;
import com.rweqx.constants.Settings;
import com.rweqx.constants.Translator;

public class APM {
	IOSettings IOS;
	Window w;
	
	
	long DELAY;
	double REFRESH;
	double time = 0;
	
	int Actions;
	int Actions5;
	
	
	public APM(Window w, IOSettings IOS) {
		this.w = w;
		this.IOS = IOS;
		
		Actions = 0;
		Actions5 = 0;
		time = 0;
		
		REFRESH = Settings.REFRESH;
		DELAY = (long) (REFRESH*1000);
		
		clickedK = new boolean[IOKeyConfig.gettKeys()];
		
		for(boolean b : clickedK){
			b = false;
		}
		
		clickedM = new boolean[Translator.getMouseButtonsLength()];
		
		for(boolean b : clickedM){
			b = false;
		}
		startKeyTimer();
	}

	
	boolean[] clickedK;
	boolean[] clickedM;
	
	public void actionK(String keyName, boolean b) {
		int i = Translator.getKeyIDFromName(keyName);
		
		if(b){
			if(!clickedK[i]){
				Actions++;
				Actions5++;
				
				LowerAction thread = new LowerAction();
				thread.start();
				clickedK[i] = true;
			}
			//Ignore if it's already clicked, aka it's just held down... 
		}else{
			clickedK[i] = false;
		}
	}
	
	public void actionM(String mouseName, boolean b){
		int i = Translator.getMouseIDFromButton(mouseName);
		
		if(b){
			if(!clickedM[i]){
				Actions++;
				Actions5++;
				
				LowerAction thread = new LowerAction();
				thread.start();
				clickedM[i] = true;
			}
		}else{
			clickedM[i] = false;
		}
	}
	
	
	Timer timer;
	
	
	private void startKeyTimer(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				time += REFRESH;
				
				if(time!=0){
					String APM = String.valueOf(Math.round(Actions/time*60));
					
					String APM5;
					if(time>=5){
						APM5 = String.valueOf(Math.round(Actions5*12)); //*60/5 = *12
					}else{
						APM5 = "---";
					}
					
					w.updateAPM(APM, APM5);
				}
				
			}
		}, DELAY, DELAY);
		
		
	}
	
	//This class is run when there is an action added to action5, it waits 5 seconds before deducting one action...
	//This allows the data to remain for the last 5 seconds (if everythign works as I want it to)
	public class LowerAction extends Thread{
		public void run(){
			try{
				Thread.sleep(5*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			Actions5 --;
		}
	}
	
	
	
	public void pauseTimer(boolean b){
		if(b){
			
		}else{
			
		}
	}

}

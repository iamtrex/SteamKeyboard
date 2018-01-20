package com.rweqx.start;

import javax.swing.SwingUtilities;

import com.rweqx.IO.IOData;
import com.rweqx.IO.IOSettings;
import com.rweqx.UI.Window;
import com.rweqx.calc.Logic;
import com.rweqx.constants.Settings;
import com.rweqx.constants.Translator;
import com.rweqx.constants.UIC;
import com.rweqx.key.Read;

public class start {
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				/**
				 * Fix fucking setup of project... Especially how IOData, and IOKeyConfig work at start to read data...
				 * It's relaly bad, workos but it's really pooorly setup
				 * 
				 */
				
				IOData IOD = new IOData();
				IOSettings IOS = new IOSettings();

				UIC UIC = new UIC();
				Settings s = new Settings();
				
				
				Translator t = new Translator(IOData.readKeyConfig());
				
				Window w = new Window(IOS);
				
				Logic l = new Logic(IOS);
				
				
				Read r = new Read(l, w);
				r.setupAndStartKeyTracking();
				
				
				l.giveW(w);
				w.giveL(l);
				
				l.run();
				w.run();
				
				
				
				
				w.showWindow();
				
			}
		});
		
	}
}

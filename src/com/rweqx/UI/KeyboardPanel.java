package com.rweqx.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.rweqx.IO.IOData;
import com.rweqx.IO.IOSettings;
import com.rweqx.calc.Logic;
import com.rweqx.constants.Settings;
import com.rweqx.constants.UIC;
import com.rweqx.constants.UIM;
import com.rweqx.constants.UIM.KeyButtons;
import com.rweqx.start.Log;

public class KeyboardPanel {
	IOSettings IOS;
	Logic l;
	
	JPanel mainPanel;
	JPanel smallPanel[] = new JPanel[5]; //4 rows of keyboard... 
	
	int tKeys = Settings.tKeys;
	Object KeyData[][] = Settings.KeyData;
	
	KeyButtons bKeys[];
	
	public KeyboardPanel(Logic l, IOSettings IOS) {
		this.l = l;
		this.IOS = IOS;
		
		createKP();
	}
	
	
	private void createKP(){
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		
		for(int x=0; x<smallPanel.length; x++){
			smallPanel[x] = new JPanel();
			smallPanel[x].setLayout(new GridBagLayout());
			
			UIM.addComponent(smallPanel[x], mainPanel, 0, x, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		}
		
		bKeys = new KeyButtons[tKeys];
		
		for(int x=0; x<tKeys; x++){
			bKeys[x] = new KeyButtons(String.valueOf(KeyData[x][0]));
			
			int row = Integer.parseInt(String.valueOf(KeyData[x][2]));
			int column = Integer.parseInt(String.valueOf(KeyData[x][1]));
			double width = Double.parseDouble(String.valueOf(KeyData[x][3]));
			
			double a = UIC.BUTTON_DIM;
			
			int b = (int) Math.round(a*width);
			int c = (int) Math.round(a);
			Dimension d = new Dimension(b, c);
			bKeys[x].setPreferredSize(d);
			bKeys[x].setMaximumSize(d);
			bKeys[x].setMinimumSize(d);
			bKeys[x].setSize(d);
			
			UIM.addComponent(bKeys[x], smallPanel[row], column, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		}
		
		mainPanel.setBorder(BorderFactory.createLineBorder(UIC.BUTTON_ACTIVE_TRUE_COLOR));
		Log.log("Completed KP creation with " + tKeys + " keys");
	}


	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return mainPanel;
	}


	public void keyPress(String keyName, boolean b) {
		
		for(int x=0; x<tKeys; x++){
			if(keyName.equalsIgnoreCase(String.valueOf(KeyData[x][0]))){
				bKeys[x].setActive(b);
			}
		}
	}

}

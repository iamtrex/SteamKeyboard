package com.rweqx.UI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rweqx.IO.IOSettings;
import com.rweqx.calc.Logic;
import com.rweqx.constants.UIC;
import com.rweqx.constants.UIM;
import com.rweqx.constants.UIM.APMLabel;
import com.rweqx.constants.UIM.SettingsButton;

public class DetailsBar {
	IOSettings IOS;
	Logic l;
	
	JPanel mainPanel = new JPanel();
	JPanel APMPanel = new JPanel();
	JPanel SettingsPanel = new JPanel();
	JPanel CenterPanel = new JPanel(); //Temp filler
	
	public DetailsBar(Logic l, IOSettings IOS){
		this.IOS = IOS;
		this.l = l;
		createDB();
	}

	APMLabel lAPM;
	APMLabel lAPM5;
	
	private void createDB(){
		lAPM = new APMLabel(UIC.APM_BLANK);
		lAPM5 = new APMLabel(UIC.APM_BLANK);
		
		SettingsButton SB = new SettingsButton();
		
		
		APMPanel.setLayout(new GridBagLayout());
		SettingsPanel.setLayout(new GridBagLayout());
		CenterPanel.setLayout(new GridBagLayout());
		mainPanel.setLayout(new GridBagLayout());
		
		UIM.addComponent(lAPM, APMPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 10, 0, 10);
		UIM.addComponent(lAPM5, APMPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		
		UIM.addComponent(SB, SettingsPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST, 0, 0, 0, 0);
		
		
		UIM.addComponent(APMPanel, mainPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		UIM.addComponent(CenterPanel, mainPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		UIM.addComponent(SettingsPanel, mainPanel, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.EAST, 0, 0, 0, 0);
		
	}
	
	public void updateAPM(String a, String a5){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				lAPM.setText("APM - " + a);
				lAPM5.setText("APM 5 - " + a5);
				mainPanel.validate();
			}
		});
	}
	public JPanel getPanel() {
		return mainPanel;
	}
}

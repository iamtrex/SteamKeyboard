package com.rweqx.UI;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rweqx.IO.IOSettings;
import com.rweqx.calc.Logic;
import com.rweqx.constants.UIC;
import com.rweqx.constants.UIM;

public class Window {
	Logic l;
	IOSettings IOS;
	
	KeyboardPanel KP;
	SettingsPane SP;
	DetailsBar DB;
	TopBar TB;
	
	
	JFrame mainFrame = new JFrame();
	
	
	public Window(IOSettings IOS){
		this.IOS = IOS;
		
	}
	public void giveL(Logic l){
		this.l = l;
	}
	
	
	
	public void run(){
		KP = new KeyboardPanel(l, IOS);
		SP = new SettingsPane(l, IOS);
		DB = new DetailsBar(l, IOS);
		TB = new TopBar(l, IOS);
		createWindow();
	}
	
	public void showWindow() {
		mainFrame.setVisible(true);
	}
	
	
	JPanel mainPanel;
	
	
	private void createWindow(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		
		
		
		mainFrame.setContentPane(mainPanel);
		
		UIM.addComponent(KP.getPanel(), contentPanel, 0, 0, 3, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 0);
		UIM.addComponent(DB.getPanel(), contentPanel, 0, 1, 3, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0, 0, 0, 0);
		
		
		UIM.addComponent(TB.getPanel(), mainPanel, 0, 0, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0, 0, 0, 0);
		UIM.addComponent(contentPanel, mainPanel, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 0, 10); //Space on right for settings bar
		
		contentPanel.setBackground(UIC.WHITE);
	
		
		JPanel glassPanel = new JPanel();
		glassPanel.setLayout(new GridBagLayout());
		UIM.addComponent(SP.getPanel(), glassPanel, 0, 0, 1, 1, 0, 1, GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 0, 0, 0, 0);
		
		
		
		setBackground(mainFrame);
		
		mainFrame.setUndecorated(true);
		mainFrame.setOpacity(UIC.OPAQUE_VALUE);
		mainFrame.setSize(700, 275);
		mainFrame.setResizable(true);
		mainFrame.setGlassPane(glassPanel);
	//	glassPanel.setVisible(true); //TEMP DISABLE GLASS PANE
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setBackground(Component CC){
		Component[] comp = ((Container) CC).getComponents();
		for(Component c : comp){
			if(c instanceof Container){
				setBackground(c);
			}
			
			c.setBackground(UIC.BACKGROUND_COLOR);
		}
	}
	public void mousePress(String buttonName, boolean b){
		
	}
	
	public void keyPress(String keyName, boolean b){
		KP.keyPress(keyName, b);
	}
	
	public void updateAPM(String APM, String APM5) {
		DB.updateAPM(APM, APM5);
		
	}

}


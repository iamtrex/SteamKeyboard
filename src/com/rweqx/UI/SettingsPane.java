package com.rweqx.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.rweqx.IO.IOSettings;
import com.rweqx.calc.Logic;
import com.rweqx.constants.UIC;



public class SettingsPane implements MouseListener {
	IOSettings IOS;
	Logic l;
	
	JPanel mainPanel = new JPanel();
	
	public SettingsPane(Logic l, IOSettings IOS) {
		this.l = l;
		this.IOS = IOS;
		
		createSP();
	}
	
	private void createSP(){
		
		
		mainPanel.addMouseListener(this);
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setSize(10, 700);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public JPanel getPanel() {
		return mainPanel;
	}
	
	
	
	boolean extended = false; //Default extended... 

	ContractTask CT;
	ExtendTask ET;
	
	//Cancels any currently operating task in order to make sure it doesn't bug out... 
	//Can still bug out sometimes... but it's better than befroe. 
	//By moving extended = true/false into the Workers, pretty much freezes the LeftMenu 
	//	until the animation is done so they can't cancel it :)
	private void cancelTask(){
		if(ET!=null){
			if(!ET.isDone()){
				ET.cancel(true);
			}
		}	
		if(CT!=null){
			if(!CT.isDone())
				CT.cancel(true);
		}
		
	}
	
	//Closes menu if not already closed
	public void contractIfNeeded(){
		if(extended){
			(CT = new ContractTask()).execute();
		}
	}
	
	private class ContractTask extends SwingWorker<Void, Void>{
		@Override
		protected Void doInBackground() throws Exception{
			while(mainPanel.getWidth() > UIC.CONTRACTED_MENU_WIDTH){
				int width = mainPanel.getWidth()-1;
				int height = mainPanel.getHeight();
				
				Dimension d = new Dimension(width, height);
				mainPanel.setPreferredSize(d);
				mainPanel.setSize(d);	
				mainPanel.setMinimumSize(d);
				mainPanel.setMaximumSize(d);
			
				try{
					Thread.sleep(UIC.EXTEND_DELAY);
				}catch(InterruptedException ignore){}

				mainPanel.repaint();
				mainPanel.validate();
				mainPanel.revalidate();
			}
			
			extended = false;

			return null;
		}
	}
	private class ExtendTask extends SwingWorker<Void, Void>{
		@Override
		protected Void doInBackground() throws Exception{
			while(mainPanel.getWidth() < UIC.EXTENDED_MENU_WIDTH){
			
				int width = mainPanel.getWidth() +1;
				int height = mainPanel.getHeight();
			
				Dimension d = new Dimension(width, height);
				mainPanel.setPreferredSize(d);
				mainPanel.setMinimumSize(d);
				mainPanel.setSize(d);
				mainPanel.setMaximumSize(d);
							
			
				try{
					Thread.sleep(UIC.EXTEND_DELAY);
				}catch(InterruptedException ignore){}

				mainPanel.repaint();
				mainPanel.validate();
				mainPanel.revalidate();
			}

			extended = true;
			return null;
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		cancelTask();
		(ET = new ExtendTask()).execute();
		System.out.println("Ex");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		cancelTask();
		(CT = new ContractTask()).execute();
		System.out.println("Co");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	

}

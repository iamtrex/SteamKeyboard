package com.rweqx.constants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * UI Constant Methods/Objects
 * @author RWEQX
 *
 */
public class UIM {

	public static class WinButtonX extends WinButton{
		public WinButtonX(){
			super();
			this.setIcon(IconParser.getIcon(UIC.WIN_X_ICON_FILE_PATH, UIC.WIN_BAR_SIZE, UIC.WIN_BAR_SIZE));
			
		}
	}
	public static class WinButtonMin extends WinButton{
		public WinButtonMin(){
			super();
			this.setIcon(IconParser.getIcon(UIC.WIN_MIN_ICON_FILE_PATH, UIC.WIN_BAR_SIZE, UIC.WIN_BAR_SIZE));
			
		}
	}
	public static class WinButtonMax extends WinButton{
		public WinButtonMax(){
			super();
			this.setIcon(IconParser.getIcon(UIC.WIN_MAX_ICON_FILE_PATH, UIC.WIN_BAR_SIZE, UIC.WIN_BAR_SIZE));
			
		}
	}
	public static class WinButton extends JButton{
		public WinButton(){
			super();
			setup();
		}
		
		private void setup(){
			this.setBorderPainted(false);
			this.setBackground(UIC.BUTTON_ACTIVE_FALSE_COLOR);
			this.setFocusable(false);
		}
		
		
	}
	public static class APMLabel extends JLabel{
		public APMLabel(){
			super();
			setup();
		}
		public APMLabel(String s){
			super(s);
			setup();
		}
		
		private void setup(){
			this.setFont(UIC.APM_LABEL_FONT);
			this.setForeground(UIC.FONT_DEFAULT_COLOR);
		}
		
		
	}
	
	public static class SettingsButton extends JButton{
		public SettingsButton(){
			setup();
		}
		
		private void setup(){
			Dimension d = new Dimension(UIC.ICON_SIZE, UIC.ICON_SIZE);
			this.setSize(d);
			this.setPreferredSize(d);
			this.setBorderPainted(false);
			this.setBackground(UIC.BUTTON_ACTIVE_FALSE_COLOR);
			this.setIcon(IconParser.getIcon(UIC.SETTINGS_ICON_FILE_PATH, UIC.ICON_SIZE, UIC.ICON_SIZE));
		}
		
		
	}
	
	
	public static class KeyButtons extends JButton{
		
		public KeyButtons(){
			super();
			setup();
		}
		public KeyButtons(String s){
			super(s);
			setup();
		}
		
		private void setup(){
			this.setBorder(BorderFactory.createLineBorder(UIC.BUTTON_BORDER_COLOR, 1));
			this.setSelected(false);
			this.setFocusable(false);
			setActive(false);
			
		}

		//Simple color invert when button is 'active'
		public void setActive(boolean b){
			if(b){
				this.setForeground(UIC.BUTTON_ACTIVE_FALSE_COLOR);
				this.setBackground(UIC.BUTTON_ACTIVE_TRUE_COLOR);
				
			}else{
				this.setForeground(UIC.BUTTON_ACTIVE_TRUE_COLOR);
				this.setBackground(UIC.BUTTON_ACTIVE_FALSE_COLOR);
				
			}
		}
		
	}
	
	
	public static void addComponent(JComponent addMe, JPanel addTo, int x, int y, int w, int h, int wx, int wy, int fill,
			int anc, int a, int b, int d, int e) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		c.fill = fill;
		c.anchor = anc;
		c.weightx = wx;
		c.weighty = wy;
		c.insets = new Insets(a, b, d, e);
		addTo.add(addMe, c);
		
	}
}

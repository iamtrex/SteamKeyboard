package com.rweqx.UI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rweqx.IO.IOSettings;
import com.rweqx.calc.Logic;
import com.rweqx.constants.UIM;
import com.rweqx.constants.UIM.WinButtonMax;
import com.rweqx.constants.UIM.WinButtonMin;
import com.rweqx.constants.UIM.WinButtonX;

public class TopBar implements MouseMotionListener, MouseListener, ActionListener {
	JPanel mainPanel;
	
	Logic l;
	IOSettings IOS;
	
	public TopBar(Logic l, IOSettings IOS){
		this.l = l;
		this.IOS = IOS;
		
		createTB();
	}
	
	WinButtonMin WBMin = new WinButtonMin();
	WinButtonMax WBMax = new WinButtonMax();
	WinButtonX WBX = new WinButtonX();
	
	private void createTB(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		
		
		WBMin.addActionListener(this);
		WBMax.addActionListener(this);
		WBX.addActionListener(this);
		
		JLabel blank = new JLabel();
		
		UIM.addComponent(blank, mainPanel, 0, 0, 1, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 0, 0, 0);
		
		UIM.addComponent(WBMin, mainPanel, 1, 0, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST, 0, 0, 0, 0);
	//	UIM.addComponent(WBMax, mainPanel, 2, 0, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST, 0, 0, 0, 0);
		UIM.addComponent(WBX, mainPanel, 3, 0, 1, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST, 0, 0, 0, 0);
		
		mainPanel.addMouseMotionListener(this);
		mainPanel.addMouseListener(this);
		
		
		
	}
	
	
	public JPanel getPanel() {
		return mainPanel;
	}

	Point p = null;
	@Override
	public void mousePressed(MouseEvent arg0) {
		p = arg0.getPoint();
	}

	Timer t;
	@Override
	public void mouseDragged(MouseEvent arg0) {
		Point pN = arg0.getLocationOnScreen();
		JFrame top = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
		top.setLocation(pN.x - p.x, pN.y - p.y);
	}
	@Override
	public void mouseMoved(MouseEvent IGNORED) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		p = null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		JFrame top = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
		
		if(source == WBMin){
			top.setState(Frame.ICONIFIED);
		}else if(source == WBMax){
			top.setState(Frame.MAXIMIZED_BOTH);
		}else if(source == WBX){
			top.dispatchEvent(new WindowEvent(top, WindowEvent.WINDOW_CLOSING));
		}
		
	}

}

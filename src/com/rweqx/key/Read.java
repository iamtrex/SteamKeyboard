package com.rweqx.key;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;


import com.rweqx.UI.Window;
import com.rweqx.calc.Logic;
import com.rweqx.constants.Translator;
import com.rweqx.start.Log;

/**
 * Reads keypresses
 * @author RWEQX
 *
 */
public class Read implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener{
	Logic l;
	Window w;
	
	public Read(Logic l, Window w){
		this.l = l;
		this.w = w;
	}
	
	private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

	//Register JNativeHook
	public void setupAndStartKeyTracking(){
		
		try{
			if(GlobalScreen.isNativeHookRegistered()){
				GlobalScreen.unregisterNativeHook();
				Log.log("Unregistered global hook");
			}
			
			
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(this);
			GlobalScreen.addNativeMouseListener(this);
			GlobalScreen.addNativeMouseMotionListener(this);
			// Disable parent logger and set the desired level.
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.ALL);

			/*
			// Add our custom formatter to a console handler.
			ConsoleHandler handler = new ConsoleHandler();
			handler.setFormatter(new LogFormatter());
			handler.setLevel(Level.WARNING);
			logger.addHandler(handler);
			*/
			
			
			Log.log("Successfully registered Native Hook");
		}catch(Exception e){
			e.printStackTrace();
			Log.log(e.getMessage(), "NativeHook failed to register, program dead", true);
		}

	}

	
	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		l.newMousePosition(x,y);
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		int buttonID = arg0.getButton();
		String buttonName = Translator.getMouseButtonFromID(buttonID);
		l.mousePress(buttonName, true);
		w.mousePress(buttonName, true);
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		int buttonID = arg0.getButton();
		String buttonName = Translator.getMouseButtonFromID(buttonID);
		l.mousePress(buttonName, false);
		w.mousePress(buttonName, false);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		String keyID = NativeKeyEvent.getKeyText(arg0.getKeyCode());
		String keyName = Translator.getKeyFromID(keyID);
		l.keyPress(keyName, true);
		w.keyPress(keyName, true);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		String keyID = NativeKeyEvent.getKeyText(arg0.getKeyCode());
		
		String keyName = Translator.getKeyFromID(keyID);
		
		System.out.println(arg0.getKeyCode() + " is "+ keyID);
		
		l.keyPress(keyName, false);
		w.keyPress(keyName, false);
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent UNUSED) {}
	@Override
	public void nativeMouseClicked(NativeMouseEvent UNUSED) {}
	
	
	@Override
	public void nativeMouseDragged(NativeMouseEvent UNUSED) {}
	
	/*
	private final class LogFormatter extends Formatter {
		@Override
		public String format(LogRecord record) {
			StringBuilder line = new StringBuilder();

			line.append(new Date(record.getMillis()))
				.append(" ")
				.append(record.getLevel().getLocalizedName())
				.append(":\t")
				.append(formatMessage(record));

			if (record.getThrown() != null) {
				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					record.getThrown().printStackTrace(pw);
					pw.close();
					line.append(sw.toString());
					sw.close();
				}
				catch (Exception ex) {}
			}

			return line.toString();
		}
	}*/
}

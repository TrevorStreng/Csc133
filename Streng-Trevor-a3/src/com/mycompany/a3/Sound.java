package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound implements Runnable {
	private Media m;
	public Sound(String fileName) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(
				getClass(), "/" + fileName);
				m = MediaManager.createMedia( is , "audio/wav", this);
			} catch (Exception err) { 
					err.printStackTrace(); 
			}
	}
	public void play() {
		if(m.isPlaying()) {
			m.pause();
		} else {
			m.play();
		}
	}
	public void run() {
		m.setTime(0);
		m.play();
		m.pause();
	}
	public void pause() {
		m.pause();
	}
	
}

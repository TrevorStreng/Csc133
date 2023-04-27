package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class SoundCommand extends Command {
	private GameWorld gw;
	public SoundCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * changing sound flag when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		boolean s = gw.getSound();
		if(s) gw.setSound(false);
		else gw.setSound(true);
	}
	
}

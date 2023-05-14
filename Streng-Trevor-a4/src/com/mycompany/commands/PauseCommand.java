package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class PauseCommand extends Command {
	private GameWorld gw;
	public PauseCommand(GameWorld gw, String w) {
//		if(!gw.getPause()) w = "play";
		super(w);
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.isPaused();
	}
}

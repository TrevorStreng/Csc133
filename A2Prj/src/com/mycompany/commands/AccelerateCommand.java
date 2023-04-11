package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.*;

public class AccelerateCommand extends Command {
	private GameWorld gw;
	public AccelerateCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * accelerating robot when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		gw.accelerate();
	}
}

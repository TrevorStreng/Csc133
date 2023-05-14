package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.*;

public class LeftTurnCommand extends Command {
	private GameWorld gw;
	public LeftTurnCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * notifying of left turn when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		gw.left();
	}
		
}

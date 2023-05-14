package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class BrakeCommand extends Command {
	private GameWorld gw;
	public BrakeCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * slowing down robot when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		gw.brake();
	}
}

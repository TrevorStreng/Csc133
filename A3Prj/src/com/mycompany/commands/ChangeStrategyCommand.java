package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ChangeStrategyCommand extends Command {
	private GameWorld gw;
	public ChangeStrategyCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * changing strategy on all non player robots when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		gw.changeStrategy();
	}
}

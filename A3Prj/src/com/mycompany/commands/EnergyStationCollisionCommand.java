package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class EnergyStationCollisionCommand extends Command {
	private GameWorld gw;
	public EnergyStationCollisionCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * notifying of collision with energy station when button is clicked 
	 */
	public void actionPerformed(ActionEvent e) {
		gw.energyCollision();
	}
}

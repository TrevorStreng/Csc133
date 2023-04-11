package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class DroneCollisionCommand extends Command {
	private GameWorld gw;
	public DroneCollisionCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * notifying of collision with drone when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		gw.droneCollision();
	}
}

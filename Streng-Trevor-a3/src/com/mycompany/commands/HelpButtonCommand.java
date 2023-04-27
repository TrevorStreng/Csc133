package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpButtonCommand extends Command {
	public HelpButtonCommand(String w) {
		super(w);
	}
	/*
	 * listing help commands on screen when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		String list = "Controls: \n"
				+ "a: accelerate\n"
				+ "b: brake\n"
				+ "l: tuirn left\n"
				+ "r: turn right\n"
				+ "e: collide with energy station\n"
				+ "g: collide with drone\n"
				+ "t: clock tick";
		Dialog.show("Help", list, "Done", null);
	}
}

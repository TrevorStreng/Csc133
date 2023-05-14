package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	public AboutCommand(String w) {
		super(w);
	}
	/*
	 * showing about tab when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		String list = "Creator: Trevor Streng\n"
				+ "CSC 133: Object Oriented Computer graphics Programming\n"
				+ "Version: 1.1.0";
		Dialog.show("About", list, "Done", null);
	}
}

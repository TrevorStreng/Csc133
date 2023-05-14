package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class QuitCommand extends Command {
	public QuitCommand(String w) {
		super(w);
	}
	/*
	 * prompting to make sure they actually want to quit game and then exiting if asked
	 */
	public void actionPerformed(ActionEvent e) {
		Command cOk = new Command("Yes");
		Command cNo = new Command("No");
		Command quit = Dialog.show("Quit", "Do you want to exit the game?", cOk, cNo);
		if(quit == cOk) {
			System.exit(0);
		}
	}
}

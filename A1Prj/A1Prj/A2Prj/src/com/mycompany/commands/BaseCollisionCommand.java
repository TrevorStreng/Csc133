package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class BaseCollisionCommand extends Command {
	private GameWorld gw;
	public BaseCollisionCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	/*
	 * getting to next base when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		Command enter = new Command("Enter");
		TextField myTF = new TextField();
		try {
			Dialog.show("Enter base number", myTF, enter);
			int baseNum = Integer.parseInt(myTF.getText().toString());
			if(baseNum < 1 || baseNum > 9) {
				System.out.println("Please enter a valid base number.");
			}
			gw.baseCollision(baseNum);
		} catch (NumberFormatException err) {
			Dialog.show("Error", "Please try again.", "Enter", null);
		}
	}
}

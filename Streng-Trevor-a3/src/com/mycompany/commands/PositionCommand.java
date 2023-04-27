package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Fixed;
import com.mycompany.a3.GameObjects;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;

public class PositionCommand extends Command {
	private GameWorld gw;
	public PositionCommand(GameWorld gw, String w) {
		super(w);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(gw.getPause()) gw.setSelect();
		IIterator it  = gw.getGameObjects().getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof Fixed) {
				if(((Fixed)temp).isSelected()) ((Fixed)temp).setSelected(false);
			}
		}
	}
	
}

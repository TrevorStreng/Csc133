package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	public boolean exit = false;
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}

	/*
	 * accepts inputs from command line
	 * to make things happen in game
	 */
	private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0) {
					switch(sCommand.charAt(0)) {
						case 'a':
							gw.accelerate();
								break;
						case 'b':
							gw.brake();
							break;
						case 'l':
							gw.left();
							break;
						case 'r':
							gw.right();
							break;
						case 'c':
							gw.robotCollision();
							break;
						case '1':
							gw.baseCollision(1);
							break;
						case '2':
							gw.baseCollision(2);
							break;
						case '3':
							gw.baseCollision(3);
							break;
						case '4':
							gw.baseCollision(4);
							break;
						case '5':
							gw.baseCollision(5);
							break;
						case '6':
							gw.baseCollision(6);
							break;
						case '7':
							gw.baseCollision(7);
							break;
						case '8':
							gw.baseCollision(8);
							break;
						case '9':
							gw.baseCollision(9);
							break;
						case 'e':
							gw.energyCollision();
							break;
						case 'g':
							gw.droneCollision();
							break;
						case 't':
							gw.clockTick();
							break;
						case 'd':
							gw.display();
							break;
						case 'm':
							gw.map();
							break;
						case 'x':
							gw.exit();
							exit = true;
							break;
						case 'y':
							if(exit) {
								gw.yes();
							}
							break;
						case 'n':
							gw.no();
							break;
						default:
							System.out.println("Try another key!");
							break;
					} //switch
				}
			} //actionPerformed
		} //new ActionListener()
		); //addActionListener
	} // play
}

package com.mycompany.a4;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private Label clockLabel;
	private Label clockTimeLabel;
	private Label livesLabel;
	private Label livesLeftLabel;
	private Label baseReachedLabel;
	private Label baseNumLabel;
	private Label energyLabel;
	private Label energyLvlLabel;
	private Label damageLabel;
	private Label damageLvlLabel;
	private Label soundLabel;
	private Label onOffLabel;
	private GameWorld gw;
	
	
	/*
	 * For showing stats at top of screen under title
	 */
	public ScoreView() {
		setInfoLayout();
		setClockLabel();
		setLivesLabel();
		setBaseReachedLabel();
		setEnergyLabel();
		setDamageLevelLabel();
		setSoundLabel();
	}
	private void setInfoLayout() {
		this.setLayout(new FlowLayout(Component.CENTER));
	}
	
	/*
	 * Setting labels and assigning them initial values
	 */
	private void setClockLabel() {
		clockLabel = styles(new Label("Time: "));
		clockTimeLabel = styles(new Label("000"));
		this.add(clockLabel);
		this.add(clockTimeLabel);
	}
	private void setLivesLabel() {
		livesLabel = styles(new Label("Lives Left:"));
		livesLeftLabel = styles(new Label("0"));
		this.add(livesLabel);
		this.add(livesLeftLabel);
	}
	private void setBaseReachedLabel() {
		baseReachedLabel = styles(new Label("Player Last base Reached:"));
		baseNumLabel = styles(new Label("00"));
		this.add(baseReachedLabel);
		this.add(baseNumLabel);
	}
	private void setEnergyLabel() {
		energyLabel = styles(new Label("Player Energy Level:"));
		energyLvlLabel = styles(new Label("000"));
		this.add(energyLabel);
		this.add(energyLvlLabel);
	}
	private void setDamageLevelLabel() {
		damageLabel = styles(new Label("Player Damage Level:"));
		damageLvlLabel = styles(new Label("000"));
		this.add(damageLabel);
		this.add(damageLvlLabel);
	}
	private void setSoundLabel() {
		soundLabel = styles(new Label("Sound: "));
		onOffLabel = styles(new Label("OFF"));
		this.add(soundLabel);
		this.add(onOffLabel);
	}
	/*
	 * Reacts to the gameworld updating and tells the values in the labels to update
	 */
	public void update(Observable o, Object arg) {
		gw = (GameWorld)arg;
		/*
		 * not sure why sometimes the numbers on damageLevel and clockTime glitch out
		 * but sometimes they work
		 */
		this.clockTimeLabel.setText("" + gw.getClock());
		this.livesLeftLabel.setText("" + gw.getLife());
		this.baseNumLabel.setText("" + gw.getLastBaseReached());
		this.energyLvlLabel.setText("" + gw.getEnergyLevel());
		this.damageLvlLabel.setText("" + gw.getDamageLevel());
		if(gw.getSound()) {
			this.onOffLabel.setText("ON");
		}
		else {
			this.onOffLabel.setText("OFF");
		}
		this.repaint();
	}
	
	/*
	 * Adding space between labels
	 */
	private Label styles(Label l) {
		l.getAllStyles().setPadding(RIGHT, 1);
		return l;
	}
}

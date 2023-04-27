package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Drone extends Moveable {
	// variables
	private int droneColor;
	private int colorR = 0;
	private int colorG = 255;
	private int colorB = 255;
	Random random = new Random();
	private int direction = random.nextInt(360);
	private int speed = random.nextInt(10);
	
	// constructor
	public Drone(int size, float x, float y) {
		super(size, x, y);
		this.droneColor = ColorUtil.rgb(colorR, colorB, colorG);
		this.setHeading(direction);
		this.setSpeed(speed);
	};
	
	// methods
	public int getDroneColor() {
		return droneColor;
	}
	
	// toString method
	public String toString() {
		return "Drone: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + "," + Math.round(this.getLocation().getX() * 1.0)
				+ " color = [" + this.colorR + "," + this.colorG + "," + this.colorB + "]" 
				+ " heading = " + this.getHeading() + " speed = " + this.getSpeed() 
				+ " size = " + this.getSize();
	}
}

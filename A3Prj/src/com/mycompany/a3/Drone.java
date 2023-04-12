package com.mycompany.a3;

public class Drone extends Moveable {
	
	// Constructor
	public Drone(int color) {
		super(color);
	}
	
	// toString method
	public String toString() {
		return "Drone: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
				+ " heading = " + this.getHeading() + " speed = " + this.getSpeed();
//				+ " size = " + this.getSize();
	}
}

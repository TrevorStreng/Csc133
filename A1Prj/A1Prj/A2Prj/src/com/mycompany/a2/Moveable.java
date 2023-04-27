package com.mycompany.a2;

public abstract class Moveable extends GameObjects {
	private int heading;
	private int speed;
//	private Point location;
	
	// constructor
	public Moveable(int size, int color) {
		super(size, color);
	}
	public Moveable(int color) {
		super(color);
	}
	
	/*
	 * allows objects to move and makes sure they don't go off the map
	 */
	public void move() {
		// convert from degrees to radians
		float deltaX = (float) Math.cos(Math.toRadians(90-heading)) * getSpeed();
		float deltaY = (float) Math.sin(Math.toRadians(90-heading)) * getSpeed();
//		location = getLocation();
		float oldX = this.getX();
		float oldY = this.getY();
		float newX = oldX + deltaX;
		if(newX > 1024) {
			float temp = newX - 1024;
			newX = 1024 - temp;
		}
		float newY = oldY + deltaY;
		if(newY > 768) {
			float temp = newY - 768;
			newY -= 768 - temp;
		}
		this.setLocation(newX, newY);
	}
	
	// getters and setters
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHeading() {
		return heading;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}

}

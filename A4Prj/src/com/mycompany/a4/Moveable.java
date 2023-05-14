package com.mycompany.a4;

//import com.codename1.ui.Transform;

public abstract class Moveable extends GameObjects {
	private int heading;
	private int speed;
	private int w = GameWorld.getMVWidth();
	private int h = GameWorld.getMVHeight();
	boolean bounceX = false;
	boolean bounceY = false;
	private float dx;
	private float dy;
	
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
	public void move(double elapsedTime) {
		if(this instanceof ShockWave) {}
		else {
			if(getX() < 0) {
				this.rotate((float)Math.toRadians(2 * (heading)));
				heading = heading - (2 * heading);
			}
			if(getX() > w) {
				this.rotate((float)Math.toRadians(2 * (heading)));
				heading = heading - (2 * heading);
			}
			if(getY() < 0) {
				this.rotate((float)Math.toRadians(2 * (heading - 90)));
				heading = heading - (2 * (heading - 90));
			}
			if(getY() > h) {
				this.rotate((float)Math.toRadians(2 * (heading - 90)));
				heading = heading - (2 * (heading - 90));
			}
		}
		double distX = (elapsedTime/100) * speed;
		double distY = (elapsedTime/100) * speed;
		dx = (float) (Math.cos(Math.toRadians(90-getHeading())) * distX);
		dy = (float) (Math.sin(Math.toRadians(90-getHeading())) * distY);

		this.translate(dx, dy);
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

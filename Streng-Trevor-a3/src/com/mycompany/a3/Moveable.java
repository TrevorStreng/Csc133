package com.mycompany.a3;



public abstract class Moveable extends GameObjects {
	private int heading;
	private int speed;
//	float deltaX = 1;
//	float deltaY = 1;
	private int w = GameWorld.getMVWidth();
	private int h = GameWorld.getMVHeight();
	boolean bounceX = false;
	boolean bounceY = false;
	private float dx;
	private float dy;
	
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
	public void move(double elapsedTime) {
		// if it works it works
		if(getX() < 0) {
			bounceX = !bounceX;
		}
		if(getX() > w) {
			bounceX = !bounceX;
		}
		if(getY() < 0) {
			bounceY = !bounceY;
		}
		if(getY() > h) {
			bounceY = !bounceY;
		}
		double distX = (elapsedTime/100) * speed;
		double distY = (elapsedTime/100) * speed;
		if(bounceX) {
			distX *= -1;
		}
		if(bounceY) {
			distY *= -1;
		}
		dx = (float) (Math.cos(Math.toRadians(90-getHeading())) * distX);
		dy = (float) (Math.sin(Math.toRadians(90-getHeading())) * distY);
		float oldX = this.getX();
		float oldY = this.getY();
		float newX = oldX - dx;
		float newY = oldY - dy;

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
	public float getDx() {
		return dx;
	}
	public void setDx(float dX) {
		this.dx = dX;
	}
	public float getDy() {
		return dy;
	}
	public void setDy(float dY) {
		this.dy = dY;
	}
}

package com.mycompany.a1;
import com.codename1.charts.models.Point;


public abstract class GameObject {
	private int size;
	private float x;
	private float y;
	private int color;
	private Point location;
	
	// constructor
	public GameObject(int size, float x, float y) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.location = new Point(x,y);
	}
	
	//getters and setters
	public int getSize() {
		return size;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Point getLocation() {
		return location;
	}
	/*
	 * used to update location of objects that move
	 */
	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
	}
}

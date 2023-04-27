package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObjects {
//	private float x;
//	private float y;
	private int color;
	private Point location;
	private int size;
	Random random = new Random();
	
	// constructor
	public GameObjects(int size, int color) {
		this.color = color;
		this.size = size;
		float x = random.nextFloat() * 1000;
		float y = random.nextFloat() * 1000;
		this.location = new Point(x, y);
	}
	public GameObjects(int color) {
		this.color = color;
		float x = random.nextFloat() * 1000;
		float y = random.nextFloat() * 1000;
		this.location = new Point(x, y);
	}
	
	//getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getX() {
		return location.getX();
	}
	public void setX(float x) {
		location.setX(x);
	}
	public float getY() {
		return location.getY();
	}
	public void setY(float y) {
		location.setX(y);
	}
	/*
	 * used to update location of objects that move
	 */
	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
	}
	public Point getLocation() {
		return this.location;
	}
	
	public String colorToString() {
		return "[" + ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color) + "]";
	}

}

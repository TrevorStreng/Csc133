package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int capacity;
	private int size;
	private int color;
	Random random = new Random();

	// constructor
	public EnergyStation() {
		super(ColorUtil.rgb(0, 0, 255));
		this.size = random.nextInt(50);
		this.capacity = size;
	}
	
	// getters and setters
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getEnergyStationColor() {
		return color;
	}
	public void setEnergyStationColor(int energyStationColor) {
		this.color = energyStationColor;
	}
	
	// toString method
	public String toString() {
		return "EnergyStation: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
				+ " size = " + this.getSize() 
				+ " capacity = " + this.getCapacity();
	}

}

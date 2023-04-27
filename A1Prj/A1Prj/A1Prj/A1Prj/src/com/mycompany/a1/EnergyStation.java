package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	
	private int capacity;
	private int size;
	private int energyStationColor;
	private int colorR = 0;
	private int colorG = 0;
	private int colorB = 255;
	
	// constructor
	public EnergyStation(int random, float x, float y) {
		super(random, x ,y);
		this.capacity = random;
		this.size = random;
		this.energyStationColor = ColorUtil.rgb(colorR, colorG, colorB);
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
		return energyStationColor;
	}
	public void setEnergyStationColor(int energyStationColor) {
		this.energyStationColor = energyStationColor;
	}
	
	// toString method
	public String toString() {
		return "EnergyStation: " + "loc = " + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color = [" + this.colorR + "," + this.colorG + "," + this.colorB + "]" 
				+ " size = " + this.getSize() 
				+ " capacity = " + this.getCapacity();
	}
}

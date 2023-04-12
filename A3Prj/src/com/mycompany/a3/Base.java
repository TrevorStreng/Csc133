package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed {
	private int sequenceNumber = 1; 
	Random random = new Random();
	
	//constructor
	public Base(int sequenceNumber) {
		super(ColorUtil.rgb(255, 0, 0));
		this.sequenceNumber = sequenceNumber;
	}
	
	// getters
	public int getBaseNumber() {
		return sequenceNumber;
	}
	public void setSize() {}
	// toString method
	public String toString() {
		return "Base: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
//				+ " size = " + this.getSize() 
				+ " seqNum = " + this.getBaseNumber();
	}
}

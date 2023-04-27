package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed {
	private int sequenceNumber = 1; 
	private int baseColor;
	private int colorR = 0;
	private int colorG = 255;
	private int colorB = 0;
	
	//constructor
	public Base(int size, float x, float y, int sequenceNumber) {
		super(size, x, y);
		this.sequenceNumber = sequenceNumber;
		this.baseColor = ColorUtil.rgb(colorR, colorG, colorB);
	}
	
	// getters
	public int getBaseNumber() {
		return sequenceNumber;
	}
	public int getBaseColor() {
		return baseColor;
	}

	// toString method
	public String toString() {
		return "Base: " + "loc = " + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color = [" + this.colorR + "," + this.colorG + "," + this.colorB + "]" 
				+ " size = " + this.getSize() 
				+ " seqNum = " + this.getBaseNumber();
	}
}

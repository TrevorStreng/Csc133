package com.mycompany.a2;

public class Robot extends Moveable {
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int maximumDamageLevel;
	private int lastBaseReached;
	private int steeringDirection;
//	private int robotColor;
	private int size;

	
	// constructor
	public Robot (int color) {
		super(color);
		this.setDamageLevel(0);
		this.setEnergyConsumptionRate(1);
		this.setMaximumSpeed(30);
		super.setSize(40);
		this.setSteeringDirection(0);
		this.setEnergyLevel(100);
		this.setLastBaseReached(1);
	}
	// methods
	/*
	 * adjust color to lighter color after robot takes damage 
	 */
//	public void resetColor() {
//		this.colorR = colorR - 50;
//		this.colorG = colorR + 50;
//		this.colorB = colorR + 50;
//		this.robotColor = ColorUtil.rgb(colorR, colorG, colorB);
//	}
	
	
	// Getters and setters
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	public int getEnergyLevel() {
		return energyLevel;
	}
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public void setEnergyConsumptionRate(int rate ) {
		this.energyConsumptionRate = rate;
	}
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	public int getMaximumDamageLevel() {
		return maximumDamageLevel;
	}
	public void setMaximumDamageLevel(int damageLevel) {
		this.maximumDamageLevel = damageLevel;
	}
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}
	public int getSteeringDirection() {
		return steeringDirection;
	}
	public void setSteeringDirection(int steeringDirection) {
		this.steeringDirection = steeringDirection;
	}
//	public int getRobotColor() {
//		return robotColor;
//	}
	public int getSize() {
		return size;
	}
	
	/*
	 * increases damage level after collision with drone or another robot
	 */
	public void takeDamage() {
		int damageLevel = getDamageLevel();
		damageLevel += 1;
		setDamageLevel(damageLevel);
	}
	
	public void reset(float x, float y) {
		this.setLocation(x, y);
		this.setHeading(0);
		this.setSpeed(0);
		this.setMaximumSpeed(30);
		this.setEnergyConsumptionRate(1);
		this.setEnergyLevel(100);
		this.setMaximumDamageLevel(100);
		this.setDamageLevel(0);
	}
	
	// toString method
	public String toString() {
		return "Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0) 
				+ " color = " + this.colorToString() 
				+ " heading = " + this.getHeading() 
				+ " speed = " + this.getSpeed() 
//				+ " size = " + this.getSize()
				+ " maxSpeed = " + this.getMaximumSpeed()
				+ " steeringDirection = " + this.getSteeringDirection()
				+ " energyLevel = " + this.getEnergyLevel()
				+ " damageLevel = " + this.getDamageLevel();
	}

}

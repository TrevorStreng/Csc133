package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Robot extends Moveable implements ISteerable {
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int maximumDamageLevel;
	private int lastBaseReached;
	private int steeringDirection;
	private int robotColor;
	private int colorR = 255;
	private int colorG = 0;
	private int colorB = 0;
	
	// constructor
	public Robot(int size, float x, float y) {
		super(40, x, y);
		super.setSpeed(0);
		super.setHeading(0);
		this.maximumSpeed = 30;
		this.steeringDirection = 0;
		this.energyLevel = 100;
		this.damageLevel = 0;
		this.maximumDamageLevel = 100;
		this.lastBaseReached = 1;
		this.energyConsumptionRate = 1;
		this.robotColor = ColorUtil.rgb(colorR, colorG, colorB);
	}
	// methods
	/*
	 * increments speed by one
	 */
	public void accelerateRobot() {
		int speed = getSpeed();
		if(speed < maximumSpeed) {
			setSpeed(++speed);
		} else {
			System.out.println("You are at the maximum speed.");
		}
	}
	/*
	 * reduces speed by one
	 */
	public void brakeRobot() {
		int speed = getSpeed();
		if(speed > 0) {
			setSpeed(--speed);
		}
	}
	/*
	 * changes direction by 5 degrees at as time
	 */
	public void leftRobot() {
		int direction = getSteeringDirection();
		int heading = getHeading();
		direction -= 5;
		if(direction < -40) {
			System.out.println("You can only turn 40 degrees.");
		} else {
			this.setSteeringDirection(direction);
			this.setHeading(heading - 5);
		}
	}
	public void rightRobot() {
		int direction = getSteeringDirection();
		int heading = getHeading();
		direction += 5;
		if(direction > 40) {
			System.out.println("You can only turn 40 degrees.");
		} else {
			this.setSteeringDirection(direction);
			this.setHeading(heading - 5);
		}
	}
	
	/*
	 * increases damage level after collision with drone or another robot
	 */
	public void takeDamage() {
		this.damageLevel++;
	}
	/*
	 * checks to see if at max damage level
	 */
	public boolean maxDamage(int damageLevel) {
		if(damageLevel == maximumDamageLevel) {
			return true;
		}
		return false;
	}
	/*
	 * adjust color to lighter color after robot takes damage 
	 */
	public void resetColor() {
		this.colorR = colorR - 50;
		this.colorG = colorR + 50;
		this.colorB = colorR + 50;
		this.robotColor = ColorUtil.rgb(colorR, colorG, colorB);
	}
	
	
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
//	public void setEnergyConsumptionRate(int energyConsumptionRate) {
//		this.energyConsumptionRate = energyConsumptionRate;
//	}
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	public int getMaximumDamageLevel() {
		return maximumDamageLevel;
	}
//	public void setMaximumDamageLevel(int maximumDamageLevel) {
//		this.maximumDamageLevel = maximumDamageLevel;
//	}
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
	public int getRobotColor() {
		return robotColor;
	}
	
	// toString method
	public String toString() {
		return "Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + "," + Math.round(this.getLocation().getY() * 1.0) 
				+ " color = [" + this.colorR + "," + this.colorG + "," + this.colorB + "]" 
				+ " heading = " + this.getHeading() 
				+ " speed = " + this.getSpeed() 
				+ " size = " + this.getSize()
				+ " maxSpeed = " + this.getMaximumSpeed()
				+ " steeringDirection = " + this.getSteeringDirection()
				+ " energyLevel = " + this.getEnergyLevel()
				+ " damageLevel = " + this.getDamageLevel();
	}
}

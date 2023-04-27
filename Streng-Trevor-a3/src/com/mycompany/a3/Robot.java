package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Robot extends Moveable {
	private int maximumSpeed;
	private int energyLevel;
	private double energyConsumptionRate;
	private int damageLevel;
	private int maximumDamageLevel;
	private int lastBaseReached;
	private int steeringDirection;
	private int colorR;
	Vector<GameObjects> collisionVector = new Vector<GameObjects>();

	
	// constructor
	public Robot (int color) {
		super(ColorUtil.rgb(255, 0, 0));
		this.setDamageLevel(-5);
		this.setEnergyConsumptionRate(1);
		this.setMaximumSpeed(30);
		super.setSize(100);
		this.setSteeringDirection(0);
		this.setEnergyLevel(100);
		this.setLastBaseReached(1);
		this.colorR = 255;
	}
	// methods
	/*
	 * adjust color to lighter color after robot takes damage 
	 */
	public void resetColor() {
		colorR -= 12;
		this.setColor(ColorUtil.rgb(colorR, 0, 0));
		
	}
	
	// why this needed here?
	public void draw(Graphics g, Point pCmdRelPrnt) {
		// I just dealt with it individually
	}
	
	/*
	 * Increase speed by one
	 */
	public void accelerateRobot() {
		int speed = getSpeed();
		if(speed < getMaximumSpeed()) {
			speed += 2;
			setSpeed(speed);
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
			speed -= 2;
			setSpeed(speed);
		}
	}
	/*
	 * changes direction by 5 degrees at as time
	 */
	public void leftRobot() {
		int direction = getSteeringDirection();
		int heading = getHeading();
		direction += 5;
		if(direction > 40) {
			System.out.println("You can only turn 40 degrees.");
		} else {
			this.setSteeringDirection(direction);
			this.setHeading(heading + 5);
		}
	}
	public void rightRobot() {
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
	public void handleCollision(ICollider otherObject, GameWorld gw) {
		GameObjects otherObj = (GameObjects)otherObject;
		// check if collision has already been handled
		if(!collisionVector.contains(otherObj)) {
			collisionVector.add(otherObj);
			if(otherObj instanceof Robot) {
				gw.robotCollision(this, otherObj);
			}
		}
	}
	public void removeCollision(ICollider otherObject) {
		GameObjects otherObj = (GameObjects)otherObject;
		collisionVector.remove(otherObj);
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
	public double getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public void setEnergyConsumptionRate(double rate) {
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

	/*
	 * increases damage level after collision with drone or another robot
	 */
	public void takeDamage() {
		int damageLevel = getDamageLevel();
		damageLevel += 5;
		setDamageLevel(damageLevel);
		this.resetColor();
	}
	
	// toString method
	public String toString() {
		return "Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0) 
				+ " color = " + this.colorToString() 
				+ " heading = " + this.getHeading() 
				+ " speed = " + this.getSpeed() 
				+ " size = " + this.getSize()
				+ " maxSpeed = " + this.getMaximumSpeed()
				+ " steeringDirection = " + this.getSteeringDirection()
				+ " energyLevel = " + this.getEnergyLevel()
				+ " damageLevel = " + this.getDamageLevel();
	}

}

package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class PlayerRobot extends Robot implements ISteerable {
	private static PlayerRobot player;
	
	private PlayerRobot() {
		super(ColorUtil.rgb(255, 255, 255));
		this.setMaximumDamageLevel(25);
	}
	
	/*
	 * For use in gameWorld
	 */
	public static PlayerRobot getPlayerRobot() {
		if(player == null) {
			player = new PlayerRobot();
		}
		return player;
	}
	
	/*
	 * Increase speed by one
	 */
	public void accelerateRobot() {
		System.out.println("acc");
		int speed = getSpeed();
		if(speed < getMaximumSpeed()) {
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
			this.setHeading(heading + 5);
		}
	}
	/*
	 * checks to see if at max damage level
	 */
	public boolean maxDamage(int damageLevel) {
		int maximumDamageLevel = getMaximumDamageLevel();
		if(damageLevel == maximumDamageLevel) {
			return true;
		}
		return false;
	}
	
	public void resetColor() {
//		this.colorR = colorR - 50;
//		this.colorG = colorR + 50;
//		this.colorB = colorR + 50;
//		this.robotColor = ColorUtil.rgb(colorR, colorG, colorB);
	}
	public String toString() {
		return "Player Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0) 
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

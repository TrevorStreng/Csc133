package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerRobot extends Robot {
	private IStrategy strat;
	
	public NonPlayerRobot () {
		super(ColorUtil.rgb(255, 255, 0));
		this.setMaximumDamageLevel(40);
	}

	/*
	 * Assigning a strategy
	 * */
	public void setStrategy(IStrategy s) {
		strat = s;
	}
	
	/*
	 * Getting and assigning a strategy to non player robots
	 */
	public IStrategy getStrategy() {
		return strat;
	}
	public void invokeStrategy() {
		strat.strategy();
	}
	public String toString() {
		return "Non Player Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0) 
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

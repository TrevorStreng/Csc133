package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class NonPlayerRobot extends Robot {
	private IStrategy strat;
	
	public NonPlayerRobot () {
		super(ColorUtil.rgb(255, 0, 0));
		this.setMaximumDamageLevel(40);
		this.setSpeed(5);
	}
	
	/*
	 * Assigning a strategy
	 * */
	public void setStrategy(IStrategy s) {
		strat = s;
	}	
	
	public void draw(Graphics g, Point pCmdRelPrnt) {
		g.setColor(getColor());
		int arrX[] = {(int)(pCmdRelPrnt.getX() + getX() - (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() + (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() + (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() - (getSize()/2))};
		int arrY[] = {(int)(pCmdRelPrnt.getY() + getY() - (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() - (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() + (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() + (getSize()/2))};
		g.drawPolygon(arrX, arrY, 4);
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
				+ " size = " + this.getSize()
				+ " maxSpeed = " + this.getMaximumSpeed()
				+ " steeringDirection = " + this.getSteeringDirection()
				+ " energyLevel = " + this.getEnergyLevel()
				+ " damageLevel = " + this.getDamageLevel();
	}
	
}

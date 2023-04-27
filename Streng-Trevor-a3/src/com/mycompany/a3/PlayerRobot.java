package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerRobot extends Robot implements ISteerable {
	private static PlayerRobot player;
	
	private PlayerRobot() {
		super(ColorUtil.rgb(255, 0, 0));
		this.setMaximumDamageLevel(100);
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
	public void draw(Graphics g, Point pCmdRelPrnt) {
		g.setColor(getColor());
		int arrX[] = {(int)(pCmdRelPrnt.getX() + getX() - (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() + (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() + (getSize()/2)), (int)(pCmdRelPrnt.getX() + getX() - (getSize()/2))};
		int arrY[] = {(int)(pCmdRelPrnt.getY() + getY() - (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() - (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() + (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() + (getSize()/2))};
		g.fillPolygon(arrX, arrY, 4);
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

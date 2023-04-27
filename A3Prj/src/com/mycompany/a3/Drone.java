package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;
//import com.mycompany.a3.GameWorld;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Drone extends Moveable {
	Random random = new Random();
	Vector<GameObjects> collisionVector = new Vector<GameObjects>();
//	private GameWorld gw;
	
	// Constructor
	public Drone() {
		super(ColorUtil.rgb(150, 0, 255));
		this.setSize(100);
		this.setSpeed(5);
		this.setHeading(random.nextInt()*360);
	}
	
	public void draw(Graphics g, Point pCmdRelPrnt) {
		g.setColor(getColor());
		// Order: top-left, bottom, top-right
		int arrX[] = {((int)pCmdRelPrnt.getX() + (int)getX()) - (getSize()/2), ((int)pCmdRelPrnt.getX() + (int)getX()), ((int)pCmdRelPrnt.getX() + (int)getX() + (getSize()/2))};
		int arrY[] = {((int)pCmdRelPrnt.getY() + (int)getY()) - (getSize()/2), ((int)pCmdRelPrnt.getY() + (int)getY()) + (getSize()/2), ((int)pCmdRelPrnt.getY() + (int)getY() - (getSize()/2))};
		// draws triangle takes 2 points and an int of how many vertices
		g.drawPolygon(arrX, arrY, 3); 
	}
	

	public void handleCollision(ICollider otherObject, GameWorld gw) {
		GameObjects otherObj = (GameObjects)otherObject;
		// check if collision has already been handled
		if(!collisionVector.contains(otherObj)) {
			if(otherObj instanceof PlayerRobot) {
				gw.droneCollision(this, otherObj);
				collisionVector.add(otherObj);
//				int dmgLvl = ((PlayerRobot)otherObj).getDamageLevel();
//				dmgLvl += 5;
//				((PlayerRobot)otherObj).setDamageLevel(dmgLvl);
//				int speed = ((PlayerRobot)otherObj).getSpeed();
//				if(speed > 1) speed -= 2;
//				if(speed == 1) speed = 0;
//				((PlayerRobot)otherObj).setSpeed(speed);
			}
		}
	}
	public void removeCollision(ICollider otherObject) {
		GameObjects otherObj = (GameObjects)otherObject;
		collisionVector.remove(otherObj);
	}
	
	// toString method
	public String toString() {
		return "Drone: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
				+ " heading = " + this.getHeading() + " speed = " + this.getSpeed()
				+ " size = " + this.getSize();
	}
}

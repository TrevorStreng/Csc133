package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class PlayerRobot extends Robot implements ISteerable {
	private static PlayerRobot player;
	private RobotBody body;
	private RobotArm arms [];
	private RobotHead head;
	private RobotWheel wheels [];
	private Transform myTranslation, myRotation, myScale;
	
	private PlayerRobot() {
		super(ColorUtil.rgb(255, 0, 0));
		this.setMaximumDamageLevel(100);
		myTranslation = Transform.makeIdentity();
		myRotation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		body = new RobotBody();
//		body.scale(0.5f, 1);
		arms = new RobotArm [2];
		RobotArm arm1 = new RobotArm();
		arm1.translate(0, 2*getSize()/3);
		arm1.rotate(90);
		arm1.scale(1, 7);
		arms[0] = arm1;
		RobotArm arm2 = new RobotArm();
		arm2.translate(getSize()-1, 2*getSize()/3);
		arm2.rotate(-90);
		arm2.scale(1, 7);
		arms[1] = arm2;
		head = new RobotHead();
		wheels = new RobotWheel [2];
		RobotWheel wheel1 = new RobotWheel();
		wheel1.translate(-(getSize()/8), 0);
		wheels[0] = wheel1;
		RobotWheel wheel2 = new RobotWheel();
		wheel2.translate(7*getSize()/8, 0);
		wheels[1] = wheel2;
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
	public boolean getMove() {
		if(getSpeed() > 0) return true;
		return false;
	}
	public void draw(Graphics g, Point pCmdRelPrnt) {
		super.draw(g, pCmdRelPrnt);
		g.setColor(getColor());
		Point pCmdRelScrn = new Point((pCmdRelPrnt.getX() + getX() - (getSize()/2)), (int)(pCmdRelPrnt.getY() + getY() - (getSize()/2)));
		head.draw(g, pCmdRelPrnt, pCmdRelScrn);
		body.draw(g, pCmdRelPrnt);
		for(int i = 0; i < arms.length; i++) {
			arms[i].draw(g, pCmdRelPrnt, pCmdRelScrn);
		}
		for(int i = 0; i < wheels.length; i++) {
			wheels[i].draw(g, pCmdRelPrnt, pCmdRelScrn);
		}
	} 
//	/*
//	 * checks to see if at max damage level
//	 */
//	public boolean maxDamage(int damageLevel) {
//		int maximumDamageLevel = getMaximumDamageLevel();
//		if(damageLevel == maximumDamageLevel) {
//			return true;
//		}
//		return false;
//	}
	
//	public String toString() {
//		return "Player Robot: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0) 
//				+ " color = " + this.colorToString() 
//				+ " heading = " + this.getHeading() 
//				+ " speed = " + this.getSpeed() 
//				+ " size = " + this.getSize()
//				+ " maxSpeed = " + this.getMaximumSpeed()
//				+ " steeringDirection = " + this.getSteeringDirection()
//				+ " energyLevel = " + this.getEnergyLevel()
//				+ " damageLevel = " + this.getDamageLevel();
//	}
}

class RobotBody {
	public RobotBody() {
	}

	public void draw(Graphics g, Point pCmdRelPrnt) {
		PlayerRobot pr = PlayerRobot.getPlayerRobot();
		// draw the body
		int x[] = {(int)(pCmdRelPrnt.getX() + pr.getX() - (pr.getSize()/2)), (int)(pCmdRelPrnt.getX() + pr.getX() + (pr.getSize()/2)), (int)(pCmdRelPrnt.getX() + pr.getX() + (pr.getSize()/2)), (int)(pCmdRelPrnt.getX() + pr.getX() - (pr.getSize()/2))};
		int y[] = {(int)(pCmdRelPrnt.getY() + pr.getY() - (pr.getSize()/2)), (int)(pCmdRelPrnt.getY() + pr.getY() - (pr.getSize()/2)), (int)(pCmdRelPrnt.getY() + pr.getY() + (pr.getSize()/2)), (int)(pCmdRelPrnt.getY() + pr.getY() + (pr.getSize()/2))};
		g.fillPolygon(x, y, 4);	
	}
}
class RobotArm {
	private Transform myTranslation, myRotation, myScale;
	private int count;
	
	public RobotArm() {
		myTranslation = Transform.makeIdentity();
		myRotation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		count = 0;
	}
	public void draw(Graphics g, Point pCmdRelPrnt, Point pCmdRelScrn) { 
		PlayerRobot pr = PlayerRobot.getPlayerRobot();
		if(count == 5) {
			this.rotate(10);
		}
		if(count == 10) {
			this.rotate(-10);
			count = 0;
		}
		if(pr.getMove()) count++;
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gOrigXform = gXform.copy();
		gXform.translate(pr.getX() + pCmdRelPrnt.getX() - (pr.getSize()/2), (pr.getY() + pCmdRelPrnt.getY() + 2*(pr.getSize()/3)));
		gXform.concatenate(myTranslation);
		gXform.concatenate(myRotation);
		gXform.concatenate(myScale);
		gXform.translate(-(pr.getX() + pCmdRelPrnt.getX() - (pr.getSize()/2)), -((pr.getY() + pCmdRelPrnt.getY() + 2*(pr.getSize()/3))));

		g.setTransform(gXform);
		int x[] = {(int)(pCmdRelScrn.getX() - 10), (int)(pCmdRelScrn.getX()),(int)(pCmdRelScrn.getX() + 10)};
		int y[] = {(int)(pCmdRelScrn.getY()), (int)(pCmdRelScrn.getY() + 10), (int)(pCmdRelScrn.getY())};
		g.fillPolygon(x, y, 3);
		g.setTransform(gOrigXform);
	}
	public void rotate(double degrees) {
		myRotation.rotate((float)Math.toRadians(degrees), 0, 0);
	}
	public void scale(double sx, double sy) {
		myScale.scale((float)sx, (float)sy);
	}
	public void translate(double tx, double ty) {
		myTranslation.translate((float)tx, (float)ty);
	}
}
class RobotHead {
	public RobotHead() {}
	public void draw(Graphics g, Point pCmdRelPrnt, Point pCmdRelScrn) {
		PlayerRobot pr = PlayerRobot.getPlayerRobot();
		g.fillArc((int)pCmdRelScrn.getX() + pr.getSize()/4, (int)pCmdRelScrn.getY() + pr.getSize(),pr.getSize()/2, pr.getSize()/2, 0, 360);
	}
}
class RobotWheel {
	private Transform myTranslation, myRotation, myScale;
	public RobotWheel() {
		myTranslation = Transform.makeIdentity();
		myRotation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
	}
	public void draw(Graphics g, Point pCmdRelPrnt, Point pCmdRelScrn) {
		PlayerRobot pr = PlayerRobot.getPlayerRobot();
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gOrigXform = gXform.copy();
		gXform.concatenate(myTranslation);
		g.setTransform(gXform);
		g.fillArc((int)pCmdRelScrn.getX(), (int)pCmdRelScrn.getY() - pr.getSize()/4, pr.getSize()/4, pr.getSize()/2, 0, 360);
		g.setTransform(gOrigXform);
	}
	public void rotate(double degrees) {
		myRotation.rotate((float)Math.toRadians(degrees), 0, 0);
	}
	public void scale(double sx, double sy) {
		myScale.scale((float)sx, (float)sy);
	}
	public void translate(double tx, double ty) {
		myTranslation.translate((float)tx, (float)ty);
	}
}


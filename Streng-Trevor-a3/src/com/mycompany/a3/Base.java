package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Base extends Fixed {
	private int sequenceNumber = 1; 
	Random random = new Random();
	Vector<GameObjects> collisionVector = new Vector<GameObjects>();
	ArrayList<GameObjects> bases = new ArrayList<GameObjects>();
	
	//constructor
	public Base(int sequenceNumber) {
		super(ColorUtil.rgb(0, 150, 255));
		this.sequenceNumber = sequenceNumber;
		super.setSize(100);
		bases.add(this); // create a list to loop through for contains method
	}
	
	public void draw(Graphics g, Point pCmdRelPrnt) {
		g.setColor(getColor());
		// Order: top-left, bottom, top-right
		int arrX[] = {((int)pCmdRelPrnt.getX() + (int)getX()) - (getSize()/2), ((int)pCmdRelPrnt.getX() + (int)getX()), ((int)pCmdRelPrnt.getX() + (int)getX() + (getSize()/2))};
		int arrY[] = {((int)pCmdRelPrnt.getY() + (int)getY()) - (getSize()/2), ((int)pCmdRelPrnt.getY() + (int)getY()) + (getSize()/2), ((int)pCmdRelPrnt.getY() + (int)getY() - (getSize()/2))};
		// draws triangle takes 2 points and an int of how many points
		if(isSelected()) {
			g.drawPolygon(arrX, arrY, 3);
		} else {
			g.fillPolygon(arrX, arrY, 3);
		}
		 
		g.setColor(ColorUtil.BLACK);
		g.drawString(getBaseNumber() + "", (int)pCmdRelPrnt.getX() + (int)getX(), (int)pCmdRelPrnt.getY() + (int)getY());
	}
	
	public void handleCollision(ICollider otherObject, GameWorld gw) {
		GameObjects otherObj = (GameObjects)otherObject;
		// check if collision has already been handled
		if(!collisionVector.contains(otherObj)) {
			if(otherObj instanceof Robot) {
				collisionVector.add(otherObj);
				gw.baseCollision(this, otherObj);
			}
		}
	}
	public void removeCollision(ICollider otherObject) {
		GameObjects otherObj = (GameObjects)otherObject;
		collisionVector.remove(otherObj);
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmdRelPrnt) {
		float px = pPtrRelPrnt.getX();
		float py = pPtrRelPrnt.getY();
		for(int i = 0; i < bases.size(); i++) {
			float iShapeX = bases.get(i).getX();
			float iShapeY = bases.get(i).getY();
			float xLoc = pCmdRelPrnt.getX() + iShapeX;
			float yLoc = pCmdRelPrnt.getY() + iShapeY;
			// check if we clicked in an object
			if(px >= xLoc && px <= xLoc+bases.get(i).getSize() && py >= yLoc && py <= yLoc+bases.get(i).getSize()) return true;
		}
		return false;
	}
	
	// getters
	public int getBaseNumber() {
		return sequenceNumber;
	}
	
	public String toString() {
		return "Base: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
				+ " size = " + this.getSize() 
				+ " seqNum = " + this.getBaseNumber();
	}
}

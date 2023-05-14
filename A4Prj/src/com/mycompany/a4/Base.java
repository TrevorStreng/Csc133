package com.mycompany.a4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.Transform.NotInvertibleException;

public class Base extends Fixed {
	private int sequenceNumber = 1; 
	Random random = new Random();
	Vector<GameObjects> collisionVector = new Vector<GameObjects>();
	ArrayList<Fixed> bases = new ArrayList<Fixed>();
	
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
		Transform str = Transform.makeIdentity();
		g.getTransform(str);
		str.translate(0, 272/4 + getY() + (getSize()/2));
		str.scale(1, -1);
		str.translate(0, -(272/4 + getY() + (getSize()/2)));
		g.setTransform(str);
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
	
//	public boolean contains(Point pPtrRelPrnt, Point pCmdRelPrnt) {
//		float px = pPtrRelPrnt.getX();
//		float py = pPtrRelPrnt.getY();
//		for(int i = 0; i < bases.size(); i++) {
//			float iShapeX = bases.get(i).getX();
//			float iShapeY = bases.get(i).getY();
//			float xLoc = pCmdRelPrnt.getX() + iShapeX;
//			float yLoc = pCmdRelPrnt.getY() + iShapeY;
//			// check if we clicked in an object
//			if(px >= xLoc && px <= xLoc+bases.get(i).getSize() && py >= yLoc && py <= yLoc+bases.get(i).getSize()) return true;
//		}
//		return false;
//	}
	public boolean contains(float x, float y, int absX, int absY) {
		boolean hit = false;
		try {
			float[] pts = {x, y};
			Transform inverseConcatLTs = Transform.makeIdentity();
			Transform xForm = Transform.makeIdentity();

			xForm.translate(-absX, -absY);
			xForm.getInverse(inverseConcatLTs);
			inverseConcatLTs.transformPoint(pts, pts);
			for(int i = 0; i < bases.size(); i++) {
				float xLoc = bases.get(i).getX();
				float yLoc = bases.get(i).getY() + 3*absY/2;
				if(x >= xLoc - bases.get(i).getSize()/2 && x <= xLoc + bases.get(i).getSize()/2 && y >= yLoc - (bases.get(i).getSize()/2) && y <= yLoc + bases.get(i).getSize()/2) hit = true;
			}
			return hit;
		} catch(NotInvertibleException e) {
			return hit;
		}
	}
	
	// getters
	public int getBaseNumber() {
		return sequenceNumber;
	}
	
//	public String toString() {
//		return "Base: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
//				+ " color = " + this.colorToString() 
//				+ " size = " + this.getSize() 
//				+ " seqNum = " + this.getBaseNumber();
//	}
}

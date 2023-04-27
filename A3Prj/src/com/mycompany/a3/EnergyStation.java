package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed {
	private int capacity;
	private int temp;
	private int color;
	Random random = new Random();
	Vector<GameObjects> collisionVector = new Vector<GameObjects>();
	ArrayList<GameObjects> esStations = new ArrayList<GameObjects>();

	// constructor
	public EnergyStation() {
		super(ColorUtil.rgb(0, 255, 0));
		this.temp = random.nextInt(30) + 30;
		this.capacity = temp;
		super.setSize(temp);
		esStations.add(this); // create a list to loop through for contains method
	}
	
	public void draw(Graphics g, Point pCmdRelPrnt) {
		// add text, color and fill
		g.setColor(getColor());
		if(isSelected()) {
			g.drawArc(((int)pCmdRelPrnt.getX() + (int)getX()) - (getSize()/2), (int)pCmdRelPrnt.getY() + (int)getY() - (getSize()/2), getSize()*3, getSize()*3, 0, 360);
		} else {
			g.fillArc(((int)pCmdRelPrnt.getX() + (int)getX()) - (getSize()/2), (int)pCmdRelPrnt.getY() + (int)getY() - (getSize()/2), getSize()*3, getSize()*3, 0, 360);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(getCapacity() + "", ((int)pCmdRelPrnt.getX() + (int)getX()) + (getSize()/2), ((int)pCmdRelPrnt.getY() + (int)getY()) + (getSize()/2));
	}
	
	public void handleCollision(ICollider otherObject, GameWorld gw) {
		GameObjects otherObj = (GameObjects)otherObject;
		// check if collision has already been handled
		if(!collisionVector.contains(otherObj)) {
			collisionVector.add(otherObj);
			if(otherObj instanceof Robot) {
				gw.energyCollision(this, otherObj);
			}
		}
	}
	public void removeCollision(ICollider otherObject) {
		GameObjects otherObj = (GameObjects)otherObject;
		collisionVector.remove(otherObj);
	}
	public boolean contains(Point pPtrRelPrnt, Point pCmdRelPrnt) {
		float px = pPtrRelPrnt.getX(); // where I clicked
		float py = pPtrRelPrnt.getY();
		for(int i = 0; i < esStations.size(); i++) {
			float iShapeX = esStations.get(i).getX(); // where energyStation is
			float iShapeY = esStations.get(i).getY();
			float xLoc = pCmdRelPrnt.getX() + iShapeX; // mapview start + where esstation is
			float yLoc = pCmdRelPrnt.getY() + iShapeY;
			// check if we clicked in an object
			if(px >= xLoc && px <= xLoc+esStations.get(i).getSize() && py >= yLoc && py <= yLoc+esStations.get(i).getSize()) return true;
		}
		return false;
	}
	
	// getters and setters
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getEnergyStationColor() {
		return color;
	}
	public void setEnergyStationColor(int energyStationColor) {
		this.color = energyStationColor;
	}
	
	// toString method
	public String toString() {
		return "EnergyStation: " + "loc = " + Math.round(this.getLocation().getX() * 1.0) + ", " + Math.round(this.getLocation().getY() * 1.0)
				+ " color = " + this.colorToString() 
				+ " size = " + this.getSize() 
				+ " capacity = " + this.getCapacity();
	}

}

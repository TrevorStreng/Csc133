package com.mycompany.a4;

import java.util.Random;
import java.util.Vector;
import java.util.Timer;

import com.codename1.charts.models.Point;
//import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class ShockWave extends Moveable {
	private int color;
	Vector<Point> controlPointVector = new Vector<Point>();
	private int timer;
	
	public ShockWave(float x, float y) {
		super(ColorUtil.rgb(255, 255, 255));
		Random r = new Random();
		timer = 1;
		setSize(100);
		setSpeed((int)(r.nextFloat() * 25));
		setHeading((int)(r.nextFloat() * 360));
		//this gets the left side
		x -= getSize()/2;
		//this get the bottom
		y -= getSize()/2;
		//generate the 4 points based on size and initial location
		for(int i = 0; i < 4; i++) {
			Point temp = new Point(((r.nextFloat() * getSize())), ((r.nextFloat() * getSize())));
			controlPointVector.add(temp);
		}
		translate(-getX(), -getY());
		translate(x, y);
//		removeShockWave();
		// call timer method for its life span
		
	}
	public void draw(Graphics g, Point pCmdRelPrnt) {
		super.draw(g, pCmdRelPrnt); 
		g.setColor(color);
		drawBezierCurve(g, controlPointVector, pCmdRelPrnt);
	}
	public void drawBezierCurve(Graphics g, Vector<Point> controlPointVector, Point pCmdRelPrnt) {
		if(straightEnough(controlPointVector)) {
			// Draw line from first control point to last point
			g.drawLine((int)(controlPointVector.get(0).getX() + getX() + pCmdRelPrnt.getX()), (int)(controlPointVector.get(0).getY() + getY() + pCmdRelPrnt.getY()), (int)(controlPointVector.get(3).getX()  + getX() + pCmdRelPrnt.getX()), (int)(controlPointVector.get(3).getY() + getY()));
		}
			else {
				Vector<Point> leftSubVector = new Vector<Point>();
				Vector<Point> rightSubVector = new Vector<Point>();
				leftSubVector.setSize(4);
				rightSubVector.setSize(4);
				subdivideCurve(controlPointVector, leftSubVector, rightSubVector);
				drawBezierCurve(g, leftSubVector, pCmdRelPrnt);
				drawBezierCurve(g, rightSubVector, pCmdRelPrnt);
			}
	}
	public void subdivideCurve(Vector<Point> Q, Vector<Point> R, Vector<Point> S) {
		R.set(0, Q.get(0));
		R.set(1, new Point((float)((Q.get(0).getX() + Q.get(1).getX())/ 2.0), (float)((Q.get(0).getY() + Q.get(1).getY())/2.0)));
		R.set(2, new Point((float)((R.get(1).getX() / 2.0) + (Q.get(1).getX() + Q.get(2).getX()) / 4.0), (float)((R.get(1).getY() / 2.0) + (Q.get(1).getY() + Q.get(2).getY()) / 4.0)));
		S.set(3, Q.get(3));
		S.set(2, new Point((float)((Q.get(2).getX() + Q.get(3).getX())/ 2.0), (float)((Q.get(2).getY() + Q.get(3).getY())/2.0)));
		S.set(1, new Point((float)(((Q.get(1).getX() + Q.get(2).getX()) / 4.0) + (S.get(2).getX()/2.0)),(float)(((Q.get(1).getY() + Q.get(2).getY()) / 4.0) + (S.get(2).getY()/2.0))));
		R.set(3, new Point((float)((R.get(2).getX() + S.get(1).getX()) / 2.0), (float)((R.get(2).getY() + S.get(1).getY()) / 2.0)));
		S.set(0, R.get(3));
	}
	public boolean straightEnough(Vector<Point> controlPointVector) {
		double epsilon = 0.001;
		double d1 = lengthOf(controlPointVector.get(0), controlPointVector.get(1)) + lengthOf(controlPointVector.get(1), controlPointVector.get(2)) + lengthOf(controlPointVector.get(2), controlPointVector.get(3));
		double d2 = lengthOf(controlPointVector.get(0), controlPointVector.get(3));
		if(Math.abs(d1 - d2) < epsilon) return true;
		return false;
	}
	public double lengthOf(Point p1, Point p2) {
		double a = p1.getX() - p2.getX();
		double aSquared = a * a;
		double b = p1.getY() - p2.getY();
		double bSquared = b * b;
		return Math.sqrt(aSquared + bSquared);
	}
	public void removeShockWave() {
		Timer t = new Timer();
		System.out.println(t);
	}
	public int getTimer() {return timer;}
	public void setTimer(int t) {this.timer = t;}

	public void handleCollision(ICollider otherObject, GameWorld gw) {
//		GameObjects otherObj = (GameObjects)otherObject;
		// check if collision has already been handled
//		if(!collisionVector.contains(otherObj)) {
//			if(otherObj instanceof PlayerRobot) {
//				gw.droneCollision(this, otherObj);
//				collisionVector.add(otherObj);
//			}
//		}
	}
	public void removeCollision(ICollider otherObject) {
//		GameObjects otherObj = (GameObjects)otherObject;
//		collisionVector.remove(otherObj);
	}
}

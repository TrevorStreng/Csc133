package com.mycompany.a4;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public abstract class GameObjects implements IDrawable, ICollider {
	private int color;
//	private Point location;
//	private float[] p1 = new float[2];
	private int size;
	private Transform myTranslate;
	private Transform myRotate;
	private Transform myScale;
	Random random = new Random();
//	Transform r;
	
	// constructor
	public GameObjects(int size, int color) {
		this.color = color;
		this.size = size;
		float x = random.nextFloat() * GameWorld.getMVWidth();
		float y = random.nextFloat() * GameWorld.getMVHeight();
//		r = Transform.makeIdentity();
		myTranslate = Transform.makeIdentity(); // this makes the 3x3 array
		this.translate(x, y);
		myRotate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
	}
	public GameObjects(int color) {
		this.color = color;
		float x = random.nextFloat() * GameWorld.getMVWidth();
		float y = random.nextFloat() * GameWorld.getMVHeight();
//		p1[0] = x;
//		p1[1] = y;
//		r = Transform.makeIdentity();
		myTranslate = Transform.makeIdentity(); // this makes the 3x3 array
		this.translate(x, y);
		myRotate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();

	}

	public boolean collidesWith(ICollider otherObject) {
		float dx = ((GameObjects)otherObject).getX() - this.getX();
		float dy = ((GameObjects)otherObject).getY() - this.getY();
		if(dx * dx + dy * dy < 5000) return true;
		return false;
	}
	
	public void translate(float x, float y) {
		myTranslate.translate(x, y);
	}
	public void rotate(float angle) {
		myRotate.rotate(angle, 0, 0);
	}
//		myRotate.concatenate(myTranslate);
	public void scale(float sx, float sy) {
		myScale.scale(sx, sy);
	}
	public void resetTransform() {
		myRotate.setIdentity();
		myTranslate.setIdentity();
		myScale.setIdentity();
		}
	public Transform getMyTranslate() {
		return myTranslate;
	}
	public Transform getMyRotate() {
		return myRotate;
	}
	public Transform getMyScale() {
		return myScale;
	}
	public void draw(Graphics g, Point pCmdRelPrnt) {
		Transform r = Transform.makeIdentity();
		g.getTransform(r);
		// translate to origin
		r.translate(pCmdRelPrnt.getX(), pCmdRelPrnt.getY());
		r.translate(getX(), getY());
		r.translate(0, getSize());
		r.concatenate(myRotate);
		r.concatenate(myScale);
//		r.scale(myScale.getScaleX(), myScale.getScaleY());
		// translate back to position
		r.translate(-pCmdRelPrnt.getX(), -pCmdRelPrnt.getY());
		r.translate(-getX(), -getY());
		r.translate(0, -getSize());
		g.setTransform(r);
	}
	
	//getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getX() {
//		return location.getX();
		return myTranslate.getTranslateX();
	}
//	public void setX(float x) {
//		location.setX(x);
//	}
	public float getY() {
		return myTranslate.getTranslateY();
	}
//	public float getSX() {
//		return myScale.getScaleX();
//	}
//	public float getSY() {
//		return myScale.getScaleY();
//	}
//	public void setY(float y) {
//		location.setX(y);
//	}
	/*
	 * used to update location of objects that move
	 */
//	public void setLocation(Transform myTrans) {
////		this.location = new Point(x,y);
////		p1[0] = getX();
////		p1[1] = getY();
//		this.myTranslate = myTrans;
//	}
	public Transform getLocation() {
//		return this.location;
		return myTranslate;
	}
	
	public String colorToString() {
		return "[" + ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color) + "]";
	}

}

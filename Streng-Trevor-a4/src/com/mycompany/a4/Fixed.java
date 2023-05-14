package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Fixed extends GameObjects implements ISelectable {
	private boolean isSelected;
	
	// constructor
	public Fixed(int size, int color) {
		super(size, color);
	}
	public Fixed(int color) {
		super(color);
	}
	
	public void setSelected(boolean b) {
		isSelected = b;		
	}
	public boolean isSelected() {return isSelected;}
	public abstract void draw(Graphics g, Point pCmdRelPrnt);
//	public abstract boolean contains(Point pPtrRelPrnt, Point pCmdRelPrnt);
	public abstract boolean contains(float x, float y, int absX, int absY);
}

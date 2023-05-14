package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	public void setSelected(boolean b);
	public boolean isSelected();
//	public boolean contains(Point pPtrRelPrnt, Point pCmdRelPrnt);
	public abstract boolean contains(float x, float y, int absX, int absY);
	public void draw(Graphics g, Point pCmdRelPrnt);
}

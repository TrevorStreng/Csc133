package com.mycompany.a4;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.Transform.NotInvertibleException;
//import com.codename1.ui.geom.Point;
import com.codename1.charts.models.Point;
//import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	public static int height;
	public static int width;
	private Transform worldToNd, ndToDisplay, theVTM, inverseVTM;
	private float winLeft, winBottom, winRight, winTop, winWidth, winHeight;
	private int pPrevDragLocX, pPrevDragLocY;
//	private Point pCmpRelScreen;
	private Point pCmpRelPrnt;

	
	/*
	 * This initializes the mapView container
	 * */
	public MapView() {
		this.getAllStyles().setMargin(1, 3);
		this.getAllStyles().setMargin(3, 3);
		this.getAllStyles().setMargin(2, 3);
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
		winLeft = 0;
		winBottom = 0;
		winRight = 931/2; // hardcoded value = this.getWidth()/2 (for ipad skin)
		winTop = 639/2; // hardcoded value = this.getHeight()/2 (for ipad skin)
		winWidth = winRight - winLeft;
		winHeight = winTop - winBottom;
		pCmpRelPrnt = new Point(getX(), getY());
//		pCmpRelScreen = new Point(getAbsoluteX(), getAbsoluteY());
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);		
		
		IIterator it = gw.getGameObjects().getIterator();
		while(it.hasNext()) {
			updateVTM();
			g.setTransform(theVTM);
			it.getNext().draw(g, pCmpRelPrnt);
			g.resetAffine();
		}
	}
	public void updateVTM() {
		theVTM = Transform.makeIdentity();
		worldToNd = Transform.makeScale(1.0f/winWidth, 1.0f/winHeight);
		worldToNd.translate(-winLeft, -winBottom);
		ndToDisplay = Transform.makeTranslation(0, winTop);			
		ndToDisplay.scale(winRight, -winTop);
		theVTM.concatenate(ndToDisplay);
		theVTM.concatenate(worldToNd);
	}
	public void zoom(float factor) {
		float newWidth = getWidth() * factor;
		float newHeight = getHeight() * factor;
		if(newWidth <= 0 || newHeight <= 0 || newWidth > 8192 || newHeight > 4320) return;
		winLeft += (winWidth - newWidth)/2;
		winBottom += (winHeight - newHeight)/2;
		winWidth = (int)newWidth;
		winHeight = (int)newHeight;
		this.repaint();
	}
	public boolean pinch(float scale) {
		zoom(scale);
		return true;
	}
	public void panHorizontal(double delta) {
		winLeft += delta;
		this.repaint();
	}
	public void panVeritcal(double delta) {
		winBottom += delta;
		this.repaint();
	}
	
	public void update(Observable o, Object arg) {
		gw = (GameWorld)arg;
		this.repaint();
	}
	public void pointerPressed(int x, int y) {
		if(gw.getSelect() && gw.getPause()) {
			x = x - this.getParent().getAbsoluteX();
			y = y - this.getParent().getAbsoluteY();
			Transform inverseConcatLTs = Transform.makeIdentity();
			try {
				float[] pts = {x, y};
				theVTM.getInverse(inverseConcatLTs);
				inverseConcatLTs.transformPoint(pts, pts);
				IIterator it = gw.getGameObjects().getIterator();
				while(it.hasNext()) {
					GameObjects temp = it.getNext();
					if(temp instanceof Fixed) {
						if(((Fixed) temp).contains(pts[0], pts[1], getAbsoluteX(), getAbsoluteY())) ((Fixed)temp).setSelected(true);
						else ((Fixed)temp).setSelected(false);
					}
					repaint();
				}
			} catch(NotInvertibleException e) {
				
			}	
		} else {
			pPrevDragLocX = x;
			pPrevDragLocY = y;
		}
	}
	public void pointerReleased(int x, int y) {
		if(gw.getSelect() && gw.getPause()) {
			x = x - this.getParent().getAbsoluteX();
			y = y - this.getParent().getAbsoluteY();
			Transform inverseConcatLTs = Transform.makeIdentity();
			try {
				float[] pts = {x, y};
				theVTM.getInverse(inverseConcatLTs);
				inverseConcatLTs.transformPoint(pts, pts);
				IIterator it = gw.getGameObjects().getIterator();
				while(it.hasNext()) {
					GameObjects temp = it.getNext();
					if(temp instanceof Fixed) {
						if(((Fixed)temp).isSelected()) {
							float oldX = ((Fixed)temp).getX();
							float oldY = ((Fixed)temp).getY();
							((Fixed)temp).translate(pts[0] - oldX, pts[1] - oldY - 3*this.getAbsoluteY()/2);
							((Fixed)temp).setSelected(false);
						}
					}
				}
			} catch(NotInvertibleException e) {}
		}
		repaint();
	}
	public void pointerDragged(int x, int y) {
		if(!gw.getSelect()) {
			double dx = pPrevDragLocX - x;
			double dy = pPrevDragLocY - y;
			dx *= winWidth / (float)getWidth();
			dy *= -(winWidth / (float)getHeight());
			panHorizontal(dx);
			panVeritcal(dy);
			pPrevDragLocX = x;
			pPrevDragLocY = y;
		}
	}

	public void setWinLeft(float winLeft) {
		this.winLeft = winLeft; 
	}
	public void setWinRight(float winRight) {
		this.winRight = winRight; 
	}
	public void setWinTop(float winTop) {
		this.winTop = winTop; 
	}
	public void setWinBottom(float winBottom) {
		this.winBottom = winBottom; 
	}
	public void setWinWidth(float winWidth) {
		this.winWidth = winWidth;
	}
	public void setWinHeight(float winHeight) {
		this.winHeight = winHeight;
	}
}



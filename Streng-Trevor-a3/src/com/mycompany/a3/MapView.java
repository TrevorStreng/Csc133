package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
//import com.codename1.ui.geom.Point;
import com.codename1.charts.models.Point;
//import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	public static int height;
	public static int width;
//	private Fixed hold;
	
	/*
	 * This initializes the mapView container
	 * */
	public MapView() {
		this.getAllStyles().setMargin(1, 3);
		this.getAllStyles().setMargin(3, 3);
		this.getAllStyles().setMargin(2, 3);
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		IIterator it = gw.getGameObjects().getIterator();
		while(it.hasNext()) {;
			it.getNext().draw(g, new Point(this.getX(), this.getY()));
		}
	}
	
	public void update(Observable o, Object arg) {
		gw = (GameWorld)arg;
		this.repaint();
	}
	public void pointerPressed(int x, int y) {
		if(gw.getSelect() && gw.getPause()) {
			x = x - this.getParent().getAbsoluteX();
			y = y - this.getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y); // where we clicked
			Point pCmdRelPrnt = new Point(getX(), getY()); // starts of mapview container
			IIterator it = gw.getGameObjects().getIterator();
			while(it.hasNext()) {
				GameObjects temp = it.getNext();
				if(temp instanceof Fixed) {
					if(((Fixed)temp).contains(pPtrRelPrnt, pCmdRelPrnt)) {
						((Fixed)temp).setSelected(true);
					} else {
						((Fixed)temp).setSelected(false);
					}
				}
				repaint();
			}
		}
	}
	public void pointerReleased(int x, int y) {
		if(gw.getSelect() && gw.getPause()) {
			x = x - this.getParent().getAbsoluteX();
			y = y - this.getParent().getAbsoluteY();
			x -= this.getX();
			y -= this.getY();
			IIterator it = gw.getGameObjects().getIterator();
			while(it.hasNext()) {
				GameObjects temp = it.getNext();
				if(temp instanceof Fixed) {
					if(((Fixed)temp).isSelected()) {
						((Fixed)temp).setLocation(x, y);
						((Fixed)temp).setSelected(false);
					}
				}
			}
			repaint();
		}
	}
}


package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private TextArea gameArea;
	private static int height;
	private static int width;
	
	/*
	 * This initializes the mapView container
	 * */
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
		gameArea = new TextArea();
		gameArea.getAllStyles().setBgColor(ColorUtil.WHITE);
		gameArea.setEditable(false);
		int size = gameArea.getMaxSize();
		gameArea.setMaxSize(size);
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		gameArea.setColumns(100);
		gameArea.setRows(20);
		this.getAllStyles().setPadding(0, 0);
		this.add(gameArea);
	}
	/*
	 * Updates the text inside the mapView container
	 * */
	public void update(Observable o, Object arg) {
		gw = (GameWorld)arg;
		IIterator it = gw.getGameObjects().getIterator();
		String d = "";
		while(it.hasNext()) {
			d += it.getNext().toString()+"\n";
		}
		gameArea.setText(d);
		this.repaint();
	}
}

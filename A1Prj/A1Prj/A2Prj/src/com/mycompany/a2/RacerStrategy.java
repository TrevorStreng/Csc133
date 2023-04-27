package com.mycompany.a2;

import com.codename1.util.MathUtil;

public class RacerStrategy implements IStrategy {
	private NonPlayerRobot npr;
	private Base base;
	
	/*
	 * the strategy non player robots use to get to the next base
	 */
	public RacerStrategy(GameObjectCollection gameObjects, NonPlayerRobot npr) {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof Base) {
				if(((Base)temp).getBaseNumber() == npr.getLastBaseReached()+1) {
					base = (Base)temp;
					break;
				}
			}
		}
		this.npr = npr;
	}
	
	public void strategy () {
		double a = npr.getX() - base.getX();
		double b = npr.getY() - base.getY();
		double angle = 90 - Math.toDegrees(MathUtil.atan2(b, a));
		npr.setHeading(npr.getSteeringDirection() + (int)angle);
	}
}

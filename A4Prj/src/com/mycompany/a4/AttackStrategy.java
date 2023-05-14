package com.mycompany.a4;

import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {
	private PlayerRobot player;
	private NonPlayerRobot npr;

	/*
	 * The strategy to follow/go after the playerRobot
	 * */
	public AttackStrategy(GameObjectCollection gameObjects, NonPlayerRobot npr) {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof PlayerRobot) {
				player = (PlayerRobot)temp;
				break;
			}
		}
		this.npr = npr;
		this.strategy();
	}
	public void strategy() {
		double a = -npr.getX() + player.getX();
		double b = -npr.getY() + player.getY();
		double angle = 90 - Math.toDegrees(MathUtil.atan2(b, a));
//		npr.rotate((int)Math.toRadians((int)(angle - npr.getHeading())));
		npr.setHeading((int)angle);
	}
}

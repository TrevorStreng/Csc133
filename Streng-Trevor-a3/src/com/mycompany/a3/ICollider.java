package com.mycompany.a3;

public interface ICollider {
	boolean collidesWith(ICollider otherObject);
	void handleCollision(ICollider otherObject, GameWorld gw);
	void removeCollision(ICollider otherObject);
}

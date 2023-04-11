package com.mycompany.a2;

public interface IIterator {
	
	/*
	 * Iterator interface
	 */
	public boolean hasNext();
	
	public GameObjects getNext();
	
	public GameObjects getCurrent();
	
	public void remove(GameObjects gameObjects);
}

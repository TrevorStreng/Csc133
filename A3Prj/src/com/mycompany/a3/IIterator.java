package com.mycompany.a3;

public interface IIterator {
	
	/*
	 * Iterator interface
	 */
	public boolean hasNext();
	
	public GameObjects getNext();
	
	public GameObjects getCurrent();
	
	public void remove(GameObjects gameObjects);
}

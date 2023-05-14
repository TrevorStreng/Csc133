package com.mycompany.a4;

public interface IIterator {
	
	/*
	 * Iterator interface
	 */
	public boolean hasNext();
	
	public GameObjects getNext();
	
	public GameObjects getCurrent();
	
	public void remove(GameObjects gameObjects);
}

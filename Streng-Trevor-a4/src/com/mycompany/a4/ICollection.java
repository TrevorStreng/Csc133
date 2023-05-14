package com.mycompany.a4;

public interface ICollection {
	/*
	 * Collection interface
	 */
	public void add(GameObjects gameObject);
	
	IIterator getIterator();
}

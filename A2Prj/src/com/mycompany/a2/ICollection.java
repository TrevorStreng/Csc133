package com.mycompany.a2;

public interface ICollection {
	/*
	 * Collection interface
	 */
	public void add(GameObjects gameObject);
	
	IIterator getIterator();
}

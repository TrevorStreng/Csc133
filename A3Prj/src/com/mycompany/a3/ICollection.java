package com.mycompany.a3;

public interface ICollection {
	/*
	 * Collection interface
	 */
	public void add(GameObjects gameObject);
	
	IIterator getIterator();
}

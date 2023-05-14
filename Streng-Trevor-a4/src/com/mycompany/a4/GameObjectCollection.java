package com.mycompany.a4;
import java.util.*;

public class GameObjectCollection implements ICollection {

	/*
	 * Collection of GameObjects in an ArrayList to hold all of the objects in one place
	 * */
	private ArrayList<GameObjects> gameObjects;
	
	public GameObjectCollection() {
		gameObjects = new ArrayList<GameObjects>();
	}
	
	public void add(GameObjects obj) {
		gameObjects.add(obj);
	}
	
	public IIterator getIterator() {
		return new CollectionIterator();
	}
	
	/*
	 * Iterator to loop through all of the objects 
	 * Used when an object is needed to be found for use in arraylist
	 * */
	private class CollectionIterator implements IIterator {
		private int i;
		public CollectionIterator() {
			i = -1;
		}
		
		public boolean hasNext() {
			if(gameObjects.size() <= 0 ) return false;
			if(i == gameObjects.size() - 1) return false;
			return true;
		}
		public GameObjects getNext() {
			i++;
			return gameObjects.get(i);
		}
		public GameObjects getCurrent() {
			return gameObjects.get(i);
		}
		public void remove(GameObjects gameObject) {
			gameObjects.remove(gameObject);
		}
	}

}

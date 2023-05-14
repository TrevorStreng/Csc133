package com.mycompany.a4;
import java.util.*;

public class GameWorld extends Observable {
	private int clock;
	private int life;
	private GameObjectCollection gameObjects;
	private int numOfBases = 4;
	private int numOfDrones = 2;
	private int numOfNPRs = 3;
	private int numofEnergyStations = 2;
	private boolean sound;
	private static int mvWidth;
	private static int mvHeight;
//	private static int energyConsumptionRate;
	private Sound rcSound;
	private Sound baseSound;
	private Sound esSound;
	private Sound deathSound;
	private BGSound bgSound;
	private boolean paused;
	private int clockOffset;
	private boolean selectOn;
	private int collisionCount;
	private int tempClock;
//	private int facing;
	
	// Constructor
	public GameWorld() {};
	/*
	 * Initializing the game world
	 * */
	public void init() {
		this.clock = 0;
		this.life = 3;
		gameObjects = new GameObjectCollection();
		this.sound = false;
		this.paused = false;
		this.clockOffset = 1;
		this.selectOn = false;
		addObjects();
		this.setChanged();
		this.notifyObservers(this);
		collisionCount = 0;
//		facing = 0;
	};
	public void resetLife() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).setEnergyLevel(100);
			}
		}
	}
	
	/*
	 * Adding the needed gameObjects to the GameObjectCollection
	 * */
	public void addObjects() {
		gameObjects.add(PlayerRobot.getPlayerRobot());
		for(int i = getLastBaseReached(); i <= numOfBases; i++) {
			gameObjects.add(new Base(i));
		}
		for(int i = 0; i <= numofEnergyStations; i++) {
			gameObjects.add(new EnergyStation());
		}
		for(int i = 0; i <= numOfNPRs; i++) {
			NonPlayerRobot npr = new NonPlayerRobot();
			if(i % 2 == 0) {
				npr.setStrategy(new AttackStrategy(gameObjects, npr));
			} else {
				npr.setStrategy(new RacerStrategy(gameObjects, npr));
			}
			gameObjects.add(npr);
		}
		for(int i = 0; i <= numOfDrones; i++) {
			gameObjects.add(new Drone());
		}
//		gameObjects.add(new ShockWave(750, 600));
	}
	public void createSounds() {
		bgSound = new BGSound("bgSound.mp3");
		rcSound = new Sound("slap.wav"); // robot collision sound
		baseSound = new Sound("Laser.wav");
		esSound = new Sound("bow.wav");
		deathSound = new Sound("glassCrunch.wav");
	}
	public void isPaused() {
		paused = !paused;
		if(!paused) {
			selectOn = false;
		}
		if(!paused && getSound()) {
			bgSound.play();
			rcSound.play();
			baseSound.play();
			esSound.play();
			deathSound.play();
		} else {
			bgSound.pause();
			rcSound.pause();
			baseSound.pause();
			esSound.pause();
			deathSound.pause();
		}
	}
	public boolean getPause() {
		return paused;
	}
	public boolean getSelect() {
		return selectOn;
	}
	public void setSelect() {
		selectOn = !selectOn;
	}
	/*
	 * accelerates or slows down speed of robot
	 */
	public void accelerate() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).accelerateRobot();
			}
		}
		System.out.println("You accelerated!");
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * Slowing down playerRobot
	 * */
	public void brake() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).brakeRobot();
				break;
			}
		}
		System.out.println("You braked!");
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * calls turn methods in robot class and steerable interface
	 */
	public void left() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).leftRobot();
				break;
			}
		}
		System.out.println("You turned left!");
		this.setChanged();
		this.notifyObservers(this);
	}
	public void right() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).rightRobot();
				break;
			}
		}
		System.out.println("You turned right!");
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * Takes damage when you get hit by another robot
	 * */
	public void robotCollision(GameObjects thisObj, GameObjects otherObj) {
		// the collision counter is a cheeky workaround just ignore it
		if(collisionCount == 6) {
			if(thisObj instanceof Moveable && otherObj instanceof Moveable) {
				((Robot)thisObj).takeDamage();
				float x = ((Robot)thisObj).getX();
				float y = ((Robot)thisObj).getY();
				gameObjects.add(new ShockWave(x, y));
				collisionCount = 4;
			}
		}
		collisionCount++;
		if(thisObj instanceof PlayerRobot && otherObj instanceof NonPlayerRobot) {
			if(sound) rcSound.play();
		}
	}
	/*
	 * if base collision has occurred increments base number
	 */
	public void baseCollision(GameObjects thisObj, GameObjects otherObj) {
		int lastBase = ((Robot) otherObj).getLastBaseReached();
		int sequenceNumber = ((Base)thisObj).getBaseNumber();
		if(sequenceNumber == lastBase+1) {
		((Robot)otherObj).setLastBaseReached(lastBase + 1);
		}
		if(sound) baseSound.play();
	}
	/*
	 * if collision with energy station occurs then robot energy is increased and 
	 * energy station capacity is decreased accordingly 
	 */
	public void energyCollision(GameObjects thisObj, GameObjects otherObj) {
		int esLevel = ((EnergyStation)thisObj).getCapacity();
		int robotEnergy = ((Robot)otherObj).getEnergyLevel();
		int diff = 100 - robotEnergy;
		int newLvl = robotEnergy + esLevel;
		if(newLvl > 100) newLvl = 100;
		((Robot)otherObj).setEnergyLevel(newLvl);
		esLevel -= diff;
		if(esLevel <= 0) {
//			this.remove();
			new EnergyStation();
		} else {
			((EnergyStation)thisObj).setSize(esLevel);
			((EnergyStation)thisObj).setCapacity(esLevel);
		}
		System.out.println("Your energy level is now " + ((Robot)otherObj).getEnergyLevel());
		if(sound) esSound.play();
	}
	/*
	 * if collision with drone occurs then robot takes damage and changes to a lighter color
	 */
	public void droneCollision(GameObjects thisObj, GameObjects otherObj) {
//		int dmgLvl = ((PlayerRobot)otherObj).getDamageLevel();
//		dmgLvl += 5;
//		((PlayerRobot)otherObj).setDamageLevel(dmgLvl);
		if(otherObj instanceof Moveable) {
			((Robot)otherObj).takeDamage();
			float x = ((Drone)thisObj).getX();
			float y = ((Drone)thisObj).getY();
			gameObjects.add(new ShockWave(x, y));				 
		}
		int speed = ((PlayerRobot)otherObj).getSpeed();
		if(speed > 1) speed -= 2;
		if(speed == 1) speed = 0;
		((PlayerRobot)otherObj).setSpeed(speed);
	} 
	
	/*
	 * Flips the strategy of all Non Player Robots
	 * */
	public void changeStrategy() {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof NonPlayerRobot) {
				NonPlayerRobot npr = (NonPlayerRobot)temp;
				if(npr.getStrategy() instanceof AttackStrategy) {
					npr.setStrategy(new RacerStrategy(gameObjects, npr));
				} else {
					npr.setStrategy(new AttackStrategy(gameObjects, npr));
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * clock tick increments time
	 * then checks if the robot has reached the final base and won the game and then ends the game
	 * otherwise checks energy and damage levels to see if you have lost a life
	 * if not it moves the robot and the drones
	*/
	public void clockTick(double elapsedTime) {
		// fix timer for elapsed time
		clockOffset++;
		// increment clock every 50 * 20 milliseconds
		if(clockOffset >= 50) {
			int clock = getClock();
			clock++;
			setClock(clock);
			clockOffset = 1;
			IIterator it10 = gameObjects.getIterator();
			while(it10.hasNext()) {
				GameObjects temp = it10.getNext();
				if(temp instanceof ShockWave) {	
					((ShockWave)temp).setTimer(((ShockWave)temp).getTimer() + 1);
				}
			}
		}
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof NonPlayerRobot) {
				if(((NonPlayerRobot)temp).getLastBaseReached() == numOfBases) {
					System.out.println("Game over. you Lose. NonPlayerRobot Wins. Total time: " + clock);
					System.exit(0);
				}
			}
			if(temp instanceof PlayerRobot) {
				if(((PlayerRobot)temp).getLastBaseReached() == numOfBases) {
					System.out.println("Game over. you win! Total time: " + clock);
					System.exit(0);
				}
				double energyLevel = ((PlayerRobot)temp).getEnergyLevel();
				((PlayerRobot)temp).setSteeringDirection(0);
				if(energyLevel > 0 && ((PlayerRobot)temp).getDamageLevel() < ((PlayerRobot)temp).getMaximumDamageLevel()) {
					((PlayerRobot)temp).move(elapsedTime);
					if(((PlayerRobot)temp).getSpeed() > 0) {
						// takes two energy a second
						if(clockOffset == 1 || clockOffset == 25) {
							double energyConsumptionRate = (((PlayerRobot)temp).getEnergyConsumptionRate());
							((PlayerRobot)temp).setEnergyConsumptionRate((energyConsumptionRate));
							energyLevel -= energyConsumptionRate;
							((PlayerRobot)temp).setEnergyLevel((int)energyLevel);
						}
					}
				} else {
					System.out.println("You have lost a life.");
					deathSound.play();
					int l = getLife();
					l--;
					setLife(l);
					if(getLife() <= 0) {
					System.out.println("Game over, you failed!");
					System.exit(0);
					}
					resetLife();
				}
			}
		}
		
		IIterator it2 = gameObjects.getIterator();
		while(it2.hasNext()) {
			GameObjects temp2 = it2.getNext();
			if(temp2 instanceof Drone) {
				((Drone)temp2).move(elapsedTime);
				// set a flag in heading method
				
			}
		}
		IIterator it3 = gameObjects.getIterator();
		while(it3.hasNext()) {
			GameObjects temp3 = it3.getNext();
			if(temp3 instanceof NonPlayerRobot) {
				((NonPlayerRobot)temp3).invokeStrategy();
				((NonPlayerRobot)temp3).move(elapsedTime);
			}
		}
		IIterator it4 = gameObjects.getIterator();
		while(it4.hasNext()) {
			GameObjects temp = it4.getNext();
			ICollider objA = (ICollider)(temp);
			IIterator it5 = gameObjects.getIterator();
			while(it5.hasNext()) {
				GameObjects temp2 = it5.getNext();
				ICollider objB = (ICollider)(temp2);
				if(objA.collidesWith(objB)) {
					objA.handleCollision(objB, this);
				} else {
					objA.removeCollision(objB);
				}
			}
		}
		IIterator it9 = gameObjects.getIterator();
		while(it9.hasNext()) {
			GameObjects temp = it9.getNext();
			if(temp instanceof ShockWave) {
				((ShockWave)temp).move(elapsedTime);	
//				((ShockWave)temp).setTimer(((ShockWave)temp).getTimer() + 1);
				if(((ShockWave)temp).getTimer() >= 10) {
					it9.remove(((ShockWave)temp));
					break;
				}
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * Gets last base reached to display in scoreview
	 * */
	public int getLastBaseReached() {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = (GameObjects)it.getNext();
			if(temp instanceof PlayerRobot) {
				return ((PlayerRobot)temp).getLastBaseReached();
			}
		}
		return 0;
	}
	/*
	 * Gets energy Level for display in scoreview
	 * */
	public int getEnergyLevel() {
		IIterator it69 = gameObjects.getIterator();
		while(it69.hasNext()) {
			GameObjects temp = (GameObjects)it69.getNext();
			if(temp instanceof PlayerRobot) {
				return ((PlayerRobot)temp).getEnergyLevel();
			}
		}
		return 0;
	}
	/*
	 * Gets damage Level for display in scoreview
	 * */
	public int getDamageLevel() {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = (GameObjects)it.getNext();
			if(temp instanceof PlayerRobot) {
				return ((PlayerRobot)temp).getDamageLevel();
			}
		}
		return 0;
	}
	
	/*
	 * Getters and setters some for use in scoreview
	 */
	public int getClock() {
		return clock;
	}
	public void setClock(int clock) {
		this.clock = clock;
	}
	public boolean getSound() {
		return sound;
	}
	public void setSound(boolean s) {
		if(s && !paused) bgSound.play();
		else bgSound.pause();
		this.sound = s;
		this.setChanged();
		this.notifyObservers(this);
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public static void setMVWidth(int mvw) {
		mvWidth = mvw;
	}
	public static int getMVWidth() {
		return mvWidth;
	}
	public static void setMVHeight(int mvh) {
		mvHeight= mvh;
	}
	public static int getMVHeight() {
		return mvHeight;
	}
	public GameObjectCollection getGameObjects() {
		return gameObjects;
	}
}

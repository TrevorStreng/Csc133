package com.mycompany.a3;
import java.util.*;

public class GameWorld extends Observable {
	private int clock;
	private int life;
	private GameObjectCollection gameObjects;
	private PlayerRobot player;
	private int numOfBases = 9;
	private boolean sound;

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
		addObjects();
		this.setChanged();
		this.notifyObservers(this);
	};
	
	/*
	 * Adding the needed gameObjects to the GameObjectCollection
	 * */
	public void addObjects() {
		gameObjects.add(PlayerRobot.getPlayerRobot());
		player = PlayerRobot.getPlayerRobot();
		for(int i = 1; i <= numOfBases; i++) {
			gameObjects.add(new Base(i));
		}
		for(int i = 0; i < 3; i++) {
			gameObjects.add(new EnergyStation());
		}
		for(int i = 0; i < 3; i++) {
			NonPlayerRobot npr = new NonPlayerRobot();
			if(i % 2 == 0) {
				npr.setStrategy(new AttackStrategy(gameObjects, npr));
			} else {
				npr.setStrategy(new RacerStrategy(gameObjects, npr));
			}
			gameObjects.add(npr);
		}
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
//		player.accelerateRobot(); // does this work??
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
	public void robotCollision() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObjects temp = iterator.getNext();
			if(temp instanceof PlayerRobot) {
				((PlayerRobot)temp).takeDamage();
				if(((PlayerRobot)temp).getDamageLevel() >= ((PlayerRobot)temp).getMaximumDamageLevel()) {
					System.out.println("Game over");
					System.exit(0);
				}
				((PlayerRobot)temp).resetColor();
				IIterator iterator2 = gameObjects.getIterator();
				while(iterator2.hasNext()) {
					GameObjects temp2 = iterator.getNext();
					if(temp2 instanceof NonPlayerRobot) {
						if(((NonPlayerRobot)temp2).getDamageLevel() < ((NonPlayerRobot)temp2).getMaximumDamageLevel()) {
							((NonPlayerRobot)temp2).takeDamage();
							break;
						} else {
							((NonPlayerRobot)temp2).setSpeed(0);
						}
					}
				}
			}
		}
		System.out.println("You hit a robot!");
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * if base collision has occurred increments base number
	 */
	public void baseCollision(int n) {
		IIterator it = gameObjects.getIterator(); // got sick of typing out iterator so its "it" now
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof PlayerRobot) {
				int currentBase = ((PlayerRobot)temp).getLastBaseReached();
				if(n == currentBase+1) {
					System.out.println("You made it to the next base!");
					((PlayerRobot)temp).setLastBaseReached(n);
					currentBase += 1;
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * if collision with energy station occurs then robot energy is increased and 
	 * energy station capacity is decreased accordingly 
	 */
	public void energyCollision() {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof PlayerRobot) {
				IIterator it2 = gameObjects.getIterator();
				while(it2.hasNext()) {
					GameObjects temp2 = it2.getNext();
					if(temp2 instanceof EnergyStation) {
						int esLevel = ((EnergyStation)temp2).getCapacity();
						if(esLevel > 0) {
							int robotEnergy = ((PlayerRobot)temp).getEnergyLevel();
							int tempLvl = 100 - robotEnergy;
							robotEnergy += esLevel;
							esLevel -= tempLvl;
							if(robotEnergy <= 100) {
								((PlayerRobot)temp).setEnergyLevel(robotEnergy);
								((EnergyStation)temp2).setSize(0);
								((EnergyStation)temp2).setCapacity(0);
							} else {
								((EnergyStation)temp2).setSize(esLevel);
								((EnergyStation)temp2).setCapacity(esLevel);
								((PlayerRobot)temp).setEnergyLevel(100);
							}
							System.out.println("Your energy level is now " + ((PlayerRobot)temp).getEnergyLevel());
						}
					}
				}
				
			}
		}
		System.out.println("You hit a Energy Station!");
		this.setChanged();
		this.notifyObservers(this);
	}
	/*
	 * if collision with drone occurs then robot takes damage and changes to a lighter color
	 */
	public void droneCollision() {
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof PlayerRobot) {
				int robotDamage = ((PlayerRobot)temp).getDamageLevel();
				int maxRobotDamage = ((PlayerRobot)temp).getMaximumDamageLevel();
				robotDamage += 5;
				if(robotDamage < maxRobotDamage) {
					((PlayerRobot)temp).setDamageLevel(robotDamage);
					int maxSpeed = ((PlayerRobot)temp).getMaximumSpeed();
					((PlayerRobot)temp).setMaximumSpeed(maxSpeed - 2);
					((PlayerRobot)temp).resetColor();
					System.out.println("You have taken damage. Current damage level: " + ((PlayerRobot)temp).getDamageLevel());
				}
				it.remove(temp);
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
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
				int baseNum = ((NonPlayerRobot) temp).getLastBaseReached();
				baseNum++;
				((NonPlayerRobot)temp).setLastBaseReached(baseNum);
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
	public void clockTick() {
		System.out.println("The clock ticked!");
		int clock = getClock();
		clock++;
		setClock(clock);
		IIterator it = gameObjects.getIterator();
		while(it.hasNext()) {
			GameObjects temp = it.getNext();
			if(temp instanceof PlayerRobot) {
				if(((PlayerRobot)temp).getLastBaseReached() == numOfBases) {
					System.out.println("Game over. you win! Total time: " + clock);
					System.exit(0);
				}
				int energyLevel = ((PlayerRobot)temp).getEnergyLevel();
				if(energyLevel > 0 && ((PlayerRobot)temp).getDamageLevel() < ((PlayerRobot)temp).getMaximumDamageLevel()) {
					((PlayerRobot)temp).move();
					((PlayerRobot)temp).setSteeringDirection(0);
					int energyConsumptionRate = ((PlayerRobot)temp).getEnergyConsumptionRate();
					energyLevel -= energyConsumptionRate;
					((PlayerRobot)temp).setEnergyLevel(energyLevel);
				} else {
					System.out.println("You have lost a life.");
					life--;
					if(life <= 0) {
						System.out.println("Game over, you failed!");
						System.exit(0);
					}
					int curBase = ((PlayerRobot)temp).getLastBaseReached();
					IIterator it2 = gameObjects.getIterator();
					while(it2.hasNext()) {
						GameObjects temp2 = it2.getNext();
						if(temp2 instanceof Base) {
							if(((Base)temp2).getBaseNumber() == curBase) {
								float x = ((Base)temp2).getX();
								float y = ((Base)temp2).getY();
								((PlayerRobot)temp2).reset(x, y);
							}
						}
					}
				}
			}
		}
		IIterator it2 = gameObjects.getIterator();
		while(it2.hasNext()) {
			GameObjects temp2 = it2.getNext();
			if(temp2 instanceof Drone) {
				((Drone)temp2).move();
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
	public void setSound(boolean sound) {
		this.sound = sound;
		this.setChanged();
		this.notifyObservers(this);
	}
	public int getLife() {
		return life;
	}
	public GameObjectCollection getGameObjects() {
		return gameObjects;
	}
}

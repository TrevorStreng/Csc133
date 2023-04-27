package com.mycompany.a1;
import java.util.*;
//import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	private int clock = 0;
	private int life = 3;
	private int robotSize = 40;
	private int baseSize = 10;
	Random random = new Random();
	private float x = 1024;
	private float y = 768;
	private int numberOfBases = 4;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	// constructor
	public GameWorld() {
	};
	
	/*
	 *Initializing all game objects and putting them into an ArrayList
	 */
	public void init() {
		gameObjects.add(new Base(baseSize, 50, 50, 1));
		gameObjects.add(new Base(baseSize, 50, 500, 2));
		gameObjects.add(new Base(baseSize, 700, 500, 3));
		gameObjects.add(new Base(baseSize, 700, 50, 4));
		gameObjects.add(new Robot(robotSize, 50, 50));
		gameObjects.add(new Drone(10+random.nextInt(40), random.nextFloat() * x, random.nextInt() * y));
		gameObjects.add(new EnergyStation(10+random.nextInt(40), 150, 150));
		gameObjects.add(new EnergyStation(10+random.nextInt(40), 600, 600));
	}
	/*
	 * accelerates or slows down speed of robot
	 */
	public void accelerate() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				((Robot)gameObjects.get(i)).accelerateRobot();
			}
		}
		System.out.println("You accelerated!");
	}
	public void brake() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				((Robot)gameObjects.get(i)).brakeRobot();
			}
		}
		System.out.println("You braked!");
	}
	/*
	 * calls turn methods in robot class and steerable interface
	 */
	public void left() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				((Robot)gameObjects.get(i)).leftRobot();
			}
		}
		System.out.println("You turned left!");
	}
	public void right() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				((Robot)gameObjects.get(i)).rightRobot();
			}
		}
		System.out.println("You turned right!");
	}
	public void robotCollision() {
		System.out.println("You hit a robot!");
	}
	/*
	 * if base collision has occurred increments base number
	 */
	public void baseCollision(int n) {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				int currentBase = ((Robot)gameObjects.get(i)).getLastBaseReached();
				if(n == currentBase+1) {
					System.out.println("You made it to the next base!");
					((Robot)gameObjects.get(i)).setLastBaseReached(n);
				} else {
					System.out.println("Please go to bases in order.");
				}
			}
		}
	}
	/*
	 * if collision with energy station occurs then robot energy is increased and 
	 * energy station capacity is decreased accordingly 
	 */
	public void energyCollision() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				for(int j = 0; j < gameObjects.size(); j++) {
					if(gameObjects.get(j) instanceof EnergyStation) {
						int esLevel = ((EnergyStation)gameObjects.get(j)).getCapacity();
						if(esLevel > 0) {
							int robotEnergy = ((Robot)gameObjects.get(i)).getEnergyLevel();
							int temp = 100 - robotEnergy;
							robotEnergy += esLevel;
							esLevel -= temp;
							if(robotEnergy <= 100) {
								((Robot)gameObjects.get(i)).setEnergyLevel(robotEnergy);
								((EnergyStation)gameObjects.get(j)).setSize(0);
								((EnergyStation)gameObjects.get(j)).setCapacity(0);
								System.out.println("Your energy level is now " + robotEnergy);
							} else {
								((EnergyStation)gameObjects.get(j)).setSize(esLevel);
								((EnergyStation)gameObjects.get(j)).setCapacity(esLevel);
								((Robot)gameObjects.get(i)).setEnergyLevel(100);
								System.out.println("Your energy level is now " + 100);
							}
						}
					}
				}
			}
		}
		System.out.println("You hit a Energy Station!");
	}
	/*
	 * if collision with drone occurs then robot takes damage and changes to a lighter color
	 */
	public void droneCollision() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				int robotDamage = ((Robot)gameObjects.get(i)).getDamageLevel();
				int maxRobotDamage = ((Robot)gameObjects.get(i)).getMaximumDamageLevel();
				robotDamage += 5;
				if(robotDamage < maxRobotDamage) {
					((Robot)gameObjects.get(i)).setDamageLevel(robotDamage);
					int maxSpeed = ((Robot)gameObjects.get(i)).getMaximumSpeed();
					((Robot) gameObjects.get(i)).setMaximumSpeed(maxSpeed - 2);
					((Robot) gameObjects.get(i)).resetColor();
					System.out.println("You have taken damage. Current damage level: " + robotDamage);
				}
				break;
			}
		}
		System.out.println("You hit a drone!");
	} 
	/*
	 * clock tick increments time
	 * then checks if the robot has reached the final base and won the game and then ends the game
	 * otherwise checks energy and damage levels to see if you have lost a life
	 * if not it moves the robot and the drones
	*/
	public void clockTick() {
		int clock = getClock();
		clock++;
		setClock(clock);
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				if(((Robot) gameObjects.get(i)).getLastBaseReached() == numberOfBases) {
					System.out.println("Game over. you win! Total time: " + clock);
					System.exit(0);
				}
				int energyLevel = ((Robot) gameObjects.get(i)).getEnergyLevel();
				if(energyLevel > 0 && (((Robot) gameObjects.get(i)).getDamageLevel() < ((Robot) gameObjects.get(i)).getMaximumDamageLevel())) {
					((Robot) gameObjects.get(i)).move();
					((Robot) gameObjects.get(i)).setSteeringDirection(0);
					int energyConsumptionRate = ((Robot) gameObjects.get(i)).getEnergyConsumptionRate();
					energyLevel -= energyConsumptionRate;
					((Robot) gameObjects.get(i)).setEnergyLevel(energyLevel);
				} else {
					System.out.println("You have lost a life..");
					life--;
					if(life <= 0) {
						System.out.println("Game over, you failed!");
						System.exit(0);
					} else {
						for(int j = 0; j < gameObjects.size(); j++) {
							gameObjects.remove(j);
						}
						init();
						break;
					}
				} 
			}
		}
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Drone) {
				((Drone) gameObjects.get(i)).move();
			}
		}
		System.out.println("The clock ticked!");
	}
	/*
	 * displays important values to console
	 */
	public void display() {
		System.out.println("Number of lives left: " + life);
		System.out.println("clock:" + clock);
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Robot) {
				int baseNumber = ((Robot)gameObjects.get(i)).getLastBaseReached();
				System.out.println("Highest base number reached: " + baseNumber);
				int energyLevel = ((Robot)gameObjects.get(i)).getEnergyLevel();
				System.out.println("Current energy level: "+ energyLevel);
				int damageLevel = ((Robot)gameObjects.get(i)).getDamageLevel();
				System.out.println("Current damage level: "+ damageLevel);
			}
		}
	}
	/*
	 * displays all gameObject values
	 */
	public void map() {
		for(int i = 0; i < gameObjects.size(); i++) {
			System.out.println(gameObjects.get(i).toString());
		}
	}
	/*
	 * makes sure to confirm exit with help of variable in Game class
	 */
	public void exit() {
		System.out.println("Are you sure you want to exit?");
		System.out.println("Y or N");
	}
	/*
	 * confirms game exit
	 */
	public void yes() {
		System.exit(0);
	}
	/*
	 * doesn't do much
	 */
	public void no() {}
	public int getClock() {
		return clock;
	}
	public void setClock(int clock) {
		this.clock = clock;
	}
}

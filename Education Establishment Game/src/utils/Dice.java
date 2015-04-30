package utils;

import java.util.Random;

public class Dice {
	//value of the dice
	int value;
	//Random number generator
	Random rand = new Random() ;
	
	/**
	 * Get current dice value.
	 * @return dice value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * rolls dice using random number generator
	 * to create a new value for the dice. 
	 * @return 
	 */
	public int roll(){
		value = (rand.nextInt(5) + 1);
		return value;
	}
	
	

}

package utils;

import java.util.Random;

public class Dice {
	//value of the dice
	
	private int[] values;
	//Random number generator
	private Random rand = new Random() ;
	
	/**
	 * Get current dice value.
	 * @return dice value.
	 */
	public int[] getValues() {
		return values;
	}
	/**
	 * @return Sum of Values
	 */
	public int getValue() {
		return values[0]+values[1];
	}
	
	/**
	 * rolls dice using random number generator
	 * to create a new value for the dice. 
	 * @return Value of one Dice Roll
	 */
	private int roll(){
		return (rand.nextInt(5) + 1);
	}
	/**
	 * Rolls 2 Dice
	 * @return Array of Size 2 with both results
	 */
	public int[] rollDice(){
		values = new int[] {roll(),roll()};
		return values;
	}
	/**
	 * Returns if the values are areDoubles()
	 * @return
	 */
	public boolean isDoubles() {
		return values[0]==values[1];
	}
	

}

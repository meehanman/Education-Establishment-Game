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
	
	public int[] diceRoll(int diceNum){
		int[] temp = new int[diceNum];
		for (int i = 0; i<diceNum; i++){
			temp[i] = roll();
		}
		return temp;
	}

	public boolean checkDoublesRoll(int[]diceRoll) {
		// check if roll was a double
		if(diceRoll.length == 0){
			//if no dice in the array
			return false;
		} else {
			//if all dice don't have the same value. 
			int firstDiceValue = diceRoll[0];
			for(int i : diceRoll){
				if (i != firstDiceValue){
					return false;
				}
			}
			//if all dice have the same value.
			return true;
		}
	}

	public int add(int[] diceRoll) {
		// add all values to get movement
		int diceValue = 0;
		for(int i : diceRoll){
			diceValue += i;
		}
		return diceValue;
	}
	
	

}

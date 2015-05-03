package board.establishment;

import board.Square;

/**
 * 
 * Restaurant (formally known as utilities)
 * 
 * @rule
 * 			If one Restaurant is owned, rent is 4 times
 * 			the amount shown on dice. If both Restaurants
 * 			are owned then the rent is x10 shown on the dice.
 * 
 * @author Dean
 *
 */
public class Restaurant extends Establishment {

	//Multiplier value when the owner owns one property
	private int[] multiplier = {0,0};
	
	public Restaurant(String name,int price) {
		super(name, price);
		
		this.multiplier[0] = 4;
		this.multiplier[1] = 10;
	}
	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////
	
	/**
	 * Calculates rent if you land on property bases on how many owned and diceRoll
	 * 
	 * @param diceRoll
	 * @param squares
	 * @return
	 */
	public int getRent(int diceRoll, Square[] squares){
		int amountOwned=0;
		//If this has an owner
		if(hasOwner()){
			for(Square square : squares){
				//If the square is a Restaurant and the owner is this owner
				if(square instanceof Restaurant && ((Establishment)(square)).getOwner().equals(getOwner())){
					amountOwned++;
				}
			}
		}
		//If 1 is owned, then it will output the first value etc..
		return diceRoll * multiplier[amountOwned];
	}
	
}

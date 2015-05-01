package board.establishment;

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
	private int MULTIPLIER_ONE;
	//Multiplier value when the owner owns two properties
	private int MULTIPLIER_TWO;
	
	public Restaurant(String name,int price) {
		super(name, price);
		
		this.MULTIPLIER_ONE = 4;
		this.MULTIPLIER_TWO = 10;
	}
	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////
	
	/**
	 * TODO Need Player to be specified to calculate if owner owns
	 * 			more than one
	 * 
	 * @param diceRoll The value rolled for current players go
	 * @return The amount payable
	 */
	public int calculateRent(int diceRoll){
		return diceRoll * MULTIPLIER_ONE;
	}
	
}

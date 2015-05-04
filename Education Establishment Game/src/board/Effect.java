package board;

/**
 * The effect class can be applied to 
 * Squares and Cards that effect the player
 * 
 * NOTE: TODO Look at desc of cards to make this redundant
 * 			@see https://github.com/DepthDeluxe/Monopoly/blob/master/src/monopoly/Card.java
 * 
 * @author Dean
 *
 */
public class Effect {

	private int money;
	private int Position;
	private boolean outOfJailFree;
	//CardPickup 0 = none, 1 = community chest, 2 = chance
	private int cardPickup = 0;
	//If the card will cause movement
	private boolean movement;
	//Charge by house?
	private boolean houseCharge;
	
	public Effect(int money, int position, boolean outOfJailFree, int cardPickup, boolean movement,
			boolean houseCharge) {
		super();
		this.money = money;
		Position = position;
		this.outOfJailFree = outOfJailFree;
		this.setCardPickup(cardPickup);
		this.movement = movement;
		this.houseCharge = houseCharge;
	}
	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return Position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		Position = position;
	}
	/**
	 * @return the outOfJailFree
	 */
	public boolean getOutOfJailFree() {
		return outOfJailFree;
	}
	/**
	 * @param outOfJailFree the outOfJailFree to set
	 */
	public void setOutOfJailFree(boolean outOfJailFree) {
		this.outOfJailFree = outOfJailFree;
	}


	/**
	 * @return the cardPickup
	 */
	public int getCardPickup() {
		return cardPickup;
	}


	/**
	 * @param cardPickup the cardPickup to set
	 */
	public void setCardPickup(int cardPickup) {
		this.cardPickup = cardPickup;
	}
	
	/**
	 * Check movement flag to see if the card will cause movement.
	 * @return - true if movement, false otherwise.
	 */
	public boolean isMovement() {
		return movement;
	}

	/**
	 * Check movement flag to see if the card will increase charge
	 * with number of houses.
	 * @return - true if movement, false otherwise.
	 */
	public boolean isHouseCharge() {
		return houseCharge;
	}


}

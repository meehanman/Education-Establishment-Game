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
	
	public Effect(int money, int position, boolean outOfJailFree, int cardPickup) {
		super();
		this.money = money;
		Position = position;
		this.outOfJailFree = outOfJailFree;
		this.cardPickup = cardPickup;
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

}

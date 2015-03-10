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
	private int outOfJailFree;
	
	
	public Effect(int money, int position, int outOfJailFree) {
		super();
		this.money = money;
		Position = position;
		this.outOfJailFree = outOfJailFree;
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
	public int getOutOfJailFree() {
		return outOfJailFree;
	}
	/**
	 * @param outOfJailFree the outOfJailFree to set
	 */
	public void setOutOfJailFree(int outOfJailFree) {
		this.outOfJailFree = outOfJailFree;
	}
	
}

package board.establishment;

import utils.Player;
import board.Square;


/**
 * 
 * The Establishment class is the superClass for 
 * Subjects Bars and Food places containing vital
 * information
 * 
 * @author Dean
 *
 */
public class Establishment extends Square{
	
	//The colour of the property. Same colour properties are used to allow for building regulations.
	protected String color;
	//Price to purchase
	protected int price;
	//Current Owner
	protected Player owner;
	//Price to mortgage (In order to lift the mortgage, the owner must pay the Bank the amount of mortgage plus 10% interest.)
	protected int mortgageValue;
	protected boolean mortgaged;
	
	/**
	 * Constructor for Subjects that have a color
	 * @param name Name of Establishment
	 * @param color Name of Color
	 * @param price Price to Buy Square
	 */
	public Establishment(String name, String color, int price) {
		super(name);

		this.price = price;
		this.mortgaged = false;
		this.color = color;
	}
	
	public Establishment(String name, int price) {
		super(name);

		this.price = price;
		this.mortgaged = false;
		this.color = null;
	}

	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////

	/**
	 * @return The Current Owner of this Establishment
	 */
	public Player getOwner() {
		return owner;
	}


	/**
	 * @param Change the owner if the Establishment
	 * is baught or traded
	 */
	private void changeOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * @param Change the owner if the Establishment
	 * is bought or traded
	 * 
	 * @return True if property Trade Successful
	 */
	public boolean tradeProperty(Player owner,Player newOwner) {
		if(this.owner!=owner){ //Not owned yet
			changeOwner(newOwner);
			return true;
		}else{
			System.out.println("The property doesn't have an owner, or the owner supplied is not the current owner");
			return false;
		}
	}

	/**
	 * @return the colour
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * @return the mortgage Value
	 */
	public int getMortgageValue() {
		return mortgageValue;
	}
	
	/**
	 * The unMortgage Value of a property is the mortgage property
	 *  + 10%
	 * 
	 * @return the UnMortgage Value
	 */
	public int getUnmortgageValue() {
		return (int)(mortgageValue*1.1);
	}
	
	/**
	 * 
	 * @return Returns the mortgageValue 
	 * 			to be given to the player
	 */
	public int MortgageProperty(){
		this.mortgaged = true;
		return getMortgageValue();
	}
	
	/**
	 * 
	 * @return Returns the mortgageValue 
	 * 			to be taken from the player
	 */
	public int unMortgageProperty(){
		this.mortgaged = false;
		return getUnmortgageValue();
	}

	
}

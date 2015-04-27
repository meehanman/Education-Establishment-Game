package board.establishment;

import utils.Player;


/**
 * 
 * The Establishment class is the superClass for 
 * Subjects Bars and Food places containing vital
 * information
 * 
 * @author Dean
 *
 */
public class Establishment{
	
	protected String name;
	protected String description;
	//The colour of the property. Same colour properties are used to allow for building regulations.
	protected String colour;
	//Price to purchase
	protected int price;
	//Current Owner
	protected Player owner;
	//Price to mortgage (In order to lift the mortgage, the owner must pay the Bank the amount of mortgage plus 10% interest.)
	protected int mortgageValue;
	protected boolean mortgaged;
	
	public Establishment(String name, String description, int price) {

		this.name = name;
		this.description = description;
		this.price = price;
		this.mortgaged = false;
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
	public void changeOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
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

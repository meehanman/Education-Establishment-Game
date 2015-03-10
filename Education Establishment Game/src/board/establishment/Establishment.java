package board.establishment;

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
public class Establishment {
	
	String name;
	String description;
	//The colour of the property. Same colour properties are used to allow for building regulations.
	String colour;
	//Price to purchase
	int price;
	//Current Owner
	Player owner;
	//Price to mortgage (In order to lift the mortgage, the owner must pay the Bank the amount of mortgage plus 10% interest.)
	int mortgageValue;
	boolean mortgaged;
	
	
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
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}


	/**
	 * @param owner the owner to set
	 */
	public void changeOwner(Player owner) {
		this.owner = owner;
	}


	/**
	 * @return the mortgaged
	 */
	public boolean isMortgaged() {
		return mortgaged;
	}


	/**
	 * @param mortgaged the mortgaged to set
	 */
	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
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
	 * @return the mortgageValue
	 */
	public int getMortgageValue() {
		return mortgageValue;
	}

	
	

	
}

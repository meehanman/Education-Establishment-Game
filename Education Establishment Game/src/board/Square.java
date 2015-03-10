package board;

import board.establishment.Establishment;

/**
 * 
 * The Square Class represents a square that a player can land on.
 * 
 * This square can either be a special square such as Go, Pay Tax or Jail (SpecialSquare)
 * or...
 * This square can be a property, utility or trainStation (subject, food or drink)  (Establishment)
 * 
 * TODO needs work
 * 
 * @author Dean
 *
 */
public class Square {

	//The Square is an establishment Square
	private Establishment establishment;
	//The Square is a Special Square
	private SpecialSquare specialSquare;
	
	//Constructor 
	public Square(Establishment establishment){
		this.establishment = establishment;
		this.specialSquare = null;
	}
	
	public Square(SpecialSquare specialSquare){
		this.establishment = null;
		this.specialSquare = specialSquare;
	}

	
	////////////////////////////////////////////////////
	/**
	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////	

	/**
	 * @return the establishment
	 */
	public Establishment getEstablishment() {
		return establishment;
	}
	/**
	 * @return if the current Square is an Establishment
	 */
	public boolean isEstablishment() {
		return (establishment != null);
	}

	/**
	 * @return the specialSquare
	 */
	public SpecialSquare getSpecialSquare() {
		return specialSquare;
	}
	/**
	 * @return if the current Square is a Special Square
	 */
	public boolean isSpecialSquare() {
		return (specialSquare != null);
	}
}

package board;


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

	private String name;
	
	public Square(String n){
		this.name = n;
	}
	
	////////////////////////////////////////////////////
	/**
	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////	
	
	
	/**
	 * @return the title
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Will return the type of Establishment such as 
	 * Bar/ Special Square / Subject / Restaurant
	 * @return
	 */
	public String getSquareType(){
		return this.getClass().getSimpleName();
	}
	
}

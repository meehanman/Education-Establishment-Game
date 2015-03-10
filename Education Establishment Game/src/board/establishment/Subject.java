package board.establishment;

/**
 * 
 * The Subject class contains the majority of the squares
 * in the game where players can manage, build, buy and 
 * mortgage to advance them in the game. 
 * 
 * @author Dean
 *
 */
public class Subject extends Establishment {

	//The MAX number of houses including final Facilities
	private int MAXHOUSES;
	//The number of houses currently owned by the owner for the Establishment
	private int houses;
	
	
	public Subject(String name, String description, int price) {
		super(name, description, price);
		
		MAXHOUSES = 5;
	}

	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////
	
	
	/**
	 * @return the houses
	 */
	public int getHouses() {
		return houses;
	}
	
	/**
	 * @return the houses
	 */
	public boolean isHouse() {
		if(houses<MAXHOUSES){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * More than 4 houses indicates the
	 * Subject has got a Facilities
	 * 
	 * @return True if they have Facilities 
	 */
	public boolean isFacility() {
		
		return !isHouse();
	}

	/**
	 * 
	 * Add a house to the Subject
	 * (Adding the 4th house adds a  Facility)
	 * 
	 * @param Returns if a house was added
	 */
	public boolean addHouse() {
		if(houses<=MAXHOUSES){
			houses++;
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * Removes a house from the Subject
	 * (Adding the 4th house adds a  Facility)
	 * 
	 * @param Returns if a house was removed
	 */
	public boolean removeHouse() {
		if(houses>0){
			houses--;
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
}

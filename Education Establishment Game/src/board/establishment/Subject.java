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

	//The number of houses currently owned by the owner for the Establishment
	private int houses;
	//Taken from Monopoly Card
	private int[] rent = new int[6];
	//Price of House (50,100,150 or 200) depending on side of board
	private int housePrice;
	
	public Subject(String name, String color, int price, int[] rental,int housePrice) {
		super(name, color, price);
		
		this.houses = 0;
		this.rent = rental;
		this.setHousePrice(housePrice);
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
	 * 
	 * If the total amount of houses is max
	 * then the player has a Facility on this location
	 * 
	 * @return True if they have Facilities 
	 */
	public boolean isFacility() {
		
		if(houses==rent.length){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * Add a house to the Subject
	 * (Adding the 4th house adds a Facility)
	 * 
	 * @param Returns if a house was added
	 */
	public boolean addHouse() {
		if(houses< rent.length){
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


	
	/**
	 * Calculates rent if you land on property bases on how many houses on property
	 * @return the rent for a particular house
	 * 0 - Site
	 * 1 - 1 house
	 * 2 - 2 houses
	 * 3 - 3 houses
	 * 4 - 4 houses
	 * 5 - Facilities (HOTEL)
	 */
	public int getRent(){
		return rent[houses];
	}
	/**
	 * Returns the rent information for UI
	 * @return
	 */
	public int[] getRentInformation(){
		return rent;
	}



	/**
	 * @return the housePrice
	 */
	public int getHousePrice() {
		return housePrice;
	}



	/**
	 * @param housePrice the housePrice to set
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}
	
	public void sellHouse(){
		if(houses > (rent.length - 1)){
			//remove facility and only refund price of one house
			//also remove all houses.
			owner.addBalance(housePrice);
			houses = 0;
		} else if (houses > 0) {
			//remove a house and add price to owner's balance.
			owner.addBalance(housePrice);
			houses--;
		} else {
			//do not own any houses
		}
	}
	
	public void sellAllHouses(){
		//sell all the houses on the property giving the money to 
		//the owner
		while(houses>0){
			sellHouse();
			}
	}
	
	public void buyHouse(){
		//if owner can afford buy house
		if (owner.getBalance() > housePrice){
			owner.subBalance(housePrice);
			addHouse();
		}
	}

	public void clearHouses() {
		// remove all house on the subject
		this.houses = 0;
	}
	
	
	
	
}

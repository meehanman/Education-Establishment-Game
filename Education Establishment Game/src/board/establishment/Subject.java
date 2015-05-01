package board.establishment;

import utils.Player;

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
	
	
	public Subject(String name, String color, int price, int[] rental) {
		super(name, color, price);
		
		this.houses = 0;
		this.rent = rental;
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
	 * (Adding the 4th house adds a  Facility)
	 * 
	 * @param Returns if a house was added
	 */
	public boolean addHouse() {
		if(houses<=rent.length){
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
	 * @return the rent for a particular house
	 * 0 - Site
	 * 1 - 1 house
	 * 2 - 2 houses
	 * 3 - 3 houses
	 * 4 - 4 houses
	 * 5 - Facilities (HOTEL)
	 */
	public int getRent(int i) {
		return rent[i];
	}
	public int getRent() {
		return rent[getHouses()];
	}
	
	/**
	 * Allows someone to stay at the property (When they land on the square)
	 */
	public void rent(Player player){
		if(player.getBalance() >= getRent()){
			player.giveMoney(owner, getRent());
		}else{
			System.out.println("Not Enough Money: " + player.getName() + " cannot pay for their rent");
		}
		
	}
	
	
	
	
	
}

package board.establishment;

import board.Square;

/**
 * 
 * Bar (formally known as Train)
 * 
 * @rule
 * 			The player that lands on the Bar 
 * 			 pays the owner the amount of bars
 * 			 the owner owns * barPrice; 
 * 
 * @author Dean
 *
 */

public class Bar extends Establishment {
	 
	
	/**
	 * 
	 * @param name Name of Bar
	 * @param price Price of Bar
	 */
	public Bar(String name, int price) {
		super(name, price);
	}
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////

	/**
	 * Calculates rent if you land on property bases on how many owned
	 * @param squares
	 * @return
	 */
	public int getRent(Square[] squares){
		int rental = getBaseRent();
		//If this has an owner
		if(hasOwner()){
			for(Square square : squares){
				//If the square is a Restaurant and the owner is this owner
				if(square instanceof Restaurant && ((Establishment)(square)).getOwner().equals(getOwner())){
					rental+=rental;
				}
			}
		}else{
			rental = 0;
		}

		//If 1 is owned, then it will output the first value etc..
		return rental;
	}
	public int getBaseRent(){
		return this.price/4;
	}
	

}

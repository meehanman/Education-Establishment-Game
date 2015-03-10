package board.establishment;

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
	 
	/** The barPrice is the amount owed to the owner
	 *   whenever another player lands on the card **/
	int barPrice;
	
	public Bar(String name, String description, int price, int barPrice) {
		super(name, description, price);
		
		this.barPrice = barPrice;
	}

}

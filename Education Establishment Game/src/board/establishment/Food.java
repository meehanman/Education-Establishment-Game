package board.establishment;

/**
 * 
 * Food (formally known as utilities)
 * 
 * @rule
 * 			If one Food Place is owned, rent is 4 times
 * 			the amount shown on dice. If both "Food" places
 * 			are owned then the rent is x10 shown on the dice.
 * 
 * @author Dean
 *
 */
public class Food extends Establishment {

	
	public Food(String name, String description, int price) {
		super(name, description, price);
		
	}
	
}

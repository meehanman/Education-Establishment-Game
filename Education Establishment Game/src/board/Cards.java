package board;

/**
 * 
 * Cards are what the player can pick up
 * if they land on a specified Square
 * 
 * In traditional Monopoly these are 
 * Chance Cards & Community Chest
 * 
 * @author Dean
 *
 */
public class Cards {
	
	//The title that is displayed on the card
	private String title;
	//The description that is displayed on the card
	private String description;
	//TODO Look at effects
	private Effect effect;
	//The Player who owns or Picked up the card
	private Player owner;
	
	public Cards(Effect effect, String title, String description) {
		this.effect = effect;
		this.title = title;
		this.description = description;
		this.owner = null;
	}
	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the effect
	 */
	public Effect getEffect() {
		return effect;
	}

	/**
	 * Returns the cards current owner
	 * 
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Gives the card a new owner
	 * 
	 * @return the owner
	 */
	public void setOwner(Player p) {
		owner = p;
	}
	
	/**
	 * @return the owner
	 */
	public Player removeOwner() {
		return null;
	}
	
	/**
	 * Returns if the card currently has an owner
	 * @return the owner
	 */
	public Player hasOwner() {
		if(owner==null){
			return false;
		}else{
			return true;
		}
	}
	
}

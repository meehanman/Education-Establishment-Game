package board;

/**
 * 
 * This Special Square can either be a special square such as Go, Pay Tax or Jail
 * 
 * 
 * @author Dean
 *
 */
public class SpecialSquare {
	
	//The title that is displayed on the card
	private String title;
	//The description that is displayed on the card
	private String description;
	//TODO Look at effects
	private Effect effect;
	
	public SpecialSquare(Effect effect, String title, String description) {
		this.effect = effect;
		this.title = title;
		this.description = description;
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

	
}

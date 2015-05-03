package board;

/**
 * 
 * This Special Square can either be a special square such as Go, Pay Tax or Jail
 * 
 * 
 * @author Dean
 *
 */
public class SpecialSquare extends Square{
	
	public static enum Type { Go, ComunityChest, ChanceCard, Jail, GotoJail, FreeParking, IncomeTax, SuperTax };
	private Type specialType;
	private Effect effect;
	
	public SpecialSquare(Type type, Effect effect) {
		super(type.toString());
		
		this.specialType = type;
		this.effect = effect;

	}
	
	
	////////////////////////////////////////////////////
	/**
 	/////////////////// METHODS ///////////////////////<
	**/
	////////////////////////////////////////////////////	

	/**
	 * @return the effect
	 */
	public Effect getEffect() {
		return effect;
	}
	
	public Type getType(){
		return specialType;
	}

	
}

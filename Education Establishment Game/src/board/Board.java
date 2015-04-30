package board;

import javafx.scene.paint.Color;
import board.establishment.Property;

/**
 * 
 * The board where everything else arises
 * Contains the cards and Squares/Tiles for the board
 * 
 * @author Dean
 *
 * TODO Needs lots of work 
 */
public class Board {
	private Property[] properties;
	private Cards card;
	
	public Board(){
		initialiseProperties();
	}
	
	private void initialiseProperties(){
		properties = new Property[40];
		double[] prices = {90,250,700,875,1050};
		properties[0] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[1] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[2] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[3] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[4] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[5] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[6] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[7] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[8] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[9] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		properties[10] = new Property("Test", Color.RED, 210, 110, prices, 150, true);
		
	}
	
	public Property getPropertyAt(int value){
		return properties[value];
	}

	public Property[] getProperties() {
		return properties;
	}
	
	
}

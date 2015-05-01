package utils;

import java.util.Arrays;

/**
 * 
 * The name of the piece the user has
 * currently is it is a string name of the 
 * images that are located in "gui/img/<pieceName>.png"
 * 
 * @author Dean
 * @author Conor
 *
 */

public class Piece {
	
	public static String[] possibleIcons = {"tophat","dog","iron","telephone","thimble","bus","car","cat"};
	private int icon;
	
	public Piece(int pieceId){
		setIconId(pieceId);
	}

	public int getIconId() {
		return icon;
	}
	
	public String getIconFileName() {
		return possibleIcons[icon]+".png";
	}
	
	public String getIconFileLocation(){
		return "img\\pieces\\"+getIconFileName();
	}
	
	//Sets the icon name and ensures it is within the list
	public void setIconId(int iconid) {
		if(Arrays.asList(possibleIcons).contains(possibleIcons[iconid])){
			this.icon = iconid;
		}else{
			this.icon = possibleIcons.length-1;
		}
	}
	

}

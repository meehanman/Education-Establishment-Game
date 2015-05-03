import java.util.ArrayList;
import java.util.Scanner;

import utils.Dice;
import utils.Piece;
import utils.Player;
import board.Board;


public class SampleGUIHandlers {
	
	private static Board board;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static int currentTurn;
	private Dice dice = new Dice();
	private static boolean playing = true;
	
	//TODO add constructor and remove dependencies
	/**
	 * Dependencies- ArrayList<Player>, Dice
	 */
	
	/*
	//TODO remove scanner in favor of GUI.
	static Scanner keyb = new Scanner(System.in);
	
	public static void main(String[] args){
		System.out.println("welcome to EEG!");
		System.out.println("how many players?");
		Scanner keyb = new Scanner(System.in);
		int numOfPlayers = keyb.nextInt();
		
		for (int i = 0; i < numOfPlayers; i++){
			//get player name.
			System.out.println("Please enter a name");
			String tempName = keyb.nextLine();
			//get player token
			System.out.println("Please enter a token");
			String tempToken = keyb.nextLine();
			//create new player in arraylist.
			players.add(new Player(tempName,new Piece(tempToken)));
			
			//launch the players into turns
			while(playing == true){
				System.out.println("please enter a command - commands"
						+ " are roll, mortgage, bankrupt, end game,"
						+ " buy, trade, end turn and upgrade");
				System.out.println(players.get(currentTurn).getName() + "'s turn!");
				takeTurn(keyb.nextLine(), players.get(currentTurn), board);
			}
		}
		
	}
	
	
	public void buy(){
		
	}
	
	public void rent(){
		
	}
	
	public void upgrade(){
		
	}
	
	public void trade(){
		
	}
	
	public void mortgage(){
		
	}
	
	public void endTurn(){
		
	}
	
	public void roll(){
		
	}
	
	public void bankrupt(){
		
	}
	
	public void endGame(){
		
	}
	
	public void displayPropertiesOwned(){
		
	}
	
	public void displayGroupOwned(){
		
	}
	
	public void displayBalance(){
		
	}
	
	public void displayToken(){
		
	}
	
	public void diplayName(){
		
	}
	*/
	
}

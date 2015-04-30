import gui.EEGBoard;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Dice;
import utils.Piece;
import utils.Player;
import board.Board;
import board.establishment.Establishment;
import board.establishment.Property;
import board.establishment.Subject;

/**
 * 
 * Main Game Class that contains the logic for the game
 * 
  ___  __| |_   _  ___ __ _| |_(_) ___  _ __   __ _| |   _____  _| |_ __ _| |__ | (_)___| |__  _ __ ___   ___ _ __ | |_ 
 / _ \/ _` | | | |/ __/ _` | __| |/ _ \| '_ \ / _` | |  / _ \ \/ / __/ _` | '_ \| | / __| '_ \| '_ ` _ \ / _ \ '_ \| __|
|  __/ (_| | |_| | (_| (_| | |_| | (_) | | | | (_| | | |  __/>  <| || (_| | |_) | | \__ \ | | | | | | | |  __/ | | | |_ 
 \___|\__,_|\__,_|\___\__,_|\__|_|\___/|_| |_|\__,_|_|  \___/_/\_\\__\__,_|_.__/|_|_|___/_| |_|_| |_| |_|\___|_| |_|\__|
										 / _` |/ _` | '_ ` _ \ / _ \
										| (_| | (_| | | | | | |  __/
										 \__, |\__,_|_| |_| |_|\___|
 *										 |___/   
 * 
 * @author Dean
 * @author Conor
 * 
 * @Comapny Mad Hatter Enterprises
 * 
 * @see http://tesuji.org/pub/Monopoly.java (<- Implementation of this class)
 */
public class Game {
	
	private static Board board;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static int currentTurn;
	private Dice dice = new Dice();
	private static boolean playing = true;
	
	//TODO remove scanner in favor of GUI.
	static Scanner keyb = new Scanner(System.in);
	
	public static void main(String[] args){
		System.out.println("welcome to EEG!");
		System.out.println("how many players?");
		Scanner keyb = new Scanner(System.in);
		int numOfPlayers = keyb.nextInt();
		
		
		//GUI 
		EEGBoard GUI = new EEGBoard();
		GUI.Launch();
		
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
	
	private static void takeTurn(String command, Player player, Board board){
		switch(command){
		case "roll":
			roll(player, board);
			landOn(board.getPropertyAt(player.getPosition()), player);
			break;
		case "mortgage":
			mortgage();
			break;
		case "Bankrupt":
			bankrupt(player);
			break;
		case "end game":
			endGame();
			break;
		case "buy":
			buy(board.getPropertyAt(player.getPosition()),player);
			break;
		case "trade":
			trade();
			break;
		case "end turn":
			endTurn();
			break;
		case "upgrade":
			upgrade();
			break;
			
		}
	}
	
	private static void upgrade() {
		// TODO Auto-generated method stub
		
	}

	private static void endTurn() {
		// change the player to the next player's turn
		currentTurn++;
		if (currentTurn > players.size()){
			currentTurn -= players.size();
		}
	}

	private static void trade() {
		// TODO Auto-generated method stub
		
	}

	private static String buy(Property property, Player player) {
		// TODO Auto-generated method stub
		if (player.buy(property) == true){
			return "bought";
		} else {
			return "sorry you do not have enough money";
		}
	}

	private static void endGame() {
		// TODO Auto-generated method stub
		
	}

	private static void rent(Property property, Player player) {
		// take rent value from one player and add to another
		if (player.getBalance() > property.getRent()){
			player.subBalance(property.getRent());
			property.getOwner().addBalance(property.getRent());
		}
		
	}

	private static String bankrupt(Player player) {
		// declare player bankrupt and remove them from game.
		String temp = player.getName();
		resalePropertiesOwnedBy(player);
		removePlayer(player);
		return  temp + " is bankrupt!";
	}
	
	private static void resalePropertiesOwnedBy(Player player){
		//remove all properties owned by a particular player.
		for(int i = 0; i > board.getProperties().length; i++){
			if (board.getPropertyAt(i).getOwner() == player){
				//clear the owner value on the property
				board.getPropertyAt(i).setOwner(null);
			} else {
				//do nothing
			}
		}
	}
	
	private static void removePlayer(Player player){
		players.remove(players.indexOf(player));
	}

	private static void mortgage() {
		// TODO Auto-generated method stub
		
	}

	private static void roll(Player player, Board board) {
		// rolls dice and ensures the value recieved keeps
		//the player on the board between start and end looping.
		Dice di = new Dice();
		int tempRoll = di.roll();
		if (player.getPosition() + tempRoll > board.getProperties().length){
			player.setPosition(player.getPosition()
					+ tempRoll - board.getProperties().length);
		} else {
			player.setPosition(player.getPosition()	+ tempRoll);
		}
		
	}

	private static void landOn(Property property, Player player){
		if (property.getOwner() == null){
			//allow the player to buy the property.
			System.out.println("Do you want to buy " + property.getName() + " for " + property.getPrice());
			if (keyb.nextLine().equalsIgnoreCase("Yes")){
				if (player.buy(property) == true){
					System.out.println("bought");
				} else {
					System.out.println("Sorry cannot afford");
				}
			} else {
				//do nothing
			}
			
		} else {
			//must pay rent
			rent(property, player);
		}
	}	
	
}

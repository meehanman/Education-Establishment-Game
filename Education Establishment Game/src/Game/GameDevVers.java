import java.util.ArrayList;
import java.util.Scanner;

import utils.Dice;
import utils.Piece;
import utils.Player;
import board.Board;
import board.establishment.Establishment;
import board.establishment.Property;

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
	
	private Board board;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentTurn;
	private Dice dice = new Dice();
	private boolean extraTurnFlag;
	private boolean rentFlag;
	private boolean doublesFlag;
	private int[][] lastRoll;
	private final int NUM_OF_DICE;
	
	public Game(){
		//pass all object into this
	}
	
	public void takeTurn(String command, Player player, Board board){
		switch(command){
		case "roll":
			do {
				//Move player via roll and then check if the roll was a double
				//and change the flag appropriately
				setExtraTurnFlag(dice.checkDoublesRoll(roll(player,board)));
				if(isForSale(property)){
				//OFFER TO SELL _ SHOULD BE IN A GUI HANDLER
				} else {
					if(isOwnedBy(property, player)){
					//no need to pay
					} else {
					//must pay rent
					setRentFlag(rent(property,player));
					}
				}
			} while(isExtraTurnFlag());
			break;
		case "Pay Rent":
			//pay rent for property and clear flag.
			setRentFlag(rent(property,player));
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
			if(isExtraTurnFlag()){
				//if player has no extra turns
				endTurn();
				setRollFlag(true);
			} else {
				//You have to roll to continue
			}
			break;
		case "upgrade":
			upgrade();
			break;
			
		}
	}
	
	private void upgrade() {
		// TODO Auto-generated method stub
		
	}

	private void endTurn(int currentTurn, ArrayList<Player> players) {
		// change the player to the next player's turn
		currentTurn++;
		if (currentTurn > players.size()){
			currentTurn -= players.size();
		}
	}

	private void trade() {
		// TODO Auto-generated method stub
		
	}

	private String buy(Property property, Player player) {
		// TODO Auto-generated method stub
		if (player.buy(property) == true){
			return "bought";
		} else {
			return "sorry you do not have enough money";
		}
	}

	private void endGame() {
		// TODO Auto-generated method stub
		
	}

	private boolean rent(Property property, Player player) {
		// take rent value from one player and add to another
		if (player.getBalance() > property.getRent()){
			player.subBalance(property.getRent());
			property.getOwner().addBalance(property.getRent());
			return true;
		} else {
			return false;
		}
		
	}

	private String bankrupt(Player player, Board board, ArrayList<Player> players) {
		// declare player bankrupt and remove them from game.
		String temp = player.getName();
		resalePropertiesOwnedBy(player, board);
		removePlayer(player, players);
		return  temp + " is bankrupt!";
	}
	
	private void resalePropertiesOwnedBy(Player player, Board board){
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
	
	private void removePlayer(Player player, ArrayList<Player> players){
		players.remove(players.indexOf(player));
	}

	private void mortgage() {
		// TODO Auto-generated method stub
		
	}

	private int[] roll(Player player, Board board) {
		// rolls dice and ensures the value recieved keeps
		//the player on the board between start and end looping.
		int[] diceValues = dice.diceRoll(NUM_OF_DICE);
		int tempRoll = dice.add(diceValues);
		if (player.getPosition() + tempRoll > board.getProperties().length){
			player.setPosition(player.getPosition()
					+ tempRoll - board.getProperties().length);
		} else {
			player.setPosition(player.getPosition()	+ tempRoll);
		}
		return diceValues;
		
	}
	
	public boolean isForSale(Property property){
		if (property.getOwner().equals(null)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOwnedBy(Property property, Player player){
		if (property.getOwner().equals(player)){
			return true;
		} else {
			return false;
		}
	}

	//_____________________________GETTERS AND SETTERS_______________________________
	//_______________________(NO NEED TO TEST THESE METHODS)_________________________
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public boolean isExtraTurnFlag() {
		return extraTurnFlag;
	}

	public void setExtraTurnFlag(boolean rollFlag) {
		this.extraTurnFlag = rollFlag;
	}

	public boolean isRentFlag() {
		return rentFlag;
	}

	public void setRentFlag(boolean rentFlag) {
		this.rentFlag = rentFlag;
	}

	public int[][] getLastRoll() {
		return lastRoll;
	}

	public void setLastRoll(int[][] lastRoll) {
		this.lastRoll = lastRoll;
	}

	public boolean isDoublesFlag() {
		return doublesFlag;
	}

	public void setDoublesFlag(boolean doublesFlag) {
		this.doublesFlag = doublesFlag;
	}
	
	
	
}
package Game;
import java.util.ArrayList;

import utils.Player;
import board.Board;
import board.Effect;
import board.SpecialSquare;
import board.Square;
import board.establishment.Bar;
import board.establishment.Restaurant;
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
	
	public Board board; //Contains all the Squares, 
	public ArrayList<Player> players = new ArrayList<Player>();
	private int currentTurn; //Holds the location in the ArrayList of the players go
	private int currentGoes; //Used to track rolling doubles next go will call same player if not 0)
	
	public Game(ArrayList<Player> players){
		
		this.players = players;
		
		board = new Board();
		board.dice.roll();
	
	}
	
	/**
	 * LOGIC: What to happen when a player lands on a square
	 */
	public void landOn(Square square){
		Player player = getCurrentPlayer();
		String typeOfSquare = square.getSquareType();
		
		if(typeOfSquare 		== "Subject"){
			Subject subject = (Subject)square; 
			//If we land of a subject a few things can happen
			//2. Owned - 
				//Not me 
				//me 
			if(subject.getOwner()==null){
				//No owned - Want to buy it?
			}else{
				if(!subject.getOwner().equals(player)){
					//Need to pay rent
				}
				//Otherwise I've landed on my own property
			}
		}else if(typeOfSquare 	== 	"Bar"){
			Bar bar = (Bar)square; 
			//If we land on a bar a few things can happen
			//1. Not owned - Want to buy it?
			//2. Owned
				//How many owned? Pay the amount * rent to owner
		}else if(typeOfSquare 	== 	"Restaurant"){
			Restaurant restaurant = (Restaurant)square; 
			//If we land on a bar a few things can happen
			//1. Not owned - Want to buy it?
			//2. Owned
				//How many owned? Pay Rent
		}else if(typeOfSquare 	== 	"SpecialSquare"){
			SpecialSquare specialSquare = (SpecialSquare)square; 
			//The effect will apply to the user
		}else{
			System.out.println("Not Sure What Happened: Couldn't hind a type of Square we're on!");
		}
		
	}

	/**
	 * Sets the next Go up by incrementing the 
	 * currentTurn to the next value
	 */
	public void nextTurn(){
		//If more than 0 goes, then user rolls again
		if(currentGoes>0){
			currentTurn--;
		}
		if(currentTurn<players.size()){
			currentTurn++;
		}else{
			currentTurn = 0;
		}
		
		takeGo();
	}
	
	/**
	 * Takes a go for the current player
	 */
	private void takeGo(){
		int RollResult = rollDice();
		
		movePlayer(RollResult);
	}
	
	/**
	 * Roll's the 2 dice in the game and returns the result
	 * @return
	 */
	public int rollDice(){
		int die1Result = board.dice.roll();
		int die2Result = board.dice.roll();
		
		//If roll doubles, give the player another go
		if(die1Result == die2Result){
			currentGoes++;
		}
		
		return die1Result+die2Result;
	}
	
	
	/**
	 * Moves the player by a position
	 * @param i
	 */
	public void movePlayer(int i){
		Player currentPlayer = getCurrentPlayer();
		
		currentPlayer.movePosition(i);
		
		landOn(board.Squares[currentPlayer.getPosition()]);
	}
	
	/**
	 * Returns the current active player
	 * @return
	 */
	private Player getCurrentPlayer(){
		return players.get(currentTurn);
	}
	
}

package Game;
import java.util.ArrayList;

import utils.Player;
import board.Board;
import board.SpecialSquare;
import board.Square;
import board.establishment.Bar;
import board.establishment.Establishment;
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
	public int currentTurn; //Holds the location in the ArrayList of the players go
	private int currentGoes; //Used to track rolling doubles next go will call same player if not 0)
	private boolean diceRolled = false;
	public Game(ArrayList<Player> players){
		
		this.players = players;
		
		board = new Board();
	
	}
	
	/**
	 * LOGIC: What to happen when a player lands on a square
	 */
	public void landOn(Square square){
		//If its an establishment then it can be baught
		System.out.println(square.getClass());
		if(square instanceof Establishment){
			Establishment est = ((Establishment)(square));
			String typeOfSquare = est.getSquareType();

			//If the establishment has an owner and it isn't the player they need to pay
			if(est.hasOwner() && !(est.getOwner().equals(getCurrentPlayer()))){
				if(typeOfSquare.equals("Subject")){
					Subject sub = (Subject)(est);
					getCurrentPlayer().giveMoney(sub.getOwner(),sub.getRent());

				}else if(typeOfSquare.equals("Restaurant")){
					Restaurant rest = (Restaurant)(est);
					getCurrentPlayer().giveMoney(rest.getOwner(), rest.getRent(board.dice.getValue(), board.Squares));
					
				}else if(typeOfSquare.equals("Bar")){
					Bar bar = (Bar)(est);
					getCurrentPlayer().giveMoney(bar.getOwner(), bar.getRent(board.Squares));
				}
			
			}else{ //If it's a SpecialSquare
				System.out.println("landOn(): You've landed on a special Square called "+square.getName());
			}
			
		}
		
	}
	/**
	 * Updates the currentTurn
	 * 
	 * @important Uses currentGoes for users who have got Doubles etc.
	 */
	public void nextTurn(){
		
		//If the user has no extra goes
		if(currentGoes==0){
			if(currentTurn>=players.size()-1){
				currentTurn=0;
			}else{
				currentTurn++;
			}
		}else{
			currentGoes--;
		}
		//Allow the user to Roll the Dice
		diceRolled=false;
	}
	/**
	 * Roll dice method
	 * @return Dice Rolls for UI
	 */
	public int[] rollDice(){
		//Role the Dice
		int[] diceRoll = board.dice.rollDice();
		
		//Move the player
		movePlayer(board.dice.getValue());
		
		//Stop this player rolling the dice again
		diceRolled=true;
		
		//Return the values for UI
		return diceRoll;
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
	public Player getCurrentPlayer(){
		return players.get(currentTurn);
	}
	/**
	 * Searches the players array for the player P and returns it's location
	 * @param Player to search
	 * @return Location in array
	 */
	public int getPlayerIndex(Player player){
		int i=0;
		for(Player p : players){
			if(p.equals(player)){
				return i;
			}else{
				i++;
			}
		}
		return -1;
			
	}
	/**
	 * @Retrun if the dice can be rolled
	 */
	public boolean canRoll(){
		return !diceRolled;
	}
		
}

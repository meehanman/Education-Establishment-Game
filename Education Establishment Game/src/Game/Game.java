package Game;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.paint.Color;
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
	private boolean extraTurn; //Used to track rolling doubles next go will call same player if not 0)
	private boolean diceRolled = false;
	private boolean rentFlag;
	private int doubledRolled = 0;
	public Game(ArrayList<Player> players){
		
		this.players = players;
		
		board = new Board();
	
	}
	
	/**
	 * LOGIC: What to happen when a player lands on a square
	 */
	public void landOn(Square square){
		//If its an establishment then it can be baught
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
				}else{
					System.out.println("landOn(): square type not found.."+square.getSquareType());
				}
			
			}else{
				System.out.println("landOn(): Establishment doesn't have an owner, or the owner is this owner.."+square.getName());
			}
			
		}else{ //If it's a SpecialSquare
			System.out.println("landOn(): square is not an instanceof Establishment.."+square.getName());
		}
		
	}
	
	/**
	 * Updates the currentTurn
	 * 
	 * @important Uses currentGoes for users who have got Doubles etc.
	 */
	public void nextTurn(){
		
		//If the user has no extra goes
		if(extraTurn){
			extraTurn = false;
		}else{
			if(currentTurn>=players.size()-1){
				currentTurn=0;
			}else{
				currentTurn++;
			}
			doubledRolled=0;	
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
		
		//Rolling Doubles
		if(board.dice.isDoubles()){
			extraTurn=true;
			//Starts counter for 3 rolls = GotoJail
			doubledRolled++;
			if(doubledRolled>=2){
				getCurrentPlayer().SendToJail();
			}
		}
		
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
	/**
	 * Game over Clause!
	 * If more than one player has money
	 * 
	 */
	public boolean isGameOver(){
		int playersWithMoney = 0;
		for(Player p : players){
			if(p.getBalance()>=0){
				playersWithMoney++;
			}
		}
		
		return playersWithMoney>1?false:true;
	}
	
	public void upgrade(Subject subject){
		//if own all of a color and they are developed equally
		if(ownsAllColor(subject) && equalDevelopment(subject)){
			subject.buyHouse();
		}
	}
	
	public boolean equalDevelopment(Subject subject){
		ArrayList<Subject> tempSubArray = getAllColor(subject.getColor());
		for(int i = 0; i <tempSubArray.size();i++){
			if((subject.getHouses() - tempSubArray.get(i).getHouses()) >= 1){
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Subject> getAllColor(String color){
		ArrayList<Subject> tempSubArray = new ArrayList<Subject>(); 
		for (Square square: board.Squares){
			//If its an establishment then it can be upgraded
			if(square instanceof Subject){
				Subject tempSub = ((Subject)(square));

				//If the establishment is same color check it is 
				if(tempSub.getColor() == color ){
					tempSubArray.add(tempSub);
				}
			}

		}
		return tempSubArray;
	}
	
	public boolean ownsAllColor(Subject subject){
		ArrayList<Subject> tempSubArray = getAllColor(subject.getColor());
		for(int i = 0; i <tempSubArray.size();i++){
			if(tempSubArray.get(i).getOwner() != subject.getOwner()){
				return false;
			}
		}
		return true;
	}
	
	
	
	public void endGame(){
		//for each property on the board
		for (Square square: board.Squares){
			//If its an establishment then it can be baught
			if(square instanceof Establishment){
				Establishment est = ((Establishment)(square));
				String typeOfSquare = est.getSquareType();

				//If the establishment has an owner and it isn't the player they need to pay
				if(est.hasOwner()){
					if(typeOfSquare.equals("Subject")){
						Subject sub = (Subject)(est);
						sub.sellAllHouses();
					}
					est.getOwner().addBalance(est.getPrice());
				}
			}

		}
		//if owned - sell all houses and the property
		// adding the value to the player who owned it
		//winner has the highest value and so on
		
		
		//sorting algorithm to put players in the right order
		ArrayList<Player> mplayers = new ArrayList<Player>();
		Player highestValue = null;
		while(players.size() > 0){
			for(int i=players.size(); i > 0; i--){
				if (players.get(i).getBalance() > highestValue.getBalance() || highestValue == null){
					highestValue = players.get(i);
				}
				mplayers.add(highestValue);
				players.remove(highestValue);
			}
		}
		
		players = mplayers;
	}
	
		
}

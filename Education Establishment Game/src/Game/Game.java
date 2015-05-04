package Game;
import java.util.ArrayList;

import utils.Player;
import board.Board;
import board.Card;
import board.SpecialSquare;
import board.SpecialSquare.Type;
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
			
		}else{ 
			//If it's a SpecialSquare
			SpecialSquare specialSquare = (SpecialSquare)square;
			if(specialSquare.getType()==Type.ChanceCard){
				//Pick up Card and be affected by it
				affect(getCurrentPlayer(),board.ChanceCardsDeck.takeCard());
				//landOn(board.Squares[getCurrentPlayer().getPosition()]);
			}else if(specialSquare.getType()==Type.CommunityChest){
				//Pick up Card
				affect(getCurrentPlayer(),board.ComunityCheckCardsChest.takeCard());
			}
		}
		
	}
	
	/**
	 * Will add or subtract money from player depending on values of card, also
	 * it will change the position of the player. 
	 * @param player - player to be affected by movement/cost. 
	 * @param takeCard - the card draw from the deck.
	 */
	private void affect(Player player, Card takeCard) {
		System.out.println("reached!!");
		//check if money depends on houses owned
		if (takeCard.getEffect().isHouseCharge()){
			if (takeCard.getEffect().getMoney() > 0){
				//if money is positive add to balance.
				//multiplied by houses owned to amplify effect and carry out card logic
				player.addBalance(takeCard.getEffect().getMoney() * housesOwned(player));
			} else{
				//if money is negative subtract from balance.
				//multiplied by houses owned to amplify effect and carry out card logic
				player.subBalance(takeCard.getEffect().getMoney() * housesOwned(player));
			}
		}else{
			//if money no dependent on houses owned
			//add or take away money 
			if (takeCard.getEffect().getMoney() > 0){
				//if money is positive add to balance.
				player.addBalance(takeCard.getEffect().getMoney());
			} else{
				//if money is negative subtract from balance.
				player.subBalance(takeCard.getEffect().getMoney());
			}
		}
		System.out.println("reach!!");
		//check if moment caused
		if(takeCard.getEffect().isMovement()){
			//check if pass go.
			if(takeCard.getEffect().getPosition() < getCurrentPlayer().getPosition()){
				//add bonus for passing go.
				System.out.println("a");
				player.addBalance(200);
			}
			//move position
			System.out.println(takeCard.getEffect().getPosition());
			System.out.println(player.getPosition());
			player.moveto(takeCard.getEffect().getPosition());
			System.out.println(player.getPosition());
			//call land on to pay rent etc for new landed on square
			System.out.println(board.Squares[player.getPosition()]);
			landOn(board.Squares[player.getPosition()]);
		}
		
	}
	
	/**
	 * Counts the number of houses owned by the player passed in.
	 * @param player - the person's houses to be counted.
	 * @return int - number of houses owned by person.
	 */
	public int housesOwned(Player player){
		int houseCount = 0;
		for (Square square: board.Squares){
			//If its an establishment then it can be baught
			if(square instanceof Subject){
				Subject sub = ((Subject)(square));
				String typeOfSquare = sub.getSquareType();
				
				if(typeOfSquare.equals("Subject")){
					if(sub.getOwner().equals(player)){
						houseCount += sub.getHouses();
					}
				}
			}
		}
		return houseCount;
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
			System.out.println("Got doubles!");
			extraTurn=true;
			//Starts counter for 3 rolls = GotoJail
			doubledRolled++;
			if(doubledRolled>=2){
				getCurrentPlayer().SendToJail();
			}
		}
		
		movePlayer(board.dice.getValue());
		
		//Stop this player rolling the dice again
		diceRolled=true;
		
		//Return the values for UI
		return diceRoll;
	}
	
	/**
	 * Allows players to trade or sell establishments to other players in
	 * exchange for other properties or funds.
	 * @param player1 - a player who is engaging in trading.
	 * @param est1 - the establishments player1 wants to trade.
	 * @param bal1 - the amount of money player1 wants to trade.
	 * @param player2 - the second player engaging in trading.
	 * @param est2 - the establishments player2 wants to trade.
	 * @param bal2 - the amount of money player2 wants to trade.
	 */
	public void trade(Player player1, ArrayList<Establishment> est1, int bal1, Player player2,
			ArrayList<Establishment> est2, int bal2){
		//swap player1's establishments with player2.
		swapOwner(est1,player2);
		//swap player2's establishments with player1.
		swapOwner(est2,player1);
		//take balance from player1 and add to player2.
		player1.giveMoney(player2, bal1);
		//take balance from player2 and add to player1.
		player2.giveMoney(player1, bal2);
		
	}
	
	/**
	 * Allows an array of establishments to change owner from one
	 * player to another.
	 * @param estArray - array of establishments that are to change owner.
	 * @param newOwner - player object of the new owner of the establishments.
	 */
	public void swapOwner(ArrayList<Establishment> estArray, Player newOwner){
		//for each establishment sent in swap to newOwner
		for(Establishment est: estArray){
			for(int i = 0; i < board.Squares.length; i++){
				if(board.Squares[i].getName().contentEquals(est.getName())){
					//switch owners
					est.changeOwner(newOwner);
				}
			}
		}
	}
	
	
	
	/**
	 * Moves the player by a position
	 * @param i
	 */
	public void movePlayer(int i){
		Player currentPlayer = getCurrentPlayer();
		
		System.out.println("Player pos was "+getCurrentPlayer().getPosition());
		System.out.println("Moving player by :"+i);
		currentPlayer.movePosition(i);
		System.out.println("Player pos is "+getCurrentPlayer().getPosition());

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
	
	/**
	 * Will test if player should be able to add houses to a
	 * subject and will take appropriate action - either to
	 * buy the house or not allow the purchase;
	 * @param subject - the subject you wish to buy a house for.
	 * @return boolean - true if purchased, false otherwise.
	 */
	public boolean upgrade(Subject subject){
		//if own all of a color and they are developed equally
		if(ownsAllColor(subject) && equalDevelopment(subject)){
			subject.buyHouse();
			return true;
		}
			return false;
	}
	
	/**
	 * Will test subjects to ensure that all subjects of a color
	 * group are progressing evenly to allow for a steady ramp
	 * up in gameplay. This will stop a single subject being
	 * upgraded to maximum house count while the rest are left 
	 * at lower house counts.
	 * @param subject - the subject you want to compare with.
	 * @return boolean value - true if evenly developed, false otherwise.
	 */
	public boolean equalDevelopment(Subject subject){
		ArrayList<Subject> tempSubArray = getAllColor(subject.getColor());
		for(int i = 0; i <tempSubArray.size();i++){
			if((subject.getHouses() - tempSubArray.get(i).getHouses()) >= 1){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Will return an Arraylist of all the subjects that share
	 * a particular color.
	 * @param color - color group to search for
	 * @return ArrayList<Subject> of all subjects of a color group.
	 */
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
	
	/**
	 * Will return a boolean value for whether a player owns
	 * all the subjects of a particular color type.
	 * @param subject
	 * @return true if all of a color group owned, false otherwise.
	 */
	public boolean ownsAllColor(Subject subject){
		ArrayList<Subject> tempSubArray = getAllColor(subject.getColor());
		for(int i = 0; i <tempSubArray.size();i++){
			if(tempSubArray.get(i).getOwner() != subject.getOwner()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Allows a user to exit the game and declare themselves as
	 * bankrupt. This will be called when a user can no longer 
	 * afford to continue the game. It resets all establishments
	 * owned by the player to the way they were at the beginning
	 * of the game.
	 */
	public void bankrupt(){
		for (Square square: board.Squares){
			//If its an establishment then it can be baught
			if(square instanceof Establishment){
				Establishment est = ((Establishment)(square));
				String typeOfSquare = est.getSquareType();
				if(est.getOwner() == getCurrentPlayer()){
					//clear owner
					est.changeOwner(null);
				}
				
				if(typeOfSquare.equals("Subject")){
					Subject sub = (Subject)(est);
					sub.clearHouses();
				}
			}
		}
		getCurrentPlayer().flagBankrupt();
	}
	
	/**
	 * called at the end of the game to systematically tally up the
	 * worth of all remaining players in the game. Will then return
	 * an arraylist of players in order of 1st to 4th. 
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> endGame(){
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
		
		return mplayers;
	}
	
		
}

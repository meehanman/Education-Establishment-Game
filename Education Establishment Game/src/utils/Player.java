package utils;

import java.util.ArrayList;

import board.establishment.Establishment;

public class Player {
	String name;
	int balance;
	Piece token;
	int position;
	ArrayList<Establishment> propertiesOwned = new ArrayList<Establishment>();	
	boolean isInJail = false;
	boolean bankrupt = false;
	/**
	 * PUBLICALLY ASSCESSIBLE METHODS
	 */
	
	public Player(String name, int pieceId){
		this.name = name;
		this.token = new Piece(pieceId);
		this.position = 0;
		this.balance = 1500;
	}
	/**
	 * Constructors that has a custom balance value
	 * @param name
	 * @param pieceId
	 */
	public Player(String name, int pieceId, int balance){
		this.name = name;
		this.token = new Piece(pieceId);
		this.position = 0;
		this.balance = balance;
	}
	/**
	 * retrive player's name.
	 * @return player's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set players name
	 * @param name - new player's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * return current player's balance.
	 * @return player's current balance.
	 */
	public int getBalance() {
		return this.balance;
	}
	
	/**
	 * set the player's current balance.
	 * @param balance - new player's balance.
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/**
	 * 
	 * @param establishment
	 * @return
	 */
	public Piece getToken(){
		return this.token;
	}
	
	public boolean buy(Establishment establishment){
		//find cost of Establishment and test against user balance
		if(!establishment.hasOwner() && this.balance >= establishment.getPrice()){
			//Return true as purchase is accepted.
			this.balance -= establishment.getPrice();
			establishment.changeOwner(this);
			System.out.println("Congratulations on your purchase of "+establishment.getName()+"!  You have £"+balance+" remaining!");
			return true;
		}else{
			//alert user that they cannot afford to buy this
			//by sending back false 
			System.out.println("Your poor! You have £"+this.balance+" and this place costs £"+establishment.getPrice());
			return false;
		}
	}
	
	/**
	 * Allows the buyer to trade a property to another player 
	 * 
	 * @param establishment
	 * @param buyer
	 */
	private void tradeProperty(Establishment establishment, Player buyer){
		establishment.tradeProperty(this, buyer);
	}
	/**
	 * Allows the player to sell a property to another player
	 * @param establishment
	 * @param buyer
	 * @param Amount
	 */
	public void sellProperty(Establishment establishment, Player buyer, int Amount){
		if(buyer.getBalance() >= Amount){
			
			//Trade the property to the buyer
			tradeProperty(establishment, buyer);
			//The buyer then gives this player the money
			buyer.giveMoney(this, Amount);
			
		}
	}
	/**
	 * Gives money from this Player to another Player
	 * 
	 * @param reciever Player to Recieve 
	 * @param Amount of Money
	 */
	public boolean giveMoney(Player reciever,int Amount){
		if(this.getBalance() >= Amount && Amount > 0){
			reciever.addBalance(Amount);
			this.addBalance(-Amount);
			System.out.println(getName()+" gave "+Amount+" to "+reciever.getName());
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @return The players current Position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * Sets the players position to a particular position on the board
	 * @param i
	 */
	public void setPosition(int i){
		this.position = i;
	}
	/**
	 * Changes the players movement around the board
	 * 
	 * If the user moves and passes go give200
	 * if they land on go give400
	 * 
	 * @param i
	 */
	public void incrementPosition(int position) {
		this.position += position;
		
		if(this.position>=40){
			this.position-=40;
			
			//If the player has landed on Go! (They get 400, not 200)
			if(this.position==0){
				addBalance(400);
			}else{
				addBalance(200);
			}
		}
	}
	
	public void addBalance(int income){
		System.out.print("\nAddBalance(): "+getName()+" has £"+getBalance());
		this.balance += income;
		System.out.print("\n but now has "+getBalance()+" because "+income+" was added");
	}
	/**
	 * Subtracts balance from player account
	 * @param expenditure
	 * @return True if successful, false if the user doesn't have enough money
	 */
	public boolean subBalance(int expenditure){
		if(getBalance()>=expenditure){
			this.balance -= expenditure;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *  Sends the user to jail
	 */
	public void SendToJail(){
		System.out.println("Player sent to Jail!");
		this.isInJail = true;
		this.position = 10;
	}
	/**
	 *  Removes inJail Flag
	 */
	public void freeFromJail(){
		this.isInJail = false;
	}
	public boolean isInJaul(){
		return this.isInJail;
	}
	/**
	 * set player bankrupt flag to true
	 * which will stop them from having any
	 * more turns.
	 */
	public void flagBankrupt() {
		this.bankrupt = true;
	}
	

}

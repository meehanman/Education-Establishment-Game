package utils;

import java.util.ArrayList;

import board.establishment.Establishment;

public class Player {
	String name;
	int balance;
	Piece token;
	int position;
	ArrayList<Establishment> propertiesOwned = new ArrayList<Establishment>();	
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
	public double getBalance() {
		return balance;
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
		if(this.balance >= establishment.getPrice()){
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
			return true;
		}else{
			return false;
		}
	}
	public void mortgage(Establishment est){
		//get the mortgage value of the house
		this.balance += est.MortgageProperty();
	}
	
	public void move(int i){
		if (this.position < (40 - i)){
			this.position += i;
		}else{
			this.position = (this.position - 40) + i;
		}
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * Used to move the player around the board
	 * @param position
	 */
	public void movePosition(int position) {
		this.position += position;
		
		if(this.position>=40){
			this.position-=40;
		}
	}
	
	public void addBalance(double income){
		this.balance += income;
	}
	
	public void subBalance(double expenditure){
		this.balance -= expenditure;
	}
	

}

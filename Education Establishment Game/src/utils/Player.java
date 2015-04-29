package utils;

import java.util.ArrayList;
import board.establishment.Establishment;

public class Player {
	String name;
	double balance;
	Piece token;
	int position;
	ArrayList<Establishment> propertiesOwned = new ArrayList<Establishment>();
	
	/**
	 * PUBLICALLY ASSCESSIBLE METHODS
	 */
	
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
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public boolean buy(Establishment est){
		//find cost of Establishment and test against user balance
		if(this.balance > est.getPrice()){
			//Return true as purchase is accepted.
			this.balance -= est.getPrice();
			return true;
		}else{
			//alert user that they cannot afford to buy this
			//by sending back false 
			return false;
		}
	}
	public void sell(Establishment est, Player player){
		//set the new owner of the property
		est.changeOwner(player);
	}
	public void mortgage(Establishment est){
		//get the mortgage value of the house
		this.balance += est.MortgageProperty();
	}
	
	public ArrayList<Establishment> getPropertiesOwned() {
		return propertiesOwned;
	}
	public void setPropertiesOwned(ArrayList<Establishment> propertiesOwned) {
		this.propertiesOwned= propertiesOwned;
	}
	
	public void move(int i){
		if (this.position < (40 - i)){
			this.position += i;
		}else{
			this.position = (this.position - 40) + i;
		}
	}
	
	/**PRIVATE ACCESSIBLE METHODS
	 * player tokens accessed interally only due
	 * to only being set in constructor.
	 */
	private void playerToken(Piece token){
		this.token = token;
	}
	
	
	
	

}

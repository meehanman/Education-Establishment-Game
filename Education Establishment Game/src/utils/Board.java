package utils;

import javax.smartcardio.Card;

import board.establishment.Establishment;

public class Board {
	//Array storing all the players
	Player players[];
	//Array storing all the establishments
	Establishment establishments[];
	//Array storing the Dice.
	Dice dice[];
	//Array storing all the cards.
	Card cards[];
	
	/**
	 * Getters and setters
	 */
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public Establishment[] getEstablishments() {
		return establishments;
	}
	public void setEstablishments(Establishment[] establishments) {
		this.establishments = establishments;
	}
	public Dice[] getDice() {
		return dice;
	}
	public void setDice(Dice[] dice) {
		this.dice = dice;
	}
	public Card[] getCards() {
		return cards;
	}
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	
	

}

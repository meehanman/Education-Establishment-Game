package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CardDeck {
	
	
	ArrayList<Card> deck;
	ArrayList<Card> spentDeck;
	
	public CardDeck(Card[] Deck){
		deck = new ArrayList<Card>(Arrays.asList(Deck));
	}
	
	//Shuffles the cards
	public void shuffleCards(){
		
		long seed = System.nanoTime();
		Collections.shuffle(deck, new Random(seed));
		
	 }
	
	/**
	 * Allows the user to take the a card from the deck
	 * 
	 * @return
	 */
	public Card takeCard(){
		
		//If the deck is not empty
		if(!deck.isEmpty()){
			Card pickedUpCard = deck.remove(0);
			
			//If it is a get out of free card
			if(pickedUpCard.getEffect().getOutOfJailFree()){
				
			}
			spentDeck.add(pickedUpCard);
			return pickedUpCard;
		}else{ //If it is then reshuffle the cards
			
			//Move the cards around
			deck = spentDeck;
			spentDeck = null;
			
			//Shuffle
			shuffleCards();
			
			//Take another Card
			return takeCard();
			
		}
	}
	
	/**
	 * Adds a card back into the spent deck
	 * (ie if it was used by the player)
	 * @param card
	 */
	public void addCard(Card card){
		spentDeck.add(card);
	}
}

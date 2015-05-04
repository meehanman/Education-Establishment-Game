package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CardDeck {
	
	
	private ArrayList<Card> deck;
	private Card lastCard;
	private ArrayList<Card> spentDeck;
	
	public CardDeck(Card[] Deck){
		deck = new ArrayList<Card>(Arrays.asList(Deck));
		spentDeck = new ArrayList<Card>();
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
		//TEST
		System.out.println("CardDeck(): takeCard(): Size: "+deck.size());
		//END
		//If the deck is not empty
		if(!deck.isEmpty()){
			this.lastCard = deck.remove(0);
			
			//If it is a get out of free card
			if(lastCard.getEffect().getOutOfJailFree()){
				
			}
			
			spentDeck.add(lastCard);
			
			return lastCard;
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
	/**
	 * Get the current card that was pulled from the deck
	 */
	public Card showLastCard(){
		return lastCard;
	}
}

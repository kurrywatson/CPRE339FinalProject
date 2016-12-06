package clue;

import java.util.ArrayList;

public class Player {
	
	int cardAmount = 0;
	int id = -1;
	CardDeck cardDeck = new CardDeck();
	private ArrayList<Integer> confirmedCards = new ArrayList<Integer>();

	public Player(int id) {
		this.id = id;
	}
	
	/* getters and setters */

	public int getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(int cardAmount) {
		this.cardAmount = cardAmount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public ArrayList<Integer> getConfirmedCards() {
		return confirmedCards;
	}

	public void setConfirmedCards(ArrayList<Integer> confirmedCards) {
		this.confirmedCards = confirmedCards;
	}
	
}

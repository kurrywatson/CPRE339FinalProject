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

	
	public void solveTethers() {
		   for (ArrayList<Integer> tether : cardDeck.getTethers()) {
			   for (int i = 0; i < tether.size(); i++) {
				   if(getCardDeck().getDeck().contains(tether.get(i)) == false) tether.remove(i);
			   }
			   if(tether.size() == 1 && getConfirmedCards().contains(tether.get(0)) == false) {
				   confirmedCards.add((tether.get(0)));
			   }
			   
			   // if a card is known and is in the tether delete the tether
			   for (int i = 0; i < tether.size(); i++) {
				   if(getConfirmedCards().contains(tether.get(i))) {
					   tether.clear();
				   }
			   }
		   }
		
	}
	
}

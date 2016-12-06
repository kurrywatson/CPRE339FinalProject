package clue;

import java.util.*;

public class CardDeck {
	private ArrayList<ArrayList> tethers = new ArrayList<ArrayList>();
	private ArrayList<Integer> deck = new ArrayList<Integer>();
	
	public CardDeck() {
		initDeck();
	}
	
	public void removeSuggestion(String suggestion) {
		removeCard(Integer.parseInt(suggestion.split(",")[0]));
		removeCard(Integer.parseInt(suggestion.split(",")[1]));
		removeCard(Integer.parseInt(suggestion.split(",")[2]));
	}
	public void removeCard(int num) {
		deck.remove(Integer.valueOf(num));
	}
	
	public void createTether(int person, int weapon, int room) {
		ArrayList<Integer> tether = new ArrayList<Integer>();
		tether.add(person);
		tether.add(weapon);
		tether.add(room);
		tethers.add(tether);
	}

	public ArrayList<Integer> getDeck() {
		return deck;
	}

	public ArrayList<ArrayList> getTethers() {
		return tethers;
	}

	public void setTethers(ArrayList<ArrayList> tethers) {
		this.tethers = tethers;
	}

	public void setDeck(ArrayList<Integer> deck) {
		this.deck = deck;
	}

	public void initDeck() {
		for (int i = 1; i <= 21; i++) {
			deck.add(i);
		}
	}

}
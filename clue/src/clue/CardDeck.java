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
	
	
	/*
	public void resolveTethers() {
		
		for (Tether tether : tethers) {
			

			
			//if we have discovered the tether relies on something that isn't there set it to 0
			if(people.contains(tether.getPerson()) == false && tether.getPerson() != 0) {
				tether.setPerson(0);
			}
			if(weapons.contains(tether.getWeapon()) == false && tether.getWeapon() != 0) {
				tether.setWeapon(0);
			}
			if(rooms.contains(tether.getRoom()) == false && tether.getRoom() != 0) {
				tether.setRoom(0);
			}
			
			if(tether.getPerson() == 0 && tether.getWeapon() == 0) {
				confirmedCards++;
			}
		}
	}*/
		
	
	// getters/setters	
	
	/*public void enterSuggestion(int person, int weapon, int room, CardDeck playerSuggested, CardDeck playerAnswered) {
		if(playerAnswered == null) {
			playerAnswered = playerSuggested;
		}
		
		CardDeck playerToAnswer = playerSuggested.nextPlayer();
		while(playerToAnswer != playerAnswered) {
			playerToAnswer.removeRoom(room);
			playerToAnswer.removeWeapon(weapon);
			playerToAnswer.removePerson(person);
			playerToAnswer.updatetethers();
			
			if(playerToAnswer == MAXPLAYERS) {
				playerToAnswer = 1;
			} else {
				playerToAnswer++;
			}
		}
		
		playerAnswered.createTether(person,weapon,room);
		
		if(playerAnswered == 0) {
			return;
		}
		
		
		
	}*/

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

	private void updatetethers() {
		// TODO Auto-generated method stub
		
	};

	public void initDeck() {
		for (int i = 1; i <= 20; i++) {
			deck.add(i);
		}
	}

}
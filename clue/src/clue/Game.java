package clue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Game {
   public static void main(String[] args) {
	   
	   ArrayList<Player> players = new ArrayList<Player>();
	   CardDeck gameDeck = new CardDeck();
	   ArrayList<Integer> solvedCards = new ArrayList<Integer>();
	   
	   
	   Scanner reader = new Scanner(System.in);
	   System.out.println("Enter amount of players: ");
	   int playerNum = reader.nextInt();
	   for (int i = 0; i < playerNum; i++) {
		   players.add(new Player(i));
	   }
	   
	   //setup clue game. 
	   
	   System.out.println("Enter how many cards you have: ");
	   players.get(0).setCardAmount(reader.nextInt());
	   
	   for (int i = 1; i < playerNum; i++) {
		   System.out.println("How many cards does Player" + i + ": ");
		   players.get(i).setCardAmount(reader.nextInt());
	   }
	   
	   // add cards that you have to your hand and eliminate them from other's hand
	   System.out.println("What are your cards: ");
	   String[] player0Cards = reader.next().split(",");
	   for (String card : player0Cards) {
		   int cardNum = Integer.parseInt(card);
		   players.get(0).getConfirmedCards().add(cardNum);
		   gameDeck.removeCard(cardNum);
		   for (Player player : players) {
			   if(player.getId() != 0) {
				   player.getCardDeck().removeCard(cardNum);
			   }
		}
	   }
	   
	   boolean solved = false;
	   while(solved == false) {
		   System.out.println("Enter suggestion: ");
		   String suggestion = reader.next();
		   while(verifySuggestion(suggestion) == false) {
			   System.out.println("Enter suggestion: ");
			   suggestion = reader.next();
		   };
		   System.out.println("Who proposed the suggestion: ");
		   int playerSuggested = reader.nextInt();
		   System.out.println("Who answered the suggestion: ");
		   int playerAnswered = reader.nextInt();
		   System.out.println("playerAnswered: " + playerAnswered);
		   enterSuggestion(players, suggestion, playerSuggested, playerAnswered, solvedCards, gameDeck);
		   updateInformation(players, gameDeck, solvedCards);
		   displayInformation(players, gameDeck, solvedCards);
		   if(gameDeck.getDeck().size() == 3 || solvedCards.size() == 3) {
			   solved = true;
		   }
	   }
	   
	   System.out.println("the cards inside are: " + solvedCards);
	   reader.close();
	}
   
   private static boolean verifySuggestion(String suggestion) {
	   int person = Integer.parseInt(suggestion.split(",")[0]);
	   int weapon = Integer.parseInt(suggestion.split(",")[1]);
	   int room = Integer.parseInt(suggestion.split(",")[2]);
	   
	   if(person < 1 || person > 6) {
		   System.out.println("Invalid Suggestion: person was entered incorrectly");
		   return false;
	   }
	   if(weapon < 7 || weapon > 12) {
		   System.out.println("Invalid Suggestion: weapon was entered incorrectly");
		   return false;
	   }
	   if(room < 13 || room > 21) {
		   System.out.println("Invalid Suggestion: room was entered incorrectly");
		   return false;
	   }
	   
	   return true;
	   
   }

   public static void enterSuggestion(ArrayList<Player> players, String suggestion, int playerSuggested, int playerAnswered, ArrayList<Integer> solvedCards, CardDeck gameDeck ) {
		
	   // if no one can disprove the suggestion, eliminate it for everyone but the person asking
	   if(playerAnswered == -1) {
		   for (Player currentPlayer : players) {
			   if(currentPlayer.getId() == playerSuggested) continue;
			   currentPlayer.getCardDeck().removeSuggestion(suggestion);
		   }
		   //if you solved the person weapon or room add it solve and eliminate everything else
		   if(playerSuggested == 0) {
			   System.out.println("made it here");
			   int person = Integer.parseInt(suggestion.split(",")[0]);
			   int weapon = Integer.parseInt(suggestion.split(",")[1]);
			   int room = Integer.parseInt(suggestion.split(",")[2]);
			   
			   System.out.println("person: " + person);
			   System.out.println("weapon: " + weapon);
			   System.out.println("room: " + room);
			   if(players.get(0).getConfirmedCards().contains(person) == false && solvedCards.contains(person) == false) {
				   solvedCards.add(person);
				   for (Player player : players) {
					   for (int i = 1; i <= 6; i++) {
						   if(i != person) {
							   player.getCardDeck().removeCard(i);
							   gameDeck.removeCard(i);
						   }
					   }
				   }
			   }
			   if(players.get(0).getConfirmedCards().contains(weapon) == false && solvedCards.contains(weapon) == false) {
				   solvedCards.add(weapon);
				   for (Player player : players) {
					   for (int i = 7; i <= 12; i++) {
						   if(i != weapon) {
							   player.getCardDeck().removeCard(i);
							   gameDeck.removeCard(i);
						   }
					   }
				   }
			   }
			   if(players.get(0).getConfirmedCards().contains(room) == false && solvedCards.contains(room) == false) {
				   solvedCards.add(room);
				   for (Player player : players) {
					   for (int i = 13; i <= 21; i++) {
						   if(i != room) {
							   player.getCardDeck().removeCard(i);
							   gameDeck.removeCard(i);
						   }
					   }
				   }
			   }
		   }
	   }
	   //this means someone did answer it.
	   else {
		   //eliminate cards for all players who could not disprove it.
		   	Player currentPlayer = getNextPlayer(players,playerSuggested);
		   	while(currentPlayer.getId() != playerAnswered) {
		   		currentPlayer.getCardDeck().removeSuggestion(suggestion);
		   		currentPlayer = getNextPlayer(players,currentPlayer.getId());
		   	}
		
		   	//if you had the suggestion, what were you shown and mark it to that person
		   	if(playerSuggested == 0) {
		   		Scanner reader = new Scanner(System.in);
		   		System.out.println("What card were you shown: ");
		   		int card = reader.nextInt();
		   		players.get(playerAnswered).getConfirmedCards().add(card);
		   		reader.close();
		   	} 
		   	// if someone else had the suggestion create a tether on the person they showed it to
		   	else {
		   		int person = Integer.parseInt(suggestion.split(",")[0]);
		   		int weapon = Integer.parseInt(suggestion.split(",")[1]);
		   		int room = Integer.parseInt(suggestion.split(",")[2]);
		   		players.get(playerAnswered).getCardDeck().createTether(person, weapon, room);
		   	}
	   }
   }
   
   public static void updateInformation(ArrayList<Player> players, CardDeck gameDeck, ArrayList<Integer> solvedCards) {
	
	   //delete all solved cards from gamedeck options
	   for (Integer solvedCard : solvedCards) {
		   gameDeck.removeCard(solvedCard);
	   }
	   
	   //finds all cards that are already known and eliminate them from the gameDeck
	   ArrayList<Integer> cardsFound = new ArrayList<Integer>();
	   for (Player player : players) {
		   cardsFound.addAll(player.getConfirmedCards());
		   for (Integer confirmedCard : player.getConfirmedCards()) {
			   gameDeck.removeCard(confirmedCard);
		   }
	   }
	   
	   for (Player player : players) {
		   //eliminates all the cards that are known from other player's cards
		   for (Integer card : cardsFound) {
			   if(player.getConfirmedCards().contains(card) == false) {
				   player.getCardDeck().removeCard(card);
			   }
		   }
		   //if all the possible cards are known for a player, eliminate other cards for them.
		   if(player.getCardAmount() == player.getConfirmedCards().size()) {
			   player.getCardDeck().setDeck(player.getConfirmedCards());
		   }
	   }
	   
	   //simplify all tethers as much as possible
	   for (Player player : players) {
		   player.solveTethers();
	   }
	   
	   //re-eliminate gamedeck cards based on new information
	   for (Player player : players) {
		   for (Integer confirmedCard : player.getConfirmedCards()) {
			   gameDeck.removeCard(confirmedCard);
		   }
	   }
   }
   
   public static void displayInformation(ArrayList<Player> players, CardDeck gameDeck, ArrayList<Integer> solvedCards) {
	   System.out.println("gamedeck: " + gameDeck.getDeck());
	   System.out.println("solvedCards: " + solvedCards);
	   for (Player player : players) {
		   System.out.println(player.getId() + ": " +  player.getCardDeck().getDeck());
		   System.out.println(player.getCardDeck().getTethers());
		   System.out.println(player.getConfirmedCards());
	   }
   }
   
   public static Player getNextPlayer(ArrayList<Player> players, int currentPlayer) {
	   if(currentPlayer == players.size() - 1) {
		   return players.get(0);
	   } else {
		   return players.get(currentPlayer + 1);
	   }  
   }
   
}

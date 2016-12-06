package clue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Game {
   public static void main(String[] args) {
	   
	   ArrayList<Player> players = new ArrayList<Player>();
	   CardDeck gameDeck = new CardDeck();
	   
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
		   System.out.println("Who proposed the suggestion: ");
		   int playerSuggested = reader.nextInt();
		   System.out.println("Who answered the suggestion: ");
		   int playerAnswered = reader.nextInt();
		   System.out.println("playerAnswered: " + playerAnswered);
		   enterSuggestion(players, suggestion, playerSuggested, playerAnswered);
		   updateInformation(players, gameDeck);
		   displayInformation(players, gameDeck);
		   if(gameDeck.getDeck().size() == 3) {
			   solved = true;
		   }
	   }
	   
	   System.out.println("Would you like to play again? (y/n) ");
	   String playAgain = reader.next();	
		
	}
   
   public static void enterSuggestion(ArrayList<Player> players, String suggestion, int playerSuggested, int playerAnswered) {
		
	   // if no one can disprove the suggestion, eliminate it for everyone but the person asking
	   if(playerAnswered == -1) {
		   for (Player currentPlayer : players) {
			   if(currentPlayer.getId() == playerSuggested) continue;
			   currentPlayer.getCardDeck().removeSuggestion(suggestion);
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
   
   public static void updateInformation(ArrayList<Player> players, CardDeck gameDeck) {
	   //finds all cards that are already known
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
				   /* DEBUG CODE
				   System.out.println("");
				   System.out.println("was false for player: " + player.getId());
				   System.out.println("the deck of cards was" + player.getCardDeck());
				   System.out.println("the confirmed cards looked like: " + player.getConfirmedCards());
				   System.out.println("the card to match was: " + card);
				   */
				   player.getCardDeck().removeCard(card);
			   }
		   }
		   //if all the possible cards are known for a player, eliminate other cards for them.
		   if(player.getCardAmount() == player.getConfirmedCards().size()) {
			   player.getCardDeck().setDeck(player.getConfirmedCards());
		   }
	   }
	   
	   for (Player player : players) {
		   ArrayList<Integer> newTether = new ArrayList<Integer>();
		   for (ArrayList<Integer> tether : player.getCardDeck().getTethers()) {
			   for (int i = 0; i < tether.size(); i++) {
				   if(player.getCardDeck().getDeck().contains(tether.get(i)) == false) tether.remove(i);
			   }
			   if(tether.size() == 1) {
				   player.getConfirmedCards().add((tether.get(0)));
			   }
			   
			   // if a card is known and is in the tether delete the tether
			   for (int i = 0; i < tether.size(); i++) {
				   if(player.getConfirmedCards().contains(tether.get(i))) {
					   tether.clear();
				   }
			   }
		   }
	   }
	   
		   
		   //eliminate all found cards from everyone elses deck
		   //eliminate all other cards from someones deck if all their cards are found
		   //
		   //solve any tethers
		   //
		   //
		   //
		   //
		   
		   
		   
		   //System.out.println(player.getCardDeck().getDeck());
		   //System.out.println(player.getCardDeck().getTethers());
   }
   
   public static void displayInformation(ArrayList<Player> players, CardDeck gameDeck) {
	   System.out.println("gamedeck" + gameDeck.getDeck());
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

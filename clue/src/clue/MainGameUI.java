package clue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGameUI extends JFrame {

	private JPanel contentPane;
	private int numPlayers;
	private int numCards;
	private String playerCards;
	private JTextField suggestionText;
	private JTextField proposedText;
	private JTextField answerText;
	private static JTextArea gameText;
	private static ArrayList<Player> players;
	private static CardDeck gameDeck;
	private static ArrayList<Integer> solvedCards;
	private JTextField sawText;
	private static JPanel altPanel;
	private JPanel standardPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameUI frame = new MainGameUI(1,1,"");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGameUI(int numPlayers, int numCards, String playerCards) {
		this.numPlayers = numPlayers;
		this.numCards = numCards;
		this.playerCards = playerCards;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 507, 194);
		contentPane.add(scrollPane);
		
		gameText = new JTextArea();
		scrollPane.setViewportView(gameText);
		gameText.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		standardPanel = new JPanel();
		standardPanel.setBounds(10, 11, 353, 117);
		contentPane.add(standardPanel);
		standardPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Confirm Info");
		btnNewButton.setBounds(228, 48, 106, 23);
		standardPanel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Player answered:");
		lblNewLabel_1.setBounds(0, 81, 162, 14);
		standardPanel.add(lblNewLabel_1);
		
		answerText = new JTextField();
		answerText.setBounds(111, 78, 86, 20);
		standardPanel.add(answerText);
		answerText.setColumns(10);
		
		proposedText = new JTextField();
		proposedText.setBounds(111, 49, 86, 20);
		standardPanel.add(proposedText);
		proposedText.setColumns(10);
		
		suggestionText = new JTextField();
		suggestionText.setBounds(111, 18, 86, 20);
		standardPanel.add(suggestionText);
		suggestionText.setColumns(10);
		
		JLabel lblSuggestion = new JLabel("Suggestion:");
		lblSuggestion.setBounds(0, 22, 123, 14);
		standardPanel.add(lblSuggestion);
		
		JLabel lblNewLabel = new JLabel("Player asking:");
		lblNewLabel.setBounds(0, 52, 170, 14);
		standardPanel.add(lblNewLabel);
		
		altPanel = new JPanel();
		altPanel.setBounds(373, 11, 144, 118);
		contentPane.add(altPanel);
		altPanel.setLayout(null);
		altPanel.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players.get(Integer.parseInt(answerText.getText())).getConfirmedCards().add(Integer.parseInt(sawText.getText()));
				updateInformation(players, gameDeck, solvedCards);
				displayInformation(players, gameDeck, solvedCards);
				altPanel.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(25, 84, 104, 23);
		altPanel.add(btnNewButton_1);
		
		JLabel lblWhatCardDid = new JLabel("What card did you see?");
		lblWhatCardDid.setBounds(0, 0, 134, 34);
		altPanel.add(lblWhatCardDid);
		
		sawText = new JTextField();
		sawText.setBounds(25, 36, 86, 20);
		altPanel.add(sawText);
		sawText.setColumns(10);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!altPanel.isVisible()) {
					if (verifySuggestion(suggestionText.getText())) {
						enterSuggestion(players, suggestionText.getText(), Integer.parseInt(proposedText.getText()),
								Integer.parseInt(answerText.getText()), solvedCards, gameDeck);
						updateInformation(players, gameDeck, solvedCards);
						displayInformation(players, gameDeck, solvedCards);
						if ( solvedCards.size() == 3) {// gameDeck.getDeck().size() == 3 ||
							gameText.setText("Puzzle solved!" + "\n" + "The cards inside are: " + solvedCards);
						}
					}
					else{
						return;
					}
				}
			}
		});
		Game();
	}
	
	private void Game() {
		players = new ArrayList<Player>();
	   gameDeck = new CardDeck();
	   solvedCards = new ArrayList<Integer>();
	   
	   for (int i = 0; i < numPlayers; i++) {
  			players.add(new Player(i));
	   }
	   
	   //setup clue game. 
	   

  		for (int i = 0; i < numPlayers; i++) {
		   
		   players.get(i).setCardAmount(numCards);
	   }
	   
   		String[] player0Cards = playerCards.split(",");
   		
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

	private static void searchSolved() {
		int personCount = 0;
		int weponCount = 0;
		int roomCount = 0;

		for (int i = 0; i < gameDeck.getDeck().size(); i++) {
			int temp = gameDeck.getDeck().get(i);
			if (temp >= 1 && temp <= 6) {
				personCount++;
			} else if (temp >= 7 && temp <= 12) {
				weponCount++;
			} else if (temp >= 13 && temp <= 21) {
				roomCount++;
			}
		}
		if (personCount == 1) {
			for (int i = 0; i < gameDeck.getDeck().size(); i++) {
				int temp = gameDeck.getDeck().get(i);
				if((temp >= 1 && temp <= 6)){
					gameDeck.removeCard(temp);
					solvedCards.add(temp);
				}
			}
		}
		if (weponCount == 1) {
			for (int i = 0; i < gameDeck.getDeck().size(); i++) {
				int temp = gameDeck.getDeck().get(i);
				if((temp >= 7 && temp <= 12)){
					gameDeck.removeCard(temp);
					solvedCards.add(temp);
				}
			}
		}
		if (roomCount == 1) {
			for (int i = 0; i < gameDeck.getDeck().size(); i++) {
				int temp = gameDeck.getDeck().get(i);
				if((temp >= 13 && temp <= 21)){
					gameDeck.removeCard(temp);
					solvedCards.add(temp);
				}
			}
		}

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
			   
			   gameText.setText(gameText.getText() +"person: " + person + "\n");
			   gameText.setText(gameText.getText() +"weapon: " + weapon + "\n");
			   gameText.setText(gameText.getText() +"room: " + room + "\n");
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
		   		altPanel.setVisible(true);
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
	   
	   //update game based on new information
	   for (Player player : players) {
		   for (Integer confirmedCard : player.getConfirmedCards()) {
			   gameDeck.removeCard(confirmedCard);
		   }
	   }
	   for (Player player : players) {
		   player.solveTethers();
	   }
	   for (Player player : players) {
		   for (Integer confirmedCard : player.getConfirmedCards()) {
			   gameDeck.removeCard(confirmedCard);
		   }
	   }
	   searchSolved();
	   
}



public static void displayInformation(ArrayList<Player> players, CardDeck gameDeck, ArrayList<Integer> solvedCards) {
       gameText.setText("");
       gameText.setText("Unknown Cards: " + gameDeck.getDeck() + "\n");
       gameText.setText(gameText.getText() + "Solved Cards: " + solvedCards + "\n" + "\n");
	   for (Player player : players) {
		   gameText.setText(gameText.getText() + "Player " + player.getId() + ": " +  player.getCardDeck().getDeck() + "\n");
		   gameText.setText(gameText.getText() + player.getCardDeck().getTethers() + "\n");
		   gameText.setText(gameText.getText() + player.getConfirmedCards() + "\n" + "\n");
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



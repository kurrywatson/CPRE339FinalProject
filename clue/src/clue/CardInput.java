package clue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CardInput extends JFrame {

	private JPanel contentPane;
	private JTextField cardsText;
	private String cards;
	private JButton btnStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardInput frame = new CardInput(0,0);
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
	public CardInput(int numPlayers,int numCards) {
		cards = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputTheCards = new JLabel("Input the cards you recived");
		lblInputTheCards.setBounds(107, 33, 223, 14);
		contentPane.add(lblInputTheCards);
		
		cardsText = new JTextField();
		cardsText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardsText.setBounds(58, 58, 272, 31);
		contentPane.add(cardsText);
		cardsText.setColumns(10);
		
		btnStart = new JButton("Start!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainGameUI game = new MainGameUI (numPlayers,numCards, cardsText.getText());
				game.setVisible(true);
				dispose();
			}
		});
		btnStart.setBounds(138, 127, 89, 23);
		contentPane.add(btnStart);
	}
	public String getCards(){
		return cards;
	}
}

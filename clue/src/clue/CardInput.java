package clue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputTheCards = new JLabel("Input the cards you recived");
		lblInputTheCards.setBounds(124, 33, 138, 14);
		contentPane.add(lblInputTheCards);
		
		cardsText = new JTextField();
		cardsText.setBounds(58, 58, 272, 102);
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
		btnStart.setBounds(136, 195, 89, 23);
		contentPane.add(btnStart);
	}
	public String getCards(){
		return cards;
	}
}

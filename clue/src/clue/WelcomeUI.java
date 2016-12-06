package clue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeUI extends JFrame {

	private JPanel contentPane;
	private JTextField numPlayersText;
	private JTextField numCardsText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeUI frame = new WelcomeUI();
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
	public WelcomeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the game of Clue!");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 39, 243, 32);
		contentPane.add(lblNewLabel);
		
		numPlayersText = new JTextField();
		numPlayersText.setEditable(false);
		numPlayersText.setText("6");
		numPlayersText.setBounds(41, 143, 86, 20);
		contentPane.add(numPlayersText);
		numPlayersText.setColumns(10);
		
		numCardsText = new JTextField();
		numCardsText.setEditable(false);
		numCardsText.setText("3");
		numCardsText.setBounds(288, 143, 86, 20);
		contentPane.add(numCardsText);
		numCardsText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Players");
		lblNewLabel_1.setBounds(32, 117, 135, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Numer of Cards Per Person");
		lblNewLabel_2.setBounds(252, 117, 172, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton startButton = new JButton("Start!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				CardInput CI = new CardInput(Integer.parseInt(numPlayersText.getText()),Integer.parseInt(numCardsText.getText()));
				CI.setVisible(true);
				dispose(); 
			}
		});
		startButton.setBounds(166, 83, 89, 23);
		contentPane.add(startButton);
		
		
	}

}

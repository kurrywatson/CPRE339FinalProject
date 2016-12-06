package clue;

import java.awt.BorderLayout;
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
	private JTextField textField_2;
	private JTextField textField_3;

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
		numPlayersText.setBounds(61, 143, 86, 20);
		contentPane.add(numPlayersText);
		numPlayersText.setColumns(10);
		
		numCardsText = new JTextField();
		numCardsText.setText("3");
		numCardsText.setBounds(288, 143, 86, 20);
		contentPane.add(numCardsText);
		numCardsText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(61, 217, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(288, 217, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Players");
		lblNewLabel_1.setBounds(43, 117, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Numer of Cards Per Person");
		lblNewLabel_2.setBounds(249, 117, 137, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(61, 192, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(300, 192, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton startButton = new JButton("Start!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//will need to be defensive here.
				setVisible(false); //you can't see me!
				CardInput CI = new CardInput(Integer.parseInt(numPlayersText.getText()),Integer.parseInt(numCardsText.getText()));
				CI.setVisible(true);
				//setVisible(false); //you can't see me!
				dispose(); 
			}
		});
		startButton.setBounds(166, 83, 89, 23);
		contentPane.add(startButton);
		
		
	}

}

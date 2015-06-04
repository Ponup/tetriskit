package com.ponup.tetriskit.panels;

import com.ponup.tetriskit.resources.EnhancedTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainPanel extends BasePanel {

	private static final long serialVersionUID = 5026054970629396982L;

	private EnhancedTextField playerNameTextfield;
	
	private JButton newGameButton;
	private JButton viewScoresButton;
	private JButton creditsButton;
	private JButton quitButton;

	public MainPanel() {
		super();
		
		playerNameTextfield = new EnhancedTextField(40);
		playerNameTextfield.setPlaceholder("PLAYER NAME");
		playerNameTextfield.setMaximumSize(playerNameTextfield.getPreferredSize());
		playerNameTextfield.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		playerNameTextfield.setOpaque(true);

		newGameButton = new JButton("New game");
		newGameButton.setOpaque(false);
		newGameButton.setActionCommand("btnNewGame");
		viewScoresButton = new JButton("View scores");
		viewScoresButton.setActionCommand("btnViewScores");
		creditsButton = new JButton("Visit game website");
		creditsButton.setActionCommand("btnCredits");
		quitButton = new JButton("Quit");
		quitButton.setActionCommand("btnQuit");

		placeComponents();
	}

	private void placeComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Tetris kit");
		title.setForeground(Color.WHITE);
		Font font = new Font("Arial", Font.ITALIC | Font.BOLD, 40);
		title.setFont(font);
		
		add(title);
		add(Box.createVerticalStrut(30));
		add(Box.createVerticalStrut(14));
		add(playerNameTextfield);
		add(newGameButton);
		add(viewScoresButton);
		add(Box.createVerticalStrut(10));
		add(creditsButton);
		add(quitButton);
	}

	public void setActionListener(ActionListener listener) {
		newGameButton.addActionListener(listener);
		viewScoresButton.addActionListener(listener);
		creditsButton.addActionListener(listener);
		quitButton.addActionListener(listener);
	}

	public EnhancedTextField getPlayerNameTextfield() {
		return playerNameTextfield;
	}	
}

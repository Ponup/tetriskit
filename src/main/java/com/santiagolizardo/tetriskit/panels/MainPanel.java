package com.santiagolizardo.tetriskit.panels;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainPanel extends BasePanel {

	private static final long serialVersionUID = 5026054970629396982L;

	private JButton btnNewGame;
	private JButton btnViewScores;
	private JButton btnGamePieces;
	private JButton btnCredits;
	private JButton btnQuit;

	public MainPanel() {
		super();

		btnNewGame = new JButton("New game");
		btnNewGame.setActionCommand("btnNewGame");
		btnViewScores = new JButton("View scores");
		btnViewScores.setActionCommand("btnViewScores");
		btnGamePieces = new JButton("Modify figures");
		btnGamePieces.setActionCommand("btnGamePieces");
		btnCredits = new JButton("Credits");
		btnCredits.setActionCommand("btnCredits");
		btnQuit = new JButton("Quit");
		btnQuit.setActionCommand("btnQuit");

		placeComponents();
	}

	private void placeComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Tetris kit");
		Font font = new Font("Arial", Font.ITALIC | Font.BOLD, 40);
		title.setFont(font);

		add(title);
		add(Box.createVerticalStrut(30));
		add(btnNewGame);
		add(btnViewScores);
		add(Box.createVerticalStrut(10));
		add(btnGamePieces);
		add(Box.createVerticalStrut(10));
		add(btnCredits);
		add(btnQuit);
	}

	public void setActionListener(ActionListener listener) {
		btnNewGame.addActionListener(listener);
		btnViewScores.addActionListener(listener);
		btnGamePieces.addActionListener(listener);
		btnCredits.addActionListener(listener);
		btnQuit.addActionListener(listener);
	}
}

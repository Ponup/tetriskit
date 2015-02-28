package com.santiagolizardo.tetriskit.panels;

import com.santiagolizardo.tetriskit.MainGUI;
import com.santiagolizardo.tetriskit.resources.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainPanel extends BasePanel {

	private static final long serialVersionUID = 5026054970629396982L;

	private JButton btnNewGame;
	private JButton btnViewScores;
	private JButton btnCredits;
	private JButton btnQuit;

	public MainPanel() {
		super();

		btnNewGame = new JButton("New game");
		btnNewGame.setOpaque(false);
		btnNewGame.setActionCommand("btnNewGame");
		btnViewScores = new JButton("View scores");
		btnViewScores.setActionCommand("btnViewScores");
		btnCredits = new JButton("Visit game website");
		btnCredits.setActionCommand("btnCredits");
		btnQuit = new JButton("Quit");
		btnQuit.setActionCommand("btnQuit");

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
		add(btnNewGame);
		add(btnViewScores);
		add(Box.createVerticalStrut(10));
		add(btnCredits);
		add(btnQuit);
	}

	public void setActionListener(ActionListener listener) {
		btnNewGame.addActionListener(listener);
		btnViewScores.addActionListener(listener);
		btnCredits.addActionListener(listener);
		btnQuit.addActionListener(listener);
	}
}

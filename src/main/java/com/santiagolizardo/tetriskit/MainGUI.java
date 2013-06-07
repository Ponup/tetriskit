package com.santiagolizardo.tetriskit;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.santiagolizardo.tetriskit.logic.Engine;
import com.santiagolizardo.tetriskit.panels.CreditsPanel;
import com.santiagolizardo.tetriskit.panels.GameSetupPanel;
import com.santiagolizardo.tetriskit.panels.MainPanel;
import com.santiagolizardo.tetriskit.panels.ScoresPanel;
import com.santiagolizardo.tetriskit.resources.SoundLoader;

/**
 * Application main entry point.
 */
@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainGUI tetris = new MainGUI();
				tetris.setVisible(true);
			}
		});
	}

	private GameSetupPanel setupPanel;

	private CardLayout cardLayout;
	private JPanel container;

	public MainGUI() {
		super();

		setTitle(Constants.GAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		MainPanel mainPanel = new MainPanel();
		mainPanel.setActionListener(this);

		ScoresPanel scoresPanel = new ScoresPanel();
		scoresPanel.setActionListener(this);

		CreditsPanel creditsPanel = new CreditsPanel();
		creditsPanel.setActionListener(this);

		setupPanel = new GameSetupPanel();
		setupPanel.getButtonStart().addActionListener(this);
		setupPanel.getButtonBack().addActionListener(this);

		cardLayout = new CardLayout();

		container = (JPanel) getContentPane();
		container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		container.setOpaque(true);
		container.setBackground(new Color(0xFF2A00));

		container.setLayout(cardLayout);
		container.add(mainPanel, "main");
		container.add(scoresPanel, "scores");
		container.add(creditsPanel, "credits");
		container.add(setupPanel, "setup");

		pack();

		SoundLoader.loadSounds();

		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setupPanel.getButtonStart()) {
			short boardWidth = setupPanel.getAltoTablero();
			short boardHeight = setupPanel.getAnchoTablero();
			Engine engine = new Engine(boardHeight, boardWidth);
			engine.setVisible(true);
			engine.start();
		} else {
			String action = event.getActionCommand();
			if ("SHOW_MAIN".equals(event.getActionCommand())) {
				setTitle("Tetris kit");
				cardLayout.show(container, "main");
			} else if ("btnNewGame".equals(action)) {
				setTitle("Game setup - Tetris kit");
				cardLayout.show(container, "setup");
			} else if ("btnViewScores".equals(action)) {
				setTitle("View scores - Tetris kit");
				cardLayout.show(container, "scores");
			} else if ("btnCredits".equals(action)) {
				setTitle("Credits - Tetris kit");
				cardLayout.show(container, "credits");
			} else if ("btnGamePieces".equals(action)) {
				setTitle("Pieces - Tetris kit");
				cardLayout.show(container, "pieces");
			} else if ("btnGameOptions".equals(action)) {
				setTitle("Game options - Tetris kit");
				cardLayout.show(container, "preferences");
			} else if ("btnQuit".equals(action)) {
				dispose();
				System.exit(0);
			}
		}
	}
}

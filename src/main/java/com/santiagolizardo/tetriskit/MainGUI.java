package com.santiagolizardo.tetriskit;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.santiagolizardo.tetriskit.logic.Engine;
import com.santiagolizardo.tetriskit.logic.GameState;
import com.santiagolizardo.tetriskit.panels.GameSetupPanel;
import com.santiagolizardo.tetriskit.panels.MainPanel;
import com.santiagolizardo.tetriskit.panels.ScoresPanel;
import com.santiagolizardo.tetriskit.resources.SoundLoader;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application main entry point.
 */
@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {

	private static final Logger logger = Logger.getLogger(MainGUI.class.getName());

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGUI tetris = new MainGUI();
				tetris.setVisible(true);
			}
		});
	}

	private MainPanel mainPanel;
	private ScoresPanel scoresPanel;
	private GameSetupPanel setupPanel;

	private CardLayout cardLayout;
	private JPanel container;

	public MainGUI() {
		super();

		setTitle(Constants.GAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		scoresPanel = new ScoresPanel();
		scoresPanel.setActionListener(this);

		mainPanel = new MainPanel();
		mainPanel.setActionListener(this);
		
		setupPanel = new GameSetupPanel();
		setupPanel.getButtonStart().addActionListener(this);
		setupPanel.getButtonBack().addActionListener(this);

		cardLayout = new CardLayout();

		container = (JPanel) getContentPane();
		container.setLayout(cardLayout);
		container.add(mainPanel, "main");
		container.add(scoresPanel, "scores");
		container.add(setupPanel, "setup");

		pack();

		SoundLoader.loadSounds();

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setupPanel.getButtonStart()) {
			short boardWidth = setupPanel.getBoardHeight();
			short boardHeight = setupPanel.getBoardWidth();
			
			GameState gameState = new GameState(boardWidth, boardHeight);
			gameState.setPlayerName(mainPanel.getPlayerNameTextfield().getText());
			
			Engine engine = new Engine(gameState);
			engine.setVisible(true);
			engine.turnOn();
		} else {
			String action = event.getActionCommand();
			if ("SHOW_MAIN".equals(event.getActionCommand())) {
				setTitle("Tetriskit");
				cardLayout.show(container, "main");
			} else if ("btnNewGame".equals(action)) {
				setTitle("Game setup - Tetriskit");
				cardLayout.show(container, "setup");
			} else if ("btnViewScores".equals(action)) {
				setTitle("View scores - Tetriskit");
				scoresPanel.refreshScores();
				cardLayout.show(container, "scores");
			} else if ("btnCredits".equals(action)) {
				openGameUrl();
			} else if ("btnGameOptions".equals(action)) {
				setTitle("Game options - Tetriskit");
				cardLayout.show(container, "preferences");
			} else if ("btnQuit".equals(action)) {
				dispose();
				System.exit(0);
			}
		}
	}

	private void openGameUrl() {
		try {
			URL url = new URL(Constants.GAME_URL);
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(url.toURI());
		} catch (URISyntaxException | IOException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}
}

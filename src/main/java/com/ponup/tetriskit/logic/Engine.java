package com.ponup.tetriskit.logic;

import com.ponup.api.Score;
import com.ponup.api.ScoreService;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import com.ponup.tetriskit.Config;
import com.ponup.tetriskit.Constants;
import com.ponup.tetriskit.canvas.BoardCanvas;
import com.ponup.tetriskit.canvas.NextPieceCanvas;
import com.ponup.tetriskit.panels.StatusPanel;
import com.ponup.tetriskit.resources.Sounds;
import com.ponup.tetriskit.resources.SoundLoader;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Most of the game logic is managed by this class.
 */
@SuppressWarnings("serial")
public class Engine extends JDialog implements Runnable, KeyListener {

	private final static Logger logger = Logger.getLogger(Engine.class.getName());

	private Thread thread;

	private BoardCanvas board;
	private NextPieceCanvas nextPieceCanvas;
	private StatusPanel statusPanel;

	private Piece fallingPiece;
	private Piece nextPiece;

	public GameState gameState;

	public Engine(final GameState gameState) {
		super();

		this.gameState = gameState;

		setTitle("Playing Tetris kit!");
		// setModal(true);
		setSize(gameState.getBoardWidth() * Constants.BLOCK_SIZE + 100, gameState.getBoardHeight()
			* Constants.BLOCK_SIZE + 40);

		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (gameState.getState().equals(GameStates.StateFinished)) {
					dispose();
					return;
				}
				gameState.setState(GameStates.StatePaused);
				int option = JOptionPane.showOptionDialog(Engine.this,
					"Are you sure you want to stop the game now?", "Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (option == JOptionPane.YES_OPTION) {
					gameState.setState(GameStates.StateStopped);
					dispose();
				} else {
					turnOn();
				}
			}
		});
		addKeyListener(this);

		board = new BoardCanvas(this);
		nextPieceCanvas = new NextPieceCanvas(gameState);
		statusPanel = new StatusPanel();

		gameState.setDropPiece(true);
		nextPiece = new Piece(this, PiecesUtil.randomPiece());

		organizeLayout();

		// pack();
		setLocationRelativeTo(null);
	}

	public BoardCanvas getBoard() {
		return board;
	}

	private void organizeLayout() {
		Container container = getContentPane();

		SpringLayout spring = new SpringLayout();

		spring.putConstraint(SpringLayout.WEST, board, 5, SpringLayout.WEST,
			container);
		spring.putConstraint(SpringLayout.NORTH, board, 5, SpringLayout.NORTH,
			container);

		spring.putConstraint(SpringLayout.WEST, nextPieceCanvas, 5,
			SpringLayout.EAST, board);
		spring.putConstraint(SpringLayout.NORTH, nextPieceCanvas, 5,
			SpringLayout.NORTH, container);

		spring.putConstraint(SpringLayout.WEST, statusPanel, 5,
			SpringLayout.EAST, board);
		spring.putConstraint(SpringLayout.NORTH, statusPanel, 5,
			SpringLayout.SOUTH, nextPieceCanvas);

		container.setLayout(spring);
		container.add(board);
		container.add(nextPieceCanvas);
		container.add(statusPanel);
	}

	public void turnOn() {
		gameState.setState(GameStates.StatePlaying);

		thread = new Thread(this);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setName(getClass().getName());
		thread.start();
	}

	@Override
	public void run() {
		try {
			while (gameState.getState().equals(GameStates.StatePlaying)) {
				processGameLoop();
				Thread.sleep(50);
			}
			if (gameState.getState().equals(GameStates.StateFinished)) {
				processGameOver();
			}
		} catch (InterruptedException | HeadlessException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private void updateStats() {
		gameState.setTime(gameState.getTime() + 1);
		if ((gameState.getTime() % 20) == 0) {
			gameState.setSeconds(gameState.getSeconds() + 1);
		}
		statusPanel.refresh(gameState);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:
				fallingPiece.drop();
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				fallingPiece.moveLeft();
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				fallingPiece.rotate();
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				fallingPiece.moveRight();
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				fallingPiece.moveDown();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	private Piece dropPiece() {
		SoundLoader.playSound(Sounds.LineCompleted);

		short[][] figure = PiecesUtil.randomPiece();
		Piece piece = new Piece(this, figure);
		return piece;
	}

	private void processGameLoop() {
		if (gameState.isDropPiece()) {

			fallingPiece = nextPiece;

			board.setFreePiece(fallingPiece);

			nextPiece = dropPiece();
			nextPieceCanvas.setPiece(nextPiece.getShape());

			gameState.setDropPiece(false);
			gameState.setPoints(gameState.getPoints()
				+ Config.getInstance().pointsPerPiece);
		}

		board.update();
		updateStats();
	}

	private void processGameOver() {
		SoundLoader.playSound(Sounds.GameOver);
		JOptionPane.showMessageDialog(this, "The game is over.", Constants.GAME_TITLE,
			JOptionPane.ERROR_MESSAGE);
		Score score = new Score();
		score.setGameName("tetriskit");
                score.setGameLevelNumber(gameState.getLevel());
		score.setPlayerName(gameState.getPlayerName());
		score.setValue(gameState.getPoints());

		ScoreService scoreService = new ScoreService();
		scoreService.add(score);
	}
}

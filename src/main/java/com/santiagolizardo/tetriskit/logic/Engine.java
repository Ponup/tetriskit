package com.santiagolizardo.tetriskit.logic;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import com.santiagolizardo.tetriskit.Config;
import com.santiagolizardo.tetriskit.Constants;
import com.santiagolizardo.tetriskit.canvas.BoardCanvas;
import com.santiagolizardo.tetriskit.canvas.NextPieceCanvas;
import com.santiagolizardo.tetriskit.panels.StatusPanel;
import com.santiagolizardo.tetriskit.resources.Sounds;
import com.santiagolizardo.tetriskit.resources.SoundLoader;
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

	public BoardCanvas board;
	private NextPieceCanvas nextPieceCanvas;
	private StatusPanel statusPanel;

	private Piece fallingPiece;
	private Piece nextPiece;

	public GameState gameState;

	public Engine(short boardWidth, short boardHeight) {
		super();

		setTitle("Playing Tetris kit!");
		// setModal(true);
		setSize(boardWidth * Constants.BLOCK_SIZE + 100, boardHeight
				* Constants.BLOCK_SIZE + 40);

		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		gameState = new GameState(boardWidth, boardHeight);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if( gameState.getState().equals(GameStates.StateFinished ) ) {
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
				if (gameState.isDropPiece()) {

					fallingPiece = nextPiece;

					board.setFreePiece(fallingPiece);

					nextPiece = PiecesUtil.dropPiece(this);
					nextPieceCanvas.setPiece(nextPiece.getShape());

					gameState.setDropPiece(false);
					gameState.setPoints(gameState.getPoints()
							+ Config.getInstance().pointsPerPiece);
				}
				Thread.sleep(50);

				board.update();
				updateStats();
			}
			if (gameState.getState().equals(GameStates.StateFinished)) {
				SoundLoader.playSound(Sounds.GameOver);
				JOptionPane.showMessageDialog(this, "Game over.", "Tetris",
						JOptionPane.ERROR_MESSAGE);
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
			fallingPiece.drop();
			break;
		case KeyEvent.VK_LEFT:
			fallingPiece.moveLeft();
			break;
		case KeyEvent.VK_UP:
			fallingPiece.rotate();
			break;
		case KeyEvent.VK_RIGHT:
			fallingPiece.moveRight();
			break;
		case KeyEvent.VK_DOWN:
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
}

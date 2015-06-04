package com.ponup.tetriskit.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ponup.tetriskit.Config;
import com.ponup.tetriskit.Constants;
import com.ponup.tetriskit.logic.Engine;
import com.ponup.tetriskit.logic.GameState;
import com.ponup.tetriskit.logic.Piece;
import com.ponup.tetriskit.logic.PiecesUtil;
import com.ponup.tetriskit.resources.Sounds;
import com.ponup.tetriskit.resources.SoundLoader;

/**
 * UI widget for the game board.
 */
@SuppressWarnings("serial")
public class BoardCanvas extends JPanel {

	private Engine engine;
	private GameState gameState;

	private Piece freePiece;
	private Image image;
	private Graphics buffer;

	private int width;
	private int height;

	public BoardCanvas(Engine engine) {
		super();

		this.engine = engine;
		this.gameState = engine.gameState;

		width = gameState.getBoardWidth() * Constants.BLOCK_SIZE;
		height = gameState.getBoardHeight() * Constants.BLOCK_SIZE;

		Dimension dimension = new Dimension(width, height);

		setMinimumSize(dimension);
		setPreferredSize(dimension);

		image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_INDEXED);
		buffer = image.getGraphics();
	}

	public void setFreePiece(Piece freePiece) {
		this.freePiece = freePiece;
	}

	public void update() {
		// Clear the buffer
		buffer.clearRect(0, 0, width, height);
		buffer.setColor(Config.getInstance().defaultBackgroundColor);
		buffer.fillRect(0, 0, width, height);
		// Draw the board
		PiecesUtil.drawPiece(buffer, 0, 0, GameState.getBoardArray());
		
		if( null != freePiece )
		{
			freePiece.draw(buffer);
			freePiece.moveDown();
			freePiece.moveDown();
		}

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	public synchronized void computeLines() {
		// Total blocks in one line
		int total = engine.gameState.getBoardWidth();

		for (int y = 0; y < gameState.getBoardHeight(); y++) {
			// Total used blocks in one line
			int usedBlocks = 0;
			for (int x = 0; x < total; x++) {
				if (GameState.getBoardArray()[y][x] == 1)
					usedBlocks++;
			}
			if (usedBlocks == total) { // If the line is fully occupied
				for (int a = y; a > 0; a--) {
					GameState.getBoardArray()[a] = GameState.getBoardArray()[a - 1]; // score!
				}
				// First line cleared
				GameState.getBoardArray()[0] = new short[engine.gameState
						.getBoardWidth()];

				gameState.setNumLines(gameState.getNumLines() + 1);
			}
		}

		if (gameState.getNumLines() > 0) {
			short scorePerLine = 0;
			switch (gameState.getNumLines()) {
			case 1:
				scorePerLine = 10;
				break;
			case 2:
				scorePerLine = 15;
				break;
			case 3:
				scorePerLine = 20;
				break;
			case 4:
				scorePerLine = 25;
				break;
			default:
				scorePerLine = 40;
			}
			gameState.setPoints(gameState.getNumLines() * scorePerLine);

			SoundLoader.playSound(Sounds.LineCompleted);
		}
	}

	public boolean detectCollision(short x, short y, short[][] figure) {
		// Left collision
		if (x < 0)
			return collisionDetected();
		// Right collision
		if (x + figure[0].length - 1 == engine.gameState.getBoardWidth())
			return collisionDetected();
		// Bottom collision
		if (y + figure.length - 1 == gameState.getBoardHeight())
			return collisionDetected();

		for (int h = 0; h < figure.length; h++) {
			for (int w = 0; w < figure[h].length; w++) {
				if (figure[h][w] == 1) {
					if (GameState.getBoardArray()[y + h][x + w] == 1) {
						return collisionDetected();
					}
				}
			}
		}

		return false;
	}

	public void mergePiece(Piece piece) {
		short[][] figure = piece.getShape();

		int x = piece.getX();
		int y = piece.getY() / Constants.BLOCK_SIZE;

		for (int yy = 0; yy < figure.length; yy++) {
			for (int xx = 0; xx < figure[yy].length; xx++) {
				if (figure[yy][xx] == 1) {
					GameState.getBoardArray()[y + yy][x + xx] = figure[yy][xx];
				}
			}
		}
	}

	private static boolean collisionDetected() {
		SoundLoader.playSound(Sounds.CollisionDetected);
		return true;
	}
}

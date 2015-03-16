package com.santiagolizardo.tetriskit.logic;

import java.awt.Graphics;

import com.santiagolizardo.tetriskit.Constants;
import com.santiagolizardo.tetriskit.canvas.BoardCanvas;

/**
 * This class represents a block falling or laying.
 */
public class Piece {

	private Engine engine;
	private BoardCanvas boardCanvas;

	private short x;
	private short y;

	private short[][] shape;

	public Piece(Engine engine, short[][] shape) {
		x = (short) (engine.gameState.getBoardWidth() / 2 - shape[0].length / 2);
		y = 0;
		this.shape = shape;

		this.engine = engine;
		this.boardCanvas = engine.getBoard();
	}

	public void setShape(short[][] shape) {
		this.shape = shape;
	}

	public short[][] getShape() {
		return shape;
	}

	public void moveLeft() {
		if (!boardCanvas.detectCollision((short) (x - 1),
				(short) (y / Constants.BLOCK_SIZE), shape)) {
			x--;
		}
	}

	public void moveRight() {
		if (!boardCanvas.detectCollision((short) (x + 1),
				(short) (y / Constants.BLOCK_SIZE), shape)) {
			x++;
		}
	}

	public void moveDown() {
		y++;
	}

	public void rotate() {
		short[][] temp = new short[shape[0].length][shape.length];
		int len = shape.length - 1;
		for (int yy = len; yy >= 0; yy--) {
			for (int xx = 0; xx < shape[yy].length; xx++) {
				temp[xx][yy] = shape[len - yy][xx];
			}
		}

		if (!boardCanvas.detectCollision(x, (short) (y / Constants.BLOCK_SIZE),
				temp)) {
			shape = temp;
		}
	}

	public void drop() {
		while (boardCanvas.detectCollision(x,
				(short) (y / Constants.BLOCK_SIZE + 1), shape) == false) {
			y++;
		}
		place();
	}

	public void draw(Graphics g) {
		PiecesUtil.drawPiece(g, x * Constants.BLOCK_SIZE, y, shape);

		short i = (short) ((y / Constants.BLOCK_SIZE) + 1);

		if (boardCanvas.detectCollision(x, i, shape)) {
			if (y < shape.length) {
				engine.gameState.setState(GameStates.StateFinished);
			} else {
				place();
			}
		}
	}

	private void place() {
		boardCanvas.mergePiece(this);
		boardCanvas.computeLines();
		engine.gameState.setDropPiece(true);
	}

	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}
}

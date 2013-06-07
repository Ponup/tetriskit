package com.santiagolizardo.tetriskit.logic;

import java.awt.Graphics;
import java.util.Random;

import com.santiagolizardo.tetriskit.Config;
import com.santiagolizardo.tetriskit.Constants;
import com.santiagolizardo.tetriskit.resources.Sounds;
import com.santiagolizardo.tetriskit.resources.SoundLoader;

/**
 * Utility methods for block handling.
 */
final public class PiecesUtil {

	private static Random random;

	static {
		random = new Random();
	}

	public static short[][] randomPiece() {
		int randomNumber = random.nextInt(Constants.PIECES.length);

		return Constants.PIECES[randomNumber];
	}

	public static Piece dropPiece(Engine engine) {
		short[][] figure = PiecesUtil.randomPiece();

		Piece piece = new Piece(engine, figure);

		SoundLoader.playSound(Sounds.LineCompleted);

		return piece;
	}

	public static void drawPiece(Graphics g, int x, int y, short[][] piece) {
		g.setColor(Config.getInstance().defaultPieceColor);
		for (int h = 0; h < piece.length; h++) {
			for (int w = 0; w < piece[h].length; w++) {
				if (piece[h][w] == 1) {
					g.fillRect(x + (w * Constants.BLOCK_SIZE) - 1, y
							+ (h * Constants.BLOCK_SIZE) - 1,
							Constants.BLOCK_SIZE - 1, Constants.BLOCK_SIZE - 1);
				}
			}
		}
	}
}

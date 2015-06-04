package com.ponup.tetriskit;

import java.awt.Color;

/**
 * Global variables.
 */
public class Config {

	private static Config singleton;

	public static Config getInstance() {
		if (singleton == null)
			singleton = new Config();
		return singleton;
	}

	private Config() {
	}

	public Color defaultBackgroundColor = Color.BLACK;
	public Color defaultPieceColor = Color.CYAN;

	public short defaultBoardWidth = 14;
	public short defaultBoardHeight = 18;

	public short pointsPerPiece = 1;
	public short pointsPerLine = 4;

	public short framesPerSecond = 50;
}

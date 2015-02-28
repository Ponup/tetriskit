package com.santiagolizardo.tetriskit;

/**
 * Application constants.
 */
public class Constants {

	public static final String GAME_TITLE = "TetrisKit";
	public static final String GAME_VERSION = "v1.2";
	public static final String GAME_URL = "https://github.com/Ponup/tetriskit";

	public final static short BLOCK_SIZE = 15;

	public final static short[][][] PIECES = new short[][][] {
			{ { 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1 } },
			{ { 1, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 },
					{ 0, 0, 1, 1, 1 } },
			{ { 1, 1, 1, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 1 } },
			{ { 1, 1, 0 }, { 0, 1, 1 } },
			{ { 0, 1, 1 }, { 1, 1, 0 } },
			{ { 1, 1 }, { 1, 1 } },
			{ { 0, 1 }, { 0, 1 }, { 1, 1 } },
			{ { 1, 1, 1 }, { 0, 1, 0 } },
			{ { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 }, { 1, 0, 1 } },
			{ { 1 }, { 1 }, { 1 }, { 1 } } };
}

package com.santiagolizardo.tetriskit.logic;

/**
 * This class remembers the status of a game in a certain time.
 */
public class GameState {

	private String playerName;
	
	private GameStates state;

	private int level;

	private int points;
	private int time;
	private int seconds;

	private boolean dropPiece;

	private short boardWidth;
	private short boardHeight;

	private static short[][] boardArray;

	private int numLines;

	public GameState(int boardWidth, int boardHeight) {
		this((short) boardWidth, (short) boardHeight);
	}

	public GameState(short boardWidth, short boardHeight) {
		state = GameStates.StateStopped;

		level = 1;
		points = 0;
		seconds = 0;

		dropPiece = true;

		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;

		boardArray = new short[boardHeight][boardWidth];

		numLines = 0;
	}

	public GameStates getState() {
		return state;
	}

	public void setState(GameStates state) {
		this.state = state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public boolean isDropPiece() {
		return dropPiece;
	}

	public void setDropPiece(boolean dropPiece) {
		this.dropPiece = dropPiece;
	}

	public short getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(short boardWidth) {
		this.boardWidth = boardWidth;
	}

	public short getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(short boardHeight) {
		this.boardHeight = boardHeight;
	}

	public static short[][] getBoardArray() {
		return boardArray;
	}

	public static void setBoardArray(short[][] boardArray) {
		GameState.boardArray = boardArray;
	}

	public int getNumLines() {
		return numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}

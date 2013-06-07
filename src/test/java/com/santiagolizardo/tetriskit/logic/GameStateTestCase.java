package com.santiagolizardo.tetriskit.logic;

import junit.framework.TestCase;

/**
 * Test case for the GameState class.
 */
public class GameStateTestCase extends TestCase {

	public void testDefaults() {
		GameState gameState = new GameState(40, 40);
		
		assertEquals(gameState.getState(), GameStates.StateStopped);
		assertEquals(gameState.getLevel(), 1);
		assertEquals(gameState.getPoints(), 0);
		assertEquals(gameState.getSeconds(), 0);
		
		assertEquals(gameState.getBoardWidth(), 40);
		assertEquals(gameState.getBoardHeight(), 40);
		
		assertEquals(gameState.getNumLines(), 0);
	}
}

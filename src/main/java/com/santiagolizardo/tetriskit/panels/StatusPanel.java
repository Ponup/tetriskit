package com.santiagolizardo.tetriskit.panels;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.santiagolizardo.tetriskit.logic.GameState;

/**
 * The UI panel where the score and time is displayed.
 */
public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel numPointsLabel;
	private JLabel numLinesLabel;
	private JLabel secondsLabel;

	public StatusPanel() {
		super();

		numPointsLabel = new JLabel("0");
		numLinesLabel = new JLabel("0");
		secondsLabel = new JLabel("0");

		Dimension dimension = new Dimension(80, 220);

		setMinimumSize(dimension);
		setPreferredSize(dimension);

		organizeElements();
	}

	private void organizeElements() {
		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		JLabel _numPoints = new JLabel("Points: ");
		JLabel _numLines = new JLabel("Lines: ");
		JLabel _seconds = new JLabel("Seconds: ");

		spring.putConstraint(WEST, _numPoints, 5, WEST, this);
		spring.putConstraint(WEST, numPointsLabel, 5, WEST, this);
		spring.putConstraint(WEST, _numLines, 5, WEST, this);
		spring.putConstraint(WEST, numLinesLabel, 5, WEST, this);
		spring.putConstraint(WEST, _seconds, 5, WEST, this);
		spring.putConstraint(WEST, secondsLabel, 5, WEST, this);

		spring.putConstraint(NORTH, _numPoints, 5, NORTH, this);
		spring.putConstraint(NORTH, numPointsLabel, 5, SOUTH, _numPoints);
		spring.putConstraint(NORTH, _numLines, 5, SOUTH, numPointsLabel);
		spring.putConstraint(NORTH, numLinesLabel, 5, SOUTH, _numLines);
		spring.putConstraint(NORTH, _seconds, 5, SOUTH, numLinesLabel);
		spring.putConstraint(NORTH, secondsLabel, 5, SOUTH, _seconds);

		add(_numPoints);
		add(numPointsLabel);
		add(_numLines);
		add(numLinesLabel);
		add(_seconds);
		add(secondsLabel);
	}

	public void refresh(GameState gameState) {
		numPointsLabel.setText(String.valueOf(gameState.getPoints()));
		numLinesLabel.setText(String.valueOf(gameState.getNumLines()));
		secondsLabel.setText(String.valueOf(gameState.getSeconds()));
	}
}

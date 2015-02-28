package com.santiagolizardo.tetriskit.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.santiagolizardo.tetriskit.logic.GameState;
import com.santiagolizardo.tetriskit.logic.PiecesUtil;

/**
 * Small UI widget for the next piece panel.
 */
@SuppressWarnings("serial")
public class NextPieceCanvas extends JPanel {

	private int width;
	private int height;

	private Image image;
	private Graphics buffer;

	public NextPieceCanvas(GameState gameState) {
		super();

		width = 80;
		height = 100;

		Dimension dimension = new Dimension(width, height);

		setMinimumSize(dimension);
		setPreferredSize(dimension);

		image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_INDEXED);
		buffer = image.getGraphics();
	}

	public void setPiece(short[][] piece) {
		buffer.clearRect(0, 0, width, height);

		PiecesUtil.drawPiece(buffer, 0, 0, piece);

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}
}

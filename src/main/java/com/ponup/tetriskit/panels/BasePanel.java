package com.ponup.tetriskit.panels;

import com.ponup.tetriskit.MainGUI;
import com.ponup.tetriskit.resources.ImageLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A UI panel with the shared properties.
 */
public class BasePanel extends JPanel {

	private static final long serialVersionUID = -5116657523675618264L;

	private Color bgColor;

	public BasePanel() {
		super();

		bgColor = new Color(0xFFFFAA);

		Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 8),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setBorder(border);
		setOpaque(true);
		setBackground(bgColor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 0, y = 0;

		int TILE_WIDTH = 256;
		int TILE_HEIGHT = 256;

		try {
			Image image = new ImageLoader().loadImage("tile.png");
			for (x = 0; x < getWidth(); x += TILE_WIDTH) {
				for (y = 0; y < getHeight(); y += TILE_HEIGHT) {
					g.drawImage(image, x, y, this);
				}
			}

		} catch (IOException ex) {
			Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}	
}

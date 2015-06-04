package com.ponup.tetriskit.resources;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class to load and cache resources.
 */
public class ImageLoader {

	public Icon load(String name) {
		URL url = getClass().getResource("images/" + name);
		return new ImageIcon(url);
	}

	public Image loadImage(String name) throws IOException {
		InputStream url = getClass().getResourceAsStream("images/" + name);
		return ImageIO.read(url);
	}
}

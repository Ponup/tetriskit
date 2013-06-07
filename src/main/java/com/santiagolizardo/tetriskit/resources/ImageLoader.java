package com.santiagolizardo.tetriskit.resources;

import java.net.URL;

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
}

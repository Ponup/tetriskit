package com.santiagolizardo.tetriskit.panels;

import java.awt.Color;

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
}

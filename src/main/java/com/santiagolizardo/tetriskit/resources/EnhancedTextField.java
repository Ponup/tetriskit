package com.santiagolizardo.tetriskit.resources;

import java.awt.Graphics;
import javax.swing.JTextField;

public class EnhancedTextField extends JTextField {

	private String placeholder;

	public EnhancedTextField(int columns) {
		super(columns);
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(getText().isEmpty() && placeholder != null) {
			g.drawString(placeholder, getInsets().left, getInsets().top + getFontMetrics(getFont()).getMaxAscent());
		}
	}
}

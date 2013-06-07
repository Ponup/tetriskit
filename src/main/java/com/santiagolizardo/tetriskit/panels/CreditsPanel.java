package com.santiagolizardo.tetriskit.panels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import com.santiagolizardo.tetriskit.resources.ImageLoader;

/**
 * The credits (author) panel.
 */
public class CreditsPanel extends BasePanel {

	private static final long serialVersionUID = 5859575552614447567L;
	
	private JLabel authorLabel;
	private JButton backButton;

	public CreditsPanel() {
		super();

		authorLabel = new JLabel(
				"Santiago Lizardo - http://www.santiagolizardo.com");

		backButton = new JButton("Back",
				new ImageLoader().load("arrow_left.png"));
		backButton.setActionCommand("SHOW_MAIN");

		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		spring.putConstraint(SpringLayout.EAST, backButton, -5,
				SpringLayout.EAST, this);
		spring.putConstraint(SpringLayout.SOUTH, backButton, -5,
				SpringLayout.SOUTH, this);

		add(authorLabel);
		add(backButton);
	}

	public void setActionListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}
}

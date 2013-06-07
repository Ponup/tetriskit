package com.santiagolizardo.tetriskit.panels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.santiagolizardo.tetriskit.resources.ImageLoader;

public class ScoresPanel extends BasePanel {

	private static final long serialVersionUID = -3462832110603557624L;

	private JButton backButton;

	public ScoresPanel() {
		super();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Player");
		model.addColumn("Score");

		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);

		backButton = new JButton("Back",
				new ImageLoader().load("arrow_left.png"));
		backButton.setActionCommand("SHOW_MAIN");

		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		spring.putConstraint(SpringLayout.EAST, backButton, -5,
				SpringLayout.EAST, this);
		spring.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST,
				this);
		spring.putConstraint(SpringLayout.EAST, scroll, -5, SpringLayout.EAST,
				this);

		spring.putConstraint(SpringLayout.SOUTH, backButton, -5,
				SpringLayout.SOUTH, this);
		spring.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.NORTH,
				this);
		spring.putConstraint(SpringLayout.SOUTH, scroll, -5,
				SpringLayout.NORTH, backButton);

		add(scroll);
		add(backButton);
	}

	public void setActionListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}
}

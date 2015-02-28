package com.santiagolizardo.tetriskit.panels;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;

import com.santiagolizardo.tetriskit.Config;
import com.santiagolizardo.tetriskit.resources.ImageLoader;

/**
 * Configuration UI panel.
 */
public class GameSetupPanel extends BasePanel implements MouseListener {

	private static final long serialVersionUID = 171553577623210131L;

	private JComboBox<Short> dropdownWidth;
	private JComboBox<Short> dropdownHeight;

	private JCheckBox cbEnableSounds;
	private JCheckBox cbEnableOddFigures;
	private JCheckBox cbEnableVisor;

	private JPanel ctrlBackground;
	private JPanel ctrlPieces;

	private JButton buttonBack;
	private JButton buttonStart;

	public GameSetupPanel() {
		initComponents();
		placeComponents();
	}

	private void initComponents() {
		Config config = Config.getInstance();

		Short[] widthArray = new Short[30];
		for (int i = 0; i < widthArray.length; i++) {
			widthArray[i] = (short) (14 + i);
		}
		Short[] heightArray = new Short[30];
		for (int i = 0; i < heightArray.length; i++) {
			heightArray[i] = (short) (14 + i);
		}

		dropdownWidth = new JComboBox<>(widthArray);
		dropdownWidth.setSelectedItem(config.defaultBoardWidth);
		dropdownHeight = new JComboBox<>(heightArray);
		dropdownHeight.setSelectedItem(config.defaultBoardHeight);

		cbEnableSounds = new JCheckBox("Enable sounds");
		cbEnableOddFigures = new JCheckBox("Enable odd figures");
		cbEnableVisor = new JCheckBox("Show the next-figure visor");

		ctrlBackground = new JPanel();
		ctrlBackground.addMouseListener(this);
		ctrlBackground.setSize(100, 15);
		ctrlBackground.setBackground(config.defaultBackgroundColor);
		ctrlPieces = new JPanel();
		ctrlPieces.addMouseListener(this);
		ctrlPieces.setSize(80, 15);
		ctrlPieces.setBackground(config.defaultPieceColor);

		buttonBack = new JButton("Back",
				new ImageLoader().load("arrow_left.png"));
		buttonBack.setActionCommand("SHOW_MAIN");

		buttonStart = new JButton("Start!");
	}

	private void placeComponents() {
		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		JLabel _ancho = new JLabel("Board width (in number of blocks)");
		JLabel _alto = new JLabel("Board height (in number of blocks)");

		JLabel _bgColor = new JLabel("Background color (board)");
		JLabel _fgColor = new JLabel("Foreground color (board)");

		spring.putConstraint(NORTH, cbEnableSounds, 0, NORTH, this);
		spring.putConstraint(NORTH, cbEnableOddFigures, 5, SOUTH,
				cbEnableSounds);
		spring.putConstraint(NORTH, cbEnableVisor, 5, SOUTH, cbEnableOddFigures);
		spring.putConstraint(NORTH, _ancho, 5, SOUTH, cbEnableVisor);
		spring.putConstraint(NORTH, dropdownWidth, 5, SOUTH, _ancho);
		spring.putConstraint(NORTH, _alto, 5, SOUTH, dropdownWidth);
		spring.putConstraint(NORTH, dropdownHeight, 5, SOUTH, _alto);
		spring.putConstraint(NORTH, _bgColor, 5, SOUTH, dropdownHeight);
		spring.putConstraint(NORTH, ctrlBackground, 5, SOUTH, _bgColor);
		spring.putConstraint(NORTH, _fgColor, 5, SOUTH, ctrlBackground);
		spring.putConstraint(NORTH, ctrlPieces, 5, SOUTH, _fgColor);

		spring.putConstraint(SOUTH, buttonBack, -5, SOUTH, this);
		spring.putConstraint(SOUTH, buttonStart, -5, SOUTH, this);

		spring.putConstraint(EAST, buttonStart, -5, EAST, this);
		spring.putConstraint(EAST, buttonBack, -5, WEST, buttonStart);

		Constraints cons = spring.getConstraints(this);
		cons.setWidth(spring.getConstraint(EAST, _ancho));
		cons.setHeight(Spring.sum(spring.getConstraint(SOUTH, ctrlPieces),
				Spring.constant(40)));

		add(cbEnableSounds);
		add(cbEnableOddFigures);
		add(cbEnableVisor);
		add(_ancho);
		add(dropdownWidth);
		add(_alto);
		add(dropdownHeight);

		add(_bgColor);
		add(ctrlBackground);
		add(_fgColor);
		add(ctrlPieces);

		add(buttonBack);
		add(buttonStart);
	}

	public JButton getButtonBack() {
		return buttonBack;
	}

	public void setButtonBack(JButton buttonBack) {
		this.buttonBack = buttonBack;
	}

	public JButton getButtonStart() {
		return buttonStart;
	}

	public void setButtonStart(JButton buttonStart) {
		this.buttonStart = buttonStart;
	}

	public short getBoardHeight() {
		return ((Short)dropdownHeight.getSelectedItem());
	}

	public short getBoardWidth() {
		return ((Short)dropdownWidth.getSelectedItem());
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Config config = Config.getInstance();

		Object source = event.getSource();
		if (source.equals(ctrlBackground)) {
			config.defaultBackgroundColor = JColorChooser
					.showDialog(this, "Select background color",
							config.defaultBackgroundColor);
			ctrlBackground.setBackground(config.defaultBackgroundColor);
		} else if (source.equals(ctrlPieces)) {
			config.defaultPieceColor = JColorChooser
					.showDialog(this, "Select foreground (pieces) color",
							config.defaultPieceColor);
			ctrlPieces.setBackground(config.defaultPieceColor);
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}
}

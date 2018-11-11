package com.nate.render_components.frames;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.nate.library.IconLib;
import com.nate.library.ImageFileException;
import com.nate.library.Settings;
import com.nate.render_components.frames.frame_components.Separator;

public class MapSize extends JDialog {
	private static final long serialVersionUID = -2660708556431746395L;
	
	public static void main(String[] args) {
		new MapSize(new JFrame(), 10,20);
	}

	public MapSize(JFrame main, int gridWidth, int gridHeight) {
		super(SwingUtilities.windowForComponent(main));
		setTitle("Map Size");
		setIconImage(IconLib.RESIZE.getImage());
		setModal(true);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		JPanel mapSize = new JPanel();
		mapSize.setLayout(new GridBagLayout());
		mapSize.setBorder(BorderFactory.createTitledBorder("Map Size: "));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(mapSize, c);
		
		
		
		JPanel anchor = new JPanel();
		anchor.setBorder(BorderFactory.createTitledBorder("Anchor: "));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(anchor, c);

		JPanel close = new JPanel();
		close.setBorder(BorderFactory.createTitledBorder(""));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(close, c);
		//add((new Separator("Map Size:", 360)).build());
		setFocusable(true);
		
		JLabel wlabel = new JLabel("    Width:");
		wlabel.setFont(Settings.FONT);
		JLabel hlabel = new JLabel("    Height:");
		hlabel.setFont(Settings.FONT);
		JLabel clabel1 = new JLabel("  cells");
		clabel1.setFont(Settings.FONT);
		JLabel clabel2 = new JLabel("  cells");
		clabel2.setFont(Settings.FONT);

		c.ipady = 30;
		c.ipadx = 40;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mapSize.add(wlabel, c);
		
		SpinnerModel spinnerWidthModel = new SpinnerNumberModel(gridWidth, 1, 60, 1);
		JSpinner widthSpinner = new JSpinner(spinnerWidthModel);
		widthSpinner.setFont(Settings.FONT);
		widthSpinner.setSize(30, 20);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 0;
		c.ipadx = 0;
		mapSize.add(widthSpinner, c);

		c.ipadx = 40;
		c.ipady = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		mapSize.add(clabel1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		mapSize.add(hlabel, c);
		
		SpinnerModel spinnerHeightModel = new SpinnerNumberModel(gridHeight, 1, 60, 1);
		JSpinner heightSpinner = new JSpinner(spinnerHeightModel);
		heightSpinner.setFont(Settings.FONT);
		heightSpinner.setSize(30, 20);
		c.ipadx = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 0;
		mapSize.add(heightSpinner, c);

		c.ipadx = 40;
		c.ipady = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		mapSize.add(clabel2, c);
		
		JButton set = new JButton("Set");
		set.setSize(getWidth()-20, getHeight()/3);
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMapSize((int) widthSpinner.getValue());
				//TODO updateWorldWindow();
				dispose();
			}
		});
		close.add(set);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					setMapSize((int) widthSpinner.getValue());
					//TODO updateWorldWindow();
					dispose();
				}
				else if (ke.getKeyCode() == KeyEvent.VK_UP && (int) widthSpinner.getValue() < 60) {
					widthSpinner.setValue((int)widthSpinner.getValue()+1);
				}
				else if (ke.getKeyCode() == KeyEvent.VK_DOWN && (int) widthSpinner.getValue() > 15) {
					widthSpinner.setValue((int)widthSpinner.getValue()-1);
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		if (this.getComponentCount() > 0) {
			int height = 0;
			for (Component comp : getComponents()) {
				height += comp.getPreferredSize().getHeight();
			}System.out.println(getComponents()[0].getPreferredSize().getWidth()+ " " + height);
			setSize(getComponents()[0].getWidth()+253, height+50);
		}
		
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(main.getGraphicsConfiguration());
		setLocation((scrSize.width - getWidth())/2, 
				(scrSize.height - toolHeight.bottom - getHeight())/2);

		setVisible(true);
	}

	public void setMapSize(int size) {
		//TODO
	}
}

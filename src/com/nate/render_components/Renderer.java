package com.nate.render_components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import com.nate.library.Block;
import com.nate.library.IconLib;
import com.nate.map.MapManager;

public class Renderer implements BlockGettable {
	
	private JLayeredPane pane;
	private Block selectedBlock;
	
	public Renderer(JFrame window, MapManager mapManager) {
		selectedBlock = Block.GRASS;
		
		pane = new JLayeredPane();
		pane.setSize(window.getWidth(), window.getHeight());
		pane.setLocation(0, 0);
		window.add(pane);
		
		MapComponent mc = new MapComponent(this, mapManager);
		pane.add(mc, 0);
		
		Selector selector = new Selector(this::setSelectedBlock);
		pane.add(selector, 0);
		
		ImageButton resize = new ImageButton(40, 40, IconLib.RESIZE.getImage());
		resize.setLocation(50, 50);
		resize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mc.resizeMap(40, 40);
			}
		});
		pane.add(resize, 0);
		
		ImageButton l = new ImageButton(40, 40, IconLib.PAN_LEFT.getImage());
		l.setLocation(100, 50);
		l.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mc.panMap(1, 0);
			}
		});
		pane.add(l, 0);
		
		ImageButton r = new ImageButton(40, 40, IconLib.PAN_RIGHT.getImage());
		r.setLocation(150, 50);
		r.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mc.panMap(-1, 0);
			}
		});
		pane.add(r, 0);
		
		ImageButton u = new ImageButton(40, 40, IconLib.PAN_UP.getImage());
		u.setLocation(200, 50);
		u.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mc.panMap(0, 1);
			}
		});
		pane.add(u, 0);
		
		ImageButton d = new ImageButton(40, 40, IconLib.PAN_DOWN.getImage());
		d.setLocation(250, 50);
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mc.panMap(0, -1);
			}
		});
		pane.add(d, 0);
		
		setMenuBar(window, mapManager);
	}
	
	public void setSelectedBlock(Block selectedBlock) {
		this.selectedBlock = selectedBlock;
	}
	
	public Block getSelectedBlock() {
		return selectedBlock;
	}
	
	
	private int gridSize = 15;
	public void setMenuBar(JFrame window, MapManager mapManager) {
		JMenuBar menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.ALT_MASK));
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);
		
		JMenuItem exportMenuItem = new JMenuItem("Export", KeyEvent.VK_S);
		exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapManager.export();
			}
		});
		fileMenu.add(exportMenuItem);
		
		JMenu mapMenu = new JMenu("Map");
		mapMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(mapMenu);
		
		JMenuItem Map_EditSize = new JMenuItem("Edit Map Size");
		Map_EditSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		Map_EditSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame sizeEdit = new JFrame();
				sizeEdit.setLayout(new FlowLayout());
				sizeEdit.setSize(200, 100);
				sizeEdit.setFocusable(true);
				
				JLabel label = new JLabel("Choose Map Size: ");
				sizeEdit.add(label);
				
				SpinnerModel spinnerTimeModel = new SpinnerNumberModel(gridSize, 15, 60, 1);
				JSpinner delaySpinner = new JSpinner(spinnerTimeModel);
				delaySpinner.setSize(30, 20);
				sizeEdit.add(delaySpinner);
				
				JButton set = new JButton("Set");
				set.setSize(sizeEdit.getWidth()-20, sizeEdit.getHeight()/3);
				set.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gridSize = (int) delaySpinner.getValue();
						//TODO updateWorldWindow();
						sizeEdit.dispose();
					}
				});
				sizeEdit.add(set);
				
				sizeEdit.addKeyListener(new KeyListener() {
					@Override
					public void keyPressed(KeyEvent ke) {
						if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
							gridSize = (int) delaySpinner.getValue();
							//TODO updateWorldWindow();
							sizeEdit.dispose();
						}
						else if (ke.getKeyCode() == KeyEvent.VK_UP && (int) delaySpinner.getValue() < 60) {
							delaySpinner.setValue((int)delaySpinner.getValue()+1);
						}
						else if (ke.getKeyCode() == KeyEvent.VK_DOWN && (int) delaySpinner.getValue() > 15) {
							delaySpinner.setValue((int)delaySpinner.getValue()-1);
						}
					}
					@Override
					public void keyReleased(KeyEvent arg0) {}
					@Override
					public void keyTyped(KeyEvent arg0) {}
				});
				sizeEdit.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
				Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(window.getGraphicsConfiguration());
				sizeEdit.setLocation((scrSize.width - sizeEdit.getWidth())/2, 
						(scrSize.height - toolHeight.bottom - sizeEdit.getHeight())/2);
				sizeEdit.setVisible(true);
			}
		});
		mapMenu.add(Map_EditSize);
		
		JMenuItem Map_Pokemon = new JMenuItem("Wild Pokemon");
		Map_Pokemon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		Map_Pokemon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO new WildPokemonEditor();
			}
		});
		mapMenu.add(Map_Pokemon);
		
		JMenu objectMenu = new JMenu("Objects");
		objectMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(objectMenu);
		
		JMenuItem imageMenuItem = new JMenuItem("Edit Images", KeyEvent.VK_E);
		imageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		imageMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO new ImageEditor();
			}
		});
		objectMenu.add(imageMenuItem);
	}
	
	
}

package com.nate.render_components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.nate.library.Block;
import com.nate.library.IconLib;
import com.nate.library.Settings;

public class Selector extends JPanel {
	private static final long serialVersionUID = 7327259306728786191L;
	private Consumer<Block> setSelectedBlock;
	private JPanel terrainPanel, buildingPanel, npcPanel, itemPanel;

	public Selector(Consumer<Block> setSelectedBlock) {
		this.setSelectedBlock = setSelectedBlock;
		setSize(Settings.SELECTOR_WIDTH, Settings.SELECTOR_HEIGHT);
		setLocation(Settings.WINDOW_WIDTH - Settings.SELECTOR_WIDTH, Settings.SELECTOR_Y);
		setLayout(null);//new GridLayout(4, 1));
		//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setSlides();
		setTabs();
	}
	
	private void setSlides() {
		terrainPanel = new JPanel();
		terrainPanel.setLayout(null);
		int size = Settings.SELECTOR_WIDTH / 3;
		
		SlideComponent grass = new SlideComponent(IconLib.SLIDER_GRASS, setSelectedBlock);
		grass.setTiles(new Tile(size, size, Block.GRASS),
					   new Tile(size, size, Block.GRASS_FOREST),
					   new Tile(size, size, Block.GRASS_PATH));
		grass.setLocation(0, 0);grass.setBackground(Color.blue);
		terrainPanel.add(grass);
		
		SlideComponent sand = new SlideComponent(IconLib.SLIDER_SAND, setSelectedBlock);
		sand.setLocation(0, Settings.SLIDE_HEADER_HEIGHT);
		terrainPanel.add(sand);sand.setBackground(Color.green);
		
		SlideComponent stone = new SlideComponent(IconLib.SLIDER_STONE, setSelectedBlock);
		stone.setTiles(new Tile(size, size, Block.GROUND_STONE),
				       new Tile(size, size, Block.GROUND_STONE_PATH),
				       new Tile(size, size, Block.GROUND_STONE_PANEL_LEFT),
				       new Tile(size, size, Block.GROUND_STONE_PANEL_RIGHT));
		stone.setLocation(0, Settings.SLIDE_HEADER_HEIGHT * 2);
		terrainPanel.add(stone);stone.setBackground(Color.red);
		
		terrainPanel.setSize(getWidth() - 20, getHeight());
		terrainPanel.setLocation(10, 10);
		add(terrainPanel);
		
		buildingPanel = new JPanel();
		buildingPanel.setLayout(null);
		buildingPanel.setSize(getWidth() - 20, getHeight());
		buildingPanel.setLocation(10, 10);
		add(buildingPanel);
		
		npcPanel = new JPanel();
		npcPanel.setLayout(null);
		SlideComponent npc = new SlideComponent(IconLib.SLIDER_NPC, setSelectedBlock);
		npc.setLocation(0, 0);
		npcPanel.add(npc);
		npcPanel.setSize(getWidth() - 20, getHeight());
		npcPanel.setLocation(10, 10);
		add(npcPanel);
		
		itemPanel = new JPanel();
		itemPanel.setLayout(null);
		itemPanel.setSize(getWidth() - 20, getHeight());
		itemPanel.setLocation(10, 10);
		add(itemPanel);
	}
	
	private void setTabs() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Terrain", createImageIcon(Block.GRASS), terrainPanel, "");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Buildings", createImageIcon(Block.VOID), buildingPanel, "");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.addTab("NPCs", createImageIcon(Block.PLAYER), npcPanel, "");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.addTab("Items", createImageIcon(Block.GROUND_STONE), itemPanel, "");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		tabbedPane.setBounds(0, 0, getWidth(), getHeight());
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tabbedPane);
	}
	
	protected ImageIcon createImageIcon(Block block) {
		ImageIcon icon = new ImageIcon();
		BufferedImage image = block.getImage();
		BufferedImage resizedImg = new BufferedImage(Settings.TAB_SIZE, Settings.TAB_SIZE, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(image, 0, 0, Settings.TAB_SIZE, Settings.TAB_SIZE, null);
	    g2.dispose();
		icon.setImage(resizedImg);
		return icon;
	}
}

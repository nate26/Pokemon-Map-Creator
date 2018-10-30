package com.nate.render_components;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.nate.library.Block;

public class Renderer implements ImageGettable {
	
	private JLayeredPane pane;
	
	public Renderer(JFrame window) {
		pane = new JLayeredPane();
		pane.setSize(window.getWidth(), window.getHeight());
		pane.setLocation(0, 0);
		window.add(pane);
		
		MapComponent mc = new MapComponent(this);
		mc.setLocation(50, 100);
		pane.add(mc, 0);
	}

	@Override
	public BufferedImage selectedImage() {
		return Block.GRASS_FOREST.getImage();
	}
	
	
}

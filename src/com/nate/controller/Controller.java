package com.nate.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.nate.library.Settings;
import com.nate.map.MapManager;
import com.nate.render_components.Renderer;

public class Controller {

	private JFrame window;
	
	public Controller() {
		window = new JFrame();
		window.setTitle("Pokemon Map Creator");
		window.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		//TODO set location under window tab
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(window.getGraphicsConfiguration());
		window.setLocation((scrSize.width - window.getWidth())/2, 
						   (scrSize.height - toolHeight.bottom - window.getHeight())/2);
		
		MapManager manager = new MapManager();	
		Renderer render = new Renderer(window, manager);	

		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}

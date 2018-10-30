package com.nate.controller;

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
		window.setVisible(true);
		
		Renderer render = new Renderer(window);
		MapManager manager = new MapManager();
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}

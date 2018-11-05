package com.nate.render_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.nate.library.Block;
import com.nate.library.IconLib;

public class Renderer implements BlockGettable {
	
	private JLayeredPane pane;
	
	public Renderer(JFrame window) {
		pane = new JLayeredPane();
		pane.setSize(window.getWidth(), window.getHeight());
		pane.setLocation(0, 0);
		window.add(pane);
		
		MapComponent mc = new MapComponent(this);
		mc.setLocation(50, 100);
		pane.add(mc, 0);
		
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
	}

	@Override
	public Block selectedBlock() {
		return Block.GRASS_FOREST;
	}
	
	
}

package com.nate.render_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.swing.JPanel;

import com.nate.library.Block;
import com.nate.library.IconLib;
import com.nate.library.Settings;

public class SlideComponent extends JPanel {
	private static final long serialVersionUID = 3216382966324172095L;
	private ImageButton header;
	private List<Tile> tileList;
	private Consumer<Block> selectedBlock;
	private boolean open = false;

	public SlideComponent(IconLib icon, Consumer<Block> selectedBlock) {
		this.selectedBlock = selectedBlock;
		setSize(Settings.SLIDE_WIDTH, Settings.SLIDE_HEADER_HEIGHT);
		setLayout(null);
		setHeader(icon.getImage());
		tileList = new ArrayList<Tile>();
	}
	
	private void setHeader(BufferedImage icon) {
		header = new ImageButton(getWidth(), getHeight(), icon);
		header.setLocation(0, 0);
		header.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!open) {
					animateOpen();
					tileList.forEach(t -> add(t));
				}
				else {
					animateClose();
				}
			}
		});
		add(header);
	}

	public void setTiles(Tile tile, Tile ...tiles) {
		int size = getWidth()/4-20;
		tileList.add(tile);
		tileList.addAll(Arrays.asList(tiles));
		
		int x = 0;
		for (Tile t : tileList) {
			t.setBounds(10 + x, Settings.SLIDE_HEADER_HEIGHT + 10, size, size);
			t.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedBlock.accept(t.getBlock());
				}
			});
			x = x + size + 20;
		}
	}
	
	private void animateOpen() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			private int grow = 1;
			@Override
			public void run() {
				if (getHeight() >= 400) {//Settings.SLIDE_HEIGHT) {
					timer.cancel();
				}
				if (grow < 10) {
					grow++;
				}
				setSize(getWidth(), getHeight() + grow);
				repaint();
			}
		}, 0, 10);
	}
	
	private void animateClose() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			private int grow = 1;
			@Override
			public void run() {
				if (getHeight() + grow <= Settings.SLIDE_HEADER_HEIGHT) {
					timer.cancel();
					setSize(getWidth(), Settings.SLIDE_HEADER_HEIGHT);
					removeAll();
					add(header);
					repaint();
				}
				if (grow < 10) {
					grow++;
				}
				setSize(getWidth(), getHeight() - grow);
				repaint();
			}
		}, 0, 10);
	}
}

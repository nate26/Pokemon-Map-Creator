package com.nate.render_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import com.nate.library.Block;
import com.nate.library.IconLib;
import com.nate.library.Settings;
import com.nate.render_components.frames.frame_components.ImageButton;

public class SlideComponent extends JPanel {
	private static final long serialVersionUID = 3216382966324172095L;
	private Selector parent;
	private int id;
	private IconLib title;
	private ImageButton header;
	private List<Tile> tileList;
	private Consumer<Block> selectedBlock;
	private boolean open = false;
	private boolean isAnimating = false;

	public SlideComponent(Selector parent, int id, IconLib title, Consumer<Block> selectedBlock) {
		this.parent = parent;
		this.id = id;
		this.title = title;
		this.selectedBlock = selectedBlock;
		setSize(Settings.SLIDE_WIDTH, Settings.SLIDE_HEADER_HEIGHT);
		setLayout(null);
		setHeader(title.getImage());
		tileList = new ArrayList<Tile>();
	}
	
	private void setHeader(BufferedImage icon) {
		header = new ImageButton(getWidth(), getHeight(), icon);
		header.setLocation(0, 0);
		header.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isAnimating) {
					if (!open) {
						animateOpen();
					}
					else {
						animateClose();
					}
					open = !open;
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
		tileList.forEach(t -> add(t));
	}
	
	public int getId() {
		return id;
	}
	
	public IconLib getTitle() {
		return title;
	}
	
	public void setAnimationAs(boolean on) {
		isAnimating = on;
	}
	
	private void animateOpen() {
		parent.animateOpen(this);
	}
	
	private void animateClose() {
		parent.animateClose(this);
	}
}

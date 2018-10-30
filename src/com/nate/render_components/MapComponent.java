package com.nate.render_components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.nate.library.Settings;
import com.nate.map.MapService;

public class MapComponent extends JPanel {
	private static final long serialVersionUID = -5389083716951042237L;
	private ImageGettable imageGetter;
	private Tile[][] tiles;
	
	public MapComponent(ImageGettable imageGetter) {
		this.imageGetter = imageGetter;
		setBackground(Color.RED);//setOpaque(false);
		setSize(Settings.DEFAULT_MAP_WIDTH, Settings.DEFAULT_MAP_HEIGHT);
		setTiles(Settings.DEFAULT_MAP_TILES_WIDE, Settings.DEFAULT_MAP_TILES_HIGH);
	}
	
	public MapComponent(int width, int height, int tileN, int tileM, ImageGettable imageGetter) {
		this.imageGetter = imageGetter;
		setBackground(Color.RED);//setOpaque(false);
		setSize(width, height);
		setTiles(tileN, tileM);
	}
	
	public void setTiles(int nx, int ny) {
		tiles = new Tile[nx][ny];
		int w = getWidth() / nx, 
			h = getHeight() / ny;
		for (int i = 0; i < nx; i++) {
			for (int j = 0; j < ny; j++) {
				final int tempI = i;
				final int tempJ = j;
				tiles[i][j] = new Tile(w, h);
				tiles[i][j].setLocation(i * w, j * h);
				tiles[i][j].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseClicked(MouseEvent e) {}

					@Override
					public void mousePressed(MouseEvent e) {
						setNextImage(tiles[tempI][tempJ]);System.out.println(tempI + " " + tempJ);
					}

					@Override
					public void mouseReleased(MouseEvent e) {}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						//setNextImage(tiles[tempI][tempJ]);
						System.out.println("slide" + tempI + " " + tempJ);
					}

					@Override
					public void mouseExited(MouseEvent e) {}
					
				});
			}
		}
	}
	

	
	private void setNextImage(Tile tile) {
		MapService service = new MapService();
		//TODO service with tile id?
		tile.setImage(imageGetter.selectedImage());
	}
}

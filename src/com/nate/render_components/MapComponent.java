package com.nate.render_components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.nate.library.Block;
import com.nate.library.Settings;
import com.nate.map.MapManager;

public class MapComponent extends JPanel {
	private static final long serialVersionUID = -5389083716951042237L;
	private BlockGettable imageGetter;
	private MapManager mapManager;
	private Tile[][] tiles;
	private boolean mouseDown = false;
	
	public MapComponent(BlockGettable imageGetter, MapManager mapManager) {
		this.imageGetter = imageGetter;
		this.mapManager = mapManager;
		setLayout(null);
		setSize(Settings.DEFAULT_MAP_WIDTH, Settings.DEFAULT_MAP_HEIGHT);
		setLocation(Settings.MAP_X, Settings.MAP_Y);
		setTiles(Settings.DEFAULT_MAP_TILES_WIDE, Settings.DEFAULT_MAP_TILES_HIGH);
	}
	
	public MapComponent(int width, int height, int tileN, int tileM, BlockGettable imageGetter) {
		this.imageGetter = imageGetter;
		mapManager = new MapManager();
		setLayout(null);
		setSize(width, height);
		setTiles(tileN, tileM);
	}
	
	public void resizeMap(int nx, int ny) {
		Tile[][] oldTiles = tiles;
		removeAll();
		setTiles(nx, ny);
		mapManager.resize(nx, ny);
		for (int i = 0; i < oldTiles.length; i++) {
			for (int j = 0; j < oldTiles[i].length; j++) {
				tiles[i][j].setBlock(oldTiles[i][j].getBlock());
				mapManager.setTile(oldTiles[i][j].getBlock(), i, j);
			}
		}
		repaint();
	}
	
	public void panMap(int displacementX, int displacementY) {
		Block[][] oldTiles = copy(tiles);
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (i + displacementX < 0 || i + displacementX >= tiles.length ||
						j + displacementY < 0 || j + displacementY >= tiles[i].length) {
					tiles[i][j].setBlock(Settings.DEFAULT_MAP_BLOCK);
					mapManager.setTile(Settings.DEFAULT_MAP_BLOCK, i, j);
				}
				else {
					tiles[i][j].setBlock(oldTiles[i + displacementX][j + displacementY]);
					mapManager.setTile(oldTiles[i + displacementX][j + displacementY], i, j);
				}
			}
		}
		repaint();
	}

	
	public void printAll(Block[][] blocks) {
		System.out.println("");
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (j == blocks[i].length - 1) {
					System.out.println(blocks[i][j]);
				}
				else {
					System.out.print(blocks[i][j] + " ");
				}
			}
		}
	}
	
	public Block[][] copy(Tile[][] original) {
		Block[][] copy = new Block[original.length][original[0].length];
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[i].length; j++) {
				copy[i][j] = original[i][j].getBlock();
			}
		}
		return copy;
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
						setNextImage(tiles[tempI][tempJ], tempI, tempJ);
						mouseDown = true;
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						mouseDown = false;
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						if (mouseDown) {
							setNextImage(tiles[tempI][tempJ], tempI, tempJ);
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {}
					
				});
				add(tiles[i][j]);
			}
		}
	}
		
	private void setNextImage(Tile tile, int i, int j) {
		tile.setBlock(imageGetter.getSelectedBlock());
		//tile.setBorderPainted(false);
		tile.repaint();
		mapManager.setTile(imageGetter.getSelectedBlock(), i, j);
	}
}

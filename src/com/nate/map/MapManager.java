package com.nate.map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONWriter;

import com.nate.library.Block;
import com.nate.library.ImageFileException;
import com.nate.library.Settings;

public class MapManager {

	private MapGrid map;
	private int nx, ny;
	
	public MapManager() {
		nx = Settings.DEFAULT_MAP_TILES_WIDE;
		ny = Settings.DEFAULT_MAP_TILES_HIGH;
		map = new MapGrid();
	}
	
	public void setTile(Block block, int i, int j) {
		nx = i;
		ny = j;
		map.setBlock(block, i, j);
	}
	
	public void resize(int i, int j) {
		map = new MapGrid(i, j, Settings.DEFAULT_MAP_BLOCK);
	}
	
	public int getCellsWide() {
		return nx;
	}
	
	public int getCellsTall() {
		return ny;
	}

	public void export() {
		//TODO export pokemon spawn in new export function
		/*StringBuilder sb = new StringBuilder();
		JSONWriter w = new JSONWriter(sb);
		w.key("map grid");*/
		
		BufferedImage full = new BufferedImage(nx * Settings.DEFAULT_TILE_SIZE, ny * Settings.DEFAULT_TILE_SIZE, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = full.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				BufferedImage img = getScaledImage(map.getBlock(i, j).getImage(), Settings.DEFAULT_TILE_SIZE, Settings.DEFAULT_TILE_SIZE);
				g2.drawImage(img, i * Settings.DEFAULT_TILE_SIZE, j * Settings.DEFAULT_TILE_SIZE, Settings.DEFAULT_TILE_SIZE, Settings.DEFAULT_TILE_SIZE, null);
			}
		}
	    g2.dispose();
	    
	    try {
	        File outputfile = new File("mymap.png");//TODO replace with chosen name
	        ImageIO.write(full, "png", outputfile);
	    } catch (IOException e) {
	    	throw new ImageFileException("Map image could not be exported.");
	    }
	}
	
	/**
	 * Scales the tile image to fit its width and height.
	 * 
	 * @param srcImg - the original image
	 * @param w - width of the tile
	 * @param h - height of the tile
	 * @return the scaled tile image
	 */
	private BufferedImage getScaledImage(Image srcImg, int w, int h) {
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}

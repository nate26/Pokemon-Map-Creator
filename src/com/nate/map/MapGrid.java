package com.nate.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nate.library.Block;
import com.nate.library.Settings;

/**
 * List object for type Block. Represented as a matrix grid of blocks.
 * 
 * @author Nate
 */
public class MapGrid extends ArrayList<Block> {
	private static final long serialVersionUID = 7655505237168230703L;
	private int width;
	private int height;
	
	/**
	 * Sets default width, height and block by Settings.
	 */
	public MapGrid() {
		width = Settings.DEFAULT_MAP_TILES_WIDE;
		height = Settings.DEFAULT_MAP_TILES_HIGH;
		populate(Settings.DEFAULT_MAP_BLOCK);
	}
	
	/**
	 * Create map grid with chosen width, height and default block type.
	 * 
	 * @param width in blocks
	 * @param height in blocks
	 * @param block - default block
	 */
	public MapGrid(int width, int height, Block block) {
		this.width = width;
		this.height = height;
		populate(block);
	}
	
	/**
	 * Gets the block on the [i,j] matrix position.
	 * 
	 * @param i position
	 * @param j position
	 * @return block at that position
	 */
	public Block getBlock(int i, int j) {
		return get(i * width + j);
	}
	
	/**
	 * Sets the block on the [i,j] matrix position.
	 * 
	 * @param block type
	 * @param i position
	 * @param j position
	 */
	public void setBlock(Block block, int i, int j) {
		add(i * width + j, block);
	}
	
	/**
	 * Gets the frequency of a certain block on the map.
	 * 
	 * @param block specified
	 * @return this block's frequency on the map
	 */
	public double getFrequency(Block block) {
		return getFrequencies().get(block);
	}
	
	/**
	 * Gets the frequencies of all the blocks on the map.
	 * 
	 * @return all the block frequencies on the map as a HashMap object
	 */
	public Map<Block, Double> getFrequencies() {
		Map<Block, Double> frequencies = new HashMap<Block, Double>();
		forEach(b -> frequencies.put(b, frequencies.get(b) + 1 / size()));
		return frequencies;
	}
	
	/**
	 * Gets the width of the map.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * Gets the height of the map.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Populates all the blocks in the map with the specified block.
	 * 
	 * @param block to populate
	 */
	private void populate(Block block) {
		if (size() > 0) removeRange(0, size());		
		for (int i = 0; i < width * height; i++) {
			add(block);
		}
	}

}

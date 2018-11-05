package com.nate.map;

import com.nate.library.Block;
import com.nate.library.Settings;

public class MapManager {

	private MapGrid map;
	
	
	public MapManager() {
		map = new MapGrid();
	}
	
	public void setTile(Block block, int i, int j) {
		map.setBlock(block, i, j);
	}
	
	public void resize(int i, int j) {
		map = new MapGrid(i, j, Settings.DEFAULT_MAP_BLOCK);
	}
}

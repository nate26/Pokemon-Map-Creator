package com.nate.library;

import java.awt.Font;

/**
 * Global settings for the application.
 * 
 * @author Nate
 */
public class Settings {
	
	public static final int WINDOW_WIDTH = 1800;
	public static final int WINDOW_HEIGHT = 1200;
	public static final int DEFAULT_TILE_SIZE = 16;

	public static final int DEFAULT_MAP_TILES_WIDE = 30;
	public static final int DEFAULT_MAP_TILES_HIGH = 30;
	public static final int DEFAULT_MAP_WIDTH = DEFAULT_MAP_TILES_WIDE * 30;
	public static final int DEFAULT_MAP_HEIGHT = DEFAULT_MAP_TILES_HIGH * 30;

	public static final int SELECTOR_Y = 100;
	public static final int SELECTOR_WIDTH = 400;
	public static final int SELECTOR_HEIGHT = WINDOW_HEIGHT - SELECTOR_Y;
	public static final int SLIDE_WIDTH = SELECTOR_WIDTH - 20;
	public static final int SLIDE_HEIGHT = 1000;
	public static final int SLIDE_HEADER_HEIGHT = (int) (SLIDE_WIDTH / 3.9);
	public static final int SLIDE_Y = 10;
	public static final int TAB_SIZE = 16;

	public static final Block DEFAULT_MAP_BLOCK = Block.VOID;
	public static final String POKEMON_JSON_PATH = "/data/pokemon.json";
	public static final int MAP_X = 50;
	public static final int MAP_Y = 100;
	
	public static final Font FONT = new Font("Calibri", Font.PLAIN, 16);
	
}

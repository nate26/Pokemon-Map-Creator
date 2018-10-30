package com.nate.library;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An Enum Library of all the image blocks for a map.
 * 
 * @author Nate
 */
public enum Block {
	
	// GRASS
	GRASS("/grass/grass.png", Interaction.NONE),
	GRASS_FOREST("/grass/grass_forest.png", Interaction.NONE),
	GRASS_SAND_PATCH("/grass/grass_sand_patch.png", Interaction.NONE),
	
	GRASS_POND_EDGE_TOP("/grass/grass_pond_edge_top.png", Interaction.NONE),
	GRASS_POND_EDGE_TOP_TREE_LEFT("/grass/grass_pond_edge_top_tree_left.png", Interaction.NONE),
	GRASS_POND_EDGE_TOP_TREE_RIGHT("/grass/grass_pond_edge_top_tree_right.png", Interaction.NONE),
	
	GRASS_PATH("/grass/path/grass_path.png", Interaction.NONE),
	
	GRASS_PATH_LEFT("/grass/path/grass_path_left.png", Interaction.NONE),
	GRASS_PATH_RIGHT("/grass/path/grass_path_right.png", Interaction.NONE),
	
	GRASS_PATH_TOP("/grass/path/grass_path_top.png", Interaction.NONE),
	GRASS_PATH_TOP_LEFT("/grass/path/grass_path_top_left.png", Interaction.NONE),
	GRASS_PATH_TOP_RIGHT("/grass/path/grass_path_top_right.png", Interaction.NONE),
	
	GRASS_PATH_BOTTOM("/grass/path/grass_path_bottom.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_LEFT("/grass/path/grass_path_bottom_left.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_RIGHT("/grass/path/grass_path_bottom_right.png", Interaction.NONE),
	
	GRASS_PATH_BOTTOM_TREE_LEFT("/grass/path/grass_path_bottom_tree_left.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_TREE_RIGHT("/grass/path/grass_path_bottom_tree_right.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_LEFT_TREE_LEFT("/grass/path/grass_path_bottom_left_tree_left.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_LEFT_TREE_RIGHT("/grass/path/grass_path_bottom_left_tree_right.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_RIGHT_TREE_LEFT("/grass/path/grass_path_bottom_right_tree_left.png", Interaction.NONE),
	GRASS_PATH_BOTTOM_RIGHT_TREE_RIGHT("/grass/path/grass_path_bottom_right_tree_right.png", Interaction.NONE),
		
	
	// PERSON
	PLAYER("/person/player.png", Interaction.NONE), 
	
	
	// SAND
	SAND("/sand/sand.png", Interaction.NONE),
	SAND_GRASS_LEFT("/sand/sand_grass_left.png", Interaction.NONE),
	SAND_GRASS_RIGHT("/sand/sand_grass_right.png", Interaction.NONE),
	SAND_GRASS_TOP("/sand/sand_grass_top.png", Interaction.NONE),
	SAND_GRASS_TOP_LEFT("/sand/sand_grass_top_left.png", Interaction.NONE),
	SAND_GRASS_TOP_RIGHT("/sand/sand_grass_top_right.png", Interaction.NONE),
	SAND_GRASS_BOTTOM("/sand/sand_grass_bottom.png", Interaction.NONE),
	SAND_GRASS_BOTTOM_LEFT("/sand/sand_grass_bottom_left.png", Interaction.NONE),
	SAND_GRASS_BOTTOM_RIGHT("/sand/sand_grass_bottom_right.png", Interaction.NONE),
	
	
	// STONE_PATH
	GROUND_STONE("/stone_path/ground_stone.png", Interaction.NONE),
	GROUND_STONE_PATH("/stone_path/ground_stone_path.png", Interaction.NONE),
	GROUND_STONE_PANEL_LEFT("/stone_path/ground_stone_panel_left.png", Interaction.NONE),
	GROUND_STONE_PANEL_RIGHT("/stone_path/ground_stone_panel_right.png", Interaction.NONE),
	GROUND_STONE_PANEL_GRASS_LEFT("/stone_path/ground_stone_panel_grass_left.png", Interaction.NONE),
	GROUND_STONE_PANEL_GRASS_RIGHT("/stone_path/ground_stone_panel_grass_right.png", Interaction.NONE),
	
	
	VOID("/void.png", Interaction.WALL);
	
	/*BERRYSOIL("/", Interaction.WALL), 
	CAVE("/", Interaction.NONE), 
	DESERT("/", Interaction.NONE), 
	DOOR("/", Interaction.PORTAL),
	FLOWER("/", Interaction.NONE), 
	OCEAN("/", Interaction.WATER),
	PERSON("/", Interaction.PERSON), 
	POKEBALL("/", Interaction.BALL), 
	POND("/", Interaction.WATER), 
	PUDDLE("/", Interaction.PUDDLE), 
	RIDGE("/", Interaction.RIDGE), 
	ROCK_SMASH("/", Interaction.ROCK_SMASH), 
	ROCK_STRENGTH("/", Interaction.ROCK_STRENGTH), 
	SIGN("/", Interaction.SIGN), 
	SIGNBARRIER("/", Interaction.PERSON), 
	TALLGRASS("/", Interaction.TALLGRASS), 
	TREE("/", Interaction.WALL),
	TREE_CUT("/", Interaction.TREE_CUT), 
	WATERFALL("/", Interaction.WATERFALL);*/
	
	private final BufferedImage image;
	private final Interaction interaction;
	
	/**
	 * Sets the block image and interaction type.
	 * 
	 * @param path to the image
	 * @param interaction type
	 */
	private Block(String path, Interaction interaction) {
		String relativePath = checkPath(path);
		try {
			image = ImageIO.read(new File(relativePath));
		} catch (IOException e) {
			throw new ImageFileException("Absolute path \"" + path + "\" for \"" 
					+ this.toString() + "\" did not find an image.");
		}
		this.interaction = interaction;
	}
	
	/**
	 * Gets the block image.
	 * 
	 * @return image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Gets the block interaction.
	 * 
	 * @return interaction type
	 */
	public Interaction getInteraction() {
		return interaction;
	}
	
	/**
	 * Checks if the path for the image exists and gets the relative path 
	 * to the resource folder.
	 * 
	 * @param path to the image
	 * @return the relative path
	 */
	private String checkPath(String path) {
		if (path == null) {
			throw new ImageFileException("path not set");
		}
		
		String relativePath;
		try {
			relativePath = Block.class.getResource(path).getPath();
		} catch (NullPointerException e) {
			throw new ImageFileException("Relative path from the resource folder \"" + path 
					+ "\" for \"" + this.toString() + "\" did not find an image.");
		}
		return relativePath;
	}
}

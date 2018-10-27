package com.nate.library;

import java.awt.image.BufferedImage;

public enum Blocks {
	
	BERRYSOIL("/", Interaction.WALL), 
	CAVE("/", Interaction.NONE), 
	DESERT("/", Interaction.NONE), 
	DOOR("/", Interaction.PORTAL),
	FLOWER("/", Interaction.NONE), 
	OCEAN("/", Interaction.WATER),
	PERSON("/", Interaction.PERSON), 
	PLAYER("/", Interaction.NONE), 
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
	WATERFALL("/", Interaction.WATERFALL);
	
	private final BufferedImage image;
	
	private Blocks(String path, Interaction interaction) {
		image = setImage(path);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	private BufferedImage setImage(String path) {
		//TODO implement
		
		return null;
	}
}

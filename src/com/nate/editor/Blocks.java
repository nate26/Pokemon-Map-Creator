package com.nate.editor;

import java.awt.image.BufferedImage;

public enum Blocks {
	
	BERRYSOIL("/", Interaction.WALL), 
	CAVE("/", Interaction.NONE), 
	DESERT("/", Interaction.NONE), 
	DOOR("/", Interaction.PORTAL),
	FLOWER("/", Interaction.WALL), 
	PERSON("/", Interaction.WALL), 
	PLAYER("/", Interaction.WALL), 
	POKEBALL("/", Interaction.WALL), 
	POND("/", Interaction.WALL), 
	PUDDLE("/", Interaction.WALL), 
	RIDGE("/", Interaction.WALL), 
	ROCKSMASH_ROCK("/", Interaction.WALL), 
	SIGN("/", Interaction.WALL), 
	SIGNBARRIER("/", Interaction.WALL), 
	STANDARD("/", Interaction.WALL), 
	STRENGTH_BOULDER("/", Interaction.WALL), 
	TALLGRASS("/", Interaction.WALL), 
	TREE("/", Interaction.WALL),
	TREE_CUT("/", Interaction.TREE_CUT), 
	TRAINERVIEW("/", Interaction.WALL), 
	WATER("/", Interaction.WALL), 
	WATERFALL("/", Interaction.WALL);
	
	private Blocks(String path, Interaction interaction) {
		
	}
}

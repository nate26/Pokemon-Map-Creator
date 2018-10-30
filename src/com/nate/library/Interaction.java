package com.nate.library;

public enum Interaction {
	
	NONE("Can pass"),
	WALL("Cannot pass"),
	WATER("Must use Surf to pass"),
	TREE_CUT("can pass after interaction, must use Cut to interact"),
	ROCK_SMASH("can pass after interaction, must use Rock Smash to interact"),
	ROCK_STRENGTH("can pass after interaction, must use Strength to interact"),
	WATERFALL("can pass after interaction, must use Waterfall to interact"),
	PORTAL("Interacting opens a new map"),
	PERSON("Cannot pass, can interact to open dialogue"), 
	BALL("can pass after interaction, interacting gives item"), 
	PUDDLE("Interacting starts puddle animation"), 
	RIDGE("Can only pass through from the elevated side"), 
	SIGN("Cannot pass, can interact to open dialogue"), 
	TALLGRASS("Can pass while walking, cannot pass on bike");

	private final String description;
	
	private Interaction(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}

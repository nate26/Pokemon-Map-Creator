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
public enum IconLib {
	
	// GRASS
	RESIZE("/icons/resize.png"),
	PAN_LEFT("/icons/panLeft.png"),
	PAN_RIGHT("/icons/panRight.png"),
	PAN_UP("/icons/panUp.png"),
	PAN_DOWN("/icons/panDown.png");
	
	private final BufferedImage image;
	
	/**
	 * Sets the icon image.
	 * 
	 * @param path to the image
	 */
	private IconLib(String path) {
		String relativePath = checkPath(path);
		try {
			image = ImageIO.read(new File(relativePath));
		} catch (IOException e) {
			throw new ImageFileException("Absolute path \"" + path + "\" for \"" 
					+ this.toString() + "\" did not find an image.");
		}
	}
	
	/**
	 * Gets the icon image.
	 * 
	 * @return image
	 */
	public BufferedImage getImage() {
		return image;
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

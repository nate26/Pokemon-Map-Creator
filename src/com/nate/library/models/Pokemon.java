package com.nate.library.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.nate.library.Block;
import com.nate.library.ImageFileException;

public class Pokemon {
	
	private String id;
	private String name;
	private String type1;
	private String type2;
	private BufferedImage icon;

	public Pokemon(String id, String name, String type1, String type2, String icon) {
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		
		String relativePath = checkPath(icon);
		try {
			this.icon = ImageIO.read(new File(relativePath));
		} catch (IOException e) {
			throw new ImageFileException("Absolute path \"" + icon + "\" for \"" 
					+ this.toString() + "\" did not find an image.");
		}
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

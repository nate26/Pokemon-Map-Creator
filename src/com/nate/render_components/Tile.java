package com.nate.render_components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import com.nate.library.Settings;
import com.nate.map.MapService;

/**
 * Each block on the map.  One Tile holds one block image.
 * Tiles can be painted over.
 * 
 * @author Nate
 */
public class Tile extends JButton {
	
	private static final long serialVersionUID = -4631982310313355027L;
	private BufferedImage image;
	
	/**
	 * Sets tile to default image with given width and height.
	 * 
	 * @param width for the tile
	 * @param height for the tile
	 */
	public Tile(int width, int height) {
		image = getScaledImage(Settings.DEFAULT_MAP_BLOCK.getImage(), width, height);
		setSize(width, height);
	}

	/**
	 * Sets tile to the given image with given width and height.
	 * 
	 * @param width for the tile
	 * @param height for the tile
	 * @param image for the tile
	 */
	public Tile(int width, int height, BufferedImage image) {
		this.image = getScaledImage(image, width, height);
		setSize(width, height);
	}
	
	/**
	 * Sets the image of the tile. Updates the image automatically.
	 * 
	 * @param image for the tile
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Paints the tile image onto itself.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Scales the tile image to fit its width and height.
	 * 
	 * @param srcImg - the original image
	 * @param w - width of the tile
	 * @param h - height of the tile
	 * @return the scaled tile image
	 */
	private BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}

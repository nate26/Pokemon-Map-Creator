package com.nate.render_components.frames.frame_components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class ImageButton extends JButton {
	private static final long serialVersionUID = -4631982310313355027L;
	private BufferedImage image;

	/**
	 * Sets button to the given image with given width and height.
	 * 
	 * @param width for the tile
	 * @param height for the tile
	 * @param image for the tile
	 */
	public ImageButton(int width, int height, BufferedImage image) {
		this.image = image;
		setSize(width, height);
	}
	
	/**
	 * Gets the image of the button.
	 * 
	 * @return image of the button
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Sets the image of the button. Updates the image automatically.
	 * 
	 * @param image for the button
	 */
	public void setImage(BufferedImage image) {
		this.image = getScaledImage(image, getWidth(), getHeight());
	}
	
	/**
	 * Resizes this component so that it has width and height. 
	 * 
	 * Resizes the containing image.
	 * 
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy.
	 * 
	 * @param width the new width of this component in pixels
	 * @param height the new height of this component in pixels
	 */
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		image = getScaledImage(image, width, height);
	}

	
	/**
	 * Moves this component to a new location. The top-left corner of
	 * the new location is specified by the x and y parameters in the 
	 * coordinate space of this component's parent. 
	 * 
	 * Resizes this component so that it has width and height. 
	 * 
	 * Resizes the containing image.
	 * 
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy.
	 * 
	 * @param x the x-coordinate of the new location's top-left corner in the parent's coordinate space
	 * @param y the y-coordinate of the new location's top-left corner in the parent's coordinate space
	 * @param width the new width of this component in pixels
	 * @param height the new height of this component in pixels
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		image = getScaledImage(image, width, height);
	}

	/**
	 * Moves and resizes this component to conform to the new bounding rectangle r. 
	 * This component's new position is specified by r.x and r.y, and its new size 
	 * is specified by r.width and r.height 
	 * 
	 * Resizes the containing image.
	 * 
	 * This method changes layout-related information, and therefore,
	 * invalidates the component hierarchy.
	 * 
	 * @param rect the new bounding rectangle for this component
	 */
	@Override
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		image = getScaledImage(image, rect.width, rect.height);
	}
	
	/**
	 * Paints the icon image onto itself.
	 * 
	 * @param g graphics object to project
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Scales the icon image to fit its width and height.
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

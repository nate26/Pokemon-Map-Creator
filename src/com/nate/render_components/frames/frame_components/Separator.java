package com.nate.render_components.frames.frame_components;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nate.library.IconLib;
import com.nate.library.ImageFileException;
import com.nate.library.Settings;

public class Separator {
	
	private JPanel separator;
	private JLabel label;
	private ImagePanel line;
	private BufferedImage lineImage;
	
	private int length;
	
	public Separator(int length) {
		separator = new JPanel();
		this.length = length;
	}
	
	public Separator(String text, int length) {
		separator = new JPanel();
		addLabel(text);
		addLine(length);
	}
	
	public void addLabel(String text) {
		label = new JLabel(text);
		label.setFont(Settings.FONT);
		label.setLocation(0, 0);
		FontMetrics fm = label.getFontMetrics(label.getFont());
		int height = fm.getHeight();
		int width = fm.stringWidth(label.getText());
		label.setSize(width, height);
		separator.add(label);System.out.println(label.getWidth());
	}
	
	public void addLine(int length) {
		int x = 0;
		int width = length - 10;
		if (label != null) {
			x = label.getWidth();
			width -= label.getWidth();
		}
		lineImage = IconLib.SEPARATOR_LINE.getImage();
		BufferedImage fullLine = drawLine(width);
		line = new ImagePanel(fullLine);
		line.setLocation(x + 5, 0);
		separator.add(line);
	}
	
	public JPanel build() {
		separator.setLayout(null);
		if (separator.getComponentCount() == 0) {
			return separator;
		}
		separator.setSize(length, separator.getComponents()[0].getHeight());
		return separator;
	}
	
	/**
	 * Draws the line image to fit its length.
	 * 
	 * @param length - width of line
	 * @return the drawn line
	 */
	private BufferedImage drawLine(int length){
	    BufferedImage fullLine = new BufferedImage(length, lineImage.getHeight(), BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = fullLine.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    for (int i = 0; i < Math.floor(length / (float) lineImage.getWidth()); i++) {
	    	g2.drawImage(lineImage, i * lineImage.getWidth(), 0, null);
	    }
	    g2.dispose();
	    return fullLine;
	}
	
}

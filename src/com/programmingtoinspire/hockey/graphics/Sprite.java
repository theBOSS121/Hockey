package com.programmingtoinspire.hockey.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprite {
	
	public int width, height;
	public int[] pixels;

	public static Sprite player = new Sprite("/player.png");
	public static Sprite player2 = new Sprite("/player2.png");
	public static Sprite pag = new Sprite("/pag.png");
	
	public Sprite(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = image.getRGB(0, 0, width, height, null, 0, width);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

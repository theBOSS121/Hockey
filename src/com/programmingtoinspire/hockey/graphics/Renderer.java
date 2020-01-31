package com.programmingtoinspire.hockey.graphics;

import com.programmingtoinspire.hockey.Hockey;

public class Renderer {
	public static int width = Hockey.WIDTH, height = Hockey.HEIGHT;
	public static int[] pixels = new int[width * height];
	
	public static void renderBackground() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = 0x037777;
			}
			
		}
	}
	
	public static void renderSprite(Sprite s, int xp, int yp) {
		if(xp < -s.width || yp < -s.height || xp >= width || yp >= height) return;
		
		for(int y = 0; y < s.height; y++) {
			int yy = y + yp;
			if(yy >= height || yy < 0) continue;
			for(int x = 0; x < s.width; x++) {
				int xx = x + xp;
				if(xx < 0 || x >= width) continue;
				int col = combineColors(s.pixels[x + y * s.width], xx, yy);
				pixels[xx + yy * width] = col;
			}
		}
	}

	private static int combineColors(int col, int x, int y) {
		int pCol = pixels[x + y * width];
		int a = (col >> 24) & 0xff;
		if(a == 0xff) return col;
		if(a <= 0) return pCol;
		
		int pr = (pCol >> 16) & 0xff;
		int pg = (pCol >> 8) & 0xff;
		int pb = (pCol) & 0xff;
		int r = (col >> 16) & 0xff;
		int g = (col >> 8) & 0xff;
		int b = (col) & 0xff;
		
		int nr = (int) (pr - ((pr - r) * (a / 255f)));
		int ng = (int) (pg - ((pg - g) * (a / 255f)));
		int nb = (int) (pb - ((pb - b) * (a / 255f)));
		
		return (nr << 16) | (ng << 8) | nb;
	}
}

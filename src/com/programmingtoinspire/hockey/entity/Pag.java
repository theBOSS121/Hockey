package com.programmingtoinspire.hockey.entity;

import java.util.Random;

import com.programmingtoinspire.hockey.Hockey;
import com.programmingtoinspire.hockey.graphics.Renderer;
import com.programmingtoinspire.hockey.graphics.Sprite;

public class Pag  extends Entity{
	
	public Random rand = new Random();
	
	public Pag(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		mass = 1.0;
	}	
	
	public void update() {
		x += velX;
		y += velY;
		if(x < 0) {
			x = 0;
			velX *= -1;
		}
		if(x + width > Hockey.WIDTH) {
			x = Hockey.WIDTH - width;
			velX *= -1;
		}
		velX *= 0.992;
		velY *= 0.992;
	}

	public void render() {
		Renderer.renderSprite(sprite, (int) x, (int) y);
	}	
}

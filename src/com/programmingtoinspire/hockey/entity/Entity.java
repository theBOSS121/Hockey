package com.programmingtoinspire.hockey.entity;

import com.programmingtoinspire.hockey.graphics.Sprite;

public class Entity {

	public double x, y;
	public int width, height;
	public Sprite sprite;
	public int radius;
	public double mass = 1.0, velX, velY;
	
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		width = sprite.width;
		height = sprite.height;
		radius = width / 2;
	}
	
	public void update() {}
	public void render() {}
}

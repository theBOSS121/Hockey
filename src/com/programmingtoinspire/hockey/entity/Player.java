package com.programmingtoinspire.hockey.entity;

import java.util.Random;

import com.programmingtoinspire.hockey.Hockey;
import com.programmingtoinspire.hockey.graphics.Renderer;
import com.programmingtoinspire.hockey.graphics.Sprite;
import com.programmingtoinspire.hockey.input.Keyboard;

public class Player extends Entity{

	public double maxVel = 6.0;
	public double acceleration = 0.3;
	
	private int left, right, up, down;
	
	public Random rand = new Random();
	
	public Player(double x, double y, Sprite sprite, int left, int right, int up, int down) {
		super(x, y, sprite);
		mass = 1.0;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}	
	
	public void update() {
		movingLogic();
	}
	
	private void movingLogic() {
		if(Keyboard.key(left) && !Keyboard.key(right)) {
			if(velX >= -maxVel) {
				velX -= acceleration;
			}
		}
		if(Keyboard.key(right) && (!Keyboard.key(left))) {
			if(velX <= maxVel) {
				velX += acceleration;
			}
		}
		if((Keyboard.key(left) && Keyboard.key(right)) ||
		   (!Keyboard.key(left) && !Keyboard.key(right))) {
			if(Math.abs(velX) < 0.1) {
				velX = 0;
			}
			if(velX > 0.0) {
				velX -= 0.05;
			}else if(velX < 0.0) {
				velX += 0.05;
			}
		}
		if(Keyboard.key(up) && !Keyboard.key(down)) {
			if(velY >= -maxVel) {
				velY -= acceleration;
			}
		}
		if(Keyboard.key(down) && !Keyboard.key(up)) {
			if(velY <= maxVel) {
				velY += acceleration;
			}
		}
		if((Keyboard.key(up) && Keyboard.key(down)) ||
	      (!Keyboard.key(up) && !Keyboard.key(down))) {
			if(Math.abs(velY) < 0.1) {
				velY = 0;
			}
			if(velY > 0.0) {
				velY -= 0.05;
			}else if(velY < 0.0) {
				velY += 0.05;
			}
		}
		
		x += velX;
		y += velY;
		
		if(x < 0) {
			x = 0;
			velX *= -0.5;
		}
		if(x + width > Hockey.WIDTH) {
			x = Hockey.WIDTH - width;
			velX *= -0.5;
		}
		if(y < 0) {
			y = 0;
			velY *= -0.5;
		}
		if(y + height > Hockey.HEIGHT) {
			y = Hockey.HEIGHT - height;
			velY *= -0.5;
		}
	}
	

	public void render() {
		Renderer.renderSprite(sprite, (int) x, (int) y);
	}
	
}

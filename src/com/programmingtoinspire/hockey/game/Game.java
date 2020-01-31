package com.programmingtoinspire.hockey.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.programmingtoinspire.hockey.Hockey;
import com.programmingtoinspire.hockey.entity.Entity;
import com.programmingtoinspire.hockey.entity.Pag;
import com.programmingtoinspire.hockey.entity.Player;
import com.programmingtoinspire.hockey.graphics.Renderer;
import com.programmingtoinspire.hockey.graphics.Sprite;

public class Game {	
	public static Player player;
	public static Player player2;
	public static Pag pag;

	public static int scorep1 = 0;
	public static int scorep2 = 0;

	public Game() {
		init();
	}
	
	public void init() {
		player = new Player(Hockey.WIDTH / 2 - Sprite.player.width / 2, Hockey.HEIGHT / 2 - Sprite.player.height / 2 + 250, Sprite.player2, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		player2 = new Player(Hockey.WIDTH / 2 - Sprite.player.width / 2, Hockey.HEIGHT / 2 - Sprite.player.height / 2 - 250, Sprite.player, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
		pag = new Pag(Hockey.WIDTH / 2 - Sprite.player.width / 2, Hockey.HEIGHT / 2 - Sprite.player.height / 2, Sprite.pag);
	}
	
	public void update() {
		player.update();
		player2.update();
		pag.update();
		
		if(pag.y + pag.height < 0) {
			scorep1++;				
			init();
		}
		if(pag.y > Hockey.HEIGHT) {
			scorep2++;
			init();			
		}
		collision();	
	}
	
	private void collision() {
		double xDiff, yDiff;
		double distance;
		
		xDiff = player2.x - player.x;
		yDiff = player2.y - player.y;
		distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		if(distance < player2.radius + player.radius) {
			resolveCollision(player, player2);
		}		
		xDiff = player2.x - pag.x;
		yDiff = player2.y - pag.y;
		distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		if(distance < player2.radius + pag.radius) {
			resolveCollision(pag, player2);
		}		
		xDiff = pag.x - player.x;
		yDiff = pag.y - player.y;
		distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		if(distance < pag.radius + player.radius) {
			resolveCollision(player, pag);
		}			
	}
	
	public double rotate(double vx, double vy, double angle, boolean which) { 
		double velX = vx;
		double velY = vy;
		
		velX = vx * Math.cos(angle) - vy * Math.sin(angle);
		velY = vx * Math.sin(angle) + vy * Math.cos(angle);
		
		if(which) {
			return velX;	
		}else {
			return velY;
		}
		
	}
	
	public void resolveCollision(Entity e1, Entity e2) {
		double xVelocityDiff = e1.velX - e2.velX;
		double yVelocityDiff = e1.velY - e2.velY;

		double xDist = e2.x - e1.x;
		double yDist = e2.y - e1.y;
		
		if(xVelocityDiff * xDist + yVelocityDiff * yDist >= 0) {
			double angle = -Math.atan2(yDist, xDist);
			double m1 = e1.mass;
			double m2 = e2.mass;
			
			double ux1 = rotate(e1.velX, e1.velY, angle, true);
			double ux2 = rotate(e2.velX, e2.velY, angle, true);
			double uy1 = rotate(e1.velX, e1.velY, angle, false);
			double uy2 = rotate(e2.velX, e2.velY, angle, false);
			
			double vx1 = (ux1 * (m1 - m2) + 2 * ux2 * m2 )/ (m1 + m2);
			double vx2 = (ux2 * (m2 - m1) + 2 * ux1 * m1 )/ (m1 + m2);
			double vy1 = uy1;
			double vy2 = uy2;
			
			double vxFinal1 = rotate(vx1, vy1, -angle, true);
			double vxFinal2 = rotate(vx2, vy2, -angle, true);
			double vyFinal1 = rotate(vx1, vy1, -angle, false);
			double vyFinal2 = rotate(vx2, vy2, -angle, false);
			
			e1.velX = vxFinal1;
			e1.velY = vyFinal1;
			e2.velX = vxFinal2;
			e2.velY = vyFinal2;	
		}		
	}

	public void render() {
		Renderer.renderBackground();
		player.render();
		player2.render();
		pag.render();		
		for(int i = 0; i < Renderer.pixels.length; i++) {
			Hockey.pixels[i] = Renderer.pixels[i];
		}
	}
	
	public void renderText(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(0xff193441, true));
		g.setFont(new Font("Arial", 1, 50));
		String p1 = "" + Game.scorep1;
		int p1w = g.getFontMetrics().stringWidth(p1) / 2;
		String p2 = "" + Game.scorep2;
		int p2w = g.getFontMetrics().stringWidth(p2) / 2;
		g.drawString(p1, Hockey.WIDTH * Hockey.scale / 9 - p1w, 80);
		g.drawString(p2, Hockey.WIDTH * Hockey.scale / 9 * 8 - p2w, 80);
	}	
}

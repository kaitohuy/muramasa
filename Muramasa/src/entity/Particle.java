package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity{

	Entity generator;
	Color color;
	int xd;
	int yd;
	int size;
	
	public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.generator = generator;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxLife = maxLife;
		this.xd = xd;
		this.yd = yd;
		
		life = maxLife;
		int offset = (gp.tileSize/2) - (size/2);
		worldX = generator.worldX + offset;
		worldY = generator.worldY + offset;
		
	}

	public void update() {
		
		life--;
		
		if(life < maxLife/3) {
			yd++;
		}
		
		worldX += xd*speed;
		worldY += yd*speed;
	
		if(life == 0) {
			alive = false;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int tempScreenX = getScreenX();
		int tempScreenY = getScreenY();

		if(gp.player.screenX > gp.player.worldX) {
			tempScreenX = worldX;
		}
		if(gp.player.screenY > gp.player.worldY) {
			tempScreenY = worldY;
		}
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if(rightOffset > gp.worldWidth - gp.player.worldX) {
			tempScreenX = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if(bottomOffset > gp.worldHeight - gp.player.worldY) {
			tempScreenY = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		g2.setColor(color);
		g2.fillRect(tempScreenX, tempScreenY, size, size);
		
	}
}

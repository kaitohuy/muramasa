package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Map extends TileManager{

	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;
	
	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		createWorldMap();
	}
	
	public void setSizeMap() {
		if(gp.currentMap == 0 || gp.currentMap == 5) {
			gp.maxWorldCol = 20;
			gp.maxWorldRow = 12;
			
		}
		if(gp.currentMap != 0 && gp.currentMap != 5) {
			gp.maxWorldCol = 50;
			gp.maxWorldRow = 50;
		}
		
		gp.worldWidth = gp.tileSize * gp.maxWorldCol;
		gp.worldHeight = gp.tileSize * gp.maxWorldRow;
	}

	public void createWorldMap() {

		worldMap = new BufferedImage[gp.maxMap];
		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		
		for(int i = 0; i < gp.maxMap; i++) {
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize* col;
				int y = gp.tileSize * row;
				if(this.tile[tileNum] != null) {
					g2.drawImage(tile[tileNum].image, x, y, null);
				}
				
				col++;
				if(col == gp.maxWorldCol) {
					col  = 0;
					row++;
				}
			}
			g2.dispose();
		}
	}
	
	public void drawFullMapScreen(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//draw map
		int width = 500;
		int height = 500;
		int x = gp.screenWidth/2 - width/2;
		int y = gp.screenHeight/2 - height/2;
		g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
		
		//draw player
		double scale = (double)(gp.tileSize* gp.maxWorldCol)/width;
		int playerX = (int)(x + gp.player.worldX/scale);
		int playerY = (int)(y + gp.player.worldY/scale);
		int playerSize = (int)(gp.tileSize/scale);
		g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
		
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			Entity e = gp.obj[gp.currentMap][i];			
			if(e != null) {
				int objX = (int)(x + e.worldX/scale);
				int objY = (int)(y + e.worldY/scale);
				int objWidth = (int)(e.getWidth()/4);
				int objHeight = (int)(e.getHeight()/4);
				
				g2.drawImage(e.image, objX, objY, objWidth, objHeight, null);
			}
		}
		
		//hint
		g2.setFont(gp.ui.maruMonica.deriveFont(32f));
		g2.setColor(Color.white);
		g2.drawString("Press ESC to close", 750, 550);
		
	}
	
	public void drawMiniMap(Graphics2D g2) {
		if(miniMapOn == true) {
			//draw map
			int width, height;
			if(gp.currentMap == 0) {
				width = gp.maxWorldCol * gp.tileSize / 6;
				height = gp.maxWorldRow * gp.tileSize / 6;
			} else {
				width = 150;
				height = 150;
			}
			int x = gp.screenWidth - width - 25;
			int y = 25;
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  0.8f));
			g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

			//draw player
			double scale = (double)(gp.tileSize* gp.maxWorldCol)/width;
			int playerX = (int)(x + gp.player.worldX/scale);
			int playerY = (int)(y + gp.player.worldY/scale);
			int playerSize = (int)(gp.tileSize/4);
			g2.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
			
			for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
				Entity e = gp.obj[gp.currentMap][i];			
				if(e != null) {
					int objX = (int)(x + e.worldX/scale);
					int objY = (int)(y + e.worldY/scale);
					int objWidth = (int)(e.getWidth()/10);
					int objHeight = (int)(e.getHeight()/10);
					
					g2.drawImage(e.image, objX, objY, objWidth, objHeight, null);
				}
			}
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  0.8f));
		}
	}
}

package main;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.Player;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y+ entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		//use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if(entity.knockBack == true) {
			direction = entity.knockBackDirection;
		}
		
		if(entityLeftCol >= 0 && entityLeftCol < 50 &&
				entityRightCol >= 0 && entityRightCol < 50 &&
				entityTopRow >= 0 && entityTopRow < 50 &&
				entityBottomRow >= 0 && entityBottomRow < 50) {
			
			switch(direction) {
			case "up":
				entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
				if(entity.worldY < -16) {
					entity.collisionOn = true;
				}
				else if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow && entityRightCol >= 0 && entityRightCol < gp.maxWorldCol) {
					tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
					tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
					if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
						entity.collisionOn = true;
					}
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
				if(entityBottomRow >= gp.maxWorldRow) {
					entity.collisionOn = true;
				}
				else if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow && entityRightCol >= 0 && entityRightCol < gp.maxWorldCol) {
					tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
					tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
					if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
						entity.collisionOn = true;
					}
				}
				break;
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
				if(entity.worldX < -16) {
					entity.collisionOn = true;
				}
				else if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
					tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
					tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
					if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
						entity.collisionOn = true;
					}
				}
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
				if(entityRightCol >= gp.maxWorldCol) {
					entity.collisionOn = true;
				}
				else if(entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
					tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
					tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
					if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
						entity.collisionOn = true;
					}
				}
				break;
			
			}
		}	
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		//use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if(entity.knockBack == true) {
			direction = entity.knockBackDirection;
		}
		
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			if(gp.obj[gp.currentMap][i] != null) {
				//get entity's solidArea position
				entity.solidArea.x = entity.worldX + entity.solidArea.x	;
				entity.solidArea.y = entity.worldY + entity.solidArea.y	;
				
				//get object's solidArea position 
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
			
				switch(direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public List<Integer> checkEntity(Entity entity, Entity[][] target) {
		
		List<Integer> index = new ArrayList<>();
		
		//use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if(entity.knockBack == true) {
			direction = entity.knockBackDirection;
		}
		
		for(int i = 0; i < target[gp.currentMap].length; i++) {
			if(target[gp.currentMap][i] != null) {
				//get entity's solidArea position
				entity.solidArea.x = entity.worldX + entity.solidArea.x	;
				entity.solidArea.y = entity.worldY + entity.solidArea.y	;
				
				//get object's solidArea position 
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
				
				switch(direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					if(target[gp.currentMap][i] != entity) {
						
						if(entity == gp.player && target[gp.currentMap][i].name.equals("ishigami") && target[gp.currentMap][i].inRage == true) {
							if(target[gp.currentMap][i].attacking == false) {
								entity.collisionOn = true;
								index.add(i);
							}
						}else {
							entity.collisionOn = true;
							index.add(i);
						}
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x	;
		entity.solidArea.y = entity.worldY + entity.solidArea.y	;
		
		//get object's solidArea position 
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
	
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}

}
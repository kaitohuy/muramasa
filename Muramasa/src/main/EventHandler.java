package main;

import data.Progress;
import entity.Entity;
import entity.NPC_Griffon;
import entity.Projectile;
import object.OBJ_Spike_Arrow;
import object.OBJ_Spike_Rock;

public class EventHandler{

	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	Projectile projectile;
	
	int previousEventX, previousEventY;
	public boolean canTouchEvent = true;
	public boolean trapRock = false;
	public boolean trapSpikeArrow = false;
	int tempMap;
	public int nextMap;
	int tempCol;
	int tempRow;
	public boolean endDialogueMap1 = false, endDialogueMap2 = false, endDialogueMap3 = false, endDialogueMap35 = false, endDialogueMap4 = false, endDialogueMap45 = false, endGriffon = false;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventMaster = new Entity(gp);
		instantiate();
	}
	
	public void instantiate() {
		eventRect = new EventRect[gp.maxMap][50][50];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < 50 && row < 50) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == 50) {
				col = 0;
				row++;
				
				if(row == 50) {
					row = 0;
					map++;
				}
			}
		}
		
		setDialogue();
	}
	
	public void setDialogue() {
		
		eventMaster.dialogues[0][0] = "you fall into a pit";
		eventMaster.dialogues[1][0] = "You drink the water.\nYour life and mana has been recovered.\n"
				+ "The process has been saved";
		
	}
	
	public void checkEvent() {
		
		//check if the player character is more than 1 tile away from last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			
			if(gp.afterSummon == false && gp.gameState == gp.playState) {
				gp.gameState = gp.summonState;
			}
			
			if(gp.afterSummon == true && gp.currentMap == 0) {
				drawDialogueSummon();
			}
			
			if(gp.currentMap == 1 && endDialogueMap1 == false) {
				endDialogueMap1 = true;
				speak(gp.npc[1][0]);
			}
			if(gp.currentMap == 2 && endDialogueMap2 == false) {
				endDialogueMap2 = true;
				speak(gp.npc[2][0]);
			}
			if(gp.currentMap == 3 && endDialogueMap3 == false) {
				endDialogueMap3 = true;
				speak(gp.npc[3][0]);
			}
			if(gp.dragonBattleOn == true && endDialogueMap35 == false) {
				speak(gp.npc[3][0]);
				endDialogueMap35 = true;
			}
			if(gp.currentMap == 4 && endDialogueMap4 == false) {
				endDialogueMap4 = true;
				speak(gp.npc[4][2]);
			}
			if(gp.ishigamiBattleOn == true && endGriffon == false) {
				speak(gp.npc[4][2]);
				endGriffon = true;
			}
			if(gp.player.worldY >= gp.tileSize * 10 && gp.player.worldX >= gp.tileSize * 8 && gp.player.worldX <= gp.tileSize * 11 && gp.currentMap == 0) {
				teleport(1, 3, 32, gp.outside, "right");
			}
			
			if(gp.player.worldY > gp.tileSize*48 && gp.player.worldX >= gp.tileSize * 24 && gp.player.worldX <= gp.tileSize * 25 && gp.currentMap == 2) {
				teleport(1, 36, 5, gp.outside, "down");
			}
			
			if(gp.player.worldY > gp.tileSize*48 && gp.player.worldX >= gp.tileSize * 23 && gp.player.worldX <= gp.tileSize * 27 && gp.currentMap == 3) {
				teleport(2, 9, 5, gp.outside, "down");
			}
			
			if(gp.player.worldX >= gp.tileSize*19 && gp.player.worldX <= gp.tileSize*20 && gp.player.worldY >= gp.tileSize*27 && gp.player.worldY <= gp.tileSize*31 && gp.currentMap == 4) {
				if(trapRock == false) {
					trapRock();
					trapRock = true;
				}
				else {
					if(projectile.alive == false) {
						trapRock = false;
					}
				}
			}
			
			if(gp.player.worldX >= gp.tileSize * 27 && gp.player.worldX <= gp.tileSize*28 && gp.player.worldY >= gp.tileSize*27 && gp.player.worldY <= gp.tileSize*31 && gp.currentMap == 4) {
				if(trapSpikeArrow == false) {
					trapSpikeArrow();
					trapSpikeArrow = true;
				}
				else {
					if(projectile.alive == false) {
						trapSpikeArrow = false;
					}
				}
			}
			
			if(endDialogueMap45 == false && gp.player.worldY >= gp.tileSize * 25 && gp.player.worldY <= gp.tileSize * 26 && gp.player.worldX >= gp.tileSize*41 && gp.player.worldX <= gp.tileSize*45 && gp.currentMap == 4) {
				speak(gp.npc[4][0]);
				endDialogueMap45 = true;
			}
			
			if(gp.player.worldY <= gp.tileSize * 20 && gp.player.worldY >= gp.tileSize * 19 && gp.player.worldX >= gp.tileSize*41 && gp.player.worldX <= gp.tileSize*45 && gp.currentMap == 4 && endDialogueMap45 == true) {
				ishigamiPhase();
			}
			
			if(gp.player.worldY >= gp.tileSize * 10 && gp.player.worldX >= gp.tileSize * 9 && gp.player.worldX <= gp.tileSize * 11 && gp.currentMap == 5) {
				teleport(2, 44, 14, gp.outside, "down");
			}
		}
	} 

	public void drawDialogueSummon() {
		if(gp.endDialogue1 == false) {
			speak(gp.npc[gp.currentMap][0]);
		}
		if(gp.endDialogue1 == true && gp.endDialogue2 == false) {
			gp.npc[0][0].dying = true;
			speak(gp.npc[gp.currentMap][0]);
			gp.npc[0][1] = new NPC_Griffon(gp);
			gp.npc[0][1].worldX = gp.tileSize*18;
			gp.npc[0][1].worldY = gp.tileSize*4;
		}
		if(gp.endDialogue2 == true && gp.endDialogue3 == false) {
			speak(gp.npc[0][1]);
			gp.npc[0][0] = null;
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
		
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		return hit;
		
	}
	
	public void trapRock() {
		projectile = new OBJ_Spike_Rock(gp);
    	projectile.set(gp.player.worldX - gp.tileSize*8, gp.player.worldY, "right", true, gp.player);
		for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
			if(gp.projectile[gp.currentMap][i] == null) {
				gp.projectile[gp.currentMap][i] = projectile;
				break;
			}
		}
	}
	
	public void trapSpikeArrow() {
		projectile = new OBJ_Spike_Arrow(gp);
    	projectile.set(gp.player.worldX - gp.tileSize, gp.player.worldY - gp.tileSize*6, "down", true, gp.player);
		for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
			if(gp.projectile[gp.currentMap][i] == null) {
				gp.projectile[gp.currentMap][i] = projectile;
				break;
			}
		}
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life --;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.playSe(2);
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}
	
	}
	
	public void teleport(int map, int col, int row, int area, String direction) {
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		nextMap = map;
		tempMap = gp.currentMap;
		tempCol = col;
		tempRow = row;
		gp.player.direction = direction;
		canTouchEvent = false;
		gp.playSe(13);
	}
	
	public void speak(Entity entity) {
		// TODO Auto-generated method stub
		gp.gameState = gp.dialogueState;
		entity.speak();
	}

	public void dragonPhase() {
		
		if(gp.dragonBattleOn == false && Progress.dragonDefeated == false) {
			gp.gameState = gp.cutSceneState;
			gp.csManager.sceneNum = gp.csManager.ryujin;
		}
	}

	public void ishigamiPhase() {
		
		if(gp.ishigamiBattleOn == false && Progress.ishigamiDefeated == false) {
			gp.gameState = gp.cutSceneState;
			gp.csManager.sceneNum = gp.csManager.ishigami;
		}
	}

	public void resetCounter() {
		// TODO Auto-generated method stub
		canTouchEvent = true;
		trapRock = false;
		trapSpikeArrow = false;
		endDialogueMap1 = false;
		endDialogueMap2 = false;
		endDialogueMap3 = false;
		endDialogueMap35 = false;
		endDialogueMap4 = false;
		endDialogueMap45 = false;
		endGriffon = false;
	}
}

package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import entity.PlayerDummy;
import monster.MON_Dragon;
import monster.MON_Ishigami;
import object.OBJ_Black_Rock;

public class CutsceneManager {

	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter = 0;
	float alpha = 0f;
	int y;
	String endCredit;
	
	//Scene Number
	public final int NA = 0;
	public final int ryujin = 1;
	public final int ishigami = 2;
	public final int ending = 3;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
		
		endCredit = "DEVELOPED BY\n"
				+ "NGUYEN DOAN HUY\n"
				+ "KAITOHUY STUDIO\n"
				+ "\n"
				+ "LEAD PROGRAMMER\n"
				+ "NGUYEN DOAN HUY\n"
				+ "\n"
				+ "PROJECT MANAGER\n"
				+ "NGUYEN DOAN HUY\n"
				+ "\n"
				+ "GRAPHICS DESIGNER\n"
				+ "NGUYEN DOAN HUY\n"
				+ "NGUYEN TRONG DUC\n"
				+ "\n"
				+ "SOUND & MUSIC\n"
				+ "NGUYEN DOAN HUY\n"
				+ "\n"
				+ "TECHNICAL SPECIFICATION\n"
				+ "NGUYEN DOAN HUY\n"
				+ "HOANG MINH TRONG\n"
				+ "\n"
				+ "TESTER\n"
				+ "NGUYEN DOAN HUY\n"
				+ "NGUYEN DOAN HUNG\n"
				+ "\n\n\n\n\n"
				+ "Special Thanks\n"
				+ "DAO MANH SON PROFESSOR\n"
				+ "\n\n\n"
				+ "SEASON2:\n"
				+ "ROAD TO HUMAN'S WORLD\n"
				+ "IS COMING SOON...."
				+ "\n\n\n"
				+ "THANKS YOU FOR PLAYING!\n"
				;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch(sceneNum) {
		case ryujin: scene_ryujin(); break;
		case ishigami: scene_ishigami(); break;
		case ending: scene_ending(); break;
		}
	}
	
	public void scene_ryujin() {
		
		if(scenePhase == 0) {
			gp.playSe(21);
			gp.dragonBattleOn = true;
			
			//search a vacant slot for the dummy
			for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			gp.player.worldY -= 4;
			
			if(gp.player.worldY < gp.tileSize * 10) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			
			for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
			
				if(gp.monster[gp.currentMap][i] != null && 
						gp.monster[gp.currentMap][i].name.equals(MON_Dragon.monName)) {

					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		
		if(scenePhase == 3) {

			gp.ui.drawDialogueScreen();
		}
		
		if(scenePhase == 4) {
			
			for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.player.direction = gp.npc[gp.currentMap][i].direction;
					
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			//start drawing the player
			gp.player.drawing = true;
			
			//reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			//change the music
			gp.stopMusic();
			gp.playMusic(22);
		}
	}

	public void scene_ishigami() {
		
		if(scenePhase == 0) {
			
			gp.ishigamiBattleOn = true;
			
			//shut the iron door
			for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Black_Rock(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*41;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*21;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSe(21);
					break;
				}
			}
			
			//search a vacant slot for the dummy
			for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			gp.player.worldY -= 6;
			
			if(gp.player.worldY < gp.tileSize * 10) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			gp.player.worldX -= 6;
			
			if(gp.player.worldX < gp.tileSize * 10) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 3) {
			for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
			
				if(gp.monster[gp.currentMap][i] != null && 
						gp.monster[gp.currentMap][i].name.equals(MON_Ishigami.monName)) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		
		if(scenePhase == 4) {
			
			gp.ui.drawDialogueScreen();
			
		}
		
		if(scenePhase == 5) {
			
			for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null &&
						gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.player.direction = gp.npc[gp.currentMap][i].direction;
					
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			gp.player.drawing = true;
			
			//reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			//change the music
			gp.stopMusic();
			gp.playMusic(19);
		}
	}
	
	public void scene_ending() {
		
		if(scenePhase == 0) {
			
			gp.stopMusic();
			gp.playMusic(26);
			if(counterReached(60) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 1) {
			
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackground(alpha);
			
			if(alpha == 1f) {
				alpha = 0;
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			
			drawBlackBackground(1f);
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "Sau trận chiến thượng đỉnh giữa Wirzard và Kira\n"
					+ "Kẻ thắng kẻ thua đã phân định, tân vương mới được thiết lập\n"
					+ "Nhưng câu chuyện chưa kết thúc ở đây\n"
					+ "Mọi thứ chỉ mới bắt đầu, câu chuyện về Kira còn tiếp diễn...\n";
			drawString(alpha, 38f, 200, text, 70);
			
			if(counterReached(300) == true) {
				gp.playMusic(0);
				scenePhase++;
			}
		}
		
		if(scenePhase == 3) {
			
			drawBlackBackground(1f);
			drawString(1f, 120f, gp.screenHeight/2, "MURAMASHA", 40);
			if(counterReached(180) == true) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 4) {
			 
			drawBlackBackground(1f);
			y = gp.screenHeight/2;
			drawString(1f, 38f, y, endCredit, 40);
			if(counterReached(60) == true) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 5) {
			 
			drawBlackBackground(1f);
			
			y--;
			drawString(1f, 38f, y, endCredit, 40);
		
		}
		
	}
	
	public boolean counterReached(int target) {
		
		boolean counterReached = false;
		counter++;
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		
		return counterReached;
	}
	
	public void drawBlackBackground(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		
		for(String line: text.split("\n")) {
			int x = gp.ui.getXForCenteredText(line);
			g2.drawString(line, x, y);
			y += lineHeight;
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

}

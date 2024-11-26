package entity;

import java.awt.image.BufferedImage;
import java.util.List;

import animation.Phoenix;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Armor_Lv0;
import object.OBJ_Shuriken;
import object.OBJ_Summon_Book;
import object.OBJ_Sword_Lv0;
import object.OBJ_Util;

public class Player extends Entity {

	public KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean lightUpdated = false;
	public int playerId = 0;
	public boolean setId = false;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		name = "player";
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultSolidArea(28, 40, 32, 32, 72, 72);
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 9;
		worldY = gp.tileSize * 5;
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "right";
		type = type_player;
		
		//player status
		level = 1;
		maxLife = 50;
		life = maxLife;
		maxMana = 10;
		mana = maxMana;
		strength = 5;
		intitalDefense = 5;
		exp = 0;
		nextLevelExp = 20;
		coin = 100;
		currentWeapon = new OBJ_Sword_Lv0(gp);
		currentArmor = new OBJ_Armor_Lv0(gp);
		currentBook = new OBJ_Summon_Book(gp);
		currentLight = null;
		projectile = new OBJ_Shuriken(gp);
		utilProjectile = new OBJ_Util(gp);
		phoenix = new Phoenix(gp);
		
		attack = getAttack();
		defense = getDefenseArmor();
		maxFrameAttack = 6;
		frameDelay = 2;
		
		inventory.clear();
		getImage();
		getAttackImage();
		setItem();
		setDialogue();
	}
	
	public void setDialogue() {
	
		dialogues[0][0] = "Bạn đã tiến đến cấp độ " + level + "!\n" + "Nguồn sức mạnh dồi dào này đang chảy trong huyết quản!";
		dialogues[1][0] = "Bạn cần đạt cấp độ 3 trở lên để sử dụng chiêu này!";
		dialogues[2][0] = "Bạn cần đạt cấp độ 5 trở lên để sử dụng chiêu này!";
		dialogues[3][0] = "Bạn cần học cách triệu hồi Phoenix thông\nqua sách hướng dẫn!";
	}
	
	public void restoreStatus() {
		switch (gp.currentMap){
			case 0: {
				worldX = gp.tileSize * 9;
				worldY = gp.tileSize * 5;
				direction = "right";
				break;
			}
			case 1: {
				worldX = gp.tileSize * 3;
				worldY = gp.tileSize * 32;
				direction = "right";
				break;
			}
			case 2: {
				worldX = gp.tileSize * 25;
				worldY = gp.tileSize * 46;
				direction = "up";
				break;
			}
			case 3: {
				worldX = gp.tileSize * 24;
				worldY = gp.tileSize * 48;
				direction = "up";
				break;
			}
			case 4: {
				worldX = gp.tileSize * 42;
				worldY = gp.tileSize * 48;
				direction = "up";
				break;
			}
		}
	
		life = maxLife;
		mana = maxMana;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	public void setItem() {
		
		inventory.add(currentArmor);	
		inventory.add(currentWeapon);
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 15;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentArmorSlot() {
		int currentArmorSlot = 15;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == currentArmor) {
				currentArmorSlot = i;
			}
		}
		return currentArmorSlot;
	}
	
	public int getCurrentBookSlot() {
		int currentBookSlot = 15;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == currentBook) {
				currentBookSlot = i;
			}
		}
		return currentBookSlot;
	}
	
	public int getDefenseArmor() {
		// TODO Auto-generated method stub
		if (currentArmor == null) {
			return intitalDefense;
		}
		return defense = intitalDefense + currentArmor.defenseValue;
	}

	public int getAttack() {
		// TODO Auto-generated method stub
		attackArea = currentWeapon.attackArea;
		return attack = strength + currentWeapon.attackvalue;
	}

	public void getImage() {
	
		up1 = setup("/player/king_up_1", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		up2 = setup("/player/king_up_2", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		down1 = setup("/player/king_down_1", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		down2 = setup("/player/king_down_2", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		left1 = setup("/player/king_left_1", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		left2 = setup("/player/king_left_2", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		right1 = setup("/player/king_right_1", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		right2 = setup("/player/king_right_2", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		
	}
	
	public void getSleepingImage(BufferedImage image) {
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
		
	}
	
	public void getAttackImage() {
		
		if(currentWeapon.type == type_sword) {
			String tempLevel = "";
			switch (currentWeapon.swordLevel) {
			case 0: tempLevel = "lv0"; break;
			case 1: tempLevel = "lv1"; break;
			case 2: tempLevel = "lv2"; break;
			case 3: tempLevel = "lv3"; break;
			}
			
			projectile.getAttackImage(tempLevel);
			
			for (int i = 0; i < maxFrameAttack; i++) {
		    	String tempPath = "/player/king_attack_" + tempLevel + "_up_" + i;
		    	attackUp[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
		    }
		    
		    for (int i = 0; i < maxFrameAttack; i++) {
		    	String tempPath = "/player/king_attack_" + tempLevel + "_down_" + i;
		    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);	    
		    }
		    
		    for (int i = 0; i < maxFrameAttack; i++) {
		    	String tempPath = "/player/king_attack_" + tempLevel + "_left_" + i;
		    	attackLeft[i] = setup(tempPath, gp.tileSize*3 + 44, gp.tileSize*3 - 12);
		    }
		    
		    for (int i = 0; i < maxFrameAttack; i++) {
		    	String tempPath = "/player/king_attack_" + tempLevel + "_right_" + i;
		    	attackRight[i] = setup(tempPath, gp.tileSize*3 + 44, gp.tileSize*3 - 12);
		    }
		}
	}
	
	public void update() {
		
		if (!canAttack) {
            cooldownCounter++;
            if (cooldownCounter > attackCooldown) {
                canAttack = true; // Cho phép tấn công lại sau khi cooldown kết thúc
            }
        }
		
		if(knockBack == true) {
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check object collision
			gp.cChecker.checkObject(this, true);
			
			//check npc collision
			gp.cChecker.checkEntity(this, gp.npc);
			
			//check monster collision
			gp.cChecker.checkEntity(this, gp.monster);
			
			if(collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
				
			} else {
				switch(knockBackDirection) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			knockBackCounter++;
			if(knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			
		}
		else if(attacking == true) {
			attacking();
		}
		else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if (keyH.upPressed == true) {
				direction = "up";
			}
			if (keyH.downPressed == true) {
				direction = "down";
			}
			if (keyH.leftPressed == true) {
				direction = "left";
			}
			if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//check npc collision
			List<Integer> npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			List<Integer> monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//check event
			gp.eHandler.checkEvent();
			
			//if collision is false, player can move
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}else {
			standCounter++;
			
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}

		}
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
				&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
			
			if(canUseSkill1 == true) {
				//set defaut coordinate
				projectile.set(worldX + solidArea.x - 12, worldY + solidArea.y - 12, direction, true, this);
				
				//sutract mana
				projectile.subtractResource(this);
				
				for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
					if(gp.projectile[gp.currentMap][i] == null) {
						gp.projectile[gp.currentMap][i] = projectile;
						break;
					}
				}
				
				shotAvailableCounter = 0;
				gp.playSe(10);
			} else {
				startDialogue(this, 1);
				gp.keyH.shotKeyPressed = false;
			}
		}
		
		if(gp.keyH.utilKeyPressed == true && utilProjectile.alive == false 
				&& utilAvailableCounter == 30 && utilProjectile.haveResource(this) == true) {
			
			if(canUseSkill2 == true) {
				//set defaut coordinate
				utilProjectile.set(worldX + solidArea.x - gp.tileSize*3 + 16, worldY + solidArea.y - gp.tileSize*3 + 8, direction, true, this);
				
				//sutract mana
				utilProjectile.subtractResource(this);
				
				for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
					if(gp.projectile[gp.currentMap][i] == null) {
						gp.projectile[gp.currentMap][i] = utilProjectile;
						break;
					}
				}
				
				utilAvailableCounter = 0;
				drawing = false;
				speed = 0;
				gp.playSe(10);
			} else {
				startDialogue(this, 2);
				gp.keyH.utilKeyPressed = false;
			}
		}

		if(utilProjectile.alive == false) {
			speed = defaultSpeed;
			drawing = true;
		}
		
		if(gp.keyH.useLifeKeyPressed == true || gp.keyH.useManaKeyPressed == true) {
			String namePosion = "";
			if(gp.keyH.useLifeKeyPressed == true) {
				namePosion = "posion red";
				gp.keyH.useLifeKeyPressed = false;
			} else {
				namePosion = "posion blue";
				gp.keyH.useManaKeyPressed = false;
			}
			int itemIndex = searchItemInventory(namePosion);
			if(itemIndex != 999) {

				Entity selectedItem = inventory.get(itemIndex);
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					}else {
						inventory.remove(itemIndex);											
					}
				}
			}
		}
		// this needs to be outside of key if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 30) {
				invincibleCounter = 0;
				invincible = false;
				transparent = false;
			}
		}
		
		if(level >= 3) {
			canUseSkill1 = true;
		}
		
		if(level >= 5) {
			canUseSkill2 = true;
		}
		
		if(life > maxLife) {
			life = maxLife;
		}
		
		if(mana > maxMana) {
			mana = maxMana;
		}
		
		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		if(utilAvailableCounter < 30) {
			utilAvailableCounter++;
		}
		
//		
		if(life <= 0) {
			gp.stopMusic();
			gp.gameState = gp.gameOverState;
			gp.playSe(12);
		}
	}

	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			//pickup only items
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
				
			}
			//obstacle
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
				gp.obj[gp.currentMap][i].interact();
		
			}
			//inventory items
			else {
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					gp.playSe(1);
					text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}else {
					text = "Bạn không thể nhặt thêm đồ được nữa!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}

	public void interactNPC(List<Integer> npcIndex) {
		
		if(!npcIndex.isEmpty()) {
			for(int i = 0; i < npcIndex.size(); i++) {
				gp.npc[gp.currentMap][npcIndex.get(i)].speak();
			}
		}
	}

	public void contactMonster(List<Integer> monsterIndex) {
		// TODO Auto-generated method stub
		if(!monsterIndex.isEmpty()) {
			
			for(int i = 0; i < monsterIndex.size(); i++) {
				
				if(invincible == false && gp.monster[gp.currentMap][monsterIndex.get(i)].dying == false) {
					gp.playSe(6);
					int damage  = gp.monster[gp.currentMap][monsterIndex.get(i)].attack - defense;
					if(damage < 0) {
						damage = 0;
					}
					if(life < 0) {
						life = 0;
					}
					life -= damage;
					invincible = true;
					transparent = true;
				}
			}
			
		}
	}

	public void damageMonster(List<Integer> target, Entity attacker, int attack, int knockBackPower) {

		if(target.size() > 0) {
			for(int i = 0; i < target.size(); i++) {
				if(gp.monster[gp.currentMap][target.get(i)].invincible == false) {
					gp.playSe(5);
					
					if(knockBackPower > 0) {
						setKnockBack(gp.monster[gp.currentMap][target.get(i)], attacker, knockBackPower);
					}
					
					if(gp.monster[gp.currentMap][target.get(i)].offBalance == true) {
						attack += 5;
						
					}
					
					int damage  = attack - gp.monster[gp.currentMap][target.get(i)].defense;
					if(damage < 0) {
						damage = 0;
					}
					
					gp.monster[gp.currentMap][target.get(i)].life -= damage;
					gp.ui.addMessage(damage + "damage!");
					
					gp.monster[gp.currentMap][target.get(i)].invincible = true;
					gp.monster[gp.currentMap][target.get(i)].damageReaction();
					
					if(gp.monster[gp.currentMap][target.get(i)].life <= 0) {
						gp.monster[gp.currentMap][target.get(i)].dying = true;
						gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][target.get(i)].name + "!");
						gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][target.get(i)].exp + "!");
						exp += gp.monster[gp.currentMap][target.get(i)].exp;
						checkLevelUp();
					}
				}
			}
			
		}
	}

	public void damageProjectile(List<Integer> projectileIndex) {

		if(!projectileIndex.isEmpty()) {
			for(int i = 0; i < projectileIndex.size(); i++) {
				Entity projectile = gp.projectile[gp.currentMap][i];
				if(projectile != null) {
					projectile.alive = false;
					generateParticle(projectile, projectile);
					gp.projectile[gp.currentMap][i] = null;
				}
			}
		}
	}

	private void checkLevelUp() {
		// TODO Auto-generated method stub
		
		if(exp >= nextLevelExp) {
			
			invincible = false;
			transparent = false;
			level++;
			nextLevelExp *= 2;
			maxLife += 50;
			life += 25;
			maxMana += 5;
			mana += 2;
			strength += 5;
			intitalDefense += 5;
			
			attack = getAttack();
			defense = getDefenseArmor();
			
			for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
				if(gp.projectile[gp.currentMap][i] != null) {
					
					switch (gp.projectile[gp.currentMap][i].name) {
						case "shuriken": {
							gp.projectile[gp.currentMap][i].attack += 5;
							gp.projectile[gp.currentMap][i].useCost = gp.player.maxMana/10;
						}
						case "util": {
							gp.projectile[gp.currentMap][i].attack += 10;
							gp.projectile[gp.currentMap][i].useCost = gp.player.maxMana/3;
						}
						case "meteors": {
							gp.projectile[gp.currentMap][i].attack += 50;
							gp.projectile[gp.currentMap][i].useCost = gp.player.maxMana/3;
						}
					}
				}
			}
			
			

			gp.playSe(8);	
			setDialogue();
			startDialogue(this, 0);
		}
	}

	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
			}
			
			if(selectedItem.type == type_armor ) {
				
				currentArmor = selectedItem;
				defense = getDefenseArmor();
			}	

			if(selectedItem.type == type_light) {
				
				if(currentLight == selectedItem) {
					currentLight = null;
				}else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			
			if(selectedItem.type == type_book) {
				
				if(currentBook == selectedItem) {
					currentBook = null;
				}else {
					currentBook = selectedItem;
					currentBook.use(this);
				}	
			}
			
			if(selectedItem.type == type_consumable) {

				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					}else {
						inventory.remove(itemIndex);											
					}
				}
			}
		}
	}
	
	public int searchItemInventory(String itemName) {
		
		int itemIndex = 999;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		//check if stackable
		if(newItem != null) {
			
			int index = searchItemInventory(newItem.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}else { // new item , check vacancy
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		}
		
		return canObtain;
	}
	
}
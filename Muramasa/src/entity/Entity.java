package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import animation.Phoenix;
import main.GamePanel;
import main.UtilityTool;
import monster.MON_Orc;
import monster.MON_Skeleton;
import monster.MON_Zombie_Fire;
import monster.MON_Zombie_Winter;
import object.OBJ_Fire_Storm;

public class Entity {
	
	GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int eWidth, eHeight;
	public int solidAreaDefaultX, solidAreaDefaultY, realWidth, realHeight;
	public boolean collision = false;
	public String dialogues[][] = new String[10][30];
	public boolean endDialogue = false;
	public Entity attacker;
	public Entity linkedEntity;
	public boolean temp = false;
	
	//state
	public int worldX, worldY;
	public String direction = "down";
	public String currentDirection = "";
	public int spriteNum = 1;
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean standing = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean transparent = false;
	public boolean offBalance = false;
	public Entity loot;
	public boolean opened = false;
	public boolean inRage = false;
	public boolean sleep = false;
	public boolean drawing = true;
	public boolean canAttack = true; // Cờ để kiểm tra xem có thể tấn công hay không
	public boolean canUseSkill3 = false, canUseSkill2 = false, canUseSkill1 = false;
	public boolean endPhase1 = false, startPhase2 = false, beingPhase = false, confirmPhase2 = false;
	
	//counter
	public int spriteCounter = 0;
	public int invincibleCounter = 0;
	public int actionLockCounter = 0;
	public int shotAvailableCounter = 0;
	public int utilAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public int recoveryManaCounter = 0;
	public int knockBackCounter = 0;
	public int offBalanceCounter = 0;
	public int frameIndex = 0;  // Frame hiện tại trong hoạt ảnh
	public int frameCounter = 0;  // Đếm số bước để đổi frame
	public int frameDelay = 0;    // Khoảng thời gian giữ một frame (dựa trên FPS 60 và 8 frame)
	public int attackCooldown = 15; // Khoảng thời gian chờ (có thể điều chỉnh)
	public int cooldownCounter = 0;
	public int skillSummonCounter = 0;
	
	//character attributes
	public String name  = "";
	public int defaultSpeed;
	public int speed;
	public int maxLife;
	public int life;
	public int level;
	public int strength;
	public int attack;
	public int maxMana;
	public int mana;
	public int intitalDefense;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon;
	public Entity currentBook;
	public Entity currentArmor;
	public Entity currentLight;
	public int skill = 0;
	public int nextSkill = 0;
	public Projectile projectile;
	public Projectile utilProjectile;
	public Phoenix phoenix;
	public boolean boss;
	public int maxFrameAttack;
	public BufferedImage[] attackUp = new BufferedImage[28];
	public BufferedImage[] attackDown = new BufferedImage[28];
	public BufferedImage[] attackLeft = new BufferedImage[28];
	public BufferedImage[] attackRight = new BufferedImage[28];
	public BufferedImage[] dead = new BufferedImage[28];
	
	//item attributes
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int swordLevel;
	public int attackvalue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadus;
	
	//type
	public int type;//0 : player, 1: npc, 2: monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_book = 5;
	public final int type_armor = 6;
	public final int type_consumable = 7;
	public final int type_pickupOnly = 8;
	public final int type_obstacle= 9;
	public final int type_light = 10;
	public final int type_pickaxe = 11;
	public final int type_skill = 12;
	public final int type_crep = 13;
	public int numOfDirecion = 4;
	
	public Entity(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public int getScreenX() {
		if(gp.player.screenX > gp.player.worldX) {
			return worldX;
		}
		
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if(rightOffset > gp.worldWidth - gp.player.worldX) {
			return gp.screenWidth - (gp.worldWidth - worldX);
		}
		
		return worldX - gp.player.worldX + gp.player.screenX;
	}
	
	public int getScreenY() {
		if(gp.player.screenY > gp.player.worldY) {
			return worldY;
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if(bottomOffset > gp.worldHeight - gp.player.worldY) {
			return gp.screenHeight - (gp.worldHeight - worldY);
		}
		return worldY - gp.player.worldY + gp.player.screenY;
	}
	
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	
	public int getTopY() {
		return worldY + solidArea.y;
	}
	
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	
	public int getCol() {
		return (worldX + solidArea.x)/gp.tileSize;
	}
	
	public int getRow() {
		return (worldY + solidArea.y)/gp.tileSize;
	}
	
	public int getCenterX() {
		return worldX + solidArea.x + solidArea.width/2;
	}
	
	public int getCenterY() {
		return worldY + solidArea.y + solidArea.height/2;
	}
	
	public int getWidth() {
        return eWidth;
    }

    public int getHeight() {
        return eHeight;
    }
	
	public int getXdistances(Entity target) {
		int xDistance = Math.abs(getCenterX() - target.getCenterX());
		return xDistance;
	}
	
	public int getYdistances(Entity target) {
		int yDistance = Math.abs(getCenterY() - target.getCenterY());
		return yDistance;
	}
	
	public int getTileDistance(Entity target) {
		int tileDistance = (getXdistances(target) + getYdistances(target))/gp.tileSize;
		return tileDistance;
	}
	
	public int getGoalCol(Entity target) {
		int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
		return goalCol;
	}
	
	public int getGoalRow(Entity target) {
		int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
		return goalRow;
	}
	
	public void resetCounter() {
		spriteCounter = 0;
		invincibleCounter = 0;
		actionLockCounter = 0;
		shotAvailableCounter = 0;
		utilAvailableCounter = 0;
		dyingCounter = 0;
		hpBarCounter = 0;
		knockBackCounter = 0;
		offBalanceCounter = 0;
	}
	
	public void setLoot(Entity loot) {}
	
	public void setAction() {}
	
	public void setDefaultSolidArea(int x, int y, int width, int height,int attackWidth, int attackHeight) {
		solidArea.x = x;
		solidArea.y = y;
		solidArea.width = width;
		solidArea.height = height;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = attackWidth;
		attackArea.height = attackHeight;
	}
	
	public void move(String direction) {}
	
	public void damageReaction() {}
	
	public void speak() {}
	
	public void setDialogue() {}
	
	public void facePlayer() {
		switch(gp.player.direction) {
		case"up": direction = "down"; break;
		case"down": direction = "up"; break;
		case"left": direction = "right"; break;
		case"right": direction = "left"; break;
		}
	}
	
	public void startDialogue(Entity entity, int setNum) {
		invincible = false;
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}

	public void interact() {}
	
	public boolean use(Entity entity) {return false;}
	
	public void checkDrop() {}
	
	public void dropItem(Entity droppedItem) {
		
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = solidArea.x + worldX - 12;
				gp.obj[gp.currentMap][i].worldY = solidArea.y + worldY - 12;
				break;
			}
		}
	}
	
	public Color getParticleColor() {
		Color color = null;
		return color;
	}
	
	public int getPariticleSize() {
		
		int size = 0;//pixel
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	
	public void generateParticle(Entity generator, Entity target) {

		Color color = generator.getParticleColor();
		int size = generator.getPariticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		gp.particleList.add(p1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		gp.particleList.add(p2);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		gp.particleList.add(p3);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p4);
	}
	
	public void checkCollision() {
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if((this.type == type_monster || this.type == type_crep) && contactPlayer == true) {
			damagePlayer(attack);
		}

		if(gp.ishigamiBattleOn == true) {
			if(skill == 3 && frameIndex >= maxFrameAttack/2-1) {
				summonMonster();
				skill = 0;
//				attacking = false;
			}
		}
	}
	
	public void update() {
		
		if(sleep == false) {
			
			checkCollision();
			if(knockBack == true) {
				
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
			else if(attacking == true && (type == type_player || type == type_monster || type == type_crep)) {
				attacking();
			}
			else {
				setAction();
				checkCollision();
				
				if(collisionOn == false) {
					switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
					}
				}
				
				spriteCounter++;
				if (spriteCounter > 24) {
					if(spriteNum == 1) {
						spriteNum = 2;
					}
					else if(spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
			}
			
			if(invincible == true) {
				invincibleCounter++;
				if(invincibleCounter > 40) {
					invincibleCounter = 0;
					invincible = false;
				}
			}
			
			if(shotAvailableCounter < 30) {
				shotAvailableCounter++;
			}
			if(utilAvailableCounter < 30) {
				utilAvailableCounter++;
			}
			
			if(offBalance == true) {
				offBalanceCounter++;
				if(offBalanceCounter > 60) {
					offBalance = false;
					offBalanceCounter = 0;
				}
			}
		}
		else if (sleep == true) {
			
		}
		
	}
	
	public boolean checkRange(int straight, int horizontal) {
		
		boolean targetInRange = false;
		int xDis = getXdistances(gp.player);
		int yDis = getYdistances(gp.player);
		
		switch(direction) {
		case "up":
			if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "down":
			if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "left":
			if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "right":
			if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		return targetInRange;
	}
	
	public void checkAttackOrNot(int rate,int straight, int horizontal) {
		if(checkRange(straight, horizontal) == true) {
			//check if it initiates an attack
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
	}
	
	public void checkShootOtNot(int rate, int shotInterval) {
	    int i = new Random().nextInt(rate);
	    if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {

	        if (name.equals("ryujin")) {
	            // Khởi tạo và thêm từng projectile với từng hướng khác nhau
	            addProjectile(worldX + solidArea.x, worldY + solidArea.y, "left");
	            addProjectile(worldX + solidArea.x + solidArea.width, worldY + solidArea.y, "right");
	            addProjectile(worldX + solidArea.x, worldY + solidArea.y - solidArea.height, "up");
	            addProjectile(worldX + solidArea.x, worldY + solidArea.y + solidArea.height, "down");
	            addProjectile(worldX + solidArea.x - solidArea.width/2, worldY + solidArea.y, "top_left");
	            addProjectile(worldX + solidArea.x + solidArea.width/2, worldY + solidArea.y, "top_right");
	            addProjectile(worldX + solidArea.x - solidArea.width/2, worldY + solidArea.y + solidArea.height, "bottom_left");
	            addProjectile(worldX + solidArea.x + solidArea.width/2, worldY + solidArea.y + solidArea.height, "bottom_right");
	            
	        } else {
	        	projectile.set(worldX + solidArea.x , worldY + solidArea.y, direction, true, this);
				
				//check vacancy
				for(int j = 0; j < gp.projectile[gp.currentMap].length; j++) {
					if(gp.projectile[gp.currentMap][j] == null) {
						gp.projectile[gp.currentMap][j] = projectile;
						break;
					}
				}
	        }
	        shotAvailableCounter = 0;
	    }
	}

	private void addProjectile(int x, int y, String direction) {
	    projectile = new OBJ_Fire_Storm(gp); // Tạo một projectile mới
	    projectile.set(x, y, direction, true, this);

	    // Kiểm tra vị trí trống và thêm vào mảng
	    for (int j = 0; j < gp.projectile[gp.currentMap].length; j++) {
	        if (gp.projectile[gp.currentMap][j] == null) {
	            gp.projectile[gp.currentMap][j] = projectile;
	            break;
	        }
	    }
	}

	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		
		if(getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = false;
			}
		}
	}
	
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		
		if(getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = true;
			}
		}
	}
	
	public void getRandomDirection(int interval) {
		actionLockCounter ++;
		
		if(actionLockCounter == interval) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;//pick up a number from 1 to 100
		
			if(i <= 25) {
				direction = "up";
			}else if(i > 25 && i <=50) {
				direction = "down";
			}else if(i > 50 && i <= 75) {
				direction = "left";
			}else if(i > 75 && i <= 100) {
				direction = "right";
			}	
			actionLockCounter = 0;
		}
	}
	
	public void moveTowardPlayer(int interval) {
		
		actionLockCounter ++;
		if(actionLockCounter > interval) {	
			if(getXdistances(gp.player) > getYdistances(gp.player) + 48) {
				if(gp.player.getCenterX() < getCenterX()) {
					direction = "left";
				}else {
					direction = "right";
				}
			}
			else if(getXdistances(gp.player) < getYdistances(gp.player) + 48) {
				if(gp.player.getCenterY() < getCenterY()) {
					direction = "up";
				}else {
					direction = "down";
				}
			}
			actionLockCounter = 0;
		}
		checkCollision();
	}

	public void attacking() {
		
		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int solidAreaWidth = solidArea.width;
		int solidAreaHeight = solidArea.height;
		
		switch(direction) {
			case"up": worldY -= attackArea.height; break;
			case"down": worldY += attackArea.height; break;
			case"left": worldX -= attackArea.width; break;
			case"right": worldX += attackArea.width; break;
		}
		
		solidArea.width = attackArea.width;
		solidArea.height = attackArea.height;
		
		if(type == type_monster || type == type_crep) {
			if(gp.cChecker.checkPlayer(this) == true) {
				damagePlayer(attack);
			}
		}
		else if(type == type_player) {

			List<Integer> monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
			
			List<Integer> projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
			gp.player.damageProjectile(projectileIndex);
		}
		
		worldX = currentWorldX;
		worldY = currentWorldY;
		solidArea.width = solidAreaWidth;
		solidArea.height = solidAreaHeight;
		
	}
	
	public void getAnimationAttack() {
		frameCounter++;

        if (frameCounter > frameDelay) {
            frameIndex++;
            if (frameIndex >= maxFrameAttack) {
                frameIndex = 0; 
                attacking = false; 
                if(inRage == true) {
                	startPhase2 = true;
                }
                if((name.equals("shuriken") || name.equals("meteors") || name.equals("spike rock") || name.equals("coin") || name.equals("util") || name.equals("black door")) && life >= 0) {
                	attacking = true;
                }
            }
            frameCounter = 0;
        }
	}
	
	public BufferedImage getDeadAnimation() {
		getAnimationAttack();
        return dead[frameIndex];
	}
	
	public BufferedImage getAttackUp() {
		getAnimationAttack();
        return attackUp[frameIndex];
	}
	
	public BufferedImage getAttackDown() {
		getAnimationAttack();
		return attackDown[frameIndex];
	}
	
	public BufferedImage getAttackLeft() {
		getAnimationAttack();
		return attackLeft[frameIndex];
	}
	
	public BufferedImage getAttackRight() {
		getAnimationAttack();
		return attackRight[frameIndex];
	}
	
	public void damagePlayer(int attack) {
		if(gp.player.invincible == false) {
			
			if(type == type_monster) {
				if(name.equals("ishigami")) {
					if(inRage == false) {
						if(skill == 1) {
							if(frameIndex == 2 || frameIndex == 3 || frameIndex == 4 || frameIndex == 5 || frameIndex == 10 || frameIndex == 11 || frameIndex == 12) {
								receiveDamage();
							}
						}
						else if(skill == 2) {
							if(frameIndex >= maxFrameAttack/2 + 1) {
								receiveDamage();
							}
						}	
					}else {
						if(skill == 1) {
							if(frameIndex == 8 || frameIndex == 9 || frameIndex == 10 || frameIndex == 11) {
								receiveDamage();
							}
						}
					}
					
				}else {
					if(frameIndex >= maxFrameAttack/2 + 1) {
						receiveDamage();
					}
				}
			}else {
				receiveDamage();
			}
		}
	}
	
	public void summonMonster() {
		int i = new Random().nextInt(6) + 5;
		int j = new Random().nextInt(6) + 5;
		int k = new Random().nextInt(4);
		int col = (worldX/gp.tileSize) + i;
		int row = (worldY/gp.tileSize) + j;
		
		if(gp.tileM.tile[gp.tileM.mapTileNum[4][col][row+3]].collision == true) {
			row-=6;
		}
		else if (gp.tileM.tile[gp.tileM.mapTileNum[4][col][row]].collision == true) {
			row-=3;
		}
		gp.aSetter.mapNum = 4;
		switch (k) {
			case 0: gp.aSetter.setUpMonster(MON_Orc.class, col, row); break;
			case 1: gp.aSetter.setUpMonster(MON_Zombie_Winter.class, col, row); break;
			case 2: gp.aSetter.setUpMonster(MON_Zombie_Fire.class, col, row); break;
			case 3: gp.aSetter.setUpMonster(MON_Skeleton.class, col, row); break;
		}
	}

	public void receiveDamage() {
		
		gp.playSe(5);
		int damage  = attack - gp.player.defense;
		
		if(damage > 0) {
			gp.player.transparent = true;
			setKnockBack(gp.player, this, knockBackPower);
		}else {
			damage = 0;
		}
		
		gp.player.life -= damage;
		gp.player.invincible = true;
	}
	
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}

	public boolean inCamera() {
		boolean inCamera = false;
		if(worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
				   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				   worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY && 
				   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			inCamera = true;
		}
		return inCamera;
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		
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
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(numOfDirecion == 4) {
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
				}
				else if (numOfDirecion == 2) {
					if(gp.player.worldX > worldX) {
						if(spriteNum == 1) {image = right1;}
						if(spriteNum == 2) {image = right2;}
					}else {
						if(spriteNum == 1) {image = left1;}
						if(spriteNum == 2) {image = left2;}
					}
				}	
			}
			if(attacking == true || standing == true) {
				if(numOfDirecion == 4) {
					if(name.equals("player")) {
						tempScreenX -= 16;
						tempScreenY -= 32;
					}
					if(name.equals("ishigami") && inRage == true) {
						if(skill == 1) {
							tempScreenX -= gp.tileSize*5;
							tempScreenY -= gp.tileSize*6;
						}
						else if(skill == 3) {
							tempScreenX -= 0;
							tempScreenY -= gp.tileSize;
						}
					}
					if(name.equals("skeleton")) {
						tempScreenY -= down1.getHeight();
					}
					image = getAttackUp();
				}
				else if(numOfDirecion == 2) {
					if(gp.player.worldX > worldX) {
						image = getAttackRight();
					}else {
						image = getAttackLeft();
					}
				}		
			}
			break;
		case "down":
			if(attacking == false) {
				if(numOfDirecion == 4) {
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
				}
				else if (numOfDirecion == 2) {
					if(gp.player.worldX > worldX) {
						if(spriteNum == 1) {image = right1;}
						if(spriteNum == 2) {image = right2;}
					}else {
						if(spriteNum == 1) {image = left1;}
						if(spriteNum == 2) {image = left2;}
					}
				}
			}
			if(endPhase1 == true && startPhase2 == false) {
				image = getDeadAnimation();
			}
			if(attacking == true || standing == true) {
				if(numOfDirecion == 4) {
					if(name.equals("player")) {
						tempScreenX -= 16;
						tempScreenY -= 16;
					}
					if(name.equals("ishigami") && inRage == true) {
						if(skill == 1) {
							tempScreenX -= gp.tileSize*3;
							tempScreenY -= gp.tileSize*3;
						}
						else if(skill == 3) {
							tempScreenX -= gp.tileSize*4 - 24;
							tempScreenY -= 0;
						}
					}
					image = getAttackDown();					
				}
				else if(numOfDirecion == 2) {
					if(gp.player.worldX > worldX) {
						image = getAttackRight();
					}else {
						image = getAttackLeft();
					}
				}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true || standing == true) {
				if(name.equals("player")) {
					tempScreenX -= 64;
					tempScreenY -= 24;
				}
				if(name.equals("ishigami") && inRage == true) {
					if(skill == 1) {
						tempScreenX -= gp.tileSize*3;
						tempScreenY -= gp.tileSize*3;
					}
					else if(skill == 3) {
						if(frameIndex <= 3) {
							tempScreenX += gp.tileSize*3;
						}
						tempScreenY -= 0;
					}
				}
				if(name.equals("skeleton")) {
					tempScreenX -= down1.getWidth();
				}
				image = getAttackLeft();	
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true || standing == true) {
				if(name.equals("ishigami") && inRage == true) {
					if(skill == 1) {
						tempScreenX -= gp.tileSize*4;
						tempScreenY -= gp.tileSize*3;
					}
					else if(skill == 3) {
						tempScreenX += gp.tileSize;
						tempScreenY -= 0;
					}
				}
				if(name.equals("player")) {
					tempScreenX -= 20;
					tempScreenY -= 24;
				}
				image = getAttackRight();						
			}
			break;
		default:
			image = getAttackRight();
			break;
		}

		if(endPhase1 == true && startPhase2 == false) {
			image = getDeadAnimation();
		}

		if(invincible == true) {
			hpBarOn = true;
			hpBarCounter = 0;
			changeAlpha(g2, 0.4f);					
		}
		
		if(name != null && name.equals("player")) {
			
			if(gp.keyH.summonKeyPressed == true) {
				if(canUseSkill3 == true) {
					if(mana >= maxMana/3) {
						phoenix.draw(g2);						
					}
				}else {
					startDialogue(this, 3);
					gp.keyH.summonKeyPressed = false;
				}
			}
			if(transparent == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
				if(invincibleCounter % 5 == 0) {
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					g2.drawImage(image, tempScreenX, tempScreenY, null);
				}
			}
			
			if(drawing == true) {
				g2.drawImage(image, tempScreenX, tempScreenY, null);
			}
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		else {
			if(dying == true) {
				dyingAnimation(g2);
			}
			if(inCamera() == true) {
				g2.drawImage(image, tempScreenX, tempScreenY, null);
				changeAlpha(g2, 1f);
			}
			else if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.worldWidth - gp.player.worldX ||
			    bottomOffset > gp.worldHeight - gp.player.worldY) {
				g2.drawImage(image, tempScreenX, tempScreenY, null);
			}
		}
//		
//		g2.setColor(Color.red);
//		g2.drawRect(tempScreenX + solidAreaDefaultX, tempScreenY + solidAreaDefaultY, solidArea.width, solidArea.height);
//		g2.setColor(Color.green);
//		if(image != null) {
//			g2.drawRect(tempScreenX, tempScreenY, image.getWidth(), image.getHeight());
//		}
		
	}

	protected void dyingAnimation(Graphics2D g2) {
		// TODO Auto-generated method stub
		dyingCounter++;
		int i = 5;
		
		if(dyingCounter % i != 0) {
			changeAlpha(g2, 1f);
		} else {
			changeAlpha(g2, 0f);
		}
	
		if(dyingCounter > i*8 && !name.equals("mage")) {
			alive = false;
		}
		
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			InputStream imageStream = getClass().getResourceAsStream(imagePath + ".png");
			if (imageStream == null) {
			    throw new IllegalArgumentException("File not found: " + imagePath + ".png");
			}
			image = ImageIO.read(imageStream);

			image = uTool.scaleImage(image, width, height);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNode(startCol, startRow, goalCol, goalRow);
		
		if(gp.pFinder.search() == true) {
			
			// Next worldX & worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			//solidArea
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				//left or right
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				//up or left
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				//up or right
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				//down or left
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				//down or right
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
		}
	}
	
	public int getDetected(Entity user, Entity target[][], String targetname) {
		
		int index = 999;
		
		//check the surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY() - gp.player.speed; break;
		case "down": nextWorldY = user.getBottomY() + gp.player.speed; break;
		case "left": nextWorldX = user.getLeftX() - gp.player.speed; break;
		case "right": nextWorldX = user.getRightX() + gp.player.speed; break;
		}
		
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		for(int i = 0; i < target[gp.currentMap].length; i++) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col && 
						target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetname)) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

}


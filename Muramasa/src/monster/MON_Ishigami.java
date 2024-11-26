package monster;

import java.awt.image.BufferedImage;
import java.util.Random;
import data.Progress;
import entity.Monster;
import entity.NPC_Mage;
import main.GamePanel;
import object.OBJ_Portal;

public class MON_Ishigami extends Monster{

	GamePanel gp;
	public static final String monName  ="ishigami";
	private int numRange = 0;
	private BufferedImage[] attackUpSkill1 = new BufferedImage[28];
	private BufferedImage[] attackDownSkill1 = new BufferedImage[28];
	private BufferedImage[] attackLeftSkill1 = new BufferedImage[28];
	private BufferedImage[] attackRightSkill1 = new BufferedImage[28];
	private BufferedImage[] attackLeftSkill2 = new BufferedImage[28];
	private BufferedImage[] attackRightSkill2 = new BufferedImage[28];
	private BufferedImage[] attackUpSkill3 = new BufferedImage[28];
	private BufferedImage[] attackDownSkill3 = new BufferedImage[28];
	private BufferedImage[] attackLeftSkill3 = new BufferedImage[28];
	private BufferedImage[] attackRightSkill3 = new BufferedImage[28];
	
	public MON_Ishigami(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		boss = true;
		sleep = true;
		direction = "right";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 10000;
		life = maxLife;
		attack = 230;
		defense = 30;
		exp = 1000000;
		knockBackPower = 10;
		maxFrameAttack = 19;
		frameDelay = 6;
		numOfDirecion = 2;
		numRange = 4;
		setDefaultSolidArea(124, 96, 96, 160, 112, 112);
		getImage();
		setDialogue();
		getAttackImage();
	}
	
	public void getImage() {
		
		int i = 7;
		
		if(inRage == false) {
			
			left1 = setup("/monster/ishigami_left_1", gp.tileSize * i, gp.tileSize * i);
			left2 = setup("/monster/ishigami_left_2", gp.tileSize * i, gp.tileSize * i);
			right1 = setup("/monster/ishigami_right_1", gp.tileSize * i, gp.tileSize * i);
			right2 = setup("/monster/ishigami_right_2", gp.tileSize * i, gp.tileSize * i);
		}
		
		if(inRage == true) {
			up1 = setup("/monster/ishigami_phase2_up_1", gp.tileSize*i, gp.tileSize*6);
			up2 = setup("/monster/ishigami_phase2_up_2", gp.tileSize*i , gp.tileSize*6);
			down1 = setup("/monster/ishigami_phase2_down_1", gp.tileSize*i, gp.tileSize*6);
			down2 = setup("/monster/ishigami_phase2_down_2", gp.tileSize*i, gp.tileSize*6);
			left1 = setup("/monster/ishigami_phase2_down_1", gp.tileSize*i, gp.tileSize*6);
			left2 = setup("/monster/ishigami_phase2_down_2", gp.tileSize*i, gp.tileSize*6);
			right1 = setup("/monster/ishigami_phase2_down_1", gp.tileSize*i, gp.tileSize*6);
			right2 = setup("/monster/ishigami_phase2_down_2", gp.tileSize*i, gp.tileSize*6);
		}
	}
	
	public void getDeadImage() {
		String path = "";
		if(direction.equals("right") || gp.player.worldX > worldX) {
			path = "right";
		}else if(direction.equals("left") || gp.player.worldX < worldX) {
			path = "left";
		}
		
		for (int i = 0; i < 19; i++) {
	    	String tempPath = "/monster/ishigami_death_" + path + "_" + i;
	    	dead[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	}
	
	public void getAttackImage() {

		for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_attack" + "_left_" + i;
	    	attackLeftSkill1[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	    
	    for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_attack" + "_right_" + i;
	    	attackRightSkill1[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	    
	    for (int i = 0; i < 11; i++) {
	    	String tempPath = "/monster/ishigami_defense" + "_left_" + i;
	    	attackLeftSkill2[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	    
	    for (int i = 0; i < 11; i++) {
	    	String tempPath = "/monster/ishigami_defense" + "_right_" + i;
	    	attackRightSkill2[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	    
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_summon" + "_left_" + i;
	    	attackLeftSkill3[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	    
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_summon" + "_right_" + i;
	    	attackRightSkill3[i] = setup(tempPath, gp.tileSize*7, gp.tileSize*7);
	    }
	}
	
	public void getAttackImagePhase2() {
		
		//attack
		for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_phase2_attack" + "_up_" + i;
	    	attackUpSkill1[i] = setup(tempPath, gp.tileSize*15, gp.tileSize*12);
	    }
	    
	    for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_phase2_attack" + "_down_" + i;
	    	attackDownSkill1[i] = setup(tempPath, gp.tileSize*15, gp.tileSize*12);
	    }
	    
	    for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_phase2_attack" + "_left_" + i;
	    	attackLeftSkill1[i] = setup(tempPath, gp.tileSize*15, gp.tileSize*12);
	    }
	    
	    for (int i = 0; i < 12; i++) {
	    	String tempPath = "/monster/ishigami_phase2_attack" + "_right_" + i;
	    	attackRightSkill1[i] = setup(tempPath, gp.tileSize*15, gp.tileSize*12);
	    }
	    
	    //summon
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_phase2_summon" + "_up_" + i;
	    	attackUpSkill3[i] = setup(tempPath, gp.tileSize*8 + 24, gp.tileSize*6+24);
	    }
	    
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_phase2_summon" + "_down_" + i;
	    	attackDownSkill3[i] = setup(tempPath, gp.tileSize*8 + 24, gp.tileSize*6+24);
	    }
	    
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_phase2_summon" + "_left_" + i;
	    	attackLeftSkill3[i] = setup(tempPath, gp.tileSize*5 + 24, gp.tileSize*6+24);
	    }
	    
	    for (int i = 0; i < 8; i++) {
	    	String tempPath = "/monster/ishigami_phase2_summon" + "_right_" + i;
	    	attackRightSkill3[i] = setup(tempPath, gp.tileSize*5 + 24, gp.tileSize*6+24);
	    }
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Ishigami: Chào mừng ngươi bước vào trận chiến tranh\nngôi vương!";
		dialogues[0][1] = "Ishigami: Không ngờ ta lại có thể giết ngươi thêm một\nlần nữa!";
		dialogues[0][2] = "Ishigami: Đừng nghĩ rằng chỉ có mình ngươi là mạnh lên\nta sẽ cho ngươi nếm thử sức mạnh mới của ta!"; 
		dialogues[0][3] = "Ishigami: Đây sẽ là trận chiến của riêng hai ta, con\nGriffon đi theo ngươi vốn do ta tạo ra."; 
		dialogues[0][4] = "Ishigami: Nhiệm vụ dẫn hắn đến đây đã hoàn thành, đến\nlúc ngươi biến mất được rồi đó!";
		
		dialogues[1][0] = "Ishigami: Ta công nhận ngươi thật sự rất mạnh, ta không\nnhớ đã bao lâu rồi mới phải dùng đến hình dạng này\nnữa.";
		dialogues[1][1] = "Ishigami: Dù ngày hôm nay ngươi có chết cũng đừng nản\nkhông có mấy ai đủ khả năng khiến ta ra nông nỗi này\nđâu";
		dialogues[1][2] = "Ishigami: Hãy tiếp tục trận chiến nào! Ta sẽ không nương\ntay nữa đâu";
	}
	
	public void setAction() {
		
		skillSummonCounter++;
		if(inRage == false && life <= maxLife/2) {
			getDeadImage();
			maxFrameAttack = 19;
			endPhase1 = true;
			inRage = true;	
			speed = 0;
			attacking = false;
		} 
		if(startPhase2 == true && beingPhase == false) {
			beingPhase = true;
			numOfDirecion = 4;
			getAttackImagePhase2();
			getImage();
			direction = "down";
			frameDelay = 2;
			numRange = 6;
			speed = 3;
			attack += 20;
			defense += 20;
			nextSkill = 0;
			skillSummonCounter = 0;
			setDefaultSolidArea(144, 0, 96, 144, 90, 90);
			startDialogue(this, 1);
		}
		if(endPhase1 == false || startPhase2 == true) {

			if(getTileDistance(gp.player) < 15) {
				moveTowardPlayer(15);
			}
			else {	
				getRandomDirection(45);
			}
			
			//check if attacks
			if(attacking == false) {
				
				if(!currentDirection.equals(direction) && inRage == true) {
					currentDirection = direction;
					switch (currentDirection) {
						case "up": setDefaultSolidArea(96, 0, 96, 288, 144, 168); break;
						case "down": setDefaultSolidArea(168, 48, 96, 168, 144, 168); break;
						case "left": setDefaultSolidArea(168, 48, 96, 168, 168, 144); break;
						case "right": setDefaultSolidArea(168, 48, 96, 168, 168, 144); break;
					}
				}
				
				if(checkRange(gp.tileSize*numRange, gp.tileSize*numRange)) {
					int i = 0;
					if(inRage == false) {
						
						if(checkRange(gp.tileSize*2, gp.tileSize*2)) {
							nextSkill = 2;
							maxFrameAttack = 11;
							i = new Random().nextInt(60);
							frameDelay = 4;
						}else {
							frameDelay = 6;
							nextSkill = 1;
							maxFrameAttack = 12;
							i = new Random().nextInt(90);
						}
					}else {
						nextSkill = 1;
						maxFrameAttack = 12;
						i = new Random().nextInt(90);
					}
					if(skill != nextSkill) {
						skill = nextSkill;
						if(inRage == false) {
							if(nextSkill == 1) {
								System.arraycopy(attackRightSkill1, 0, attackRight, 0, attackRightSkill1.length);
								System.arraycopy(attackLeftSkill1, 0, attackLeft, 0, attackLeftSkill1.length);
								
							}
							if(nextSkill == 2) {
								System.arraycopy(attackRightSkill2, 0, attackRight, 0, attackRightSkill2.length);
								System.arraycopy(attackLeftSkill2, 0, attackLeft, 0, attackLeftSkill2.length);
							}
						}else {
							System.arraycopy(attackUpSkill1, 0, attackUp, 0, attackUpSkill1.length);
							System.arraycopy(attackDownSkill1, 0, attackDown, 0, attackDownSkill1.length);
							System.arraycopy(attackRightSkill1, 0, attackRight, 0, attackRightSkill1.length);
							System.arraycopy(attackLeftSkill1, 0, attackLeft, 0, attackLeftSkill1.length);
						}
					}
					if(i == 0) {
						attacking = true;
					}
				}
			}
			if(skillSummonCounter == 3600) {
				nextSkill = 3;
				maxFrameAttack = 8;
				attacking = true;
				if(skill != nextSkill) {
					skill = nextSkill;
					if(inRage == false) {
						if(nextSkill == 3) {
							System.arraycopy(attackRightSkill3, 0, attackRight, 0, attackRightSkill3.length);
							System.arraycopy(attackLeftSkill3, 0, attackLeft, 0, attackLeftSkill3.length);
						}
					}else {
						System.arraycopy(attackUpSkill3, 0, attackUp, 0, attackUpSkill3.length);
						System.arraycopy(attackDownSkill3, 0, attackDown, 0, attackDownSkill3.length);
						System.arraycopy(attackRightSkill3, 0, attackRight, 0, attackRightSkill3.length);
						System.arraycopy(attackLeftSkill3, 0, attackLeft, 0, attackLeftSkill3.length);
					}
				}
				skillSummonCounter = 0;
			}	
		}
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
	}

	public void checkDrop() {
		
		gp.ishigamiBattleOn = false;
		Progress.ishigamiDefeated = true;
		for(int i = 0; i < gp.monster[gp.currentMap].length; i++) {
			if(gp.monster[gp.currentMap][i] != null) {
				gp.monster[gp.currentMap][i].dying = true;
			}
		}
		gp.npc[4][2] = null;
		gp.aSetter.npcIndex = 2;
		gp.aSetter.mapNum = 4;
		gp.aSetter.setUpNPC(NPC_Mage.class, worldX/gp.tileSize, worldY/gp.tileSize);
		gp.npc[4][2].speak();
		gp.aSetter.objIndex = 29;
		gp.aSetter.setUpObject(OBJ_Portal.class, 0, 9);
	}
}

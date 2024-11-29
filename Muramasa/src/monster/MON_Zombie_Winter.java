package monster;

import data.Progress;
import entity.Monster;
import main.GamePanel;
import object.OBJ_Key;

public class MON_Zombie_Winter extends Monster{

	GamePanel gp;
	public static final String monName  = "zombie winter";
	
	public MON_Zombie_Winter(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		eWidth = gp.tileSize * 7;
		eHeight = gp.tileSize * 7;
		maxLife = 1500;
		life = maxLife;
		attack = 150;
		defense = 15;
		exp = 5000;
		knockBackPower = 5;
		
		maxFrameAttack = 24;
		frameDelay = 1;
		
		setDefaultSolidArea(144, 128, 46, 46, 90, 72);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/zombie_winter_up_1", eWidth, eHeight);
		up2 = setup("/monster/zombie_winter_up_2", eWidth, eHeight);
		down1 = setup("/monster/zombie_winter_down_1", eWidth, eHeight);
		down2 = setup("/monster/zombie_winter_down_2", eWidth, eHeight);
		left1 = setup("/monster/zombie_winter_left_1", eWidth, eHeight);
		left2 = setup("/monster/zombie_winter_left_2", eWidth, eHeight);
		right1 = setup("/monster/zombie_winter_right_1", eWidth, eHeight);
		right2 = setup("/monster/zombie_winter_right_2", eWidth, eHeight);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_winter_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, eWidth, eHeight);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_winter_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, eWidth, eHeight);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_winter_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, eWidth, eHeight);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_winter_attack_right_" + i;
	    	attackRight[i] = setup(tempPath, eWidth, eHeight);
	    }
	}

	public void setAction() {
		
		if(onPath == true) {
			
			//check if it stops chasing
			checkStopChasingOrNot(gp.player, 15, 100);	
			
			//search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
		}
		else {
			//check if it start chasing
			checkStartChasingOrNot(gp.player, 5, 100);

			//get random direction
			getRandomDirection(60);
		}
		
		//check if attacks
		if(attacking == false) {
			checkAttackOrNot(30, gp.tileSize*3, gp.tileSize*2);
		}
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		if(gp.currentMap == 2) {
			Progress.zombieWinterDefeated = true;
			dropItem(new OBJ_Key(gp));
		}else {
			System.out.println("hehe");
		}
	}
}

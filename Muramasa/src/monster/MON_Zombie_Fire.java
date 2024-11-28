package monster;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Bone;

public class MON_Zombie_Fire extends Monster{

	GamePanel gp;
	public static final String monName  = "zombie fire";
	
	public MON_Zombie_Fire(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 3000;
		life = maxLife;
		attack = 180;
		defense = 20;
		exp = 20000;
		knockBackPower = 5;
		maxFrameAttack = 24;
		frameDelay = 2;
		
		motion1_duration = 30;
		motion2_duration = 65;
		
		setDefaultSolidArea(100, 96, 46, 46, 64, 48);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/zombie_fire_up_1", gp.tileSize*5, gp.tileSize*5);
		up2 = setup("/monster/zombie_fire_up_2", gp.tileSize*5 , gp.tileSize*5);
		down1 = setup("/monster/zombie_fire_down_1", gp.tileSize*5, gp.tileSize*5);
		down2 = setup("/monster/zombie_fire_down_2", gp.tileSize*5, gp.tileSize*5);
		left1 = setup("/monster/zombie_fire_left_1", gp.tileSize*5, gp.tileSize*5);
		left2 = setup("/monster/zombie_fire_left_2", gp.tileSize*5, gp.tileSize*5);
		right1 = setup("/monster/zombie_fire_right_1", gp.tileSize*5, gp.tileSize*5);
		right2 = setup("/monster/zombie_fire_right_2", gp.tileSize*5, gp.tileSize*5);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_fire_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, gp.tileSize*5, gp.tileSize*5);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_fire_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*5, gp.tileSize*5);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_fire_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, gp.tileSize*5, gp.tileSize*5);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/zombie_fire_attack_right_" + i;
	    	attackRight[i] = setup(tempPath, gp.tileSize*5, gp.tileSize*5);
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
			getRandomDirection(120);
		}
		
		//check if attacks
		if(attacking == false) {
			checkAttackOrNot(30, gp.tileSize*2, gp.tileSize);
		}
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		
		if(gp.currentMap == 3) {
			dropItem(new OBJ_Bone(gp));
		}
	}
	
}

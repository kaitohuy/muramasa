package monster;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Skull;

public class MON_Skeleton extends Monster{

	GamePanel gp;
	public static final String monName  = "skeleton";
	
	public MON_Skeleton(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 2500;
		life = maxLife;
		attack = 180;
		defense = 30;
		exp = 10000;
		
		knockBackPower = 5;
		maxFrameAttack = 3;
		frameDelay = 10;
		
		setDefaultSolidArea(48, 48, 48, 46, 72, 72);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/skeletonlord_up_1", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/monster/skeletonlord_up_2", gp.tileSize*3 , gp.tileSize*3);
		down1 = setup("/monster/skeletonlord_down_1", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/monster/skeletonlord_down_2", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/monster/skeletonlord_left_1", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/monster/skeletonlord_left_2", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/monster/skeletonlord_right_1", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/monster/skeletonlord_right_2", gp.tileSize*3, gp.tileSize*3);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/skeletonlord_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*6);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/skeletonlord_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*6);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/skeletonlord_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, gp.tileSize*6, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/skeletonlord_attack_right_" + i;
	    	attackRight[i] = setup(tempPath, gp.tileSize*6, gp.tileSize*3);
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
			checkStartChasingOrNot(gp.player, 5, 50);

			//get random direction
			getRandomDirection(60);
		}
		
		//check if attacks
		if(attacking == false) {
			checkAttackOrNot(30, gp.tileSize*3, gp.tileSize*3);
		}
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		
		if(gp.currentMap == 3) {
			dropItem(new OBJ_Skull(gp));
		}
	}
}

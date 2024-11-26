package monster;

import data.Progress;
import entity.Monster;
import main.GamePanel;
import object.OBJ_Key;

public class MON_Orc extends Monster{

	GamePanel gp;
	public static final String monName  = "orc";
	
	public MON_Orc(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 500;
		life = maxLife;
		attack = 70;
		defense = 10;
		exp = 500;
		knockBackPower = 5;
		maxFrameAttack = 8;
		frameDelay = 4;
		
		setDefaultSolidArea(48, 48, 48, 36, 64, 64);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/orc_up_1", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/monster/orc_up_2", gp.tileSize*3 , gp.tileSize*3);
		down1 = setup("/monster/orc_down_1", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/monster/orc_down_2", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/monster/orc_left_1", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/monster/orc_left_2", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/monster/orc_right_1", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/monster/orc_right_2", gp.tileSize*3, gp.tileSize*3);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc_attack_right_" + i;
	    	attackRight[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
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
			checkAttackOrNot(15, gp.tileSize*2, gp.tileSize*2);
		}
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
	}
	
	public void checkDrop() {
		Progress.orcDefeated = true;
		dropItem(new OBJ_Key(gp));
	}
}

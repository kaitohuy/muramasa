package monster;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Diamond;
import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;

public class MON_Orc1 extends Monster{

	GamePanel gp;
	public static final String monName  = "orc1";
	
	public MON_Orc1(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 500;
		life = maxLife;
		attack = 50;
		defense = 5;
		exp = 100;
		knockBackPower = 5;
		maxFrameAttack = 8;
		frameDelay = 4;
		
		setDefaultSolidArea(56, 48, 36, 36, 48, 48);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/orc1_up_1", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/monster/orc1_up_2", gp.tileSize*3 , gp.tileSize*3);
		down1 = setup("/monster/orc1_down_1", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/monster/orc1_down_2", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/monster/orc1_left_1", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/monster/orc1_left_2", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/monster/orc1_right_1", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/monster/orc1_right_2", gp.tileSize*3, gp.tileSize*3);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc1_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc1_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc1_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/orc1_attack_right_" + i;
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
//		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100);
		
		//set the monster drop
		if(i < 20) {
			dropItem(new OBJ_Diamond(gp));
		}
		if(i >= 20 && i < 60) {
			dropItem(new OBJ_Posion_Red(gp));
		}
		if(i >= 60 && i < 100) {
			dropItem(new OBJ_Posion_Blue(gp));
		}
	}
}

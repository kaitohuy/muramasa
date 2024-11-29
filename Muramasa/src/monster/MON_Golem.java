package monster;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Diamond;
import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;

public class MON_Golem extends Monster{

	GamePanel gp;
	public static final String monName  = "golem";
	
	public MON_Golem(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 1000;
		life = maxLife;
		attack = 150;
		defense = 10;
		exp = 500;
		
		knockBackPower = 5;
		maxFrameAttack = 8;
		frameDelay = 4;
		
		setDefaultSolidArea(48, 64, 48, 36, 56, 56);
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/golem_up_1", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/monster/golem_up_2", gp.tileSize*3 , gp.tileSize*3);
		down1 = setup("/monster/golem_down_1", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/monster/golem_down_2", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/monster/golem_left_1", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/monster/golem_left_2", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/monster/golem_right_1", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/monster/golem_right_2", gp.tileSize*3, gp.tileSize*3);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/golem_attack_up_" + i;
	    	attackUp[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/golem_attack_down_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/golem_attack_left_" + i;
	    	attackLeft[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
	    }
	    
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/monster/golem_attack_right_" + i;
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
		if(i < 50) {
			dropItem(new OBJ_Diamond(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Posion_Red(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_Posion_Blue(gp));
		}
	}
}


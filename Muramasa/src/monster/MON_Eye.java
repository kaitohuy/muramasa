package monster;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;

public class MON_Eye extends Monster{

	GamePanel gp;
	public static final String monName  = "eye";
	
	public MON_Eye(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_crep;
		name = monName;
		eWidth = (int)(gp.tileSize*1.5);
		eHeight = (int)(gp.tileSize*1.5);
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 2000;
		life = maxLife;
		attack = 170;
		defense = 20;
		exp = 20000;
		knockBackPower = 5;
		numOfDirecion = 2;
		
		setDefaultSolidArea(18, 12, 32, 32, 64, 64);
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/eye_up_1", eWidth, eHeight);
		up2 = setup("/monster/eye_up_2", eWidth, eHeight);
		down1 = setup("/monster/eye_down_1", eWidth, eHeight);
		down2 = setup("/monster/eye_down_2", eWidth, eHeight);
		left1 = setup("/monster/eye_left_1", eWidth, eHeight);
		left2 = setup("/monster/eye_left_2", eWidth, eHeight);
		right1 = setup("/monster/eye_right_1", eWidth, eHeight);
		right2 = setup("/monster/eye_right_2", eWidth, eHeight);
		
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
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction;
//		onPath = true;
	}

	public void checkDrop() {
		int i = new Random().nextInt(100);
		
		//set the monster drop
		if(i < 50) {
			dropItem(new OBJ_Posion_Red(gp));
		}
		if(i >= 50 && i < 100) {
			dropItem(new OBJ_Posion_Blue(gp));
		}
	}
}

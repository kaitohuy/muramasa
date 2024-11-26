package monster;

import java.util.Random;

import entity.Monster;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Diamond;
import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;

public class MON_Demon_Fire extends Monster{

	GamePanel gp;
	public static final String monName  = "demon";
	
	public MON_Demon_Fire(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_crep;
		name = monName;
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 500;
		life = maxLife;
		attack = 120;
		defense = 5;
		exp = 1000;
		knockBackPower = 5;
		numOfDirecion = 2;
		
		setDefaultSolidArea(18, 12, 32, 32, 64, 64);
		getImage();
	}
	
	public void getImage() {

		left1 = setup("/monster/demon_left_0", gp.tileSize+12, gp.tileSize+6);
		left2 = setup("/monster/demon_left_1", gp.tileSize+12, gp.tileSize+6);
		right1 = setup("/monster/demon_right_0", gp.tileSize+12, gp.tileSize+6);
		right2 = setup("/monster/demon_right_1", gp.tileSize+12, gp.tileSize+6);
		up1 = left1;
		up2 = left2;
		down1 = right1;
		down2 = right2;
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
		onPath = true;
	}

	public void checkDrop() {
		int i = new Random().nextInt(100);
		
		//set the monster drop
		if(i < 25) {
			dropItem(new OBJ_Coin(gp));
		}
		if(i >= 25 && i < 50) {
			dropItem(new OBJ_Posion_Red(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Posion_Blue(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_Diamond(gp));
		}
	}
}

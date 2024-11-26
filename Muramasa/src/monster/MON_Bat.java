package monster;

import java.util.Random;
import entity.Monster;
import main.GamePanel;

import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;

public class MON_Bat extends Monster{

	GamePanel gp;
	public static final String monName  = "Bat";
	
	public MON_Bat(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		type = type_crep;
		name = monName;
		eWidth = (int)(gp.tileSize*1.5);
		eHeight = (int)(gp.tileSize*1.5);
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 1000;
		life = maxLife;
		attack = 150;
		defense = 10;
		exp = 10000;

		setDefaultSolidArea(18, 12, 32, 32, 64, 64);
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/bat_up_1", eWidth, eHeight);
		up2 = setup("/monster/bat_up_2", eWidth, eHeight);
		down1 = setup("/monster/bat_down_1", eWidth, eHeight);
		down2 = setup("/monster/bat_down_2", eWidth, eHeight);
		left1 = setup("/monster/bat_left_1", eWidth, eHeight);
		left2 = setup("/monster/bat_left_2", eWidth, eHeight);
		right1 = setup("/monster/bat_right_1", eWidth, eHeight);
		right2 = setup("/monster/bat_right_2", eWidth, eHeight);
		
	}

	public void setAction() {
		
		getRandomDirection(10);
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
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

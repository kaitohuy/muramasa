package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Util extends Projectile{

	public static final String objName = "util";
	
	GamePanel gp;
	
	public OBJ_Util(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = type_skill;
		speed = 0;
		maxLife = 90;
		life = maxLife;
		attack = 50;
		useCost = 10;
		knockBackPower = 5;
		alive = false;
		maxFrameAttack = 28;
		frameDelay = 1;
		
		eWidth = gp.tileSize*6;
		eHeight = gp.tileSize*6;
		setDefaultSolidArea(0, 0, eWidth, eHeight, eWidth, eHeight);
		attacking = true;
		getAttackImage();
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/util_" + i;
	    	attackUp[i] = setup(tempPath, eWidth, eHeight);
	    	attackRight[i] = attackUp[i];
	    	attackLeft[i] = attackUp[i];
	    	attackDown[i] = attackUp[i];
	    }
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
}

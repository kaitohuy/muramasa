package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{

	public static final String objName =  "coin";
	
	GamePanel gp;
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_pickupOnly;
		name = objName;
		attacking = true;
		maxFrameAttack = 10;
		frameDelay = 4;
		life = 1;
		value = 20;
		getAttackImage();
	}
	
	private void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/coin_" + i;
	    	attackRight[i] = setup(tempPath, gp.tileSize+12, gp.tileSize+12);
	    	attackLeft[i] = attackRight[i];
	    	attackUp[i] = attackRight[i];
	    	attackDown[i] = attackRight[i];
	    }
	}
	
	public boolean use(Entity entity) {
			
		gp.playSe(1);
		gp.ui.addMessage("Coin + " + value);
		entity.coin += value;
	
		return true;
	}
}

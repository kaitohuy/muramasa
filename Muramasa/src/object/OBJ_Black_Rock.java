package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Black_Rock extends Entity{

public static final String objName = "black door";
	
	GamePanel gp;
	
	public OBJ_Black_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize*5;
		eHeight = gp.tileSize*5;
		type = type_obstacle;
		direction = "";
		name = objName;
		life = 1;
		maxFrameAttack = 6;
		frameDelay = 2;
		attacking = true;
		setDefaultSolidArea(24, 24, eWidth, eHeight, 0, 0);
		collision = true;
	
		getAttackImage();
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/black_rock_" + i;
		    attackRight[i] = setup(tempPath, eWidth, eHeight);
	    }
	}
}
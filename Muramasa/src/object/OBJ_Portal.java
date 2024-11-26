package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Portal extends Entity{

	public static final String objName = "portal";
	
	GamePanel gp;
	
	public OBJ_Portal(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_obstacle;
		name = objName;
		standing = true;
		attacking = true;

		maxFrameAttack = 15;
		frameDelay = 4;
		
		collision = true;
		getStandingImage();
		setDefaultSolidArea(96, 48, 96, 164, 0, 0);
	}
	
	public void getStandingImage() {
		for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/portal_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*6, gp.tileSize*6);
		}
	}
	
	public void interact() {
		
		gp.gameState = gp.cutSceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
	}
}

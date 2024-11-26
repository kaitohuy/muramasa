package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity{

	public static final String objName = "blue heart";
			
	GamePanel gp;
	
	public OBJ_BlueHeart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_pickupOnly;
		name  = objName;
		down1 = setup("/objects/blueheart", eWidth, eHeight);
		
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "You pick up a beauitiful blue gem.";
		dialogues[0][1] = "You find the Blue Heart, the legendary treasure!";
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutSceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
				
		return true;
	}
}

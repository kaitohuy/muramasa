package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bone_Head extends Entity{

	public static final String objName = "bone_head";
	
	GamePanel gp;
	
	public OBJ_Bone_Head(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/bone_head", eWidth, eHeight);
		down1 = image;
		collision = false;
		
		setDefaultSolidArea(16, 16, gp.tileSize, gp.tileSize, 0, 0);
		
	}

}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bone_Body extends Entity{

	public static final String objName = "bone_body";
	
	GamePanel gp;
	
	public OBJ_Bone_Body(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/bone_body", eWidth, eHeight);
		down1 = image;
		collision = false;
		
		setDefaultSolidArea(16, 16, gp.tileSize*2, gp.tileSize*2, 0, 0);
		
	}

}

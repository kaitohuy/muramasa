package object;

import entity.Bridge;
import main.GamePanel;

public class OBJ_Stair extends Bridge{

	public static final String objName = "stair";
	
	GamePanel gp;
	
	public OBJ_Stair(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/stair", eWidth, eHeight);
		down1 = image;
		collision = false;
		
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
	}

}

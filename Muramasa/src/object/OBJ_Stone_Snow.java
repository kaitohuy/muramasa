package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Stone_Snow extends Entity{

	public static final String objName = "stone_snow";
	
	GamePanel gp;
	
	public OBJ_Stone_Snow(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/stone_snow", gp.tileSize*2, gp.tileSize*2);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(16, 48, 64, 24, 0, 0);
		
	}

}

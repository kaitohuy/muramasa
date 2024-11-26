package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SnowMan extends Entity{

	public static final String objName = "snowman";
	
	GamePanel gp;
	
	public OBJ_SnowMan(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_obstacle;
		name = objName;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize*2;
		image = setup("/objects/snowman", gp.tileSize, gp.tileSize*2);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(4, gp.tileSize, gp.tileSize - 8, gp.tileSize - 8, 0, 0);
		
	}

}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Crystal_Snow extends Entity{

	public static final String objName = "crystal";
	
	GamePanel gp;
	
	public OBJ_Crystal_Snow(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/crystal", gp.tileSize*2, gp.tileSize*2);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(gp.tileSize - 16, gp.tileSize, 32, 32, 0, 0);
		
	}

}

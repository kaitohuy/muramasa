package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Eating_Plant extends Entity{

	public static final String objName = "eating_plant";
	
	GamePanel gp;
	
	public OBJ_Eating_Plant(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/eating_plant", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(8, 8, 64, 64, 0, 0);
		
	}

}

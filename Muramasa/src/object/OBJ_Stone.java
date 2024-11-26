package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Stone extends Entity{

	public static final String objName = "stone";
	
	GamePanel gp;
	
	public OBJ_Stone(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/stone", gp.tileSize*2, gp.tileSize*2);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(12, 12, 72, 64, 0, 0);
		
	}

}

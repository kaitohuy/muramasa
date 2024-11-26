package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ocean extends Entity{

	public static final String objName = "ocean";
	
	GamePanel gp;
	
	public OBJ_Ocean(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/ocean", gp.tileSize*2, gp.tileSize*2);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(12, 12, 72, 64, 0, 0);
		
	}

}

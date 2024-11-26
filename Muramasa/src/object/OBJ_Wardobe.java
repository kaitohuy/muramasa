package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wardobe extends Entity{

	public static final String objName = "wardobe";
	
	GamePanel gp;
	
	public OBJ_Wardobe(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/wardobe", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
		
	}

}

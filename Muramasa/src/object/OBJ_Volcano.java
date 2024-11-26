package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Volcano extends Entity{

	public static final String objName = "volcano";
	
	GamePanel gp;
	
	public OBJ_Volcano(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*4;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/volcano", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(48, 48, 40, 32, 0, 0);
		
	}

}

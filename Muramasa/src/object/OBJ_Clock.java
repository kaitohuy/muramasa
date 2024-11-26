package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Clock extends Entity{

	public static final String objName = "clock";
	
	GamePanel gp;
	
	public OBJ_Clock(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*4;
		eHeight = gp.tileSize*4;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/clock", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
		
	}

}

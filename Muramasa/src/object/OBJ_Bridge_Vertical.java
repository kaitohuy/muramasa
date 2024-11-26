package object;

import entity.Bridge;
import main.GamePanel;

public class OBJ_Bridge_Vertical extends Bridge{

	public static final String objName = "bridge vertical";
	
	GamePanel gp;
	
	public OBJ_Bridge_Vertical(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*6;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/bridge_vertical", eWidth, eHeight);
		down1 = image;
		collision = false;
		
	}

}

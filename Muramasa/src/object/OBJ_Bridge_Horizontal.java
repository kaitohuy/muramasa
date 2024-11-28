package object;

import entity.Bridge;
import main.GamePanel;

public class OBJ_Bridge_Horizontal extends Bridge{

	public static final String objName = "bridge horizontal";
	
	GamePanel gp;
	
	public OBJ_Bridge_Horizontal(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*6;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/bridge_horizontal", eWidth, eHeight);
		down1 = image;
		collision = false;
		
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
	}

}

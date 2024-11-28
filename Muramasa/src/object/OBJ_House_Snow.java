package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House_Snow extends Entity{

	public static final String objName = "house_snow";
	
	GamePanel gp;
	
	public OBJ_House_Snow(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*6;
		eHeight = gp.tileSize*6;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/house_snow",eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(48, 112, eWidth - 96, eHeight - 112, 0, 0);
		
	}

	public void interact() {
		
		gp.eHandler.teleport(5, 10, 9, gp.indoor, "up");
	}

}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Table extends Entity{

	public static final String objName = "table";
	
	GamePanel gp;
	
	public OBJ_Table(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*2;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/table", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
		
	}
	
	public void interact() {
		gp.npc[5][0].speak();
	}
}

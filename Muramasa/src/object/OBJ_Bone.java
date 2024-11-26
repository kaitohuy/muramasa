package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bone extends Entity{

	public static final String objName = "bone";
	
	GamePanel gp;
	
	public OBJ_Bone(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_consumable;
		name  = objName;
		down1 = setup("/objects/bone", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nDùng để mở cổng tới\nchỗ dragon.";

		price = 20;
		stackable = true;
		
	}
}

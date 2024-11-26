package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Skull extends Entity{

	public static final String objName = "skull";
	
	GamePanel gp;
	
	public OBJ_Skull(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_consumable;
		name  = objName;
		down1 = setup("/objects/skull", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nDùng để mở cổng tới\nchỗ dragon.";

		price = 20;
	}
}

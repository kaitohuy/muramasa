package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{

	public static final String objName = "lantern";
	
	GamePanel gp;
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_light;
		name = objName;
		down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
		description = "[lantern]\nÁnh dương tỏa sáng soi\nlối nhân gian.";
		price = 50;
		lightRadus = 350;
	}
}

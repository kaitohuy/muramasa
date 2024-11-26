package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv3 extends Entity{

	public static final String objName = "fire_armor";
	
	public OBJ_Armor_Lv3(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		swordLevel = 3;
		type = type_armor;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		name = objName;
		down1 = setup("/objects/armor_lv3", gp.tileSize, gp.tileSize);
		defenseValue = 50;
		description = "[" + name + "]\nLửa không có sợ\nlửa.";

		price = 200;
	}

}

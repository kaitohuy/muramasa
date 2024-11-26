package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv2 extends Entity{

	public static final String objName = "freeze_armor";
	
	public OBJ_Armor_Lv2(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		swordLevel = 2;
		type = type_armor;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		name = objName;
		down1 = setup("/objects/armor_lv2", gp.tileSize, gp.tileSize);
		defenseValue = 20;
		description = "[" + name + "]\nCái lạnh ngàn năm\ncũng vô dụng.";

		price = 100;
	}

}

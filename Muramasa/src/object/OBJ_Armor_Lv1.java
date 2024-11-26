package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv1 extends Entity{

	public static final String objName = "iron_armor";
	
	public OBJ_Armor_Lv1(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		swordLevel = 1;
		type = type_armor;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		name = objName;
		down1 = setup("/objects/armor_lv1", gp.tileSize, gp.tileSize);
		defenseValue = 10;
		description = "[" + name + "]\nkhông một thanh gươm\nnào có thể đả đụng\nđến.";

		price = 50;
	}

}

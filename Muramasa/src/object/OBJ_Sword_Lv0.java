package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Lv0 extends Entity{

	public static final String objName = "wood_sword";
	
	public OBJ_Sword_Lv0(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_sword;
		swordLevel = 0;
		name = objName;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		down1 = setup("/objects/sword_lv0", gp.tileSize, gp.tileSize);
		attackvalue = 5;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\n	Mảnh gỗ trong tay\nthiên tài cũng là\nthanh gươm sắc bén.";
		
		price = 10;
		knockBackPower = 3;
		motion1_duration = 5;
		motion2_duration = 25;
	}

}

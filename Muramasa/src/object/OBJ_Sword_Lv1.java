package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Lv1 extends Entity{

	public static final String objName = "nature_sword";
	
	public OBJ_Sword_Lv1(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		swordLevel = 1;
		type = type_sword;
		name = objName;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		down1 = setup("/objects/sword_lv1", gp.tileSize, gp.tileSize);
		attackvalue = 10;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\nThiên nhiên là nguồn\nsức mạnh đáng sợ\nnhất.";
		
		price = 50;
		knockBackPower = 3;
		motion1_duration = 5;
		motion2_duration = 25;
	}
	
	
}

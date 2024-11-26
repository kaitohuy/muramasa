package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public static final String objName = "Normal Sword";
	
	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_sword;
		name = objName;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackvalue = 3;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\nAn old sword.";
		
		price = 5;
		knockBackPower = 3;
		motion1_duration = 5;
		motion2_duration = 25;
	}
	
	
}

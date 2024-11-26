package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{

	public static final String objName = "tent";
	
	GamePanel gp;
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nYou can sleep until\nnextmorning.";
		price = 50;
		stackable = true;
		
	}

	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.playSe(14);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		gp.player.getSleepingImage(down1);
		return true; //one then disappear
		//any number of time return false;
		
	}
	
}

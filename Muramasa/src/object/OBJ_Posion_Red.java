package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Posion_Red extends Entity{

	public static final String objName = "posion red";
			
	GamePanel gp;
	
	public OBJ_Posion_Red(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		value = 10;
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/posion_red", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nHồi phục " + value + " điểm sinh"
				+ "mệnh.";

		price = 20;
		stackable = true;

	}

	public boolean use(Entity entity) {
		
		gp.gameState = gp.playState;
		value = gp.player.maxLife/5;
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSe(2);
		gp.ui.addMessage("Life + " + value);
		
		return true;
	}
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Posion_Blue extends Entity{

	public static final String objName = "posion blue";
	
	GamePanel gp;

	public OBJ_Posion_Blue(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		value = 2;
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/posion_blue", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nHồi phục " + value + " điểm năng\nlượng.";
		
		price = 20;
		stackable = true;	

	}

	public boolean use(Entity entity) {
		
		gp.gameState = gp.playState;
		value = gp.player.maxMana/5;
		entity.mana += value;
		if(gp.player.mana > gp.player.maxMana) {
			gp.player.mana = gp.player.maxMana;
		}
		gp.playSe(2);
		gp.ui.addMessage("Mana + " + value);
		
		
		return true;
	}
}

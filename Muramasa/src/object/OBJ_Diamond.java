package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Diamond extends Entity{

	public static final String objName = "diamond";
	
	GamePanel gp;

	public OBJ_Diamond(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_pickupOnly;
		value = 100;
		name = objName;
		down1 = setup("/objects/diamond", gp.tileSize, gp.tileSize);
	
	}

	public boolean use(Entity entity) {
		
		gp.playSe(2);
		gp.ui.addMessage("Coin + " + value);
		entity.coin += value;
		
		return true;
	}
}

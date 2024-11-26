package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{

	public static final String objName = "Mana Crystal";
	
	GamePanel gp;

	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		type = type_pickupOnly;
		value = 1;
		name = objName;
		down1 = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
	
	}

	public boolean use(Entity entity) {
		
		gp.playSe(2);
		gp.ui.addMessage("Mana + " + value);
		entity.mana += value;
		
		return true;
	}
}

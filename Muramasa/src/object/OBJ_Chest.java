package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{

	public static final String objName = "chest";
	
	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/chest", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Bạn đã nhận được vật phẩm:  " + loot.name + " !" + "\n...Nhưng bạn không đủ chỗ chứa để mang theo";
		dialogues[1][0] = "Bạn đã nhận được vật phẩm: " + loot.name + " !";
		dialogues[2][0] = "Tham lam, hết rồi bạn ơi!";
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
		
	}
	
	public void interact() {
		
		if(opened == false) {
			gp.playSe(3);
			
			if(gp.player.canObtainItem(loot) == false) {
				startDialogue(this, 0);
			}else {
				startDialogue(this, 1);
				down1 = image2;
				opened = true;
			}
		}else {
			startDialogue(this, 2);
		}
	}
}

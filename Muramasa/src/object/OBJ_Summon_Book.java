package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Summon_Book extends Entity{

	public static final String objName = "summon book";
			
	GamePanel gp;
	
	public OBJ_Summon_Book(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		type = type_book;
		name = objName;
		down1 = setup("/objects/summon_book", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\n Hướng dẫn triệu hồi\nPhoenix";

		price = 5;
		stackable = true;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Bạn đã đã học được cách gọi Phoenix, để triệu hồi\nnhấn phím 'O' hoặc '5'.";
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.playState;
		gp.player.canUseSkill3 = true;
		startDialogue(this, 0);
		return true;
	}
}

package object;

import data.Progress;
import entity.Entity;
import main.GamePanel;

public class OBJ_Gate_Dungeon extends Entity{

	public static final String objName = "gate_dungeon";
	
	GamePanel gp;
	
	public OBJ_Gate_Dungeon(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*4 + 24;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/gate_dungeon", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDialogue();
		setDefaultSolidArea(24, 0, 96, 96, 0, 0);
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Ryujin: Hahaha, lấy được đầu của ta trước đi rồi hẵng\ntính chuyện đi tiếp, hỡi tên con người yếu đuối đáng\nthương kia ơi.";

		dialogues[1][0] = "Hahaha, Khá khen cho nhà ngươi khi có thể hạ gục con\nrồng đó.";
		dialogues[1][1] = "Nhưng cho ngươi biết 1 điều rằng, ngài ấy có thể làm\nđiều đó chỉ với một chiêu trong khi ngươi phải chật vật\nnãy giờ";
		dialogues[1][2] = "Mau tiến tới hầm ngục bên dưới đi, ngài ấy đang đợi\nngươi đó hahaha";
	}
	
	public void interact() {
		if(Progress.dragonDefeated == false) {
			startDialogue(this, 1);
			
		}else {
			startDialogue(this, 0);
		}
	}
}

package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

	public static final String npcName = "Dummy";
	
	public PlayerDummy(GamePanel gp) {
		super(gp);
		
		name = npcName;
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/player/king_up_1", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		up2 = setup("/player/king_up_2", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		down1 = setup("/player/king_down_1", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		down2 = setup("/player/king_down_2", (int)(gp.tileSize*1.8), (int)(gp.tileSize*1.8));
		left1 = setup("/player/king_left_1", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		left2 = setup("/player/king_left_2", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		right1 = setup("/player/king_right_1", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		right2 = setup("/player/king_right_2", (int)(gp.tileSize*1.8) + 8, (int)(gp.tileSize*1.8));
		
	}
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Demon_Door extends Entity{

	public static final String objName = "door";
	
	GamePanel gp;
	
	public OBJ_Demon_Door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize*5;
		eHeight = gp.tileSize*5;
		type = type_obstacle;
		opened = false;
		name = objName;
		image = setup("/objects/door_close", eWidth, eHeight);
		image2 = setup("/objects/door_open", eWidth, eHeight);
		image3 = setup("/objects/door_open_2", eWidth, eHeight);
		down1 = image;
		setDefaultSolidArea(48, 48, eWidth - 96, eHeight - 48, 0, 0);
		collision = true;
		
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Chỉ khi ngươi tiêu diệt hết đám quái thuộc hạ của ta\nthì ngươi mới có thể đi tiêp.";
		dialogues[1][0] = "Đừng có ngạo mạn, mọi thứ chỉ mới bắt đầu thôi. Đi đi\nđám thuộc hạ khác của ta đang đợi ngươi đó hahaha.";
		dialogues[2][0] = "Mang xác thuộc hạ của ta tới đây nếu ngươi muốn\nđi tiếp!";
	}
	
	public void interact() {
		
		if(gp.currentMap != 3) {
			if(opened == true) {
				if(gp.currentMap == 1) {
					gp.eHandler.teleport(2, 25, 46, gp.outside, "up");
				}
				else if(gp.currentMap == 2) {
					gp.eHandler.teleport(3, 24, 48, gp.outside, "up");
				}
			}
			else {
				int itemIndex = gp.player.searchItemInventory("key");
				if(itemIndex != 999) {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}else {
						gp.player.inventory.remove(itemIndex);											
					}
					startDialogue(this, 1);
					gp.playSe(3);
					down1 = image2;
					opened = true;
				}
				else {
					startDialogue(this, 0);
				}
			}
		}else {
			int skullIndex = gp.player.searchItemInventory("skull");
			int boneIndex = gp.player.searchItemInventory("bone");
			
			if(skullIndex != 999 && boneIndex != 999) {
				down1 = image3;
				setDefaultSolidArea(0, 0, 0, 0, 0, 0);
				gp.player.inventory.remove(skullIndex);		
				boneIndex = gp.player.searchItemInventory("bone");
				gp.player.inventory.remove(boneIndex);	
				gp.eHandler.dragonPhase();
				
			}else {
				startDialogue(this, 2);
			}
		}
		
	}

}

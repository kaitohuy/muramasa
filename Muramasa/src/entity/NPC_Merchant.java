package entity;

import main.GamePanel;
import object.OBJ_Armor_Lv0;
import object.OBJ_Armor_Lv1;
import object.OBJ_Armor_Lv2;
import object.OBJ_Armor_Lv3;
import object.OBJ_Lantern;
import object.OBJ_Posion_Blue;
import object.OBJ_Posion_Red;
import object.OBJ_Sword_Lv0;
import object.OBJ_Sword_Lv1;
import object.OBJ_Sword_Lv2;
import object.OBJ_Sword_Lv3;


public class NPC_Merchant extends Entity{
	
	public NPC_Merchant(GamePanel gp) {
		
		super(gp);
		direction = "right";
		name = "merchant";
		speed = 0;
		eWidth = (int)(gp.tileSize*2.5);
		eHeight = (int)(gp.tileSize*2.5);
		getImage();
		setDialogue();
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
		setItem();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/merchant_1", (int)(gp.tileSize*2.5), (int)(gp.tileSize*2.5));
		up2 = setup("/npc/merchant_2", (int)(gp.tileSize*2.5), (int)(gp.tileSize*2.5));
		down1 = up1;
		down2 = up2;
		left1 = up1;
		left2 = up2;
		right1 = up1;
		right2 = up2;
		
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Sứ mệnh gia tộc ta là tạo ra những vũ khí, vật phẩm để\ntiêu diệt quỷ dữ. Tất nhiên cái gì cũng có giá của nó.\nLần này ngươi tới để mua hay bán đây ?";
		dialogues[1][0] = "Tạm biệt, mong người hoàn thành được sứ mệnh của mình!";
		dialogues[2][0] = "Nghèo mà hay đi mua đồ quá, ra ngoài kiếm thiêm đi!";
		dialogues[3][0] = "Túi đồ của ngươi đã đầy rồi, vứt bớt vài thứ đi!";
		dialogues[4][0] = "Ngươi không thể bán món đồ mà mình đang sử dụng được\nhãy tháo nó trước khi bán!";
	}
	
	public void setItem() {
		
		inventory.add(new OBJ_Posion_Red(gp));
		inventory.add(new OBJ_Posion_Blue(gp));
		inventory.add(new OBJ_Sword_Lv0(gp));
		inventory.add(new OBJ_Sword_Lv1(gp));
		inventory.add(new OBJ_Sword_Lv2(gp));
		inventory.add(new OBJ_Sword_Lv3(gp));
		inventory.add(new OBJ_Armor_Lv0(gp));
		inventory.add(new OBJ_Armor_Lv1(gp));
		inventory.add(new OBJ_Armor_Lv2(gp));
		inventory.add(new OBJ_Armor_Lv3(gp));
		inventory.add(new OBJ_Lantern(gp));
	}
	
	public void speak() {

		gp.ui.npc = this;
		gp.gameState = gp.tradeState;

	}
}


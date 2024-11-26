package entity;

import main.GamePanel;

public class NPC_Mage_Red extends Entity{
	
	public NPC_Mage_Red(GamePanel gp) {
		
		super(gp);
		name = "npc_mage_red";
		type = type_npc;
		direction = "down";
		dialogueSet = 0;
		maxFrameAttack = 14;
		standing = true;
		getStandingImage();
		setDialogue();
		onPath= false;
		setDefaultSolidArea(0, 0, 0, 0, 0, 0);
	}
	
	private void getStandingImage() {
		for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/npc/mage_red_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
		}
	}

	public void setDialogue() {
		dialogues[0][0] = "Hello, Senku.";
		dialogues[0][1] = "So you've come to this island to find\nthe treasure?";
		dialogues[0][2] = "I used to be a great wizard but now...\nI'm a bit too old for taking an adventure";
		dialogues[0][3] = "Well, good luck on you.";
	
		dialogues[1][0] = "If you become tired, rest at the water.";
		dialogues[1][1] = "However, the monster reappear if you rest.\nI dont't know why but that's how it works.";
		dialogues[1][1] = "In any case, dont't push yourself too hard";
		
		dialogues[2][0] = "I wonder how to open that door...";
	}

	public void speak() {
	
		startDialogue(this, dialogueSet);
	}
	
}

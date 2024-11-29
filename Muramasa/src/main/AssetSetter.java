package main;

import entity.*;
import monster.*;
import monster.MON_Zombie_Winter;
import object.*;
	public class AssetSetter {

	GamePanel gp;
	public int mapNum = 0;
	public int npcIndex, objIndex, monIndex;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		mapNum = gp.currentMap;
		switch (mapNum) {
			case 1: {
				objIndex= 0;
				
				setUpObject(OBJ_Bridge_Horizontal.class, 8, 42);
				setUpObject(OBJ_Bridge_Vertical.class, 4, 35);
				setUpObject(OBJ_Stair.class, 3, 27);
				setUpObject(OBJ_BigTree.class, 0, 0);
				setUpObject(OBJ_BigTree.class, 0, 23);
				setUpObject(OBJ_BigTree.class, 1, 14);
				setUpObject(OBJ_BigTree.class, 4, 19);
				setUpObject(OBJ_BigTree.class, 6, 2);
				setUpObject(OBJ_BigTree.class, 7, 23);
				setUpObject(OBJ_BigTree.class, 7, 13);
				setUpObject(OBJ_BigTree.class, 11, 26);
				setUpObject(OBJ_BigTree.class, 11, 36);
				setUpObject(OBJ_BigTree.class, 11, 0);
				setUpObject(OBJ_BigTree.class, 13, 19);
				setUpObject(OBJ_BigTree.class, 13, 12);
				setUpObject(OBJ_BigTree.class, 18, 0);
				setUpObject(OBJ_BigTree.class, 21, 23);
				setUpObject(OBJ_BigTree.class, 22, 5);
				setUpObject(OBJ_BigTree.class, 23, 10);
				setUpObject(OBJ_BigTree.class, 27, 37);
				setUpObject(OBJ_BigTree.class, 28, 30);
				setUpObject(OBJ_BigTree.class, 31, 3);
				setUpObject(OBJ_BigTree.class, 35, 45);
				setUpObject(OBJ_BigTree.class, 39, 28);
				setUpObject(OBJ_BigTree.class, 46, 0);
				setUpObject(OBJ_BigTree.class, 47, 12);
				setUpObject(OBJ_BigTree.class, 47, 36);
				setUpObject(OBJ_BigTree.class, 47, 5);

				setUpObject(OBJ_Stone.class, 0, 19);
				setUpObject(OBJ_Stone.class, 4, 5);
				setUpObject(OBJ_Stone.class, 9, 14);
				setUpObject(OBJ_Stone.class, 11, 2);
				setUpObject(OBJ_Stone.class, 14, 5);
				setUpObject(OBJ_Stone.class, 14, 24);
				setUpObject(OBJ_Stone.class, 19, 35);
				setUpObject(OBJ_Stone.class, 33, 4);
				setUpObject(OBJ_Stone.class, 41, 28);
				setUpObject(OBJ_Stone.class, 46, 38);

				setUpObject(OBJ_Demon_Door.class, 35, 0);
				
				gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
				gp.obj[mapNum][objIndex].setLoot(new OBJ_Sword_Lv1(gp));
				gp.obj[mapNum][objIndex].worldX = gp.tileSize*5;
				gp.obj[mapNum][objIndex].worldY = gp.tileSize*15;
				objIndex++;
				
				gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
				gp.obj[mapNum][objIndex].setLoot(new OBJ_Armor_Lv1(gp));
				gp.obj[mapNum][objIndex].worldX = gp.tileSize*29;
				gp.obj[mapNum][objIndex].worldY = gp.tileSize*39;
				objIndex++;
				
				gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
				gp.obj[mapNum][objIndex].setLoot(new OBJ_Lantern(gp));
				gp.obj[mapNum][objIndex].worldX = gp.tileSize*6;
				gp.obj[mapNum][objIndex].worldY = gp.tileSize*48;
				objIndex++;
				break;
			}
			case 2: {
				objIndex = 0;
				
				setUpObject(OBJ_Tree_Snow_1.class, 47,34);
				setUpObject(OBJ_Tree_Snow_1.class, 47,38);
				setUpObject(OBJ_Tree_Snow_1.class, 47,42);
				setUpObject(OBJ_Tree_Snow_1.class, 47,46);
				setUpObject(OBJ_Tree_Snow_1.class, 0,20);
				setUpObject(OBJ_Tree_Snow_1.class, 0,24);
				setUpObject(OBJ_Tree_Snow_1.class, 0,28);
				setUpObject(OBJ_Tree_Snow_1.class, 0,32);
				setUpObject(OBJ_Tree_Snow_1.class, 0,36);
				setUpObject(OBJ_Tree_Snow_1.class, 0,40);
				setUpObject(OBJ_Tree_Snow_1.class, 0,44);
				setUpObject(OBJ_Tree_Snow_1.class, 1, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 5, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 9, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 13, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 17, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 28, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 32, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 36, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 40, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 44, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 48, 46);
				setUpObject(OBJ_Tree_Snow_1.class, 0, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 4, 47); 
				setUpObject(OBJ_Tree_Snow_1.class, 8, 47); 
				setUpObject(OBJ_Tree_Snow_1.class, 12, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 16, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 20, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 29, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 33, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 37, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 41, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 45, 47);
				setUpObject(OBJ_Tree_Snow_1.class, 30, 0);
				setUpObject(OBJ_Tree_Snow_1.class, 38, 0);
				setUpObject(OBJ_Tree_Snow_1.class, 42, 0);
				setUpObject(OBJ_Tree_Snow_1.class, 46, 0);
				setUpObject(OBJ_Tree_Snow_1.class, 40, 6);
				setUpObject(OBJ_Tree_Snow_1.class, 44, 6);
				setUpObject(OBJ_Tree_Snow_1.class, 34, 8);
				setUpObject(OBJ_Tree_Snow_1.class, 48, 8);

				setUpObject(OBJ_Tree_Snow_2.class, 47,36);
				setUpObject(OBJ_Tree_Snow_2.class, 47,40);
				setUpObject(OBJ_Tree_Snow_2.class, 47,44);
				setUpObject(OBJ_Tree_Snow_2.class, 0,18);
				setUpObject(OBJ_Tree_Snow_2.class, 0,22);
				setUpObject(OBJ_Tree_Snow_2.class, 0,26);
				setUpObject(OBJ_Tree_Snow_2.class, 0,30);
				setUpObject(OBJ_Tree_Snow_2.class, 0,34);
				setUpObject(OBJ_Tree_Snow_2.class, 0,38);
				setUpObject(OBJ_Tree_Snow_2.class, 0,42);
				setUpObject(OBJ_Tree_Snow_2.class, 3, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 7, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 11, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 15, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 19, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 30, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 34, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 38, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 42, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 46, 46);
				setUpObject(OBJ_Tree_Snow_2.class, 2, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 6, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 10, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 14, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 18, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 27, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 31, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 35, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 39, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 43, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 47, 47);
				setUpObject(OBJ_Tree_Snow_2.class, 38, 6);
				setUpObject(OBJ_Tree_Snow_2.class, 42, 6);
				setUpObject(OBJ_Tree_Snow_2.class, 46, 7);
				setUpObject(OBJ_Tree_Snow_2.class, 33, 10);
				setUpObject(OBJ_Tree_Snow_2.class, 28, 0);
				setUpObject(OBJ_Tree_Snow_2.class, 36, 0);
				setUpObject(OBJ_Tree_Snow_2.class, 40, 0);
				setUpObject(OBJ_Tree_Snow_2.class, 44, 0);
				setUpObject(OBJ_Tree_Snow_2.class, 48, 0);
				
				setUpObject(OBJ_House_Snow.class, 41, 8);
				setUpObject(OBJ_Light_Snow.class, 37, 19);
				
				setUpObject(OBJ_Bridge_Snow.class, 25, 15);
				setUpObject(OBJ_Bridge_Snow.class, 31, 5);
				
				setUpObject(OBJ_Ocean.class, 18, 28);
				setUpObject(OBJ_Ocean.class, 25, 32);
				setUpObject(OBJ_Ocean.class, 26, 27);
				
				setUpObject(OBJ_Stone_Snow.class, 1, 13);
				setUpObject(OBJ_Stone_Snow.class, 4, 41);
				setUpObject(OBJ_Stone_Snow.class, 5, 7);
				setUpObject(OBJ_Stone_Snow.class, 6, 5);
				setUpObject(OBJ_Stone_Snow.class, 13, 45);
				setUpObject(OBJ_Stone_Snow.class, 18, 16);
				setUpObject(OBJ_Stone_Snow.class, 19, 1);
				setUpObject(OBJ_Stone_Snow.class, 26, 9);
				setUpObject(OBJ_Stone_Snow.class, 28, 20);
				setUpObject(OBJ_Stone_Snow.class, 33, 26);
				setUpObject(OBJ_Stone_Snow.class, 34, 42);
				setUpObject(OBJ_Stone_Snow.class, 41, 45);
				
				setUpObject(OBJ_SnowMan.class, 1, 15);
				setUpObject(OBJ_SnowMan.class, 5, 42);
				setUpObject(OBJ_SnowMan.class, 5, 29);
				setUpObject(OBJ_SnowMan.class, 6, 42);
				setUpObject(OBJ_SnowMan.class, 7, 42);
				setUpObject(OBJ_SnowMan.class, 8, 42);
				setUpObject(OBJ_SnowMan.class, 14, 45);
				setUpObject(OBJ_SnowMan.class, 16, 18);
				setUpObject(OBJ_SnowMan.class, 22, 22);
				setUpObject(OBJ_SnowMan.class, 31, 35);
				setUpObject(OBJ_SnowMan.class, 35, 44);
				setUpObject(OBJ_SnowMan.class, 36, 23);
				setUpObject(OBJ_SnowMan.class, 40, 42);
				setUpObject(OBJ_SnowMan.class, 46, 22);
				setUpObject(OBJ_SnowMan.class, 46, 3);
				setUpObject(OBJ_SnowMan.class, 47, 3);
				setUpObject(OBJ_SnowMan.class, 48, 3);
				
				setUpObject(OBJ_Crystal_Snow.class, 5, 2);
				setUpObject(OBJ_Crystal_Snow.class, 6, 34);
				setUpObject(OBJ_Crystal_Snow.class, 11, 42);
				setUpObject(OBJ_Crystal_Snow.class, 12, 22);
				setUpObject(OBJ_Crystal_Snow.class, 14, 8);
				setUpObject(OBJ_Crystal_Snow.class, 17, 0);
				setUpObject(OBJ_Crystal_Snow.class, 33, 44);
				setUpObject(OBJ_Crystal_Snow.class, 45, 42);
				
				setUpObject(OBJ_Demon_Door.class, 8, 0);
				break;
			}
			case 3: {
				objIndex = 0;
				
				setUpObject(OBJ_Volcano.class, 5, 37);
				setUpObject(OBJ_Volcano.class, 9, 27);
				setUpObject(OBJ_Volcano.class, 10, 39);
				setUpObject(OBJ_Volcano.class, 11, 21);
				setUpObject(OBJ_Volcano.class, 14, 4);
				setUpObject(OBJ_Volcano.class, 14, 45);
				setUpObject(OBJ_Volcano.class, 15, 25);
				setUpObject(OBJ_Volcano.class, 17, 1);
				setUpObject(OBJ_Volcano.class, 30, 0);
				setUpObject(OBJ_Volcano.class, 33, 45);
				setUpObject(OBJ_Volcano.class, 37, 21);
				setUpObject(OBJ_Volcano.class, 39, 38);
				setUpObject(OBJ_Volcano.class, 39, 27);
				
				setUpObject(OBJ_Eating_Plant.class, 0, 0);
				setUpObject(OBJ_Eating_Plant.class, 0, 5);
				setUpObject(OBJ_Eating_Plant.class, 0, 40);
				setUpObject(OBJ_Eating_Plant.class, 2, 14);
				setUpObject(OBJ_Eating_Plant.class, 4, 45);
				setUpObject(OBJ_Eating_Plant.class, 9, 12);
				setUpObject(OBJ_Eating_Plant.class, 11, 2);
				setUpObject(OBJ_Eating_Plant.class, 16, 29);
				setUpObject(OBJ_Eating_Plant.class, 17, 38);
				setUpObject(OBJ_Eating_Plant.class, 22, 36);
				setUpObject(OBJ_Eating_Plant.class, 27, 34);
				setUpObject(OBJ_Eating_Plant.class, 29, 48);
				setUpObject(OBJ_Eating_Plant.class, 33, 38);
				setUpObject(OBJ_Eating_Plant.class, 33, 29);
				setUpObject(OBJ_Eating_Plant.class, 37, 48);
				setUpObject(OBJ_Eating_Plant.class, 38, 8);
				setUpObject(OBJ_Eating_Plant.class, 40, 0);
				setUpObject(OBJ_Eating_Plant.class, 42, 15);
				setUpObject(OBJ_Eating_Plant.class, 47, 44);
				setUpObject(OBJ_Eating_Plant.class, 47, 37);
				setUpObject(OBJ_Eating_Plant.class, 48, 3);
				setUpObject(OBJ_Eating_Plant.class, 48, 13);
				
				setUpObject(OBJ_Gate_Dungeon.class, 24, 0);
				setUpObject(OBJ_Demon_Door.class, 23, 25);
				break;
			}
			case 4: {
				objIndex = 0;
				setUpObject(OBJ_Bone_Body.class, 3, 43);
				setUpObject(OBJ_Bone_Body.class, 7, 1);
				setUpObject(OBJ_Bone_Body.class, 15, 25);
				setUpObject(OBJ_Bone_Body.class, 18, 18);
				setUpObject(OBJ_Bone_Body.class, 26, 36);
				setUpObject(OBJ_Bone_Body.class, 39, 2);
				setUpObject(OBJ_Bone_Body.class, 43, 29);

				setUpObject(OBJ_Arm_Left.class, 48, 43);
				setUpObject(OBJ_Arm_Left.class, 37, 38);
				setUpObject(OBJ_Arm_Left.class, 30, 46);
				setUpObject(OBJ_Arm_Left.class, 12, 46);
				setUpObject(OBJ_Arm_Left.class, 0, 40);
				setUpObject(OBJ_Arm_Left.class, 10, 25);
				setUpObject(OBJ_Arm_Left.class, 33, 16);
				setUpObject(OBJ_Arm_Left.class, 48, 13);
				setUpObject(OBJ_Arm_Left.class, 25, 16);
				setUpObject(OBJ_Arm_Left.class, 14, 14);
				setUpObject(OBJ_Arm_Left.class, 3, 5);
				
				setUpObject(OBJ_Arm_Right.class, 46, 36);
				setUpObject(OBJ_Arm_Right.class, 34, 43);
				setUpObject(OBJ_Arm_Right.class, 21, 40);
				setUpObject(OBJ_Arm_Right.class, 11, 38);
				setUpObject(OBJ_Arm_Right.class, 0, 27);
				setUpObject(OBJ_Arm_Right.class, 37, 30);
				setUpObject(OBJ_Arm_Right.class, 37, 3);
				setUpObject(OBJ_Arm_Right.class, 30, 10);
				setUpObject(OBJ_Arm_Right.class, 7, 17);
				setUpObject(OBJ_Arm_Right.class, 25, 3);
				setUpObject(OBJ_Arm_Right.class, 19, 1);
				break;
			}
			case 5: {
				objIndex = 0;
				
				setUpObject(OBJ_Clock.class, 8, 0);
				setUpObject(OBJ_Wardobe.class, 6, 1);
				setUpObject(OBJ_Bookcase.class, 12, 1);
				setUpObject(OBJ_Table.class, 4, 5);
				break;
			}
		}
	}
	
	public void setNPC() {
		mapNum = gp.currentMap;
		switch(mapNum) {
			case 0: {
				npcIndex = 0;
				setUpNPC(NPC_Mage.class, 16, 4);
				break;
			}
			case 1: {
				npcIndex = 0;
				setUpNPC(NPC_Griffon.class, 1, 32);
				break;
			}
			case 2: {
				npcIndex = 0;
				setUpNPC(NPC_Griffon.class, 25, 49);
				break;
			}
			case 3: {
				npcIndex = 0;
				setUpNPC(NPC_Griffon.class, 25, 49);
				break;
			}
			case 4: {
				npcIndex = 0;
				setUpNPC(NPC_Mage_Red.class, 44, 24);
				setUpNPC(NPC_Mage_Blue.class, 40, 24);
				setUpNPC(NPC_Griffon.class, 41, 49);
				break;
			}
			case 5: {
				npcIndex = 0;
				setUpNPC(NPC_Merchant.class, 1, 5);
				setUpNPC(NPC_Witch.class, 14, 4);
				break;
			}
		}
	}
	
	public void setMonster() {
		mapNum = gp.currentMap;
		switch (mapNum){
			case 1: {
				monIndex = 0;
	
				setUpMonster(MON_GreenSlime.class, 6, 42);
				setUpMonster(MON_GreenSlime.class, 8, 10);
				setUpMonster(MON_GreenSlime.class, 17, 40);
				setUpMonster(MON_GreenSlime.class, 17, 15);
				setUpMonster(MON_GreenSlime.class, 22, 25);
				setUpMonster(MON_GreenSlime.class, 26, 2);
				setUpMonster(MON_GreenSlime.class, 32, 36);
				setUpMonster(MON_GreenSlime.class, 37, 47);
				setUpMonster(MON_GreenSlime.class, 39, 8);
				setUpMonster(MON_GreenSlime.class, 44, 41);
				setUpMonster(MON_GreenSlime.class, 48, 19);

				setUpMonster(MON_RedSlime.class, 12, 19);
				setUpMonster(MON_RedSlime.class, 0, 22);
				setUpMonster(MON_RedSlime.class, 9, 25);
				setUpMonster(MON_RedSlime.class, 32, 16);
				setUpMonster(MON_RedSlime.class, 33, 23);
				setUpMonster(MON_RedSlime.class, 42, 42);
				setUpMonster(MON_RedSlime.class, 44, 1);
				setUpMonster(MON_RedSlime.class, 48, 10);
				setUpMonster(MON_RedSlime.class, 49, 32);

				setUpMonster(MON_Orc.class, 38, 20);
				setUpMonster(MON_Orc1.class, 46, 34);
				setUpMonster(MON_Orc1.class, 45, 4);
				setUpMonster(MON_Orc1.class, 3, 19);
				break;
			}
			case 2: {
				monIndex = 0;
				
				setUpMonster(MON_Snow_Slime.class, 3, 34);
				setUpMonster(MON_Snow_Slime.class, 3, 25);
				setUpMonster(MON_Snow_Slime.class, 4, 40);
				setUpMonster(MON_Snow_Slime.class, 5, 0);
				setUpMonster(MON_Snow_Slime.class, 11, 10);
				setUpMonster(MON_Snow_Slime.class, 15, 42);
				setUpMonster(MON_Snow_Slime.class, 15, 7);
				setUpMonster(MON_Snow_Slime.class, 13, 33);
				setUpMonster(MON_Snow_Slime.class, 16, 1);
				setUpMonster(MON_Snow_Slime.class, 18, 22);
				setUpMonster(MON_Snow_Slime.class, 26, 2);
				setUpMonster(MON_Snow_Slime.class, 31, 44);
				setUpMonster(MON_Snow_Slime.class, 35, 4);
				setUpMonster(MON_Snow_Slime.class, 38, 3);
				setUpMonster(MON_Snow_Slime.class, 38, 17);
				setUpMonster(MON_Snow_Slime.class, 42, 21);
				setUpMonster(MON_Snow_Slime.class, 42, 44);
				setUpMonster(MON_Snow_Slime.class, 45, 6);
				setUpMonster(MON_Snow_Slime.class, 45, 27);
				
				setUpMonster(MON_Golem.class, 37, 13);
				setUpMonster(MON_Golem.class, 8, 24);
				
				setUpMonster(MON_Orc2.class, 7, 41);
				setUpMonster(MON_Orc2.class, 21, 18);
				setUpMonster(MON_Orc2.class, 30, 3);
				setUpMonster(MON_Orc2.class, 31, 24);
				setUpMonster(MON_Orc2.class, 41, 3);
				setUpMonster(MON_Orc2.class, 43, 43);

				setUpMonster(MON_Zombie_Winter.class, 9, 5);
				break;
			}
			case 3: {
				monIndex = 0;
				
				setUpMonster(MON_Skeleton.class, 4, 5);
				setUpMonster(MON_Zombie_Fire.class, 42, 5);
				setUpMonster(MON_Dragon.class, 23, 7);
				
				setUpMonster(MON_Demon_Fire.class, 0, 4);
				setUpMonster(MON_Demon_Fire.class, 0, 38);
				setUpMonster(MON_Demon_Fire.class, 1, 12);
				setUpMonster(MON_Demon_Fire.class, 4, 26);
				setUpMonster(MON_Demon_Fire.class, 7, 33);
				setUpMonster(MON_Demon_Fire.class, 8, 48);
				setUpMonster(MON_Demon_Fire.class, 10, 4);
				setUpMonster(MON_Demon_Fire.class, 18, 30);
				setUpMonster(MON_Demon_Fire.class, 19, 38);
				setUpMonster(MON_Demon_Fire.class, 27, 32);
				setUpMonster(MON_Demon_Fire.class, 32, 36);
				setUpMonster(MON_Demon_Fire.class, 38, 11);
				setUpMonster(MON_Demon_Fire.class, 39, 48);
				setUpMonster(MON_Demon_Fire.class, 40, 3);
				setUpMonster(MON_Demon_Fire.class, 44, 27);
				setUpMonster(MON_Demon_Fire.class, 48, 10);
				break;
			}
			case 4: {
				monIndex = 0;
				setUpMonster(MON_Ishigami.class, 5, 8);

				setUpMonster(MON_Bat.class, 45, 37);
				setUpMonster(MON_Bat.class, 31, 38);
				setUpMonster(MON_Bat.class, 10, 37);
				setUpMonster(MON_Bat.class, 7, 43);
				
				setUpMonster(MON_Eye.class, 36, 42);
				setUpMonster(MON_Eye.class, 28, 45);
				setUpMonster(MON_Eye.class, 21, 42);
				setUpMonster(MON_Eye.class, 4, 40);

				setUpMonster(MON_Spide.class, 37, 44);
				setUpMonster(MON_Spide.class, 24, 45);
				setUpMonster(MON_Spide.class, 15, 44);
				setUpMonster(MON_Spide.class, 30, 38);
				
				
				break;
			}
		}
	}
	
	public void setUpObject(Class<? extends Entity> objectClass, int col, int row) {
	    try {
	        gp.obj[mapNum][objIndex] = objectClass.getDeclaredConstructor(GamePanel.class).newInstance(gp);
	        gp.obj[mapNum][objIndex].worldX = gp.tileSize * col;
	        gp.obj[mapNum][objIndex].worldY = gp.tileSize * row;
	        objIndex++;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void setUpMonster(Class<? extends Entity> objectClass, int col, int row) {
	    try {
	        gp.monster[mapNum][monIndex] = objectClass.getDeclaredConstructor(GamePanel.class).newInstance(gp);
	        gp.monster[mapNum][monIndex].worldX = gp.tileSize * col;
	        gp.monster[mapNum][monIndex].worldY = gp.tileSize * row;
	        monIndex++;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void setUpNPC(Class<? extends Entity> objectClass, int col, int row) {
	    try {
	        gp.npc[mapNum][npcIndex] = objectClass.getDeclaredConstructor(GamePanel.class).newInstance(gp);
	        gp.npc[mapNum][npcIndex].worldX = gp.tileSize * col;
	        gp.npc[mapNum][npcIndex].worldY = gp.tileSize * row;
	        npcIndex++;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

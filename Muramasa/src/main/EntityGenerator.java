package main;

import entity.Entity;
import object.*;
import monster.*;

public class EntityGenerator {

	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(String itemName) {
		
		Entity obj = null;

		switch(itemName) {
		case OBJ_Arm_Left.objName: obj = new OBJ_Arm_Left(gp); break;
		case OBJ_Arm_Right.objName: obj = new OBJ_Arm_Right(gp); break;
		case OBJ_Armor_Lv0.objName: obj = new OBJ_Armor_Lv0(gp); break;
		case OBJ_Armor_Lv1.objName: obj = new OBJ_Armor_Lv1(gp); break;
		case OBJ_Armor_Lv2.objName: obj = new OBJ_Armor_Lv2(gp); break;
		case OBJ_Armor_Lv3.objName: obj = new OBJ_Armor_Lv3(gp); break;
		case OBJ_BigTree.objName: obj = new OBJ_BigTree(gp); break;
		case OBJ_Black_Rock.objName: obj = new OBJ_Black_Rock(gp); break;
		case OBJ_Bone.objName: obj = new OBJ_Bone (gp); break;
		case OBJ_Bone_Body.objName: obj = new OBJ_Bone_Body (gp); break;
		case OBJ_Bone_Head.objName: obj = new OBJ_Bone_Head (gp); break;
		case OBJ_Bookcase.objName: obj = new OBJ_Bookcase (gp); break;
		case OBJ_Bridge_Vertical.objName: obj = new OBJ_Bridge_Vertical (gp); break;
		case OBJ_Bridge_Horizontal.objName: obj = new OBJ_Bridge_Horizontal (gp); break;
		case OBJ_Bridge_Snow.objName: obj = new OBJ_Bridge_Snow (gp); break;
		case OBJ_Clock.objName: obj = new OBJ_Clock(gp); break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
		case OBJ_Coin.objName: obj = new OBJ_Coin(gp); break;
		case OBJ_Crystal_Snow.objName: obj = new OBJ_Crystal_Snow(gp); break;
		case OBJ_Demon_Door.objName: obj = new OBJ_Demon_Door(gp); break;
		case OBJ_Eating_Plant.objName: obj = new OBJ_Eating_Plant(gp); break;
		case OBJ_Gate_Dungeon.objName: obj = new OBJ_Gate_Dungeon(gp); break;
		case OBJ_House_Snow.objName: obj = new OBJ_House_Snow(gp); break;
		case OBJ_Meteors.objName: obj = new OBJ_Meteors(gp); break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
		case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
		case OBJ_Light_Snow.objName: obj = new OBJ_Light_Snow(gp); break;
		case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
		case OBJ_Ocean.objName: obj = new OBJ_Ocean(gp); break;
		case OBJ_Portal.objName: obj = new OBJ_Portal(gp); break;
		case OBJ_Posion_Blue.objName: obj = new OBJ_Posion_Blue(gp); break;
		case OBJ_Posion_Red.objName: obj = new OBJ_Posion_Red(gp); break;
		case OBJ_Grass_Bullet.objName: obj = new OBJ_Grass_Bullet(gp); break;
		case OBJ_SnowMan.objName: obj = new OBJ_SnowMan(gp); break;
		case OBJ_Stair.objName: obj = new OBJ_Stair(gp); break;
		case OBJ_Stone.objName: obj = new OBJ_Stone(gp); break;
		case OBJ_Stone_Snow.objName: obj = new OBJ_Stone_Snow(gp); break;
		case OBJ_Skull.objName: obj = new OBJ_Skull(gp); break;
		case OBJ_Summon_Book.objName: obj = new OBJ_Summon_Book(gp); break;
		case OBJ_Table.objName: obj = new OBJ_Table(gp); break;
		case OBJ_Sword_Lv0.objName: obj = new OBJ_Sword_Lv0(gp); break;
		case OBJ_Sword_Lv1.objName: obj = new OBJ_Sword_Lv1(gp); break;
		case OBJ_Sword_Lv2.objName: obj = new OBJ_Sword_Lv2(gp); break;
		case OBJ_Sword_Lv3.objName: obj = new OBJ_Sword_Lv3(gp); break;
		case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
		case OBJ_Tree_Snow_1.objName: obj = new OBJ_Tree_Snow_1(gp); break;
		case OBJ_Tree_Snow_2.objName: obj = new OBJ_Tree_Snow_2(gp); break;
		case OBJ_Volcano.objName: obj = new OBJ_Volcano(gp); break;
		case OBJ_Wardobe.objName: obj = new OBJ_Wardobe(gp); break;
		}
		return obj;
	}

	public Entity getMonster(String monName) {
		Entity mon = null;
		
		switch (monName) {
		case MON_Bat.monName: mon = new MON_Bat(gp); break;
		case MON_Demon_Fire.monName: mon = new MON_Demon_Fire(gp); break;
		case MON_Dragon.monName: mon = new MON_Dragon(gp); break;
		case MON_Eye.monName: mon = new MON_Eye(gp); break;
		case MON_Golem.monName: mon = new MON_Golem(gp); break;
		case MON_GreenSlime.monName: mon = new MON_GreenSlime(gp); break;
		case MON_Ishigami.monName: mon = new MON_Ishigami(gp); break;
		case MON_Orc.monName: mon = new MON_Orc(gp); break;
		case MON_Orc1.monName: mon = new MON_Orc1(gp); break;
		case MON_Orc2.monName: mon = new MON_Orc2(gp); break;
		case MON_RedSlime.monName: mon = new MON_RedSlime(gp); break;
		case MON_Skeleton.monName: mon = new MON_Skeleton(gp); break;
		case MON_Snow_Slime.monName: mon = new MON_Snow_Slime(gp); break;
		case MON_Spide.monName: mon = new MON_Spide(gp); break;
		case MON_Zombie_Fire.monName: mon = new MON_Zombie_Fire(gp); break;
		case MON_Zombie_Winter.monName: mon = new MON_Zombie_Winter(gp); break;
		}
		return mon;
	}
}

package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Shuriken extends Projectile{

	public static final String objName = "shuriken";
	private int levelShuriken = 0;
	
	GamePanel gp;
	
	public OBJ_Shuriken(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = type_skill;
		speed = 10;
		maxLife = 120;
		life = maxLife;
		attack = 40;
		useCost = 2;
		knockBackPower = 5;
		alive = false;
		maxFrameAttack = 6;
		attacking = true;
	}
	
	public void getAttackImage(String level) {
		
		switch (level) {
			case "lv0": {
				levelShuriken = 0; 
				attack = 40;
				break;
			}
			case "lv1": {
				levelShuriken = 1; 
				attack = 40 + gp.player.currentWeapon.attack;
				break;
			}
			case "lv2": {
				levelShuriken = 2;
				attack = 50 + gp.player.currentWeapon.attack;
				break;
			}
			case "lv3": {
				levelShuriken = 3; 
				attack = 100 + gp.player.currentWeapon.attack;
				break;
			}
		}
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/shuriken_" + level + "_right_" + i;
	    	attackUp[i] = setup(tempPath, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5));
	    	attackRight[i] = setup(tempPath, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5));
	    }
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/shuriken_" + level + "_left_" + i;
	    	attackLeft[i] = setup(tempPath, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5));
		    attackDown[i] = setup(tempPath, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5));
	    }
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		
		Color color = null;
		
		switch (levelShuriken) {
			case 0: color = new Color(66,76,110); break;
			case 1: color = new Color(126,229,96); break;
			case 2: color = new Color(36,159,222); break;
			case 3: color = new Color(223,62,35); break;
		}
		
		return color;
	}
	
	public int getPariticleSize() {
		
		int size = 8;//pixel
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}

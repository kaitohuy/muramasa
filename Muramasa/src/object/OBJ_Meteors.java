package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Meteors extends Projectile{

	public static final String objName = "meteors";
	
	GamePanel gp;
	
	public OBJ_Meteors(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		speed = 5;
		maxLife = 90;
		life = maxLife;
		if(gp.player != null) {
			attack = 200 + (gp.player.level - 8) * 50;
			useCost = gp.player.maxMana / 24;
		}else {
			attack = 200;
			useCost = 12;
		}
		knockBackPower = 5;
		maxFrameAttack = 6;
		alive = false;
		attacking = true;
		getAttackImage();
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/objects/meteors_" + i;
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
		Color color = new Color(240,50,0);
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

	@Override
	public void getNextPosition() {
		worldX += speed;
		worldY += speed;
	}
}
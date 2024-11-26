package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Spike_Rock extends Projectile{

	public static final String objName = "spike rock";
	
	GamePanel gp;
	
	public OBJ_Spike_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		speed = 10;
		maxLife = 180;
		life = maxLife;
		attack = 200;
		useCost = 1;
		knockBackPower = 5;
		maxFrameAttack = 8;
		frameDelay = 2;
		alive = false;
		attacking = true;
		getAttackImage();
		setDefaultSolidArea(12, 12, (int)(gp.tileSize*2.5) - 24, (int)(gp.tileSize*2.5) - 24, 0, 0);
		
	}
	
	public void getAttackImage() {
		
	    for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/projectile/spike_rock_" + i;
		    attackRight[i] = setup(tempPath, (int)(gp.tileSize*2.5), (int)(gp.tileSize*2.5));
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
		Color color = new Color(128,128,128);
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
	}
}
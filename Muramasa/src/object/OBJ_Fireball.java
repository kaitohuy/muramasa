package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{

	public static final String objName = "Fireball";
	
	GamePanel gp;
	
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		speed = 6;
		maxLife = 80;
		life = maxLife;
		attack = 3;
		useCost = 1;
		knockBackPower = 5;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize*2);
		up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize*2);
		down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize*2);
		down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize*2);
		left1 = setup("/projectile/fireball_left_1", gp.tileSize*2, gp.tileSize);
		left2 = setup("/projectile/fireball_left_2", gp.tileSize*2, gp.tileSize);
		right1 = setup("/projectile/fireball_right_1", gp.tileSize*2, gp.tileSize);
		right2 = setup("/projectile/fireball_right_2", gp.tileSize*2, gp.tileSize);
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
}

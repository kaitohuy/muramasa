package object;

import java.awt.Color;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fire_Bullet extends Projectile{

	public static final String objName = "fire bullet";
	
	GamePanel gp;
	
	public OBJ_Fire_Bullet(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		name = objName;
		speed = 5;
		maxLife = 160;
		life = maxLife;
		attack = 30;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/fire_bullet_up", eWidth, eHeight);
		up2 = setup("/projectile/fire_bullet_up", eWidth, eHeight);
		down1 = setup("/projectile/fire_bullet_down", eWidth, eHeight);
		down2 = setup("/projectile/fire_bullet_down", eWidth, eHeight);
		left1 = setup("/projectile/fire_bullet_left", eWidth, eHeight);
		left2 = setup("/projectile/fire_bullet_left", eWidth, eHeight);
		right1 = setup("/projectile/fire_bullet_right", eWidth, eHeight);
		right2 = setup("/projectile/fire_bullet_right", eWidth, eHeight);
	}

	public Color getParticleColor() {
		Color color = new Color(244, 141, 108, 255);
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

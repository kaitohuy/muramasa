package object;

import java.awt.Color;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Spike_Arrow extends Projectile{

	public static final String objName = "spike arrow";
	
	GamePanel gp;
	
	public OBJ_Spike_Arrow(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		eWidth = gp.tileSize*12;
		eHeight = gp.tileSize*3;
		name = objName;
		speed = 4;
		maxLife = 120;
		life = maxLife;
		attack = 250;
		useCost = 1;
		alive = false;
		getImage();
		setDefaultSolidArea(0, gp.tileSize, eWidth, gp.tileSize*2, 0, 0);
	}
	
	public void getImage() {
		down1 = setup("/projectile/spike_arrow", eWidth, eHeight);
		down2 = down1;
	}

	public Color getParticleColor() {
		Color color = new Color(40,50,0);
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
		worldY += speed;
	}
}

package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Light_Snow extends Entity{

	public static final String objName = "light_snow";
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	GamePanel gp;
	
	public OBJ_Light_Snow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		eWidth = gp.tileSize;
		eHeight = gp.tileSize*2;
		type = type_obstacle;
		name = objName;
		down1 = setup("/objects/light_snow", gp.tileSize, gp.tileSize*2);
		lightRadus = 350;
		setDefaultSolidArea(20, 64, 12, 24, 0, 0);
		collision = true;
	}
}
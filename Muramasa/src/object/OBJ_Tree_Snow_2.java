package object;

import entity.Tree;
import main.GamePanel;

public class OBJ_Tree_Snow_2 extends Tree{

	public static final String objName = "tree_snow_2";
	
	GamePanel gp;
	
	public OBJ_Tree_Snow_2(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*3;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/tree_snow_2", gp.tileSize*3, gp.tileSize*3);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(48, gp.tileSize*2, 40, 40, 0, 0);
		
	}

}

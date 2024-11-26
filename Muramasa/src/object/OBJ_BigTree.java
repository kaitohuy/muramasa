package object;

import entity.Tree;
import main.GamePanel;

public class OBJ_BigTree extends Tree{

	public static final String objName = "big_tree";
	
	GamePanel gp;
	
	public OBJ_BigTree(GamePanel gp) {
		super(gp);
		this.gp = gp;

		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*4;
		type = type_obstacle;
		name = objName;
		image = setup("/objects/big_tree", eWidth, eHeight);
		down1 = image;
		collision = true;
		
		setDefaultSolidArea(48, 144, 40, 32, 0, 0);
		
	}

}

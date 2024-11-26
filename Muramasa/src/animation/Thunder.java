package animation;

import main.GamePanel;

public class Thunder extends Animation{

	public Thunder(GamePanel gp) {
		super(gp);
    	nameSheet = "thunder";
        aScreenX = gp.tileSize*8;
        aScreenY = gp.tileSize;
        totalFrames = 11;
        currentFrame = 0;
        frameDelay = 50;
        
        getSheet(nameSheet);
	}
	
	@Override
	protected void onAnimationEnd() {
	    gp.endThunderSummon = true;
	}
}

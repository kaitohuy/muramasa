package animation;

import main.GamePanel;


public class Summon extends Animation{
	
    public Summon(GamePanel gp) {
    	super(gp);
    	nameSheet = "summon";
        aScreenX = gp.tileSize*7;
        aScreenY = gp.tileSize*3;
        totalFrames = 54;
        currentFrame = 0;
        frameDelay = 50;
        
        getSheet(nameSheet);
    }
    
    @Override
    protected void onAnimationEnd() {
        gp.endSummon = true;
    }
}



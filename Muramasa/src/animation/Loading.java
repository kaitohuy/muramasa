package animation;

import java.awt.event.KeyEvent;

import main.GamePanel;

public class Loading extends Animation{
	
	public boolean titleState = false;
	public boolean overState = false;
	
    public Loading(GamePanel gp) {
    	super(gp);
    	nameSheet = "loading";
        aScreenX = gp.tileSize*9 + 24;
        aScreenY = gp.tileSize*6 - 24;
        totalFrames = 16;
        currentFrame = 0;
        frameDelay = 50;
        
        getSheet(nameSheet);
    }
    
    @Override
    protected void onAnimationEnd() {
       counter ++;
       if(titleState == true) {
    	   gp.player.keyH.titleState(KeyEvent.VK_TAB);
    	   titleState = false;
       }
       if(gp.currentMap != 0 && overState == true) {
    	   gp.player.keyH.gameOverState(KeyEvent.VK_TAB);
    	   overState = false;
       }
       if(counter > 3) {
    	  counter = 0;
       }
    }
}



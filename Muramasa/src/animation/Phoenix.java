package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Projectile;
import main.GamePanel;
import object.OBJ_Meteors;

public class Phoenix extends Animation{

	public Phoenix(GamePanel gp) {
		super(gp);
    	nameSheet = "phoenix";
        aScreenX = 0;
        aScreenY = 0;
        totalFrames = 9;
        currentFrame = 0;
        frameDelay = 2;
        
        getSheet(nameSheet);
	}
	
	@Override
	protected void drawFrame(Graphics2D g2) {
	    int frameX = currentFrame * frameWidth;
	   
	    if (aScreenX <= gp.screenWidth) {
	 
	        // Vẽ frame hiện tại
	        BufferedImage frame = sheet.getSubimage(frameX, 0, frameWidth, frameHeight);
	        g2.drawImage(frame, aScreenX, aScreenY, null);  // Vẽ frame tại vị trí (screenX, screenY)
	        
	        aScreenX += 15;  // Di chuyển sang phải
	        
        	if(aScreenX % 90 == 0) {
        		Projectile projectile;
            	projectile = new OBJ_Meteors(gp);
            	projectile.set(aScreenX + gp.player.worldX - gp.player.screenX - 90, aScreenY + gp.player.worldY - gp.player.screenY, "down", true, gp.player);
    			for(int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
    				if(gp.projectile[gp.currentMap][i] == null) {
    					gp.projectile[gp.currentMap][i] = projectile;
    					break;
    				}
    			}
        	}
			
	    } else {
	        currentFrame = 0;
	        aScreenX = 0;
	        gp.keyH.summonKeyPressed = false;
	    }
	}
}

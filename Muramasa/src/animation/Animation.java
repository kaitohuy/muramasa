package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Animation {

	protected BufferedImage sheet;
	protected BufferedImage lastFrame;
	protected String nameSheet;
    protected int totalFrames;
    protected int currentFrame;
    protected int frameDelay;
    protected int aScreenX, aScreenY;
    protected int frameWidth;
	protected int frameHeight;
    private long lastFrameTime;
    public int counter = 0;
    protected GamePanel gp;
    
    public Animation(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void getSheet(String nameSheet) {
    	String path = "/animation/" + nameSheet + ".png";
    	try {
			sheet = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	frameWidth = sheet.getWidth()/totalFrames;
    	frameHeight = sheet.getHeight();
    	lastFrameTime = System.nanoTime();
    }
 
    protected void drawFrame(Graphics2D g2) {
    	// Tính toán vị trí x của frame hiện tại trong sprite sheet
        int frameX = currentFrame * frameWidth;
    	if (currentFrame < totalFrames) {	
            BufferedImage frame = sheet.getSubimage(frameX, 0, frameWidth, frameHeight);
            g2.drawImage(frame, aScreenX, aScreenY, frameWidth, frameHeight, null);  // Vẽ frame tại vị trí (x, y)
        	if(currentFrame == totalFrames - 1) {
            	lastFrame = frame;
            	currentFrame = 0; 
            	onAnimationEnd();
            }
        }
    }
    
    public void drawLastFrame(Graphics2D g2) {
    	g2.drawImage(lastFrame, aScreenX, aScreenY, frameWidth, frameHeight, null);
    }
    
    protected void onAnimationEnd() {
        // Mặc định không làm gì, lớp con có thể override
    }
    
    private void getTimeDelay() {
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime - lastFrameTime) / 1_000_000;  // Đổi sang mili giây

        // Cập nhật frame nếu đã đủ thời gian delay giữa các frame
        if (elapsedTime >= frameDelay) {
            currentFrame++;  // Chuyển đến frame tiếp theo
            if (currentFrame >= totalFrames) {
                currentFrame = 0;  // Quay lại frame đầu tiên nếu hết frame
            }
            lastFrameTime = currentTime;  // Cập nhật thời gian frame cuối
        }
    }

    public void draw(Graphics2D g2) {
    	getTimeDelay();
        drawFrame(g2);
   }
}



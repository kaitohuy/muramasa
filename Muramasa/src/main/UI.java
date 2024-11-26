package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import entity.Entity;
import object.OBJ_Coin;


public class UI {

	GamePanel gp;
	Graphics2D g2;
	public Font maruMonica, VT323;
	Font arial_40, arial_80B, arial_28;
	BufferedImage coin;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>(); 
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = -3;// 0 : first screen, 1; second screen
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int numberSlotX = 5;
	public int numberSlotY = 4;
	int subState = 0;
	int counter = 0;
	public Entity npc;
	private int charIndex = 0;
	private String combinaedText = "";
	private int counterSummon = 0;
	private boolean soundSummon = false;
	private boolean soundThunder = false;
	private boolean endLine = false;
	private UtilityTool uTool = new UtilityTool();
    private BufferedImage healthBarImage = null;
	private BufferedImage lockSkill = null;
	private BufferedImage utilIcon = null;
	private BufferedImage phoenixIcon = null;
	private BufferedImage background = null;
	private BufferedImage transition = null;
	public String username = "";
	public String password = "";
	public String confirmPassword = "";
	public boolean wrongPass = false;
	public boolean existUser = false;
	public boolean wrongConfirm = false;

	public UI(GamePanel gp) {
	    this.gp = gp;

	    maruMonica = loadFont("x12y16pxMaruMonica");
	    VT323 = loadFont("VT323-Regular");

	    // Tạo đối tượng HUD
	    try {
			healthBarImage = ImageIO.read(getClass().getResourceAsStream("/player/health_bar.png"));
			lockSkill = ImageIO.read(getClass().getResourceAsStream("/objects/lock.png"));
			utilIcon = ImageIO.read(getClass().getResourceAsStream("/objects/utilIcon.png"));
			phoenixIcon = ImageIO.read(getClass().getResourceAsStream("/objects/phoenix.png"));
			background = ImageIO.read(getClass().getResourceAsStream("/background/titleScreen.png"));
			transition = ImageIO.read(getClass().getResourceAsStream("/background/transition.png"));
			phoenixIcon = uTool.scaleImage(phoenixIcon, gp.tileSize, gp.tileSize);
			healthBarImage = uTool.scaleImage(healthBarImage, (int)(gp.tileSize*4.5), gp.tileSize*2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Entity bronze_coin = new OBJ_Coin(gp);
	    coin = bronze_coin.down1;

	    // Phông chữ
	    arial_40 = new Font("Arial", Font.PLAIN, 40);
	    arial_28 = new Font("Arial", Font.PLAIN, 28);
	    arial_80B = new Font("Arial", Font.BOLD, 80);
	}
	
	public void resetCounter() {
		
		counter = 0;
		counterSummon = 0;
		soundSummon = false;
		soundThunder = false;
		endLine = false;
	}
	
	private Font loadFont(String path) {
		String tempPath = "/fonts/" + path + ".ttf";
		try {
			InputStream is = getClass().getResourceAsStream(tempPath);
			return Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException | IOException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(VT323.deriveFont(Font.BOLD, 40f));
		g2.setColor(Color.white);
		
		//title state
		if(gp.gameState == gp.titleState) {
			g2.setColor(new Color(0,0,0,255));
	        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);
	        drawTitleScreen();
		}
		
		//play state
		if(gp.gameState == gp.playState) {
			//do play state stuff
			if(gp.currentMap != 0 && gp.currentMap != 5) {
				drawSkill(gp.player);
				drawPlayerLife();
				drawMonsterLife();
				drawMessage();
			}
		}
		//pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		//dialogue state
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		//character State
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		//option state
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		
		//game over state
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		
		//transition state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
		//trade state
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		
		if(gp.gameState == gp.summonState) {
			drawSummonState();
		}
	}

	private void drawSummonState() {
		if (soundSummon == false) {
			soundSummon = true;
			gp.playSe(25);
		}
		drawIncantation("Hikari no shugosha yo, kõrin seyo, Kuchiyose no Jutsu!");
		gp.currentArea = gp.indoor;
		if(counterSummon >= 60) {
			gp.aSummon.draw(g2);
			counterSummon = 60;
		}else {
			counterSummon++;
		}
		if(gp.endSummon == true) {
			if (soundThunder == false) {
				soundThunder = true;
				gp.playSe(24);
			}
			gp.aSummon.drawLastFrame(g2);
			gp.aThunder.draw(g2);	
			if(gp.endThunderSummon == true) {
				gp.afterSummon = true;
				gp.gameState = gp.playState;
						
			}
		}
		 
	}

	public void drawPlayerLife() {
		
	    int x = gp.tileSize / 2; // Vị trí bắt đầu
	    int y = gp.tileSize / 2;
	    int barWidth = 146; // Chiều dài của thanh
	    int barHeight = 12; // Chiều cao của thanh
   
	    // Tính toán giá trị thanh máu dựa trên phần trăm life hiện tại
	    float lifePercentage = (float) gp.player.life / gp.player.maxLife;
	    int lifeBarWidth = (int) (barWidth * lifePercentage);
	    
	 // Vẽ nền màu xám trước cho thanh máu
	    g2.setColor(new Color(50, 50, 50)); // Màu xám
	    g2.fillRect(x + 64, y + 18, barWidth, barHeight); // Vẽ thanh xám
	    
	    // Vẽ thanh sinh mệnh (life)
	    g2.setColor(new Color(255, 0, 0)); // Màu đỏ cho thanh máu
	    g2.fillRect(x + 64, y + 18, lifeBarWidth, barHeight); // Vẽ thanh máu
	    
	    // Tính toán giá trị thanh mana
	    float manaPercentage = (float) gp.player.mana / gp.player.maxMana;
	    int manaBarWidth = (int) (barWidth * manaPercentage);
	    
	 // Vẽ nền màu xám trước cho thanh máu
	    g2.setColor(new Color(50, 50, 50)); // Màu xám
	    g2.fillRect(x + 72, y + 36, barWidth - 36, barHeight); // Vẽ thanh xám
	    
	    // Vẽ thanh năng lượng (mana)
	    g2.setColor(new Color(0, 0, 255)); // Màu xanh cho thanh mana
	    g2.fillRect(x + 72, y + 36, manaBarWidth - 36, barHeight); // Vẽ thanh mana
	    
	    // Vẽ biểu tượng nhân vật
	    g2.drawImage(healthBarImage, x, y, null);
	    
	    g2.setColor(new Color(255, 255, 255)); // Màu xanh cho thanh mana
	    g2.setFont(VT323.deriveFont(Font.BOLD, 14f));
	    String currentLevel = "Level: " + gp.player.level;
	    g2.drawString(currentLevel, x + 82, y + 62);
	     
	}

	public void drawMonsterLife() {
		
		for(int i = 0; i < gp.monster[gp.currentMap].length; i++) {
			
			Entity monster = gp.monster[gp.currentMap][i];
			
			if(monster != null && monster.inCamera() == true) {
				
				if(monster.hpBarOn == true && monster.boss == false) {
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					g2.setColor(new Color(35,35,35));
					g2.fillRect(monster.getScreenX() - 1 + monster.solidArea.x , monster.getScreenY()  - 16 + monster.solidArea.y/2, gp.tileSize+2, 12);
					g2.setColor(new Color(255,0,30));
					g2.fillRect(monster.getScreenX() + monster.solidArea.x, monster.getScreenY()  - 15 + monster.solidArea.y/2, (int)hpBarValue, 10);
					
					monster.hpBarCounter++;
					
					if(monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				else if(monster.boss == true) {
					
					double oneScale = (double)gp.tileSize * 8/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.tileSize*10;
					
					g2.setColor(new Color(35,35,35));
					g2.fillRect(x - 1, y  - 1, gp.tileSize*8 + 2, 22);
					g2.setColor(new Color(255,0,30));
					g2.fillRect(x , y, (int)hpBarValue, 20);
					
					g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, x + 4, y - 10);
				}
			}
		}
		 
	}
		
	private void resetAccount() {

		//reset account
		username = "";
		password = "";
		confirmPassword = "";
		wrongPass = false;
		existUser = false;
		wrongConfirm = false;
	}
	
	public void drawTitleScreen() {
		// TODO Auto-generated method stub
		
		g2.drawImage(background, 0, 0, null);
		
		if(titleScreenState == -3) {
			
			resetAccount();
			
			g2.setFont(VT323.deriveFont(Font.BOLD, 96f));
			String text = " Muramasa Adventure";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			
			//title name
			g2.setColor(Color.WHITE);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(new Color(255, 140, 0));
			g2.drawString(text, x + 4, y + 4);
			y += (int)(gp.tileSize*1.5);

			//menu
			g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
			
			text = "SIGN IN";
			x = getXForCenteredText(text);
			y += gp.tileSize*3.7;
			int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}		
			
			text = "SIGN UP";
			x = getXForCenteredText(text);
			y += gp.tileSize*1.3;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
			text = "QUIT";
			x = getXForCenteredText(text);
			y += gp.tileSize*1.3;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
		}
		else if(titleScreenState == -2) {  // Màn hình đăng nhập
	        g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
	        String text = "SIGN IN";
	        int x = getXForCenteredText(text);
	        int y = gp.tileSize * 3;
	        
	        // Tiêu đề
	        g2.setColor(Color.WHITE);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(new Color(255, 140, 0));
			g2.drawString(text, x + 4, y + 4);
	        
	        // Trường nhập liệu
			g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
	        text = "Username:    " + username;  // Hiển thị tên người dùng đã nhập
	        y += (int)(gp.tileSize * 1.5);
	        g2.drawString(text, gp.tileSize, y);
	        g2.drawRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize); // Ô nhập liệu tên người dùng
						
	        if(commandNum == 0) {
	        	g2.setColor(new Color(255, 255, 255, 127));
	            g2.fillRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize);
			}	
			
	        text = "Password:    " + password;  // Hiển thị mật khẩu đã nhập (có thể thay bằng dấu sao cho bảo mật)
	        y += (int)(gp.tileSize * 1.5);
	        g2.drawString(text, gp.tileSize, y);
	        g2.drawRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize); // Ô nhập liệu mật khẩu

	        if(commandNum == 1) {
	        	g2.setColor(new Color(255, 255, 255, 127));
	            g2.fillRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize);
			}	
	        
	       if(wrongPass == true) {
	    	   text = "*Wrong password or username, please try again!";  // Hiển thị mật khẩu đã nhập (có thể thay bằng dấu sao cho bảo mật)
		        g2.setFont(VT323.deriveFont(Font.PLAIN, 24f));
		        g2.setColor(new Color(200, 0, 0));
		        g2.drawString(text, gp.tileSize*10, y + gp.tileSize*2/3);
	       }
	       
	       //reset g2
	       g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
	       g2.setColor(new Color(255, 140, 0));
	       
	        // Nút SIGN IN
	        text = "SIGN IN";
	        y += gp.tileSize * 3;
	        x = getXForCenteredText(text);
	        g2.drawString(text, x, y);
	        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}	
			
			text = "BACK";
	        y += (int)(gp.tileSize * 1.5);
	        x = getXForCenteredText(text);
	        g2.drawString(text, x, y);
	        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
	    }
	    else if (titleScreenState == -1) {  // Màn hình đăng ký
	        g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
	        String text = "SIGN UP";
	        int x = getXForCenteredText(text);
	        int y = gp.tileSize * 3;
	        
	        // Tiêu đề
	        g2.setColor(Color.WHITE);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(new Color(255, 140, 0));
			g2.drawString(text, x + 4, y + 4);
	        
	        // Trường nhập liệu
			g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
	        text = "Username:    " + username;  // Hiển thị tên người dùng đã nhập
	        y += (int)(gp.tileSize * 1.5);
	        g2.drawString(text, gp.tileSize, y);
	        g2.drawRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize); // Ô nhập liệu tên người dùng

	        if(commandNum == 0) {
	        	g2.setColor(new Color(255, 255, 255, 127));
	            g2.fillRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize);
			}
	        
	        text = "Password:    " + password;  // Hiển thị mật khẩu đã nhập
	        y += (int)(gp.tileSize * 1.5);
	        g2.drawString(text, gp.tileSize, y);
	        g2.drawRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize); // Ô nhập liệu mật khẩu

	        if(commandNum == 1) {
	        	g2.setColor(new Color(255, 255, 255, 127));
	            g2.fillRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize);
			}
	        
	        text = "Confirm:     " + confirmPassword;  // Hiển thị xác nhận mật khẩu
	        y += (int)(gp.tileSize * 1.5);
	        g2.drawString(text, gp.tileSize, y);
	        g2.drawRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize); // Ô xác nhận mật khẩu

	        if(commandNum == 2) {
	        	g2.setColor(new Color(255, 255, 255, 127));
	            g2.fillRect(gp.tileSize * 6, y - gp.tileSize*3/4, gp.tileSize * 10, gp.tileSize);
			}
	        
	        if(existUser == true) {
				text = "*The user has already existed!";  // Hiển thị mật khẩu đã nhập (có thể thay bằng dấu sao cho bảo mật)
				g2.setFont(VT323.deriveFont(Font.PLAIN, 24f));
				g2.setColor(new Color(200, 0, 0));
				g2.drawString(text, gp.tileSize*10, y + gp.tileSize*2/3);
		    }else if(wrongConfirm == true){
		    	text = "*Password doesn't match, please try again!";  // Hiển thị mật khẩu đã nhập (có thể thay bằng dấu sao cho bảo mật)
				g2.setFont(VT323.deriveFont(Font.PLAIN, 24f));
				g2.setColor(new Color(200, 0, 0));
				g2.drawString(text, gp.tileSize*10, y + gp.tileSize*2/3);
		    }
		       
		       //reset g2
	        g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
		    g2.setColor(new Color(255, 140, 0));
				
	        
	        // Nút SIGN UP
	        text = "SIGN UP";
	        y += gp.tileSize * 2;
	        x = getXForCenteredText(text);
	        g2.drawString(text, x, y);
	        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}	

			text = "BACK";
	        y += (int)(gp.tileSize * 1.5);
	        x = getXForCenteredText(text);
	        g2.drawString(text, x, y);
	        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			
			if(commandNum == 4) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
	    }
		else if(titleScreenState == 0) {
			g2.setFont(VT323.deriveFont(Font.BOLD, 96f));
			String text = " Muramasa Adventure";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			
			//title name
			g2.setColor(Color.WHITE);
			g2.drawString(text, x + 3, y + 3);
			g2.setColor(new Color(255, 140, 0));
			g2.drawString(text, x + 4, y + 4);		
			y += gp.tileSize*1.5;
			
			//menu
			g2.setFont(VT323.deriveFont(Font.BOLD, 48f));
			
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize*3.7;
			int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}		
			
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize*1.3;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}

			text = "QUIT";
			x = getXForCenteredText(text);
			y += gp.tileSize*1.3;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
		} else if (titleScreenState == 1) {
			
			//class selection screen
			g2.setColor(new Color(255, 140, 0));
			g2.setFont(g2.getFont().deriveFont(42f));
			
			String text = "Select Level!";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
			int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			
			text = "Easy";
			x = getXForCenteredText(text);
			y += gp.tileSize*3;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
			
			text = "Medium";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
			
			text = "Hard";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
		
			text = "Asian";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
			
			text = "Back";
			x = getXForCenteredText(text);
			y += gp.tileSize*2;
			length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			g2.drawString(text, x, y);
			if(commandNum == 4) {
				g2.drawString(">", x - gp.tileSize, y);
				g2.drawString("<", x + gp.tileSize + length - sublength , y);
			}
		}
		username = username.trim();
		password = password.trim();
		confirmPassword = confirmPassword.trim();
	}

	public void drawIncantation(String currentDialogue) {
		//window
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 2;
	
		drawSubWindow(x, y, width, height);
		
		g2.setFont(VT323.deriveFont(28f));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			
			g2.drawString(line, x, y);
			y += 40;
		}
		 
	}
	
	public void drawMessage() {
		// TODO Auto-generated method stub
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(maruMonica.deriveFont(32F));
		
		for(int i = 0; i < message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i,  counter);
				messageY += 40;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
			
		}
	}
		
	public void drawDialogueScreen() {

		//window
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 3;
	
		drawSubWindow(x, y, width, height);
		
		g2.setFont(VT323.deriveFont(28f));
		x += gp.tileSize;
		y += gp.tileSize;
		
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
			
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if(charIndex < characters.length) {
				gp.playSe(17);
				String s = String.valueOf(characters[charIndex]);
				combinaedText = combinaedText + s;
				currentDialogue = combinaedText;
				charIndex++;
			}else {
				endLine = true;
			}
			
			if(gp.keyH.passDialoguePressed == true && endLine == false) {
				currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
				gp.keyH.passDialoguePressed = false;
				charIndex = characters.length;
				endLine = true;
			}
			
			if(endLine == true && (gp.keyH.passDialoguePressed == true || gp.keyH.tradeKeyPressed == true)) {
				
				charIndex = 0;
				combinaedText = "";
				endLine = false;
				
				if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutSceneState) {
					npc.dialogueIndex++;
					gp.keyH.passDialoguePressed = false;
					gp.keyH.tradeKeyPressed = false;
				}
			}
		}else {
			
			npc.dialogueIndex = 0;
			
			if(npc.name != null && npc.name.equals("mage")) {
				if(npc.dialogueSet == 0) {
					gp.endDialogue1 = true;
				}
				if(npc.dialogueSet == 1) {
					gp.endDialogue2 = true;
				}
			}
			if(npc.name != null && npc.name.equals("griffon") && gp.currentMap == 0) {
				gp.endDialogue3 = true;
			}
			if(npc.name != null && npc.name.equals("gate_dungeon")) {
				gp.eHandler.teleport(4, 42, 48, gp.dungeon, "up");
			}
			if(gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
				if(gp.endDialogue3 == false) {
					gp.eHandler.checkEvent();
				}
			}
			if(gp.gameState == gp.summonState) {
				gp.gameState = gp.playState;
			}
			if(gp.gameState == gp.cutSceneState) {
				gp.csManager.scenePhase++;
			}
		}
		
		for(String line : currentDialogue.split("\n")) {
			
			g2.drawString(line, x, y);
			y += 40;
		}
		 
	}
	
	private void drawCharacterScreen() {
		// TODO Auto-generated method stub
		
		//create a frame
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*7;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//text
		g2.setColor(Color.white);
		g2.setFont(VT323.deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 36;
		
		//names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 8;
		
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 12;
		
		g2.drawString("Armor", textX, textY);
		textY += lineHeight;
		
		//values
		int tailX = (frameX + frameWidth) - 32;
		//reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXForRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		//weapon
		if(gp.player.currentWeapon != null) {
			g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 30, null);
		}
		textY += gp.tileSize;
		if(gp.player.currentArmor != null) {
			g2.drawImage(gp.player.currentArmor.down1, tailX - gp.tileSize, textY - 30, null);			
		}
		//g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 30, null);
		 
	}
	
	private void drawSkill(Entity entity) {
	    int frameX = gp.tileSize * 7 + 24;
	    int frameY = gp.tileSize * 10;
	    int frameWidth = gp.tileSize * 6 + 18;
	    int frameHeight = gp.tileSize + 18;

	    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

	    final int slotXstart = frameX + 6;
	    final int slotYstart = frameY + 6;
	    int slotX = slotXstart;
	    int slotY = slotYstart;
	    int slotSize = gp.tileSize + 12;

	    int shorKeyX = 0, shortKeyY = 0;
        String shortKey = "";
        
	    g2.setColor(Color.white);
	    for (int i = 1; i < 5; i++) {
	        int lineX = slotXstart - 3 + i * slotSize;
	        g2.drawLine(lineX, slotYstart, lineX, slotYstart - 6 + slotSize);
	    }
	    
	    //skill1
	    g2.drawImage(gp.player.projectile.attackUp[0], slotX - 2, slotY - 2, gp.tileSize + 12, gp.tileSize + 12, null);
        g2.setFont(VT323.deriveFont(24f));
        
        shortKey = "U";
        shorKeyX = getXForRightText(shortKey, slotX + 20);
        shortKeyY = slotY + 20;

        g2.setColor(new Color(60, 60, 60));
        g2.drawString(shortKey, shorKeyX, shortKeyY);

        g2.setColor(Color.white);
        g2.drawString(shortKey, shorKeyX - 2, shortKeyY - 2);
        
        if(gp.player.canUseSkill1 == false) {
        	gp.player.changeAlpha(g2, 0.8f);	
        	g2.drawImage(lockSkill, slotX, slotY, gp.tileSize + 8, gp.tileSize + 8, null);
        	gp.player.changeAlpha(g2, 1f);
        }
        
        slotX += slotSize;
	    
        //skill2

        g2.drawImage(utilIcon, slotX - 2, slotY - 2, gp.tileSize + 8, gp.tileSize + 8, null);
        g2.setFont(VT323.deriveFont(24f));
        
        shortKey = "I";
        shorKeyX = getXForRightText(shortKey, slotX + 20);
        shortKeyY = slotY + 20;

        g2.setColor(new Color(60, 60, 60));
        g2.drawString(shortKey, shorKeyX, shortKeyY);

        g2.setColor(Color.white);
        g2.drawString(shortKey, shorKeyX - 2, shortKeyY - 2);
        
        if(gp.player.canUseSkill2 == false) {
        	gp.player.changeAlpha(g2, 0.8f);	
        	g2.drawImage(lockSkill, slotX, slotY, gp.tileSize + 8, gp.tileSize + 8, null);
        	gp.player.changeAlpha(g2, 1f);
        }
        
        slotX += slotSize;
        
        //skill3
        g2.drawImage(phoenixIcon, slotX, slotY + 3, gp.tileSize + 8, gp.tileSize + 8, null);
        g2.setFont(VT323.deriveFont(24f));
        
        shortKey = "O";
        shorKeyX = getXForRightText(shortKey, slotX + 20);
        shortKeyY = slotY + 20;

        g2.setColor(new Color(60, 60, 60));
        g2.drawString(shortKey, shorKeyX, shortKeyY);

        g2.setColor(Color.white);
        g2.drawString(shortKey, shorKeyX - 2, shortKeyY - 2);
        
        if(gp.player.canUseSkill3 == false) {
        	gp.player.changeAlpha(g2, 0.8f);	
        	g2.drawImage(lockSkill, slotX, slotY, gp.tileSize + 8, gp.tileSize + 8, null);
        	gp.player.changeAlpha(g2, 1f);
        }
        
        
        slotX += slotSize;
        
        for (int i = 0; i < entity.inventory.size(); i++) {
	        if (entity.inventory.get(i).name.equals("posion blue") || entity.inventory.get(i).name.equals("posion red")) {
	        	
	            g2.drawImage(entity.inventory.get(i).down1, slotX + 3, slotY, null);

	            if (entity.inventory.get(i).amount > 1) {
	                g2.setFont(VT323.deriveFont(24f));
	                String s = "" + entity.inventory.get(i).amount;

	                int amountX = getXForRightText(s, slotX + 52);
	                int amountY = slotY + gp.tileSize + 4;

	                g2.setColor(new Color(60, 60, 60));
	                g2.drawString(s, amountX, amountY);

	                g2.setColor(Color.white);
	                g2.drawString(s, amountX - 2, amountY - 2);
	            }
            
	            if(entity.inventory.get(i).name.equals("posion blue")) {
	            	shortKey = "E";
	            }else {
	            	shortKey = "R";
	            }
	            shorKeyX = getXForRightText(shortKey, slotX + 20);
	            shortKeyY = slotY + 20;

	            g2.setColor(new Color(60, 60, 60));
	            g2.drawString(shortKey, shorKeyX, shortKeyY);

	            g2.setColor(Color.white);
	            g2.drawString(shortKey, shorKeyX - 2, shortKeyY - 2);
	            
	            slotX += slotSize;
	        }
	    }
	}
	
	public void drawInventory(Entity entity, boolean cursor) {
		
		//frame
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			frameX = gp.tileSize*12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		} else {
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		//frame
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+4;
		
		//draw player's item
		for(int i = 0, tmp = 0; i < entity.inventory.size(); i++) {
			
			//equip cursor
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentBook ||
							entity.inventory.get(i) == entity.currentArmor ||
					entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);						
			//display amount
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				
				g2.setFont(VT323.deriveFont(24f));
				int amountX;
				int amountY;
			
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXForRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				//shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				
				//number
				g2.setColor(Color.white);
				g2.drawString(s, amountX - 2, amountY - 2);
			}
			
			slotX += slotSize;
			if(i + 1 == numberSlotX + tmp + tmp * numberSlotY) {
				slotX = slotXstart;
				slotY += slotSize;
				tmp++;
			}
		}
		
		//cursor
		if(cursor == true) {
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			//draw cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
			//description frame
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*4;
		
			//draw description text
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(VT323.deriveFont(28F));
			
			int itemIndex = getItemIndexSlot(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size()) {
				
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
		 
	}	
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(VT323.deriveFont(Font.BOLD, 110f));
		
		text = "GameOver";
		g2.setColor(Color.black);
		x = getXForCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		
		//main
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y -  4);
		
		//retry
		g2.setFont(VT323.deriveFont(50f));
		text = "Retry";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int sublength = (int)g2.getFontMetrics().getStringBounds("<", g2).getWidth();
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
			g2.drawString("<", x + gp.tileSize + length - sublength , y);
		}	
		
		//back to the title screen
		text = "Quit";
		x = getXForCenteredText(text);
		y += gp.tileSize*2;
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		g2.drawString(text, x, y);
		
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
			g2.drawString("<", x + gp.tileSize + length - sublength , y);
		}	
		 
	}	
	
	public void drawOptionScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(VT323.deriveFont(32F));
		
		//sub window
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotification(frameX, frameY);
			break;
		case 2:
			options_controls(frameX, frameY);
			break;
		case 3:
			options_endGameConfirm(frameX, frameY);
			break;
		}
		gp.keyH.enterPressed = false;
		 
	}	
	
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//title
		String text = "Options";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//full screen on off
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 24, textY);
			if (gp.keyH.enterPressed == true) {
			    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			    Main.window.dispose();  // Dispose the window
			    
			    if (gp.fullScreenOn == false) {
			        gp.fullScreenOn = true;
			
			        Main.window.setUndecorated(true);  // Remove window decorations
			        device.setFullScreenWindow(Main.window);  // Set to fullscreen
			    } else {
			        gp.fullScreenOn = false;
			        
			        device.setFullScreenWindow(null);  // Exit fullscreen
			        Main.window.setUndecorated(false);  // Add window decorations
			        Main.window.setVisible(true);  // Make the window visible
			    }
			    
			    subState = 1;
			}
		}
		
		//music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 24, textY);
		}
		
		//SE
		textY += gp.tileSize;
		g2.drawString("Sound Effect", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 24, textY);
		}
		
		//Control
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		//End game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
				gp.stopMusic();
			}
		}
		
		//back
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//full screen check box
		textX = frameX + gp.tileSize*4 + 24;
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true ) {
			g2.fillRect(textX, textY, 24, 24);
			
		}
				
		//music volume
		textY = frameY + gp.tileSize*3 + 24;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.VolumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//SE
		textY = frameY + gp.tileSize*4 + 24;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.VolumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	} 	
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "The change will take \neffect after restarting \nthe game";
	
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//back
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	
	public void options_controls(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//title
		String text = "Control";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Fireball", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Information", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pass mes", textX, textY);
		textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("J", textX, textY);
		textY += gp.tileSize;
		g2.drawString("U", textX, textY);
		textY += gp.tileSize;
		g2.drawString("C", textX, textY);
		textY += gp.tileSize;
		g2.drawString("P", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Space", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Enter", textX, textY);
		textY += gp.tileSize;
		
		//back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}	
	
	public void options_endGameConfirm(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Thoát game và trở về màn\nhình chính (tự động lưu)?";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//yes
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			if(gp.isLoading == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				titleScreenState = 0;
				gp.stopMusic();
				gp.playMusic(26);
				gp.saveLoad.save();
				gp.inProgress = true;
				gp.isLoading = false;
			}
			else {
				g2.drawString(">", textX - 24, textY);
				if(gp.keyH.enterPressed == true) {
					gp.isLoading = true;
					
				}
			}
		}

		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 24, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	}
	
	public void drawTransition() {
		// TODO Auto-generated method stub
		counter++;
		if(gp.isLoading == false) {
			gp.isLoading = true;
		}
		if(counter <= 50) {
			g2.setColor(new Color(0, 0, 0, counter*5));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		}
		if(counter > 50) {
			g2.drawImage(transition, 0, 0, null);
			if(gp.isLoading == false) {
				gp.isLoading = true;
			}
		}
		if(counter > 60) {
			counter = 0;
			gp.changeAttribute();
			gp.gameState = gp.playState;
			gp.isLoading = false;
		}
		
	}	
	
	private void drawTradeScreen() {
		// TODO Auto-generated method stub
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.tradeKeyPressed = false;
		  
	}
	
	public void trade_select() {
		
		npc.dialogueSet = 0;
		drawDialogueScreen();
		//draw window
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//draw texts
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.tradeKeyPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.tradeKeyPressed == true) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.tradeKeyPressed == true) {
				commandNum = 0;
				currentDialogue = "";
				npc.startDialogue(npc, 1);
			}
		}
		y += gp.tileSize;
	}
	
	public void trade_buy() {
		
		//draw player inventory
		drawInventory(gp.player, false);
		//draw npc inventory
		drawInventory(npc, true);
		
		//draw hint window
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);
	
		//draw player coin
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);
		
		//draw price window
		int itemIndex = getItemIndexSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.tileSize*5.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
		
			int price = npc.inventory.get(itemIndex).price;
			String text= "" + price;
			x = getXForRightText(text, gp.tileSize*7);
			g2.drawString(text, x, y + 34);
			
			//buy an item
			if(gp.keyH.tradeKeyPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					currentDialogue = "";
					npc.startDialogue(npc, 2);
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						currentDialogue = "";
						npc.startDialogue(npc, 3);
					}
				}
			}
		}
	}	
	
	public void trade_sell() {
		
		//draw player inventory
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;
		
		//draw hint window
		x = gp.tileSize * 2;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);
	
		//draw player coin
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);
		
		//draw price winodw
		int itemIndex = getItemIndexSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			
			x = (int)(gp.tileSize*15.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
		
			int price = gp.player.inventory.get(itemIndex).price;
			String text= "" + price;
			x = getXForRightText(text, gp.tileSize*17);
			g2.drawString(text, x, y + 34);
			
			//buy an item
			if(gp.keyH.tradeKeyPressed == true) {
				
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentArmor) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 4);
				}else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}else {
						gp.player.inventory.remove(itemIndex);						
					}
					gp.player.coin += price;
				}
			}
		}
	}		
	public int getItemIndexSlot(int slotCol, int slotRow) {
		return slotCol + (slotRow*numberSlotX);
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 0.7F);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 4, y + 4, width - 8, height - 8, 25, 25);
		 
	}

	public void drawPauseScreen() {
		
		g2.setFont(VT323.deriveFont(108f));
		String text = "GAME PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		 
	}

	public int getXForCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXForRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

}

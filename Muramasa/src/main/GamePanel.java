package main;

import javax.swing.JPanel;

import ai.PathFinder;
import animation.Loading;
import animation.Summon;
import animation.Thunder;
import data.Progress;
import data.SaveLoad;
import entity.Entity;
import entity.Monster;
import entity.NPC;
import entity.Bridge;
import entity.Player;
import entity.Projectile;
import entity.Tree;
import enviroment.EnviromentManager;
import object.OBJ_Stone;
import tile.Map;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Dimension;
import java.awt.Color;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	// screen setting
	final int originalTitleSize = 16; // 16x16
	final int scale = 3;
	
	public final int tileSize = originalTitleSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public int screenX;
	public int screenY;
	
	//world settings
	public int maxWorldCol = 20;
	public int maxWorldRow = 12;
	public int worldWidth = tileSize * maxWorldCol;
	public int worldHeight = tileSize * maxWorldRow;
	public final int maxMap = 6;
	public int currentMap = 0;
	
	//for full screen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;

	//FPS
	int FPS = 60;
	
	//system
	public String level = "";
	public Map map = new Map(this);
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	EnviromentManager eManage = new EnviromentManager(this);	
	SaveLoad saveLoad = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	public CutsceneManager csManager = new CutsceneManager(this);
	Thread gameThread;
	
	//entity and object
	public Summon aSummon = new Summon(this);
	public Thunder aThunder = new Thunder(this);
	public Loading loading = new Loading(this);
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][200];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][50];
	public Entity projectile[][] = new Entity[maxMap][20];
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//ai
	public PathFinder pFinder = new PathFinder(this);
	
	//game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	public final int cutSceneState = 11;
	public final int summonState = 12;
	
	//others
	public boolean dragonBattleOn = false;	
	public boolean ishigamiBattleOn = false;	
	public boolean defeatDragon = false;
	public boolean endSummon = false;
	public boolean endThunderSummon = false;
	public boolean afterSummon = false;
	public boolean endDialogue1 = false;
	public boolean endDialogue2 = false;
	public boolean endDialogue3 = false;
	public boolean setWorld = false;
	public boolean newGame = true;
	public boolean inProgress = false;
	public boolean isLoading = false;
	public boolean allowed = false;
	
	//area
	public int previousMap;
	public int currentArea;
	public int nextArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;
	
	public GamePanel() {
		super();
		this.screenX = 0;
		this.screenY = 0;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void changeAttribute() {
		currentMap = eHandler.nextMap;
		previousMap = eHandler.tempMap;

		changeTileMap();
		setWorld();
		
		/*player*/
		player.worldX = tileSize * eHandler.tempCol;
		player.worldY = tileSize * eHandler.tempRow;
		eHandler.previousEventX = player.worldX;
		eHandler.previousEventY = player.worldY;
		
		saveLoad.save();
	}
	
	public void changeTileMap() {
		changeArea();
		tileM.instantiate();
		map = new Map(this);
	}
	
	public void setWorld() {
		switch (currentMap) {
		case 1: {
			if(Progress.orcDefeated == false) {
				setWorld = true;
			}
			break;
		}
		case 2: {
			if(Progress.zombieWinterDefeated == false && previousMap != 5) {
				setWorld = true;
			}
			break;
		}
		case 3: {
			if(Progress.dragonDefeated == false) {
				setWorld = true;
			}
			break;
		}
		case 4: {
			if(Progress.ishigamiDefeated == false) {
				setWorld = true;
			}
			break;
		}
		case 5:{
			setWorld = true;
			break;
		}
	}
		if(setWorld == true ) {
			aSetter.setNPC();
			if(newGame == true) {
				aSetter.setMonster();
				aSetter.setObject();
			}
			setWorld = false;
		}
	}
	
	public void changeArea() {
		
		if(nextArea != currentArea) {
			map.setSizeMap();
			stopMusic();
			
			if(nextArea == outside) {
				playMusic(0);
			}
			if(nextArea == indoor) {
				playMusic(18);
			}
			if(nextArea == dungeon) {
				playMusic(19);
			}
			
		}
		currentArea = nextArea;
	}
	
	public void setupGame() {
		currentMap = 0;
		aSetter.setNPC();
		eManage.setup();
		gameState = titleState;
		playMusic(26);
		currentArea = indoor;
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
	}
	
	public void resetGame() {
		
		stopMusic();
		ui.resetCounter();
		removeTempEntity();
		
		for(int j = 0; j < maxMap; j++) {
			for(int i = 0; i < npc[j].length; i++) {
				if(npc[j][i] != null) {
					npc[j][i] = null;
				}
			}
		}
		dragonBattleOn = false;	
		ishigamiBattleOn = false;	
		defeatDragon = false;
		endSummon = false;
		endThunderSummon = false;
		afterSummon = false;
		endDialogue1 = false;
		endDialogue2 = false;
		endDialogue3 = false;
		setWorld = false;
		newGame = true;	
		currentMap = 0;
		currentArea = indoor;
		maxWorldCol = 20;
		maxWorldRow = 12;
		changeTileMap();
	    setWorld();
	    player.setDefaultValues();
		player.restoreStatus();
		player.resetCounter();
		saveLoad.updatePlayerId(ui.username, ui.password);
		saveLoad.reset();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setObject();	
		eManage.setup();
		playMusic(26);
	}
 	
	public void setFullScreen() {
		
		//get local screen device
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		//get full screen width and height
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
		
		screenX = 0;
		screenY = 0;
	}
	
	public void setMiniScreen() {
		
		//get local screen device
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		
		gd.setFullScreenWindow(null);
		
		//get full screen width and height
		screenWidth2 = screenWidth;
		screenHeight2 = screenHeight;
		
		//????
		screenX = (Main.window.getWidth() - screenWidth)/2 - 8;
		screenY = (Main.window.getHeight() - screenHeight)/2 - 18;
		
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				drawTempScreen();
				drawToScreen();
				delta--;
			}
			
		}
	}
	
	public void update() {
		
		if(currentMap == 0) {
			if (afterSummon == false && gameState != titleState) {
				ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
				scheduler.schedule(() -> {
				   eHandler.checkEvent();
				}, 2, TimeUnit.SECONDS);
			}
		}
		
		if(fullScreenOn == true) {
			setFullScreen();
		}else {
			setMiniScreen();
		}
		
		if(gameState == playState) {
			//player
			player.update();
			//npc
			
			for(int i = 0; i < npc[currentMap].length; i++) {
				if(npc[currentMap][i] != null) {

					if(npc[currentMap][i].alive == true && npc[currentMap][i].dying == false) {
						npc[currentMap][i].update();
					}
					
					if(npc[currentMap][i].alive == false) {
						npc[currentMap][i] = null;
					}
				}
			}
			
			if(currentMap != 0) {
				for(int i = 0; i < monster[currentMap].length; i++) {
					if(monster[currentMap][i] != null) {
						if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
							monster[currentMap][i].update();
						}
						
						if(monster[currentMap][i].alive == false) {
							monster[currentMap][i].checkDrop();
							monster[currentMap][i] = null;
						}
					}
				}
				
				for(int i = 0; i < projectile[currentMap].length; i++) {
					if(projectile[currentMap][i] != null) {
						if(projectile[currentMap][i].alive == true) {
							projectile[currentMap][i].update();
						}
						if(projectile[currentMap][i].alive == false) {
							projectile[currentMap][i] = null;
						}
					}
				}
				
				for(int i = 0; i < particleList.size(); i++) {
					if(particleList.get(i) != null) {
						if(particleList.get(i).alive == true) {
							particleList.get(i).update();
						}
						if(particleList.get(i).alive == false) {
							particleList.remove(i);
						}
					}
				}
			}
			
			eManage.update();
		}
		
		if(gameState == pauseState) {
			
			
		}
	}
	
	public void drawTempScreen() {
		//debug

		
		
		//title screen 
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		//map screen
		else if(gameState == mapState) {
			map.drawFullMapScreen(g2);
		}

		//other
		else {
			
			//tile
			tileM.draw(g2);
			
			for(int i = 0; i < npc[currentMap].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}

			if(currentMap != 0) {
				for(int i = 0; i < obj[currentMap].length; i++) {
					if(obj[currentMap][i] != null) {
						entityList.add(obj[currentMap][i]);
					}
				}
			}
			
			if(currentArea != indoor) {

				for(int i = 0; i < monster[currentMap].length; i++) {
					if(monster[currentMap][i] != null) {
						entityList.add(monster[currentMap][i]);
					}
				}
				
				for(int i = 0; i < projectile[currentMap].length; i++) {
					if(projectile[currentMap][i] != null) {
						entityList.add(projectile[currentMap][i]);
					}
				}
				
				for(int i = 0; i < particleList.size(); i++) {
					if(particleList.get(i) != null) {
						entityList.add(particleList.get(i));
					}
				}
			}
			
			if(afterSummon == true) {
				entityList.add(player);
				if(currentMap == 0) {
					aSummon.drawLastFrame(g2);
				}
			}

			Collections.sort(entityList, new Comparator<Entity>() {
			    @Override
			    public int compare(Entity e1, Entity e2) {
			        if (e1 == null || e2 == null) {
			            throw new IllegalArgumentException("Entities cannot be null.");
			        }

			        // Case 1: OBJ_Stone luôn trên cùng
			        if (e1 instanceof OBJ_Stone && !(e2 instanceof OBJ_Stone)) {
			            return -1;
			        }
			        if (e2 instanceof OBJ_Stone && !(e1 instanceof OBJ_Stone)) {
			            return 1;
			        }

			        // Case 2: Tree vs Player/NPC/Monster/Projectile
			        if ((e1 instanceof Player || e1 instanceof NPC || e1 instanceof Monster || e1 instanceof Projectile) && e2 instanceof Tree) {
			            return Integer.compare(e1.worldY, e2.worldY + tileSize * 3 - 12);
			        }
			        if ((e2 instanceof Player || e2 instanceof NPC || e2 instanceof Monster || e2 instanceof Projectile) && e1 instanceof Tree) {
			            return Integer.compare(e1.worldY + tileSize * 3 - 12, e2.worldY);
			        }

			        // Case 3: Bridge vs Player/NPC/Monster/Projectile
			        if ((e1 instanceof Player || e1 instanceof NPC || e1 instanceof Monster || e1 instanceof Projectile) && e2 instanceof Bridge) {
			            return 1; // Player luôn trên Bridge
			        }
			        if ((e2 instanceof Player || e2 instanceof NPC || e2 instanceof Monster || e2 instanceof Projectile) && e1 instanceof Bridge) {
			            return -1; // Bridge luôn dưới Player
			        }

			        // Case 4: General worldY comparison
					return Integer.compare(e1.worldY, e2.worldY);
			    }
			});

			//draw entities
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}

			//empty entity list
			entityList.clear();
			
			//enviroment
			eManage.draw(g2);

			//minimap
			map.drawMiniMap(g2);
			
			if(currentArea == dungeon || currentMap == 4 || currentMap == 3) {
				//cutscene
				csManager.draw(g2);
			}
			
			//ui
			ui.draw(g2);
		}
		if(isLoading == true) {
			loading.draw(g2);
		}
	}
	
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, screenX, screenY, screenWidth2, screenHeight2, null);
		g.dispose();
		
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSe(int i) {
		
		se.setFile(i);
		se.play();
	}

	public void removeTempEntity() {
		
		for(int mapNum = 0; mapNum < maxMap; mapNum++) {
			
			for (int i = 0; i < obj[currentMap].length; i++) {
				if(obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
					obj[mapNum][i] = null;
				}
			}
		}
	}
}
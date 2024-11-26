package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	boolean drawPath = true;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		instantiate();
	}
	
	public void instantiate() {
		switch (gp.currentMap) {
		case 0: tile = new Tile[14]; break;
		case 1: tile = new Tile[67]; break;
		case 2: tile = new Tile[39]; break;
		case 3: tile = new Tile[53]; break;
		case 4: tile = new Tile[48]; break;
		case 5: tile = new Tile[27]; break;
		}
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileIamge();
		switch (gp.currentMap) {
		case 0: loadMap("/maps/map00", 0); break;
		case 1: loadMap("/maps/map01", 1); break;
		case 2: loadMap("/maps/map02", 2); break;
		case 3: loadMap("/maps/map03", 3); break;
		case 4: loadMap("/maps/map04", 4); break;
		case 5: loadMap("/maps/map05", 5); break;
		}
	}
	
	private void getTileIamge() {
		
		if(gp.currentMap == 0) {
			setUp(0, "0126", true);
			setUp(1, "0127", true);
			setUp(2, "0128", true);
			setUp(3, "0129", true);
			setUp(4, "0130", true);
			setUp(5, "0131", true);
			setUp(6, "0132", false);
			setUp(7, "0133", true);
			setUp(8, "0134", true);
			setUp(9, "0135", true);
			setUp(10, "0136", true);
			setUp(11, "0137", true);
			setUp(12, "0138", true);
			setUp(13, "0139", true);
		}
		else if(gp.currentMap == 1) {
			setUp(0, "0000", false);
			setUp(1, "0001", true);
			setUp(2, "0002", false);
			setUp(3, "0003", true);
			setUp(4, "0004", false);
			setUp(5, "0005", true);
			setUp(6, "0006", true);
			setUp(7, "0007", true);
			setUp(8, "0008", true);
			setUp(9, "0009", true);
			setUp(10, "0010", true);
			setUp(11, "0011", false);
			setUp(12, "0012", false);
			setUp(13, "0013", false);
			setUp(14, "0014", false);
			setUp(15, "0015", false);
			setUp(16, "0016", false);
			setUp(17, "0017", false);
			setUp(18, "0018", false);
			setUp(19, "0019", false);
			setUp(20, "0020", false);
			setUp(21, "0021", false);
			setUp(22, "0022", false);
			setUp(23, "0023", false);
			setUp(24, "0024", false);
			setUp(25, "0025", false);
			setUp(26, "0026", false);
			setUp(27, "0027", false);
			setUp(28, "0028", false);
			setUp(29, "0029", false);
			setUp(30, "0030", false);
			setUp(31, "0031", false);
			setUp(32, "0032", false);
			setUp(33, "0033", false);
			setUp(34, "0034", false);
			setUp(35, "0035", false);
			setUp(36, "0036", false);
			setUp(37, "0037", false);
			setUp(38, "0038", true);
			setUp(39, "0039", false);
			setUp(40, "0040", false);
			setUp(41, "0041", true);
			setUp(42, "0042", true);
			setUp(43, "0043", true);
			setUp(44, "0044", true);
			setUp(45, "0045", true);
			setUp(46, "0046", true);
			setUp(47, "0047", true);
			setUp(48, "0048", true);
			setUp(49, "0049", true);
			setUp(50, "0050", false);
			setUp(51, "0051", false);
			setUp(52, "0052", false);
			setUp(53, "0053", false);
			setUp(54, "0054", true);
			setUp(55, "0055", true);
			setUp(56, "0056", false);
			setUp(57, "0057", false);
			setUp(58, "0058", false);
			setUp(59, "0059", false);
			setUp(60, "0060", false);
			setUp(61, "0061", false);
			setUp(62, "0062", false);
			setUp(63, "0063", false);
			setUp(64, "0064", false);
			setUp(65, "0065", false);
			setUp(66, "0066", false);

		}
		else if(gp.currentMap == 2) {
			setUp(0, "0140", false);
			setUp(1, "0141", false);
			setUp(2, "0142", false);
			setUp(3, "0143", false);
			setUp(4, "0144", false);
			setUp(5, "0145", false);
			setUp(6, "0146", false);
			setUp(7, "0147", false);
			setUp(8, "0148", false);
			setUp(9, "0149", false);
			setUp(10, "0150", true);
			setUp(11, "0151", true);
			setUp(12, "0152", true);
			setUp(13, "0153", true);
			setUp(14, "0154", false);
			setUp(15, "0155", false);
			setUp(16, "0156", true);
			setUp(17, "0157", true);
			setUp(18, "0158", true);
			setUp(19, "0159", true);
			setUp(20, "0160", true);
			setUp(21, "0161", true);
			setUp(22, "0162", true);
			setUp(23, "0163", false);
			setUp(24, "0164", false);
			setUp(25, "0165", false);
			setUp(26, "0166", false);
			setUp(27, "0167", false);
			setUp(28, "0168", false);
			setUp(29, "0169", false);
			setUp(30, "0170", false);
			setUp(31, "0171", false);
			setUp(32, "0172", false);
			setUp(33, "0173", false);
			setUp(34, "0174", false);
			setUp(35, "0175", false);
			setUp(36, "0176", false);
			setUp(37, "0177", false);
			setUp(38, "0178", true);
		}
		else if(gp.currentMap == 3) {
			setUp(0, "0179", false);
			setUp(1, "0180", false);
			setUp(2, "0181", false);
			setUp(3, "0182", false);
			setUp(4, "0183", false);
			setUp(5, "0184", false);
			setUp(6, "0185", false);
			setUp(7, "0186", false);
			setUp(8, "0187", false);
			setUp(9, "0188", true);
			setUp(10, "0189", false);
			setUp(11, "0190", false);
			setUp(12, "0191", false);
			setUp(13, "0192", false);
			setUp(14, "0193", false);
			setUp(15, "0194", false);
			setUp(16, "0195", false);
			setUp(17, "0196", false);
			setUp(18, "0197", false);
			setUp(19, "0198", true);
			setUp(20, "0199", true);
			setUp(21, "0200", true);
			setUp(22, "0201", true);
			setUp(23, "0202", true);
			setUp(24, "0203", true);
			setUp(25, "0204", true);
			setUp(26, "0205", true);
			setUp(27, "0206", false);
			setUp(28, "0207", false);
			setUp(29, "0208", false);
			setUp(30, "0209", false);
			setUp(31, "0210", false);
			setUp(32, "0211", false);
			setUp(33, "0212", false);
			setUp(34, "0213", false);
			setUp(35, "0214", false);
			setUp(36, "0215", false);
			setUp(37, "0216", false);
			setUp(38, "0217", false);
			setUp(39, "0218", false);
			setUp(40, "0219", false);
			setUp(41, "0220", false);
			setUp(42, "0221", false);
			setUp(43, "0222", false);
			setUp(44, "0223", false);
			setUp(45, "0224", false);
			setUp(46, "0225", false);
			setUp(47, "0226", false);
			setUp(48, "0227", false);
			setUp(49, "0228", false);
			setUp(50, "0229", false);
			setUp(51, "0230", false);
			setUp(52, "0231", false);
		}
		else if(gp.currentMap == 4) {
			setUp(0, "0232", true);
			setUp(1, "0233", true);
			setUp(2, "0234", true);
			setUp(3, "0235", true);
			setUp(4, "0236", true);
			setUp(5, "0237", true);
			setUp(6, "0238", true);
			setUp(7, "0239", false); 
			setUp(8, "0240", false);
			setUp(9, "0241", false);
			setUp(10, "0242", false);
			setUp(11, "0243", false);
			setUp(12, "0244", false);
			setUp(13, "0245", false);
			setUp(14, "0246", false);
			setUp(15, "0247", false);
			setUp(16, "0248", false);
			setUp(17, "0249", false);
			setUp(18, "0232", false); //later
			setUp(19, "0232", false); //later
			setUp(20, "0252", true);
			setUp(21, "0253", true);
			setUp(22, "0254", false);
			setUp(23, "0255", false);
			setUp(24, "0256", false);
			setUp(25, "0257", false);
			setUp(26, "0258", false);
			setUp(27, "0259", false);
			setUp(28, "0260", false);
			setUp(29, "0261", false);
			setUp(30, "0262", false);
			setUp(31, "0263", false);
			setUp(32, "0264", false);
			setUp(33, "0265", false);
			setUp(34, "0266", false);
			setUp(35, "0267", false);
			setUp(36, "0268", false);
			setUp(37, "0269", true);
			setUp(38, "0270", false);
			setUp(39, "0271", false);
			setUp(40, "0272", false);
			setUp(41, "0273", false);
			setUp(42, "0274", false);
			setUp(43, "0275", true);
			setUp(44, "0276", false);
			setUp(45, "0277", false);
			setUp(46, "0278", false);
			setUp(47, "0279", false);
		}
		else if(gp.currentMap == 5) {
			setUp(0, "0280", false);
			setUp(1, "0281", false);
			setUp(2, "0282", false);
			setUp(3, "0283", false);
			setUp(4, "0284", false);
			setUp(5, "0285", false);
			setUp(6, "0286", false);
			setUp(7, "0287", false);
			setUp(8, "0288", false);
			setUp(9, "0289", true);
			setUp(10, "0290", true);
			setUp(11, "0291", true);
			setUp(12, "0292", true);
			setUp(13, "0293", true);
			setUp(14, "0294", true);
			setUp(15, "0295", true);
			setUp(16, "0296", true);
			setUp(17, "0297", true);
			setUp(18, "0298", true);
			setUp(19, "0299", true);
			setUp(20, "0300", true);
			setUp(21, "0301", true);
			setUp(22, "0302", true);
			setUp(23, "0303", true);
			setUp(24, "0304", true);
			setUp(25, "0305", true);
			setUp(26, "0306", true);
		}
	}
	
	private void setUp(int index, String imageName, boolean collision) {
			
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index]  = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadMap(String filemap, int map) {
		try {
		
			InputStream is = getClass().getResourceAsStream(filemap);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;	
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			is.close();
		}catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//stop moving camera at the edge
			if(gp.player.screenX > gp.player.worldX) {
				screenX = worldX;
			}
			if(gp.player.screenY > gp.player.worldY) {
				screenY = worldY;
			}
			int rightOffset = gp.screenWidth - gp.player.screenX;
			if(rightOffset > gp.worldWidth - gp.player.worldX) {
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			}
			int bottomOfset = gp.screenHeight - gp.player.screenY;
			if(bottomOfset > gp.worldHeight - gp.player.worldY) {
				screenY = gp.screenHeight - (gp.worldHeight - worldY);
			}
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				if (tile[tileNum] != null && tile[tileNum].image != null) {
				    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				} 
			}
			else if(gp.player.screenX > gp.player.worldX ||
					gp.player.screenY > gp.player.worldY ||
					rightOffset > gp.worldWidth - gp.player.worldX ||
					bottomOfset > gp.screenHeight - gp.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			 
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
//		if(drawPath == true) {
//			g2.setColor(new Color(255, 0, 0, 70));
//			
//			for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
//				
//				int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
//				int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
//				int screenX = worldX - gp.player.worldX + gp.player.screenX;
//				int screenY = worldY - gp.player.worldY + gp.player.screenY;
//				
//				g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//			}
//		}
	}
}

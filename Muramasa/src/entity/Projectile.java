package entity;

import java.util.List;

import main.GamePanel;

public class Projectile extends Entity{

	protected Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}

	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		
	}
	
	public void update() {
		
		if(user == gp.player) {
			List<Integer> monsterIndex  = gp.cChecker.checkEntity(this, gp.monster);
			if(!monsterIndex.isEmpty()) {
				for(int i = 0; i < monsterIndex.size(); i++) {
					gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
					if(!name.equals("util")) {
						generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex.get(i)]);
					}
				}
				if(!name.equals("util")) {
					alive = false;					
				}
			}	
		}
		
		if(user != gp.player) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				generateParticle(user.projectile, gp.player);
				alive = false;
			}
		}
		
		if(name.equals("spike rock") || name.equals("spike arrow")) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				generateParticle(this, gp.player);
				alive = false;
			}
		}
		
		getNextPosition();

		life--;
		if(life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	}
	
	public void getNextPosition() {
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		case "top_left" : {
			worldX -= speed;
			worldY -= speed;
			break;
		}
		case "top_right" : {
			worldX += speed;
			worldY -= speed;
			break;
		}
		case "bottom_left" : {
			worldX -= speed;
			worldY += speed;
			break;
		}
		case "bottom_right" : {
			worldX += speed;
			worldY += speed;
			break;
		}
		
		}
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	
	public void subtractResource(Entity user) {}
	
	public void getAttackImage(String level) {}
}

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, useManaKeyPressed, useLifeKeyPressed, passDialoguePressed, guardPressed, shotKeyPressed, utilKeyPressed, summonKeyPressed, tradeKeyPressed;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void keyTyped(KeyEvent e) {
	    char keyChar = e.getKeyChar();

	    // Kiểm tra nếu là phím Enter hoặc ký tự không hợp lệ
	    if (Character.isISOControl(keyChar) && keyChar != KeyEvent.VK_BACK_SPACE) {
	        return;  // Bỏ qua các ký tự điều khiển (Enter, Tab, ...)
	    }

	    if (gp.ui.titleScreenState == -2) {  // Đăng nhập
	        if (gp.ui.commandNum == 0) {  // Nhập tên người dùng
	            if (keyChar == KeyEvent.VK_BACK_SPACE && gp.ui.username.length() > 0) {
	                gp.ui.username = gp.ui.username.substring(0, gp.ui.username.length() - 1);  // Xóa ký tự cuối
	            } else {
	                gp.ui.username += keyChar;  // Thêm ký tự vào tên người dùng
	            }
	        } else if (gp.ui.commandNum == 1) {  // Nhập mật khẩu
	            if (keyChar == KeyEvent.VK_BACK_SPACE && gp.ui.password.length() > 0) {
	                gp.ui.password = gp.ui.password.substring(0, gp.ui.password.length() - 1);  // Xóa ký tự cuối
	            } else {
	                gp.ui.password += keyChar;  // Thêm ký tự vào mật khẩu
	            }
	        }
	    } else if (gp.ui.titleScreenState == -1) {  // Đăng ký
	        if (gp.ui.commandNum == 0) {  // Nhập tên người dùng
	            if (keyChar == KeyEvent.VK_BACK_SPACE && gp.ui.username.length() > 0) {
	                gp.ui.username = gp.ui.username.substring(0, gp.ui.username.length() - 1);  // Xóa ký tự cuối
	            } else {
	                gp.ui.username += keyChar;  // Thêm ký tự vào tên người dùng
	            }
	        } else if (gp.ui.commandNum == 1) {  // Nhập mật khẩu
	            if (keyChar == KeyEvent.VK_BACK_SPACE && gp.ui.password.length() > 0) {
	                gp.ui.password = gp.ui.password.substring(0, gp.ui.password.length() - 1);  // Xóa ký tự cuối
	            } else {
	                gp.ui.password += keyChar;  // Thêm ký tự vào mật khẩu
	            }
	        } else if (gp.ui.commandNum == 2) {  // Nhập xác nhận mật khẩu
	            if (keyChar == KeyEvent.VK_BACK_SPACE && gp.ui.confirmPassword.length() > 0) {
	                gp.ui.confirmPassword = gp.ui.confirmPassword.substring(0, gp.ui.confirmPassword.length() - 1);  // Xóa ký tự cuối
	            } else {
	                gp.ui.confirmPassword += keyChar;  // Thêm ký tự vào xác nhận mật khẩu
	            }
	        }
	    }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
	
		if(gp.isLoading == false) {
			//title state
			if(gp.gameState == gp.titleState) {
				titleState(code);
			}
			
			//play state
			else if(gp.gameState == gp.playState) {
				playState(code);
			}
			//pause state
			else if(gp.gameState == gp.pauseState) {
				pauseState(code);
			}
			
			//dialogue state
			else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutSceneState) {
				dialogueState(code);
			}
			//character State
			else if(gp.gameState == gp.characterState) {
				characterState(code);
			}
			//option State
			else if(gp.gameState == gp.optionState) {
				optionState(code);
			}
			//game over state 
			else if(gp.gameState == gp.gameOverState) {
				gameOverState(code);
			}
			//trade state 
			else if(gp.gameState == gp.tradeState) {
				tradeState(code);
			}
			//map state 
			else if(gp.gameState == gp.mapState) {
				mapState(code);
			}
		}
	}

	public void titleState(int code) {
		int maxCommandNum = 0;
		if(gp.ui.titleScreenState == -3) {
			maxCommandNum = 2;
			if (code == KeyEvent.VK_ENTER) {
				gp.playSe(9);
				
				if(gp.ui.commandNum == 0) {
					gp.ui.titleScreenState = -2;
					gp.ui.commandNum = 0;
				}
				
				if(gp.ui.commandNum == 1) {
					gp.ui.titleScreenState = -1;
					gp.ui.commandNum = 0;
					
				}
				
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else if(gp.ui.titleScreenState == -2) {
			maxCommandNum = 4;
			if(gp.ui.commandNum == 2 && gp.isLoading == true) {
				gp.saveLoad.updatePlayerId(gp.ui.username, gp.ui.password);
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum = 0;
				gp.isLoading = false;
			}
			if (code == KeyEvent.VK_ENTER) {
				gp.playSe(9);
				
				if(gp.ui.commandNum == 0) {
					gp.ui.commandNum = 1;
				}
				else if(gp.ui.commandNum == 1) {
					gp.ui.commandNum = 2;
				}		
				else if(gp.ui.commandNum == 2) {
					if(gp.saveLoad.checkPassword(gp.ui.username, gp.ui.password) == false) {
						gp.ui.wrongPass = true;
						gp.ui.password = "";
						gp.ui.commandNum = 1;
					}else {
						gp.isLoading = true;
						gp.loading.titleState = true;
					}
				}
				else if(gp.ui.commandNum == 3) {
					gp.ui.titleScreenState = -3;
					gp.ui.commandNum = 0;
				}
			}
		}
		else if(gp.ui.titleScreenState == -1) {
			maxCommandNum = 4;
			if(gp.ui.commandNum == 3 && gp.isLoading == true) {
				gp.saveLoad.addAccount(gp.ui.username, gp.ui.password);
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum = 0;
				gp.isLoading = false;
			}
			if (code == KeyEvent.VK_ENTER) {
				gp.playSe(9);
				
				if(gp.ui.commandNum == 0) {
					gp.ui.commandNum = 1;
				}
				else if(gp.ui.commandNum == 1) {
					gp.ui.commandNum = 2;
				}
				else if(gp.ui.commandNum == 2) {
					gp.ui.commandNum = 3;
				}
				else if(gp.ui.commandNum == 3) {
					if(!gp.ui.password.equals(gp.ui.confirmPassword)) {
						gp.ui.wrongConfirm = true;
						gp.ui.confirmPassword = "";
						gp.ui.commandNum = 2;
					}else {
						if(gp.saveLoad.checkExistAccount(gp.ui.username) == true) {
							gp.ui.existUser = true;
							gp.ui.username = "";
							gp.ui.password = "";
							gp.ui.confirmPassword = "";
							gp.ui.commandNum = 0;
						}else {
							gp.isLoading = true;
							gp.loading.titleState = true;
						}
					}
				}
				else if(gp.ui.commandNum == 4) {
					gp.ui.titleScreenState = -3;
					gp.ui.commandNum = 0;
				}
			}
		}
		else if(gp.ui.titleScreenState == 0) {
			maxCommandNum = 2;
			
			if(gp.ui.commandNum == 0 && gp.isLoading == true) {
				if(gp.inProgress == true) {
					gp.resetGame();
				}
				gp.ui.titleScreenState = 1;
				gp.isLoading = false;
			}
			else if(gp.ui.commandNum == 1 && gp.isLoading == true) {
				gp.newGame = false;
				gp.saveLoad.load();
				gp.gameState = gp.playState;
				gp.playMusic(23);
				gp.isLoading = false;
			}
			
			if (code == KeyEvent.VK_ENTER) {
				gp.playSe(9);
				
				if(gp.ui.commandNum == 0 || gp.ui.commandNum == 1) {
					gp.isLoading = true;
					gp.loading.titleState = true;
				}
				
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else if(gp.ui.titleScreenState == 1) {
			
			maxCommandNum = 4;
			
			if (code == KeyEvent.VK_ENTER) {
				gp.playSe(9);
				if(gp.ui.commandNum == 0) {
					gp.stopMusic();
					gp.gameState = gp.playState;
					gp.level = "easy";
					gp.playMusic(23);
					gp.ui.commandNum = 0;
				}
				
				if(gp.ui.commandNum == 1) {
					//later
					gp.stopMusic();
					gp.playMusic(23);
					gp.gameState = gp.playState;
					gp.level = "medium";
					gp.ui.commandNum = 0;
				}
				
				if(gp.ui.commandNum == 2) {
					//later
					gp.stopMusic();
					gp.playMusic(23);
					gp.gameState = gp.playState;
					gp.level = "hard";
					gp.ui.commandNum = 0;
				}
				
				if(gp.ui.commandNum == 3) {
					//later
					gp.stopMusic();
					gp.playMusic(23);
					gp.gameState = gp.playState;
					gp.level = "asian";
					gp.ui.commandNum = 0;
				}
				
				if(gp.ui.commandNum == 4) {
					gp.ui.titleScreenState = 0;
					gp.ui.commandNum = 0;
				}
			}
		}
		if ((code == KeyEvent.VK_W && gp.ui.titleScreenState >= 0) || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSe(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		
		if ((code == KeyEvent.VK_S && gp.ui.titleScreenState >= 0) || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSe(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
	}
	
	public void playState(int code) {
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;	
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (gp.player.canAttack == true && gp.player.attacking == false) {
			if(code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) { 
				gp.playSe(7);
				gp.player.attacking = true;
				gp.player.canAttack = false;
				gp.player.cooldownCounter = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_F) {
			guardPressed = true;
		}
		if (code == KeyEvent.VK_E) {
			useManaKeyPressed = true;
		}
		if (code == KeyEvent.VK_R) {
			useLifeKeyPressed = true;
		}
		if (code == KeyEvent.VK_U || code == KeyEvent.VK_NUMPAD4) {
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_I || code == KeyEvent.VK_NUMPAD5) {
			utilKeyPressed = true;
		}
		if (code == KeyEvent.VK_O || code == KeyEvent.VK_NUMPAD6) {
			summonKeyPressed = true;
		}
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_SPACE) {
			gp.gameState = gp.optionState;
		}
		if (code == KeyEvent.VK_M) {
			gp.gameState = gp.mapState;
		}
		if (code == KeyEvent.VK_X) {
			if(gp.map.miniMapOn == false) {
				gp.map.miniMapOn = true;
			}else {
				gp.map.miniMapOn = false;
			}
		}
	}
	
	public void pauseState(int code) {
		
		if (code == KeyEvent.VK_P || code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code) {
		
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			passDialoguePressed = true;
		}
	}
	
	public void characterState(int code) {
		
		if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE) {
			gp.gameState = gp.playState;
		}
		
		playerInventory(code);
		
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) {
			gp.player.selectItem();
			gp.playSe(9);
		}
	}

	private void optionState(int code) {
		// TODO Auto-generated method stub
		if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			gp.ui.commandNum = 0;
		}
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 2: maxCommandNum = 0; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.playSe(9);
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.playSe(9);
			gp.ui.commandNum++;
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.VolumeScale > 0) {
					gp.music.VolumeScale--;
					gp.music.checkVolume();
					gp.playSe(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.VolumeScale > 0) {
					gp.se.VolumeScale--;
					gp.playSe(9);
				}
			}
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.VolumeScale < 5) {
					gp.music.VolumeScale++;
					gp.music.checkVolume();
					gp.playSe(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.VolumeScale < 5) {
					gp.se.VolumeScale++;
					gp.playSe(9);
				}
			}
		}
	}
	
	public void gameOverState(int code) {
		// TODO Auto-generated method stub
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.playSe(9);
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
		}
		
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.playSe(9);
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}
		if( gp.isLoading == true) {
			if(gp.ui.commandNum == 0) {
				gp.eManage.lighting.dayCounter = 0;
				gp.eManage.lighting.dayState = gp.eManage.lighting.day;
				if(gp.currentMap < 4) {
					gp.playMusic(0);
				}else if(gp.currentMap < 5) {
					gp.playMusic(19);
				}else {
					gp.playMusic(18);
				}
				gp.saveLoad.load();
				gp.gameState = gp.playState;
				
			}else {				
				gp.gameState = gp.titleState;
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum = 0;
				gp.resetGame();
				gp.stopMusic();
				gp.playMusic(26);
			}
			gp.isLoading = false;
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.isLoading = true;
			gp.loading.overState = true;
		}
	}

	private void tradeState(int code) {
		// TODO Auto-generated method stub
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) {
			tradeKeyPressed = true;
		}
		
		if(gp.ui.subState == 0) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.playSe(9);
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			
			if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.playSe(9);
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			
		}
		
		if(gp.ui.subState == 0) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.playState; 
			}
		}
		
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE) {
				gp.ui.subState = 0; 
			}
		}
		
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE) {
				gp.ui.subState = 0; 
			}
		}
		
	}
	
	public void mapState(int code) {
		if (code == KeyEvent.VK_M || code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
	}
	
	public void playerInventory(int code) {
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.playerSlotRow == 0) {
				gp.ui.playerSlotRow = gp.ui.numberSlotY - 1;
			}else {
				gp.ui.playerSlotRow--;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.playerSlotRow == gp.ui.numberSlotY - 1) {
				gp.ui.playerSlotRow = 0;
			}else {
				gp.ui.playerSlotRow++;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.playerSlotCol == 0) {
				gp.ui.playerSlotCol = gp.ui.numberSlotX - 1;
			}else {
				gp.ui.playerSlotCol--;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.playerSlotCol == gp.ui.numberSlotX - 1) {
				gp.ui.playerSlotCol = 0;
			}else {
				gp.ui.playerSlotCol++;
			}
			gp.playSe(9);
		}
	}
	
	public void npcInventory(int code) {
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.npcSlotRow == 0) {
				gp.ui.npcSlotRow = gp.ui.numberSlotY - 1;
			}else {
				gp.ui.npcSlotRow--;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.npcSlotRow == gp.ui.numberSlotY - 1) {
				gp.ui.npcSlotRow = 0;
			}else {
				gp.ui.npcSlotRow++;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.npcSlotCol == 0) {
				gp.ui.npcSlotCol = gp.ui.numberSlotX - 1;
			}else {
				gp.ui.npcSlotCol--;
			}
			gp.playSe(9);
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.npcSlotCol == gp.ui.numberSlotX - 1) {
				gp.ui.npcSlotCol = 0;
			}else {
				gp.ui.npcSlotCol++;
			}
			gp.playSe(9);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;	
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}		
		if (code == KeyEvent.VK_F) {
			guardPressed = false;
		}
		if (code == KeyEvent.VK_E) {
			useManaKeyPressed = false;
		}
		if (code == KeyEvent.VK_R) {
			useLifeKeyPressed = false;
		}
		if (code == KeyEvent.VK_U || code == KeyEvent.VK_NUMPAD4) {
			shotKeyPressed = false;
		}
		if (code == KeyEvent.VK_I || code == KeyEvent.VK_NUMPAD5) {
			utilKeyPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
	}
	
}

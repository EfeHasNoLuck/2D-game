package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
	//debug
	public boolean debug = false;
	public boolean godMode = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE
		if(gp.gameState == gp.titleState)	{
			titleState(code);
		}
		
		// PLAY
		else if(gp.gameState == gp.playState){
			playState(code);
		}
		
		// PAUSE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// DIALOGUE
		else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
			dialogueState(code);
		}
		
		// STATS
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		// OPTION
		else if(gp.gameState == gp.optionsState) {
			optionState(code);
		}
		// GAMEOVER
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		// TRADE
		else if(gp.gameState == gp.tradeState) {
			tradeState(code);
		}
	}
	
	public void titleState(int code) {
		if(code == KeyEvent.VK_W)
		{
			 gp.playSE(11);
			 gp.ui.commandNum--;
			 if(gp.ui.commandNum < 0) {
				 gp.ui.commandNum = 2;
			 }
		}
		if(code == KeyEvent.VK_S)
		{
			 gp.playSE(11);
			gp.ui.commandNum++;
			 if(gp.ui.commandNum > 2) {
				 gp.ui.commandNum = 0;
			 }
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.stopMusic();
				gp.playMusic(6,0.8F);
			}
			if(gp.ui.commandNum == 1) {
				gp.saveLoad.load();
				gp.gameState = gp.playState;
				gp.stopMusic();
				gp.playMusic(6,0.8F);
			}
			if(gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		//debug
		if(code == KeyEvent.VK_T){
			 debug = !debug; 
		}
		if(code == KeyEvent.VK_G){
			 godMode = !godMode; 
		}
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
		gp.gameState = gp.playState;
	}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}
	public void characterState(int code) {
	
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		playerInventory(code);
	}

	public void optionState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(11);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(11);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(11);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(11);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(11);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(11);
				}
			}
		}
	}
	public void gameOverState(int code) {
	
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(11);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(11);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.resetGame(false);
				gp.stopMusic();
				if(gp.currentMap == 0) {
					gp.playMusic(6, 0.8F);
				}
				if(gp.currentMap == 1) {
					gp.playMusic(18, 0.8F);
				}
				if(gp.currentMap == 2) {
					gp.playMusic(18, 0.8F);
				}
				if(gp.currentMap == 3) {
					gp.playMusic(18, 0.8F);
				}

			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.resetGame(true);
			}
		}
	}
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(11);
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(11);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	public void playerInventory(int code) {
	
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(11);
			}
		}
	}
	
	public void npcInventory(int code) {
		
		if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(11);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(11);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}

package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.PlayerFake;
import monster.MON_SkeletonLord;
import object.OBJ_Door_Iron;
import object.OBJ_Eclipse;

public class CutsceneManager {

	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter = 0;
	float alpha = 0f;
	int y;
	String endCredit;
	
	// Scene Number
	public final int NA = 0;
	public final int skeletonLord = 1;
	public final int ending = 2;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
		
		endCredit = "Program/Music/Art\n"
				+ "Ahmed Hamza KERMAN\n"
				+ "Ahmet Efe SAYGILI\n"
				+ "Hasan Ulaş ÇELİK\n"
				+ "Hüseyin Can ÇALTI"
				+ "\n\n\n\n\n\n\n\n"
				+ "Özel Teşekkürler\n"
				+ "RyiSnow\n"
				+ "piskelapp.com"
				+ "\n\n\n\n\n\n"
				+ "Oynadığınız için teşekkürler!";
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {  // if we want to create more than one cutscene for the game we can use this switch
		case skeletonLord: scene_skeletonLord(); break; 
		case ending: scene_ending(); break;
		}
	}
	
	public void scene_skeletonLord() {
		
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			// iron door behind us
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize * 51;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize * 44;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(25);
					break;
				}
			}
			// Search vacant slot for the copy of our character
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerFake(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			
			scenePhase++;
		}

		if(scenePhase == 1) {
			gp.player.worldY -= 2;
			
			if(gp.player.worldY < gp.tileSize*31) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			for(int i = 0; i < gp.monster[1].length; i++) {
				if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName) {
					
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++; 
					break;
				}
			}
		}
		if(scenePhase == 3) {
			
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 4) {
			
			// return to player
			
			//search the fake player that stands for us for only visibility
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerFake.npcName)) {
					// restore the player
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					// delete the place holder char the fake one
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			gp.player.drawing = true;
			
			// reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// Change the music
			gp.stopMusic();
			gp.playMusic(26, 0.8F);
		}
	}
	public void scene_ending() {
		
		if(scenePhase == 0) {
			
			gp.stopMusic();
			gp.ui.npc = new OBJ_Eclipse(gp);
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			// dialogues
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 2) {
			
			// play the ending sound 
			gp.playSE(4);
			scenePhase++;
		}
		if(scenePhase == 3) {
			
			if(counterReached(300) == true) {
				scenePhase++;
				gp.playMusic(27, 0.8F);
			}
		}
		if(scenePhase == 4) {
			
			// screen gets darker
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackGround(alpha);
			
			if(alpha == 1f) {
				alpha = 0;
				scenePhase++;
			}
		}
		if(scenePhase == 5) {
			
			drawBlackBackGround(1f);
			
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "Hüseyin'in GPT ile yaptığı savaştan sonra \n"
					+ "sonunda Hayatın Yapıtaşı Eclipse taşını buldu.\n"
					+ "Ama bu onun yolculuğunun sonu değil\n" 
					+ "İstün'deki maceralar yeni başlıyor";
			
			drawString(alpha, 38f ,200, text, 70);
			
			if(counterReached(600) == true) {
				//gp.playMusic(18, 0.8F);
				scenePhase++;
			}
		}
		if(scenePhase == 6) {
			
			drawBlackBackGround(1f);
			
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "SİSTEM GÜNCELLENDİ...\n"
					+ "BİR SONRAKİ AŞAMAYA GEÇİLİYOR...\n"
					+ "YÖNETİCİLER ÇEVRİMİÇİ...\n";
			
			drawString(alpha, 38f ,200, text, 70);
			
			if(counterReached(600) == true) {
			//	gp.stopMusic();
			//	gp.playMusic(18, 0.8F);
				scenePhase++;
			}
		}
		if(scenePhase == 7) {
			
			drawBlackBackGround(1f);
			
			drawString(1f, 120f, gp.screenHeight/2, "İstüne Hoş Geldiniz", 40);
			
			if(counterReached(480) == true) {
				//gp.stopMusic();
				//gp.playMusic(18, 0.8F);
				scenePhase++;
			}
		}
		if(scenePhase == 8) {
			
			drawBlackBackGround(1f);
			
			y = gp.screenHeight/2;
			drawString(1f, 38f, y, endCredit, 40);
			
			if(counterReached(480) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 9) {
			
			drawBlackBackGround(1f);
			
			// Scrolling the credit
			y--;
			drawString(1f, 38f, y, endCredit, 40);
		}
	}
	public boolean counterReached(int target) {
		boolean counterReached = false;
		
		counter++;
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		
		return counterReached;
	}
	public void drawBlackBackGround(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
	
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		
		for(String line: text.split("\n")) {
			int x = gp.ui.getXCenter(line);
			g2.drawString(line, x, y);
			y += lineHeight;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
} 

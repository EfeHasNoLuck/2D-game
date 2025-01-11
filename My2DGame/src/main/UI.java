package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Coin_tl;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font pixelFont;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public boolean messageOn = false;;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0; // 0-first scene, 1-second scene
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	int counter = 0;
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";

	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/PixelFont.ttf");
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create hud object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity bronzeCoin = new OBJ_Coin_tl(gp);
		coin = bronzeCoin.down1;
	}
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(pixelFont);

			// TITLE STATE
			if(gp.gameState == gp.titleState) {
				drawTitleScreen();
			}
			
			// PLAY
			if(gp.gameState == gp.playState)
			{
				drawPlayerLife();
				drawMonsterLife();
				drawMessage();
			}
			
			// PAUSE
			if(gp.gameState == gp.pauseState){
				drawPauseScreen();
			}
			
			// DIALOGUE
			if(gp.gameState == gp.dialogueState) {
				drawDialogueScreen();
			}
			
			// CHAR STATE
			if(gp.gameState == gp.characterState) {
				drawCharacterScreen();
				drawInventory(gp.player, true);
			}

			// OPTIONS
			if(gp.gameState == gp.optionsState) {
				drawOptionsScreen();
			}
			
			// GAME OVER 
			if(gp.gameState == gp.gameOverState) {
				drawGameOverScreen();
			}
			
			// TRANSITION
			if(gp.gameState == gp.transitionState) {
				drawTransition(gp.eHandler.colo);
			}
			
			// TRADE
			if(gp.gameState == gp.tradeState) {
				drawTradeScreen();
			}
		
		}	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2 - 5;
		int y = gp.tileSize/2 - 5;
		int i = 0;
		
		// draw blank
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += 35;
		}
		
		// draw reset
		x = gp.tileSize/2 - 5;
		y = gp.tileSize/2 - 5;
		i = 0;
		
		// draw current
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
		i++;
			x += 35;
		}
		
		// Draw max mana
		x = gp.tileSize/2 - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
		}
		
		//Draw mana
		x = gp.tileSize/2 - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x+=35;
		}
		
		
	}
	public void drawMonsterLife() {
		
		// Monster HP
		
		for(int i = 0; i < gp.monster[1].length; i++) {
			
			Entity monster = gp.monster[gp.currentMap][i];
			
			if(monster != null && monster.inCamera() == true) {
				
				if(monster.hpBarOn == true && monster.boss == false)
				{
					double oneScale = (double)gp.tileSize/monster.maxLife; 
					if(monster.life < 0) {monster.life = 0;}
					double hpBarValue = oneScale*monster.life;
					
					g2.setColor(new Color(35, 35, 35));
					g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-14, gp.tileSize+2, 12);
					g2.setColor(new Color(255, 0, 30));
					g2.fillRect(monster.getScreenX(), monster.getScreenY() - 13, (int)hpBarValue, 10);
					
					monster.hpBarCounter++;
					
					if(monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				else if(monster.boss == true) {
					
					double oneScale = (double)gp.tileSize*8/monster.maxLife; 
					if(monster.life < 0) {monster.life = 0;}
					double hpBarValue = oneScale*monster.life;
					
					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.tileSize*10;
					
					g2.setColor(new Color(35, 35, 35));
					g2.fillRect(x-1, y-1, gp.tileSize*8 + 2, 22);
					
					g2.setColor(new Color(255, 0, 30));
					g2.fillRect(x, y, (int)hpBarValue, 20);
					
					g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, x + 4, y - 10);
				}
			}
		}
	}
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
		
	}
	public void drawTitleScreen() {
		
		//background color
		//g2.setColor(new Color(129,19,49));
		//g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		BufferedImage bgImage = null;
		try {
			bgImage = ImageIO.read(getClass().getResourceAsStream("/ui/java_2dmenu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (bgImage != null) {
		    g2.drawImage(bgImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
		}
		//title name
		//g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "ISTÜN'e Hoş geldin";
		int x = getXCenter(text);
		int y = gp.tileSize * 3;
		
		//shadow
		//g2.setColor(Color.black);
		//g2.drawString(text, x+5, y+5);
		
		//main color
		//g2.setColor(Color.white);
		//g2.drawString(text, x, y);
		
		//image
		//x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		//y += gp.tileSize*2;
		//g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		g2.setColor(Color.white);
		
		text = "YENİ OYUN";
		x = getXCenter(text);
		y += gp.tileSize*5;
		//shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		//text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			//shadow
			g2.setColor(Color.black);
			g2.drawString(">", x+3-gp.tileSize, y+3);
			//text
			g2.setColor(Color.white);
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "KAYITLI OYUN";
		x = getXCenter(text);
		y += gp.tileSize;
		//shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		//text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			//shadow
			g2.setColor(Color.black);
			g2.drawString(">", x+3-gp.tileSize, y+3);
			//text
			g2.setColor(Color.white);
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "ÇIKIŞ";
		x = getXCenter(text);
		y += gp.tileSize;
		//shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		//text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			//shadow
			g2.setColor(Color.black);
			g2.drawString(">", x+3-gp.tileSize, y+3);
			//text
			g2.setColor(Color.white);
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "DURDURULDU";
		int x = getXCenter(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		
		int x = gp.tileSize*3;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*6);
		int height = gp.tileSize*4;
		drawSubWindow(x, y, width, height);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
//			currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if(charIndex < characters.length) {
				
				gp.playSE(24);
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
			}
			
			if(gp.keyH.enterPressed == true) {
				
				charIndex = 0;
				combinedText = "";
				
				if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
					npc.dialogueIndex++;
					gp.keyH.enterPressed = false;
				}
			}
		}
		else { // if no text in array
			npc.dialogueIndex = 0;
			
			if(gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
			}
			if(gp.gameState == gp.cutsceneState) {
				gp.csManager.scenePhase++;
			}
		}
		
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	public void drawCharacterScreen() {
		
		// Frame
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 36;
		
		// Names
		g2.drawString("Seviye", textX, textY);  textY += lineHeight;
		g2.drawString("Can", textX, textY);  textY += lineHeight;
		g2.drawString("Mana", textX, textY);  textY += lineHeight;
		g2.drawString("Kuvvet", textX, textY);  textY += lineHeight;
		g2.drawString("Çeviklik", textX, textY);  textY += lineHeight;
		g2.drawString("Saldırı", textX, textY);  textY += lineHeight;
		g2.drawString("Savunma", textX, textY);  textY += lineHeight;
		g2.drawString("XP", textX, textY);  textY += lineHeight;
		g2.drawString("Gereken XP", textX, textY);  textY += lineHeight;
		g2.drawString("Türk Lirası", textX, textY);  textY += lineHeight + 10;
		g2.drawString("Silah", textX, textY);  textY += lineHeight + 10;
		g2.drawString("Kalkan", textX, textY);  textY += lineHeight;
		

		// Values
		int tailX = (frameX + frameWidth - 30);
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize + 5, textY - 28, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize + 5, textY - 28, null);
		textY += gp.tileSize;
	}
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX = 0;
		int frameY = 0; 
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			//Frame
			frameX = gp.tileSize*12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			//Frame
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//Slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+3;
		
		//draw player's inventory
		for(int i = 0; i < entity.inventory.size(); i++) {
			
			//Equipped items
			if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield) {
				g2.setColor(new Color(228, 208, 10));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			//display amount
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32F));
				int amountX;
				int amountY;
				
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXAlignRight(s, slotX + 44);
				amountY	= slotY + gp.tileSize;
				
				// shadow 
				g2.setColor(new Color(60, 60, 60));
				g2.drawString(s, amountX, amountY);
				
				// number
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}
		
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		//Cursor
		if(cursor == true) {
			
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			//Draw Cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			//Description Frame
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*3;
			
			//Description Text
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			
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
		
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over";
		// Shadow
		g2.setColor(Color.black);
		x = getXCenter(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Tekrar Dene!";
		x = getXCenter(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		// Back to the title screen
		text = "Menüye Dön";
		x = getXCenter(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
		
		
		
	}
	public void drawOptionsScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// Title
		String text = "Seçenekler";
		textX = getXCenter(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// Full Screen .... it is not working now we had an error while doing it
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Tam Ekran", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.fullScreenOn = !gp.fullScreenOn;
				
				subState = 1;
			}
		}
		
		// Music
		textY += gp.tileSize;
		g2.drawString("Müzik", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		// SE
		textY += gp.tileSize;
		g2.drawString("Efekt", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		
		// Control
		textY += gp.tileSize;
		g2.drawString("Kontroller", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// End Game
		textY += gp.tileSize;
		g2.drawString("Oyundan Çık", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// Back
		textY += gp.tileSize * 2;
		g2.drawString("Geri", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		// Full Screen Check Box
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*2 + gp.tileSize/2;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
		}
		
		// Music Volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); 
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, gp.tileSize/2);
		
		// SE Volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, gp.tileSize/2);
		
	}
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
	//currentDialogue = "The change will take \neffect after restarting \nthe game.";
		currentDialogue = "Tam ekran özelliği \nmalesef şuan da \nçalışmamaktadır.";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
			
		// BACK 
		textY = frameY + gp.tileSize*9;
		g2.drawString("Geri", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// Title
		String text = "Kontroller";
		textX = getXCenter(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Hareket", textX, textY); textY += gp.tileSize;
		g2.drawString("Etkileşim/Saldırı", textX, textY); textY += gp.tileSize;
		g2.drawString("Büyü", textX, textY); textY += gp.tileSize;
		g2.drawString("Karakter Ekranı", textX, textY); textY += gp.tileSize;
		g2.drawString("Durdurma", textX, textY); textY += gp.tileSize;
		g2.drawString("Seçenekler", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("F", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		
		// Back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Geri", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
		
	}
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Oyundan çıkıp \nana menüye dönmek\nistediğine emin misin?";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//Yes
		String text = "Evet";
		textX = getXCenter(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.resetGame(true);
				gp.stopMusic();
				gp.playMusic(5,0.8F);
			}
		}
		
		//No
		text = "No";
		textX = getXCenter(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
		
	}
	public void drawTransition(int value) {
		
		counter++;
		g2.setColor(new Color(255, 255, 255, counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void trade_select() {
		
		npc.dialogueSet = 0;
		drawDialogueScreen();
		
		// DRAW WINDOW
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		// DRAW TEXTS
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Al", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sat", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Ayrıl", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				npc.startDialogue(npc, 1);
			}
		}

	}
	public void trade_buy() {
		// draw player inventory
		drawInventory(gp.player, false);
		// draw npc inventory
		drawInventory(npc, true);
		
		// draw hint window
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Geri", x+24, y+60);
		
		// draw player coin window
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Madeni Paran: " + gp.player.coin, x+24, y+60);
		
		// draw price window
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.tileSize*5.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXAlignRight(text, gp.tileSize*8-20);
			g2.drawString(text, x, y+34);
			
			//BUY
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					npc.startDialogue(npc,2);
					drawDialogueScreen();
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				}  
			}
		}
	}
	public void trade_sell() {
		
		// Draw Player Inventory
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;
		
		// draw hint window
		 x = gp.tileSize*2;
		 y = gp.tileSize*9;
		 width = gp.tileSize*6;
		 height = gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Geri", x+24, y+60);
		
		// draw player coin window
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Madeni Paran: " + gp.player.coin, x+24, y+60);
		
		// draw price window
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			
			x = (int)(gp.tileSize*15.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXAlignRight(text, gp.tileSize*18-20);
			g2.drawString(text, x, y+34);
			
			//SELL
			if(gp.keyH.enterPressed == true) {
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 4);
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5)); 
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXCenter(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int getXAlignRight(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}

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

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Heart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font pixelFont;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	double playTime;
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
		

	}
	
	public void ShowMessage(String text) {
		
		message = text;
		messageOn = true;
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
			}
			
			// PAUSE
			if(gp.gameState == gp.pauseState){
				drawPauseScreen();
			}
			
			// DIALOGUE
			if(gp.gameState == gp.dialogueState) {
				drawPlayerLife();
				drawDialogueScreen();
			}
			
			// Character state
			if(gp.gameState == gp.characterState) {
				drawCharacterScreen();
			}
		}	
		
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// draw blank
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize/1.4;
		}
		
		// draw reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		// draw current
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
		i++;
			x += gp.tileSize/1.4;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (bgImage != null) {
		    g2.drawImage(bgImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
		}
		//title name
		//g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "ISTUN'e Hoş geldin";
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
		
		text = "NEW GAME";
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
		
		text = "LOAD GAME";
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
		
		text = "QUIT";
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
		String text = "PAUSED";
		int x = getXCenter(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	public void drawCharacterScreen() {
		
		// Frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize - 15;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 33;
		
		// Names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
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
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Dublaj", textX, textY);
		textY += lineHeight;
		
		
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
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 20, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 20, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.dublaj.down1, tailX - gp.tileSize, textY - 20, null);
		
		
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

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

import object.OBJ_Heart;
import object.SuperObject;

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
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
	}
	
	public void ShowMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(pixelFont);
/*
		if(gameFinished == true) {
			
			g2.setFont(pixelFont);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Your TIme is:" + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			 
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			 
			gp.gameThread = null;
		}
		else {
			g2.setFont(arial_40);
			g2.setFont(pixelFont);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);
			
			//Time
			playTime += (double)1/60;
			g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);
			
			//message
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
*/
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
}

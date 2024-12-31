package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
	//Screen Settings
	
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol * tileSize; //768
	public final int screenHeight = maxScreenRow * tileSize; //576 
	
	//World Settings
	
	public int maxWorldCol; // final 50
	public int maxWorldRow; // final 50
	
	int fps = 60;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	//Entity & Object
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
		
	// Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setUpGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		playMusic(5,0.8F);
		gameState = titleState;
	}

	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() 
	{
		double drawInterval = 1000000000/fps; // 0.0166 second
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000)
			{
				{
					drawCount = 0;
					timer = 0;
				}
			}
		}
	}
	
	public void update(){

		if(gameState == playState) {	
			//player
			player.update();
			
			//npc
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			//monster
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive == true) {
						monster[i].update();
					}
					if(monster[i].alive == false && monster[i].dying == false) {
						monster[i] = null;
					}		
				}
			}
		}
		
		if(gameState == pauseState) {
			//stop
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		long start = 0;
		if(keyH.debug == true){
			start = System.nanoTime();
		}
		
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		// GAME
		else {
			// TILE
			tileM.draw(g2);

			//Add Entities to the list
			entityList.add(player); 
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			//Sort
			Collections.sort(entityList, new Comparator<Entity>() {
				
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			
			// Draw Entities
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// Empty entity list
			entityList.clear();
			
			// UI
			ui.draw(g2);
		}
		
		// TILE
	
		
		if(keyH.debug == true) {
			
			long end = System.nanoTime();
			long diff = end - start;
			
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX" + player.worldX, x, y);  y += lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y);	y += lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y);	y += lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y);	y += lineHeight;
			g2.drawString("diff: " + diff, x, y);
		}
				
		g2.dispose();
	}

	public void playMusic(int id, float volume) {
		music.setFile(id);
		music.setVolume(volume);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
	    System.out.println("Playing sound index: " + i);
		se.setFile(i);
		se.play();
	}
}

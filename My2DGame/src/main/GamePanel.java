package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
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
	Thread gameThread;
	
	//ENTITY
	public Player player = new Player(this, keyH);
	public SuperObject target[] = new SuperObject[10];
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
		
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
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
			player.update();
			//npc
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
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
		if(keyH.debug == true)
		{
			start = System.nanoTime();
		}
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		// GAME
		else {
			tileM.draw(g2);
			
			//OBJECT
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			//PLAYER
			player.draw(g2);
			
			// UI
			ui.draw(g2);
		}
		
		// TILE
	
		
		if(keyH.debug == true)
		{
			long end = System.nanoTime();
			long diff = end - start;
			g2.setColor(Color.white);
			g2.drawString("diff: " + diff, 10, 400);
			System.out.println(diff);
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
		
		se.setFile(i);
		se.play();
	}
}

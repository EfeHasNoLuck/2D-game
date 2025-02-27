package main;

import data.Progress;
import entity.Entity;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];	
	Entity eventMaster;
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int colo = 255;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 5;
			eventRect[map][col][row].height = 5;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
		setDialogue();
	}
	public void setDialogue() {
		
		eventMaster.dialogues[0][0] = "Çukura düştün! -1 can";
		
		eventMaster.dialogues[1][0] = "Şifalı su içtin.\nManan ve Canın yenilendi.\n" + "(Şu anki gelişmen kaydedildi)";
	}
	public void checkEvent() {
		
		// checking if the player is away more than 1 tile
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}

		if(canTouchEvent == true) {
				
			if(hit(0, 68, 51, "any") == true) {teleport(1, 62, 55, 18);}
			else if(hit(1, 62, 55, "any") == true) {teleport(0, 68, 51, 6);}
			else if(hit(0, 75, 34, "any") == true) {teleport(1, 71, 41, 18);}
			else if(hit(1, 71, 41, "any") == true) {teleport(0, 75, 34, 6);}
			else if(hit(0, 36, 23, "any") == true) {teleport(2, 79, 43, 18);}
			else if(hit(2, 79, 43, "any") == true) {teleport(0, 36, 23, 6);}
			else if(hit(3, 51, 43, "any") == true) {skeletonLord();}
	//else if(hit(0, 56, 90, "any") == true) {teleport(4, 46, 46, 6);}
		//	else if(hit(0, 56, 90, "any") == true) {teleport(2, 41, 39, 6);}
			else if(hit(3, 51, 28, "any") == true) {teleport(4, 46, 47, 6);}
			else if(hit(2, 42, 38, "any") == true) {teleport(3, 34, 59, 18);}
			//if(hit(0, 23, 18, "left") == true) {healingPool(gp.dialogueState);}
			//else if(hit(0, 23, 15, "any") == true) {teleport(1, 51, 18, 18);}
			//else if(hit(0, 23, 13, "any") == true) {teleport(2, 15, 15, 18);}
			//else if(hit(1, 51, 18, "any") == true) {teleport(0, 23, 15, 6);}
			//else if(hit(1, 14, 13, "any") == true) {damagePit(gp.dialogueState);}
			else if(hit(3, 64, 51, "right") == true) {speak(gp.npc[3][5]);}
		}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {	
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.y;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}

		return hit;
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.playSE(20); // cukura dusme sesi ekle
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1;
///		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(20); // sifa sesi su icme save tarzi bi ses
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}
	}
	
	public void teleport(int map, int col, int row, int sound) {
		
		
		if (map == 4) {
			colo = 0;
		}
		else {
			colo = 255;
		}
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		//gp.currentMap = map;
		//gp.player.worldX = gp.tileSize * col;
		//gp.player.worldY = gp.tileSize * row;
		//previousEventX = gp.player.worldX;
		//previousEventY = gp.player.worldY;
		
		canTouchEvent = false;
		gp.stopMusic();
		gp.playMusic(sound, 0.7F);
		gp.playSE(20); // teleport sound
	}
	
	public void speak(Entity entity) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	public void skeletonLord() {
		
		if(gp.bossBattleOn == false && Progress.skeletonLordDefetaed == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonLord;
		}
	}
}

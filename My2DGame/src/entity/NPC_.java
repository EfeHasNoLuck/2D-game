package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import object.OBJ_Hougyoku;
import object.OBJ_Key;

public class NPC_ extends Entity{

	public NPC_(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		getImage();
		setDialogue();
		dialogueSet--;
	}

	public void getImage()
	{
		up1 = setup("/npc/girl_up_1");
		up2 = setup("/npc/girl_up_2");
		down1 = setup("/npc/girl_down_1");
		down2 = setup("/npc/girl_down_2");
		left1 = setup("/npc/girl_left_1");
		left2 = setup("/npc/girl_left_2");
		right1 = setup("/npc/girl_right_1");
		right2 = setup("/npc/girl_right_2");
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Kız: Ah, sonunda geldin!";
		dialogues[0][1] = "Kız: Çantana Anahtar koydum bu anahtarla\n alt kattaki b701 kapısını açabilirsin";
		dialogues[0][2] = "Kız: Çantana Hougyoku'da koydum\nBununla anlam veremediğin\nkapıları açabilirsin";
		dialogues[0][3] = "Kız: Aaa bu arada!";
		
		dialogues[1][0] = "Her şeyin bir zamanı var, \nama zamanın ne olduğunu hiç \nkimse tam olarak bilemez...";
	}
	
	public void setAction() {
		
		if(onPath == true) {
			
		//	int goalCol = 3;
		//	int goalRow = 3;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
		}
		else {
			
			actionLockCounter++;
			
			if(actionLockCounter == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1; 
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
	}
	
	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet++;
		
//		if(dialogues[dialogueSet][0] == null) {
//			dialogueSet = 0;
//		}
		
// 		son dialogueyi donduruo onemli bi info olursa sona koyariz
		
		if(dialogueSet == 0) {
			if(itemGiven == false) {
				gp.player.inventory.add(new OBJ_Hougyoku(gp));
				gp.player.inventory.add(new OBJ_Key(gp));
			}
		}
		
		if(dialogues[dialogueSet][0] == null) {

			dialogueSet--;
		}
		
		//konusmadan sonra takip etmesi icin
		//onPath = true;
	}
}

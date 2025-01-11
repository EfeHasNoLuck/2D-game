package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Feyza_2 extends Entity {

	public NPC_Feyza_2(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	
	public void getImage()
	{
		up1 = setup("/npc/feyza_left_blink");
		up2 = setup("/npc/feyza_left_jump");
		down1 = setup("/npc/feyza_right_blink");
		down2 = setup("/npc/feyza_right_jump");
		left1 = setup("/npc/feyza_left_blink");
		left2 = setup("/npc/feyza_left_jump");
		right1 = setup("/npc/feyza_right_blink");
		right2 = setup("/npc/feyza_right_jump");
	}
	
	public void setDialogue() {
		 
		dialogues[1][0] = "Feyza: beni kandırdın... \nsenin yüzünden burada hapis kaldım.\nsen ne diyorsun be kadın!";
		dialogues[1][1] = "Hüseyin: Ne demek istiyorsun?";
		dialogues[1][2] = "Feyza: Anlamazdan gelme! \nKim olduğumu biliyordun, \nama bunu bana söylemedin. \nBu yaptığın affedilemez!";
		dialogues[1][3] = "Hüseyin: Ben... Gerçekten özür dilerim. \nBöyle bir şeyin olacağını asla düşünemedim.";
		dialogues[1][4] = "Feyza: Bunun bedelini ödeyeceksin...";
		
		dialogues[2][0] = "Feyza: Hemde canınla!";
		
		dialogues[3][0] = "(Hüseyin biraz korktu)";
		
	}

	
	
	public void setAction() {
		
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
	
	public void speak() {
	
		super.speak();
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet++;
		
		if(dialogueSet == 3) {
			this.worldX = 56*gp.tileSize;
			this.worldY = 46*gp.tileSize;
		}
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
	}
}

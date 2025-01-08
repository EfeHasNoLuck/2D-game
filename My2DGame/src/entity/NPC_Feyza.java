package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Feyza extends Entity {

	public NPC_Feyza(GamePanel gp) {
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
		
		//dialogues[0] = "Girl: hos geldin";
		//dialogues[1] = "Biz: merhaba hos buldum";
		//dialogues[2] = "Girl: gorusurz";
		//dialogues[3] = "Biz: gorusurz";
		
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
	}
}

package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_ extends Entity{

	public NPC_(GamePanel gp) {
		super(gp);
		
		direction = "up";
		speed = 1;
		
		getImage();
		setDialogue();
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
		
		dialogues[0] = "Hello lady, how is your day going\n I hope you are well asf";
		dialogues[1] = "Wassup";
		dialogues[2] = "How are you";
		dialogues[3] = "GoodBye";
		
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

package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Eray extends Entity {

	public NPC_Eray(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	
	public void getImage()
	{
		up1 = setup("/npc/eray_1");
		up2 = setup("/npc/eray_2");
		down1 = setup("/npc/eray_2");
		down2 = setup("/npc/eray_2");
		left1 = setup("/npc/eray_3");
		left2 = setup("/npc/eray_3");
		right1 = setup("/npc/eray_4");
		right2 = setup("/npc/eray_4");
	}
	
	public void setDialogue() {
		
		//dialogues[0] = "Eray: Selam naber";
		//dialogues[1] = "Biz: Iyi valla nolsun";
		//dialogues[2] = "Eray: tamam gorusurz";
		//dialogues[3] = "Biz: gorusurz";
		
	}

	
	
	public void setAction() {

		actionLockCounter++;
		int time = 4;
		
		if(actionLockCounter > 0 && actionLockCounter <= time) {
			direction = "up";
		}
		if(actionLockCounter > time && actionLockCounter <= time*2) {
			direction = "down";
		}
		if(actionLockCounter > time*2 && actionLockCounter <= time*3) {
			direction = "left";
		}
		if(actionLockCounter > time*3 && actionLockCounter <= time*4) {
			direction = "right";
		}
			
		if(actionLockCounter > time*4) {
			actionLockCounter = 0;
		}
	}
	
	public void speak() {
		
		super.speak();
	}
}

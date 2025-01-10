package entity;

import main.GamePanel;

public class NPC_Ali extends Entity{

	public NPC_Ali(GamePanel gp) {
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
		
		dialogues[0][0] = "Eray: Selam naber";
		dialogues[0][1] = "Biz: Iyi valla nolsun";
		dialogues[0][2] = "Eray: tamam gorusurz";
		dialogues[0][3] = "Biz: gorusurz";
		
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
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
		
// 		son dialogueyi donduruo onemli bi info olursa sona koyariz
//		if(dialogues[dialogueSet][0] == null) {
//			dialogueSet--;
//		
//		}
		
		//konusmadan sonra takip etmesi icin
		//onPath = true;
	}
}

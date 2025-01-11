package entity;

import main.GamePanel;

public class NPC_Makineci extends Entity {

	public NPC_Makineci(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	
	public void getImage()
	
	{
		up1 = setup("/npc/makineci_1");
		up2 = setup("/npc/makineci_1");
		down1 = setup("/npc/makineci_2");
		down2 = setup("/npc/makineci_2");
		left1 = setup("/npc/makineci_1");
		left2 = setup("/npc/makineci_1");
		right1 = setup("/npc/makineci_2");
		right2 = setup("/npc/makineci_2");
	}
	
	public void setDialogue() {
		
		dialogues[1][0] = "Hüseyin: Hayatını kurtardım, bir şey demeyecek misin?";
		dialogues[1][1] = "Makineci: Hayatımın tehlikede olduğunu hatırlamıyorum.";
		dialogues[1][2] = "Hüseyin: Az önce burası canavarlarla doluydu, \nfarkında değil misin?!";
		dialogues[1][3] = "Makineci: Ha, onları mı diyorsun?\n Merak etme canım, o yaratıklar sadece akıl sağlığı \nyerinde olan kişilere saldırırlar.";
		dialogues[1][4] = "Hüseyin: Hmm...";
		dialogues[1][5] = "Makineci: Halüsinasyonlar gördüğüm için uzun\nzaman önce insanlar tarafından dışlandım.\nBen de bu yaratıkların arasında saklanmaya\nkarar verdim.";
		dialogues[1][6] = "Hüseyin: Ne gibi halüsinasyonlardan bahsediyorsun?";
		dialogues[1][7] = "Makineci: Kampüste koşan bir anime kızı.";
		dialogues[1][8] = "Hüseyin: ...";
		dialogues[1][9] = "Makineci: O anime kızı, \nburada mekanın sahibiyle \nkonuşmanın tek yoludur yalnız.";
		dialogues[1][10] = "Hüseyin: Aga, sen dibi görmüşsün be... \n(Mırıldanarak) Makineci falan mı acaba?";
	
		dialogues[2][0] = "Makineci: EVET, nereden bildin?\n Sana minnettarım... (Kaybolur)";
		dialogues[2][1] = "İç Ses: Bari biraz konuşsaydık, ortam gitgide \nciddiyetini kaybetmeye başladı. Kampüste \nanime kızı mı arayacağım ben şimdi? Off, OFFF!";
		
		
		/*
		 * Diyalogu:
-(Makine müh.) …
-(Hüseyin)Hayatını kurtardım bir şey demeyecek misin?
-Hayatımın tehlikede olduğunu hatırlamıyorum.
-Az önce burası canavarlarla doluydu farkında değil misin?!
-Ha onları mı diyorsun merak etme canım o yaratıklar sadece akıl sağlığı yerinde olan kişilere saldırırlar.
-hmm
-Halüsinasyonlar gördüğüm için uzun zaman önce insanlar tarafından dışlandım. Ben de bu yaratıkların arasında saklanmaya karar verdim.
-Ne gibi halüsinasyonlardan bahsediyorsun.
-Kampüste koşan bir anime kızı.
-…
-O anime kızı burada mekanın sahibiyle konuşmanın tek yoludur yalnız.
-Aga sen dibi görmüşsün be.. (mırıldanarak)makineci falan mı acaba.
-EVET nereden bildin sana minnettar..(kaybolur)
-Bari 1 kelime etseydik ortam gitgide ciddiyetini kaybetmeye başladı.Kampüste anime kızı mı arayacağım ben şimdi  off OFFF

		 */
	}

	
	
	public void setAction() {

		actionLockCounter++;
		int time = 15;
		
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
		
		if(dialogueSet == 2) {
			this.worldX = 1;
			this.worldY = 1;
		}
		
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

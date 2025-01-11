package entity;

import main.GamePanel;
import object.OBJ_Axe;

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
		up2 = setup("/npc/eray_1");
		down1 = setup("/npc/eray_2");
		down2 = setup("/npc/eray_2");
		left1 = setup("/npc/eray_3");
		left2 = setup("/npc/eray_3");
		right1 = setup("/npc/eray_4");
		right2 = setup("/npc/eray_4");
	}
	public void setDialogue() {
		
		dialogues[1][0] = "Hüseyin: Merhaba!";
		dialogues[1][1] = "Yabancı: ...";
		dialogues[1][2] = "Hüseyin: Burası neresi?!";
		dialogues[1][3] = "Yabancı: (mırıldanarak) 5x30";
		dialogues[1][4] = "Hüseyin: Anlayamıyorum seni,\nyardım edecek misin, etmeyecek misin?";
		dialogues[1][5] = "Yabancı: (Mırıldanarak) Ngl bro, that’s crazy, man...";
		dialogues[1][6] = "Hüseyin: E-Eray Dayı?! Bu sen misin?";
		dialogues[1][7] = "Eray: Evet, o benim. YouTube’da 20 bin abonem var,\nkanalıma şu linkten ulaşa...";
		dialogues[1][8] = "Hüseyin: Kanka, şimdi sırası değil!\nBuradan nasıl çıkacağımızı bulmamız gerek.";
		dialogues[1][9] = "Eray: Hatırlayamayacağım kadar uzun süredir buradayım.\nBunca zamandır ne çevremdekilerden\nne de kendi kimliğimden haberim vardı...";
		dialogues[1][10] = "Eray: ta ki sen ismimi söyleyene kadar.\nBurada kimse kimseyi tanımaz;\nsen beni nasıl hatırlayabildin?";
		dialogues[1][11] = "Hüseyin: Bilmiyorum...\nMeditasyon yaparken kullandığın kelimeler...\n Sanırım ne olduysa, onları duyduktan sonra\nkim olduğun bir anda aklımda canlandı.";
		dialogues[1][12] = "Hüseyin: Neler olduğu konusunda yardım edecek misin?";
		dialogues[1][13] = "Eray: İsterdim, ancak buradaki vaktim doldu.\nSanırım buradan çıkmanın yolu kim olduğunu hatırlamak.";
		dialogues[1][14] = "Hüseyin: Ne?! Nasıl yani?";
		dialogues[1][15] = "Eray: Gidiyorum.";
		dialogues[1][16] = "Hüseyin: A-ama ben?!.";
		dialogues[1][17] = "Eray: Çantana koyduğum baltayı kullanarak\nilerideki turnikeyi geçebilirsin.";
		dialogues[1][18] = "Eray: İçeride başka insanlar da var.\nEğer teorim doğruysa, onları da kurtarabilirsin.\nGit ve onlara yardım et.";
		dialogues[1][19] = "Eray: Artık vakit geldi... (Hâlâ bir şey olmuyor.).";
		dialogues[1][20] = "Hüseyin: DUR! DAHA İŞİMİZ BİTMEDİ, ERAY DAYI!!";
		dialogues[1][21] = "Eray: Olamaz, acilen saçımı üçe vurup\nzengin olmam lazım, fr.\nTekrar karşılaşacağız, ngl.";
		
		dialogues[2][0] = "Eray: Lan, senin saçın zate... gitti... AMAN BE!";

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
		
		if(dialogueSet == 2) {
			this.worldX = 1;
			this.worldY = 1;
		}
		if(dialogueSet == 1) {
			if(itemGiven == false) {
				gp.player.inventory.add(new OBJ_Axe(gp));
			}
		}
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
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

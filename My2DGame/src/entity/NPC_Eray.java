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
		
		dialogues[0][0] = "Hüseyin: Merhaba!";
		dialogues[0][1] = "Yabancı: …";
		dialogues[0][2] = "Hüseyin: Burası neresi?!";
		dialogues[0][3] = "Yabancı: (mırıldanarak) 5x30";
		dialogues[0][4] = "Hüseyin: Anlayamıyorum seni, yardım edecek misin, etmeyecek misin?";
		dialogues[0][5] = "Yabancı: (Mırıldanarak) Ngl bro, that’s crazy, man...";
		dialogues[0][6] = "Hüseyin: E-Eray Dayı?! Bu sen misin?";
		dialogues[0][7] = "Eray: Evet, o benim. YouTube’da 20 bin abonem var, kanalıma şu linkten ulaşa...";
		dialogues[0][8] = "Hüseyin: Kanka, şimdi sırası değil! Buradan nasıl çıkacağımızı bulmamız gerek.";
		dialogues[0][9] = "Eray: Hatırlayamayacağım kadar uzun süredir buradayım.\nBunca zamandır ne çevremdekilerden ne de kendi kimliğimden haberim vardı...";
		dialogues[0][10] = "Eray: ta ki sen ismimi söyleyene kadar.\nBurada kimse kimseyi tanımaz; sen beni nasıl hatırlayabildin?";
		dialogues[0][11] = "Eray: Bilmiyorum... Meditasyon yaparken kullandığın kelimeler...\n Sanırım ne olduysa, onları duyduktan sonra kim olduğun bir anda aklımda canlandı.";
		dialogues[0][12] = "Hüseyin: Neler olduğu konusunda bana yardım edecek misin?";
		dialogues[0][13] = "Eray: İsterdim, ancak buradaki vaktim doldu.\nSanırım buradan çıkmanın yolu kim olduğunu hatırlamak.";
		dialogues[0][14] = "Hüseyin: Ne?! Nasıl yani?";
		dialogues[0][15] = "Eray: Gidiyorum.";
		dialogues[0][16] = "Hüseyin: A-ama ben?!.";
		dialogues[0][17] = "Eray: Şu aşağıdaki kartı kullanarak ilerideki turnikeyi geçebilirsin.";
		dialogues[0][18] = "Eray: İçeride başka insanlar da var. Eğer teorim doğruysa, onları da kurtarabilirsin.\nGit ve onlara yardım et. Artık vakit geldi... (Hâlâ bir şey olmuyor.).";
		dialogues[0][19] = "Hüseyin: DUR! DAHA İŞİMİZ BİTMEDİ, ERAY DAYI!!";
		dialogues[0][20] = "Eray: Olamaz, acilen saçımı üçe vurup zengin olmam lazım, fr.\nTekrar karşılaşacağız, ngl.";
		dialogues[0][21] = "Eray: Lan, senin saçın zate... gitti... AMAN BE!";
		

		
		/*
		 * -Hüseyin: Merhaba!
-Eray: …
-Hüseyin: Burası neresi?! 
-(mırıldanarak)5x30
-Anlayamıyorum seni yardım edecek misin etmeyecek misin?
-(mırıldanarak) ngl bro thats craaazy mann
-E Eray dayı ?! bu sen misin?
-evet o benim, youtube da 20 bin abonem var kanalıma şu linkten ulaşa…
-kanka şimdi sırası değil buradan nasıl çıkacağımızı bulmamız gerek. 
-Ben hatırlayamayacağım kadar uzun süredir buradayım. Bunca zamandır ne çevremdekilerden ne de kendi kimliğimden haberim vardı taa ki sen ismimi söyleyene kadar. Burada kimse kimseyi tanımaz sen beni nasıl hatırlayabildin?
-Bilmiyorum.. meditasyon yaparken kullandığın kelimeler.. sanırım ne olduysa onları duyduktan sonra kim olduğun bir anda aklımda canlandı.Bana yardım edecek misin.
-İsterdim ancak buradaki vaktim doldu sanırım buradan çıkmanın yolu kim olduğunu hatırlamak
-Ne! nasıl yani?
- Gidiyorum
-A ama ben??
-Şu aşağıdaki kartı kullanarak ilerideki turnikeyi geçebilirsin
-İçeride başka insanlara da var eğer teorim doğruysa onları da kurtarabilirsin.Git ve onlara yardım et.Artık vakit geldi..(hala bir şey olmuyor)
-DUR DAHA İŞİMİZ BİTMEDİ ERAY DAYI!!
-Olamaz acilen saçımı üçe vurup zengin olmam lazım fr Tekrar karşılaşacağız ngl.
-lan senin saçın zate… gitti.. AMAN BE!

		 */
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

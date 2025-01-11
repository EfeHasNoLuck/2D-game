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
		up1 = setup("/npc/ali_1");
		up2 = setup("/npc/ali_1");
		down1 = setup("/npc/ali_2");
		down2 = setup("/npc/ali_2");
		left1 = setup("/npc/ali_1");
		left2 = setup("/npc/ali_1");
		right1 = setup("/npc/ali_2");
		right2 = setup("/npc/ali_2");
	}
	
	public void setDialogue() {
		
		dialogues[1][0] = "Yabancı: Vaov, yeni bir yüz demek.";
		dialogues[1][1] = "Hüseyin: Dayı neden yanında excel logosu var";
		dialogues[1][2] = "Yabancı: İnsan önce bir selam verir, saygısız!";
		dialogues[1][3] = "Hüseyin: Kanka, sebebini bilmiyorum ama\nsana kibar davranmak pek içimden gelmiyor,\nkusura bakma.";
		dialogues[1][4] = "Yabancı: Alışığım buna, merak etme.";
		dialogues[1][5] = "Hüseyin: Etmiyorum zaten.";
		dialogues[1][6] = "Yabancı: Burası bir okul. Eski hayatımızda\n burada öğrenci olduğumuza inanıyorum.";
		dialogues[1][7] = "Hüseyin: Ama maalesef bu konuda hatırladığım\n tek şey yanımda duran 'E' harfi.\nBir de kesinlikle mühendis olmadığım";
		dialogues[1][8] = "Hüseyin: Dostum, sen de şu \n*ndüstricilerden misin, yoksa?";
		dialogues[1][9] = "*ndüstrici: EVET, nereden anladın?! \nHafızam yerine gelmeye başladı, \nhatta sanırım seni de hatırlıyorum. \nSen şu Dubl...";
		dialogues[1][10] = "Hüseyin: Sus, senden kendimle ilgili \nhiçbir şey duymak istemiyorum.";
		
		dialogues[2][0] = "*ndüstrici: Sen bilirsin, dostum...,\nBeni üzdün. Efe Dayım olsa \nböyle yapmazdı.(Ortadan kaybolur)";
		dialogues[2][1] = "Hüseyin: Ahh! Tam da onu hatırlamaya başlamıştım...\nKeşke bölümünü söylemeden önce ismini söyleseydi.";
		dialogues[2][2] = "Can dostumun kalbini kırdım...\n(Hüseyin, pişmanlık duyduğu için \nbir Beypazarı'nı kaybetti.)";
		dialogues[2][3] = "İç Ses: En azından şunu öğrendim: \nKonuştuğum kişiye kimliğini hatırlatmak için \nilla ismini söylememe gerek yok. Daha basit\nşeylerle de hafızalarını geri kazanabiliyorlar.";
		
/*
 -(*ndüstrici): Vaov, yeni bir yüz demek. 
-(hüseyin): Dayı neden yanında excel logosu var?
-İnsan önce selam verir, saygısız!
-Kanka gerçekten bir sebebi yok ama sana kibar davranasım gelmiyor.
-Alışığım buna merak etme
-Etmiyorum zaten.
-Burası bir okul eski hayatımızda burada öğrenci olduğumuza inanıyorum ama maalesef bu konuda hatırladığım tek şey bu yanımda duran e harfi ve kesinlikle mühendis olmadığım.
-Dostum sen de şu *ndüstricilerden misin yoksa
-EVET, nerden anladın!! Hafızam yerine gelmeye başladı hatta sanırım seni de hatırlıyorum sen şu Dubl….
-Sus senden kendim hakkında bir şey duymak istemiyorum.
-sen bilirsin dostum beni üzdün.. Efe dayım olsa böyle yapmazdı (ortadan kaybolur)
-ahh! Tam da onu hatırlamaya başlamıştım keşke bölümünü söylemeden önce ismini söyleseydi can dostumun kalbini kırdım(Hüseyin pişmanlık duyduğu için bir beypazarını kaybetti)
-En azından şunu öğrendim konuştuğum kişiye kimliğini hatırlatmak için illa ismini söylememe gerek yok daha basit şeylerden de hafızalarını geri kazanabiliyorlar.(Bu kısım yazıyla verilebilir sese gerek yok)

 */
		
	}

	
	
	public void setAction() {

		actionLockCounter++;
		int time = 10;
		
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
			gp.player.life -= 2;
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

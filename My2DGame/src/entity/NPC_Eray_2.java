package entity;

import main.GamePanel;

public class NPC_Eray_2 extends Entity {

	public NPC_Eray_2(GamePanel gp) {
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
		
		dialogues[1][0] = "Hüseyin: Of ya, bu devasa yapay zekadan\nhiçbir şey öğrenemedim. Mekânın sahibi \nbu muydu gerçekten?";
		dialogues[1][1] = "Hüseyin: Her yer bembeyaz... Neredeyim ben?";
		dialogues[1][2] =  "Eray: Sana tekrar görüşeceğiz dememiş miydim?"; 
		dialogues[1][3] = "Hüseyin: Ee, zengin oldun mu bari?";
		dialogues[1][4] = "Eray: Oldum tabii! Olmasam buraya gelir miydim?\nSeni buradan çıkaracağım, ama kolay olmayacak.";
		dialogues[1][5] = "Hüseyin: Söyle hadi, ne yapmamız gerekiyor?";
		dialogues[1][6] = "Eray: İki şey. Birincisi, seni öldürmek isteyen\nkızı bu fikrinden vazgeçirmemiz lazım.";
		dialogues[1][7] = "Eray: İkincisi, bu hiçliğin ortasında bir geçit\nkapısı var ve onu bulmamız gerekiyor.";
		dialogues[1][8] = "Hüseyin: Hangisi daha zor, bilemedim. [gülerek]";
		dialogues[1][9] = "Hüseyin: Peki ya şu arkandaki taş ne?";
		dialogues[1][10] = "Eray: O sadece bir taş değil.\nBu, hayatın yapıtaşı... Eclipse Taşı.\nGPT'yi yendiğine göre, bu gücü almayı hak ettin.";
		dialogues[1][11] = "Eray: Ama baştan söyleyeyim, \nbir efsaneye göre bu taşı almak \nölümcül sonuçlar doğurabilir.";
		dialogues[1][12] = "Hüseyin: Tamam fazla uzatma da kenara çekil,\nSen benim kim olduğumu unuttun galiba";
		dialogues[2][0] = "hadi!";
		dialogues[3][0] = "Alsana, korktun mu?";
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
			this.worldX = 57*gp.tileSize;
			this.worldY = 46*gp.tileSize;
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

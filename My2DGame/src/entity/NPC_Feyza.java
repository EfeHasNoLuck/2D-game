package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Potion_Red;

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
		 
		dialogues[1][0] = "Hüseyin: Kafayı yiyeceğim, sen niye bu kadar mutlusun?";
		dialogues[1][1] = "Yabancı: Mutlu olmamda bir sorun mu var? \n21. yüzyılda kadın olmak zaten çok zor \nve sen benim gülümsememe mi laf ediyorsun? \nHa, tartışmak mı istiyorsun benimle?";
		dialogues[1][2] = "Hüseyin: Hayır, hayır, öyle demek istemedim.\nSadece başımdan çok şey geçti, \nbiraz yorulmuşum sanırım. Kusuruma bakma.";
		dialogues[1][3] = "Yabancı: Neyse, önemli değil. Bugün iyi günümdeyim.";
		dialogues[1][4] = "Hüseyin: Etrafta bir sürü ilaç \nşişesi var. Bunlardan en azından\n bazıları bir işe yarıyor mu acaba?";
		dialogues[1][5] = "Yabancı: Evet, tabii! 2 canavar lirasi\n karşılığında sana bir can tamamlama iksiri verebilirim.";
		dialogues[1][6] = "Feyza: Hatta al yine iyisin\n denemen için 1 numune attım çantana";
		dialogues[1][7] = "(Bu kişi bir eczacı. \nHatta kim olduğunu da anladım ama \nbir süre sesimi çıkarmayacağım.)";
		dialogues[1][8] = "Feyza: Bir daha iksir lazım olursa bana uğra. Bay bay!";
		
		dialogues[2][0] = "Feyza: Bir daha iksir lazım olursa bana uğra. Bay bay!";
		
		/*
		 * "Kafayı yiyeceğim, sen niye bu kadar mutlusun?"
"Mutlu olmamda bir sorun mu var? 21. yüzyılda kadın olmak zaten çok zor ve sen bana gülümsememe mi laf ediyorsun? Ha, tartışmak mı istiyorsun benimle?"
"Hayır, hayır, öyle demek istemedim. Sadece başımdan çok şey geçti, biraz yorulmuşum sanırım. Kusuruma bakma."
"Neyse, önemli değil. Bugün iyi günümdeyim."
"Etrafta bir sürü ilaç şişesi var. Bunlardan en azından bazıları bir işe yarıyor mu acaba?"
"Evet, tabii! 2 canavar ruhu karşılığında sana bir can tamamlama veya hasar arttırıcı iksir verebilirim."
(Bu kişi bir eczacı. Hatta kim olduğunu da anladım ama bir süre sesimi çıkarmayacağım.)
"Bir daha iksir lazım olursa bana uğra. Bay bay!"
"Herkesin mi bir tahtası eksik olur? Daha fazla soru sormadığım iyi oldu. Şimdi kaybolur falan. Öğrenmem gerekenleri şu sözde mekanın sahibinden öğrenirim artık."

		 */
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
		
		if(dialogueSet == 1) {
			if(itemGiven == false) {
				gp.player.inventory.add(new OBJ_Potion_Red(gp));
			}
		}
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
	}
}

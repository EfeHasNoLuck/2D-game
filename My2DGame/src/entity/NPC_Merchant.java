package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		super(gp);
	
		direction = "down";
		speed = 0;
		
		solidArea = new Rectangle();
		solidArea.x = 8; //8
		solidArea.y = 16;  //16
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;  //32
		solidArea.height = 32;  //32
		
		getImage();
		setDialogue();
		setItems();
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
		
		dialogues[0][0] = "Demek beni buldun.\nİşe yarar bir kaç eşyam var\nTakas yapmak ister misin?";
		dialogues[0][1] = "Tekrar Beklerim!";
		dialogues[0][2] = "Bunu almak için yeterli paran yok!";
		dialogues[0][3] = "Daha fazla eşya taşıyamazsın";
		dialogues[0][4] = "Kullandığın eşyayı satamazsın!";
	}
	
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
	}

	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
	
	
	
}

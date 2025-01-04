package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = "Anahtar";
		down1 = setup("/objects/key");
		description = "[" + name + "]\nIt opens a door.";
		price = 100;
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(entity, gp.obj, "Door");
		
		if(objIndex != 999) {
			gp.ui.currentDialogue = name + "kullanarak kapıyı açtın";
			gp.playSE(21);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Ne yapıyorsun? Etrafta Kapı yok.";
			return false;
		}
	}
}

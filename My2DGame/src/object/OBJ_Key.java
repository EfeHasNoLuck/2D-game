package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	GamePanel gp;
	public static final String objName = "Anahtar";
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/key");
		description = "[" + name + "]\nIt opens a door.";
		price = 100;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {

		dialogues[0][0] = name + "kullandın ve kapıyı açtın";
		dialogues[1][0] = "Ne yapıyorsun? Etrafta Kapi \nolmadan anahtarı kullanamazsın";
	}
	
	public boolean use(Entity entity) {
		
		
		int objIndex = getDetected(entity, gp.obj, "Door");
		
		if(objIndex != 999) {
			startDialogue(this, 0);
			gp.playSE(21);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			startDialogue(this, 1);
			return false;
		}
	}
}

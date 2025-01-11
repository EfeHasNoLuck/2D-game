package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hougyoku extends Entity{
	
	GamePanel gp;
	public static final String objName = "Hougyoku";
	
	public OBJ_Hougyoku(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/hougyoku");
		description = "[" + name + "]\nBüyülü küre, ana kapılar\n için gerekebilir";
		price = 100;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {

		dialogues[0][0] = name + " kullandın ve kapıyı açtın";
		dialogues[1][0] = "Ne yapıyorsun? Bunu boşa harcamak istemezsin";
	}
	
	public boolean use(Entity entity) {
		
		
		int objIndex = getDetected(entity, gp.obj, "Door_turn");
		
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
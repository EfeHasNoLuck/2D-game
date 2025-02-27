package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red  extends Entity{
	
	GamePanel gp;
	public static final String objName = "Can iksiri";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		value = 4;
		down1 = setup("/objects/potion_red");
		description = "[Red Potion]\nHeals your life by " + value + ".";
		price = 25;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] =  name + "içtin!\n" +
				"canın " + value + "miktarında yenilendi";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		entity.life += value;
		gp.playSE(19);
		return true;
	}
}

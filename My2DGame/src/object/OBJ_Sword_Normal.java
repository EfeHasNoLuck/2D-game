package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);

		type = type_sword;
		name = "Kemal Javacinin Kilici";
		down1 = setup("/objects/sword_normal");
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nBu kilic cok oop.";
		price = 20;
		knockBackPower = 2;
	}

}

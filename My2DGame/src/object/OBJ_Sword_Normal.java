package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public static final String objName = "Kemal Javacinin Kilici";
	
	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);

		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_normal");
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nBu kilic cok oop.";
		price = 20;
		knockBackPower = 2;
		motion1_duration = 5;
		motion2_duration = 25;
	}
}

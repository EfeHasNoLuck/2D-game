package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);

		type = type_shield;
		name = "Wood Shield";
		down1 = setup("/objects/shield_normal");
		defenseValue = 1;
		description = "[" + name + "]\nMade by wood.";
	}
}

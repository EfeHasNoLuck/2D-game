package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{

	public OBJ_Axe(GamePanel gp) {
		super(gp);

		type = type_axe;
		name = "Oduncu Baltası";
		down1 = setup("/objects/axe");
		attackValue = 0;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]\nEski ama hala\niş görür.";
		price = 75;
		knockBackPower = 10;
	}

}

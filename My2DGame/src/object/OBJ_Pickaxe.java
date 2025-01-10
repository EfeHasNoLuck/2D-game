package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{

	public static final String objName = "Hamza'nın Kazması";
	
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);

		type = type_pickaxe;
		name = objName;
		down1 = setup("/objects/pickaxe");
		attackValue = 0;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]\nYeni gibi görünüyor\n taşları yıkabilirim.";
		price = 75;
		knockBackPower = 10;
		motion1_duration = 15;
		motion2_duration = 30;
	}
}

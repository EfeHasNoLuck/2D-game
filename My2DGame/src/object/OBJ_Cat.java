package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cat extends Entity {

	public static final String objName = "Cat";
	
	public OBJ_Cat(GamePanel gp) {
		super(gp);
		
		name = objName;
		down1 = setup("/objects/cat");
		
	}

}
